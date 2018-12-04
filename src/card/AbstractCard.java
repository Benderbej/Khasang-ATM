package card;

import bank.Bank;
import bank.BankAccount;
import bank.PaySystem;

import java.math.BigDecimal;

public abstract class AbstractCard implements Card {

    private PaySystem paySytem;
    private long cardNumber;
    private BankAccount bankAccount;
    private int cvv;
    private String pin;

    private AbstractCard() {};//чтобы неповадно было без аргументов создавать!!!

    public AbstractCard(PaySystem paySystem, long cardNumber, BankAccount bankAccount, int cvv, String pin) throws CardImplementationException {
        init(paySystem, cardNumber, bankAccount);
        initCvv(cvv);
        initPin(pin);
    }

    private void init(PaySystem paySystem, long cardNumber, BankAccount bankAccount) {
        this.paySytem = paySystem;
        this.cardNumber = cardNumber;
        this.bankAccount = bankAccount;
    }






    public AbstractCard(PaySystem paySystem, long cardNumber, int cvv, String pin) throws CardImplementationException {
        init(paySystem, cardNumber, bankAccount);
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

    public BigDecimal getBalance() {
        return bankAccount.getBalance();
    }

    public void setBalance(BigDecimal bigDecimal) {
        bankAccount.setBalance(bigDecimal);
    }






    public BigDecimal getBalance2(Bank bank) {
        return bank.getCardAccount(this).getBalance();
    }

    public void setBalance2(Bank bank, BigDecimal bigDecimal) {
        bank.getCardAccount(this).setBalance(bigDecimal);
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