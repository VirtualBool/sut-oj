package com.chen.sutoj.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.sutoj.common.PageRequest;
import com.chen.sutoj.model.dto.ContestsRequest;
import com.chen.sutoj.model.entity.Contests;
import com.chen.sutoj.model.vo.ContestsVO;

/**
 * @author 86176
 * @description 针对表【contests(竞赛信息表)】的数据库操作Service
 * @createDate 2025-06-30 13:43:51
 */
public interface ContestsService extends IService<Contests> {

    /**
     * 查询竞赛列表
     *
     * @param pageRequest
     * @return
     */
    Page<ContestsVO> getAllContests(PageRequest pageRequest);

    /**
     * 添加竞赛
     *
     * @param contestsRequest
     */
    void addContest(ContestsRequest contestsRequest);
}
