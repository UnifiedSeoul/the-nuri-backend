package com.nuri.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class JwtToken {

    private String accessToken;
    private String refreshToken;
    private String grantType;
}
