package card;

import bank.Bank;

import java.math.BigDecimal;
import java.util.Date;

public interface Card {

    //boolean currentValidationPassed = false;//данные о последней валидации
    int DEFAULT_CVV  = 555;
    String DEFAULT_PIN = "0000";

    boolean checkValidByCvv(int cvv);

    boolean checkValidByPin(String pin);

    int getCvv();//заставляем сразу определять cvv - насколько правомерен такой прием? так вообще делают? (setter не делаем чтобы была возможность сделать его private)

    String getPin();//заставляем сразу определять pin - насколько правомерен такой прием? так вообще делают? (setter не делаем чтобы была возможность сделать его private)

    Date getExpirationDate();

    boolean checkValidByDate(Date date);

    void setBalance(Bank bank, BigDecimal bigDecimal);

    BigDecimal getBalance(Bank bank);
}