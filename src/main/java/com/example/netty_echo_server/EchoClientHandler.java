package com.example.netty_echo_server;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ByteBuf message = Unpooled.copiedBuffer("Hello, server!", io.netty.util.CharsetUtil.US_ASCII);
        ctx.writeAndFlush(message); // send the message to the server
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Client received: " + in.toString(io.netty.util.CharsetUtil.US_ASCII));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close(); // close the connection when an exception is raised
    }
}

