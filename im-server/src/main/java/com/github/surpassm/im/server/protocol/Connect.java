

package com.github.surpassm.im.server.protocol;

import com.github.surpassm.im.server.pojo.SessionStore;
import com.github.surpassm.im.server.service.IAuthService;
import com.github.surpassm.im.server.service.ISessionStoreService;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mc
 * Create date 2020/4/1 17:43
 * Version 1.0
 * Description 连接处理
 */
public class Connect {

	private static final Logger LOGGER = LoggerFactory.getLogger(Connect.class);

	private ISessionStoreService sessionStoreService;

	private IAuthService authService;


	public Connect(ISessionStoreService sessionStoreService, IAuthService authService) {
		this.sessionStoreService = sessionStoreService;
		this.authService = authService;
	}

	public void processConnect(String userName ,Channel channel) {
		// 如果会话中已存储这个新连接的clientId, 就关闭之前该clientId的连接
		if (sessionStoreService.containsKey(userName)) {
			SessionStore sessionStore = sessionStoreService.get(userName);
			Channel previous = sessionStore.getChannel();
			boolean cleanSession = sessionStore.isCleanSession();
			if (cleanSession) {
				sessionStoreService.remove(userName);
			}
			previous.close();
		}
		SessionStore sessionStore = new SessionStore(userName, channel, true, null);
		// 至此存储会话信息及返回接受客户端连接
		sessionStoreService.put(userName, sessionStore);
	}

}
