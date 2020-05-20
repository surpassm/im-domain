package com.github.surpassm.im.server.protocol;


import com.github.surpassm.im.server.pojo.SessionStore;
import com.github.surpassm.im.server.service.ISessionStoreService;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mc
 * Create date 2020/4/1 17:43
 * Version 1.0
 * Description 断开连接处理
 */
public class DisConnect {
    private static final Logger LOGGER = LoggerFactory.getLogger(DisConnect.class);

    private ISessionStoreService sessionStoreService;

    public DisConnect(ISessionStoreService sessionStoreService) {
        this.sessionStoreService = sessionStoreService;
    }

    public void processDisConnect(String username, Channel channel) {
        SessionStore sessionStore = sessionStoreService.get(username);
        if (sessionStore.isCleanSession()) {
            //TODO 这里可以根据连接时设定，是否清除扩展消息数据，目前0.0.1版本未涉及到业务
        }
        sessionStoreService.remove(username);
        channel.close();
    }
}
