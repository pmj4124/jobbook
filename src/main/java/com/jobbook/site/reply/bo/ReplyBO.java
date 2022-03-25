package com.jobbook.site.reply.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobbook.site.model.Reply;
import com.jobbook.site.reply.dao.ReplyDAO;


@Service
public class ReplyBO {
	@Autowired
	private ReplyDAO replyDAO;
	
	public int useAddReply(String userId, String userName, int postId, String replyContent) {
		return replyDAO.addReply(userId, userName, postId, replyContent);
	}
	
	
	public List<Reply> getReplyList(int postId){
		return replyDAO.selectReplyList(postId);
	};
	
	
}
