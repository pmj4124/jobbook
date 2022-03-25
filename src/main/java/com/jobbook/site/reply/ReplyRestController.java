package com.jobbook.site.reply;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobbook.site.reply.bo.ReplyBO;



@RestController
@RequestMapping("/reply")
public class ReplyRestController {
	@Autowired
	private ReplyBO replyBO;
	

	
	@PostMapping("/add")
	public Map<String, String> add(
			@RequestParam("postId") int postId,
			@RequestParam("replyContent") String replyContent,
			HttpServletRequest request
			){

	HttpSession session = request.getSession();
	String userId = (String)session.getAttribute("kakaoid");
	String userName = (String)session.getAttribute("nickname");
	int count = replyBO.useAddReply(userId, userName, postId, replyContent);
	
	Map<String, String> result = new HashMap<>();
	if(count == 1) {
		result.put("result", "success");
	}else {
		result.put("result", "fail");
	}
	return result;
	}
	
	

	
	
	
	
	
	
}
