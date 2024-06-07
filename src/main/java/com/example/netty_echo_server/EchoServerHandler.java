package com.example.netty_echo_server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Server received: " + in.toString(io.netty.util.CharsetUtil.US_ASCII));
        ctx.write(in.retain()); // write the message back to the sender and retain the reference
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush(); // flush the messages to the remote peer
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close(); // close the connection when an exception is raised
    }
}

