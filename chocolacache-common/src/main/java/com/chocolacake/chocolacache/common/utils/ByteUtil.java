package com.chocolacake.chocolacache.common.utils;

import com.chocolacake.chocolacache.common.entity.Command;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class ByteUtil {

    public static byte[] commandToByte(Command command) {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        byteOutputStream.write(command.getCommandVersion());
        byteOutputStream.write(command.getCommandTypeCode());
        byteOutputStream.write(command.getHeaderLength());
        byteOutputStream.write(command.getCommandHeader());
        byteOutputStream.write(command.getBodyLength());
        byteOutputStream.write(command.getCommandBody());
        return byteOutputStream.getBytes();
    }
}
