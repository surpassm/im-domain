package com.github.surpassm.im.server.protocol;

import com.github.surpassm.im.server.service.IAuthService;
import com.github.surpassm.im.server.service.IRetainMessageStoreService;
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
    @Resource
    private IRetainMessageStoreService retainMessageStoreService;

    private Connect connect;

    private DisConnect disConnect;

    private Publish publish;

    public Connect connect() {
        if (connect == null) {
            connect = new Connect(sessionStoreService, authService);
        }
        return connect;
    }

    public DisConnect disConnect() {
        if (disConnect == null) {
            disConnect = new DisConnect(sessionStoreService);
        }
        return disConnect;
    }

    public Publish publish() {
        if (publish == null) {
            publish = new Publish(sessionStoreService, retainMessageStoreService);
        }
        return publish;
    }
}
