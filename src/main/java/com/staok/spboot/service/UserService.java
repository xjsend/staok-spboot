package com.staok.spboot.service;

import com.staok.spboot.entity.User;
import com.staok.spboot.mapper.UserMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 用户service
 * Created by xiejs on 2018/2/9.
 */
@Service
public class UserService {
    @Resource(name = "testSqlSessionTemplate")
    private SqlSessionTemplate testSqlSessionTemplate;
    @Autowired
    private UserMapper userMapper;

    public User selectById(String id){
        return userMapper.selectById(id);
    }

    public User selectTestById(String id){
        return testSqlSessionTemplate.getMapper(UserMapper.class).selectById(id);
    }
}
