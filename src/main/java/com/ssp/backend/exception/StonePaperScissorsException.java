package com.ssp.backend.exception;


import com.ssp.backend.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
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
}
