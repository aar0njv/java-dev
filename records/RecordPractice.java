
import java.math.BigDecimal;
public class RecordPractice {
    public static void main(String[] args) {
        // 1. Instantiation looks exactly like a normal class
        OrderEvent event = new OrderEvent(
            "evt-999", 
            "ord-123", 
            "CREATED", 
            new BigDecimal("50.00"), 
            "2026-11-05T20:48:00Z"
        );

        // 2. Accessing data (Use the field name directly, no 'get' prefix)
        System.out.println("Processing Order: " + event.orderId());
        System.out.println("Transaction Amount: $" + event.amount());

        // 3. Built-in toString() safety
        System.out.println(event); 
        // Outputs a clean string instead of a memory address:
        // OrderEvent[eventId=evt-999, orderId=ord-123, type=CREATED, amount=50.00, occurredAt=2026-11-05T20:48:00Z]
    }
}