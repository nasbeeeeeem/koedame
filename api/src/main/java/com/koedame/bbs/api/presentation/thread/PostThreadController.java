package com.koedame.bbs.api.presentation.thread;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koedame.bbs.api.application.postthread.PostThreadService;
import com.koedame.bbs.api.domain.postthread.PostThread;
import com.koedame.bbs.api.dto.postthread.PostThreadDto;
import com.koedame.bbs.api.mapper.PostThreadMapper;

@RestController
@RequestMapping("/api/threads")
public class PostThreadController {
  private final PostThreadService postThreadService;
  private final PostThreadMapper postThreadMapper;

  public PostThreadController(PostThreadService postThreadService, PostThreadMapper postThreadMapper) {
    this.postThreadService = postThreadService;
    this.postThreadMapper = postThreadMapper;
  }

  @GetMapping
  public ResponseEntity<List<PostThreadDto>> getAllThreads() {
    List<PostThread> threads = postThreadService.getAllThreads();
    return ResponseEntity.ok(postThreadMapper.toDtoList(threads));
  }

  @PostMapping
  public ResponseEntity<PostThreadDto> createThread(@RequestBody CreateThreadRequest request) {
    PostThread thread = postThreadService.createThread(request.title());
    PostThreadDto dto = postThreadMapper.toDto(thread);
    return ResponseEntity.status(HttpStatus.CREATED).body(dto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostThreadDto> getThread(@PathVariable("id") Long id) {
    PostThread thread  = postThreadService.getThreadById(id);
    PostThreadDto dto = postThreadMapper.toDto(thread);
    return ResponseEntity.ok(dto);
  }

  public record CreateThreadRequest(String title) {}
}
