package com.koedame.bbs.api.common.response;

import java.util.Collections;
import java.util.List;

public record ApiResponse<T>(
  String status,
  String message,
  T data,
  List<FieldError> errors
) {
  public record FieldError(String field, String message) {}

  public static <T> ApiResponse<T> success(String message, T data) {
    return new ApiResponse<>("SUCCESS", message, data, Collections.emptyList());
  }

  public static <T> ApiResponse<T> success(T data) {
    return success("ok", data);
  }

  public static ApiResponse<Void> successMessage(String message) {
    return new ApiResponse<Void>("SUCCESS", message, null, Collections.emptyList());
  }

  public static ApiResponse<?> error(String message, List<FieldError> errors) {
        return new ApiResponse<>("ERROR", message, null, errors == null ? Collections.emptyList() : errors);
  }

  public static ApiResponse<?> error(String message) {
    return error(message, Collections.emptyList());
  }
}
