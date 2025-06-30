package com.chen.sutoj.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 返回给前端的竞赛列表类
 *
 * @TableName contests
 */
@TableName(value = "contests")
@Data
@NoArgsConstructor
public class ContestsVO implements Serializable {
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

    /**
     * 竞赛状态: PENDING, RUNNING, FINISHED, ARCHIVED
     */
    @TableField(value = "status")
    private String status;


}