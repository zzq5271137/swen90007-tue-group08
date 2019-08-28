package domain;

public class Book {
    private String isbn;
    private String author;
    private String title;
    private float price;
    private int copies;

    public Book() {
    }

    public Book(String isbn, String author, String title, float price, int copies) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.price = price;
        this.setCopies(copies);
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    public int getCopies() {
        return copies;
    }
}
