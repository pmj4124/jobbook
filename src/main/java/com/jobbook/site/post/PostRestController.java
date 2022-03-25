package com.jobbook.site.post;

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

import com.jobbook.site.model.KakaoUser;
import com.jobbook.site.model.Post;
import com.jobbook.site.post.bo.PostBO;
import com.jobbook.site.tag.bo.TagBO;


@RestController
@RequestMapping("/post")
public class PostRestController {
	@Autowired
	private PostBO postBO;
	@Autowired
	private TagBO tagBO;
	
	
	@PostMapping("/create")
	public Map<String, String> create(
		HttpServletRequest request,
		@ModelAttribute Post post,
		Model model
		){
		System.out.println("입력시작");
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("kakaoid");		
		
		KakaoUser sessionConfigVO = (KakaoUser)session.getAttribute("sessionConfigVO");
	    String kakaoToken = (String)session.getAttribute("kakaoToken");
	    String nickname = (String)session.getAttribute("nickname");
	    String profile_image = (String)session.getAttribute("profile_image");
	 
		
		post.setUserId(userId);
		post.setUserName(nickname);
		
		int count = postBO.addPost(post);

		Post postResult = postBO.getPost(post.getId());
		model.addAttribute("postResult", postResult);
		
	
		Map<String, String> result = new HashMap<>();
		if(count == 1) {
			result.put("result", "success");
		}else {
			result.put("result", "fail");
		}
		return result;
	}
	

	
	
}
