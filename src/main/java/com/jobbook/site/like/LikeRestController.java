package com.jobbook.site.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobbook.site.like.bo.LikeBO;
import com.jobbook.site.model.KakaoUser;
import com.jobbook.site.model.Like;



@RestController
@RequestMapping("/like")
public class LikeRestController {
	@Autowired
	private LikeBO likeBO;
	
	@PostMapping("/onLike")
	public Map<String, Boolean> findLike(
			@RequestParam("postId") int postId, 
			@ModelAttribute Like like,
			HttpServletRequest request,
			Model model
			){
		HttpSession session = request.getSession();
		
		KakaoUser sessionConfigVO = (KakaoUser)session.getAttribute("sessionConfigVO");
	    String kakaoToken = (String)session.getAttribute("kakaoToken");
	    String nickname = (String)session.getAttribute("nickname");
	    String profile_image = (String)session.getAttribute("profile_image");
	    String userId = (String)session.getAttribute("kakaoid");
	
	    like.setUserId(userId);
	    like.setPostId(postId);
	   
		boolean isLike = likeBO.useLike(userId, postId);
		Map<String, Boolean> result = new HashMap<>();
		result.put("isLike", isLike);
		return result;
	}
	
}
