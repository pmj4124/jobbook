package com.jobbook.site.tag.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jobbook.site.model.Tag;

@Repository
public interface TagDAO {
	public int addTag(
			@Param("userId") String userId,
			@Param("postId") int postId,
			@Param("tag") String tag
			);
	public List<Tag> searchTag(@Param("searchTag") String searchTag);


}
