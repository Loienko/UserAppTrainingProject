package net.ukr.dreamsicle.dao.imp;

import net.ukr.dreamsicle.connection.ConnectionManager;
import net.ukr.dreamsicle.dao.Dao;
import net.ukr.dreamsicle.entity.Role;
import net.ukr.dreamsicle.exception.MyOwnException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements Dao<Role> {

    public static final String PROBLEM_OF_WORKING_WITH_THE_DATABASE = "Sorry, problem of working with the database";

    @Override
    public Role findById(Integer id) {
        var sqlQuery = "SELECT * FROM users.public.role WHERE role_id = ?";
        Role role = null;

        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sqlQuery)) {
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    role = new Role(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    );
                }
            }
        } catch (SQLException e) {
            throw new MyOwnException(PROBLEM_OF_WORKING_WITH_THE_DATABASE, e);
        }
        return role;
    }

    @Override
    public List<Role> findAll() {
        List<Role> list = new ArrayList<>();
        var sqlQuery = "SELECT * FROM users.public.role ORDER BY role_id";
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sqlQuery)) {
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(new Role(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    ));
                }
            }
        } catch (SQLException e) {
            throw new MyOwnException(PROBLEM_OF_WORKING_WITH_THE_DATABASE, e);
        }
        return list;
    }

    @Override
    public Integer create(Role role) {
        var sqlQuery = "INSERT INTO users.public.role(role_name, role_description) VALUES (?, ?)";
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.setString(2, role.getRoleDescription());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new MyOwnException(PROBLEM_OF_WORKING_WITH_THE_DATABASE, e);
        }
    }

    @Override
    public Integer delete(Integer id) {
        var sqlQuery = "DELETE FROM users.public.role WHERE role_id = ?";
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new MyOwnException(PROBLEM_OF_WORKING_WITH_THE_DATABASE, e);
        }
    }

    @Override
    public Integer update(Integer id, Role role) {
        var sqlQuery = "Update users.public.role set role_name = ?, role_description = ? WHERE role_id = ?";

        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.setString(2, role.getRoleDescription());
            preparedStatement.setInt(3, id);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new MyOwnException(PROBLEM_OF_WORKING_WITH_THE_DATABASE, e);
        }
    }
}
