package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LoadBalancerServer {

    private LoadBalancer loadBalancer;
    private int loadBalancerPort;

    public LoadBalancerServer(LoadBalancer loadBalancer, int loadBalancerPort) {
        this.loadBalancer = loadBalancer;
        this.loadBalancerPort = loadBalancerPort;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(loadBalancerPort)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket, loadBalancer)).start();
            }
        }
    }
}
