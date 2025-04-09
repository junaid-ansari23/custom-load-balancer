package lb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomLoadBalancer {
    private final List<String> servers;
    private final AtomicInteger index;

    public CustomLoadBalancer(List<String> servers) {
        this.servers = servers;
        this.index = new AtomicInteger(0);
    }

    public String getNextServer() {
        int currentIndex = index.getAndUpdate(i -> (i + 1) % servers.size());
        return servers.get(currentIndex);
    }

    public static void main(String[] args) {

        List<String> serverList = Arrays.asList("server-1", "server-2", "server-3");
        // List.Of

        CustomLoadBalancer lb = new CustomLoadBalancer(serverList);

        // this to be store in the DB for persistent & caching
        Map<String, Server> serverResource = new HashMap<>();
        Server server1 = new Server(new ArrayList<>(), "server-1");
        serverResource.put("server-1", server1);

        Server server2 = new Server(new ArrayList<>(), "server-1");
        serverResource.put("server-2", server2);

        Server server3 = new Server(new ArrayList<>(), "server-3");
        serverResource.put("server-3", server3);

        // Simulating 10 incoming requests
        for (int i = 1; i <= 10; i++) {
            String serverId = lb.getNextServer();
            Server server = serverResource.get(serverId);
            server.getResources().add(i);
            System.out.println("Request " + i + " routed to: " + serverId);
        }

        System.out.println("Server-1 resources list: " + server1.getResources());
        System.out.println("Server-2 resources list: " + server2.getResources());
        System.out.println("Server-3 resources list: " + server3.getResources());
    }

    static class Server {

        private List<Integer> resources;
        private String serverId;

        public Server(List<Integer> resources, String serverId) {
            this.resources = resources;
            this.serverId = serverId;
        }

        public List<Integer> getResources() {
            return resources;
        }

        public void setResources(List<Integer> resources) {
            this.resources = resources;
        }

        public String getServerId() {
            return serverId;
        }

        public void setServerId(String serverId) {
            this.serverId = serverId;
        }

    }
}
