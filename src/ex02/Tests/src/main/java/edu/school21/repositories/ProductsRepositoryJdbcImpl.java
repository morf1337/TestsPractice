package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        String query = "select * from store.product;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Product newProduct = new Product(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getLong(3));
                productList.add(newProduct);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return productList;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Product product = null;
        String query = "select * from store.product where id = " + id;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                product = new Product(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getLong(3));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(product);
    }

    @Override
    public void update(Product product) {
        String query = "update store.product set name = ?, price = ? where id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, product.getName());
            statement.setLong(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void save(Product product) {
        String query = "insert into store.product (name, price) values (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, product.getName());
            statement.setLong(2, product.getPrice());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        String query = "delete from store.product where id = " + id;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);) {
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
