package com.jobbook.site.tag.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobbook.site.like.bo.LikeBO;
import com.jobbook.site.model.Post;
import com.jobbook.site.model.PostDetail;
import com.jobbook.site.model.Reply;
import com.jobbook.site.model.Tag;
import com.jobbook.site.post.dao.PostDAO;
import com.jobbook.site.reply.bo.ReplyBO;
import com.jobbook.site.tag.dao.TagDAO;


@Service
public class TagBO {
	@Autowired
	private TagDAO tagDAO;
	@Autowired
	private ReplyBO replyBO;
	@Autowired
	private PostDAO postDAO;
	@Autowired
	private LikeBO likeBO;
	
	public int useAddTag(String userId, int postId, String tag) {		
		return tagDAO.addTag(userId, postId, tag);
	}
	
	public List<PostDetail> useSearchTag(String userId, String searchTag){
		System.out.println("Tag BO 시작");
		
		String searchTags[] = searchTag.split(" ");
		for(String tag : searchTags) {
			
		}
		
		//태그로 다오에서 검색해서 가져온 태그리스트
		List<Tag> tagList =  tagDAO.searchTag(searchTag);
		System.out.println("tagList : " + tagList);
		
		
		List<Integer> postIdList =new ArrayList<>();
		List<PostDetail> postDetailList = new ArrayList<>();
		for(Tag tag : tagList) {
			//태그리스트에서 그 태그에 해당하는 postId꺼내서 post를 가져옴
			Post post = postDAO.selectPost(tag.getPostId());
			System.out.println(post);
			//postDetail에 해당 post를 넣음
			PostDetail postDetail = new PostDetail();
			postDetail.setPost(post);
			//postDetailList에 담음
			System.out.println("postDetail : " + postDetail);
			postDetailList.add(postDetail);	
			
		}

		System.out.println("postDetailList : " + postDetailList);

		
		return postDetailList;
	}
	
	//검색어가 여러개일때
	//public List<PostDetail> useSearchTags(String userId, String searchTag){
		//post 테이블에서 tag를 가져와서 쪼갠다.
		
		//postDAO.selectPostByTags(searchTag);
		
		//입력된 tag값을 쪼갠다.
		
		//입력된 tag값을 모두 포함하고 있는 post를 가져와서 postDetailList에 넣어준다.
//	}


}
