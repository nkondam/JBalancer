package org.example;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;


class LoadBalancerServerTest {

    @Test
    public void testServerStart() {
        Server server1 = new Server("localhost", 8081);
        Server server2 = new Server("localhost", 8082);
        Server server3 = new Server("localhost", 8083);

        LoadBalancer loadBalancer = new LoadBalancer(Arrays.asList(server1, server2, server3));
        LoadBalancerServer loadBalancerServer = new LoadBalancerServer(loadBalancer, 8080);

        new Thread(() -> {
            try {
                loadBalancerServer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Add a short delay to ensure the server has started
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}