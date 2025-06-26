package com.chen.sutoj.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "健康检查")
public class health {

    @ApiOperation("健康检查接口")
    @GetMapping("/health")
    String health(){
        log.info("health");
        return "ok";
    }
}
