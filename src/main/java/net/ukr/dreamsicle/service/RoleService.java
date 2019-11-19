package net.ukr.dreamsicle.service;

import net.ukr.dreamsicle.dao.imp.RoleDaoImpl;
import net.ukr.dreamsicle.dto.RoleDto;
import net.ukr.dreamsicle.dto.RoleMapper;

import java.util.stream.Collectors;

public class RoleService {

    private final RoleDaoImpl role = new RoleDaoImpl();
    private final RoleMapper roleMapper = new RoleMapper();

    public String findAll() {
        return roleMapper.findAll(
                role.findAll())
                .stream()
                .map(roleDto -> RoleDto.builder().toJson(roleDto))
                .collect(Collectors.toList())
                .toString();
    }

    public String findById(Integer id) {
        return RoleDto.builder()
                .toJson(roleMapper.toDto(
                        role.findById(id)
                        )
                );
    }
}
