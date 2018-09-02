package network;

/**
 * Created by mayn on 2018/6/19.
 * 网络请求的Url
 */

public class NetWorkConfig {
//    /*正式sign*/
//    public static final String SIGN = "se5sdd2s236yix423";
    /*测试sign*/
    public static final String SIGN = "ak1d2ig3ewc6d4erd9";
    /**网络baseurl*/
    public static final String BaseURL = "http://58.87.86.183:9501/";
    /**用户注册url*/
    public static final String USER_RegisterURL ="user/user/register?";
    /**用户登录*/
    public static final String USER_LoginURL = "user/user/login?";
    /**用户退出*/
    public static final String USER_LoginOutURL = "user/user/logout?";
    /**修改密码*/
    public static final String USER_UpdatePWDURL = "user/user/updatePwd?";
    /**获取个人信息*/
    public static final String USER_GetInfoURL = "user/user/getDetails?";
    /**忘记密码*/
    public static final String USER_ForgetPWDURL = "user/user/forget?";
    /**获取卡片列表*/
    public static final String USER_UserCardList = "user/card/getList?";
    /**添加卡片*/
    public static final String USER_UserCardAdd = "user/card/add?";
    /**更新卡片*/
    public static final String USER_UserCardEdit = "user/card/edit?";
    /**删除卡片*/
    public static final String USER_UserCardDel = "user/card/del?";
    /**获取卡片详情*/
    public static final String USER_UserCardDetail = "user/card/getDetails?";
    /**获取账单列表*/
    public static final String USER_UserBillList = "user/bill/getList?";
    /**获取账单日志列表*/
    public static final String USER_UserBillLogList = "user/bill/getLogList?";
    /**获取还款记录*/
    public static final String USER_UserRepayList = "user/bill/getRepayLogList?";
    /**获取还款详情*/
    public static final String USER_UserBillDetail = "user/bill/getRepayDetails?";
    /**获取红包列表*/
    public static final String USER_UserRedPacList = "user/redpkg/getList?";
    /**获取邮箱列表*/
    public static final String OPEN_OpenMailList = "open/mail/getList?";
    /**获取银行列表*/
    public static final String OPEN_OpenBankList = "open/bank/getList?";
    /**获取城市列表*/
    public static final String OPEN_OpenCityList = "open/city/getList?";
    /**生成城市缓存*/
    public static final String OPEN_OpenCityCache = "open/city/makeCache?";
    /**联系银行*/
    public static final String OPEN_OpenBankContact = "open/bank/contact?";
    /**联系我们*/
    public static final String OPEN_OpenBankAboutUs = "open/about/contact?";
    /**检测版本*/
    public static final String OPEN_OpenVersionCheck = "open/version/check?";
    /**获取消息分类*/
    public static final String USER_UserMSGType = "user/msg/getType?";
    /**获取消息列表*/
    public static final String USER_UserMSGList = "user/msg/getList?";
    /**获取消息详情*/
    public static final String USER_UserMSGDetail = "user/msg/getDetails?";
    /**标记已还清*/
    public static final String USER_MarkRepayOff = "user/bill/markRepayOff?";
    /**标记已还部分*/
    public static final String USER_MarkRepayedAmount = "user/bill/markRepayedAmount?";
    /**获取首页轮播图*/
    public static final String OPEN_HomeBanner = "open/about/getHomeBanner?";
}
