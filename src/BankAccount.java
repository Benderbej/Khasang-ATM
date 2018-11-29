import java.math.BigDecimal;

public class BankAccount {

    private BigDecimal balance;

    BankAccount() {
        this.balance = new BigDecimal("0");;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccount account = (BankAccount) o;

        return balance != null ? balance.equals(account.balance) : account.balance == null;
    }

    @Override
    public int hashCode() {
        return balance != null ? balance.hashCode() : 0;
    }
}