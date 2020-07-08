package com.io.hydralisk.constant;

/**
 * 跳转页面地址常量
 */
public class PageConst {
    public static final String WEB_HOST = "http://localhost:8080/";
    public static final String CATEGORY_INDEX = WEB_HOST + "/h5/pageb2c/b2c_category/index";//商品类别首页
    public static final String ABOUT_US = WEB_HOST+"/h5/pages/html/aboutus";//关于页面
    public static final String PRODUCT_SHOW = WEB_HOST+"/h5/pageb2c/b2c_product/show?id=";//显示商品
    public static final String PRODUCT_LIST_4CATEGORY = WEB_HOST+"/h5/pageb2c/b2c_product/list?catid=";//根据品项类别显示商品列表
    public static final String KEFU_INDEX = WEB_HOST+ "/h5/pages/kefu/index";//客服页面
    public static final String INDEX_LOGIN = WEB_HOST+ "/index.php?m=login";//登录页面
    public static final String INDEX_PAGE = WEB_HOST+ "/index.php";//首页
    public static final String USER_INDEX = WEB_HOST+ "/h5/pageb2c/b2c_user/index";//登录后进入首页
    public static final String MY_NOTICE = WEB_HOST+ "/h5/pages/notice/my";//我的消息页面
    public static final String MY_PAY_LOG = WEB_HOST+ "/h5/pages/pay_log/my";//消费记录
    public static final String RECHARGE_LOG = WEB_HOST+ "/h5/pages/recharge/my";//充值记录

    public static final String ORDER_CONFIRM = WEB_HOST+ "/h5/pageb2c/b2c_order/confirm";//订单确认页面
    public static final String ORDER_LIST = WEB_HOST+ "/h5/pageb2c/b2c_order/my";//订单列表页面
    public static final String ORDER_DETAIL_SHOW = WEB_HOST+ "/h5/pageb2c/b2c_order/show?orderid=";//订单详情页面

    public static final String ADDRESS_ADD = WEB_HOST+ "/h5/pages/user_address/add";//添加收货地址页面

    public static final String CART_SHOW = WEB_HOST+ "/h5/pageb2c/b2c_cart/index";//查询购物车信息

    public static final String USER_PWD_SHOW = WEB_HOST+ "/h5/pages/user/password";//修改用户(密码)页面
    public static final String USER_PAY_PWD_SHOW = WEB_HOST+ "/h5/pages/user/paypwd";//修改支付密码页面
    public static final String USER_INFO_SHOW = WEB_HOST+  "/h5/pages/user/info";//用户信息页面
    public static final String USER_SET_SHOW = WEB_HOST+  "/h5/pages/user/set";//用户设置页面



}
