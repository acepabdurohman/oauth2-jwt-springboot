package com.acepabdurohman.belajaroauth2jwt.exception;

import com.acepabdurohman.belajaroauth2jwt.dto.MyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalHandlerException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MyResponse handleErrorInput(MethodArgumentNotValidException e){

        log.error(e.getMessage());
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        return new MyResponse(
                "field " + fieldErrors.get(0).getField() + " can not be empty"
        );
    }
}