package com.staok.spboot.controller;

import com.staok.spboot.entity.User;
import com.staok.spboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.staok.spboot.mapper.UserMapper;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@ResponseBody
    @RequestMapping(value = "/getById/{id}", produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public Object getUserById(@PathVariable("id") String id){
        User user =  userService.selectById(id);
        if(user == null){
            return "data not exist!";
        }
        return user;
    }
    @ResponseBody
    @RequestMapping(value = "/getTestById/{id}", produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public Object getTestById(@PathVariable("id") String id){
        User user =  userService.selectTestById(id);
        if(user == null){
            return "data not exist!";
        }
        return user;
    }
}
