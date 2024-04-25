package com.nuri.backend.dto.api.job;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobInfoTotalResponse {

    @XmlElement(name = "header")
    private Header header;

    @XmlElement(name = "body")
    private Body body;

    @Getter
    @Setter
    @XmlRootElement(name = "header")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Header {

        @XmlElement(name = "resultCode")
        private String resultCode;

        @XmlElement(name = "resultMsg")
        private String resultMsg;
    }

    @Getter
    @Setter
    @XmlRootElement(name = "body")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Body {

        @XmlElement(name = "item")
        @XmlElementWrapper(name = "items")
        private List<JobInfoResponse> jobList;

        @XmlElement(name = "numOfRows")
        private Integer numOfRows;

        @XmlElement(name = "totalCount")
        private Integer totalCount;

        @XmlElement(name = "pageNo")
        private Integer pageNo;

    }

}


