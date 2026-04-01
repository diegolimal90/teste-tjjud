package br.com.sdweb.agenda.configuration.handler;

import br.com.sdweb.agenda.controller.httmodel.ResponseHttpModel;
import exceptions.BusinessException;
import exceptions.OutException;
import exceptions.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ControllerAdviceException {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseHttpModel<ErrorDTO>> businessException(BusinessException ex) {

        var err = new ResponseHttpModel<>(
                "ERROR",
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                System.currentTimeMillis(),
                "Request error",
                ex.getErrorList().stream()
                        .map(s -> ErrorDTO.builder()
                                .code(s.getCode())
                                .message(s.getMessage())
                                .build())
                        .toList());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseHttpModel<ErrorDTO>> businessException(HttpMessageNotReadableException ex) {

        var err = new ResponseHttpModel<>(
                "ERROR",
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                System.currentTimeMillis(),
                "Request error",
                List.of(ErrorDTO.builder()
                        .code("9999")
                        .message(ex.getMessage())
                        .build()));
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

    @ExceptionHandler(OutException.class)
    public ResponseEntity<ResponseHttpModel<ErrorDTO>> outException(OutException ex) {

        var err = new ResponseHttpModel<>(
                "ERROR",
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                System.currentTimeMillis(),
                "Internal Dataprovider error",
                ex.getErrorList().stream()
                        .map(s -> ErrorDTO.builder()
                                .code(s.getCode())
                                .message(s.getMessage())
                                .build())
                        .toList());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(err);
    }
}
