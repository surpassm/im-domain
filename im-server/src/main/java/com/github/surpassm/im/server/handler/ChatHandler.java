package com.github.surpassm.im.server.handler;

import com.github.surpassm.im.server.core.ProtocolProcess;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @author mc
 * Create date 2020/4/1 17:50
 * Version 1.0
 * Description
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private ProtocolProcess protocolProcess;

    public ChatHandler(ProtocolProcess protocolProcess) {
        this.protocolProcess = protocolProcess;
    }

    /**
     * 当客户端连接服务端（或者是打开连接之后）
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //获取客户端所对应的channel，添加到一个管理的容器中即可
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame msg) throws Exception {
        //text()获取从客户端发送过来的字符串
        String content = msg.text();
    }


    /**
     * 客户端断开
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //实际上是多余的，只要handler被移除，client会自动的把对应的channel移除掉
        //每一而channel都会有一个长ID与短ID
        //一开始channel就有了，系统会自动分配一串很长的字符串作为唯一的ID，如果使用asLongText()获取的ID是唯一的，asShortText()会把当前ID进行精简，精简过后可能会有重复
        System.out.println("channel的长ID：" + ctx.channel().id().asLongText());
        System.out.println("channel的短ID：" + ctx.channel().id().asShortText());
    }

}
