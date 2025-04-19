package com.project.modac.domain;

import java.util.Arrays;

public enum SatisfactionLevel {

    /**
     *  클라이언트에서 int로 보내고,
     * 서버에서는 enum으로 변환해서 저장 & 처리!
     * 즉,
     * POST 요청에서 1~5 숫자 받음
     * Controller → Service로 넘기기 전에 SatisfactionLevel.fromValue(dto.getSatisfactionScore())로 변환
     * DB에는 enum을 저장하거나, 필요 시 int 값만 저장
     */
    VERY_BAD(1),
    BAD(2),
    NORMAL(3),
    GOOD(4),
    VERY_GOOD(5);
    
    private final int value;

    SatisfactionLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SatisfactionLevel fromValue(int value) {
        return Arrays.stream(values())
                .filter(s -> s.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid satisfaction level: " + value));
    }
}
