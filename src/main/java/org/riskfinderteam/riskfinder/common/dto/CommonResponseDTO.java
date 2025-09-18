package org.riskfinderteam.riskfinder.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class CommonResponseDTO<T> {
    private int status;
    private String message;
    private T data;
    
    // 성공 응답
    public static <T> CommonResponseDTO<T> success(HttpStatus status, String message, T data) {
        return new CommonResponseDTO<T>(status.value(), message, data);
    }

    public static <T> CommonResponseDTO<T> success(HttpStatus status, String message) {
        return new CommonResponseDTO<>(status.value(), message, null);
    }

    // 실패 응답
    public static <T> CommonResponseDTO<T> error(String message, int status) {
        return new CommonResponseDTO<>(status, message, null);
    }
}
