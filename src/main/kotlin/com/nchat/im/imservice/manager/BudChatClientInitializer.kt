/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.nchat.im.imservice.manager

import com.nchat.im.imservice.network.Netty4Socket
import com.nchat.im.utils.Logger
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInitializer
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.socket.SocketChannel

class BudChatClientInitializer : ChannelInitializer<SocketChannel?>() {
    var channelHandlerContext: ChannelHandlerContext? = null

    public override fun initChannel(ch: SocketChannel?) {
        ch?.pipeline()?.addLast(io.netty.handler.codec.LengthFieldBasedFrameDecoder
            (400*1024, 0, 4, -4, 0))
        ch?.pipeline()?.addLast( object: SimpleChannelInboundHandler<Any>() {
            private val logger = Logger.getLogger(Netty4Socket::class.java)

            override fun channelActive(ctx: ChannelHandlerContext) {
                channelHandlerContext = ctx
                // content = ctx.alloc().directBuffer(256).writeZero(256);
                logger.d("channelActive() called with: ctx = $ctx")
                IMSocketManager.instance().onMsgServerConnected()
            }

            override fun channelInactive(ctx: ChannelHandlerContext) {
                logger.d("channelInactive() called with: ctx = $ctx")
                //content?.release();
                //IMSocketManager.instance().onMsgServerDisconn()
                //IMHeartBeatManager.instance().onMsgServerDisconn()
            }

            override fun channelRead0(ctx: ChannelHandlerContext, msg: Any) {
                logger.d("channelRead() called with: ctx = $ctx, msg = $msg")
                val m = msg as ByteBuf // (1)
                try {
                    IMSocketManager.instance().packetDispatch(m)
                    //ctx.close()
                } finally {
                    // m.release()
                }
            }

            override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
                logger.d("exceptionCaught() called with: ctx = $ctx, cause = $cause")
                cause.printStackTrace()
                ctx.close()
                //IMSocketManager.instance().onConnectMsgServerFail()
            }
        })
    }

}