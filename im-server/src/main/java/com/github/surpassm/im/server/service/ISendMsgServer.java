package com.github.surpassm.im.server.service;

import com.github.surpassm.im.server.vo.req.SendMsgReqVO;

/**
 * @author mc
 * Create date 2020/4/2 17:56
 * Version 1.0
 * Description
 */
public interface ISendMsgServer {
    /**
     * 向服务端发送消息
     * @param sendMsgReqVO
     */
    void sendMsg(SendMsgReqVO sendMsgReqVO);
}
