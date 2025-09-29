package org.riskfinderteam.riskfinder.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<Map<String, String>> buildResponse(ErrorCode code, String message) {
        String finalMessage = (message != null) ? message : code.getMessage();

        return ResponseEntity.status(code.getHttpStatus())
                .body(Map.of(
                        "code", code.name(),
                        "message", finalMessage
                ));
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleBaseException(BaseException e) {
        log.error("BaseException 발생 - code: {}, message: {}",
                e.getErrorCode(), e.getMessage(), e);
        return buildResponse(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception e) {
        log.error("Unhandled Exception 발생", e);
        return buildResponse(ErrorCode.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
    }
}
