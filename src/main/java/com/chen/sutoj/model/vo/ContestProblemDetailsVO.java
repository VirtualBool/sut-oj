package com.chen.sutoj.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 竞赛题目信息表
 * @TableName problems
 */
@TableName(value ="problems")
@Data
public class ContestProblemDetailsVO {
    /**
     * 题目ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 所属竞赛ID
     */
    @TableField(value = "contest_id")
    private Long contestId;

    /**
     * 题目编号（例如：A, B, C）
     */
    @TableField(value = "problem_code")
    private String problemCode;

    /**
     * 题目名称
     */
    @TableField(value = "title")
    private String title;

    /**
     * 题目描述（文件路径或内容）
     */
    @TableField(value = "description")
    private String description;

    /**
     * 时间限制（毫秒）
     */
    @TableField(value = "time_limit_ms")
    private Integer timeLimitMs;

    /**
     * 内存限制（MB）
     */
    @TableField(value = "memory_limit_mb")
    private Integer memoryLimitMb;

    /**
     * 输出限制（MB）
     */
    @TableField(value = "output_limit_mb")
    private Integer outputLimitMb;

}