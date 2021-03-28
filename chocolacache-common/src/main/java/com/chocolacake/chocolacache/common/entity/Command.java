package com.chocolacake.chocolacache.common.entity;

import java.io.Serializable;

/**
 * The cache command , Version commandHeader commandBody
 */
public class Command implements Serializable {

    protected int commandVersion;
    protected int commandTypeCode;
    protected int headerLength;
    protected byte[] commandHeader;
    protected int bodyLength;
    protected byte[] commandBody;

    public int getCommandVersion() {
        return commandVersion;
    }

    public void setCommandVersion(int commandVersion) {
        this.commandVersion = commandVersion;
    }

    public int getCommandTypeCode() {
        return commandTypeCode;
    }

    public void setCommandTypeCode(int commandTypeCode) {
        this.commandTypeCode = commandTypeCode;
    }

    public int getHeaderLength() {
        return headerLength;
    }

    public void setHeaderLength(int headerLength) {
        this.headerLength = headerLength;
    }

    public byte[] getCommandHeader() {
        return commandHeader;
    }

    public void setCommandHeader(byte[] commandHeader) {
        this.commandHeader = commandHeader;
    }

    public int getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(int bodyLength) {
        this.bodyLength = bodyLength;
    }

    public byte[] getCommandBody() {
        return commandBody;
    }

    public void setCommandBody(byte[] commandBody) {
        this.commandBody = commandBody;
    }
}
