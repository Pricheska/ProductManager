package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.Book;
import ru.netology.Product;
import ru.netology.Smartphone;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    Book book1 = new Book(100, "Demian", 300, "Herman Hesse");
    Book book2 = new Book(010, "Tropic of Cancer", 500, "Henry Miller");
    Book book3 = new Book(001, "First Man", 400, "Alber Camu");

    Smartphone phone1 = new Smartphone(110, "Galaxy s9", 30_000, "Samsung");
    Smartphone phone2 = new Smartphone(111, "Iphone 3g", 10_000, "Apple");
    Smartphone phone3 = new Smartphone(112, "Honor2", 20_000, "Honor");


    @Test
    public void Test() {
        Smartphone phone4 = new Smartphone(121, "Nokia 3310", 100000, "Nokia");

        manager.add(phone4);
        Product[] expected = {book1, book2, book3, phone1, phone2, phone3, phone4};
        Product[] actual = repo.all();

        Assertions.assertArrayEquals(expected, actual);
    }

    @BeforeEach
    public void setup() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(phone1);
        manager.add(phone2);
        manager.add(phone3);
    }


    @Test
    public void ShouldShowAllProducts() {

        Product[] expected = {book1, book2, book3, phone1, phone2, phone3};
        Product[] actual = repo.all();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldRemoveById() {
        repo.removeById(001);
        Product[] expected = {book1, book2, phone1, phone2, phone3};
        Product[] actual = repo.all();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldAddSmartphones() {

        ProductRepository repo = new ProductRepository();
        ProductManager manager = new ProductManager(repo);

        Smartphone phone1 = new Smartphone(100, "Galaxy s9", 30_000, "Samsung");
        Smartphone phone2 = new Smartphone(100, "Iphone 3g", 10_000, "Apple");
        Smartphone phone3 = new Smartphone(100, "Honor2", 20_000, "Honor");

        manager.add(phone1);
        manager.add(phone2);
        manager.add(phone3);


        Product[] expected = {phone1, phone2, phone3};
        Product[] actual = repo.all();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldRemoveSmartphones() {
        repo.removeById(001);

        Product[] expected = {book1, book2, phone1, phone2, phone3};
        Product[] actual = repo.all();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldSearchProductByName() {

        Product[] expected = {phone1};
        Product[] actual = manager.searchByName("Galaxy s9");

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void ShouldSearchBookByName() {

        Product[] expected = {book1};
        Product[] actual = manager.searchByName("Demian");

        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void ShouldGetPrice() {

        int expected = 300;
        int actual = book1.getPrice();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void ShouldGetManufacturer() {

        String expected = "Samsung";
        String actual = phone1.getManufacturer();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void ShoulGetAuthor() {

        String expected = "Herman Hesse";
        String actual = book1.getAuthor();

        Assertions.assertEquals(expected, actual);
    }
}