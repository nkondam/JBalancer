package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private LoadBalancer loadBalancer;

    public ClientHandler(Socket clientSocket, LoadBalancer loadBalancer) {
        this.clientSocket = clientSocket;
        this.loadBalancer = loadBalancer;
    }

    @Override
    public void run() {
        Server server = loadBalancer.getNextServer();

        try (Socket serverSocket = new Socket(server.getHost(), server.getPort())) {

            server.incrementCurrentConnections();

            relayData(clientSocket.getInputStream(), serverSocket.getOutputStream());
            relayData(serverSocket.getInputStream(), clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.decrementCurrentConnections();
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void relayData(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
    }

}
