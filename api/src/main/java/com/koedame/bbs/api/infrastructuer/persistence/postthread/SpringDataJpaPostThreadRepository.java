package com.koedame.bbs.api.infrastructuer.persistence.postthread;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koedame.bbs.api.domain.postthread.PostThread;

public interface SpringDataJpaPostThreadRepository extends JpaRepository<PostThread, Long> {
    // 必要があれば独自クエリを定義できます
}
