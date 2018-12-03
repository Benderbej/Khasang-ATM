package card;

import java.math.BigDecimal;

public interface Card {

    //boolean currentValidationPassed = false;//данные о последней валидации

    int getCvv();//заставляем сразу определять cvv - насколько правомерен такой прием? так вообще делают? (setter не делаем чтобы была возможность сделать его private)

    int getPin();//заставляем сразу определять cvv - насколько правомерен такой прием? так вообще делают? (setter не делаем чтобы была возможность сделать его private)

    boolean checkValidByCvv(int cvv);

    boolean checkValidByPin(int pin);

    BigDecimal getBalance();

    void setBalance(BigDecimal bigDecimal);

    void insert();

    void eject();
}