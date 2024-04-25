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
public class JobDetailResponse {

    @XmlElement(name = "acptMthdCd")
    private String acceptanceMethodCode; // 접수 방법 코드

    @XmlElement(name = "age")
    private Integer age; // 나이

    @XmlElement(name = "ageLim")
    private String ageLimit; // 나이 제한 ex) 제한

    @XmlElement(name = "clerk")
    private String clerkName; // 담당자 이름

    @XmlElement(name = "clerkContt")
    private String clerkContact; // 담당자 연락처

    @XmlElement(name = "clltPrnnum")
    private Integer recruitmentPersonNumber; // 모집 인원 수

    @XmlElement(name = "createDy")
    private String creationDate; // 생성 일자

    @XmlElement(name = "detCnts")
    private String detailedContents; // 상세내용

    @XmlElement(name = "etcItm")
    private String etcItem; // 기타 항목

    @XmlElement(name = "frAcptDd")
    private String fromAcceptanceDate; // 접수 시작일

    @XmlElement(name = "homepage")
    private String homepageUrl; // 홈페이지 URL

    @XmlElement(name = "jobId")
    private String jobId; // 채용 공고 ID

    @XmlElement(name = "lnkStmId")
    private String linkSystemId; // 연결 시스템 ID

    @XmlElement(name = "organYn")
    private String companyType; // 구분값 (대민, 업무)

    @XmlElement(name = "plDetAddr")
    private String placeDetailAddress; // 상세 근무지 주소

    @XmlElement(name = "plbizNm")
    private String placeBusinessName; // 사업장 명칭

    @XmlElement(name = "repr")
    private String representative; // 대표자

    @XmlElement(name = "stmId")
    private String systemId; // 시스템 ID

    @XmlElement(name = "toAcptDd")
    private String toAcceptanceDate; // 접수 종료일

    @XmlElement(name = "updDy")
    private String updateDay; // 업데이트 일자

    @XmlElement(name = "wantedAuthNo")
    private String recruitmentAuthNumber; // 요구 사항 인증 번호

    @XmlElement(name = "wantedTitle")
    private String recruitmentTitle; // 공고 제목
}
