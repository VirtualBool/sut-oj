package com.chen.sutoj.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sutoj.exception.ErrorCode;
import com.chen.sutoj.exception.ThrowUtils;
import com.chen.sutoj.mapper.ProblemsMapper;
import com.chen.sutoj.model.entity.Problems;
import com.chen.sutoj.model.vo.ContestProblemDetailsVO;
import com.chen.sutoj.model.vo.ContestProblemsVO;
import com.chen.sutoj.service.ProblemsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author 86176
* @description 针对表【problems(竞赛题目信息表)】的数据库操作Service实现
* @createDate 2025-06-30 19:08:00
*/
@Service
public class ProblemsServiceImpl extends ServiceImpl<ProblemsMapper, Problems>
    implements ProblemsService {

    /**
     * 获取一场比赛的题目列表
     * @param contestId
     * @return
     */
    @Override
    public List<ContestProblemsVO> getProblemsByContestId(Long contestId) {
        // 获取题目
        QueryWrapper<Problems> problemsQueryWrapper = new QueryWrapper<>();
        problemsQueryWrapper.eq("contest_id", contestId)
                .orderByAsc("problem_code");
        List<Problems> problemsList = this.getBaseMapper().selectList(problemsQueryWrapper);
        // 转为VO类
        List<ContestProblemsVO> contestProblemsVOList =  problemsList.stream()
                .map(problem->{
                    ContestProblemsVO contestProblemsVO = new ContestProblemsVO();
                    BeanUtil.copyProperties(problem, contestProblemsVO);
                    return contestProblemsVO;
                })
                .collect((Collectors.toList()));
        return contestProblemsVOList;
    }

    /**
     * 获取竞赛中题目的详细信息
     * @param problemId
     * @return
     */
    @Override
    public ContestProblemDetailsVO getProblemsById(Long problemId) {
        Problems problem = this.getById(problemId);
        ThrowUtils.throwIf(problem == null, ErrorCode.NOT_FOUND_ERROR);
        ContestProblemDetailsVO contestProblemDetailsVO = new ContestProblemDetailsVO();
        BeanUtil.copyProperties(problem, contestProblemDetailsVO);
        return contestProblemDetailsVO;
    }
}




