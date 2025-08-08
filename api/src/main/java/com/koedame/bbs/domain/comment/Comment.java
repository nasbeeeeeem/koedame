package com.koedame.bbs.domain.comment;

import java.time.LocalDateTime;

import com.koedame.bbs.domain.postthread.PostThread;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String author;
  
  private String content;

  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "thread_id", nullable = false)
  private PostThread postThread;

  public Comment(String author, String content) {
    this.author = author;
    this.content = content;
    this.createdAt = LocalDateTime.now();
  }

  public void setPostThread(PostThread postThread) {
    this.postThread = postThread;
  }
}
