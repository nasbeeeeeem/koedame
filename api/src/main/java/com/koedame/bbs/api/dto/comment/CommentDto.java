package com.koedame.bbs.api.dto.comment;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public class CommentDto {
  
  private Long id;
  
  @NotBlank(message = "投稿者名は必須です")
  private String author;

  @NotBlank(message = "コメント内容は必須です")
  private String content;
  
  private LocalDateTime createdAt;

  // ゲッター/セッター
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getAuthor() { return author; }
  public void setAuthor(String author) { this.author = author; }

  public String getContent() { return content; }
  public void setContent(String content) { this.content = content; }

  public LocalDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
