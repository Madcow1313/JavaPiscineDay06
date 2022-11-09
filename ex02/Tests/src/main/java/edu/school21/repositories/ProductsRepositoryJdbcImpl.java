package edu.school21.repositories;

import javax.sql.DataSource;
import javax.swing.plaf.OptionPaneUI;
import javax.swing.plaf.nimbus.State;

import edu.school21.models.Product;
import edu.school21.repositories.ProductsRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository{
    private DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {
        List<Product> allProducts = new ArrayList<>();
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM shop.product");
            while (resultSet.next()){
                allProducts.add(new Product(resultSet.getLong(1),
                        resultSet.getString(2), resultSet.getLong(3)));
            }
        }
        catch (SQLException e){
            System.out.println("Something went wrong");
            System.out.println("Exception caught: " + e);
        }

        return allProducts;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Product product;
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        try{
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM shop.product WHERE identifier = " + id);
            if (resultSet.next()){
                product = new Product(resultSet.getLong(1),
                        resultSet.getString(2), resultSet.getLong(3));
                return Optional.of(product);
            }
        }
        catch (SQLException e){
            System.out.println("Something went wrong");
            System.out.println("Exception caught: " + e);
        }
        return Optional.empty();
    }

    @Override
    public void save(Product product) {
        Connection connection;
        Statement statement;

        try{
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.executeQuery("INSERT INTO shop.product (name, price) VALUES ('"
                        + product.getName() + "', '" + product.getPrice() + "')");
        }
        catch (SQLException e){
            System.out.println("Something went wrong");
            System.out.println("Exception caught: " + e);
        }
    }

    @Override
    public void update(Product product) {
        Connection connection;
        PreparedStatement statement;

        try{
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("UPDATE shop.product SET name = ?, price = ? WHERE identifier = ?");
            statement.setString(1, product.getName());
            statement.setLong(2, product.getPrice());
            statement.setLong(3, product.getIdentifier());
            statement.execute();

        }
        catch (SQLException e){
            System.out.println("Something went wrong");
            System.out.println("Exception caught: " + e);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection;
        PreparedStatement statement;

        try{
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("DELETE FROM shop.product WHERE identifier = " + id);
            statement.execute();
        }
        catch (SQLException e){
            System.out.println("Something went wrong");
            System.out.println("Exception caught: " + e);
        }
    }
}
