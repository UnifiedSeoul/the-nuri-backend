package com.nuri.backend.dto.api.job;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobInfoResponse {

    @XmlElement(name = "acptMthd")
    private String acceptanceMethodCode; // 접수 방법 코드

    @XmlElement(name = "deadline")
    private String deadlineStatus; // 마감 유무, ex) 마감, 접수중

    @XmlElement(name = "emplymShp")
    private String employmentShape; // 고용 형태 ex) 정규직, 계약직 채용공고 고용형태 CM0101 : 정규직 CM0102 : 계약직 CM0103 : 시간제일자리 CM0104 : 일당직 CM0105 : 기타

    @XmlElement(name = "emplymShpNm")
    private String employmentShapeName; // 채용공고 형태명 ex) 시간제 일자리

    @XmlElement(name = "frDd")
    private String fromAcceptanceDate; // 공고 시작일

    @XmlElement(name = "jobId")
    private String jobId; // 채용 공고 ID

    @XmlElement(name = "jobcls")
    private String jobCode; // 직종코드

    @XmlElement(name = "jobId")
    private String jobCategory; // 직종명

    @XmlElement(name = "oranNm")
    private String companyName; // 기업명

    @XmlElement(name = "organYn")
    private String companyType; // 기업종류 대민: N, 업무: Y

    @XmlElement(name = "recrtTitle")
    private String recruitmentTitle; // 채용제목

    @XmlElement(name = "stmId")
    private String linkSystemId; // 연계시스템ID

    @XmlElement(name = "stmNm")
    private String linkSystemName; // 연계시스템명

    @XmlElement(name = "toDd")
    private String toAcceptanceDate; // 마감일

    @XmlElement(name = "workPlc")
    private String workPlaceCode; // 근무지 코드

    @XmlElement(name = "workPlcNm")
    private String workPlaceName; // 근무지 시, 구 ex) 서울시 중구
}
