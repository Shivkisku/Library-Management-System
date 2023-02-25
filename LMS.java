import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private boolean available;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Library {
    private ArrayList<Book> books;
    private Map<Integer, String> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void addUser(int userId, String name) {
        users.put(userId, name);
    }

    public void removeUser(int userId) {
        users.remove(userId);
    }

    public void lendBook(int bookId, int userId) {
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is already lent.");
            return;
        }

        String userName = users.get(userId);
        if (userName == null) {
            System.out.println("User not found.");
            return;
        }

        book.setAvailable(false);
        System.out.println("Book lent to " + userName);
    }

    public void returnBook(int bookId) {
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (book.isAvailable()) {
            System.out.println("Book is not lent.");
            return;
        }

        book.setAvailable(true);
        System.out.println("Book returned.");
    }

    private Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        // Add some books
        library.addBook(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"));
        library.addBook(new Book(2, "To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book(3, "1984", "George Orwell"));

        // Add some users
        library.addUser(1, "Alice");
        library.addUser(2, "Bob");

        // Perform some actions
        library.lendBook(1, 1); // lend book 1 to user 1
        library.lendBook(1, 2); // lend book 1 to user 2 (should fail)
        library.returnBook(2); // try to return book 2 (should fail)

        scanner.close();
    }
}
