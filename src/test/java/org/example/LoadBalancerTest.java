package org.example;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

class LoadBalancerTest {

    private LoadBalancer loadBalancer;
    private Server server1;
    private Server server2;
    private Server server3;

    @Before
    public void setUp() {
        server1 = new Server("localhost", 8081);
        server2 = new Server("localhost", 8082);
        server3 = new Server("localhost", 8083);
        List<Server> servers = Arrays.asList(server1, server2, server3);
        loadBalancer = new LoadBalancer(servers);
    }

    @Test
    public void testRoundRobin() {
        assertEquals(server1, loadBalancer.getNextServer());
        assertEquals(server2, loadBalancer.getNextServer());
        assertEquals(server3, loadBalancer.getNextServer());
        assertEquals(server1, loadBalancer.getNextServer());
    }

}