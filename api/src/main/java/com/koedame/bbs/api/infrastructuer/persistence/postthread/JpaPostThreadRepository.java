package com.koedame.bbs.api.infrastructuer.persistence.postthread;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.koedame.bbs.api.domain.postthread.PostThread;
import com.koedame.bbs.api.domain.postthread.PostThreadRepository;

@Repository
public class JpaPostThreadRepository implements PostThreadRepository {

    private final SpringDataJpaPostThreadRepository jpaRepository;

    public JpaPostThreadRepository(SpringDataJpaPostThreadRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<PostThread> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<PostThread> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public PostThread save(PostThread thread) {
        return jpaRepository.save(thread);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
