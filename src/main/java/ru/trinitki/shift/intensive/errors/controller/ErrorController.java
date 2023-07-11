package ru.trinitki.shift.intensive.errors.controller;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.utils.PropertyResolverUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.trinitki.shift.intensive.errors.CodeAbleException;
import ru.trinitki.shift.intensive.users.exception.EmailConflictException;
import ru.trinitki.shift.intensive.users.exception.UserForbiddenException;
import ru.trinitki.shift.intensive.users.exception.UserNotAuthorizedException;
import ru.trinitki.shift.intensive.users.exception.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@RestControllerAdvice
public class ErrorController {
    private static final Logger log = LoggerFactory.getLogger(ErrorController.class);

    private final PropertyResolverUtils propertyResolverUtils;

    @Autowired
    public ErrorController(PropertyResolverUtils propertyResolverUtils) {
        this.propertyResolverUtils = propertyResolverUtils;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error(exception.getMessage());
        return handleCodeAbleException(HttpStatus.INTERNAL_SERVER_ERROR, new CodeAbleException(500, message("Internal server error!")));
    }

    @ExceptionHandler(EmailConflictException.class)
    public ResponseEntity<ErrorResponse> handleEmailConflictException(EmailConflictException exception) {
        return handleCodeAbleException(HttpStatus.CONFLICT, exception);
    }

    @ExceptionHandler(UserNotAuthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUserNotAuthorizedException(UserNotAuthorizedException exception) {
        return handleCodeAbleException(HttpStatus.UNAUTHORIZED, exception);
    }

    @ExceptionHandler(UserForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleUserForbiddenException(UserForbiddenException exception) {
        return handleCodeAbleException(HttpStatus.FORBIDDEN, exception);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exception) {
        return handleCodeAbleException(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return handleBindValidationException(exception);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException exception) {
        return handleCustomException(exception, HttpStatus.BAD_REQUEST);
    }

    public record ErrorResponse(LocalDateTime timestamp, String message, int code) {
    }

    protected ResponseEntity<ErrorResponse> handleCustomException(Exception exception, HttpStatus status) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body(exception.getMessage(), status.value()));
    }

    protected ResponseEntity<ErrorResponse> handleBindValidationException(BindException exception) {
        String message = IntStream.range(0, exception.getErrorCount()).mapToObj(i -> i + 1 + "." + exception.getAllErrors().get(i).getDefaultMessage()).collect(Collectors.joining("; "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body(message, 400));
    }

    protected ResponseEntity<ErrorResponse> handleCodeAbleException(HttpStatus status, CodeAbleException exception) {
        return ResponseEntity.status(status).body(body(exception));
    }

    protected ErrorResponse body(CodeAbleException exception) {
        return body(message(exception.getMessage()), exception.getCode());
    }

    protected ErrorResponse body(String message, Integer code) {
        return new ErrorResponse(LocalDateTime.now(), message, code);
    }

    private String message(String property) {
        return this.propertyResolverUtils.resolve(property, Locale.getDefault());
    }
}
