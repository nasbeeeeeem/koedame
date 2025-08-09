// package: com.koedame.bbs.api.common.exception

package com.koedame.bbs.api.common.exception;

import com.koedame.bbs.api.common.response.ApiResponse;
import com.koedame.bbs.api.common.response.ApiResponse.FieldError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  // @Valid のバリデーションエラー
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
    BindingResult br = ex.getBindingResult();
    List<FieldError> errors = br.getFieldErrors().stream()
      .map(fe -> new FieldError(fe.getField(), fe.getDefaultMessage()))
      .collect(Collectors.toList());

    ApiResponse<?> body = ApiResponse.error("Validation failed", errors);
    logger.debug("Validation error: {}", errors);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
  }

  // @Validated (パラメータや service 層での ConstraintViolation)
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ApiResponse<?>> handleConstraintViolation(ConstraintViolationException ex) {
    List<FieldError> errors = ex.getConstraintViolations().stream()
      .map(this::toFieldError)
      .collect(Collectors.toList());

    ApiResponse<?> body = ApiResponse.error("Validation failed", errors);
    logger.debug("Constraint violations: {}", errors);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
  }

  private FieldError toFieldError(ConstraintViolation<?> violation) {
    // propertyPath は "createThread.arg0.title" のようになることがあるので、見やすいフィールド名に整形
    String path = violation.getPropertyPath() == null ? "" : violation.getPropertyPath().toString();
    String field = extractLastPathNode(path);
    return new FieldError(field, violation.getMessage());
  }

  private String extractLastPathNode(String propertyPath) {
    if (propertyPath == null || propertyPath.isEmpty()) return propertyPath;
    int lastDot = propertyPath.lastIndexOf('.');
    if (lastDot >= 0 && lastDot < propertyPath.length() - 1) {
      return propertyPath.substring(lastDot + 1);
    }
    return propertyPath;
  }

  // データが見つからない（404）
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ApiResponse<?>> handleEntityNotFound(EntityNotFoundException ex) {
    ApiResponse<?> body = ApiResponse.error(ex.getMessage() == null ? "Not Found" : ex.getMessage());
    logger.info("Entity not found: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }

  // ビジネスロジックの引数エラー (400)
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiResponse<?>> handleIllegalArgument(IllegalArgumentException ex) {
    ApiResponse<?> body = ApiResponse.error(ex.getMessage() == null ? "Bad request" : ex.getMessage());
    logger.info("Illegal arg: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
  }

  // それ以外の例外 (500)
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<?>> handleAnyException(Exception ex) {
    logger.error("Unhandled exception", ex);
    ApiResponse<?> body = ApiResponse.error("Internal server error");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
  }
}
