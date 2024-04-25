package com.nuri.backend.domain.enums;

public enum EmploymentType {
    REGULAR("CM0101", "정규직"),
    CONTRACT("CM0102", "계약직"),
    TEMPORARY("CM0103", "시간제일자리"),
    DAILY("CM0104", "일당직"),
    OTHER("CM0105", "기타");

    private final String code;
    private final String description;

    EmploymentType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static String getDescriptionByCode(String code) {
        for (EmploymentType type : values()) {
            if (type.getCode().equals(code)) {
                return type.getDescription();
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}
