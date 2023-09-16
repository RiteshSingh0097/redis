package com.ritesh.redis.controller;

import com.ritesh.redis.cache.CacheServiceDao;
import com.ritesh.redis.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private CacheServiceDao dao;

    @GetMapping("employee")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
    }
}
