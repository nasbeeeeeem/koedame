package com.koedame.bbs.api.common.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.koedame.bbs.api.common.response.ApiErrorResponse;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

public class GlobalExceptionHandler {
  
  /**
   * @Valid のバリデーションエラー
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
    List<ApiErrorResponse.FieldError> errorList = ex.getBindingResult().getFieldErrors().stream()
      .map(err -> new ApiErrorResponse.FieldError(err.getField(), err.getDefaultMessage()))
      .toList();

    ApiErrorResponse response = new ApiErrorResponse(
      "ERROR",
      "Validation failed",
      errorList
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  /**
   * @Validated のConstraintViolationException
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ApiErrorResponse> handleConstraintViolatio(ConstraintViolationException ex) {
    List<ApiErrorResponse.FieldError> errorList = ex.getConstraintViolations().stream()
      .map(violation -> new ApiErrorResponse.FieldError(
        violation.getPropertyPath().toString(),
        violation.getMessage()
      ))
      .toList();

    ApiErrorResponse response = new ApiErrorResponse(
      "ERROR",
      "Validation failed",
      errorList
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  /**
   * データが見つからない場合
   */
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ApiErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
    ApiErrorResponse response = new ApiErrorResponse(
      "ERROR",
      ex.getMessage(),
      List.of()
    );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  /**
   * その他の例外
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiErrorResponse> handleGeneralException(Exception ex) {
    ApiErrorResponse response = new ApiErrorResponse(
      "ERROR",
      ex.getMessage(),
      List.of()
    );
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}


