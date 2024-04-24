package com.nuri.backend.schedule.service;

import com.nuri.backend.dto.api.job.JobDetailResponse;
import com.nuri.backend.dto.api.job.JobDetailTotalResponse;
import com.nuri.backend.dto.api.job.JobInfoResponse;
import com.nuri.backend.dto.api.job.JobInfoTotalResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

@Slf4j
public class JobInfoSchedulingServiceTest {

    @Test
    @DisplayName("온전한 일자리 정보 Xml을 Pojo로 변환하면 결과 코드가 00이다.")
    void given_IntactJobInfoXml_When_XmlToPojo_Then_ResultCodeIs00() throws IOException, JAXBException {

        // given
        ClassPathResource xmlResource = new ClassPathResource("test-data/jobInfo-complete.xml");
        InputStream inputStream = xmlResource.getInputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(JobInfoTotalResponse.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        // when
        JobInfoTotalResponse jobInfoTotalResponse = (JobInfoTotalResponse) unmarshaller.unmarshal(inputStream);
        inputStream.close();

        // then
        Assertions.assertEquals("00", jobInfoTotalResponse.getHeader().getResultCode());
    }

    @Test
    @DisplayName("마감 기한이 누락된 Xml을 Pojo로 변환하면 마감기한은 Null이다.")
    void given_MissedDeadLineJobInfoXml_When_XmlToPojo_Then_DeadLineIsNull()
            throws IOException, JAXBException {

        // given
        ClassPathResource xmlResource = new ClassPathResource("test-data/jobInfo-flawed.xml");
        InputStream inputStream = xmlResource.getInputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(JobInfoTotalResponse.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        // when
        JobInfoTotalResponse jobInfoTotalResponse = (JobInfoTotalResponse) unmarshaller.unmarshal(inputStream);
        inputStream.close();

        // then
        List<JobInfoResponse> jobList = jobInfoTotalResponse.getBody().getJobList();
        Assertions.assertNull(jobList.get(0).getDeadlineStatus());
    }

    @Test
    @DisplayName("공고 상세 정보 xml을 pojo로 변환한다.")
    void given_JobInfoDetail_When_XmlToPojo_Then_ResultCodeIs00() throws IOException, JAXBException {

        //given
        ClassPathResource xmlResource = new ClassPathResource("test-data/jobInfoDetail-complete.xml");
        InputStream inputStream = xmlResource.getInputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(JobDetailTotalResponse.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        // when
        JobDetailTotalResponse jobDetailTotalResponse = (JobDetailTotalResponse) unmarshaller.unmarshal(inputStream);
        inputStream.close();

        // then
        JobDetailResponse jobDetailResponse = jobDetailTotalResponse.getBody().getJobDetailList().get(0);

        Assertions.assertEquals("00", jobDetailTotalResponse.getHeader().getResultCode());
        Assertions.assertEquals(jobDetailResponse.getAge(), 60);
    }
}
