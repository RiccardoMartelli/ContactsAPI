package com.owtchallenge.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ContactApiAdvice {

  @ResponseBody
  @ExceptionHandler(ContactNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String contactNotFoundHandler(ContactNotFoundException ex) {
    return ex.getMessage();
  }
  
  @ResponseBody
  @ExceptionHandler(SkillNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String skillNotFoundHandler(SkillNotFoundException ex) {
    return ex.getMessage();
  }
  
  @ResponseBody
  @ExceptionHandler(ContactDuplicatedException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  String contactDuplicatedHandler(ContactDuplicatedException ex) {
    return ex.getMessage();
  }
  
  @ResponseBody
  @ExceptionHandler(SkillDuplicatedException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  String skillDuplicatedHandler(SkillDuplicatedException ex) {
    return ex.getMessage();
  }
  
  @ResponseBody
  @ExceptionHandler(ContactAlreadyHasSkillException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  String contactAlreadyHasSkillHandler(ContactAlreadyHasSkillException ex) {
    return ex.getMessage();
  }
  
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(
    MethodArgumentNotValidException ex) {
      Map<String, String> errors = new HashMap<>();
      ex.getBindingResult().getAllErrors().forEach((error) -> {
          String fieldName = ((FieldError) error).getField();
          String errorMessage = error.getDefaultMessage();
          errors.put(fieldName, errorMessage);
      });
      return errors;
  }
  
}