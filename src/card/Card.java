package card;

import java.math.BigDecimal;

public interface Card {

    //boolean currentValidationPassed = false;//данные о последней валидации

    int DEFAULT_CVV  = 555;
    String DEFAULT_PIN = "0000";

    boolean checkValidByCvv(int cvv);

    boolean checkValidByPin(String pin);

    BigDecimal getBalance();

    void setBalance(BigDecimal bigDecimal);

    void insert();

    void eject();

    int getCvv();//заставляем сразу определять cvv - насколько правомерен такой прием? так вообще делают? (setter не делаем чтобы была возможность сделать его private)

    String getPin();//заставляем сразу определять pin - насколько правомерен такой прием? так вообще делают? (setter не делаем чтобы была возможность сделать его private)

}