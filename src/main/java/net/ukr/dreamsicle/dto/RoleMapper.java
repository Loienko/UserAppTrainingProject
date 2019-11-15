package net.ukr.dreamsicle.dto;

import net.ukr.dreamsicle.entity.Role;
import net.ukr.dreamsicle.exception.MyOwnException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoleMapper implements Factory<Role, RoleDto> {

    String INPUT_PARAMETER_NOT_FOUND = "Input parameter not found";

    @Override
    public RoleDto toDto(Role role) {
        Optional.ofNullable(role).orElseThrow(() -> new MyOwnException(INPUT_PARAMETER_NOT_FOUND));
        return new RoleDto.RoleDtoBuilder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .roleDescription(role.getRoleDescription())
                .build();
    }

    @Override
    public Role fromDto(RoleDto roleDto) {
        Optional.ofNullable(roleDto).orElseThrow(() -> new MyOwnException(INPUT_PARAMETER_NOT_FOUND));
        return new Role(
                roleDto.getRoleId(),
                roleDto.getRoleName(),
                roleDto.getRoleDescription()
        );
    }

    @Override
    public List<RoleDto> findAll(List<Role> roles) {
        Optional.ofNullable(roles).orElseThrow(() -> new MyOwnException(INPUT_PARAMETER_NOT_FOUND));

        return roles.stream().map(this::toDto).collect(Collectors.toList());
    }
}
