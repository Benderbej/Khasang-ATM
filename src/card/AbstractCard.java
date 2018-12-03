package card;

import bank.BankAccount;
import bank.PaySystem;

import java.math.BigDecimal;

public abstract class AbstractCard implements Card {

    private PaySystem paySytem;
    private long cardNumber;
    private BankAccount bankAccount;
    private int cvv;
    private int pin;

    public abstract void insert();

    public abstract void eject();

    private AbstractCard() {};//чтобы неповадно было без аргументов создавать!!!

    public AbstractCard(PaySystem paySystem, long cardNumber, BankAccount bankAccount, int cvv, int pin) throws CardImplementationException {
        this.paySytem = paySystem;
        this.cardNumber = cardNumber;
        this.bankAccount = bankAccount;
        initCvv(cvv);
        initPin(pin);
    }

    private void initCvv(int cvv) {
        if (Integer.toString(cvv).length()!=3){
            try {
                throw new Exception("setCvv() in Card Interface is not correctly implemented");//насколько правомочна такая конструкция?
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.cvv = cvv;
    }

    private void initPin(int pin) throws CardImplementationException {
        if (Integer.toString(pin).length()!=4){throw new CardImplementationException("setPin() in Card Interface is not correctly implemented");}//здесь сделал немного по-другому, в интерфейсе будет видно что метод может выкинуть исключение
        this.pin = pin;
    }

    public boolean checkValidByCvv(int cvv) {
        boolean res = (cvv == this.getCvv()) ? true : false;
        return res;
    }

    public boolean checkValidByPin(int pin) {
        boolean res = (pin == this.getPin()) ? true : false;
        return res;
    }

    public BigDecimal getBalance() {
        return bankAccount.getBalance();
    }

    public void setBalance(BigDecimal bigDecimal) {
        bankAccount.setBalance(bigDecimal);
    }

    public int getCvv() {
        return 0;
    }

    private void setPin(int pin) {
        this.pin = pin;
    }

    public int getPin() {
        return 0;
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