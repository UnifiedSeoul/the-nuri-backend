package com.nuri.backend.domain.embeddables;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class LinkSystem {

    private String linkSystemId; // 연계시스템ID
    private String linkSystemName; // 연계시스템명
}