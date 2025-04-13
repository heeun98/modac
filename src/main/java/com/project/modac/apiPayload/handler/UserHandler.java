package com.project.modac.apiPayload.handler;


import com.project.modac.apiPayload.GeneralException;
import com.project.modac.apiPayload.code.BaseErrorCode;


public class UserHandler extends GeneralException {
    public UserHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
