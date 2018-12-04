package bank;

import bankclient.ATM;
import bankclient.BankClient;
import bankclient.InternetShop;
import bankclient.ProcessCardAble;
import card.Card;
import card.CardImplementationException;
import card.SomeCard;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static Scanner scanner;

    public static void main(String[] args) throws CardImplementationException {
        scanner = new Scanner(System.in);

        //парень пришел в банк и оформил карточку
        Bank bank = new Bank();

        //где-то в банке...
        Client client = new Client("viktor_semenovich");
        BankAccount account = new BankAccount(353453455355345l);
        //Card card = new SomeCard(account, 233, "3333");
        Card card = new SomeCard(233, "3333");

        client = bank.firstServiceClient(client, card, account);

        //hashCode() и equals() переопределены в Client(сначала client содержал и объекты BankAccount и Card и хешкод и equals были определены и там) что позволяет нам:
        //где-то в банкомате... создать новый экземпляр, идентичный
        Client theSameClient = new Client("viktor_semenovich");
        BankAccount account2 = new BankAccount(353453455355345l);
        //Card card2 = new SomeCard(account, 233, "3333");
        Card card2 = new SomeCard(233, "3333");

        theSameClient = bank.firstServiceClient(theSameClient, card2, account2);

        System.out.println("client hash"+client.hashCode());//возвращает hash 891650685
        System.out.println("theSameClient hash"+theSameClient.hashCode());//возвращает такой же хеш 891650685

        if ( bank.getClients().contains(theSameClient)) {
            ATM atm = new ATM(bank);
            atm.processInsertCard(bank.getBasicCard(theSameClient));
            atm.getBalance();
            atm.putCash(new BigDecimal("20500"));

            atm.getBalance();
            atm.withdrawCash(new BigDecimal("240"));
            atm.getBalance();
            atm.processInsertCard(bank.getBasicCard(theSameClient));
            atm.processEjectCard();
            //пользуемся интерфейсом
            ProcessCardAble shop = new InternetShop(bank);
            Card c = bank.getBasicCard(theSameClient);
            System.out.println(card2);
            shop.processStartCard(card2);
            shop.withdraw(new BigDecimal("1030"));
            shop.processStopCard();

        } else {
            System.out.println("client not found");
        }


        scanner.close();
    }
}
/*
На повторение коллекций:
9. (*) Наряду с atm.ATM добавьте класс InternetShop, который тоже может работать с картой, но вместо pin-кода использует для подтверждения CVV-код и срок действия.
10. (*** на проектирование) Добавьте эмуляцию режима 3d-secure, когда пользователю перед списанием с карты отправляется СМС с кодом подтверждения.
Прочитайте детальнее, как устроен LinkedList: https://habrahabr.ru/post/127864/
=======================================
*/