package com.nchat.im.imservice.network

import com.google.protobuf.GeneratedMessageLite
import com.nchat.im.config.SysConstant
import com.nchat.im.imservice.manager.TextClientInitializer
import com.nchat.im.protobuf.base.DataBuffer
import com.nchat.im.protobuf.base.Header
import com.nchat.im.utils.Logger
import io.netty.bootstrap.Bootstrap
import io.netty.buffer.ByteBuf
import io.netty.channel.*
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.example.securechat.SecureChatClient
import io.netty.example.securechat.SecureChatClientInitializer
import org.jboss.netty.channel.SimpleChannelHandler
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import java.util.concurrent.TimeUnit

class Netty4Socket(val handler: SimpleChannelHandler?) {
    private val logger = Logger.getLogger(Netty4Socket::class.java)
    private var workerGroup: EventLoopGroup? = null
    private var channelFuture: Channel? = null
    private var channelHandlerContext: ChannelHandlerContext? = null

    private var content: ByteBuf? = null

    fun linkToServer(host: String, port: Int) {
        workerGroup = NioEventLoopGroup()
        try {
            val bootstrap = Bootstrap() // (1)
            bootstrap.group(workerGroup) // (2)
            bootstrap.channel(NioSocketChannel::class.java) // (3)
            bootstrap.option(ChannelOption.TCP_NODELAY, true)
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true) // (4)
            //bootstrap.handler(BudChatClientInitializer())
            bootstrap.handler(TextClientInitializer())

            // Start the client.
            channelFuture = bootstrap.connect(host, port).sync().channel() // (5)

            // Wait until the connection is closed.
            channelFuture?.closeFuture()?.sync()
        } finally {
            workerGroup?.shutdownGracefully()
        }
    }

    fun linkToServerText(host: String, port: Int) {
        val group: EventLoopGroup = NioEventLoopGroup()
        try {
            val b = Bootstrap()
            b.group(group)
                .channel(NioSocketChannel::class.java)
                .handler(SecureChatClientInitializer())

            // Start the connection attempt.
            val ch = b.connect(host, port).sync().channel()
            input(ch)

        } finally {
            // The connection is closed automatically on shutdown.
            group.shutdownGracefully()
        }
    }

    private fun input(ch: Channel?) {
        var lastWriteFuture: ChannelFuture? = null;
        val inf = BufferedReader(InputStreamReader(System.`in`))
        while (true) {
            var line = inf.readLine()
            if (line == null) {
                break;
            }

            // Sends the received line to the server.
            lastWriteFuture = ch?.writeAndFlush(line + "\r\n");

            // If user typed the 'bye' command, wait until the server closes
            // the connection.
            if ("bye".equals(line.toLowerCase())) {
                ch?.closeFuture()?.sync();
                break;
            }
        }

        // Wait until all messages are flushed before closing the channel.
        lastWriteFuture?.sync()
    }

    fun isClose(): Boolean {
        return channelFuture?.isActive == true
    }

    fun close() {
        workerGroup?.shutdownGracefully(100, 100, TimeUnit.MILLISECONDS)
        //workerGroup?.shutdownGracefully()
    }

    fun getCh() = channelFuture

    fun sendRequest(requset: GeneratedMessageLite, header: Header): Boolean {
        val headerBuffer = header.encode()
        val bodyBuffer = DataBuffer()
        val bodySize = requset.serializedSize
        bodyBuffer.writeBytes(requset.toByteArray())


        val buffer = DataBuffer(SysConstant.PROTOCOL_HEADER_LENGTH + bodySize)
        buffer.writeDataBuffer(headerBuffer)
        buffer.writeDataBuffer(bodyBuffer)


        val d = buffer.array()

        content = channelHandlerContext?.alloc()?.directBuffer(d.size)?.writeZero(d.size);
        content?.writeBytes(d)


        channelFuture?.writeAndFlush(content)?.addListener(trafficGenerator)

/*        if (channelHandlerContext != null && channelHandlerContext?.isRemoved == false) {
            logger.d("sendRequest: size = ${d.size}")

            content = channelHandlerContext?.alloc()?.directBuffer(d.size)?.writeZero(d.size);
            content?.writeBytes(d)

            //channelHandlerContext?.writeAndFlush(content?.retainedDuplicate())?.addListener(trafficGenerator)
            channelHandlerContext?.channel()?.writeAndFlush(content?.retainedDuplicate())?.sync()
            channelHandlerContext?.channel()?.close()?.sync()
        }*/
        return true
    }

    private val trafficGenerator = ChannelFutureListener { future ->
        logger.d("writeAndFlush#trafficGenerator: ctx = ${future.isSuccess}")

        future.cause()?.printStackTrace()
    }

}