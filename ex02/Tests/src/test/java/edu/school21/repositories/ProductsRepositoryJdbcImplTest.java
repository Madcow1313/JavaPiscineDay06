package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImplTest {
    private ProductsRepositoryJdbcImpl repositoryJdbc;
    private DataSource dataSource;

    List<Product> ALL = Arrays.asList(
            new Product(0L, "phone", 10000L),
            new Product(1L, "car", 4111110L),
            new Product(2L, "chocolate bar", 100L),
            new Product(3L, "toothbrush", 250L),
            new Product(4L, "pen", 50L)
    );
    List<Product> DELETED_FIRST = Arrays.asList(
            new Product(1L, "car", 4111110L),
            new Product(2L, "chocolate bar", 100L),
            new Product(3L, "toothbrush", 250L),
            new Product(4L, "pen", 50L)
    );
    @BeforeEach
    public void init(){
        dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        repositoryJdbc = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    public void findAllTest(){
        Assertions.assertEquals(ALL, repositoryJdbc.findAll());
    }

    @Test
    public void findByIdTest() throws SQLException {
        Long id = 2L;

        Assertions.assertEquals(new Product(id, "chocolate bar", 100L), repositoryJdbc.findById(id).get());
        Assertions.assertEquals(Optional.empty(), repositoryJdbc.findById(10000L));
    }

    @Test
    public void updateTest() throws SQLException{
        Long id = 2L;
        Product product = new Product(id, "icecream", 150L);
        repositoryJdbc.update(product);
        Assertions.assertEquals(product, repositoryJdbc.findById(id).get());
        Assertions.assertEquals(Optional.empty(), repositoryJdbc.findById(null));
    }

    @Test
    public void saveTest() throws SQLException{
        Product product = new Product(5L, "bubblegum", 1L);
        repositoryJdbc.save(product);
        Assertions.assertEquals(product, repositoryJdbc.findById(5L).get());
    }

    @Test
    public void deleteTest() throws SQLException{
        repositoryJdbc.delete(0L);
        Assertions.assertEquals(DELETED_FIRST, repositoryJdbc.findAll());
        Assertions.assertFalse(repositoryJdbc.findById(0L).isPresent());
    }


}
