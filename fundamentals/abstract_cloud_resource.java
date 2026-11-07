package fundamentals;
// Abstract class and upcasting

import java.util.*;

public class main {
    public static void main(String[] args) {

        // upcasting (referring to a child class obj through parent class)
        // uniform collection ( all in the same list (CloudRes) allowing to group and process them together)
        CloudResource bucket = new StorageBucket("s3-logs-bucket", "us-east-1", 500.0, 0.023);
        CloudResource instance = new Instance("ec2-web-node", "us-west-2", 720, 0.05);

        double bucket_cost = bucket.calculateMonthlyCost();
        System.out.println("Bucket Cost: " + bucket_cost);

        double instance_cost = instance.calculateMonthlyCost();
        System.out.println("Instance Cost: " + instance_cost);

        double total = bucket_cost + instance_cost;
        System.out.println("Total Cost: " + total);
    }
}


public abstract class CloudResource {
    private String resourceId;
    private String region;

    public CloudResource(String resourceId, String region) {
        this.resourceId = resourceId;
        this.region = region;
    }

    public void printMetadata() {
        System.out.println("Resource ID: " + resourceId + "\n Region: " + region + "\n");
    }

    public String getResourceId() {
        return resourceId;
    }

    public abstract double calculateMonthlyCost();
}

public class StorageBucket extends CloudResource {
    private double size;
    private double rate;

    public StorageBucket(String resourceId, String region, double size, double rate) {
        super(resourceId, region);
        this.size = size;
        this.rate = rate;
    }

    @Override
    public double calculateMonthlyCost() {
        return size * rate;
    }   
}

public class Instance extends CloudResource {
    private int active;
    private double rate;

    public Instance(String resourceId, String region, int active, double rate) {
        super(resourceId, region);
        this.active = active;
        this.rate = rate; 
    }

    @Override
    public double calculateMonthlyCost() {
        return active * rate;
    }
}
