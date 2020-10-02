package com.mmalaenko.repository.impl;

import com.mmalaenko.model.User;
import com.mmalaenko.repository.UserRepository;
import com.mmalaenko.utill.DBConnector;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_SELECT_BY_LOGIN = "SELECT*FROM users WHERE  login=?";
    private static final String SQL_SELECT_ALL_USERS = "SELECT*FROM users";
    private static final String SQL_INSERT_USER = "INSERT INTO users(login,password) VALUES(?,?)";

    @Override
    public Optional<User> getUserByLogin(String login) {
        User user=null;
        try(Connection connection = DBConnector.getConnection()) {
            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_LOGIN)) {
                preparedStatement.setString(1, login);
                final ResultSet resultSet = preparedStatement.executeQuery();
                user = getUser(resultSet);
            }
        } catch (SQLException e) {
           log.error("Error while select from Users, login:{[]}", login,e.getMessage());
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAll() {
        try(Connection connection = DBConnector.getConnection()) {
            try(Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
                return getUsers(resultSet);
            }
        } catch (SQLException e) {
            log.error("Error while select All Users",e.getMessage());
        }
        return  null;
    }

    @Override
    public void save(User user) {
        try(Connection connection=DBConnector.getConnection()){
            try(PreparedStatement preparedStatement=connection.prepareStatement(SQL_INSERT_USER)){
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("Error while INSERT User",e.getMessage());
        }
    }

    private List<User> getUsers(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>() ;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String userLogin = resultSet.getString("login");
            String password = resultSet.getString("password ");
            users.add(User.builder()
            .id(resultSet.getInt("id"))
            .login(resultSet.getString("login"))
            .password(resultSet.getString("password"))
            .build());
        }
        return users;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            return User.builder()
                    .id(resultSet.getInt("id"))
                    .login(resultSet.getString("login"))
                    .password(resultSet.getString("password"))
                    .build();
        }
        return null;
    }

}
