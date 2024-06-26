package com.nuri.backend.service;

import com.nuri.backend.domain.JobInfo;
import com.nuri.backend.domain.embeddables.GeoLocation;
import com.nuri.backend.dto.JobInfoDto;
import com.nuri.backend.dto.api.job.JobDetailResponse;
import com.nuri.backend.dto.api.job.JobDetailTotalResponse;
import com.nuri.backend.dto.api.job.JobInfoResponse;
import com.nuri.backend.dto.api.job.JobInfoTotalResponse;
import com.nuri.backend.repository.JobInfoRepository;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobInfoSchedulingService {

    private final RestClient restClient;
    private final JobInfoRepository jobInfoRepository;
    private final MapService mapService;

    @Value("${public-data.job-list-url}")
    private String jobListUrl;

    @Value("${public-data.job-detail-url}")
    private String jobDetailUrl;

    @Value("${public-data.service-key}")
    private String serviceKey;

    public List<JobInfoDto> fetchJobs() {

        URI jobListUri = getJobListUri();
        JobInfoTotalResponse jobInfoTotalResponse = getJobInfoTotalResponse(jobListUri);
//        assert jobListResponseDto != null;
        List<JobInfoResponse> jobList = jobInfoTotalResponse.getBody().getJobList();

        List<JobInfo> jobInfoList = new ArrayList<>();

        for (JobInfoResponse jobInfoResponse : jobList) {

            String jobId = jobInfoResponse.getJobId();
            log.info("jobInfoDto = {}", jobInfoResponse.getCompanyName());
            URI jobDetailInfoUri = getJobDetailInfoUri(jobId);

            JobDetailTotalResponse jobDetailTotalResponse = getJobDetailTotalResponse(jobDetailInfoUri);

            JobDetailResponse jobDetailResponse = jobDetailTotalResponse.getBody().getJobDetailList().get(0);

            // 필터링
            if (!jobDetailResponse.getPlaceDetailAddress().startsWith("0")) continue;

            GeoLocation geoLocation = mapService.getGeoLocationByApi(jobDetailResponse.getPlaceDetailAddress());
            if (geoLocation == null) continue;

            jobInfoList.add(JobInfo.of(jobInfoResponse, jobDetailResponse, geoLocation));
        }

        jobInfoRepository.saveAll(jobInfoList);

        log.info("jobInfoList.size() = {}", jobInfoList.size());
        return jobInfoList.stream()
                .map(JobInfoDto::from)
                .toList();
    }

    private JobDetailTotalResponse getJobDetailTotalResponse(URI jobDetailInfoUri) {
        return restClient.get()
                .uri(jobDetailInfoUri)
                .accept(MediaType.APPLICATION_XML)
                .retrieve()
                .body(JobDetailTotalResponse.class);
    }

    private URI getJobDetailInfoUri(String jobId) {
        return UriComponentsBuilder
                .fromUriString(jobDetailUrl)
                .queryParam("serviceKey", "{serviceKey}")
                .queryParam("id", "{jobId}")
                .encode()
                .buildAndExpand(serviceKey, jobId)
                .toUri();
    }

    private JobInfoTotalResponse getJobInfoTotalResponse(URI jobListUri) {
        return restClient.get()
                .uri(jobListUri)
                .accept(MediaType.APPLICATION_XML)
                .retrieve()
                .body(JobInfoTotalResponse.class);
    }

    private URI getJobListUri() {
        return UriComponentsBuilder
                .fromUriString(jobListUrl)
                .queryParam("serviceKey", "{serviceKey}")
                .queryParam("pageNo","{pageNo}")
                .queryParam("numOfRows","{numOfRows}")
                .encode()
                .buildAndExpand(serviceKey, 1, 500)
                .toUri();
    }
}
