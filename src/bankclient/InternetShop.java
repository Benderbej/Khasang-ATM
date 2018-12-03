package bankclient;

import card.Card;

import java.math.BigDecimal;
import java.util.Scanner;

public class InternetShop implements ProcessCardAble{

    private Card currentCard;
    private int attemptCvv;
    private String expirationDate;


    @Override
    public void withdraw(BigDecimal b) {

    }

    @Override
    public void processStartCard(Card card) {

    }

    @Override
    public void processStopCard() {

    }

    @Override
    public boolean checkValid() {
        return false;
    }
}