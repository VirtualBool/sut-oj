package com.chen.sutoj.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sutoj.mapper.UserMapper;
import com.chen.sutoj.model.entity.User;
import com.chen.sutoj.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 86176
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2025-06-27 10:20:52
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}




