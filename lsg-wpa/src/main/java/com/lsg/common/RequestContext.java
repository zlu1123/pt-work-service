package com.lsg.common;

import com.alibaba.fastjson.JSON;
import com.lsg.common.Constant;

import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Administrator
 */
public class RequestContext extends HttpServletRequestWrapper {


    private Logger logger = LoggerFactory.getLogger(getClass());

    HttpServletRequest customRequest = null;

    private final Map<String, String> headers;

    private byte[] body;

    public RequestContext(HttpServletRequest request) throws IOException {
        super(request);
        this.customRequest = request;
        this.headers = new HashMap<String, String>();
        body = HttpHelper.getBodyString(request).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        boolean bl = super.getHeader(HttpHeaders.CONTENT_TYPE).equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE);

        if (body != null && body.length > 0) {
            String bodyParams = new String(body);
            body = JsoupUtil.clean(bodyParams).getBytes();
            if (bl) {
                try {
                    bodyParams = new String(body);
                    body = this.dealWithParams(bodyParams);
                } catch (Exception e) {
                    logger.error("body参数,json转map异常,异常信息:{}", e);
                    throw new IOException(e.getMessage());
                }

            }
        }

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }

    /**
     * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(JsoupUtil.clean(name));
        if (null != value) {
            value = JsoupUtil.clean(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(JsoupUtil.clean(name));
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = JsoupUtil.clean(values[i]);
        }
        return encodedValues;
    }

    @Override
    public Map getParameterMap() {

        HashMap paramMap = (HashMap) super.getParameterMap();
        paramMap = (HashMap) paramMap.clone();

        for (Iterator iterator = paramMap.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String[] values = (String[]) entry.getValue();
            for (int i = 0; i < values.length; i++) {
                if (values[i] instanceof String) {
                    values[i] = JsoupUtil.clean(values[i]);
                }
            }
            entry.setValue(values);
        }
        return paramMap;
    }


    public void putHeader(String name, String value) {
        this.headers.put(name, value);
    }

    @Override
    public String getHeader(String name) {
        String headerName = JsoupUtil.clean(name);
        // check the custom headers first
        String headerValue = headers.get(headerName);

        if (headerValue != null) {
            return JsoupUtil.clean(headerValue);
        }
        // else return from into the original wrapped object
        return ((HttpServletRequest) getRequest()).getHeader(headerName);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        // create a set of the custom header names
        Set<String> set = new HashSet<String>(headers.keySet());

        // now add the headers from the wrapped request object
        @SuppressWarnings("unchecked")
        Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
        while (e.hasMoreElements()) {
            // add the names of the request headers into the list
            String n = e.nextElement();
            set.add(n);
        }

        // create an enumeration from the set and return
        return Collections.enumeration(set);
    }


    @Override
    public Enumeration<String> getHeaders(String name) {
        String headerName = JsoupUtil.clean(name);
        if (Constant.KEY_USERID.equals(headerName) || Constant.KEY_ORGID.equals(headerName) || Constant.KEY_TENANTID.equals(headerName)) {
            List<String> list = new ArrayList<>();
            list.add(this.getHeader(headerName));
            return Collections.enumeration(list);
        }

        Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaders(headerName);
        return e;
    }


    /**
     * 获取最原始的request
     *
     * @return
     */
    public HttpServletRequest getCustomRequest() {
        return customRequest;
    }

    /**
     * 获取最原始的request的静态方法
     *
     * @return
     */
    public static HttpServletRequest getCustomRequest(HttpServletRequest req) {
        if (req instanceof RequestContext) {
            return ((RequestContext) req).getCustomRequest();
        }
        return req;
    }


    private byte[] dealWithParams(String json) throws Exception {
        Map<String, Object> map = StringUtils.jsonToMap(json);

        Pattern r = Pattern.compile(Constant.PATTERN);
        for (String key : map.keySet()) {
            Object value = map.get(key);

            if (value instanceof List) {
                List list = (List) value;
                List resultList = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    Object o = list.get(i);
                    Matcher m = r.matcher(o.toString());
                    if (m.matches()) {
                        resultList.add(o);
                    }
                }
                value = resultList;
            }else {

                Matcher m = r.matcher(value.toString());
                if (!m.matches()) {
                    value = null;
                }
            }
            map.put(key, value);
        }
        return JSON.toJSONString(map).getBytes();
    }
}
