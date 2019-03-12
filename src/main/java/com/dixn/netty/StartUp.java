package com.dixn.netty;

import com.dixn.netty.LengthFieldBasedFrameDecoder.CustomServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Description：start up
 * @projectName：dxboot
 * @packageName： com.dixn.dxboot
 * @ClassName：StartUp
 * @author：zhaoxu
 * @createTime：2018/11/19 18:30
 * @upate：zhaoxu
 * @updateDate：2018/11/19 18:30
 * @remark：
 * @version v1.0
 */
@Component
@Order(value = 1)
@Slf4j
public class StartUp implements ApplicationRunner {

    @Value("${tcp.port}")
    String tcpPort;

	@Override
	public void run(ApplicationArguments var1) throws Exception {
	    log.info("netty server start......... ");
        startTcpServer();
	}

    public void startTcpServer() throws Exception {
        new CustomServer(Integer.parseInt(tcpPort)).start();
    }

}
