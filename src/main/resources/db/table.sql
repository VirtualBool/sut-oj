-- 创建库
create database if not exists sut_oj;

-- 切换库
use sut_oj;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin',
    editTime     datetime     default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    UNIQUE KEY uk_userAccount (userAccount),
    INDEX idx_userName (userName)
) comment '用户' collate = utf8mb4_unicode_ci;

-- 存储竞赛的基本信息。
CREATE TABLE `contests`
(
    `id`                BIGINT       NOT NULL AUTO_INCREMENT COMMENT '竞赛ID',
    `name`              VARCHAR(255) NOT NULL COMMENT '竞赛名称',
    `start_time`        DATETIME     NOT NULL COMMENT '竞赛开始时间',
    `end_time`          DATETIME     NOT NULL COMMENT '竞赛结束时间',
    `duration_seconds`  INT          NOT NULL COMMENT '竞赛时长（秒）',
    `rules_description` TEXT COMMENT '竞赛描述以及规则描述',
    `status`            VARCHAR(50)  NOT NULL DEFAULT 'PENDING' COMMENT '竞赛状态: PENDING, RUNNING, FINISHED, ARCHIVED',
    `created_at`        DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`        DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_start_time` (`start_time`),
    INDEX `idx_status` (`status`)
) COMMENT ='竞赛信息表';

-- 存储参赛队伍的基本信息
CREATE TABLE `teams`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '队伍ID',
    `contest_id`  BIGINT       NOT NULL COMMENT '所属竞赛ID',
    `name`        VARCHAR(255) NOT NULL COMMENT '队伍名称',
    `institution` VARCHAR(255) COMMENT '所属学校/机构',
    `created_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_contest_team_name` (`contest_id`, `name`) -- 同一竞赛下队伍名唯一
) COMMENT ='参赛队伍信息表';

-- 存储竞赛的题目信息
CREATE TABLE `problems`
(
    `id`              BIGINT       NOT NULL AUTO_INCREMENT COMMENT '题目ID',
    `contest_id`      BIGINT       NOT NULL COMMENT '所属竞赛ID',
    `problem_code`    VARCHAR(50)  NOT NULL COMMENT '题目编号（例如：A, B, C）',
    `title`           VARCHAR(255) NOT NULL COMMENT '题目名称',
    `description`     TEXT COMMENT '题目描述（文件路径或内容）',
    `time_limit_ms`   INT COMMENT '时间限制（毫秒）',
    `memory_limit_mb` INT COMMENT '内存限制（MB）',
    `output_limit_mb` INT COMMENT '输出限制（MB）',
    `created_at`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_contest_problem_code` (`contest_id`, `problem_code`) -- 同一竞赛下题目编号唯一
) COMMENT ='竞赛题目信息表';

-- 存储每次选手提交代码的详细记录。
CREATE TABLE `submissions`
(
    `id`                BIGINT      NOT NULL AUTO_INCREMENT COMMENT '提交ID',
    `contest_id`        BIGINT      NOT NULL COMMENT '所属竞赛ID',
    `team_id`           BIGINT      NOT NULL COMMENT '提交队伍ID',
    `problem_id`        BIGINT      NOT NULL COMMENT '提交题目ID',
    `submission_time`   DATETIME    NOT NULL COMMENT '提交时间',
    `language`          VARCHAR(50) NOT NULL COMMENT '编程语言',
    `code_length`       INT COMMENT '代码长度',
    `verdict`           VARCHAR(50) NOT NULL COMMENT '判题结果：ACCEPTED, WRONG_ANSWER, TIME_LIMIT_EXCEEDED, MEMORY_LIMIT_EXCEEDED, RUNTIME_ERROR, COMPILE_ERROR, PENDING, JUDGING, etc.',
    `execution_time_ms` INT COMMENT '执行时间（毫秒）',
    `memory_used_mb`    INT COMMENT '内存使用（MB）',
    `is_first_accepted` BOOLEAN     NOT NULL DEFAULT FALSE COMMENT '是否是该队伍首次通过此题',
    `created_at`        DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    INDEX `idx_contest_team_problem` (`contest_id`, `team_id`, `problem_id`), -- 用于快速查询队伍在某题上的提交
    INDEX `idx_submission_time` (`submission_time`)
) COMMENT ='选手提交记录表';

-- 这是与 Redis Sorted Set 进行同步的表。
-- 它存储了每个队伍在特定竞赛中的最新积分和排名相关数据，供历史查询和最终统计使用。
CREATE TABLE `contest_team_rankings`
(
    `contest_id`            BIGINT   NOT NULL COMMENT '竞赛ID',
    `team_id`               BIGINT   NOT NULL COMMENT '队伍ID',
    `score`                 INT      NOT NULL DEFAULT 0 COMMENT '总得分（通常是AC题目数）',
    `penalty_time`          INT      NOT NULL DEFAULT 0 COMMENT '罚时（分钟）',
    `solved_problems_count` INT      NOT NULL DEFAULT 0 COMMENT '解决题目数',
    -- 存储每道题目的状态，例如：
    -- 可以是JSON格式存储每道题的尝试次数和通过状态，例如：
    -- `problem_status_json` JSON COMMENT '各题目状态（例: {"A": {"attempts": 2, "ac_time_minutes": 30}, "B": {"attempts": 1, "ac_time_minutes": null}})'
    -- 或者，更规范化地，如果需要详细查询，可以考虑一个单独的 `team_problem_status` 表
    `last_synced_at`        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后从Redis同步到数据库的时间',
    `created_at`            DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录首次创建时间',
    `updated_at`            DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
    PRIMARY KEY (`contest_id`, `team_id`),                                             -- 复合主键，唯一标识一个队伍在某个竞赛中的排名
    INDEX `idx_contest_score_penalty` (`contest_id`, `score` DESC, `penalty_time` ASC) -- 用于查询某一竞赛的排名（分数降序，罚时升序）
) COMMENT ='竞赛队伍实时排名快照表';