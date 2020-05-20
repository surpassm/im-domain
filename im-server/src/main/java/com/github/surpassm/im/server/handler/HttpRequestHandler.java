package com.github.surpassm.im.server.handler;

import com.github.surpassm.im.server.common.Overall;
import com.github.surpassm.im.server.common.ResultCode;
import com.github.surpassm.im.server.exception.CustomException;
import com.github.surpassm.im.server.protocol.ProtocolProcess;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;

import java.net.URLDecoder;

/**
 * @author mc
 * Create date 2020/4/2 17:05
 * Version 1.0
 * Description  初始http
 */
@Slf4j
@ChannelHandler.Sharable
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private ProtocolProcess protocolProcess;

    public HttpRequestHandler(ProtocolProcess protocolProcess) {
        this.protocolProcess = protocolProcess;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        String url = request.uri();
        if(url.contains("/ws")) {
            if (!url.contains(";")){
                ctx.close();
                throw new CustomException(ResultCode.INTERFACE_ADDRESS_INVALID.getCode(),ResultCode.INTERFACE_ADDRESS_INVALID.getMsg());
            }
            String[] temp = url.split(";");
            String name = URLDecoder.decode(temp[1], "UTF-8");
            ctx.channel().attr(Overall.key).set(name);
            request.setUri("/ws");
            // 传递到下一个handler：升级握手
            ctx.fireChannelRead(request.retain());
            //获取客户端所对应的channel，添加到一个管理的容器中即可,此处采用内存管理存储，备份物理磁盘，单机压力在100W
            protocolProcess.connect().processConnect(name,ctx.channel());
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
