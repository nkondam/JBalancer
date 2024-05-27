package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ServerTest {

    private  Server server;

    @Before
    void setUp() {
        server = new Server("localhost", 8081);
    }

    @Test
    void testInitialConnection() {
        assertEquals(0, server.getCurrentConnections());
    }

    @Test
    public void testIncrementConnections() {
        server.incrementCurrentConnections();
        assertEquals(1, server.getCurrentConnections());
    }

    @Test
    public void testDecrementConnections() {
        server.incrementCurrentConnections();
        server.decrementCurrentConnections();
        assertEquals(0, server.getCurrentConnections());
    }

    @Test
    public void testHost() {
        assertEquals("localhost", server.getHost());
    }

    @Test
    public void testPort() {
        assertEquals(8081, server.getPort());
    }
}
