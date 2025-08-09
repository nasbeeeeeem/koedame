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
import com.koedame.bbs.api.common.response.ApiResponse;
import com.koedame.bbs.api.domain.postthread.PostThread;
import com.koedame.bbs.api.dto.postthread.CreatePostThreadRequest;
import com.koedame.bbs.api.dto.postthread.PostThreadDto;
import com.koedame.bbs.api.mapper.PostThreadMapper;

import jakarta.validation.Valid;

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
  public ResponseEntity<ApiResponse<List<PostThreadDto>>> getAllThreads() {
    List<PostThread> threads = postThreadService.getAllThreads();
    List<PostThreadDto> dtos = postThreadMapper.toDtoList(threads);
    return ResponseEntity.ok(ApiResponse.success("OK", dtos));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<PostThreadDto>> createThread(@RequestBody @Valid CreatePostThreadRequest request) {
    PostThread thread = postThreadService.createThread(request.getTitle());
    PostThreadDto dto = postThreadMapper.toDto(thread);
    ApiResponse<PostThreadDto> body = ApiResponse.success("Thread created", dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(body);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<PostThreadDto>> getThread(@PathVariable("id") Long id) {
    PostThread thread  = postThreadService.getThreadById(id);
    PostThreadDto dto = postThreadMapper.toDto(thread);
    return ResponseEntity.ok(ApiResponse.success("OK", dto));
  }
}
