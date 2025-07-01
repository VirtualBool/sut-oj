package com.chen.sutoj.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 竞赛队伍实时排名快照表，同时用做报名表
 * @TableName contest_team_rankings
 */
@TableName(value ="contest_team_rankings")
@Data
public class ContestTeamRankings implements Serializable {
    /**
     * 竞赛ID
     */
    @TableId(value = "contest_id")
    private Long contestId;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 总得分（通常是AC题目数）
     */
    @TableField(value = "score")
    private Integer score;

    /**
     * 罚时（分钟）
     */
    @TableField(value = "penalty_time")
    private Integer penaltyTime;

    /**
     * 解决题目数
     */
    @TableField(value = "solved_problems_count")
    private Integer solvedProblemsCount;

    /**
     * 各题目状态
     */
    @TableField(value = "problem_status_json")
    private Object problemStatusJson;

    /**
     * 记录首次创建时间
     */
    @TableField(value = "created_at")
    private LocalDateTime createdAt;

    /**
     * 记录更新时间,即redis最新谢图时间
     */
    @TableField(value = "updated_at")
    private LocalDateTime updatedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}