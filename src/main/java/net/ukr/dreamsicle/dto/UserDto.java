package net.ukr.dreamsicle.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserDto {

    private final Integer id;
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final Integer roleId;

    public UserDto(Integer id, String userName, String firstName, String lastName, Integer roleId) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleId = roleId;
    }

    public static UserDto.UserDtoBuilder builder() {
        return new UserDto.UserDtoBuilder();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) &&
                Objects.equals(userName, userDto.userName) &&
                Objects.equals(firstName, userDto.firstName) &&
                Objects.equals(lastName, userDto.lastName) &&
                Objects.equals(roleId, userDto.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, firstName, lastName, roleId);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roleId=" + roleId +
                '}';
    }

    public static class UserDtoBuilder {
        private Integer id;
        private String userName;
        private String firstName;
        private String lastName;
        private Integer roleId;

        public UserDtoBuilder() {
        }

        public UserDto.UserDtoBuilder id(final Integer id) {
            this.id = id;
            return this;
        }

        public UserDto.UserDtoBuilder userName(final String userName) {
            this.userName = userName;
            return this;
        }

        public UserDto.UserDtoBuilder firstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDto.UserDtoBuilder lastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDto.UserDtoBuilder roleId(final Integer roleId) {
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

        public UserDto build() {
            return new UserDto(this.id, this.userName, this.firstName, this.lastName, this.roleId);
        }

        public String toJson(UserDto userDto) {
            return "{\n" +
                    "\t\"id\": \"" + userDto.id + "\",\n" +
                    "\t\"username\": \"" + userDto.userName + "\",\n" +
                    "\t\"first_name\": \"" + userDto.firstName + "\",\n" +
                    "\t\"last_name\": \"" + userDto.lastName + "\",\n" +
                    "\t\"role\": \"" + userDto.roleId + "\" \n" +
                    "}";
        }

        public UserDto userDtoFromJson(String body) {
            List<String> collect = Stream.of(body.split(",\t"))
                    .map(s -> s.strip().replace("\"", "").replace("}", "").split(": ")[1].trim())
                    .collect(Collectors.toList());

            return new UserDto(null, collect.get(0), collect.get(1), collect.get(2), Integer.parseInt(collect.get(3)));
        }
    }
}
