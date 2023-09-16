package com.ritesh.redis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomHealthController {

    @GetMapping(value = "/v1/health", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Map<String, String> health(@RequestParam("value")String value){
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("status", "UP");
        responseBody.put("value", value);
        return responseBody;
    }
}
