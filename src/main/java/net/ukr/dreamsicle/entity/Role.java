package net.ukr.dreamsicle.entity;

import net.ukr.dreamsicle.exception.ApplicationException;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static net.ukr.dreamsicle.util.Constants.PROBLEM_OF_WORKING_WITH_THE_DATABASE;

public class Role {
    private final Integer roleId;
    private final String roleName;
    private final String roleDescription;

    private Role(Integer roleId, String roleName, String roleDescription) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }

    public static Role.RoleBuilder builder() {
        return new Role.RoleBuilder();
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId) &&
                Objects.equals(roleName, role.roleName) &&
                Objects.equals(roleDescription, role.roleDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, roleDescription);
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public static class RoleBuilder {
        private static final Logger LOGGER = Logger.getLogger(RoleBuilder.class);
        private Integer roleId;
        private String roleName;
        private String roleDescription;

        public RoleBuilder() {
        }

        public Role.RoleBuilder roleId(final Integer roleId) {
            this.roleId = roleId;
            return this;
        }

        public Role.RoleBuilder roleName(final String roleName) {
            this.roleName = roleName;
            return this;
        }

        public Role.RoleBuilder roleDescription(final String roleDescription) {
            this.roleDescription = roleDescription;
            return this;
        }

        public Role build() {
            return new Role(this.roleId, this.roleName, this.roleDescription);
        }

        @Override
        public String toString() {
            return "RoleDtoBuilder{" +
                    "roleId=" + roleId +
                    ", roleName='" + roleName + '\'' +
                    ", roleDescription='" + roleDescription + '\'' +
                    '}';
        }

        public Role transformFromResultSetToRole(ResultSet resultSet) {
            try {
                return new Role(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            } catch (SQLException e) {
                LOGGER.error(PROBLEM_OF_WORKING_WITH_THE_DATABASE + e.getMessage(), e);
                throw new ApplicationException(PROBLEM_OF_WORKING_WITH_THE_DATABASE + e.getMessage(), e);
            }
        }
    }
}
