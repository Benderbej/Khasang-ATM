package bankclient;

import bank.Bank;
import card.Card;

import java.math.BigDecimal;

public class ATM implements Checkable {

    private Card currentCard;
    private int currentPin;

    public boolean processInsertCard(Card card) {
        boolean result = false;
        if(!checkIfNotRepeatInsertion()){
            System.out.println("банкомат глотает карту(банкомат не выплюнул)");
            setCurrentCard(card);
            currentCard.insert();
            result = true;
        } else {System.out.println("банкомат выплевывает карту");}
        return result;
    }

    public void processEjectCard() {
        currentCard.eject();
        currentCard = null;
        System.out.println("карта вынута из банкомата и находится вас в руках! ждем вас снова!");
    };

    public void getBalance() {
        if(currentCard == null){
            System.out.println("вставьте карту");
            return;
        }
        System.out.println("ваш баланс: "+ currentCard.getBalance());
    }

    public void putCash(BigDecimal cash) {
        System.out.println("");
        currentCard.setBalance(currentCard.getBalance().add(cash));
        System.out.println("Сумма "+cash+" зачислена на счет");
    }

    public void withdrawCash(BigDecimal cashQuery) {
        if (haveEnoughCash(cashQuery)){
            currentCard.setBalance(currentCard.getBalance().subtract(cashQuery));
            System.out.println("Сумма "+cashQuery+ "списана со счета");
        }
    }

    private Card getCurrentCard() {
        return currentCard;
    }

    private void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    private boolean haveEnoughCash(BigDecimal cashQuery) {
        if (cashQuery.compareTo(getCurrentCard().getBalance()) < 0){
            System.out.println("успешно, сумма "+cashQuery+" будет списана со счета");
            return true;
        }
        System.out.println("у вас недостаточно средств, сумма "+cashQuery+" не может быть списана со счета");
        return false;
    }

    private boolean checkIfNotRepeatInsertion() {
        if(currentCard != null){return true;}
        return false;
    }

    @Override
    public boolean checkValid() {
        return currentCard.checkValidByPin(currentPin);
    }

    public void setCurrentPin(int currentPin) {
        this.currentPin = currentPin;
    }
}