package com.koedame.bbs.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koedame.bbs.api.application.postthread.PostThreadService;
import com.koedame.bbs.api.mapper.PostThreadMapper;
import com.koedame.bbs.api.presentation.thread.PostThreadController;


@WebMvcTest(PostThreadController.class)
public class PostThreadControllerValidationTest {
  
  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockitoBean
  PostThreadService postThreadService;

  @MockitoBean
  PostThreadMapper postThreadMapper; // ← 追加

  @Test
  @DisplayName("スレッド作成時にタイトルが空の場合は400とエラー応答を返す")
  void createThread_titleBlank_returnsValidationError() throws Exception {
    // タイトルがからのリクエスト
    String requestBody = """
        {
          "title": ""
        }
        """;

    mockMvc.perform(post("/api/threads")
      .contentType(MediaType.APPLICATION_JSON)
      .content(requestBody))
    .andExpect(status().isBadRequest())
    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    // 成功/失敗構造の統一型ApiResponseを使用しているためstatusフィールドに"ERROR"が期待される 
    .andExpect(jsonPath("$.status").value("ERROR"))
    .andExpect(jsonPath("$.message").value("Validation failed"))
    .andExpect(jsonPath("$.errors[0].field").value("title"));
  }
}
