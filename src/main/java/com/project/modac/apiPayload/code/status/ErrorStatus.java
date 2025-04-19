package com.project.modac.apiPayload.code.status;

import com.project.modac.apiPayload.code.BaseErrorCode;
import com.project.modac.apiPayload.code.ErrorReasonDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorStatus implements BaseErrorCode {


    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    //
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON401","서버에 문제가 발생했습니다"),

    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "COMMON402", "잘못된 요청입니다."),

    // Register

    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "MEMBER401","이미 존재하는 아이디입니다"),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "MEMBER402","비밀번호가 일치하지 않습니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST,"MEMBER403" ,"회원을 찾을 수 없습니다."),
    ADDRESS_NOMATCH(HttpStatus.BAD_REQUEST,"MEMBER404","주소 형식이 올바르지 않습니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .isSuccess(false)
                .code(code)
                .message(message)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .isSuccess(false)
                .code(code)
                .message(message)
                .httpStatus(httpStatus)
                .build();
    }
}
