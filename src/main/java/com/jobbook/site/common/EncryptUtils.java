package com.jobbook.site.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
	//암호화 메소드
	//static키워드를 붙여주면 객체생성 없이 바로 쓸 수 있다. 
	public static String md5(String message) {
		String encData = "";
		try {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] bytes = message.getBytes();
		// 00000000 8개의 이진수로 만들어진 숫자. 
		//asdf
		//[00000000, 00000000, 000000000, 00000000]
		//a라는 문자를 byte형태로 바꾸는 것.. 
		
		md.update(bytes);
		byte[] digest = md.digest();
		
		//숫자 -> 문자열
		for(int i = 0; i < digest.length; i++) {
			encData += Integer.toHexString(digest[i] & 0xff);
			//00101000
			//00110100
			//00100000
			//이진수를 &로 연산을 하게 되면 ... ~~연산을 해나감. 곱하기인가?
		}
		
		
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
			
		return encData;
	}

}
