package com.lsg.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lsg.exception.BusinessException;
import com.lsg.model.Result;
import com.lsg.service.PositionReleaseService;
import com.lsg.vo.CommonVo;
import com.lsg.vo.PostionInfoVo;

@RestController
@RequestMapping("/postionRelease" )
public class PositionReleaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private PositionReleaseService positionReleaseService;	
	
	/**
	   * 职位发布维护-新增
	 * @throws BusinessException 
	 * */
	@ResponseBody
	@RequestMapping("/insert" )
	public Result insert(@RequestBody(required = false) @Valid PostionInfoVo postionInfoVo,
                     @RequestHeader(value = "openId", required = true) String openId) throws BusinessException {
		
		logger.info("/release/insert  param is {} ", postionInfoVo);
		//职位发布维护-新增
		return positionReleaseService.insert(postionInfoVo, openId);

	}
	
	/**
	   *职位发布维护-更新
	 * */
	@ResponseBody
	@RequestMapping("/update" )
	public Result update(@RequestBody(required = false) @Valid PostionInfoVo postionInfoVo,
            @RequestHeader(value = "openId", required = true) String openId) {
		
		//职位发布维护-更新
		return positionReleaseService.update(postionInfoVo, openId);

	}
	
	/**
	   *职位发布维护-删除
	 * */
	@ResponseBody
	@RequestMapping("/delete" )
	public Result delete(@RequestBody(required = false) @Valid PostionInfoVo postionInfoVo,
          @RequestHeader(value = "openId", required = true) String openId) {
		
		//职位发布维护-删除
		return positionReleaseService.delete(postionInfoVo, openId);

	}
	
	
	/**
	 *职位详情
	 * @throws BusinessException 
	 * */
	@ResponseBody
	@RequestMapping("/positionInfo" )
    public Result positionInfo(@RequestBody(required = false) @Valid PostionInfoVo postionInfoVo,
            @RequestHeader(value = "openId", required = true) String openId) throws BusinessException {
        
		//职位详情	
        return positionReleaseService.positionInfo(postionInfoVo, openId);

    }
	
}
 