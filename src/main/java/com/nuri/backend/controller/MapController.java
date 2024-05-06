package com.nuri.backend.controller;

import com.nuri.backend.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MapController {

    private final MapService mapService;

    @GetMapping(value = "/path", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPath(@RequestParam(name = "start") String start,
            @RequestParam(name = "goal") String goal,
            @RequestParam(name = "option") String option) {

        return ResponseEntity.ok(mapService.getPath(start, goal, option));
    }
}
