package org.riskfinderteam.riskfinder.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 400 BadRequest
    AUTH_VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "사용자 입력값이 올바르지 않습니다."),
    AUTH_EMAIL_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    JWT_NOT_CONFIGURED(HttpStatus.BAD_REQUEST, "JWT 비밀 키가 설정되지 않았습니다."),

    // 401 Unauthorized
    AUTH_TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "유효하지 않거나 만료된 토큰입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),

    // 403 Forbidden
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없는 요청입니다."),

    // 404 Not Found
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),

    // 500 Internal Server Error
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "DB 에러가 발생했습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다."),

    // 502 Bad Gateway
    HTTP_REQUEST_FAILED(HttpStatus.BAD_GATEWAY, "외부 API 요청 실패"),
    EXTERNAL_API_ERROR(HttpStatus.BAD_GATEWAY, "외부 API 호출 실패"),
    BAD_GATEWAY(HttpStatus.BAD_GATEWAY, "잘못된 게이트웨이");

    private final HttpStatus httpStatus;
    private final String message;


}
