package com.github.surpassm.im.server.controller;

import com.github.surpassm.im.server.common.Result;
import com.github.surpassm.im.server.service.ISendMsgServer;
import com.github.surpassm.im.server.vo.req.SendMsgReqVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author Administrator
 */
@CrossOrigin
@RestController
@RequestMapping("/notice/")
public class IndexController {

    @Resource
    private ISendMsgServer sendMsgServer;

    @PostMapping("v1/sendPersonMsg")
    @ApiOperation(value = "给指定的人员发送消息")
    public Object sendPersonMsg(@RequestBody SendMsgReqVO sendMsgReqVO) {
        sendMsgServer.sendPersonMsg(sendMsgReqVO);
        return Result.ok();
    }

    @PostMapping("v1/sendAllMsg")
    @ApiOperation(value = "给所有人员发送消息")
    public Object sendAllMsg(@RequestBody SendMsgReqVO sendMsgReqVO) {
        sendMsgServer.sendMsg(sendMsgReqVO);
        return Result.ok();
    }
}
