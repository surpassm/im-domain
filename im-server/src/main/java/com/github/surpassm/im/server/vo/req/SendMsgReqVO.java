package com.github.surpassm.im.server.vo.req;


import io.swagger.annotations.ApiModelProperty;

/**
 * @author Administrator
 */
public class SendMsgReqVO {
    @ApiModelProperty(value = "消息内容")
    private Object msg ;

    @ApiModelProperty(value = "用户唯一标识")
    private String username ;

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
