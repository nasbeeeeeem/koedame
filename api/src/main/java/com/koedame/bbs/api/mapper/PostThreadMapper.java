package com.koedame.bbs.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.koedame.bbs.api.domain.postthread.PostThread;
import com.koedame.bbs.api.dto.postthread.PostThreadDto;

@Mapper(componentModel = "spring")
public interface PostThreadMapper {

  PostThreadDto toDto(PostThread entity);

  List<PostThreadDto> toDtoList(List<PostThread> threads);
}
