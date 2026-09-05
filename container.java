/* Encapsulation using private keyword and getter and setter methods */


import java.util.*;

public class main {
    public static void main(String[] args) {
        Container webApp = new Container("c101", "nginx:latest", 8080);

        webApp.displayContainer();
        webApp.setPort(9090);
        webApp.start();
        webApp.displayContainer();
        
    }
}

public class Container {
    private String containerId;
    private String image;
    private int port;
    private boolean isRunning;

    public Container(String containerId, String image, int port) {
        this.containerId = containerId;
        this.image = image;
        setPort(port);
        this.isRunning = false;
    }

    public String getContainerId() {
        return containerId;
    }

    public void displayContainer() {
        System.out.println("Container " + this.getContainerId() + " running on port: " + this.getPort() + " Status: " + this.isRunning());
    }

    public String getImage() {
        return image;
    }

    public int getPort() {
        return port;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setPort(int port) {
        if (port >= 1024 && port <= 65535) {
            this.port = port;
        } else {
            System.err.println("Invalid port number.");
        }
    }

    public void start() {
        this.isRunning = true;
        System.out.println("Container " + containerId + " Running on port: " + port);
    }

    public void stop() {
        this.isRunning = false;
        System.out.println("Container " + containerId + " stopped.");
    }




    
}
