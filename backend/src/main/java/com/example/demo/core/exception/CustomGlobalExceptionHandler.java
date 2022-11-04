package com.example.demo.core.exception;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class CustomGlobalExceptionHandler {

  //May be used for further ExceptionHandlers
  //messageSource.getMessage("errors.exception.message", null, LocaleContextHolder.getLocale())
  private MessageSource messageSource;

  @Autowired
  public CustomGlobalExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ResponseError handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
    return new ResponseError().setTimeStamp(LocalDate.now())
                              .setErrors(ex.getBindingResult()
                                           .getFieldErrors()
                                           .stream()
                                           .collect(Collectors.toMap(FieldError::getField,
                                               DefaultMessageSourceResolvable::getDefaultMessage)))
                              .build();
  }


  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> fallthroughExceptionHandler(Exception e) {
    return getString(e);
  }

  private ResponseEntity<String> getString(Exception e) {
    log.warn("{}", e.getMessage());
    e.printStackTrace();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"errors\":{\"" + e.getMessage() + "\"}}");
  }
}


