package com.lsg.common;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsg.model.Result;
import com.lsg.vo.PostionInfoVo;

public class ComonUtil {
	
	private static Logger logger = LoggerFactory.getLogger(ComonUtil.class);
	 /**
     * 获取请求参数中所有的信息
     * 
     * @param request
     * @return
     */
    public static Map<String, String> getRequestParam(final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                // 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
                if (null == res.get(en) || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        return res;
    }
    
    

	
	public static  void printlnRequest( Object vo, String openId) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper(); //json
		String input = objectMapper.writeValueAsString(vo);
		logger.info("request input:body{} header:{} ", input,openId);
		
	}
	
	public static void printlnResponse(Result out) throws JsonProcessingException { 
		ObjectMapper objectMapper = new ObjectMapper(); //json
		String output = objectMapper.writeValueAsString(out.getData());
		logger.info("response output:body{} header:{}", output,out.toString());
	}

}
