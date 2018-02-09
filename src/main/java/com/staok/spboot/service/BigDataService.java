package com.staok.spboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.staok.spboot.entity.BigData;
import com.staok.spboot.mapper.BigDataMapper;

/**
 * 大数据service
 * @author xiejs
 */
@Service
public class BigDataService {
	@Autowired
	private BigDataMapper bigDataMapper;
	/**
	 * 分页数据查询
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
    public Page<BigData> findByPage(int currentPage,int pageSize) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);
        Page<BigData> dataPage = bigDataMapper.selectAll();
        return dataPage;
    }
}
