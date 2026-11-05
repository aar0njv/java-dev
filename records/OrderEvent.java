
import java.math.BigDecimal;

public record OrderEvent(
    String eventId, 
    String orderId, 
    String type, 
    BigDecimal amount, 
    String occurredAt
) {}