package flindgren.potter;

import java.util.HashSet;
import java.util.Set;

public class Package {
    public static final int BASE_PRICE = 8;
    public static final double TWO_DISCOUNT = 0.95;
    public static final double THREE_DISCOUNT = 0.9;
    public static final double FOUR_DISCOUNT = 0.80;
    public static final double FIVE_DISCOUNT = 0.75;
    private Set<Book> books = new HashSet<>();


    public boolean accept(Book book) {
        return books.add(book);
    }

    public void balance(Package other){
        if (shouldBalance(other)){
            Set<Book> candidates = new HashSet<>(books);
            candidates.removeAll(other.books);
            Book move = candidates.iterator().next();
            other.accept(move);
            books.remove(move);
        }
    }

    private boolean shouldBalance(Package other){
        return size() == 5 && other.size() == 3;
    }

    private int size(){
        return books.size();
    }

    public double price() {
        return BASE_PRICE * discountFactor() * books.size();
    }

    private double discountFactor() {
        switch (books.size()) {
            case 2:
                return Package.TWO_DISCOUNT;
            case 3:
                return Package.THREE_DISCOUNT;
            case 4:
                return Package.FOUR_DISCOUNT;
            case 5:
                return Package.FIVE_DISCOUNT;
            default:
                return 1;
        }

    }

}
