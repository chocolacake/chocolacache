package com.chocolacake.chocolacache.client.model;

import com.chocolacake.chocolacache.common.entity.Response;
import com.chocolacake.chocolacache.common.utils.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {
    private String host;
    private int port;

    public Connection(String address) {
        String[] split = address.split(":");
        this.host = split[0];
        this.port = Integer.parseInt(split[1]);
    }

    public Response sendRequestSync(byte[] bytes) throws IOException {
        Socket socket = new Socket(host, port);
        socket.getOutputStream().write(bytes);
        socket.getOutputStream().flush();

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        return JsonUtil.deserialize(dataInputStream.readUTF(), new TypeReference<Response>() {
        });
    }
}
