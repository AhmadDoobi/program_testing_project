package com.bookstore;

import com.bookstore.model.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testBookConstructorAndGetters() {
        Book book = new Book(1L, "Java Programming", "Author Name", 49.99, 10);

        assertEquals(1L, book.getId());
        assertEquals("Java Programming", book.getTitle());
        assertEquals("Author Name", book.getAuthor());
        assertEquals(49.99, book.getPrice());
        assertEquals(10, book.getQuantity());
    }

    @Test
    void testSetters() { 
        Book book = new Book();
        book.setId(2L);
        book.setTitle("Spring Framework");
        book.setAuthor("Author");
        book.setPrice(29.99);
        book.setQuantity(5);

        assertEquals(2L, book.getId());
        assertEquals("Spring Framework", book.getTitle());
        assertEquals("Author", book.getAuthor());
        assertEquals(29.99, book.getPrice());
        assertEquals(5, book.getQuantity());
    }

    @Test
    void testPriceValidation() {
        Book book = new Book();

        assertThrows(IllegalArgumentException.class, () -> book.setPrice(-10.0));
    }

    @Test
    void testQuantityValidation() {
        Book book = new Book();

        assertThrows(IllegalArgumentException.class, () -> book.setQuantity(-5));
    }

    @Test
    void testToString() {
        Book book = new Book(1L, "Java Programming", "Author Name", 49.99, 10);
        String expected = "Book{id=1, title='Java Programming', author='Author Name', price=49.99, quantity=10}";

        assertEquals(expected, book.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Book book1 = new Book(1L, "Java Programming", "Author Name", 49.99, 10);
        Book book2 = new Book(1L, "Java Programming", "Author Name", 49.99, 10);
        Book book3 = new Book(2L, "Spring Framework", "Another Author", 29.99, 5);

        assertEquals(book1, book2);
        assertNotEquals(book1, book3);
        assertEquals(book1.hashCode(), book2.hashCode());
        assertNotEquals(book1.hashCode(), book3.hashCode());
    }
}
