package com.github.surpassm.im.server.core;

import com.github.surpassm.im.server.service.IAuthService;
import com.github.surpassm.im.server.service.ISessionStoreService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author mc
 * Create date 2020/4/1 17:43
 * Version 1.0
 * Description
 */
@Component
public class ProtocolProcess {

    @Resource
    private ISessionStoreService sessionStoreService;
    @Resource
    private IAuthService authService;

    private Connect connect;

    public Connect connect() {
        if (connect == null) {
            connect = new Connect(sessionStoreService, authService);
        }
        return connect;
    }
}
