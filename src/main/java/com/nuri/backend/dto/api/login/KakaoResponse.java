package com.nuri.backend.dto.api.login;

import java.util.Map;

public class KakaoResponse {

    private final Map<String, Object> attribute;

    public KakaoResponse(Map<String, Object> attribute) {

        this.attribute = (Map<String, Object>) attribute.get("response");
    }

    public String getProvider() {

        return "kakao";
    }

    public String getProviderId() {
        return attribute.get("id").toString();
    }

    public String getEmail() {
        return attribute.get("email").toString();
    }

    public String getName() {
        return attribute.get("name").toString();
    }
}