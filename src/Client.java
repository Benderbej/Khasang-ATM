
import java.util.HashSet;
import java.util.LinkedList;

/*
для простоты пока каждый клиент является владельцем карты
 */
public class Client {

    private String cardOwnerNameLat;
    private HashSet<BankAccount> accounts;
    private BankAccount basicAccount;
    private HashSet<Card> cards;

    private Card basicCard;

    Client(String cardOwnerNameLat) {
        this.cardOwnerNameLat = cardOwnerNameLat;
        accounts = new HashSet<>();
        cards = new HashSet<>();
    }

    public HashSet<BankAccount> getAccounts() {
        return accounts;
    }

    public BankAccount getAccount(BankAccount account) {
        accounts.contains(account);
        return account;
    }

    public BankAccount addAccount(BankAccount bankAccount){
        accounts.add(bankAccount);
        return bankAccount;
    }

    public BankAccount getBasicAccount() {
        return basicAccount;
    }

    public void setBasicAccount(BankAccount basicAccount) {
        this.basicAccount = basicAccount;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card getBasicCard() {
        return basicCard;
    }

    public void setBasicCard(Card basicCard) {
        this.basicCard = basicCard;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (cardOwnerNameLat != null ? !cardOwnerNameLat.equals(client.cardOwnerNameLat) : client.cardOwnerNameLat != null)
            return false;
        if (accounts != null ? !accounts.equals(client.accounts) : client.accounts != null) return false;
        if (basicAccount != null ? !basicAccount.equals(client.basicAccount) : client.basicAccount != null)
            return false;
        if (cards != null ? !cards.equals(client.cards) : client.cards != null) return false;
        return basicCard != null ? basicCard.equals(client.basicCard) : client.basicCard == null;
    }

    @Override
    public int hashCode() {
        int result = cardOwnerNameLat != null ? cardOwnerNameLat.hashCode() : 0;
        result = 31 * result + (accounts != null ? accounts.hashCode() : 0);
        result = 31 * result + (basicAccount != null ? basicAccount.hashCode() : 0);
        result = 31 * result + (cards != null ? cards.hashCode() : 0);
        result = 31 * result + (basicCard != null ? basicCard.hashCode() : 0);
        return result;
    }
}