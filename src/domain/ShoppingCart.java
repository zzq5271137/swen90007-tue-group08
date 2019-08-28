package domain;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Book, Integer> bookCopies;

    public ShoppingCart() {
        this.bookCopies = new HashMap<>();
    }

    public void setBookCopies(Map<Book, Integer> bookCopies) {
        this.bookCopies = bookCopies;
    }

    public Map<Book, Integer> getBookCopies() {
        return bookCopies;
    }

    public void addBook(Book book) {
        bookCopies.put(book, 1);
    }
}
