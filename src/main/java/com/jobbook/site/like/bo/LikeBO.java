package com.jobbook.site.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobbook.site.like.dao.LikeDAO;
import com.jobbook.site.model.Like;



@Service
public class LikeBO {
	@Autowired
	private LikeDAO likeDAO;

	public boolean useLike(String userId, int postId) {
		if(this.isLike(userId, postId)) {//좋아요 상태 -> 좋아요 취소 
			likeDAO.deleteLike(userId, postId);
			return false;
		}else {//좋아요 아닐 때 -> 좋아요 
			likeDAO.like(userId, postId);
			return true;
		}		
	}
	
	public int useCountLike(int postId) {
		return likeDAO.countLike(postId);
	}
	
	public boolean isLike(String userId, int postId) {
		return !(likeDAO.selectLikeCountByUserId(userId, postId)==0);
	}
	
	

}
