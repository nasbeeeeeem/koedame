package com.koedame.bbs.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.koedame.bbs.api.domain.comment.Comment;
import com.koedame.bbs.api.dto.comment.CommentDto;

@Mapper(componentModel = "spring")
public interface CommentMapper {
  CommentDto toDto(Comment comment);

  List<CommentDto> toDtoList(List<Comment> comments);
}
