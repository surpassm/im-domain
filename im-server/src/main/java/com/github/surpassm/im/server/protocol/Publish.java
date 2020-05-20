package com.github.surpassm.im.server.protocol;


import com.github.surpassm.im.server.pojo.SessionStore;
import com.github.surpassm.im.server.service.IRetainMessageStoreService;
import com.github.surpassm.im.server.service.ISessionStoreService;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author mc
 * Create date 2020/4/1 17:43
 * Version 1.0
 * Description
 */

public class Publish {
    private static final Logger LOGGER = LoggerFactory.getLogger(Publish.class);

    private ISessionStoreService sessionStoreService;

    private IRetainMessageStoreService retainMessageStoreService;

    public Publish(ISessionStoreService sessionStoreService, IRetainMessageStoreService retainMessageStoreService) {
        this.sessionStoreService = sessionStoreService;
        this.retainMessageStoreService = retainMessageStoreService;
    }

    /**
     * 给指定在线成员发送文本消息
     *
     * @param username 成员唯一标识
     * @param msg      msg
     */
    public void processPublish(String username, String msg) {
        if (sessionStoreService.containsKey(username)) {
            SessionStore sessionStore = sessionStoreService.get(username);
            Channel storeChannel = sessionStore.getChannel();
            TextWebSocketFrame tws = new TextWebSocketFrame(msg);
            storeChannel.writeAndFlush(tws);
        } else {
            LOGGER.info("用户不在线，无法发送");
        }
    }

    /**
     * 给在线所有人员发送消息
     *
     * @param msg msg
     */
    public void processPublish(String msg) {
        Map<String, SessionStore> all = sessionStoreService.getAll();
        all.forEach((k, y) -> y.getChannel().writeAndFlush(new TextWebSocketFrame(msg)));
    }
}
