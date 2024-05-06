package com.nuri.backend.dto.api.job;

import java.util.List;

public class JobCustomResponse {

    private List<String> recommendJobId;

    public List<String> getRecommendJobId() {
        return recommendJobId;
    }

    public void setRecommendJobId(List<String> recommendjobid) {
        this.recommendJobId = recommendjobid;
    }
}
