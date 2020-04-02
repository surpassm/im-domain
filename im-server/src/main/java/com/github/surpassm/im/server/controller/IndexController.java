package com.github.surpassm.im.server.controller;

import com.github.surpassm.im.server.service.ISendMsgServer;
import com.github.surpassm.im.server.vo.req.SendMsgReqVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Administrator
 */
@CrossOrigin
@RestController
@RequestMapping("/")
public class IndexController {

    @Resource
    private ISendMsgServer sendMsgServer;

    /**
     * 向服务端发消息
     */
    @PostMapping(value = "sendMsg")
    public Object sendMsg(@RequestBody SendMsgReqVO sendMsgReqVO){
        sendMsgServer.sendMsg(sendMsgReqVO) ;
        return "";
    }

}
