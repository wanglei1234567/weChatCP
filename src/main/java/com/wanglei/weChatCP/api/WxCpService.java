package com.wanglei.weChatCP.api;

import com.wanglei.weChatCP.config.WxCpConfigStorage;

public interface WxCpService {
    /**
     * 注入 {@link WxCpConfigStorage} 的实现
     */
    void setWxCpConfigStorage(WxCpConfigStorage wxConfigProvider);

    /**
     * 获取access_token, 不强制刷新access_token
     */
    String getAccessToken();

    /**
     * <pre>
     * 获取access_token，本方法线程安全
     * 且在多线程同时刷新时只刷新一次，避免超出2000次/日的调用次数上限
     * 另：本service的所有方法都会在access_token过期是调用此方法
     * 程序员在非必要情况下尽量不要主动调用此方法
     * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=获取access_token
     */
    String getAccessToken(boolean forceRefresh);

    UserService getUserService();

    void setUserService(UserService userService);
}
