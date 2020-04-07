package com.github.surpassm.im.server.vo.req;


import lombok.Data;

/**
 * @author Administrator
 */
public class SendMsgReqVO {

    private String msg ;

    private Long userId ;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
