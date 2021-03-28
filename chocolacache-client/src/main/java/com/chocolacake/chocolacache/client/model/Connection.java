package com.chocolacake.chocolacache.client.model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Connection {
    private String host;
    private int port;

    public Connection(String address) throws MalformedURLException {
        String[] split = address.split(":");
        this.host = split[0];
        this.port = Integer.parseInt(split[1]);
    }

    public String sendRequestSync(byte[] bytes) throws IOException {
        try (Socket socket = new Socket(host, port)) {
            socket.getOutputStream().write(bytes);
            socket.getOutputStream().flush();

            // todo:
            BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
            StringBuilder sb = new StringBuilder();
            byte[] buffer = new byte[1024];
            int read = bufferedInputStream.read(buffer, 0, 1024);
            sb.append(new String(buffer, StandardCharsets.UTF_8));
            return sb.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
