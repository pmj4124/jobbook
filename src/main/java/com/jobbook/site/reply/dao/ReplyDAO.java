package com.jobbook.site.reply.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jobbook.site.model.Reply;

@Repository
public interface ReplyDAO {
	public int addReply(
			@Param("userId") String userId,
			@Param("userName") String userName,
			@Param("postId") int postId,
			@Param("replyContent") String replyContent
			);
	
	public List<Reply> selectReplyList(
			@Param("postId") int postId
			);
	
	public int deleteReply(
			@Param("userId") String userId,
			@Param("replyId") int replyId
			);
	
	public Reply selectReply(
			@Param("replyId") int replyId
			);
}
