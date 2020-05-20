package com.github.surpassm.im.server.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.surpassm.im.server.protocol.ProtocolProcess;
import com.github.surpassm.im.server.service.ISendMsgServer;
import com.github.surpassm.im.server.util.JsonUtils;
import com.github.surpassm.im.server.vo.req.SendMsgReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author mc
 * Create date 2020/4/2 17:57
 * Version 1.0
 * Description
 */
@Slf4j
@Service
public class SendMsgServer implements ISendMsgServer {

    @Resource
    private ProtocolProcess protocolProcess;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void sendMsg(SendMsgReqVO sendMsgReqVO) {
        protocolProcess.publish().processPublish(JsonUtils.objectToJson(sendMsgReqVO.getMsg(), objectMapper));
    }

    @Override
    public void sendPersonMsg(SendMsgReqVO sendMsgReqVO) {
        protocolProcess.publish().processPublish(sendMsgReqVO.getUsername(), JsonUtils.objectToJson(sendMsgReqVO.getMsg(), objectMapper));
    }
}
