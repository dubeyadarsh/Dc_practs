import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoundRobinLoadBalancer {
    
    private List<String> servers;
    private Iterator<String> serverIterator;
    
    public RoundRobinLoadBalancer(List<String> servers) {
        this.servers = new ArrayList<>(servers);
        this.serverIterator = cycle(servers);
    }
    
    private static <T> Iterator<T> cycle(List<T> list) {
        return new Iterator<T>() {
            private Iterator<T> iterator = list.iterator();

            public boolean hasNext() {
                return list.size() > 0;
            }

            public T next() {
                if (!iterator.hasNext()) {
                    iterator = list.iterator();
                }
                return iterator.next();
            }
        };
    }
    
    public String getNextServer() {
        return serverIterator.next();
    }
    

    public static void main(String[] args) {
        List<String> servers = new ArrayList<>();
        servers.add("server1");
        servers.add("server2");
        servers.add("server3");
        
        RoundRobinLoadBalancer balancer = new RoundRobinLoadBalancer(servers);
        
        for (int i = 1; i <= 10; i++) {
            String server = balancer.getNextServer();
            System.out.printf("Request %d routed to %s\n", i, server);
        }
    }
}

//OUTPUT 
//Request 1 routed to server1
//Request 2 routed to server2
//Request 3 routed to server3
//Request 4 routed to server1
//Request 5 routed to server2
//Request 6 routed to server3
//Request 7 routed to server1
//Request 8 routed to server2
//Request 9 routed to server3
//Request 10 routed to server1
