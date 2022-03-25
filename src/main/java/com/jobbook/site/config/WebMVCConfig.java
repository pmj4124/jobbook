package com.jobbook.site.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jobbook.site.common.FileManagerService;
import com.jobbook.site.common.PermissionInterceptor;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
	
	@Autowired
	PermissionInterceptor permissionInterceptor;
	
	//컴퓨터(서버) 내 특정 경로를 클라이언트(브라우저)에서 특정 path로 접근하도록 하는 설정
	@Override
	public void addResourceHandlers (ResourceHandlerRegistry registry) {
		//registry라는 곳에다가 설정 추가
		registry.addResourceHandler("/images/**")//클라이언트에서 접근하도록하는 path
		//.addResourceLocations("file:///경로복사"); // 이 파일을 위 경로로 접근할 수 있게 한다... images만 붙이면 접근 가능하게 한다. 
		//똑같은 내용이 다른 파일에 나눠져있으면 실수할 가능성 높음. static값을 그냥 가져오는게 안전. 
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH);
	}
	
	
	
	//로그인 안된상태에서 에러 뜨는거 없애기
	//모든 페이지가 우리가 만든 인터셉터를 통해서 거쳐 가도록 설정
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(permissionInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/static/**", "/images/**", "/user/sign_out");
	
	}
	
	
}
