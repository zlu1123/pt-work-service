package com.lsg.common;

/**
 * 常量
 */
public class Constant {

    public static final String RET_CODE_SUCCESS = "00000";
    public static final String RET_INFO_SUCCESS = "操作成功";

    public static final String RET_CODE_DEBUG = "00001";
    public static final String RET_INFO_DEBUG = "系统内部错误";

    public static final String RET_CODE_ERROR = "00002";
    public static final String RET_INFO_ERROR = "操作失败";

    public static final String RET_CODE_PARAM_NULL = "00100";
    public static final String RET_INFO_PARAM_NULL = "参数为空";

    public static final String RET_CODE_ID_NULL = "00101";
    public static final String RET_INFO_ID_NULL = "id为空";

    public static final String RET_CODE_OBJ_NULL = "00102";
    public static final String RET_INFO_OBJ_NULL = "该id对应的对象不存在";

    //id字符长度
    public static final Integer ID_MAX_LENGTH = 32;
    public static final String RET_INFO_ID_FORMAT_ERR = "ID最长32位字符！";

    /**
     * 禁用
     */
    public static final Character STATUS_DISABLED = '0';
    public static final String STATUS_DISABLED_MESSGE = "停用";

    /**
     *  启用
     */
    public static final Character STATUS_NORMAL = '1';
    public static final String STATUS_NORMAL_MESSGE = "启用";


    /**
     *  删除
     */
    public static final Character STATUS_REMOVE = '2';

    /**
     *  平台初始密码
     */
    public static final String INIT_PASSWORD = "pt123456";

    /**
     * 租户初始密码
     */
    public static final String INIT_TENANT_PASSWORD = "zh123456";


    public  static final String CHAR_INIT = "-";


    /**
     * 默认页码
     */
    public static final Integer INIT_PAGENUM = 1;

    /**
     * 默认页容量
     */
    public static final Integer INIT_PAGESIZE = 50;

    /**
     * 最大页容量
     */
    public static final Integer MAX_PAGENUM = 1024;


    public static final Character DELETE_STATUS_TRUE = '1';

    public static final Character DELETE_STATUS_FALSE = '0';

    /**
     *  个人
     */
    public static final Character STATUS_PERSONAL = '1';

    /**
     *  企业
     */
    public static final Character STATUS_COMPANY = '2';


    /**
     * 平台租户ID
     */
    public static final String PT_TENANT_ID = "-1";


    /**
     * 租户管理员角色ID
     */
    public static final  String TENANT_MANGER_ROLE = "294ab88a9bdf487bb3f081120814e0f5";



    public static final String KEY_USERID = "userId";

    public static final String KEY_TENANTID = "tenantId";

    public static final String KEY_ORGID = "orgId";

    public static final  String KEY_USERNAME = "userName";

    public static final  String KEY_CLIENTID = "clientId";

    public static final  String KEY_TELEPHONE = "telephone";

    public static final String KEY_APPLICATION = "X-app-id";

    public static  final String KEY_AUTHCODE = "auth_code";

    public static final  String KEY_TIME = "timestamp";
    /**
     * 根菜单 父级ID
     */
    public static final String SNAIL_RESOURCE_ROOT = "-1";
    /**
     * 平台默认系统ID
     */
    public static final String PT_APPLICATION_CODE = "27b55ffc786c4ff093d89543818a0b0a";

    /**
     * 平台默认系统ID
     */
    public static final Integer MAX_LEVEL = 10;

    //application type
    public static final String APPLICATION_TYPE ="1";
    //level
    public static final Integer APPLICATION_LEVEL=1;

    //resource 父级Id
    public static final String RESOURCE_PARENT_ID = "33a03e690bf84a36966170e6479787f9";

    public static final String SNAIL_RESOURCE_CODE = "root";


    public static final  String PATTERN = "^[a-zA-Z0-9_@\\.\u4e00-\u9fa5]+$";

}
