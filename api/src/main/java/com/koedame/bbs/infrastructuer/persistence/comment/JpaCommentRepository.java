package com.koedame.bbs.infrastructuer.persistence.comment;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.koedame.bbs.domain.comment.Comment;
import com.koedame.bbs.domain.comment.CommentRepository;

@Repository
public class JpaCommentRepository implements CommentRepository {

    private final SpringDataJpaCommentRepository jpaRepository;

    public JpaCommentRepository(SpringDataJpaCommentRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Comment> findByPostThreadId(Long postThreadId) {
        return jpaRepository.findByPostThread_IdOrderByCreatedAtAsc(postThreadId);
    }

    @Override
    public Comment save(Comment comment) {
        return jpaRepository.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
