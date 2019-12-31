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

import com.lsg.model.Result;
import com.lsg.service.CommonService;
import com.lsg.vo.CommonVo;

@RestController
@RequestMapping("/common" )
public class CommonController {
	

	@Autowired
    private CommonService commonService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 
	   *广告信息
	 * */
	@ResponseBody
	@RequestMapping("/advert" )
    public Result advert(@RequestHeader(value = "openId", required = true) String openId) {
        //广告信息	
        return commonService.advert(openId);

    }
	
	
	
	/**
	 *首页职位信息 
	 * */
	@ResponseBody
	@RequestMapping("/queryPosition" )
    public Result queryPosition(@RequestBody(required = false) @Valid CommonVo queryPostionVo,
            @RequestHeader(value = "openId", required = true) String openId) {
        
		//职位信息	
        return commonService.queryPosition(queryPostionVo, openId);

    }
	
	
	
	/**
	 *投诉建议
	 * */
	@ResponseBody
	@RequestMapping("/suggestions" )
    public Result suggestions(@RequestBody(required = false) @Valid CommonVo suggestionsVo,
    		@RequestHeader(value = "openId", required = true) String openId) {
        
		//投诉建议
        return commonService.suggestions(suggestionsVo, openId);

    }
	
}
