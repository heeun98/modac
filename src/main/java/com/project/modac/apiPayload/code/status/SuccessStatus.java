package com.project.modac.apiPayload.code.status;

import com.project.modac.apiPayload.code.BaseCode;
import com.project.modac.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {
    // 가장 일반적인 응답
    _OK(HttpStatus.OK, "COMMON200", "요청에 성공했습니다."),
    _LOGIN_SUCCESS(HttpStatus.OK, "LOGIN200", "로그인 성공했습니다.")
    ;

    // 멤버 관련 응답

    // ~~~ 관련 응답 ....


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
