package com.chocolacake.chocolacache.protocol.command;

import java.io.Serializable;

public class Command<H, B> implements Serializable {

    public static final byte VERSION = 0;



    public int getCommandType() {
        return 1;
    }

    public byte[] getCommandHeader() {

    }

    public byte[] getCommandBody() {

    }
}
