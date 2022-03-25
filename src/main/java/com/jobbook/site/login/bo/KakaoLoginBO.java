package com.jobbook.site.login.bo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jobbook.site.login.dao.KakaoLoginDAO;


@Service
public class KakaoLoginBO {
	@Autowired
	private KakaoLoginDAO kakaoLoginDAO;
	
	  public String getReturnAccessToken(String code) {
		  System.out.println("getReturnAccessToken 시작");
	         String access_token = "";
	         String refresh_token = "";
	         String reqURL = "https://kauth.kakao.com/oauth/token";
	        
	        try {
	            URL url = new URL(reqURL);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            System.out.println("유알엘 : " + url);
	            System.out.println("콘 : " + conn);
	             //HttpURLConnection 설정 값 셋팅
	            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
	             conn.setRequestMethod("POST");
	             conn.setDoOutput(true);
	             
	             
	             // buffer 스트림 객체 값 셋팅 후 요청
	             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	             StringBuilder sb = new StringBuilder();
	             sb.append("grant_type=authorization_code");
	             sb.append("&client_id=c8fbc5d8a703ef846bea8fb1443bcc32");  //앱 KEY VALUE
	             sb.append("&redirect_uri=http://localhost:8080/kakao_callback"); // 앱 CALLBACK 경로
	             sb.append("&code=" + code);
	             bw.write(sb.toString());
	             bw.flush();
	             

	             //결과 코드가 200이라면 성공
	             int responseCode = conn.getResponseCode();
	             System.out.println("결과코드 : " + responseCode);

	             //  RETURN 값 result 변수에 저장
	             BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	             String br_line = "";
	             String result = "";
	 
	             while ((br_line = br.readLine()) != null) {
	                 result += br_line;
	             }
	             //여기 하고 있었음
	             //프로젝트에 잭슨 라이브러리 내장되어 있음
	             //잭슨 제이슨 파서 
	            System.out.println(result);
	            JsonParser parser = new JsonParser();
	            JsonElement element = parser.parse(result);
	 
	             
	             // 토큰 값 저장 및 리턴
	             access_token = element.getAsJsonObject().get("access_token").getAsString();
	           
	             br.close();
	             bw.close();
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	 
	         return access_token;
	     }

	    
	    
	    
		
		//@Override
	    public Map<String,Object> getUserInfo(String access_token) {
	        Map<String,Object> resultMap =new HashMap<>();
	        String reqURL = "https://kapi.kakao.com/v2/user/me";
	         try {
	             URL url = new URL(reqURL);
	             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	             conn.setRequestMethod("GET");
	             System.out.println("유저 인포 받기 시작");
	 
	            //요청에 필요한 Header에 포함될 내용
	             conn.setRequestProperty("Authorization", "Bearer " + access_token);
	 
	             int responseCode = conn.getResponseCode();
	             System.out.println("리스폰스코드 : " + responseCode);
	 
	             BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	 
	             String br_line = "";
	             String result = "";
	 
	             while ((br_line = br.readLine()) != null) {
	                 result += br_line;
	             }
	             
	            System.out.println("리스폰스:" + result);
	 
	             JsonParser parser = new JsonParser();
	             JsonElement element = parser.parse(result);
	 
	             JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
	             JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
	             String id = element.getAsJsonObject().get("id").getAsString();
	             System.out.println("아이디: " + id);
	             String nickname = properties.getAsJsonObject().get("nickname").getAsString();
	             String profile_image = properties.getAsJsonObject().get("profile_image").getAsString();
	             resultMap.put("id", id);
	             resultMap.put("nickname", nickname);
	             resultMap.put("profile_image", profile_image);
	             
	         } catch (IOException e) {
	             // TODO Auto-generated catch block
	             e.printStackTrace();
	         }
	         System.out.println("유저 인포 가져오기 리턴");
	         return resultMap;
	     }

	    public int useSaveUser(String kakaoid, String nickname, String profile_image) {
//	    	public int useSaveUser(KakaoUser configVO) {
	    	return kakaoLoginDAO.saveUser(kakaoid, nickname, profile_image);

	    }
	    
	    
	    //@Override
	    public void getLogout(String access_token) {
	    	 System.out.println("로그아웃 시작" );
	        String reqURL ="https://kapi.kakao.com/v1/user/logout";
	        try {
	            URL url = new URL(reqURL);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("POST");
	            
	            conn.setRequestProperty("Authorization", "Bearer " + access_token);
	            int responseCode = conn.getResponseCode();
	            System.out.println("로그아웃 리스폰스 코드 : " + responseCode);
	 
	            if(responseCode ==400)
	                throw new RuntimeException("카카오 로그아웃 도중 오류 발생");
	            
	            
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            
	            String br_line = "";
	            String result = "";
	            while ((br_line = br.readLine()) != null) {
	                result += br_line;
	            }
	            System.out.println("로그아웃 결과");
	            System.out.println(result);
	        }catch(IOException e) {
	            
	        }
	    }
}
