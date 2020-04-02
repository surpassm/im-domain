

package com.github.surpassm.im.server.service.impl;

import com.github.surpassm.im.server.pojo.SessionStore;
import com.github.surpassm.im.server.service.ISessionStoreService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 会话存储服务
 *
 * @author Administrator
 */
@Service
public class SessionStoreService implements ISessionStoreService {

    private Map<String, SessionStore> sessionCache = new ConcurrentHashMap<>();

    /**
     * 存储会话
     */
    @Override
    public void put(String clientId, SessionStore sessionStore) {
        sessionCache.put(clientId, sessionStore);
    }

    /**
     * 获取会话
     */
    @Override
    public SessionStore get(String clientId) {
        return sessionCache.get(clientId);
    }

    /**
     * clientId的会话是否存在
     */
    @Override
    public boolean containsKey(String clientId) {
        return sessionCache.containsKey(clientId);
    }

    /**
     * 删除会话
     */
    @Override
    public void remove(String clientId) {
        sessionCache.remove(clientId);
    }
}
