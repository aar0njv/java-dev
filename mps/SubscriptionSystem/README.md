# Java Data Structures and Object Comparison

This document outlines the core concepts Interfaces and Abstraction, ENums, hashCode/equals, Overriding and immutability

## The @Override Annotation

Every Java class implicitly inherits from a master parent class called `Object`, which provides default (and often unhelpful) versions of standard methods. By using `@Override`, you tell the compiler that you are intentionally replacing that parent behavior. If you accidentally misspell the method (e.g., `hashcode()` instead of `hashCode()`), the `@Override` annotation forces the compiler to break the build, protecting you from a silent bug.

## Step 1: The hashCode() Method (The Storage)

When you store or look up an object in a `Map`, Java calls `hashCode()` to calculate an integer value that dictates exactly which memory "bucket" the object belongs in.

*   `Objects.hash(merchantId, planTier)`: This utility reliably generates the same integer as long as the inputs are identical. If two objects are considered equal, they absolutely must route to the exact same hash code bucket.

## Step 2: The equals() Method (The Final Verification)

Because multiple distinct objects can occasionally generate the same hash code (a collision), the Map navigates to the assigned bucket and uses `equals()` to verify the actual data inside.

*   `if (this == o) return true;`: If the variables point to the exact same object in memory, it skips the rest of the checks and immediately returns true.
*   `if (o == null || getClass() != o.getClass()) return false;`: The safety check. This guarantees the incoming object is not null and is specifically a `MerchantAccount`, preventing your backend from crashing with a `NullPointerException` or a class cast error.
*   `MerchantAccount that = (MerchantAccount) o;`: Once the safety checks pass, the generic `Object o` is cast into a `MerchantAccount` so its specific properties can be accessed.
*   `return Objects.equals(merchantId, that.merchantId) && ...`: The actual data comparison. `Objects.equals()` safely compares the fields without crashing if one of them happens to be null.

## Execution Notes

### Safe Map Retrieval
The code creates two distinct `MerchantAccount` instances (`acme` and `acmeDuplicate`) with identical data. Because Java natively compares memory addresses, these are technically two completely different objects in memory. However, because `equals()` and `hashCode()` were correctly overridden together, the `HashMap` uses the hash code to find the exact same memory bucket, uses `equals()` to verify the data, and successfully retrieves the 100 transactions using the duplicate key. Mastering this tandem relationship is a strict prerequisite to prevent silent data loss.

### Collection Iteration
The `for` loop iterates over the `monthlyTransactions.entrySet()`, which safely unpacks both the `MerchantAccount` key and the integer value (transaction count) simultaneously so they can be processed together.

### Polymorphic Enum Execution
When the loop calls `account.getPlanTier().calculateMonthlyFee(transactionCount)`, it delegates the business logic entirely to the Enum. Because the Enum uses abstract methods to enforce a strict contract, the execution automatically triggers the unique mathematical formula locked inside the `BASIC` or `PREMIUM` states without requiring a single `if/else` or `switch` statement.

### Financial Precision
The resulting bill is returned and processed as a `BigDecimal`. This strictly adheres to enterprise safety rules, which dictate that you must never use `double` or `float` for financial data to prevent compounding rounding errors.