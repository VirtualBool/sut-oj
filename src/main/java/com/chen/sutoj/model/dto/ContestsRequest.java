package com.chen.sutoj.model.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 创建竞赛请求信息表
 *
 * @TableName contests
 */

@Data
public class ContestsRequest {

    /**
     * 竞赛名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 竞赛开始时间
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    /**
     * 竞赛结束时间
     */
    @TableField(value = "end_time")
    private LocalDateTime endTime;

    /**
     * 竞赛时长（秒）
     */
    @TableField(value = "duration_seconds")
    private Integer durationSeconds;

    /**
     * 竞赛描述以及规则描述
     */
    @TableField(value = "rules_description")
    private String rulesDescription;


}