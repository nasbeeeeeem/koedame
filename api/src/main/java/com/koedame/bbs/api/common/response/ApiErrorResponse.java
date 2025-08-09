package com.koedame.bbs.api.common.response;

import java.util.List;

public record ApiErrorResponse(
  String status,
  String message,
  List<FieldError> errors
){
  public record FieldError(String field, String message) {}
}
