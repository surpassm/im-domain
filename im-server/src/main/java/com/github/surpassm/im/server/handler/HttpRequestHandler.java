package com.github.surpassm.im.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.net.URLDecoder;

/**
 * @author mc
 * Create date 2020/4/2 17:05
 * Version 1.0
 * Description  初始http
 */
@Slf4j
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static AttributeKey<String> key = AttributeKey.valueOf("userName");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        String url = request.uri();
        if(-1 != url.indexOf("/ws")) {
            String[] temp = url.split(";");
            String name = URLDecoder.decode(temp[1], "UTF-8");
            ctx.channel().attr(key).set(name);
            request.setUri("/ws");
            // 传递到下一个handler：升级握手
            ctx.fireChannelRead(request.retain());
        } else {
            log.info("not socket!");
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
