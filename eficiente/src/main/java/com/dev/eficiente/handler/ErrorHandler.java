package com.dev.eficiente.handler;

import com.dev.eficiente.dto.ApiErrorDto;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDto> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorHandler::formatMessage)
                .toList();

        ApiErrorDto apiError = new ApiErrorDto("Validation failed", errors);

        return ResponseEntity.badRequest().body(apiError);
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiErrorDto> handleBadRequestException(BadRequestException ex) {
        ApiErrorDto apiError = new ApiErrorDto("Validation failed", List.of(ex.getMessage()));

        return ResponseEntity.badRequest().body(apiError);
    }

    private static String formatMessage(FieldError error) {
        return String.format("Field '%s' %s", error.getField(), error.getDefaultMessage());
    }
}
