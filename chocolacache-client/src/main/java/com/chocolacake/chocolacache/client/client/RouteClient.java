package com.chocolacake.chocolacache.client.client;

class RouteClient {
    private BootStrapClient bootStrapClient;

    public RouteClient(BootStrapClient bootStrapClient) {
        this.bootStrapClient = bootStrapClient;
    }

    /**
     * Get target server address
     *
     * @param key
     * @return
     */
    public String getServerByKey(String key) {
        return "localhost:10086";
    }

}
