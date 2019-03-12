package com.dixn.netty;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class NettyApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(NettyApplication.class).web(WebApplicationType.NONE).run(args);
    }
}
