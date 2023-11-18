package com.ssp.backend.exception;


import com.ssp.backend.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class StonePaperScissorsException {

    @ExceptionHandler(value = {CustomException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(CustomException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(value = {InternalServerError.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(InternalServerError ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);

            log.error(fieldName+": "+errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleValidationExceptions(MissingServletRequestParameterException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        log.error("Missing parameter ==> "+ ex.getParameterName());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    //HttpRequestMethodNotSupportedException
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleValidationExceptions(HttpRequestMethodNotSupportedException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        log.error("Missing parameter ==> "+ ex.getMessage(), ex);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
