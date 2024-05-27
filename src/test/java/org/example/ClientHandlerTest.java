package org.example;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.mockito.Mockito.*;

class ClientHandlerTest {

    private Socket clientSocket;
    private Socket serverSocket;
    private LoadBalancer loadBalancer;
    private Server backendServer;
    private ClientHandler clientHandler;

    @Before
    public void setUp() throws IOException {
        clientSocket = mock(Socket.class);
        serverSocket = mock(Socket.class);
        backendServer = new Server("localhost", 8081);
        loadBalancer = mock(LoadBalancer.class);

        when(loadBalancer.getNextServer()).thenReturn(backendServer);

        clientHandler = new ClientHandler(clientSocket, loadBalancer);

        // Mock input and output streams
        InputStream clientInputStream = new ByteArrayInputStream("client data".getBytes());
        OutputStream clientOutputStream = new ByteArrayOutputStream();
        InputStream serverInputStream = new ByteArrayInputStream("server response".getBytes());
        OutputStream serverOutputStream = new ByteArrayOutputStream();

        when(clientSocket.getInputStream()).thenReturn(clientInputStream);
        when(clientSocket.getOutputStream()).thenReturn(clientOutputStream);
        when(serverSocket.getInputStream()).thenReturn(serverInputStream);
        when(serverSocket.getOutputStream()).thenReturn(serverOutputStream);
    }

    @Test
    public void testRun() throws IOException {
        new Thread(clientHandler).start();

        // Add a short delay to ensure the handler has processed
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify the interactions with the backend server
        verify(loadBalancer).getNextServer();
    }
}