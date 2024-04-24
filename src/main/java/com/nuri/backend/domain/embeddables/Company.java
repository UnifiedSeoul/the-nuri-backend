package com.nuri.backend.domain.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private String companyName; // 기업명
    private String companyStaffName; // 담당자 이름
    private String companyStaffContact; // 담당자 연락처
    private String companyHomePageUrl; // 홈페이지 URL
    private String companyWorkingName; // 사업장 명칭
    private String companyRepresentative; // 대표자
}

