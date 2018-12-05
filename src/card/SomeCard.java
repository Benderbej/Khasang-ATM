package card;

import bank.Bank;
import bank.BankAccount;
import bank.PaySystem;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class SomeCard extends AbstractCard {

    private Date date;

    public SomeCard(PaySystem paySystem, long cardNumber, int cvv, String pin) throws CardImplementationException {
        super(paySystem, cardNumber, cvv, pin);
    }

    public SomeCard(int cvv, String pin, Date date) throws CardImplementationException {
        this(Bank.getPaySystem(), Bank.getCardNum(), cvv, pin);
        this.date = addThreeYearsToExpiration(date);
    }

    public SomeCard(int cvv, String pin) throws CardImplementationException {
        this(Bank.getPaySystem(), Bank.getCardNum(), cvv, pin);
        date = addThreeYearsToExpiration(getYesterdayDate(new Date()));//use emulation Date getYesterdayDate(new Date())
    }

    @Override
    public Date getExpirationDate() {
        return date;
    }

    @Override
    public boolean checkValidByDate(Date date) {
        //System.out.println(date);
        //System.out.println(trim(this.date));
        if(trim(this.date).equals(date)){
            Date d = new Date();
            if(d.before(date)){
                return true;
            } else {
                System.out.println("срок действия карты истек");
            }
        } else {
            System.out.println("введенная дата не является действующей датой истечения срока годности карты");
        }
        return false;
    }

    private Date addThreeYearsToExpiration(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, 3);
        return c.getTime();
    }

    private Date getYesterdayDate(Date date) {//for emulation only
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        Date dateBefore = cal.getTime();
        return dateBefore;
    }

    private Date trim(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.clear(); // as per BalusC comment.
        cal.setTime( date );
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}