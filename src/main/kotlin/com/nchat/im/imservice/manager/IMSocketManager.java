package com.nchat.im.imservice.manager;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

public class IMSocketManager {
    @NotNull
    public static IMSocketManager instance() {
        return new IMSocketManager() ;
    }

    public void onMsgServerConnected() {

    }

    public void packetDispatch(@NotNull ByteBuf m) {

    }
}
