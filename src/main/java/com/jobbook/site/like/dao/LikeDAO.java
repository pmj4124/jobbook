package com.jobbook.site.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	public int countLike(
			@Param("postId") int postId
			);
	
	public int selectLikeCountByUserId(
			@Param("userId") String userId,
			@Param("postId") int postId
			);
	public int like(
			@Param("userId") String userId,
			@Param("postId") int postId
			);
	
	public int deleteLike(
			@Param("userId") String userId,
			@Param("postId") int postId
			);
	
	

}
