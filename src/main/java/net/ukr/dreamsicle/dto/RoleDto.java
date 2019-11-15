package net.ukr.dreamsicle.dto;

import java.util.Objects;

public class RoleDto {
    private Integer roleId;
    private String roleName;
    private String roleDescription;

    public RoleDto() {
    }

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

    public void setRoleId(final Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(final String roleDescription) {
        this.roleDescription = roleDescription;
    }

    static class RoleDtoBuilder {
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
    }
}
