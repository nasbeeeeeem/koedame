package com.koedame.bbs.api.dto.postthread;

import java.time.LocalDateTime;

public class PostThreadDto {
  private Long id;
  private String title;
  private LocalDateTime createdAt;

  // ゲッター／セッター
  public Long getId() {
      return id;
  }

  public void setId(Long id) {
      this.id = id;
  }

  public String getTitle() {
      return title;
  }

  public void setTitle(String title) {
      this.title = title;
  }

  public LocalDateTime getCreatedAt() {
      return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
  }
}


