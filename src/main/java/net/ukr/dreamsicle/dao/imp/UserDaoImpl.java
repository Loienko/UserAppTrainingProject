package net.ukr.dreamsicle.dao.imp;

import net.ukr.dreamsicle.connection.ConnectionManager;
import net.ukr.dreamsicle.dao.Dao;
import net.ukr.dreamsicle.entity.User;
import net.ukr.dreamsicle.exception.MyOwnException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static net.ukr.dreamsicle.dao.imp.RoleDaoImpl.PROBLEM_OF_WORKING_WITH_THE_DATABASE;

public class UserDaoImpl implements Dao<User> {

    @Override
    public User findById(Integer id) {
        var sqlQuery = "SELECT * FROM users.public.user WHERE id = ?";
        User user = null;

        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sqlQuery)) {
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = new User(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)
                    );
                }
            }
        } catch (SQLException e) {
            throw new MyOwnException(PROBLEM_OF_WORKING_WITH_THE_DATABASE, e);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        var sqlQuery = "SELECT * FROM users.public.user ORDER BY id";
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sqlQuery)) {
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(new User(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)
                    ));
                }
            }
        } catch (SQLException e) {
            throw new MyOwnException(PROBLEM_OF_WORKING_WITH_THE_DATABASE, e);
        }
        return list;
    }

    @Override
    public Integer create(User user) {
        var sqlQuery = "INSERT INTO users.public.user(username, first_name, last_name, role_id) VALUES (?, ?, ?, ?)";
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setInt(4, user.getRoleId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new MyOwnException(PROBLEM_OF_WORKING_WITH_THE_DATABASE, e);
        }
    }

    @Override
    public Integer delete(Integer id) {
        var sqlQuery = "DELETE FROM users.public.user WHERE id = ?";
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new MyOwnException(PROBLEM_OF_WORKING_WITH_THE_DATABASE, e);
        }
    }

    @Override
    public Integer update(Integer id, User user) {
        var sqlQuery = "Update users.public.user set username = ?, first_name = ?, last_name = ?, role_id =? WHERE id = ?";

        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setInt(4, user.getRoleId());
            preparedStatement.setInt(5, id);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new MyOwnException(PROBLEM_OF_WORKING_WITH_THE_DATABASE, e);
        }
    }
}
