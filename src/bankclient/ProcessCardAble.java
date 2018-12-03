package bankclient;

import card.Card;

import java.math.BigDecimal;

public interface ProcessCardAble {

    void withdraw(BigDecimal b);

    void processStartCard(Card card);

    void processStopCard();

    boolean checkValid();

}
