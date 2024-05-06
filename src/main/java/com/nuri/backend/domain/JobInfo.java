package com.nuri.backend.domain;

import com.nuri.backend.domain.embeddables.Acceptance;
import com.nuri.backend.domain.embeddables.Company;
import com.nuri.backend.domain.embeddables.Employment;
import com.nuri.backend.domain.embeddables.GeoLocation;
import com.nuri.backend.domain.embeddables.LinkSystem;
import com.nuri.backend.domain.embeddables.Place;
import com.nuri.backend.domain.enums.EmploymentType;
import com.nuri.backend.dto.api.job.JobDetailResponse;
import com.nuri.backend.dto.api.job.JobInfoResponse;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@Builder
@Table(name = "jobInfo")
@NoArgsConstructor
public class JobInfo {

    @Id
    private String jobId; // 채용 공고 ID

    @Embedded
    private Acceptance acceptance;

    @Embedded
    private Company company;

    @Embedded
    private Employment employment;

    @Embedded
    private LinkSystem linkSystem;

    @Embedded
    private Place place;

    @Embedded
    private GeoLocation geoLocation;

    private String deadlineStatus; // 마감 유무, ex) 마감, 접수중
    private String detailedContents; // 상세내용
    private String placeDetailAddress; // 상세 근무지 주소
    private String recruitmentTitle; // 채용제목

    @ColumnDefault("0")
    private int viewCount;

    @ColumnDefault("0")
    private int homePageVisitCount;

    protected JobInfo(String jobId, Acceptance acceptance, Company company, Employment employment,
            LinkSystem linkSystem, Place place, GeoLocation geoLocation, String deadlineStatus, String detailedContents,
            String placeDetailAddress, String recruitmentTitle, int viewCount, int homepageVisitCount) {
        this.jobId = jobId;
        this.acceptance = acceptance;
        this.company = company;
        this.employment = employment;
        this.linkSystem = linkSystem;
        this.place = place;
        this.geoLocation = geoLocation;
        this.deadlineStatus = deadlineStatus;
        this.detailedContents = detailedContents;
        this.placeDetailAddress = placeDetailAddress;
        this.recruitmentTitle = recruitmentTitle;
        this.viewCount = viewCount;
        this.homePageVisitCount = homepageVisitCount;
    }

    public static JobInfo of(final JobInfoResponse jobInfoResponse, final JobDetailResponse jobDetailResponse, final GeoLocation geoLocation) {

        Acceptance acceptance = new Acceptance(
                jobInfoResponse.getAcceptanceMethodCode(),
                jobInfoResponse.getFromAcceptanceDate(),
                jobInfoResponse.getToAcceptanceDate()
        );

        Company company = new Company(
                jobInfoResponse.getCompanyName(),
                jobDetailResponse.getClerkName(),
                jobDetailResponse.getClerkContact(),
                jobDetailResponse.getHomepageUrl(),
                jobDetailResponse.getPlaceBusinessName(),
                jobDetailResponse.getRepresentative()
        );

        Employment employment = new Employment(
                jobDetailResponse.getAge(),
                jobDetailResponse.getAgeLimit(),
                EmploymentType.getDescriptionByCode(jobInfoResponse.getEmploymentShape()),
                jobInfoResponse.getEmploymentShapeName(),
                jobInfoResponse.getJobCode(),
                jobInfoResponse.getJobCategory(),
                jobDetailResponse.getRecruitmentPersonNumber(),
                jobDetailResponse.getEtcItem(),
                jobDetailResponse.getCompanyType()
        );

        LinkSystem linkSystem = new LinkSystem(
                jobInfoResponse.getLinkSystemId(),
                jobInfoResponse.getLinkSystemName()
        );

        Place place = new Place(
                jobInfoResponse.getWorkPlaceCode(),
                jobInfoResponse.getWorkPlaceName()
        );

        return JobInfo.builder()
                .jobId(jobDetailResponse.getJobId())
                .acceptance(acceptance)
                .company(company)
                .employment(employment)
                .linkSystem(linkSystem)
                .place(place)
                .geoLocation(geoLocation)
                .deadlineStatus(jobInfoResponse.getDeadlineStatus())
                .detailedContents(jobDetailResponse.getDetailedContents())
                .placeDetailAddress(jobDetailResponse.getPlaceDetailAddress())
                .recruitmentTitle(jobDetailResponse.getRecruitmentTitle())
                .build();
    }
}
