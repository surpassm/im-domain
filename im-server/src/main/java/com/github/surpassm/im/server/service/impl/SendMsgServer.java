package com.github.surpassm.im.server.service.impl;

import com.github.surpassm.im.server.service.ISendMsgServer;
import com.github.surpassm.im.server.vo.req.SendMsgReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author mc
 * Create date 2020/4/2 17:57
 * Version 1.0
 * Description
 */
@Slf4j
@Service
public class SendMsgServer implements ISendMsgServer {


    @Override
    public void sendMsg(SendMsgReqVO sendMsgReqVO) {
        log.info("向服务端发送消息");
    }
}
