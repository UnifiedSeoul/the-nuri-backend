package com.nuri.backend.dto.api.job;

import java.util.List;

public class JobCustomResponse {

    private List<String> recommendedjobid;

    public List<String> getRecommendedjobid() {
        return recommendedjobid;
    }

    public void setRecommendedjobid(List<String> recommendedjobid) {
        this.recommendedjobid = recommendedjobid;
    }
}
