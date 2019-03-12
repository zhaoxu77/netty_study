package com.dixn.netty.LengthFieldBased;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2019-03-06 12:54
 **/
@Data
@AllArgsConstructor
public class Message {

    //消息类型
    private byte type;

    //消息长度
    private int length;

    //消息体
    private String msgBody;
}
