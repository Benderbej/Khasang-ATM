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

        return number == that.number;
    }

    @Override
    public int hashCode() {//НИКАКОГО БАЛАНСА НЕ ДОЛЖНО БЫТЬ В ХЕШ-ФУНКЦИИ!!! баланс - параметр изменяемый, а сами счета у нас кое-где (Bank.cardsAccounts) играют роль ключей в HashMap
        return (int) (number ^ (number >>> 32));
    }
}