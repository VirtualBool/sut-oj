package com.chen.sutoj.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 竞赛信息表
 *
 * @TableName contests
 */
@TableName(value = "contests")
@Data
public class Contests implements Serializable {
    /**
     * 竞赛ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at")
    private LocalDateTime updatedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}