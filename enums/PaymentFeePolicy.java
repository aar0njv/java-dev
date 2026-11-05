// enums with abstract method all us to implement methods directly inside
// the enum object body. Enforces strict contracts for each enum const.



import java.math.BigDecimal;

public enum PaymentFeePolicy {
    CREDIT_CARD {
        @Override
        public BigDecimal calculateFee(BigDecimal amount) {
            return amount.multiply(new BigDecimal("0.029"));
        }
    },
    BANK_TRANSFER {
        @Override
        public BigDecimal calculateFee(BigDecimal amount) {
            return new BigDecimal("1.00");
        }
    },
    CASH {
        @Override
        public BigDecimal calculateFee(BigDecimal amount) {
            return BigDecimal.ZERO;
        }
    };

    public abstract BigDecimal calculateFee(BigDecimal amount);
}