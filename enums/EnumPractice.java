// public enum .... {}

enum PaymentStatus {
    PENDING(100, "Awaiting gateway confirmation"),
    COMPLETED(200, "Funds secured"),
    FAILED(400, "Transaction declined by issuer"),
    REFUNDED(500, "Transaction declined, amount refunded");

    // Enum fields
    private final int code;
    private final String description;

    // Constructor 
    PaymentStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}

public class EnumPractice {
    public static void main(String[] args) {
        PaymentStatus currentStatus = PaymentStatus.PENDING;

        // Modern switch expression (returns a value directly, no 'break' needed)
        String userMessage = switch (currentStatus) {
            case PENDING -> "Please wait while we process your payment.";
            case COMPLETED -> "Success! " + currentStatus.getDescription();
            case FAILED -> "Error: " + currentStatus.getDescription();
            case REFUNDED -> "Notice: " + currentStatus.getDescription();
        };

        System.out.println(currentStatus.getCode());
        System.out.println(currentStatus.getDescription());
        System.out.println(userMessage);
    }
}