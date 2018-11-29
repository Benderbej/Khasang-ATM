import java.math.BigDecimal;

public interface Card extends  Insertable {

    BigDecimal getBalance();

    void setBalance(BigDecimal bigDecimal);

    void insert();

    void eject();

}
