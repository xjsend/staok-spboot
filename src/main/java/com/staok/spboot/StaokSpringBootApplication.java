package com.staok.spboot;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.pagehelper.PageHelper;
/**
 * 项目启动类
 * @author xiejs
 */
@SpringBootApplication
public class StaokSpringBootApplication {
	/**
	 * 启动方法
	 * @param args
	 */
	public static void main(String[] args){
        SpringApplication.run(StaokSpringBootApplication.class,args);
    }
}
