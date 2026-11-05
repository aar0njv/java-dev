
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

public class MerchantProfile {
    private final String id;
    private final String companyName;

    public MerchantProfile(String id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        // check if same obj
        if (this == o) return true;
        
        // check if null or different class
        if (o == null || getClass() != o.getClass()) return false;
        
        // Cast the object and compare actual data fields
        MerchantProfile that = (MerchantProfile) o;
        return Objects.equals(id, that.id) && 
               Objects.equals(companyName, that.companyName);
    }

    @Override
    public int hashCode() {
        // Objects.hash() reliably generates the same integer 
        // as long as the inputs (id and companyName) are identical.
        return Objects.hash(id, companyName);
    }

    public static void main(String[] args) {
        MerchantProfile acme1 = new MerchantProfile("101", "Acme Corp");
        MerchantProfile acme2 = new MerchantProfile("101", "Acme Corp");

        // override equals(), this prints 'true'
        System.out.println("Are they equal? " + acme1.equals(acme2));

        Set<MerchantProfile> uniqueMerchants = new HashSet<>();
        uniqueMerchants.add(acme1);
        uniqueMerchants.add(acme2); 
        
        System.out.println("Total unique merchants: " + uniqueMerchants.size()); // Prints 1
    }
}