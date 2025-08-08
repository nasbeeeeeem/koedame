package com.koedame.bbs.api.dto.comment;

public class CreateCommentRequest {
  private String author;
  private String content;

  // ゲッター/セッター
  public String getAuthor() { return author; }
  public void setAuthor(String author) { this.author = author; }

  public String getContent() { return content; }
  public void setContent(String content) { this.content = content; }
}
