package com.nuri.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuri.backend.domain.Edu;
import com.nuri.backend.exception.EduException;
import com.nuri.backend.exception.ErrorCode;
import com.nuri.backend.repository.EduRepository;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class EduService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;
    private final EduRepository eduRepository;

    @Value("${edu.url}")
    private String eduUrl;

    @Transactional
    public List<Edu> saveEdu() throws JsonProcessingException {
        String body = restClient.get()
                .uri(eduUrl)
                .retrieve()
                .body(String.class);

        Map<String, Object> jsonMap = objectMapper.readValue(body, Map.class);


        List<Map<String, Object>> rows = (List<Map<String, Object>>) ((Map<String, Object>) jsonMap.get(
                "tbViewProgram")).get("row");

        List<Edu> res = objectMapper.convertValue(rows,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Edu.class));

        eduRepository.saveAll(res);
        return res;
    }

    @Transactional(readOnly = true)
    public List<Edu> getEdu(Pageable pageable) {
        Page<Edu> eduList = eduRepository.findAll(pageable);
        return eduList.getContent();
    }

    @Transactional(readOnly = true)
    public Edu getEduById(String eduId) {
        return eduRepository.findById(eduId).orElseThrow(() -> new EduException(ErrorCode.INVALID_EDU_ID));
    }
}
