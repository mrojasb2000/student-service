package com.services.ms.student.app.infrastructure.adapters.input.restapi;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.services.ms.student.app.domain.exception.StudentNotFoundException;
import com.services.ms.student.app.infrastructure.adapters.input.restapi.model.response.ErrorResponse;

import static com.services.ms.student.app.utils.ErrorCatalogEnum.STUDENT_NOT_FOUND;
import static com.services.ms.student.app.utils.ErrorCatalogEnum.INVALID_STUDENT;
import static com.services.ms.student.app.utils.ErrorCatalogEnum.GENERIC_ERROR;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalControllerAdvice {
   @ResponseStatus(HttpStatus.NOT_FOUND)
   @ExceptionHandler(StudentNotFoundException.class)
   public ErrorResponse handlerStudentNotFoundException() {
        return ErrorResponse.builder()
                .code(STUDENT_NOT_FOUND.getCode())
                .message(STUDENT_NOT_FOUND.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
   } 

   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ErrorResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();

        return ErrorResponse.builder()
                .code(INVALID_STUDENT.getCode())
                .message(INVALID_STUDENT.getMessage())
                .details(result.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList())
                )
                .timestamp(LocalDateTime.now())
                .build();
   }

   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @ExceptionHandler(Exception.class)
   public ErrorResponse handlerInternalServerErrorException(Exception e) {
    
    return ErrorResponse.builder()
            .code(GENERIC_ERROR.getCode())
            .message(GENERIC_ERROR.getMessage())
            .details(Collections.singletonList(e.getMessage()))
            .timestamp(LocalDateTime.now())
            .build();
   } 

}
