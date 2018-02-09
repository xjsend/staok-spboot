package com.staok.spboot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.staok.spboot.entity.User;
@Mapper
public interface UserMapper {
	
	User selectById(String id);
}
