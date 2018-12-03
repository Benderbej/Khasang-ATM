package card;

import bank.Bank;
import bank.BankAccount;
import bank.PaySystem;

public class SomeCard extends AbstractCard {

    public SomeCard(PaySystem paySystem, long cardNumber, BankAccount bankAccount, int cvv, String pin) throws CardImplementationException {
        super(paySystem, cardNumber, bankAccount, cvv, pin);
    }

    public SomeCard(BankAccount bankAccount, int cvv, String pin) throws CardImplementationException {
        this(Bank.getPaySystem(), Bank.getCardNum(), bankAccount, cvv, pin);
    }
}