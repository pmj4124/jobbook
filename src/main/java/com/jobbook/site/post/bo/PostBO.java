package com.jobbook.site.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jobbook.site.like.bo.LikeBO;
import com.jobbook.site.model.Like;
import com.jobbook.site.model.Post;
import com.jobbook.site.model.PostDetail;
import com.jobbook.site.model.Reply;
import com.jobbook.site.post.dao.PostDAO;
import com.jobbook.site.reply.bo.ReplyBO;
import com.jobbook.site.tag.bo.TagBO;


@Service
public class PostBO {
	@Autowired
	private PostDAO postDAO;
	@Autowired
	private TagBO tagBO;
	@Autowired
	private LikeBO likeBO;
	@Autowired
	private ReplyBO replyBO;
	
	public int addPost(Post post) {	
		String originalURL = post.getMovieURL();
		String movieURL = originalURL.substring(originalURL.lastIndexOf("=")+1);		
		post.setMovieURL(movieURL);	
		
		postDAO.insertPost(post);
		
		String tags[] = post.getTag().split(" ");
		for(int i = 0; i<tags.length; i++) {
		tagBO.useAddTag(post.getUserId(), post.getId(), tags[i]);
		}

		return 1;	
	}//addPost
	
	public Post getPost(int id) {
		return postDAO.selectPost(id);		
	}
	
	
	public List<PostDetail> useSelectPost(String userId){
		List<Post> postList = postDAO.selectAllPost();
		List<PostDetail> postDetailList = new ArrayList<>();
		for(Post post : postList) {
			PostDetail postDetail = new PostDetail();
			postDetail.setPost(post);
			
			List<Reply> replyList = replyBO.getReplyList(post.getId());
			postDetail.setReplyList(replyList);
			
			int likeCount = likeBO.useCountLike(post.getId());
			postDetail.setLikeCount(likeCount);
			
			boolean isLike = likeBO.isLike(userId, post.getId());
			postDetail.setLike(isLike);
			
			postDetailList.add(postDetail);	
		}
		return postDetailList;
	}
	
	
	
	
	
	
}//PostBO
