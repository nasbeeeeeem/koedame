package com.koedame.bbs.api.dto.comment;

import jakarta.validation.constraints.NotBlank;

public class CreateCommentRequest {

  @NotBlank(message = "投稿者名は必須です")
  private String author;
  @NotBlank(message = "コメント内容は必須です")
  private String content;

  // ゲッター/セッター
  public String getAuthor() { return author; }
  public void setAuthor(String author) { this.author = author; }

  public String getContent() { return content; }
  public void setContent(String content) { this.content = content; }
}
