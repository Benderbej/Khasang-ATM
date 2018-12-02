package card;

import bank.BankAccount;
import bank.PaySystem;

import java.math.BigDecimal;

public abstract class AbstractCard implements Card {

    private PaySystem paySytem;
    private long cardNumber;
    private BankAccount bankAccount;

    public abstract void insert();

    public abstract void eject();

    private AbstractCard(){};//чтобы неповадно было без аргументов создавать!!!

    public AbstractCard(PaySystem paySystem, long cardNumber, BankAccount bankAccount){
        this.paySytem = paySystem;
        this.cardNumber = cardNumber;
        this.bankAccount = bankAccount;
    }

    public BigDecimal getBalance() {
        return bankAccount.getBalance();
    }

    public void setBalance(BigDecimal bigDecimal) {
        bankAccount.setBalance(bigDecimal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractCard that = (AbstractCard) o;

        if (cardNumber != that.cardNumber) return false;
        if (paySytem != null ? !paySytem.equals(that.paySytem) : that.paySytem != null) return false;
        return bankAccount != null ? bankAccount.equals(that.bankAccount) : that.bankAccount == null;
    }

    @Override
    public int hashCode() {
        int result = paySytem != null ? paySytem.hashCode() : 0;
        result = 31 * result + (int) (cardNumber ^ (cardNumber >>> 32));
        result = 31 * result + (bankAccount != null ? bankAccount.hashCode() : 0);
        return result;
    }
}