package com.jobbook.site.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobbook.site.login.bo.KakaoLoginBO;
import com.jobbook.site.model.KakaoUser;



@Controller
public class LoginRestController {
	@Autowired
	private KakaoLoginBO kakaoLoginBO;
	
	//다시 시작
	@RequestMapping(value="/kakao_login")
	public String kakao_login() {
		System.out.println("로그인 프로세스 시작");
        StringBuffer loginUrl = new StringBuffer();
        loginUrl.append("https://kauth.kakao.com/oauth/authorize?client_id=");
        loginUrl.append("c8fbc5d8a703ef846bea8fb1443bcc32"); 
        loginUrl.append("&redirect_uri=");
        loginUrl.append("http://localhost:8080/kakao_callback"); 
        loginUrl.append("&response_type=code");
        return "redirect:"+loginUrl.toString();
    }
	


	
	@RequestMapping(value="/kakao_callback", method=RequestMethod.GET)
	  public String redirectkakao(@RequestParam String code, HttpServletRequest request) throws IOException {
		//HttpSession session 는 수업때 안씀. HttpSession session = request.getSession();
		//request를 우리가 쓰는 자바에서 사용하기 편리하도록 서블릿에서 객체화시켜서 우리한테 전달해주는것. request문자열을 우리가 보기 힘드니까 서블릿이 우리가 사용하기 편리하게 보내주는 것 
		System.out.println("코드 : " + code);
        System.out.println("카카오 콜백에서 code 프린트");
        
        //접속토큰 get
        String kakaoToken = kakaoLoginBO.getReturnAccessToken(code);
        System.out.println("카카오 토큰");
        //접속자 정보 get
        Map<String,Object> result = kakaoLoginBO.getUserInfo(kakaoToken);
        System.out.println("컨트롤러 출력"+result.get("nickname")+result.get("profile_image"));
        
		
        
        KakaoUser configVO =new KakaoUser();
        configVO.setProfile_nickname((String)result.get("nickname"));
        configVO.setProfile_image((String)result.get("profile_image"));
        /*로그아웃 처리 시, 사용할 토큰 값*/
        String kakaoid = (String)result.get("id");
        String nickname = (String)result.get("nickname");
        String profile_image = (String)result.get("profile_image");
        

        
        HttpSession session = request.getSession();
        session.setAttribute("sessionConfigVO", configVO);
        session.setAttribute("kakaoToken", kakaoToken);
        session.setAttribute("kakaoid", kakaoid);
        session.setAttribute("nickname", nickname);
        session.setAttribute("profile_image", profile_image);
        
        kakaoLoginBO.useSaveUser(kakaoid, nickname, profile_image);
        
   // return "post/timeline";
    return "redirect:/post/main_view";
    //redirect는 페이지 이동
}
	
    @RequestMapping(value="/login/logout_proc")
    public String logout(ModelMap modelMap, HttpSession session)throws IOException {
    	kakaoLoginBO.getLogout((String)session.getAttribute("kakaoToken"));
//    	if(SystemUtil.EmptyCheck((String)session.getAttribute("kakaoToken"))){
//        }else {
//        	kakaoLoginBO.getLogout((String)session.getAttribute("kakaoToken"));
//        }
    	
    	
    	 System.out.println("세션에서 정보 지우기 시작");
        //session.setAttribute("sessionConfigVO", null);
        session.removeAttribute("sessionConfigVO");
        session.removeAttribute("kakaoToken");
        session.removeAttribute("kakaoid");
        session.removeAttribute("nickname");
        session.removeAttribute("profile_image");
        System.out.println("세션에서 정보 지우기 완료");
        
        HashMap<String, String> message = new HashMap<>();
        message.put("title", "로그아웃");
        message.put("script", "location.href='/'");
        message.put("msg", "로그아웃 되었습니다");
        message.put("type","alert");
        modelMap.addAttribute("message",message);
        return "redirect:/user/login_view";
    }
}
