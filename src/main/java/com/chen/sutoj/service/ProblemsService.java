package com.chen.sutoj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.sutoj.model.entity.Problems;
import com.chen.sutoj.model.vo.ContestProblemDetailsVO;
import com.chen.sutoj.model.vo.ContestProblemsVO;

import java.util.List;

/**
* @author 86176
* @description 针对表【problems(竞赛题目信息表)】的数据库操作Service
* @createDate 2025-06-30 19:08:00
*/
public interface ProblemsService extends IService<Problems> {

    /**
     * 获取一场比赛的题目列表
     * @param contestId
     * @return
     */
    List<ContestProblemsVO> getProblemsByContestId(Long contestId);

    /**
     * 获取竞赛中题目的详细信息
     * @param problemId
     * @return
     */
    ContestProblemDetailsVO getProblemsById(Long problemId);

}
