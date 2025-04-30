package com.project.modac.domain;

import com.project.modac.apiPayload.GeneralException;
import com.project.modac.apiPayload.code.status.ErrorStatus;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Gender {
    MALE("남자"),
    FEMALE("여자");


    private final String gender;


    Gender(String gender) {
        this.gender = gender;
    }

    public static Gender fromGender(String gender) {
        for (Gender value : Gender.values()) {
            if (value.getGender().equals(gender)) {
                return value;
            }
        }
        throw new GeneralException(ErrorStatus.INVALID_REQUEST);
    }

}
