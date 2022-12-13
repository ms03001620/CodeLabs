package com.nchat.im

import com.nchat.im.imservice.network.Netty4Socket
import io.netty.channel.ChannelFuture
import java.io.BufferedReader
import java.io.InputStreamReader

class TestMain {


}

fun main() {
    //MsgServerAddrsEntity{code=0, msg='null', priorIP='18.162.245.23', backupIP='18.162.245.23', port=8000, npayBackup='https://test.n-chat.com.ng/api.php/', npayPrior='https://test.n-chat.com.ng/api.php/'}
    //MsgServerAddrsEntity{code=0, msg='null', priorIP='18.159.134.195', backupIP='18.159.134.195', port=8000, npayBackup='https://c.n-chat.com.ng/api.php/', npayPrior='https://c.n-chat.com.ng/api.php/'}

/*    val ip = "18.162.245.23"
    val port = 8000*/

    val ip = System.getProperty("host", "127.0.0.1")
    val port = System.getProperty("port", "8992").toInt()

    val thread = Thread(Runnable {
        val socketThread = Netty4Socket(null)
        //socketThread.linkToServer(ip, port)
        socketThread.linkToServerText(ip, port)
       // input(socketThread)
    })

    thread.start()
    thread.join()


    println("----end------")
}

fun input(netty4Socket: Netty4Socket){
    var lastWriteFuture: ChannelFuture? = null;
    val inf = BufferedReader(InputStreamReader(System.`in`))
    while (true) {
        var line = inf.readLine()
        if (line == null) {
            break;
        }

        val ch = netty4Socket.getCh()

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