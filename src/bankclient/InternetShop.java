package bankclient;

import bank.Bank;
import bank.Main;
import card.Card;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;

public class InternetShop implements ProcessCardAble{

    private Card currentCard;
    private int attemptCvv;
    private Date attemptDate;
    private Scanner scanner;
    Bank bank;

    public InternetShop(Bank bank){
        this.bank = bank;
    }

    @Override
    public void withdraw(BigDecimal query) {
        if (currentCard != null) {
            if (haveEnoughSumm(query)) {
                setCurrentCardBalance(getCurrentCardBalance().subtract(query));
                System.out.println("Сумма " + query + "списана со счета");
            }
        }
    }

    @Override
    public void processStopCard() {
        if (currentCard != null) {
            currentCard = null;
            System.out.println("карта вынута из банкомата и находится вас в руках! ждем вас снова!");
        }
    }

    @Override
    public boolean checkValid() {
        System.out.println("c="+currentCard);
        boolean cvvVal = getCurrentCard().checkValidByCvv(attemptCvv);
        boolean dateVal = getCurrentCard().checkValidByDate(attemptDate);
        return currentCard.checkValidByCvv(attemptCvv);
    }

    @Override
    public void processStartCard(Card card) {
        System.out.println("card="+card);
        setCurrentCard(card);
        System.out.println("c="+currentCard);
        setAttemptCvv();
        if(checkValid()) {
            System.out.println("банкомат глотает карту(банкомат не выплюнул)");
        } else {
            System.out.println("неверный пин-код! банкомат выплевывает карту");
            setCurrentCard(null);
        }
    }

    private boolean haveEnoughSumm(BigDecimal cashQuery) {
        if (cashQuery.compareTo(getCurrentCardBalance()) < 0){
            System.out.println("успешно, сумма "+cashQuery+" будет списана со счета");
            return true;
        }
        System.out.println("у вас недостаточно средств, сумма "+cashQuery+" не может быть списана со счета");
        return false;
    }

    private void setAttemptCvv() {
        //System.out.println("yuy");
        scanner = new Scanner(System.in);
        //System.out.println("");
        //System.out.println("введите трехзначный Cvv-код, с обратной стороны карты");
        if(scanner == null){
            System.out.println("scanner is null");
        }
        attemptCvv = Integer.parseInt(scanner.nextLine());
    }

    private void setExpirationDate() {
        scanner = Main.scanner;
        System.out.println("введите дату  с карты в формате ");
        attemptCvv = Integer.parseInt(scanner.nextLine());
    }

    private Card getCurrentCard() {
        return currentCard;
    }

    private void setCurrentCard(Card currentCard) {
        System.out.println("currentCard="+currentCard);
        this.currentCard = currentCard;
    }

    private BigDecimal getCurrentCardBalance() {
        return currentCard.getBalance(bank);
    }

    private void setCurrentCardBalance(BigDecimal bigDecimal) {
        currentCard.setBalance(bank, bigDecimal);
    }



}