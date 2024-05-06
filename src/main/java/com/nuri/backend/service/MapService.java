package com.nuri.backend.service;

import com.nuri.backend.domain.embeddables.GeoLocation;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class MapService {

    private final RestClient restClient;
    private static Map<String, GeoLocation> address = new HashMap<>();

    @Value("${map.client-id}")
    private String clientId;

    @Value("${map.client-secret}")
    private String clientSecret;

    @Value("${map.url}")
    private String mapUrl;

    @Value("${map.geo-path}")
    private String geoPath;

    private static final GeometryFactory geometryFactory = new GeometryFactory();

    public GeoLocation getGeoLocationByApi(String fullAddress) {

        String addressWithoutPostalCode = fullAddress.replaceFirst("^\\d{5}\\s+", "");

        if (address.containsKey(addressWithoutPostalCode)) {
            return address.get(addressWithoutPostalCode);
        }
        URI mapGeoUri = getMapGeoUri(addressWithoutPostalCode);
        ResponseEntity<String> geoInfo = getMapGeoEntity(mapGeoUri);

        GeoLocation geoLocation = getGeoLocation(geoInfo);
        address.put(addressWithoutPostalCode, geoLocation);
        return geoLocation;
    }

    private GeoLocation getGeoLocation(ResponseEntity<String> geoInfo) {
        JSONObject obj = new JSONObject(geoInfo.getBody());
        JSONObject address = obj.getJSONArray("addresses").getJSONObject(0);

        String x = address.getString("x");
        String y = address.getString("y");
        Point point = geometryFactory.createPoint(new Coordinate(Double.parseDouble(x), Double.parseDouble(y)));

        return new GeoLocation(x, y, x + "," + y, point);
    }

    private ResponseEntity<String> getMapGeoEntity(URI mapGeoUri) {

        ResponseEntity<String> entity = restClient.get()
                .uri(mapGeoUri)
                .headers(headers -> {
                    headers.add("X-NCP-APIGW-API-KEY-ID", clientId);
                    headers.add("X-NCP-APIGW-API-KEY", clientSecret);
                })
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class);

        return entity;
    }

    private URI getMapGeoUri(String addressWithoutPostalCode) {
        URI mapGeoUri = UriComponentsBuilder
                .fromUriString(mapUrl)
                .path(geoPath)
                .queryParam("query", "{query}")
                .encode()
                .buildAndExpand(addressWithoutPostalCode)
                .toUri();
        return mapGeoUri;
    }
}
