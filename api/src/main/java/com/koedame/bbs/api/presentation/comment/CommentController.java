package com.koedame.bbs.api.presentation.comment;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koedame.bbs.api.application.comment.CommentService;
import com.koedame.bbs.api.application.postthread.PostThreadService;
import com.koedame.bbs.api.domain.comment.Comment;
import com.koedame.bbs.api.dto.comment.CommentDto;
import com.koedame.bbs.api.mapper.CommentMapper;

@RestController
@RequestMapping("/api/threads/{threadId}/comments")
public class CommentController {

  private final CommentService commentService;
  private final CommentMapper commentMapper;

  public CommentController(CommentService commentService, PostThreadService postThreadService, CommentMapper commentMapper) {
    this.commentService = commentService;
    this.commentMapper = commentMapper;
  }

  @GetMapping
  public List<CommentDto> getComments(@PathVariable("id") Long threadId) {
    List<Comment> comments = commentService.getCommentsByThreadId(threadId);
    return commentMapper.toDtoList(comments);
  }

  @PostMapping
  public CommentDto addComment(@PathVariable("id") Long threadId, @RequestBody AddCommentRequest request) {
    Comment comment = commentService.addComment(threadId, request.author(), request.content());
    return commentMapper.toDto(comment);
  }

  public record AddCommentRequest(String author, String content) {}
}
