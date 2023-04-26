package com.YapiKredi.YapiKredi.exceptions;

import com.YapiKredi.YapiKredi.util.ErroObject;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroObject> handleVacationNotFounException(ResourceNotFoundException exception, WebRequest webRequest) {
        ErroObject erroObject = new ErroObject();
        erroObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        erroObject.setMessage(exception.getMessage());
        erroObject.setTimestamp(new Date());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroObject);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroObject> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception, WebRequest webRequest) {

        ErroObject erroObject = new ErroObject();
        erroObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        erroObject.setMessage(exception.getMessage());
        erroObject.setTimestamp(new Date());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroObject);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroObject> handleInternalServerErrorException(Exception exception, WebRequest webRequest) {

        ErroObject erroObject = new ErroObject();
        erroObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        erroObject.setMessage(exception.getMessage());
        erroObject.setTimestamp(new Date());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroObject);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErroObject> handleIConstraintViolationException(Exception exception, WebRequest webRequest) {

        ErroObject erroObject = new ErroObject();
        erroObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        erroObject.setMessage("custom message");
        erroObject.setTimestamp(new Date());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroObject);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("timestamp", new Date());
        body.put("statusCode", HttpStatus.BAD_REQUEST.value());
        List<String> errros = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
        body.put("message", errros);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }


}
