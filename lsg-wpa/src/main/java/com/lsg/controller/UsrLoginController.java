package com.lsg.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsg.model.Result;
import com.lsg.service.UserLoginService;
import com.lsg.vo.UserLoginVo;


@RestController
@RequestMapping(value="/user")
public class UsrLoginController {
	
private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private UserLoginService userLoginService;	
	
	
	/**
	   * 用户登陆
	 * */
	@RequestMapping("/login")
	@ResponseBody
	public Result  login(@RequestBody(required = false) @Valid UserLoginVo userLoginVo,
            @RequestHeader(value = "openId", required = true) String  openId) throws Exception {


		logger.info("用户登录开始");
		
		Result out = userLoginService.UserLogin(userLoginVo,openId);
		
		logger.info("用户登录结束");
		return out;	
		
	} 
	
	
	/**
	   * 用户登陆
	 * */
	@RequestMapping("/omslogin")
	@ResponseBody
	public Result  omslogin(@RequestBody(required = false) @Valid UserLoginVo userLoginVo) throws Exception {


		logger.info("内管登录开始");
		
		Result out = userLoginService.OmsUserLogin(userLoginVo);
		
		logger.info("内管登录结束");
		return out;	
		
	} 
	
}
