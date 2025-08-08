package com.koedame.bbs.domain.postthread;

import java.util.List;
import java.util.Optional;

public interface PostThreadRepository {
  Optional<PostThread> findById(Long id);

  List<PostThread> findAll();

  PostThread save(PostThread thread);

  void deleteById(Long id);
}
