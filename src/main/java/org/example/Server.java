package org.example;

public class Server {
    private String host;
    private int port;
    private int currentConnections;

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
        this.currentConnections = 0;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getCurrentConnections() {
        return currentConnections;
    }

    public void incrementCurrentConnections() {
        currentConnections++;
    }

    public void decrementCurrentConnections() {
        currentConnections--;
    }

}
