package flindgren.potter;

import org.junit.Test;

import static flindgren.potter.Book.*;
import static flindgren.potter.Package.BASE_PRICE;
import static org.assertj.core.api.Assertions.assertThat;

public class PotterTest {

    @Test
    public void none() {
        assertThat(BookOrder.price()).isEqualTo(0);
    }

    @Test
    public void singleBook() {
        BookOrder bookOrder4 = new BookOrder(new Book[]{Book.ONE});
        assertThat(bookOrder4.price()).isEqualTo(BASE_PRICE);
        BookOrder bookOrder3 = new BookOrder(new Book[]{TWO});
        assertThat(bookOrder3.price()).isEqualTo(BASE_PRICE);
        BookOrder bookOrder2 = new BookOrder(new Book[]{THREE});
        assertThat(bookOrder2.price()).isEqualTo(BASE_PRICE);
        BookOrder bookOrder1 = new BookOrder(new Book[]{FOUR});
        assertThat(bookOrder1.price()).isEqualTo(BASE_PRICE);
        BookOrder bookOrder = new BookOrder(new Book[]{FIVE});
        assertThat(bookOrder.price()).isEqualTo(BASE_PRICE);
    }

    @Test
    public void multipleSimilar() {
        BookOrder bookOrder1 = new BookOrder(new Book[]{ONE, ONE});
        assertThat(bookOrder1.price()).isEqualTo(BASE_PRICE * 2);
        BookOrder bookOrder = new BookOrder(new Book[]{TWO, TWO, TWO});
        assertThat(bookOrder.price()).isEqualTo(BASE_PRICE * 3);
    }

    @Test
    public void twoBooksDiscount() {
        BookOrder bookOrder = new BookOrder(new Book[]{ONE, TWO});
        assertThat(bookOrder.price()).isEqualTo(BASE_PRICE * 2 * Package.TWO_DISCOUNT);
    }

    @Test
    public void threeBooksDiscount() {
        BookOrder bookOrder = new BookOrder(new Book[]{ONE, TWO, THREE});
        assertThat(bookOrder.price()).isEqualTo(BASE_PRICE * 3 * Package.THREE_DISCOUNT);
    }

    @Test
    public void fourBooksDiscount() {
        BookOrder bookOrder = new BookOrder(new Book[]{ONE, TWO, THREE, FOUR});
        assertThat(bookOrder.price()).isEqualTo(BASE_PRICE * 4 * Package.FOUR_DISCOUNT);
    }

    @Test
    public void fiveBooksDiscount() {
        BookOrder bookOrder = new BookOrder(new Book[]{ONE, TWO, THREE, FOUR, FIVE});
        assertThat(bookOrder.price()).isEqualTo(BASE_PRICE * 5 * Package.FIVE_DISCOUNT);
    }

    @Test
    public void severalDiscounts() {
        BookOrder bookOrder2 = new BookOrder(new Book[]{ONE, ONE, TWO});
        assertThat(bookOrder2.price()).isEqualTo(BASE_PRICE + (BASE_PRICE * 2 * Package.TWO_DISCOUNT));
        BookOrder bookOrder1 = new BookOrder(new Book[]{ONE, ONE, TWO, TWO});
        assertThat(bookOrder1.price()).isEqualTo(2 * (BASE_PRICE * 2 * Package.TWO_DISCOUNT));
        BookOrder bookOrder = new BookOrder(new Book[]{ONE, ONE, TWO, THREE, THREE, FOUR});
        assertThat(bookOrder.price()).isEqualTo((4 * BASE_PRICE * Package.FOUR_DISCOUNT) + (BASE_PRICE * 2 * Package.TWO_DISCOUNT));
    }

    @Test
    public void edgeCase() {
        BookOrder bookOrder = new BookOrder(new Book[]{ONE, ONE, TWO, TWO, THREE, THREE, FOUR, FIVE});
        assertThat(bookOrder.price()).isEqualTo(2 * (BASE_PRICE * 4 * Package.FOUR_DISCOUNT));
    }

    @Test
    public void edgeCase2() {
        BookOrder bookOrder = new BookOrder(new Book[]{ONE,
                                                       ONE,
                                                       ONE,
                                                       ONE,
                                                       ONE,
                                                       TWO,
                                                       TWO,
                                                       TWO,
                                                       TWO,
                                                       TWO,
                                                       THREE,
                                                       THREE,
                                                       THREE,
                                                       THREE,
                                                       FOUR,
                                                       FOUR,
                                                       FOUR,
                                                       FOUR,
                                                       FOUR,
                                                       FIVE,
                                                       FIVE,
                                                       FIVE,
                                                       FIVE});
        assertThat(bookOrder.price())
            .isEqualTo((3 * BASE_PRICE * 5 * Package.FIVE_DISCOUNT) + (2 * BASE_PRICE * 4 * Package.FOUR_DISCOUNT));
    }
}
