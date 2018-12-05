package bank;

import bankclient.ATM;
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
        //пришел в банк и оформил карточку
        Bank bank = new Bank();
        //где-то в банке...
        Client client = new Client("viktor_semenovich");
        BankAccount account = new BankAccount(353453455355345l);
        Card card = new SomeCard(233, "3333");
        client = bank.firstServiceClient(client, card, account);

        //hashCode() и equals() переопределены в Client(сначала client содержал и объекты BankAccount и Card и хешкод и equals были определены и там) что позволяет нам:
        //где-то в банкомате... создать новый экземпляр, идентичный
        Client theSameClient = new Client("viktor_semenovich");
        BankAccount account2 = new BankAccount(353453455355345l);
        Card card2 = new SomeCard(233, "3333");

        theSameClient = bank.firstServiceClient(theSameClient, card2, account2);

        //System.out.println("client hash"+client.hashCode());//возвращает hash 891650685
        //System.out.println("theSameClient hash"+theSameClient.hashCode());//возвращает такой же хеш 891650685

        if ( bank.getClients().contains(theSameClient)) {
            System.out.println("***работа с банкоматом***");
            ATM atm = new ATM(bank);
            atm.processInsertCard(bank.getBasicCard(theSameClient));
            atm.getBalance();
            atm.putCash(new BigDecimal("20500"));
            atm.getBalance();
            atm.withdrawCash(new BigDecimal("240"));
            atm.getBalance();
            atm.processInsertCard(bank.getBasicCard(theSameClient));//пробуем повторно впихнуть карту
            atm.processEjectCard();

            System.out.println("***работа с интернет магазином***");
            //пользуемся интерфейсом в обращении к InternetShop
            ProcessCardAble shop = new InternetShop(bank);
            Card c = bank.getBasicCard(theSameClient);
            System.out.println(c);
            shop.processStartCard(c);
            shop.withdraw(new BigDecimal("1030"));
            shop.processStopCard();
        } else {
            System.out.println("client not found");
        }
        scanner.close();
    }
}