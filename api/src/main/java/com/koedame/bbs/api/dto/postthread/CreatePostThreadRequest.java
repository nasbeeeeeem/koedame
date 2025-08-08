package com.koedame.bbs.api.dto.postthread;

import jakarta.validation.constraints.NotBlank;

public class CreatePostThreadRequest {

  @NotBlank(message = "タイトルは必須です")
  private String title;

  // ゲッター／セッター
  public String getTitle() {
      return title;
  }

  public void setTitle(String title) {
      this.title = title;
  }
}
