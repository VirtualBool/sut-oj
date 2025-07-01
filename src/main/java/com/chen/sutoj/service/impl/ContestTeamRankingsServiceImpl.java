package com.chen.sutoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sutoj.mapper.ContestTeamRankingsMapper;
import com.chen.sutoj.model.entity.ContestTeamRankings;
import com.chen.sutoj.service.ContestTeamRankingsService;
import org.springframework.stereotype.Service;

/**
* @author 86176
* @description 针对表【contest_team_rankings(竞赛队伍实时排名快照表，同时用做报名表)】的数据库操作Service实现
* @createDate 2025-07-01 19:04:03
*/
@Service
public class ContestTeamRankingsServiceImpl extends ServiceImpl<ContestTeamRankingsMapper, ContestTeamRankings>
    implements ContestTeamRankingsService {

}




