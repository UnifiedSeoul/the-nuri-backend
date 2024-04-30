package com.nuri.backend.dto;

import com.nuri.backend.domain.JobInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobInfoDto {

    private String jobId; // 채용 공고 ID
    private String recruitmentTitle; // 채용제목
    private String acceptanceMethodCode; // 접수 방법 코드
    private Integer age;
    private Integer recruitmentPersonNumbers;
    private String fromAcceptanceDate; // 접수 시작일
    private String toAcceptanceDate; // 접수 종료일
    private String deadlineStatus; // 마감 유무, ex) 마감, 접수중
    private String placeDetailAddress; // 상세 근무지 주소
    private String companyName; // 기업명
    private String companyStaffName; // 담당자 이름
    private String companyStaffContact; // 담당자 연락처
    private String companyHomePageUrl; // 홈페이지 URL
    private String employmentShape; // 고용 형태

    public static JobInfoDto from(JobInfo jobInfo) {
        return JobInfoDto.builder()
                .jobId(jobInfo.getJobId())
                .recruitmentTitle(jobInfo.getRecruitmentTitle())
                .acceptanceMethodCode(jobInfo.getAcceptance().getAcceptanceMethodCode())
                .age(jobInfo.getEmployment().getAge())
                .recruitmentPersonNumbers(jobInfo.getEmployment().getRecruitmentPersonNumber())
                .fromAcceptanceDate(jobInfo.getAcceptance().getFromAcceptanceDate())
                .toAcceptanceDate(jobInfo.getAcceptance().getToAcceptanceDate())
                .deadlineStatus(jobInfo.getDeadlineStatus())
                .placeDetailAddress(jobInfo.getPlaceDetailAddress())
                .companyName(jobInfo.getCompany().getCompanyName())
                .companyStaffName(jobInfo.getCompany().getCompanyStaffName())
                .companyStaffContact(jobInfo.getCompany().getCompanyStaffContact())
                .companyHomePageUrl(jobInfo.getCompany().getCompanyHomePageUrl())
                .employmentShape(jobInfo.getEmployment().getEmploymentShape())
                .build();
    }

}
