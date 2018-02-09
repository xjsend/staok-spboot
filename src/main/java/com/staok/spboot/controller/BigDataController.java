package com.staok.spboot.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.staok.spboot.entity.BigData;
import com.staok.spboot.mapper.BigDataMapper;
import com.staok.spboot.service.BigDataService;
import com.staok.spboot.utils.ExcelDataExportUtils;
import com.staok.spboot.utils.ObjectMapUtils;
import com.staok.spboot.vo.ResponseMsg;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/bigData")
public class BigDataController {
    private final static Logger  logger = LoggerFactory.getLogger(BigDataController.class);
	@Autowired
	private BigDataService bigDataService;
	@Autowired
	private BigDataMapper bigDataMapper;
	
	@ResponseBody
    @RequestMapping(value = "/batchInsert", produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public Object batchInsert(){
		BigData bigData = bigDataMapper.selectById(1l);
		for(int i=1;i<100000;i++){
			bigDataMapper.insert(bigData);
		}
        return new ResponseMsg("1","成功！");
    }
	

	@ResponseBody
    @RequestMapping(value = "/selectPage", produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public Object selectPage(){
		Page<BigData> list = bigDataService.findByPage(1, 5);
		logger.info("查询数量：" + list.getResult().size());
		return new ResponseMsg("1","成功！",list);
    }
	
	public PageInfo<Map<String, Object>> getExportDataList(int pageIndex,int pageSize){
		Page<BigData> pageList = bigDataService.findByPage(pageIndex, pageSize);
    	PageInfo<BigData> pageInfoList = new PageInfo<BigData>(pageList);
    	List<Map<String, Object>> rsList = ObjectMapUtils.objectToMap(pageInfoList.getList());
    	PageInfo<Map<String, Object>> rsPage = new PageInfo<Map<String, Object>>();
    	rsPage.setList(rsList);
    	rsPage.setPages(pageList.getPages());
    	return rsPage;
	}
	
	/**
	 * 数据导出
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
    @RequestMapping(value = "/exportData",method = RequestMethod.GET)
    public Object exportData(HttpServletResponse response) throws UnsupportedEncodingException{
		String[] keys = {"actTypeName","actName","doctorId","doctorName","doctorHospital","doctorDepartments","doctorTitle","hospitalProvince","hospitalCity","isJoinCircle",
                "subject","startTime","endTime","limit","signed","referrerIntegral","doctorCredit","mrId","mrName","mrDepartment","isEnterDetail","received","addCredit"};
		String[] columnNames = new String[]{"活动类型", "活动名称", "医生ID", "医生姓名", "医生所属医院", "医生所属科室", "医生职称", "医生医院所在省份", "医生医院所在市区","是否通过福利加入的圈子",
                "直播主题", "开始时间", "结束时间", "限制人数", "已参加人数", "推广人员奖励", "医生奖励", "医药代表ID", "医药代表姓名", "医药代表所在部门", "是否进入福利详情页", "是否领取福利", "领取学币数量"};
		String fileName = "大数据测试";
		//输出流
		int pageSize = 30000;
    	int pageIndex = 1,pageTotal = 1;
    	List<Map<String, Object>> dataList = null;
    	SXSSFWorkbook wbook = null;
		do{
			PageInfo<Map<String, Object>> pageList = getExportDataList(pageIndex, pageSize);
	    	pageTotal = pageList.getPages();
	    	dataList = pageList.getList();
	    	//创建excel表格
			wbook = ExcelDataExportUtils.createWorkBook(wbook,"sheet"+ pageIndex,dataList, keys, columnNames);
	    	pageIndex ++;
		}while(pageIndex<=pageTotal);
		//创建excel表格
		try {
			this.writeData(response, wbook, fileName, columnNames, keys, dataList);
			return new ResponseMsg("1","成功！");
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseMsg("2","失败！");
		}
    }
    
    public void writeData(HttpServletResponse response,SXSSFWorkbook wbook, String fileName, String[] columnNames, String[] keys,
			List<Map<String, Object>> list) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		//创建excel表格
		wbook.write(os);
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
}
