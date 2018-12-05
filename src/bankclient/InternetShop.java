package bankclient;

import bank.Bank;
import bank.Main;
import card.Card;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InternetShop implements ProcessCardAble, ThreeDSecure {

    private Card currentCard;
    private int attemptCvv;
    private Date attemptDate;
    private Scanner scanner;
    Bank bank;

    public InternetShop(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void withdraw(BigDecimal query) {
        if (currentCard != null) {
            if (getClientCheck()) {
                if (haveEnoughSumm(query)) {
                    setCurrentCardBalance(getCurrentCardBalance().subtract(query));
                    System.out.println("Сумма " + query + "списана со счета");
                }
            } else {
                System.out.println("код подтверждения неверен операция отменена");
            }
        }
    }

    @Override
    public void processStopCard() {
        if (currentCard != null) {
            currentCard = null;
            System.out.println("спасибо! Чек будет выслан на ваш e-mail");
        }
    }

    @Override
    public boolean checkValid() {
        setAttemptCvv();
        if(checkCvv()){
            setAttemptDate();
            if(checkDate()){
                return true;
            }
        }
        return false;
    }

    private boolean checkCvv(){
        return getCurrentCard().checkValidByCvv(attemptCvv);
    }

    private boolean checkDate(){
        return getCurrentCard().checkValidByDate(attemptDate);
    }

    @Override
    public void processStartCard(Card card) {
        setCurrentCard(card);
        if(checkValid()) {
            System.out.println("спасибо! ваша карта принята к обслуживанию");
        } else {
            System.out.println("cvv или дата истечения срока карты неверны");
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

    public boolean getClientCheck(){
        int code = manageWithdrawConfirmationQuery();
        Scanner scanner = Main.scanner;
        String codeString = scanner.nextLine();
        if(codeString.equals(String.valueOf(code))){
            return true;
        }
        return false;
    }

    public int manageWithdrawConfirmationQuery() {
        int code = bank.getConfirmationCode();//получаем от банка код безопасности
        System.out.println("you've got the sms: your confirmation code is: "+ code);
        return code;
    }

    private void setAttemptCvv() {
        scanner = new Scanner(System.in);
        System.out.println("введите трехзначный Cvv-код, с обратной стороны карты [код 233]");
        attemptCvv = Integer.parseInt(scanner.nextLine());
    }

    private void setAttemptDate() {
        scanner = Main.scanner;
        System.out.println("введите дату  с карты в формате yyyy-MM-dd, например 2018-12-01 [вчерашняя дата на 3 года вперед от текущей, например 2021-12-03]");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = scanner.nextLine();
        try {
            attemptDate = format.parse(dateString);
        } catch (ParseException e) {
            System.out.println("неправильный формат даты");
            e.printStackTrace();
        }
    }

    private Card getCurrentCard() {
        return currentCard;
    }

    private void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    private BigDecimal getCurrentCardBalance() {
        return currentCard.getBalance(bank);
    }

    private void setCurrentCardBalance(BigDecimal bigDecimal) {
        currentCard.setBalance(bank, bigDecimal);
    }
}