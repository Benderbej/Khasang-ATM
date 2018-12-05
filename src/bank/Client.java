package bank;

import card.Card;

import java.util.HashSet;

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
    }//TODO по хорошему уникальность хешкода клиента, конечно, не может быть основана только на его имени, ужен уникальный идентификатор
}