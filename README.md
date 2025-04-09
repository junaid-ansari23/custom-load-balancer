# Custom Load Balancer

This project implements a high-level round-robin style load balancer in Java. The load balancer distributes incoming requests evenly across a set of servers in a circular order.

## Features

- **Round-Robin Algorithm**: Ensures that requests are distributed evenly across all available servers.
- **Server Resource Management**: Each server maintains a list of resources (requests) assigned to it.
- **Persistence and Caching**: Server details and resources are stored in a `Map` for easy access and management.

## How It Works

1. A list of server IDs is provided to the `CustomLoadBalancer` class.
2. The `getNextServer()` method selects the next server in a round-robin fashion.
3. Incoming requests are routed to the selected server, and the request ID is added to the server's resource list.
4. The program simulates 10 incoming requests and prints the distribution of requests across the servers.

## Code Structure

- **CustomLoadBalancer**: The main class that implements the round-robin load balancing logic.
- **Server**: A nested class representing a server, which holds its ID and a list of assigned resources.

## Example Output

The program simulates 10 incoming requests and distributes them across three servers (`server-1`, `server-2`, `server-3`). Below is an example output:

```
Request 1 routed to: server-1
Request 2 routed to: server-2
Request 3 routed to: server-3
Request 4 routed to: server-1
Request 5 routed to: server-2
Request 6 routed to: server-3
Request 7 routed to: server-1
Request 8 routed to: server-2
Request 9 routed to: server-3
Request 10 routed to: server-1

Server-1 resources list: [1, 4, 7, 10]
Server-2 resources list: [2, 5, 8]
Server-3 resources list: [3, 6, 9]
```

## How to Run

1. Clone the repository or copy the code into your local environment.
2. Compile the Java program:
   ```bash
   javac -d . src/customds/CustomLoadBalancer.java
   ```
3. Run the program:
   ```bash
   java customds.CustomLoadBalancer
   ```

## Requirements

- Java 8 or higher.

## Future Enhancements

- Add dynamic server addition/removal functionality.
- Implement persistence using a database for server resources.
- Introduce weighted load balancing for uneven server capacities.
