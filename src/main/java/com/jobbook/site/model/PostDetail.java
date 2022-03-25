package com.jobbook.site.model;

import java.util.List;

public class PostDetail {
	//데이터를 저장하는 것을 DTO라고 함	
	//포스트 하나 + 댓글 리스트 + 좋아요 갯수
	private Post post;
	private List<Reply> replyList;
	private int likeCount;
	private boolean isLike;
	
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public List<Reply> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public boolean isLike() {
		return isLike;
	}
	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}
	

	
	
	
	
	
}
