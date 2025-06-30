package com.chen.sutoj.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.sutoj.common.BaseResponse;
import com.chen.sutoj.common.PageRequest;
import com.chen.sutoj.common.ResultUtils;
import com.chen.sutoj.model.dto.AddProblemRequest;
import com.chen.sutoj.model.dto.ContestsRequest;
import com.chen.sutoj.model.vo.ContestProblemDetailsVO;
import com.chen.sutoj.model.vo.ContestProblemsVO;
import com.chen.sutoj.model.vo.ContestsVO;
import com.chen.sutoj.service.ContestsService;
import com.chen.sutoj.service.ProblemsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/contest")
@Slf4j
@Api(tags = "比赛管理")
public class ContestController {

    @Resource
    ContestsService contestsService;
    @Resource
    ProblemsService problemsService;

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

    // 删除竞赛
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除竞赛")
    public BaseResponse<String> deleteContest(@PathVariable Long id) {
        contestsService.deleteContest(id);
        log.info("删除竞赛成功");
        return ResultUtils.success("删除成功");
    }

    // 为竞赛添加问题
    @PostMapping("/addProblem")
    @ApiOperation("为竞赛添加问题")
    public BaseResponse<String> addProblem(@RequestBody AddProblemRequest addProblemRequest) {

        contestsService.addProblem(addProblemRequest);
        return ResultUtils.success("添加成功");
    }

    // 获取竞赛的题目列表
    @GetMapping("/getProblems/{contest_id}")
    @ApiOperation("获取竞赛的题目列表")
    public BaseResponse<List<ContestProblemsVO>> getProblems(@PathVariable("contest_id") Long contestId) {
        List<ContestProblemsVO> contestProblemsVOList = problemsService.getProblemsByContestId(contestId);
        return ResultUtils.success(contestProblemsVOList);
    }
    // 获取竞赛中题目的详细信息
    @GetMapping("/getProblemDetails/{problem_id}")
    @ApiOperation("获取竞赛中题目的详细信息")
    public BaseResponse<ContestProblemDetailsVO> getProblemDetails(@PathVariable("problem_id") Long problemId) {

        ContestProblemDetailsVO contestProblemDetailsVO = problemsService.getProblemsById(problemId);
        return ResultUtils.success(contestProblemDetailsVO);
    }
}
