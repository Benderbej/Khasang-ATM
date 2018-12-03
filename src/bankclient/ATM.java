package bankclient;

import bank.Bank;
import card.Card;

import java.math.BigDecimal;
import java.util.Scanner;

public class ATM implements Checkable {

    private Card currentCard;
    private String attemptPin;

    public boolean processInsertCard(Card card) {
        boolean result = false;
        if(!checkIfNotRepeatInsertion()){
            setCurrentCard(card);
            setAttemptPin();
            if(checkValid()) {
                System.out.println("банкомат глотает карту(банкомат не выплюнул)");
                currentCard.insert();
                result = true;
            } else {
                System.out.println("неверный пин-код! банкомат выплевывает карту");
                setCurrentCard(null);
            }
        } else {System.out.println("банкомат выплевывает карту");}
        return result;
    }

    public void processEjectCard() {
        if (currentCard != null) {
            currentCard.eject();
            currentCard = null;
            System.out.println("карта вынута из банкомата и находится вас в руках! ждем вас снова!");
        }
    }

    public void getBalance() {
        if (currentCard != null) {
            System.out.println("ваш баланс: " + currentCard.getBalance());
        }
    }

    public void putCash(BigDecimal cash) {
        if(currentCard != null) {
            currentCard.setBalance(currentCard.getBalance().add(cash));
            System.out.println("Сумма " + cash + " зачислена на счет");
        }
    }

    public void withdrawCash(BigDecimal cashQuery) {
        if (currentCard != null) {
            if (haveEnoughCash(cashQuery)) {
                currentCard.setBalance(currentCard.getBalance().subtract(cashQuery));
                System.out.println("Сумма " + cashQuery + "списана со счета");
            }
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
        System.out.println();
        return currentCard.checkValidByPin(attemptPin);
    }

    private void setAttemptPin() {
        System.out.println("введите PIN-код, пожалуйста");
        Scanner scanner = new Scanner(System.in);
        attemptPin = scanner.nextLine();
        //System.in.close();
    }


    public int getCurrentPin(int currentPin) {
        return currentPin;
    }
}