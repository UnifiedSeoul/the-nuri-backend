package com.nuri.backend.domain.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Place {

    private String workPlaceCode; // 근무지 코드
    private String workPlaceName; // 근무지 시, 구
}
