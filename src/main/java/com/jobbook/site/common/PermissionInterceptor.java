package com.jobbook.site.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



//스프링빈이라는 곳에 클래스를 등록해놔야함. .. 스프링 빈에 등록하는 기능이 포함되어있어서 autowired로 사용할수있었음. 그런데 인터셉터는 그 어노테이션이 없음.. 
//controller, service는 다 기능이 있는 어노테이션.. 
@Component
//service의 부모임. 그냥 스프링 빈에 등록하기 위한것. 등록하면  autowired통해 사용가능
public class PermissionInterceptor implements HandlerInterceptor {

	//요청이 들어올 때 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		
		//로그인 정보
		HttpSession session = request.getSession();
		
		//현재 요청한 url에 path(uri) ex) /user/signin_view
		String uri = request.getRequestURI();

		//로그인 상태
		if(session.getAttribute("kakaoToken") != null ) {
			//로그인 화면과 회원가입 화면 접근 못하게
			//리스트 화면으로 이동
			
		//	if(uri.startsWith("/user/")) {
				if(uri.startsWith("/other/")) {
				//리스트 페이지로 이동 
				response.sendRedirect("/post/main_view");//에러 뜨면 add throws함 
				return false;//목표로 했던 컨트롤러로 안가고 그냥 response. 위 리다이렉트 대로 이동하게 됨. 
			}
			
			
		}else {//비로그인 상태
			//리스트 화면, 디테일 화면
			//로그인 페이지로 이동 
			if(uri.startsWith("/post/")) {
				response.sendRedirect("/user/login_view");
				return false; 
			}
			
		}
		
		return true;
			
		
	}
	
	
	//response 처리할 때
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) {
		
	}
	
	
	//모든 것이 완료 되었을 때 
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response
			,Object handler, Exception ex) {
		
	}
	
	
}
