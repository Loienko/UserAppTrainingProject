package net.ukr.dreamsicle.entity;

import java.util.Objects;

public class User {
    private final Integer id;
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final Integer roleId;

    public User(String userName, String firstName, String lastName, Integer roleId) {
        this(null, userName, firstName, lastName, roleId);
    }

    public User(Integer id, String userName, String firstName, String lastName, Integer roleId) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleId = roleId;
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
}
