package com.chocolacake.chocolacache.common.utils;

import com.chocolacake.chocolacache.common.entity.Command;

import java.nio.ByteBuffer;

public class ByteUtil {

    private static int VERSION_LEN = 4;
    private static int TYPE_LEN = 4;
    private static int HEADER_LEN = 4;
    private static int BODY_LEN = 4;

    public static byte[] commandToByte(Command command) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(VERSION_LEN + TYPE_LEN + HEADER_LEN +
                command.getHeaderLength() + BODY_LEN + command.getBodyLength());
        byteBuffer.putInt(command.getCommandVersion());
        byteBuffer.putInt(command.getCommandTypeCode());
        byteBuffer.putInt(command.getHeaderLength());
        byteBuffer.put(command.getCommandHeader());
        byteBuffer.putInt(command.getBodyLength());
        byteBuffer.put(command.getCommandBody());
        return byteBuffer.array();
    }


    private static byte[] intToByte(int num) {
        return ByteBuffer.allocate(4).putInt(num).array();
    }
}
