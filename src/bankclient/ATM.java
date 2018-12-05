package bankclient;

import bank.Bank;
import bank.Main;
import card.Card;

import java.math.BigDecimal;
import java.util.Scanner;

public class ATM implements ProcessCardAble {

    private Card currentCard;
    private String attemptPin;
    private Bank bank;
    //private Scanner scanner;

    public ATM(Bank bank){
        this.bank = bank;
    }

    @Override
    public void withdraw(BigDecimal b) {
        withdrawCash(b);
    }

    @Override
    public void processStartCard(Card card) {
        processInsertCard(card);
    }

    @Override
    public void processStopCard() {
        processEjectCard();
    }

    @Override
    public boolean checkValid() {
        return currentCard.checkValidByPin(attemptPin);
    }

    public boolean processInsertCard(Card card) {
        boolean result = false;
        if(!checkIfNotRepeatInsertion()){
            setCurrentCard(card);
            setAttemptPin();
            if(checkValid()) {
                System.out.println("банкомат глотает карту(банкомат не выплюнул)");
                result = true;
            } else {
                System.out.println("неверный пин-код! банкомат выплевывает карту");
                setCurrentCard(null);
            }
        } else {System.out.println("в банкомате уже присутствует карта! - банкомат выплевывает карту");}
        return result;
    }

    public void processEjectCard() {
        if (currentCard != null) {
            currentCard = null;
            System.out.println("карта вынута из банкомата и находится вас в руках! ждем вас снова!");
        }
    }

    public void getBalance() {
        if (currentCard != null) {
            System.out.println("ваш баланс: " + getCurrentCardBalance());
        }
    }

    public void putCash(BigDecimal cash) {
        if(currentCard != null) {
            setCurrentCardBalance(getCurrentCardBalance().add(cash));
            System.out.println("Сумма " + cash + " зачислена на счет");
        }
    }

    public void withdrawCash(BigDecimal cashQuery) {
        if (currentCard != null) {
            if (haveEnoughSumm(cashQuery)) {
                setCurrentCardBalance(getCurrentCardBalance().subtract(cashQuery));
                //System.out.println("withdrawCash() currentCard="+currentCard);
                System.out.println("Сумма " + cashQuery + "списана со счета");
            }
        }
    }

    private BigDecimal getCurrentCardBalance() {
        return currentCard.getBalance(bank);
    }

    private void setCurrentCardBalance(BigDecimal bigDecimal) {
        currentCard.setBalance(bank, bigDecimal);
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    private void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    private boolean haveEnoughSumm(BigDecimal cashQuery) {
        if (cashQuery.compareTo(getCurrentCardBalance()) < 0){
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

    private void setAttemptPin() {
        Scanner scanner = Main.scanner;
        System.out.println("введите PIN-код, пожалуйста [3333]");
        attemptPin = scanner.nextLine();
    }

    public int getCurrentPin(int currentPin) {
        return currentPin;
    }

}