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
import com.staok.spboot.vo.ResponseMsg;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BigDataControllerTest {
	@Autowired  
    private BigDataController controller;  
	@Autowired
    private MockMvc mvc;
	
	@Before  
    public void setUp() throws Exception {  
        mvc = MockMvcBuilders.standaloneSetup(controller).build();  
    }
    

    @Test
    public void getUserById() throws Exception {
    	MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/bigData/batchInsert")
    			.accept(MediaType.APPLICATION_JSON)).andReturn();  
        Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());  
        String body = result.getResponse().getContentAsString();
        ResponseMsg respMsg = JSON.parseObject(body,ResponseMsg.class);
        Assert.assertEquals("1", respMsg.getCode());  
    }
    @Test
    public void getAllPage() throws Exception {
    	MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/bigData/selectPage")
    			.accept(MediaType.APPLICATION_JSON)).andReturn();  
        Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());  
        String body = result.getResponse().getContentAsString();
        ResponseMsg respMsg = JSON.parseObject(body,ResponseMsg.class);
        System.out.println("===="+ JSON.toJSONString(respMsg));
        Assert.assertEquals("1", respMsg.getCode());  
    }
}
