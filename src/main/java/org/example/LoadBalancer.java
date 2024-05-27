package org.example;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LoadBalancer {

    private List<Server> servers;
    private AtomicInteger currentIndex;

    public LoadBalancer(List<Server> servers) {
        this.servers = servers;
        this.currentIndex = new AtomicInteger(0);
    }

    public Server getNextServer() {
        int index = currentIndex.getAndIncrement() % servers.size();
        return servers.get(index);
    }
}
