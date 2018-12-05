package bank;

import bankclient.BankClient;
import card.Card;
import card.SomeCard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class Bank {

    private HashSet<Client> clients;
    private HashMap<Client, BankAccount> clientsBasicAccounts;
    private HashMap<Client, ArrayList<BankAccount>> clientsAccounts;//!!!!!!!!!!!!
    //счет может существовать без карты(создается раньше карты), а карта без счета,
    // кроме того, к одной карте может быть привязано несколько счетов, а у на один счет оформлено несколько карт... налицо - многие ко многим...
    //как наиболее простой способ решения проблемы я решил создать два хешмапа, один для одной связи, другой - для другой, сделать приватными эти поля,
    //а для доступа открыть лишь метод bindAccountToCard, в котором бы не забывалось одновлять данные в обоих хешмапах
    //пробовал и другие варианты решения, но мне они показались громоздкими и "дорогими"
    //выпендриватся с циклами containsValue, переворачивать ключи со значениями, и заниматься подобным  - в итоге не стал(хотя пробовал по всякому)
    //от простого HashMap отказыаться не хотел, изза уникальности ключей,  O(1) сложности операций get(), и возможности инициализировать null-ом значения
    private HashMap<Card, BankAccount>  cardsAccounts;
    private HashMap<BankAccount, Card> accountsCards;
    private static PaySystem defaultPaySystem;

    public Bank() {
        this.clients = new HashSet<>();
        this.clientsBasicAccounts = new HashMap<>();
        clientsAccounts = new HashMap<>();
        cardsAccounts = new HashMap<>();
        accountsCards = new HashMap<>();
        defaultPaySystem = new PaySystem("Mastercard");
    }

    void addCard(Card card){
        cardsAccounts.put(card, null);
    }

    void addClient(Client client){
        clients.add(client);
        clientsAccounts.put(client, new ArrayList<>());
    }

    void addAccount(Client client, BankAccount account){
        clientsAccounts.get(client).add(account);
    }

    public void bindAccountToCard(Card card, BankAccount account){
        cardsAccounts.put(card, account);
        accountsCards.put(account, card);
    }

    public Client firstServiceClient(Client client, Card card, BankAccount account){//обслуживание нового клиента по выпуску карты и созданию основного счета
        addClient(client);

        addAccount(client, account);
        clientsBasicAccounts.put(client, account);
        addCard(card);
        bindAccountToCard(card, account);
        clients.add(client);
        return client;
    }

    private Client updateClient(Client client, BankAccount bankAccount) {
        return client;
    }

    public static long getCardNum(){
        Long cardNum = 4342423423432424l;
        return cardNum;
    }

    public static PaySystem getPaySystem() {
        PaySystem paySystem = defaultPaySystem;
        return paySystem;
    }

    public HashSet<Client> getClients() {
        return clients;
    }

    BankAccount getBasicAccount(Client client){
        return clientsBasicAccounts.get(client);
    }//ищем основной счет

    Card getBasicCard(Client client){//по основному счету ищем карту
        return accountsCards.get(getBasicAccount(client));
    }

    public BankAccount getCardAccount(Card card){//инкапсулируем счета в банке, чтобы они не были доступны через карту!!!
        return cardsAccounts.get(card);
    }

    public boolean setAccountBalance(Card card, BigDecimal bigDecimal) {//метод обновляет все коллекции класса чтобы не нарушить целостность данных
        BankAccount bankAccount = cardsAccounts.get(card);
        bankAccount.setBalance(bigDecimal);
        cardsAccounts.put(card, bankAccount);
        return true;
    }

    public int getConfirmationCode() {
        int randomNum = ThreadLocalRandom.current().nextInt(1000, 9999);
        System.out.println("randomNum "+randomNum);
        return randomNum;
    }
}