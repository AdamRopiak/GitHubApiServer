package com.githubapiserver.configuration;

import com.githubapiserver.client.ErrorHandlerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExeptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorHandlerResponse> handleNotFound(NotFoundException e) {
        ErrorHandlerResponse error = new ErrorHandlerResponse(e.getStatus(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    public ResponseEntity<ErrorHandlerResponse> handleHttpMediaTypeException(HttpMediaTypeException e) {
        ErrorHandlerResponse wrongMedia = new ErrorHandlerResponse(406, "Only JSON header is allowed");
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).contentType(MediaType.APPLICATION_JSON).body(wrongMedia);
    }

}
