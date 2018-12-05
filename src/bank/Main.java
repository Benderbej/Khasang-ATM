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
        //сотрудник банка открыл счетб выпустил карточкуб привязал счет к карточке
        client = bank.firstServiceClient(client, card, account);

        //где-то в банкомате... (!!!!!!!новый экземпляр, идентичный, Client.hashcode() определен)
        //System.out.println("client hash"+client.hashCode());//возвращает hash 891650685
        //System.out.println("theSameClient hash"+theSameClient.hashCode());//возвращает такой же хеш 891650685
        Client theSameClient = new Client("viktor_semenovich");

        if ( bank.getClients().contains(theSameClient)) {
            System.out.println("***работа с банкоматом***");
            ATM atm = new ATM(bank);
            atm.processInsertCard(bank.getBasicCard(theSameClient));//вставляем карту, вводим пин
            atm.getBalance();//смотрим баланс
            atm.putCash(new BigDecimal("20500"));//кладем кеш
            atm.getBalance();//смотрим баланс
            atm.withdrawCash(new BigDecimal("240"));//берем кеш
            atm.getBalance();//смотрим баланс
            atm.processInsertCard(bank.getBasicCard(theSameClient));//пробуем повторно впихнуть карту
            atm.processEjectCard();//забираем карту

            System.out.println("***работа с интернет магазином***");
            //пользуемся интерфейсом в обращении к InternetShop
            ProcessCardAble shop = new InternetShop(bank);
            Card c = bank.getBasicCard(theSameClient);
            shop.processStartCard(c);//идентификация карты
            shop.withdraw(new BigDecimal("1030"));//магазин списал денег со счета привязанного к карте
            shop.processStopCard();
        } else {
            System.out.println("client not found");
        }
        scanner.close();
    }
}