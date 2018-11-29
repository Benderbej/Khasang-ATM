import java.util.HashMap;
import java.util.HashSet;

public class Bank {

    private HashSet<Client> clients;
    private HashSet<PaySystem> paySystems;
    private HashSet<Card> cards;
    private HashSet<BankAccount> accounts;
    private HashMap<Client, BankAccount> clientsBasicAccounts;
    private static PaySystem defaultPaySystem;

    public Bank() {
        this.clients = new HashSet<>();
        this.paySystems = new HashSet<>();
        this.cards = new HashSet<>();
        this.accounts = new HashSet<>();
        this.clientsBasicAccounts = new HashMap<>();
    }

    public BankAccount openFirstAccount(Client client) {//при первом создании счета для клиента добавляем счет также в список базовых счетов банка
        BankAccount account = new BankAccount();
        client.addAccount(account);
        client.setBasicAccount(account);
        accounts.add(account);
        clientsBasicAccounts.put(client, account);
        return account;
    }

    public Client firstServiceClient(String engName){//обслуживание нового клиента по выпуску карты
        Client client = new Client(engName);
        BankAccount account = openFirstAccount(client);
        client.addAccount(account);
        Card card = new SomeCard(client, account);
        cards.add(card);
        client.setBasicCard(card);
        clients.add(client);
        return client;
    }

    public static long getCardNum(){
        Long cardNum = 4342423423432424l;
        return cardNum;
    }

    public static PaySystem getPaySystem(){
        PaySystem paySystem = defaultPaySystem;
        return paySystem;
    }

    public HashSet<Client> getClients() {
        return clients;
    }
}