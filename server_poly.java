/* Method Overloading and Method Overriding */

import java.util.*;

public class main {
    public static void main(String[] args) {
        CloudServer cloudNode = new CloudServer("cloud-01", "192.168.56.102");
        WebServer webNode = new WebServer("web-01", "192.168.56.103");

        cloudNode.startServer();
        System.out.println();
        webNode.startServer();
    }
}

public class CloudServer {
    protected String serverId;
    protected String ipAddr;
    protected boolean isRunning;

    public CloudServer(String serverId, String ipAddr) {
        this.serverId = serverId;
        this.ipAddr = ipAddr;
        this.isRunning = false;
    }
    
    public void startServer() {
        this.isRunning = true;
        System.out.println("Starting cloud server " + serverId + "\n Address: " + ipAddr);
        
    }

    public void stopServer() {
        this.isRunning = false;
    }

}

public class WebServer extends CloudServer {

    public WebServer(String serverId, String ipAddr) {
        super(serverId, ipAddr);
    }
    
    // method overriding (redifining parent behavior)
    @Override
    public void startServer() {
        this.isRunning = true;
        System.out.println("Starting NGINX web server (" + serverId + ") on port 8080...");
    }

    // method overloading (same method name, different parameters = different behavior)
    public void startServer(int timeoutSeconds) {
        System.out.println("Starting server with timeout of " + timeoutSeconds + "s");
    }
}
