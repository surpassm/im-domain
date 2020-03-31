package com.github.surpassm.im.server.server;

import com.github.surpassm.im.server.config.ImConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author mc
 * Create date 2020/1/27 12:06
 * Version 1.0
 * Description
 * 整体服务启动初始类
 */
@Slf4j
@Service
public class ImServer {
	private final EventLoopGroup bossGroup;
	private final EventLoopGroup workerGroup;
	private final EventExecutorGroup businessGroup;

	@Resource
	private ImConfig.ImServerConfig serverConfig;

	@Resource
	private ImServerChannelInit imServerChannelInit;

	private Channel channel;

	public ImServer(@Qualifier("businessGroup") EventExecutorGroup businessGroup,
					@Qualifier("workerGroup") EventLoopGroup workerGroup,
					@Qualifier("bossGroup") EventLoopGroup bossGroup) {
		this.businessGroup = businessGroup;
		this.workerGroup = workerGroup;
		this.bossGroup = bossGroup;
	}

	@PostConstruct
	public void start() throws InterruptedException {
		mqttServer();
	}

	/**
	 * 销毁资源
	 */
	@PreDestroy
	public void stop() {
		bossGroup.shutdownGracefully().syncUninterruptibly();
		workerGroup.shutdownGracefully().syncUninterruptibly();
		businessGroup.shutdownGracefully().syncUninterruptibly();
		channel.closeFuture().syncUninterruptibly();
		channel = null;
		log.info("TCP服务关闭成功");
	}

	/**
	 * MQTT服务配置
	 */
	private void mqttServer() throws InterruptedException {
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(bossGroup, workerGroup)
				.channel(serverConfig.isEpoll()? EpollServerSocketChannel.class :NioServerSocketChannel.class)
				// handler在初始化时就会执行
				.handler(new LoggingHandler(LogLevel.INFO))
				// childHandler会在客户端成功connect后才执行
				.childHandler(imServerChannelInit)
				//服务端可连接队列数,对应TCP/IP协议listen函数中SO_BACKLOG参数
				.option(ChannelOption.SO_BACKLOG, serverConfig.getSoBackLog())
				//设置发送缓冲大小
				.option(ChannelOption.SO_SNDBUF, serverConfig.getSoSendBuf())
				//设置接收缓冲大小
				.option(ChannelOption.SO_RCVBUF, serverConfig.getSoReceiveBuf())
				//保持连接 是否开启心跳保活机制, 默认开启
				.childOption(ChannelOption.SO_KEEPALIVE, serverConfig.isSoKeepAlive())
				;
		//内存泄漏检测 开发推荐PARANOID 线上SIMPLE
		ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.SIMPLE);
		ChannelFuture future = serverBootstrap.bind(serverConfig.getTcpPort()).sync();
		if (future.isSuccess()) {
			log.info("TCP服务启动完毕,port={}", serverConfig.getTcpPort());
			channel = future.channel();
		}
	}
}
