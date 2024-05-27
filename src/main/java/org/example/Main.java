package org.example;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Server server1 = new Server("localhost", 8081);
        Server server2 = new Server("localhost", 8082);
        Server server3 = new Server("localhost", 8083);
        Server server4 = new Server("localhost", 8084);

        LoadBalancer loadBalancer = new LoadBalancer(Arrays.asList(server1, server2, server3, server4));

        LoadBalancerServer loadBalancerServer = new LoadBalancerServer(loadBalancer, 8080);

        try {
            loadBalancerServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}