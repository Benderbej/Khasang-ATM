package bank;

import bankclient.ATM;
import card.Card;
import card.SomeCard;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        //парень пришел в банк и оформил карточку
        Bank bank = new Bank();

        //где-то в банке...
        Client client = new Client("viktor_semenovich");
        BankAccount account = new BankAccount();
        Card card = new SomeCard(account);
        client = bank.firstServiceClient(client, card, account);

        //hashCode() и equals() переопределены в bank.Client(сначала client содержал и объекты BankAccount и Card и хешкод и equals были определены и там) что позволяет нам:
        //где-то в банкомате... создать новый экземпляр, идентичный
        Client theSameClient = new Client("viktor_semenovich");
        BankAccount account2 = new BankAccount();
        Card card2 = new SomeCard(account);
        theSameClient = bank.firstServiceClient(theSameClient, card2, account2);

        System.out.println("client hash"+client.hashCode());//возвращает hash 891650685
        System.out.println("theSameClient hash"+theSameClient.hashCode());//возвращает такой же хеш 891650685

        if ( bank.getClients().contains(theSameClient)){
            ATM atm = new ATM();
            atm.processInsertCard(bank.getBasicCard(theSameClient));
            atm.getBalance();
            atm.putCash(new BigDecimal("20500"));
            atm.getBalance();
            atm.withdrawCash(new BigDecimal("240"));
            atm.getBalance();
            atm.processInsertCard(bank.getBasicCard(theSameClient));
            atm.processEjectCard();
        } else {
            System.out.println("client not found");
        }
    }
}
/*
На повторение коллекций:

8. Добавьте хранение и проверку pin-кода, чтобы операции с картой были защищены предварительным вводом пина.
9. (*) Наряду с atm.ATM добавьте класс InternetShop, который тоже может работать с картой, но вместо pin-кода использует для подтверждения CVV-код и срок действия.
10. (*** на проектирование) Добавьте эмуляцию режима 3d-secure, когда пользователю перед списанием с карты отправляется СМС с кодом подтверждения.
Прочитайте детальнее, как устроен LinkedList: https://habrahabr.ru/post/127864/
=======================================
*/