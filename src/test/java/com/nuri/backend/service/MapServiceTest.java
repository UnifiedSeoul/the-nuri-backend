package com.nuri.backend.service;

import java.net.URI;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest
class MapServiceTest {

    @Value("${map.client-id}")
    private String clientId;

    @Value("${map.client-secret}")
    private String clientSecret;

    @Value("${map.url}")
    private String mapUrl;

    @Value("${map.geo-path}")
    private String geoPath;

    @Autowired
    private RestClient restClient;

    @Test
    @DisplayName("주소로 네이버 지도 API를 호출해서 위도, 경도를 가져온다.")
    void given_FullAddress_When_CallMapAPI_Then_StatusCodeIs200() {
        // given
        String fullAddress = "06063 서울특별시 강남구 삼성로149길 15, 4층 402호 (청담동, JW B/D)";
        String addressWithoutPostalCode = fullAddress.replaceFirst("^\\d{5}\\s+", "");

        URI mapGeoUri = UriComponentsBuilder
                .fromUriString(mapUrl)
                .path(geoPath)
                .queryParam("query", "{query}")
                .encode()
                .buildAndExpand(addressWithoutPostalCode)
                .toUri();

        // when
        ResponseEntity<String> entity = restClient.get()
                .uri(mapGeoUri)
                .headers(headers -> {
                    headers.add("X-NCP-APIGW-API-KEY-ID", clientId);
                    headers.add("X-NCP-APIGW-API-KEY", clientSecret);
                })
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class);

        // then
        Assertions.assertEquals(entity.getStatusCode().value(), 200);
    }
    
    @Test
    @DisplayName("위도, 경도를 Parsing한다.")
    void given_JsonResponse_When_Parsing_Then_LatAndLngIsNotNull() {
        // given
        String jsonResponse = """
        {
            "status": "OK",
            "meta": {
                "totalCount": 1,
                "page": 1,
                "count": 1
            },
            "addresses": [
                {
                    "roadAddress": "경기도 성남시 분당구 불정로 6 그린팩토리",
                    "jibunAddress": "경기도 성남시 분당구 정자동 178-1 그린팩토리",
                    "englishAddress": "6, Buljeong-ro, Bundang-gu, Seongnam-si, Gyeonggi-do, Republic of Korea",
                    "addressElements": [
                        {
                            "types": [
                                "POSTAL_CODE"
                            ],
                            "longName": "13561",
                            "shortName": "",
                            "code": ""
                        }
                    ],
                    "x": "127.10522081658463",
                    "y": "37.35951219616309",
                    "distance": 20.925857741585514
                }
            ],
            "errorMessage": ""
        }
        """;

        // when
        JSONObject obj = new JSONObject(jsonResponse);

        JSONObject address = obj.getJSONArray("addresses").getJSONObject(0);

        // then
        String x = address.getString("x");
        String y = address.getString("y");

        Assertions.assertNotNull(x);
        Assertions.assertNotNull(y);
        Assertions.assertEquals(x, "127.10522081658463");
        Assertions.assertEquals(y, "37.35951219616309");
    }
}