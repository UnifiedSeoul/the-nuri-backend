package com.nuri.backend.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Edu {

    @Id
    @JsonProperty("IDX")
    private String id;

    @JsonProperty("SUBJECT")
    private String eduTitle;

    @JsonProperty("STARTDATE")
    private String eduStartDate;

    @JsonProperty("ENDDATE")
    private String eduEndDate;

    @JsonProperty("APPLICATIONSTARTDATE")
    private String applyStartDate;

    @JsonProperty("APPLICATIONENDDATE")
    private String applyEndDate;

    @JsonProperty("REGISTPEOPLE")
    private String registerPeople;

    @JsonProperty("REGISTCOST")
    private String registerCost;

    @JsonProperty("APPLY_STATE")
    private String applyState;

    @JsonProperty("VIEWDETAIL")
    private String viewDetail;
}
