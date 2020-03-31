package com.github.surpassm.im.server.server;

import com.github.surpassm.im.server.config.ImConfig;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author mc
 * Create date 2019/11/10 10:08
 * Version 1.0
 * Description
 */
@Component
public class ImServerChannelInit extends ChannelInitializer<SocketChannel> {

	@Resource
	private ImConfig.ImServerConfig serverConfig;

	@Override
	protected void initChannel(SocketChannel socketChannel) throws Exception {
		ChannelPipeline pipeline = socketChannel.pipeline();
		//Netty提供的心跳检测
		pipeline.addLast(new IdleStateHandler(serverConfig.getReaderIdleTimeSeconds(),serverConfig.getWriterIdleTimeSeconds(),serverConfig.getKeepAlive(), TimeUnit.SECONDS))
				//代理服务
//		 		.addLast("broker", new BrokerHandler(protocolProcess))
		;

	}
}
