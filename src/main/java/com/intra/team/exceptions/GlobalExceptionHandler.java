package com.intra.team.exceptions;



import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ---------- custom ----------

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> badRequest(
            BadRequestException ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), req);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFound(
            ResourceNotFoundException ex, HttpServletRequest req) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), req);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> unauthorized(
            UnauthorizedException ex, HttpServletRequest req) {
        return build(HttpStatus.UNAUTHORIZED, ex.getMessage(), req);
    }

    // ---------- validation ----------

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validation(
            MethodArgumentNotValidException ex,
            HttpServletRequest req) {

        String msg = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return build(HttpStatus.BAD_REQUEST, msg, req);
    }

    // ---------- type mismatch ----------

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> typeMismatch(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest req) {

        return build(HttpStatus.BAD_REQUEST,
                "Invalid parameter: " + ex.getName(),
                req);
    }

    // ---------- security ----------

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> forbidden(
            AccessDeniedException ex,
            HttpServletRequest req) {

        return build(HttpStatus.FORBIDDEN,
                "Access denied",
                req);
    }

    // ---------- media type ----------

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> mediaType(
            HttpMediaTypeNotSupportedException ex,
            HttpServletRequest req) {

        return build(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                "Unsupported content type",
                req);
    }

    // ---------- DB ----------

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> database(
            DataAccessException ex,
            HttpServletRequest req) {

        return build(HttpStatus.INTERNAL_SERVER_ERROR,
                "Database error",
                req);
    }

    // ---------- fallback ----------

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> any(
            Exception ex,
            HttpServletRequest req) {

        return build(HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                req);
    }

    // ---------- builder ----------

    private ResponseEntity<ErrorResponse> build(
            HttpStatus status,
            String message,
            HttpServletRequest req) {

        ErrorResponse body = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(req.getRequestURI())
                .build();

        return new ResponseEntity<>(body, status);
    }
}

