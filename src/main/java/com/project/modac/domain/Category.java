package com.project.modac.domain;

import com.project.modac.apiPayload.GeneralException;
import com.project.modac.apiPayload.code.status.ErrorStatus;
import lombok.Getter;

@Getter
public enum Category {


    ALOPECIA_AREATA("원형탈모"),
    HAIR_TRANSPLANT("모발이식"),
    HAIR_MEDICINE("탈모약"),
    HOSPITAL_QUESTION("병원질문"),
    FREE_TALK("자유수다");

    private String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public static Category fromDisplayName(String displayName) {
        for (Category category : Category.values()) {
            if (category.getDisplayName().equals(displayName)) {
                return category;
            }
        }
        throw new GeneralException(ErrorStatus.NO_CATEGORY);
    }
}
