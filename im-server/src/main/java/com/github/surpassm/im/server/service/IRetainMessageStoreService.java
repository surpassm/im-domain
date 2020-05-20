

package com.github.surpassm.im.server.service;


import com.github.surpassm.im.server.pojo.RetainMessageStore;

import java.util.List;

/**
 * 消息存储服务接口
 */
public interface IRetainMessageStoreService {

    /**
     * 存储retain标志消息
     *
     * @param topic
     * @param retainMessageStore
     */
    void put(String topic, RetainMessageStore retainMessageStore);

    /**
     * 获取retain消息
     *
     * @param topic
     * @return
     */
    RetainMessageStore get(String topic);

    /**
     * 删除retain标志消息
     *
     * @param topic
     */
    void remove(String topic);

    /**
     * 判断指定topic的retain消息是否存在
     *
     * @param topic
     * @return
     */
    boolean containsKey(String topic);

    /**
     * 获取retain消息集合
     *
     * @param topicFilter
     * @return
     */
    List<RetainMessageStore> search(String topicFilter);

}
