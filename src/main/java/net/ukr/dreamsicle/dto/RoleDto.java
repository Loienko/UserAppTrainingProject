package net.ukr.dreamsicle.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RoleDto {
    private final Integer roleId;
    private final String roleName;
    private final String roleDescription;

    public RoleDto(Integer roleId, String roleName, String roleDescription) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }

    public static RoleDto.RoleDtoBuilder builder() {
        return new RoleDto.RoleDtoBuilder();
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return Objects.equals(roleId, roleDto.roleId) &&
                Objects.equals(roleName, roleDto.roleName) &&
                Objects.equals(roleDescription, roleDto.roleDescription);
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

    public static class RoleDtoBuilder {
        private Integer roleId;
        private String roleName;
        private String roleDescription;

        public RoleDtoBuilder() {
        }

        public RoleDto.RoleDtoBuilder roleId(final Integer roleId) {
            this.roleId = roleId;
            return this;
        }

        public RoleDto.RoleDtoBuilder roleName(final String roleName) {
            this.roleName = roleName;
            return this;
        }

        public RoleDto.RoleDtoBuilder roleDescription(final String roleDescription) {
            this.roleDescription = roleDescription;
            return this;
        }

        public RoleDto build() {
            return new RoleDto(this.roleId, this.roleName, this.roleDescription);
        }

        @Override
        public String toString() {
            return "RoleDtoBuilder{" +
                    "roleId=" + roleId +
                    ", roleName='" + roleName + '\'' +
                    ", roleDescription='" + roleDescription + '\'' +
                    '}';
        }

        public String toJson(RoleDto roleDto) {
            return new StringBuilder()
                    .append("{\n")
                    .append("\t\"role_id\": \"").append(roleDto.getRoleId()).append("\",\n")
                    .append("\t\"role_name\": \"").append(roleDto.getRoleName()).append("\",\n")
                    .append("\t\"role_description\": \"").append(roleDto.getRoleDescription()).append("\" \n")
                    .append("}").toString();
        }

        public RoleDto roleDtoFromJson(String body) {
            List<String> roleDtoParseList = Stream.of(body.split(",\t"))
                    .map(s -> s.strip().replace("\"", "").replace("}", "").split(": ")[1].trim())
                    .collect(Collectors.toList());

            return new RoleDto(null, roleDtoParseList.get(0), roleDtoParseList.get(1));
        }
    }
}
