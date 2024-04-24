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
public class Acceptance {

    private String acceptanceMethodCode; // 접수 방법 코드
    private String fromAcceptanceDate; // 접수 시작일
    private String toAcceptanceDate; // 접수 종료일
}
