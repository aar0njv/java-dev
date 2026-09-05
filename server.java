/* 
- Inheritance: extends allows a child to inherit from parent class.
- super() calls the parent class constructors. it must be the first statement inside the child constructor.
*/

import java.util.*;

public class main {
    public static void main(String[] args) {
        WebServer webNode = new WebServer("web-01", "192.168.56.103", 8080);
        DatabaseServer dbNode = new DatabaseServer("db-01", "192.168.56.104", "PostgreSQL");
        
        webNode.startServer();
        dbNode.startServer();

        webNode.serveTraffic();
        dbNode.runQuery("SELECT * from payments;");
    }
}

public class CloudServer {
    protected String serverId;
    protected String ipAddr;
    private boolean isRunning;

    public CloudServer(String serverId, String ipAddr) {
        this.serverId = serverId;
        this.ipAddr = ipAddr;
        this.isRunning = false;
    }

    public void displayServer() {
        System.out.println("Server: " + serverId + "\n ip address: " + ipAddr + "\n Running: " + isRunning);
    }

    public void startServer() {
        this.isRunning = true;
    }

    public void stopServer() {
        this.isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}

public class WebServer extends CloudServer {
    private int httpPort;

    public WebServer(String serverId, String ipAddr, int httpPort) {
        // call the parent constructor using super() as the FIRST line
        super(serverId, ipAddr);
        this.httpPort = httpPort;
    }

    public void serveTraffic() {
        if (isRunning()) {
            System.out.println("WebServer " + serverId + " listening for HTTP requests on port " + httpPort);
        } else {
            System.out.println("Cannot serve traffic: Server " + serverId + " is offline.");
        }
    }
}

public class DatabaseServer extends CloudServer {
    private String dbEngine;

    public DatabaseServer(String serverId, String ipAddr, String dbEngine) {
        super(serverId, ipAddr);
        this.dbEngine = dbEngine;
    }

    public void runQuery(String sql) {
        System.out.println("Executing SQL query on " + dbEngine + " database (" + serverId + "): " + sql);
    }
}
