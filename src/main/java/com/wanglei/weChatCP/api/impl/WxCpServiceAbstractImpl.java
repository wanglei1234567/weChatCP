package com.wanglei.weChatCP.api.impl;

import com.alibaba.fastjson.JSON;
import com.wanglei.weChatCP.config.WxCpConfigStorage;
import com.wanglei.weChatCP.api.UserService;
import com.wanglei.weChatCP.api.WxCpService;
import com.wanglei.weChatCP.util.HttpClient;
import com.wanglei.weChatCP.bean.AccessToekn;

public abstract class WxCpServiceAbstractImpl implements WxCpService {

    protected UserService userService;
    protected WxCpConfigStorage configStorage;
    //刷新token全局锁
    protected final Object globalAccessTokenRefreshLock = new Object();

    public String getAccessToken() {
        long nowTime = System.currentTimeMillis();
        long expiresTime = configStorage.getExpiresTime();
        if(configStorage.getExpiresTime() < nowTime){
            getAccessToken(false);
        }
        return configStorage.getAccessToken();
    }

    public String getAccessToken(boolean forceRefresh) {
        synchronized (this.globalAccessTokenRefreshLock) {
            String token = HttpClient.doGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + configStorage.getCorpId() + "&corpsecret=" + configStorage.getSecret());
            AccessToekn accessToekn = JSON.parseObject(token, AccessToekn.class);
            System.out.println(accessToekn.toString());
            configStorage.setExpiresTime(System.currentTimeMillis()+6500*1000);
            configStorage.setAccessToken(accessToekn.getAccessToken());
            configStorage.setCount(configStorage.getCount()+1);
            return configStorage.getAccessToken();
        }
    }

    public void setWxCpConfigStorage(WxCpConfigStorage wxConfigProvider) {
        this.configStorage = wxConfigProvider;
    }
    public UserService getUserService() {
        return userService;
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
