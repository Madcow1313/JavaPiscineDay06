package edu.school21.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmbeddedDataSourceTest {

    DataSource dataSource;
    @BeforeEach
    public void init(){
        dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("schema.sql")
                .addScript("data.sql").build();
    }

    @Test
    public void simpleTest() throws SQLException {
        Assertions.assertNotNull(dataSource.getConnection());
    }
}
