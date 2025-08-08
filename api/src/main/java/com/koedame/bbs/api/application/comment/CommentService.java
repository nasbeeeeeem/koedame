package com.koedame.bbs.api.application.comment;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koedame.bbs.api.domain.comment.Comment;
import com.koedame.bbs.api.domain.comment.CommentRepository;
import com.koedame.bbs.api.domain.postthread.PostThread;
import com.koedame.bbs.api.domain.postthread.PostThreadRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostThreadRepository postThreadRepository;

    public CommentService(CommentRepository commentRepository, PostThreadRepository postThreadRepository) {
        this.commentRepository = commentRepository;
        this.postThreadRepository = postThreadRepository;
    }

    @Transactional(readOnly = true)
    public List<Comment> getCommentsByThreadId(Long threadId) {
        return commentRepository.findByPostThreadId(threadId);
    }

    @Transactional
    public Comment addComment(Long threadId, String author, String content) {
        PostThread thread = postThreadRepository.findById(threadId)
                .orElseThrow(() -> new IllegalArgumentException("Thread not found: " + threadId));

        Comment comment = new Comment(thread, author, content);
        return commentRepository.save(comment);
    }
}
