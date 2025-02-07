package net.ukr.dreamsicle.dao.imp;

import net.ukr.dreamsicle.dao.AbstractDao;
import net.ukr.dreamsicle.dao.Dao;
import net.ukr.dreamsicle.entity.Role;
import net.ukr.dreamsicle.exception.ThrowingFunction;

import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends AbstractDao implements Dao<Role> {

    private static final String DELETE = "DELETE FROM users.public.role WHERE role_id = ?";
    private static final String UPDATE = "Update users.public.role set role_name = ?, role_description = ? WHERE role_id = ?";
    private static final String CREATE = "INSERT INTO users.public.role(role_name, role_description) VALUES (?, ?)";
    private static final String FIND_ALL = "SELECT * FROM users.public.role ORDER BY role_id";
    private static final String FIND_BY_ID = "SELECT * FROM users.public.role WHERE role_id = ?";

    @Override
    public Role findById(Integer id) {

        return executeStatement(() -> FIND_BY_ID, ThrowingFunction.unchecked(preparedStatement -> {
                    preparedStatement.setInt(1, id);
                    Role role = null;
                    try (var resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            role = Role.builder().transformFromResultSetToRole(resultSet);
                        }
                    }
                    return role;
                })
        );
    }

    @Override
    public List<Role> findAll() {
        List<Role> list = new ArrayList<>();

        return executeStatement(() -> FIND_ALL, ThrowingFunction.unchecked(preparedStatement -> {
                    try (var resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            list.add(Role.builder().transformFromResultSetToRole(resultSet));
                        }
                    }
                    return list;
                })
        );
    }

    @Override
    public Integer create(Role role) {
        return executeStatement(() -> CREATE, ThrowingFunction.unchecked(preparedStatement -> {
                    preparedStatement.setString(1, role.getRoleName());
                    preparedStatement.setString(2, role.getRoleDescription());

                    return preparedStatement.executeUpdate();
                })
        );
    }

    @Override
    public Integer delete(Integer id) {
        return executeStatement(() -> DELETE, ThrowingFunction.unchecked(preparedStatement -> {
                    preparedStatement.setInt(1, id);

                    return preparedStatement.executeUpdate();
                })
        );
    }

    @Override
    public Integer update(Integer id, Role role) {
        return executeStatement(() -> UPDATE, ThrowingFunction.unchecked(preparedStatement -> {
                    preparedStatement.setString(1, role.getRoleName());
                    preparedStatement.setString(2, role.getRoleDescription());
                    preparedStatement.setInt(3, id);

                    return preparedStatement.executeUpdate();
                })
        );
    }
}
