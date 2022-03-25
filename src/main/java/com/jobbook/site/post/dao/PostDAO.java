package com.jobbook.site.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jobbook.site.model.Post;


@Repository
public interface PostDAO {
	public int insertPost(
			Post post
			);
	

	public Post selectPost(
			@Param("id") int id);
	

	public List<Post> selectAllPost();
	
	public Post selectPostByTags(
			@Param("searchTag") String tag
			);

}
