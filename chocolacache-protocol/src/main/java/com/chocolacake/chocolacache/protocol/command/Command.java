package com.chocolacake.chocolacache.protocol.command;

import java.io.Serializable;

/**
 * The cache command , Version commandHeader commandBody
 */
public class Command implements Serializable {

    public static final byte VERSION = 0;

    private int commandVersion;
    private int commandTypeCode;
    private byte[] commandHeader;
    private byte[] commandBody;


    public int getCommandVersion() {
        return commandVersion;
    }

    public int getCommandTypeCode() {
        return commandTypeCode;
    }

    public byte[] getCommandHeader() {
        return commandHeader;
    }

    public byte[] getCommandBody() {
        return commandBody;
    }

}
