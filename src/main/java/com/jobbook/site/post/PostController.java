package com.jobbook.site.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobbook.site.model.KakaoUser;
import com.jobbook.site.model.PostDetail;
import com.jobbook.site.post.bo.PostBO;
import com.jobbook.site.tag.bo.TagBO;




@Controller
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostBO postBO;
	@Autowired
	private TagBO tagBO;
	
	@GetMapping("/main_view")
	public String mainView(HttpServletRequest request, Model model
			,@RequestParam(value="searchTag", required=false) String searchTag
			
			) {	
		HttpSession session = request.getSession();
		
		KakaoUser sessionConfigVO = (KakaoUser)session.getAttribute("sessionConfigVO");
	    String kakaoToken = (String)session.getAttribute("kakaoToken");
	    String nickname = (String)session.getAttribute("nickname");
	    String profile_image = (String)session.getAttribute("profile_image");
	    String userId = (String)session.getAttribute("kakaoid");
	 
	    if(searchTag == null) {
			List<PostDetail> postList = postBO.useSelectPost(userId);
			model.addAttribute("postList", postList);
 	
	    }else {
	    	 List<PostDetail> postList = tagBO.useSearchTag(userId, searchTag);
	 	    model.addAttribute("postList", postList);
	    }
		return "post/main";
	}
}
