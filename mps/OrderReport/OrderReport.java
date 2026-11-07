
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

record Order(String orderId, String merchantId, BigDecimal amount, String status) {}

public class OrderReport {
    public static void main(String args[]) {
        // Key-value collection, act as a lookup for merchant names
       Map<String, String> merchantDirectory = Map.of(
        "M-101", "ACME Corp",
        "M-102", "Stark Industries",
        "M-103", "Wayne Enterprises"
       );

       // List (Ordered Collection): Simulating batch of orders received
       List<Order> orders = List.of(
        new Order("ord-01", "M-101", new BigDecimal("250.75"), "Completed"),
        new Order("ord-02", "M-102", new BigDecimal("150.00"), "Pending"),
        new Order("ord-03", "M-103", new BigDecimal("300.50"), "Completed"),
        new Order("ord-04", "M-101", new BigDecimal("450.00"), "Failed"),
        new Order("ord-05", "M-103", new BigDecimal("95.50"), "Completed")
       );

       // Setting the threshold for high value orders
       BigDecimal threshold = new BigDecimal("100.00");

       // Streams pipeline: stream().filter().map().toList()
       List<String> highValueCompletedOrders = orders.stream()
        .filter(order -> order.status().equals("Completed") && order.amount().compareTo(threshold) > 0)
        .map(order -> {
            String merchantName = merchantDirectory.getOrDefault(order.merchantId(), "Unknown Merchant");
            return "Order ID: " + order.orderId() + ", Merchant: " + merchantName + ", Amount: $" + order.amount();
        })
        // Collect final strings to a new List
        .toList();
        
        System.out.println("High Value Completed Orders:");
        highValueCompletedOrders.forEach(System.out::println);
    }
}
