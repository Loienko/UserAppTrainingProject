package net.ukr.dreamsicle.entity;

import net.ukr.dreamsicle.exception.ApplicationException;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class User implements DreamsicleUtilEntity {
    private final Integer id;
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final Integer roleId;

    public User(Integer id, String userName, String firstName, String lastName, Integer roleId) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleId = roleId;
    }

    public static User.UserBuilder builder() {
        return new User.UserBuilder();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roleId=" + roleId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(roleId, user.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, firstName, lastName, roleId);
    }

    public Integer getId() {
        return id;
    }


    public String getUserName() {
        return userName;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public Integer getRoleId() {
        return roleId;
    }

    public static class UserBuilder {
        private static final Logger LOGGER = Logger.getLogger(User.UserBuilder.class);
        private Integer id;
        private String userName;
        private String firstName;
        private String lastName;
        private Integer roleId;

        public UserBuilder() {
        }

        public User.UserBuilder id(final Integer id) {
            this.id = id;
            return this;
        }

        public User.UserBuilder userName(final String userName) {
            this.userName = userName;
            return this;
        }

        public User.UserBuilder firstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public User.UserBuilder lastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public User.UserBuilder roleId(final Integer roleId) {
            this.roleId = roleId;
            return this;
        }

        @Override
        public String toString() {
            return "UserDtoBuilder{" +
                    "id=" + id +
                    ", userName='" + userName + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", roleId=" + roleId +
                    '}';
        }

        public User build() {
            return new User(this.id, this.userName, this.firstName, this.lastName, this.roleId);
        }

        public User transformFromResultSetToUser(ResultSet resultSet) {
            try {
                return new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                );
            } catch (SQLException e) {
                LOGGER.error(PROBLEM_OF_WORKING_WITH_THE_DATABASE + e.getMessage(), e);
                throw new ApplicationException(PROBLEM_OF_WORKING_WITH_THE_DATABASE + e.getMessage(), e);
            }
        }
    }
}
