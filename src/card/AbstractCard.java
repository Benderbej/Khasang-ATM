package card;

import bank.Bank;
import bank.BankAccount;
import bank.PaySystem;

import java.math.BigDecimal;

public abstract class AbstractCard implements Card {

    private PaySystem paySytem;
    private long cardNumber;
    private int cvv;
    private String pin;

    private AbstractCard() {};//чтобы неповадно было без аргументов создавать!!!

    public AbstractCard(PaySystem paySystem, long cardNumber, int cvv, String pin) throws CardImplementationException {
        init(paySystem, cardNumber);
        initCvv(cvv);
        initPin(pin);
    }

    private void init(PaySystem paySystem, long cardNumber) {
        this.paySytem = paySystem;
        this.cardNumber = cardNumber;
    }

    private void initCvv(int cvv) {
        if (String.valueOf(cvv).length()!=3){
            try {
                throw new Exception("setCvv() in Card Interface is not correctly implemented");//насколько правомочна такая конструкция?
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.cvv = cvv;
    }

    private void initPin(String pin) throws CardImplementationException {
        if (pin.length()!=4){throw new CardImplementationException("setPin() in Card Interface is not correctly implemented");}//здесь сделал немного по-другому, в интерфейсе будет видно что метод может выкинуть исключение
        this.pin = pin;
    }

    public boolean checkValidByCvv(int cvv) {
        boolean res = (cvv == (getCvv())) ? true : false;
        return res;
    }

    public boolean checkValidByPin(String pin) {
        boolean res = (pin.equals(this.getPin())) ? true : false;
        return res;
    }

    public BigDecimal getBalance(Bank bank) {
        return bank.getCardAccount(this).getBalance();
    }

    public void setBalance(Bank bank, BigDecimal bigDecimal) {
        bank.setAccountBalance(this, bigDecimal);
        //bank.getCardAccount(this).setBalance(bigDecimal);
    }

    public int getCvv() {
        return cvv;
    }

    private void setPin(String pin) {
        this.pin = pin;
    }

    public String getPin() {
        return pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractCard that = (AbstractCard) o;

        if (cardNumber != that.cardNumber) return false;
        if (cvv != that.cvv) return false;
        if (!paySytem.equals(that.paySytem)) return false;
        return pin.equals(that.pin);
    }

    @Override
    public int hashCode() {
        int result = paySytem.hashCode();
        result = 31 * result + (int) (cardNumber ^ (cardNumber >>> 32));
        result = 31 * result + cvv;
        result = 31 * result + pin.hashCode();
        return result;
    }
}