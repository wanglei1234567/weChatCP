package com.wanglei.weChatCP.api.impl;

import com.wanglei.weChatCP.api.UserService;
import com.wanglei.weChatCP.api.WxCpService;

public class UserServiceImpl implements UserService {
    private WxCpService mainService;

    public UserServiceImpl(WxCpService mainService) {
        this.mainService = mainService;
    }
    public String getName() {
        return mainService.getAccessToken()+"a";
    }
}
