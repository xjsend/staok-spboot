package com.staok.spboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.staok.spboot.entity.BigData;
@Mapper
public interface BigDataMapper {
	/**
	 * 通过id查询数据
	 * @param id
	 * @return
	 */
	BigData selectById(@Param("actId")Long id);
	/**
	 * 查询数据
	 * @return
	 */
	Page<BigData> selectAll();
	/**
	 * 总记录数
	 * @return
	 */
	Integer selectAllCount();
	/**
	 * 数据插入
	 * @param bigData
	 * @return
	 */
	int insert(@Param("bean")BigData bigData);
}
