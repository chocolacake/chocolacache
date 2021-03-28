package com.chocolacake.chocolacache.client.client;


import com.chocolacake.chocolacache.client.config.ChocolacacheConfig;
import org.junit.Test;

public class ChocolacacheClientTest {

    @Test
    public void get() {
        String[] host = {"localhost:10086"};
        ChocolacacheConfig config = ChocolacacheConfig.builder().hosts(host).build();
        ChocolacacheClient bootStrapClient = new ChocolacacheClient(config);
        bootStrapClient.set("test1", "value1");
        String test1 = bootStrapClient.getString("test1");
        System.out.println(test1);
    }

    @Test
    public void set() {
    }
}