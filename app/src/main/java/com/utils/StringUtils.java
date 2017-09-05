package com.utils;

/**
 * 字符串工具类
 */
public class StringUtils {

    public final static String MOBILE_REG = "(^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$)|(^0?[1][3578][0-9]{9}$)";
    public final static String EMAIL_REG = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
    public final static String URL_REG ="^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://|)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$";
    public final static String QQ_REG = "^[1-9][0-9]{4,14}$";
    public final static String PASSWORD_REG = "^[\\w]{6,20}$";
    public final static String IS_LOADING = "is_loading";
    public final static String ADMIN_ENTITY = "admin_entity";
    public final static String ADMIN_ID = "admin_id";
    public final static String IS_ADMIN_LOGIN = "is_admin_login";
    public final static String MEMBER_ENTITY = "member_entity";
    public final static String MEMBER_ID = "member_id";
    public final static String IS_MEMBER_LOGIN = "is_member_login";
    public final static String KEY = "key";
    public final static String VALUE = "value";
    public final static String MEMBER_ENTITY_LIST = "member_entity_list";
    public final static String JUMPTO = "jump_to";  //跳转到
    public final static String JUMP_TO_BUNDLE = "jump_to_bundle"; //跳转，附带信息
    public final static String REFRESHPAGE = "refreshPage";
    public final static String ITEM_TYPE_ENTITY_LIST = "item_type_entity_list";
    public final static String ITEM_ENTITY_LIST = "item_entity_list";
    public final static String SIGN = "sign";
    public final static String SIGN_CUSTOMER_MANAGEMENT = "sign_customer_management";   //用于 客户管理列表页，某项跳转到别的页，再回到客户管理列表页时，能够显示该项
    public final static String BUSINESS_TEMPLATE_ENTITY_LIST = "business_template_entity_list";
    public final static String SYSTEM_TEMPLATE = "system_template";
    public final static String USER_DEFINED_TEMPLATE = "user_defined_template";
    public final static String TYPE = "type";
    public final static String TEMP_TEMPLATE = "temp_template";
    public final static String TEMPLATE_ENTITY_LIST = "template_entity_list";
    public final static String CONTRACT_ENTITY_LIST = "contract_entity_list";
    public final static String ITEM_ID = "item_id"; //项目id，用于进入商务跟踪页，在其三个子页面使用项目id
    public final static String CONTACT_ENTITY_LIST = "contact_entity_list";
    public final static String ENTITY = "entity";
    public final static String BUSINESS_LOG_ENTITY_LIST = "business_log_entity_list";
    public final static String ITEM_FEE_ENTITY_LIST = "item_fee_entity_list";   //项目费用信息集合
    public final static String ITEM_FILE_ENTITY_LIST = "item_file_entity_list";     //材料交互信息集合
    public final static String MY_SERVICE_ENTITY_LIST = "my_service_entity_list";     //我的服务 信息集合
    public final static String MESSAGE_ENTITY_LIST = "message_entity_list";     // 留言 集合
    public final static String UPDATE_DONE = "update_done";  //修改操作完成
    public final static String NUMINFO = "num_info";    // 企业用户  留言总条数、资料互动次数、服务个数、机构个数、未读留言条数  信息  or  服务机构  未读留言数
    public final static String UNREADEDINFO = "unReadedInfo"; // 未读留言的详情
    public final static String NAVIGATIONDATAREFRESH = "navigation_data_refresh"; //首页 侧滑导航栏 数据刷新 true/false ；点击 侧滑导航栏的数据刷新键时，数据获取成功时，设置为true,会在相应页面的  initData()方法中依据此标记做相应操作  同时 下拉刷新功能和initData()方法执行时，取消该标记
    public final static String SOFT_KEY_BOARD_HEIGHT="soft_key_board_height";//软键盘高度
    public final static String FROM_SWITCH_NOT_SAVE="from_switch_not_save";
    public final static String SEARCH_CONDITION="search_condition";
    public final static String CONTRACT_FLAG="contract_flag";
    public final static String ITEM_FILE_FLAG="item_file_flag";
    public final static String USER_CONTRACT_FLAG="user_contract_flag";
    public final static String USER_ITEM_FILE_FLAG="user_item_file_flag";
    public static final String MIME_MapTable[][]={
            //{后缀名，MIME类型}
            {".3gp",    "video/3gpp"},
            {".apk",    "application/vnd.android.package-archive"},
            {".asf",    "video/x-ms-asf"},
            {".avi",    "video/x-msvideo"},
            {".bin",    "application/octet-stream"},
            {".bmp",    "image/bmp"},
            {".c",  "text/plain"},
            {".class",  "application/octet-stream"},
            {".conf",   "text/plain"},
            {".cpp",    "text/plain"},
            {".doc",    "application/msword"},
            {".docx",   "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls",    "application/vnd.ms-excel"},
            {".xlsx",   "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe",    "application/octet-stream"},
            {".gif",    "image/gif"},
            {".gtar",   "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h",  "text/plain"},
            {".htm",    "text/html"},
            {".html",   "text/html"},
            {".jar",    "application/java-archive"},
            {".java",   "text/plain"},
            {".jpeg",   "image/jpeg"},
            {".jpg",    "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log",    "text/plain"},
            {".m3u",    "audio/x-mpegurl"},
            {".m4a",    "audio/mp4a-latm"},
            {".m4b",    "audio/mp4a-latm"},
            {".m4p",    "audio/mp4a-latm"},
            {".m4u",    "video/vnd.mpegurl"},
            {".m4v",    "video/x-m4v"},
            {".mov",    "video/quicktime"},
            {".mp2",    "audio/x-mpeg"},
            {".mp3",    "audio/x-mpeg"},
            {".mp4",    "video/mp4"},
            {".mpc",    "application/vnd.mpohun.certificate"},
            {".mpe",    "video/mpeg"},
            {".mpeg",   "video/mpeg"},
            {".mpg",    "video/mpeg"},
            {".mpg4",   "video/mp4"},
            {".mpga",   "audio/mpeg"},
            {".msg",    "application/vnd.ms-outlook"},
            {".ogg",    "audio/ogg"},
            {".pdf",    "application/pdf"},
            {".png",    "image/png"},
            {".pps",    "application/vnd.ms-powerpoint"},
            {".ppt",    "application/vnd.ms-powerpoint"},
            {".pptx",   "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop",   "text/plain"},
            {".rc", "text/plain"},
            {".rmvb",   "audio/x-pn-realaudio"},
            {".rtf",    "application/rtf"},
            {".sh", "text/plain"},
            {".tar",    "application/x-tar"},
            {".tgz",    "application/x-compressed"},
            {".txt",    "text/plain"},
            {".wav",    "audio/x-wav"},
            {".wma",    "audio/x-ms-wma"},
            {".wmv",    "audio/x-ms-wmv"},
            {".wps",    "application/vnd.ms-works"},
            {".xml",    "text/plain"},
            {".z",  "application/x-compress"},
            {".zip",    "application/x-zip-compressed"},
            {"",        "*/*"}
    };

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

}
