package net.ukr.dreamsicle.dto;

import net.ukr.dreamsicle.entity.Role;
import net.ukr.dreamsicle.exception.ApplicationException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoleMapper implements Factory<Role, RoleDto> {

    private static final String INPUT_PARAMETER_FOR_ROLE_NOT_FOUND = "Input parameter for Role not found";

    @Override
    public RoleDto toDto(Role role) {
        Optional.ofNullable(role).orElseThrow(() -> new ApplicationException(INPUT_PARAMETER_FOR_ROLE_NOT_FOUND));
        return new RoleDto.RoleDtoBuilder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .roleDescription(role.getRoleDescription())
                .build();
    }

    @Override
    public Role fromDto(RoleDto roleDto) {
        Optional.ofNullable(roleDto).orElseThrow(() -> new ApplicationException(INPUT_PARAMETER_FOR_ROLE_NOT_FOUND));
        return Role.builder()
                .roleId(roleDto.getRoleId())
                .roleName(roleDto.getRoleName())
                .roleDescription(roleDto.getRoleDescription())
                .build();
    }

    @Override
    public List<RoleDto> findAll(List<Role> roles) {
        Optional.ofNullable(roles).orElseThrow(() -> new ApplicationException(INPUT_PARAMETER_FOR_ROLE_NOT_FOUND));

        return roles.stream().map(this::toDto).collect(Collectors.toList());
    }
}
