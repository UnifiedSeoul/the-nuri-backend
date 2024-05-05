package com.nuri.backend.domain.embeddables;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class GeoLocation {

    private String x;
    private String y;
    private String coords;
    private Point point;
}
