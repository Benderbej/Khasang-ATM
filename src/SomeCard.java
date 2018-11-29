public class SomeCard extends AbstractCard {

    public SomeCard(PaySystem paySystem, long cardNumber, Client client, BankAccount bankAccount) {
        super(paySystem, cardNumber, client, bankAccount);
    }

    public SomeCard(Client client, BankAccount bankAccount) {
        this(Bank.getPaySystem(), Bank.getCardNum(), client, bankAccount);
    }

    @Override
    public void insert() {
        System.out.println("какая-то карточка из недорогого пластика с шумом въезжает в специальный лоток банкомата... проверяется на подлинность с помощью специальной технологии...");
    }

    @Override
    public void eject() {
        System.out.println("[...]здесь какой то текст-предупреждение о том что нельзя никому говорить номер и cvc код [...]");
    }

}