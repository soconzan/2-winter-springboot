package com.example.myschool.post.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.myschool.post.controller.PostForm;
import com.example.myschool.post.domain.Post;
import com.example.myschool.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

	private final PostRepository postRepository;
	
	// 글 전체 조회 (최신순)
	public List<Post> getPosts() {
		return postRepository.findAllByOrderByDateDesc();
	}

	// 글 조회
	public Post getPost(int id) {
		return postRepository.findById(id).get();
	}
	
	// 글 등록
	public Post save(Post post) {
		postRepository.save(post);
		return post;
	}
	
	// 글 삭제
	public void deletePost(int id) {
		postRepository.deleteById(id);
	}
	
	// 글 수정
	public void updatePost(int id, PostForm form) {
		Post post = this.getPost(id);
		
		post.setTitle(form.getTitle());
		post.setContent(form.getContent());
		
		this.save(post);
	}

}
