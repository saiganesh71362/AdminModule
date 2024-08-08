package com.acruent.admin.exceptionhandle;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.acruent.admin.controller.CategoryController;

@ControllerAdvice
public class GlobalExceptionHandle 
{
    private static final Logger logger = LogManager.getLogger(CategoryController.class);

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleIdNotFoundException(IdNotFoundException idNotFoundException, WebRequest webRequest) {
        logger.error("ID not found exception occurred: {}", idNotFoundException.getMessage());

        ExceptionMessage message = new ExceptionMessage(
            new Date(), 
            idNotFoundException.getMessage(), 
            webRequest.getDescription(false)
        );

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

}
