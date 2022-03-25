package com.jobbook.site.login.dao;

import org.apache.ibatis.annotations.Param;

import com.jobbook.site.model.KakaoUser;



public interface KakaoLoginDAO {
	public int insertUser(
			@Param("code") String code
			//@Param("loginId") String loginId
			);
	
	public KakaoUser selectUser(
			//@Param("loginId") String loginId,
			@Param("code") String code
			);
	
	public int saveUser(
		//	@Param("configVO") KakaoUser configVO
				@Param("kakaoid") String kakaoid,
				@Param("nickname") String nickname,
				@Param("profile_image") String profile_image
				
			);
			
}
