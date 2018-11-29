import java.math.BigDecimal;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        //парень пришел в банк и оформил карточку
        Bank bank = new Bank();

        Client client = bank.firstServiceClient("viktor_semenovich");
        System.out.println("client hash"+client.hashCode());


        Client theSameClient = new Client("viktor_semenovich");
        BankAccount bankAccount = new BankAccount();
        theSameClient.setBasicAccount(bankAccount);
        theSameClient.addAccount(bankAccount);
        //Card sameCard = new SomeCard(theSameClient, bankAccount);
        //theSameClient.addCard(sameCard);
        //theSameClient.setBasicCard(sameCard);

        Client theSameClient2 = new Client("viktor_semenovich");
        BankAccount bankAccount2 = new BankAccount();
        theSameClient.setBasicAccount(bankAccount2);
        theSameClient.addAccount(bankAccount2);
        //Card sameCard2 = new SomeCard(theSameClient2, bankAccount2);
        //theSameClient.addCard(sameCard2);
        //theSameClient.setBasicCard(sameCard2);

        System.out.println("theSameClient hash"+theSameClient.hashCode());
        System.out.println("theSameClient2 hash"+theSameClient2.hashCode());

        if(theSameClient == theSameClient2){
            System.out.println("== links");
        }
        if(theSameClient.equals(theSameClient2)){
            System.out.println("equals");
        }

        HashSet<Client> cls = new HashSet<>();
        cls.add(theSameClient);
        if (cls.contains(theSameClient2)){
            System.out.println("hashcodes equals and object found");
        }


        //где-то в банкомате...
        //Client client = new Client("viktor_semenovich");
        if ( bank.getClients().contains(theSameClient)){
            System.out.println("contains");

            ATM atm = new ATM();

            atm.processInsertCard(client.getBasicCard());
            atm.getBalance();
            atm.putCash(new BigDecimal("20500"));
            atm.getBalance();
            atm.withdrawCash(new BigDecimal("240"));
            atm.getBalance();
            atm.processInsertCard(client.getBasicCard());
            atm.processEjectCard();
        } else {
            System.out.println("client not found");
        }





    }
}

//храню HashSet со списком счетов в клиенте и в банке тоже храню HashSet с общим списком счетов, архитектурно верно?


/*
Самостоятельная работа
a) Попробуйте по памяти, с нуля создать программу Atm, ту, которую мы делали.
b) Доработайте задачи:
Простые (beginner):
1. Проверить вставлена ли карта перед снятием денег и инфо о балансе
2. Перед снятием денег проверить достаточно ли финансов на карте

Сложные (intermediate):
1. Разнесите на три пакета весь код: пакет с АТМ, пакет с Картами и пакет с точкой входа.
(для того, чтобы потренироваться использовать модификаторы доступа: public, protected, default, private)
2. Сделайте так, чтобы деньги хранились на счету в банке, а не на карте.

3. Добавьте класс Клиент, экземпляр которого нужно передавать банку для выпуска карты и открытия счета.
На повторение коллекций:
4. В Банке используйте коллекцию для хранения всех клиентов банка. Какой интерфейс будете использовать Set или List?


5. Используйте, например, коллекцию HashMap, чтобы каждому пользователю сопоставить основной счет в Банке.

6. (*) У пользователя же может быть много счетов? Модифицируйте использование коллекции HashMap,
чтобы сопоставлять пользователю коллекцию (реализующую интерфейс Set или List), которая уже в свою очередь хранит все счета клиента.
7. (** на поиск информации) Используйте классы BigDecimal / BigInteger для хранения финансовых данных.
8. Добавьте хранение и проверку pin-кода, чтобы операции с картой были защищены предварительным вводом пина.
9. (*) Наряду с ATM добавьте класс InternetShop, который тоже может работать с картой, но вместо pin-кода использует для подтверждения CVV-код и срок действия.
10. (*** на проектирование) Добавьте эмуляцию режима 3d-secure, когда пользователю перед списанием с карты отправляется СМС с кодом подтверждения.
Прочитайте детальнее, как устроен LinkedList: https://habrahabr.ru/post/127864/
*/