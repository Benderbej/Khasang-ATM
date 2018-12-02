package bank;

import card.Card;

import java.util.HashSet;

/*
для простоты пока каждый клиент является владельцем карты
 */
public class Client {

    private String cardOwnerNameLat;

    public Client(String cardOwnerNameLat) {
        this.cardOwnerNameLat = cardOwnerNameLat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return cardOwnerNameLat != null ? cardOwnerNameLat.equals(client.cardOwnerNameLat) : client.cardOwnerNameLat == null;
    }

    @Override
    public int hashCode() {
        return cardOwnerNameLat != null ? cardOwnerNameLat.hashCode() : 0;
    }

    public BankAccount getBasicAccount(Bank bank) {
        return bank.getBasicAccount(this);
    }

    public Card getBasicCard(Bank bank){
        return bank.getBasicCard(this);
    }


}