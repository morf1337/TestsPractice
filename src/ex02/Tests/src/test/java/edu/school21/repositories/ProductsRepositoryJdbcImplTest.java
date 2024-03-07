package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {
    private DataSource dataSource;
    private ProductsRepository productsRepository;
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "Laptop Dell XPS 13", 1299L),
            new Product(2L, "Smartphone Samsung Galaxy S21", 899L),
            new Product(3L, "4K Ultra HD Smart TV", 1499L),
            new Product(4L, "Wireless Noise-Canceling Headphones", 199L),
            new Product(5L, "Coffee Maker with Grinder", 79L)
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = EXPECTED_FIND_ALL_PRODUCTS.get(1);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(5L, "Coffee Maker WITHOUT Grinder", 50L);
    final Product EXPECTED_SAVED_PRODUCT = new Product(6L, "Bluetooth Wireless Earbuds", 249L);

    @BeforeEach
    public void init() {
        dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("schema.sql").addScript("data.sql").build();
        productsRepository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    public void findAllTest() {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepository.findAll());
    }

    @Test
    public void findByIdTest() {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsRepository.findById(2L).get());
    }

    @Test
    public void updateTest() {
        productsRepository.update(new Product(5L, "Coffee Maker WITHOUT Grinder", 50L));
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, productsRepository.findById(5L).get());
    }

    @Test
    public void saveTest() {
        productsRepository.save(EXPECTED_SAVED_PRODUCT);
        Assertions.assertEquals(EXPECTED_SAVED_PRODUCT, productsRepository.findById(6L).get());
    }

    @Test
    public void deleteTest() {
        productsRepository.delete(EXPECTED_FIND_BY_ID_PRODUCT.getId());
        Assertions.assertFalse(productsRepository.findById(EXPECTED_FIND_BY_ID_PRODUCT.getId()).isPresent());
    }
}
