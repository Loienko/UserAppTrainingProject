package net.ukr.dreamsicle.dto;

import net.ukr.dreamsicle.entity.Role;
import net.ukr.dreamsicle.exception.DreamsicleException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoleMapper implements Factory<Role, RoleDto>, DreamsicleUtilDto {

    private static final String ROLE = "Role";

    @Override
    public RoleDto toDto(Role role) {
        Optional.ofNullable(role).orElseThrow(() -> new DreamsicleException(String.format(INPUT_PARAMETER_NOT_FOUND, ROLE), 404));
        return new RoleDto.RoleDtoBuilder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .roleDescription(role.getRoleDescription())
                .build();
    }

    @Override
    public Role fromDto(RoleDto roleDto) {
        Optional.ofNullable(roleDto).orElseThrow(() -> new DreamsicleException(String.format(INPUT_PARAMETER_NOT_FOUND, ROLE), 404));
        return Role.builder()
                .roleId(roleDto.getRoleId())
                .roleName(roleDto.getRoleName())
                .roleDescription(roleDto.getRoleDescription())
                .build();
    }

    @Override
    public List<RoleDto> findAll(List<Role> roles) {
        Optional.ofNullable(roles).orElseThrow(() -> new DreamsicleException(String.format(INPUT_PARAMETER_NOT_FOUND, ROLE), 404));

        return roles.stream().map(this::toDto).collect(Collectors.toList());
    }
}
