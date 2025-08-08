package com.koedame.bbs.api.infrastructuer.persistence.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koedame.bbs.api.domain.comment.Comment;

public interface SpringDataJpaCommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findByPostThread_IdOrderByCreatedAtAsc(Long postThreadId);

}
