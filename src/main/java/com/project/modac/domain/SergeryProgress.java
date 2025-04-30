package com.project.modac.domain;


import lombok.Getter;

@Getter
public enum SergeryProgress {

    ONE_WEEK("1주"),
    TWO_WEEKS("2주"),
    ONE_MONTH("1달"),
    THREE_MONTHS("3달"),
    SIX_MONTHS("6달"),
    ONE_YEAR("1년");


    private String progress;

    SergeryProgress(String progress) {
        this.progress = progress;
    }

}
