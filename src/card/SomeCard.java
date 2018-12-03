package card;

import bank.Bank;
import bank.BankAccount;
import bank.PaySystem;

public class SomeCard extends AbstractCard {

    public SomeCard(PaySystem paySystem, long cardNumber, BankAccount bankAccount, int cvv, int pin) throws CardImplementationException {
        super(paySystem, cardNumber, bankAccount, cvv, pin);
    }

    public SomeCard(BankAccount bankAccount, int cvv, int pin) throws CardImplementationException {
        this(Bank.getPaySystem(), Bank.getCardNum(), bankAccount, cvv, pin);
    }

    @Override
    public void insert() {
        System.out.println("какая-то карточка из недорогого пластика с шумом въезжает в специальный лоток банкомата... видимо проверяется на подлинность с помощью специальной технологии... слышится серия технических звуков...");
    }

    @Override
    public void eject() {
        System.out.println("[...]здесь какой то текст-предупреждение о том что нельзя никому говорить номер и cvc код [...]");
    }
}