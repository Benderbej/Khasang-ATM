package card;

public class CardImplementationException extends Exception {

    CardImplementationException() {
        super("Card Interface is not correctly implemented");
    }

    CardImplementationException(String s) {
        super(s);
    }
}
