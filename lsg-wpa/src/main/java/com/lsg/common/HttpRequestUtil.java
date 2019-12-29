package com.lsg.common;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Administrator
 */
public abstract class HttpRequestUtil {

    /**
     * 获取客户端真实IP(有代理的请况)
     *
     * @param request
     * @return
     */
    public static String getRealRemoteAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static void resolveContext(HttpServletRequest request) {
        String reqUri = request.getServletPath();
        String reqMethod = request.getMethod().toUpperCase();
        String clientIp = HttpRequestUtil.getRealRemoteAddr(request);
        RequestDealContext.putClientIp(clientIp);
        RequestDealContext.putReqUri(reqUri);
        RequestDealContext.putReqMethod(reqMethod);

        String logString = StringUtils.rightPad(reqMethod, 5, ' ') + " " + reqUri;
        RequestDealContext.putLogString(logString);

        RequestDealContext.putTimeStart(System.currentTimeMillis());
    }
}
