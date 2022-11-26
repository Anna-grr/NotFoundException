package ru.netology.product.exception;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ru.netology.product.data.Book;
import ru.netology.product.data.Product;
import ru.netology.product.data.Smartphone;
import ru.netology.product.manager.ProductManager;
import ru.netology.product.repository.Repository;

public class ExceptionTest {

    Repository repository = new Repository();
    ProductManager manager = new ProductManager(repository);

    Product book1 = new Book(1, "Harry Potter 1", 499, "J.K.Rowling");
    Product smartphone1 = new Smartphone(2, "Galaxy S22", 70000, "Samsung");
    Product book2 = new Book(3, "Harry Potter 2", 599, "J.K.Rowling");
    Product book3 = new Book(4, "Harry Potter 3", 699, "J.K.Rowling");
    Product smartphone2 = new Smartphone(5, "iPhone 13", 60000, "Apple");

    @Test
    public void ShouldRemoveByExistingId() {
        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone2);

        manager.removeById(3);

        Product[] expected = {book1, smartphone1, book3, smartphone2};
        Product[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldGenNotFoundExceptionIfIdDoesNotExist() {
        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone2);

        assertThrows(NotFoundException.class, () -> {
            manager.removeById(9);
        });
    }

    @Test
    public void ShouldAddNewProduct() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);

        Product[] expected = {book1, book2, book3};
        Product[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldNotAddNewProductIfIdAlreadyExists() {
        Product smartphone3 = new Smartphone(6, "iPhone 13 blue", 65000, "Apple");
        Product smartphone4 = new Smartphone(6, "iPhone 13 black", 65000, "Apple");

        manager.add(smartphone3);

        assertThrows(AlreadyExistsException.class, () -> {
            manager.add(smartphone4);
        });
    }
}
