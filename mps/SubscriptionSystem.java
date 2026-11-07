
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.HashMap;

// Interfaces define what an object must do, but not how it does it. allows ploymorphism and abstraction.
interface FeePolicy {
    BigDecimal calculateMonthlyFee(int transactionCount);
}

// Enums implement interfaces. Forces every plan to provide its own MonthlyFree calculation.
enum PlanTier implements FeePolicy {
    BASIC {
        @Override // Overrides the parent method to provide own implementation.
        public BigDecimal calculateMonthlyFee(int transactionCount) {
            BigDecimal baseFee = new BigDecimal("10.00"); 
            BigDecimal transactionFee = new BigDecimal("0.50").multiply(new BigDecimal(transactionCount));
            return baseFee.add(transactionFee);
        }
    },

    PREMIUM {
        @Override 
        public BigDecimal calculateMonthlyFee(int transactionCount) {
            BigDecimal baseFee = new BigDecimal("20.00");
            BigDecimal transactionFee = new BigDecimal("0.25").multiply(new BigDecimal(transactionCount));
            return baseFee.add(transactionFee);
        }
    }
}

// Immutable class. By making fiedlds final and no setters, they are immutable and cannot be changed.
class MerchantAccount {

    private final String merchantId;
    private final PlanTier planTier;
    

    public MerchantAccount(String merchantId, PlanTier planTier) {
        this.merchantId = merchantId;
        this.planTier = planTier;
    }

    public String getMerchantId() { return merchantId; }
    public PlanTier getPlanTier() { return planTier; }

    // Overridden together so identical accounts route to the exact same bucket in a Map.
    @Override 
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MerchantAccount that = (MerchantAccount) o;
        return Objects.equals(merchantId, that.merchantId) && planTier == that.planTier; // comparing enums with  ( == ) is safe and fast.
    }

    @Override
    public int hashCode() {
        return Objects.hash(merchantId, planTier);
    }

    @Override
    public String toString() {
        return "MerchantAccount{" +
                "merchantId='" + merchantId + '\'' +
                ", planTier=" + planTier +
                '}';
    }
}

public class SubscriptionSystem {
    public static void main(String args[]){

        MerchantAccount acme = new MerchantAccount("101", PlanTier.BASIC);
        MerchantAccount acmeDuplicate = new MerchantAccount("101", PlanTier.BASIC);
        MerchantAccount beta = new MerchantAccount("102", PlanTier.PREMIUM);

        Map<MerchantAccount, Integer> monthlyTransactions = new HashMap<>();

        monthlyTransactions.put(acme, 100);
        monthlyTransactions.put(beta, 3000);

        // Testing equals/hashCode:
        // Even though acmeDuplicate is a newly instantiated object in memory, 
        // the Map perfectly retrieves the 100 transactions because the data matches.
        System.out.println("Acme Transactions: " + monthlyTransactions.get(acmeDuplicate));

        for (Map.Entry<MerchantAccount, Integer> entry : monthlyTransactions.entrySet()) {
            MerchantAccount account = entry.getKey();
            int transactionCount = entry.getValue();

            BigDecimal bill = account.getPlanTier().calculateMonthlyFee(transactionCount);
            System.out.println(account + " - Monthly Bill: $" + bill);
            
        }
    }

}
