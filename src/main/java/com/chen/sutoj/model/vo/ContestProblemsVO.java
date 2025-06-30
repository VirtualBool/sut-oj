package com.chen.sutoj.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 竞赛题目信息表
 * @TableName problems
 */
@TableName(value ="problems")
@Data
public class ContestProblemsVO implements Serializable {
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

}