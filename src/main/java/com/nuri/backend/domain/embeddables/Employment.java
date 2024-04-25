package com.nuri.backend.domain.embeddables;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Employment {

    private Integer age; // 나이
    private String ageLimit; // 나이 제한
    private String employmentShape; // 고용 형태
    private String employmentShapeName; // 채용공고 형태명
    private String jobCode; // 직종코드
    private String jobCategory; // 직종명
    private Integer recruitmentPersonNumber; // 모집 인원 수
    private String etcItem; // 기타 항목
    private String companyType; // 기업종류
}