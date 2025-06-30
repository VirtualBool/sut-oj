package com.chen.sutoj.controller;

import com.chen.sutoj.model.vo.Score;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@Api(tags = "用户相关接口")
@RequestMapping("/user")
public class UserController {

    @GetMapping("/ranking")
    List<Score> getRank() {
        log.info("获取排名");
        List<Score> list = new ArrayList<>();
        list.add(new Score("spt", 92.5));
        list.add(new Score("virtual", 21.6));
        list.add(new Score("bool", 36.0));
        list.add(new Score("sut", 52.1));
        list.add(new Score("pojone", 33));
        return list;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from SutOj Application!";
    }

}
