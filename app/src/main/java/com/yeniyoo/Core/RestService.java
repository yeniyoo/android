package com.yeniyoo.Core;

import com.yeniyoo.Core.Service.UserService;

/**
 * Created by YJLaptop on 2016-07-16.
 */
public class RestService {
    UserService userService = AppController.getInstance().getRetrofit().create(UserService.class);

    public UserService getUserService() {
        return userService;
    }
}
