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
import com.koedame.bbs.api.domain.comment.Comment;

@RestController
@RequestMapping("/api/threads/{threadId}/comments")
public class CommentController {
  
  private final CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping
  public ResponseEntity<List<Comment>> getComments(@PathVariable("id") Long threadId) {
    return ResponseEntity.ok(commentService.getCommentsByThreadId(threadId));
  }

  @PostMapping
  public ResponseEntity<Comment> addComment(@PathVariable("id") Long threadId, @RequestBody AddCommentRequest request) {
    Comment comment = commentService.addComment(threadId, request.author(), request.content());
    return ResponseEntity.ok(comment);
  }

  public record AddCommentRequest(String author, String content) {}
}
