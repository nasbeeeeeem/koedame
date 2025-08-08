package com.koedame.bbs.domain.postthread;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.koedame.bbs.domain.comment.Comment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "threads")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostThread {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments = new ArrayList<>();

  public PostThread(String title) {
    this.title = title;
    this.createdAt = LocalDateTime.now();
  }

  public void addComment(Comment comment) {
    comment.setPostThread(this);
    comments.add(comment);
  }
}
