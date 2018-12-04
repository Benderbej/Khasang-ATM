package bank;

import java.math.BigDecimal;
import java.util.Random;

public class BankAccount {

    private long number;
    private BigDecimal balance;

    public BankAccount(long number) {
        this.balance = new BigDecimal("0");
        this.number = number;
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

        BankAccount that = (BankAccount) o;

        if (number != that.number) return false;
        return balance != null ? balance.equals(that.balance) : that.balance == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (number ^ (number >>> 32));
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }
}