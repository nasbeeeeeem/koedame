package com.koedame.bbs.api.presentation.thread;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koedame.bbs.api.application.postthread.PostThreadService;
import com.koedame.bbs.api.domain.postthread.PostThread;

@RestController
@RequestMapping("/api/threads")
public class PostThreadController {
  private final PostThreadService postThreadService;

  public PostThreadController(PostThreadService postThreadService) {
    this.postThreadService = postThreadService;
  }

  @GetMapping
  public ResponseEntity<List<PostThread>> getAllThreads() {
    return ResponseEntity.ok(postThreadService.getAllThreads());
  }

  @PostMapping
  public ResponseEntity<PostThread> createThread(@RequestBody CreateThreadRequest request) {
      PostThread thread = postThreadService.createThread(request.title());
      return ResponseEntity.ok(thread);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostThread> getThread(@PathVariable("id") Long id) {
      return ResponseEntity.ok(postThreadService.getThreadById(id));
  }

  public record CreateThreadRequest(String title) {}
}
