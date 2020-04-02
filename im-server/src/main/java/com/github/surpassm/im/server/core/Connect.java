

package com.github.surpassm.im.server.core;

import cn.hutool.core.util.StrUtil;
import com.github.surpassm.im.server.pojo.SessionStore;
import com.github.surpassm.im.server.service.IAuthService;
import com.github.surpassm.im.server.service.ISessionStoreService;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.mqtt.*;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 连接处理
 * @author Administrator
 */
public class Connect {

	private static final Logger LOGGER = LoggerFactory.getLogger(Connect.class);

	private ISessionStoreService sessionStoreService;

	private IAuthService authService;


	public Connect(ISessionStoreService sessionStoreService, IAuthService authService) {
		this.sessionStoreService = sessionStoreService;
		this.authService = authService;
	}

	public void processConnect(Channel channel, WebSocketFrame msg) {
	}

}
