package com.koedame.bbs.api.domain.comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

  Optional<Comment> findById(Long id);

  List<Comment> findByPostThreadId(Long postThreadId);

  Comment save(Comment comment);

  void deleteById(Long id);
}
