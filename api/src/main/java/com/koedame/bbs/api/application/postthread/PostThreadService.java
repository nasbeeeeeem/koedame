package com.koedame.bbs.api.application.postthread;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koedame.bbs.api.domain.postthread.PostThread;
import com.koedame.bbs.api.domain.postthread.PostThreadRepository;


@Service
public class PostThreadService {
  
  private final PostThreadRepository postThreadRepository;
  
  public PostThreadService(PostThreadRepository postThreadRepository) {
    this.postThreadRepository = postThreadRepository;
  }

  @Transactional(readOnly = true)
  public List<PostThread> getAllThreads() {
    return postThreadRepository.findAll();
  }

  @Transactional(readOnly = true)
  public PostThread getThreadById(Long id) {
    return postThreadRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Thread not found: " + id));
    }

  @Transactional
  public PostThread createThread(String title) {
    PostThread thread = new PostThread(title);
    return postThreadRepository.save(thread);
  }
}
