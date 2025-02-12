package net.ukr.dreamsicle.dao.imp;

import net.ukr.dreamsicle.dao.AbstractDao;
import net.ukr.dreamsicle.dao.Dao;
import net.ukr.dreamsicle.entity.User;
import net.ukr.dreamsicle.exception.ThrowingFunction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements Dao<User> {

    private static final String FIND_ALL = "SELECT * FROM users.public.user ORDER BY id";
    private static final String CREATE = "INSERT INTO users.public.user(username, first_name, last_name, role_id) VALUES (?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM users.public.user WHERE id = ?";
    private static final String DELETE = "DELETE FROM users.public.user WHERE id = ?";
    private static final String UPDATE = "Update users.public.user set username = ?, first_name = ?, last_name = ?, role_id =? WHERE id = ?";

    @Override
    public User findById(Integer id) {
        return executeStatement(() -> FIND_BY_ID, ThrowingFunction.unchecked(preparedStatement -> {
                    preparedStatement.setInt(1, id);
                    User user = null;
                    try (var resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            user = User.builder().transformFromResultSetToUser(resultSet);
                        }
                    }
                    return user;
                })
        );
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();

        return executeStatement(() -> FIND_ALL, ThrowingFunction.unchecked(preparedStatement -> {
                    try (var resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            list.add(User.builder().transformFromResultSetToUser(resultSet));
                        }
                    }
                    return list;
                })
        );
    }

    public Integer create(User user) {
        return executeStatement(() -> CREATE, ThrowingFunction.unchecked(preparedStatement -> {
                    preparedStatement.setString(1, user.getUserName());
                    preparedStatement.setString(2, user.getFirstName());
                    preparedStatement.setString(3, user.getLastName());
                    preparedStatement.setInt(4, user.getRoleId());

                    return preparedStatement.executeUpdate();
                })
        );
    }

    public Integer delete(Integer id) {
        return executeStatement(() -> DELETE, ThrowingFunction.unchecked(preparedStatement -> {
                    preparedStatement.setInt(1, id);

                    return preparedStatement.executeUpdate();
                })
        );
    }

    public Integer update(Integer id, User user) {
        return executeStatement(() -> UPDATE, ThrowingFunction.unchecked(preparedStatement -> {
                    preparedStatement.setString(1, user.getUserName());
                    preparedStatement.setString(2, user.getFirstName());
                    preparedStatement.setString(3, user.getLastName());
                    preparedStatement.setInt(4, user.getRoleId());
                    preparedStatement.setInt(5, id);

                    return preparedStatement.executeUpdate();
                })
        );
    }
}
