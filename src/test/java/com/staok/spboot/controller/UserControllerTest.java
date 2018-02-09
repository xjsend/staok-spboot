package com.staok.spboot.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.alibaba.fastjson.JSON;
import com.staok.spboot.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired  
    private UserController controller;  
	@Autowired
    private MockMvc mvc;
	
	@Before  
    public void setUp() throws Exception {  
        mvc = MockMvcBuilders.standaloneSetup(controller).build();  
    }
    

    @Test
    public void getUserByIdTwo() throws Exception {
    	String userId = "169b61437b064df0a952d0202363a1c3";
    	MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/user/getById/"+userId)
    			.accept(MediaType.APPLICATION_JSON)).andReturn();  
        Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());  
        String body = result.getResponse().getContentAsString();
        User user = JSON.parseObject(body,User.class);
        Assert.assertEquals(user.getId(), userId);  
    }
}
