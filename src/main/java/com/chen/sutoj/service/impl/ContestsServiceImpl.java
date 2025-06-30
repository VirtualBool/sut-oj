package com.chen.sutoj.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sutoj.common.PageRequest;
import com.chen.sutoj.exception.ErrorCode;
import com.chen.sutoj.exception.ThrowUtils;
import com.chen.sutoj.mapper.ContestsMapper;
import com.chen.sutoj.mapper.ProblemsMapper;
import com.chen.sutoj.model.dto.AddProblemRequest;
import com.chen.sutoj.model.dto.ContestsRequest;
import com.chen.sutoj.model.entity.Contests;
import com.chen.sutoj.model.entity.Problems;
import com.chen.sutoj.model.vo.ContestsVO;
import com.chen.sutoj.service.ContestsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 86176
 * @description 针对表【contests(竞赛信息表)】的数据库操作Service实现
 * @createDate 2025-06-30 13:43:51
 */
@Service
public class ContestsServiceImpl extends ServiceImpl<ContestsMapper, Contests>
        implements ContestsService {

    @Resource
    ProblemsMapper problemsMapper;
    /**
     * 查询竞赛列表
     *
     * @param pageRequest
     * @return
     */
    @Override
    public Page<ContestsVO> getAllContests(PageRequest pageRequest) {
        Page page = new Page(pageRequest.getCurrent(), pageRequest.getPageSize());
        QueryWrapper<Contests> contestsQueryWrapper = new QueryWrapper<>();
        contestsQueryWrapper.orderByDesc("start_time");
        Page<Contests> contestsPage = this.page(page, contestsQueryWrapper);
        List<ContestsVO> contestsVOList = contestsPage.getRecords()
                .stream()
                .map(contests -> {
                    ContestsVO contestsVO = new ContestsVO();
                    BeanUtils.copyProperties(contests, contestsVO);
                    return contestsVO;
                })
                .collect(Collectors.toList());
        Page<ContestsVO> contestsVOPage = new Page<ContestsVO>();
        contestsVOPage.setRecords(contestsVOList);
        contestsVOPage.setTotal(contestsPage.getTotal());
        contestsVOPage.setSize(pageRequest.getPageSize());
        contestsVOPage.setCurrent(pageRequest.getCurrent());
        return contestsVOPage;
    }

    /**
     * 添加竞赛
     *
     * @param contestsRequest
     */
    @Override
    public void addContest(ContestsRequest contestsRequest) {
        Contests contests = new Contests();
        BeanUtils.copyProperties(contestsRequest, contests);
        contests.setStatus("PENDING");
        boolean result = this.save(contests);
         ThrowUtils.throwIf( !result, ErrorCode.SYSTEM_ERROR);;


    }

    /**
     * 删除竞赛
     *
     * @param id
     */
    @Transactional
    @Override
    public void deleteContest(Long id) {
        baseMapper.delete(new QueryWrapper<Contests>().eq("id", id));
        //删除竞赛下的所有题目
        QueryWrapper<Problems> problemsQueryWrapper = new QueryWrapper<>();
        problemsQueryWrapper.eq("contest_id", id);
        problemsMapper.delete(problemsQueryWrapper);
    }

    /**
     * 添加题目
     *
     * @param addProblemRequest
     */
    @Override
    public void addProblem(AddProblemRequest addProblemRequest) {
        Problems problems = new Problems();
        BeanUtils.copyProperties(addProblemRequest, problems);
        problemsMapper.insert(problems);
    }
}




