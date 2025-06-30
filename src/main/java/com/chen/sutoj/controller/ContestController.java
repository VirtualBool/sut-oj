package com.chen.sutoj.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.sutoj.common.BaseResponse;
import com.chen.sutoj.common.PageRequest;
import com.chen.sutoj.common.ResultUtils;
import com.chen.sutoj.model.dto.ContestsRequest;
import com.chen.sutoj.model.vo.ContestsVO;
import com.chen.sutoj.service.ContestsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/contest")
@Slf4j
@Api(tags = "比赛管理")
public class ContestController {

    @Resource
    ContestsService contestsService;

    @ApiOperation("分页获取竞赛列表")
    @PostMapping("/list/page")
    public BaseResponse<Page<ContestsVO>> list(@RequestBody PageRequest pageRequest) {
        log.info("获取竞赛列表");
        return ResultUtils.success(contestsService.getAllContests(pageRequest));
    }

    @PostMapping("/add")
    @ApiOperation("添加竞赛")
    public BaseResponse<String> addContest(@RequestBody ContestsRequest contestsRequest) {

        contestsService.addContest(contestsRequest);
        log.info("添加竞赛成功");
        return ResultUtils.success("创建成功");
    }

}
