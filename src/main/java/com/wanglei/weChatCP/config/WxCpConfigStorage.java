package com.wanglei.weChatCP.config;

//微信的参数的接口类
public interface WxCpConfigStorage {
    String getAccessToken();
    void setAccessToken(String token);
    long getExpiresTime();
    void setExpiresTime(long expiresTime);
    String getCorpId();
    String getSecret();
    //测试用
    long getCount();
    void setCount(long count);
}
