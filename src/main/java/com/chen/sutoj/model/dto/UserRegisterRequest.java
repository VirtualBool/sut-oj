package com.chen.sutoj.model.dto;


import lombok.Data;


/**
 * 用户
 *
 * @TableName user
 */

@Data
public class UserRegisterRequest {


    /**
     * 账号
     */

    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 重复密码
     */
    private String userPassword2;

    /**
     * 验证码
     */
    private String captcha;


}