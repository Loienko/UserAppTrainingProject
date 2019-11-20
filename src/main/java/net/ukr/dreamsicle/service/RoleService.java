package net.ukr.dreamsicle.service;

import net.ukr.dreamsicle.dao.imp.RoleDaoImpl;
import net.ukr.dreamsicle.dto.RoleDto;
import net.ukr.dreamsicle.dto.RoleMapper;

import java.util.stream.Collectors;

public class RoleService {

    private static final String SUCCESSFULLY_CREATED = "Role successfully created";
    private static final String SORRY_ROLE_NOT_CREATED = "Sorry, role not created";
    private static final String ROLE_SUCCESSFULLY_UPDATED = "Role successfully updated";
    private static final String SORRY_ROLE_NOT_UPDATED = "Sorry, role not updated";
    private static final String SORRY_ROLE_NOT_DELETED = "Sorry, role not deleted";
    private static final String ROLE_SUCCESSFULLY_DELETED = "Role successfully deleted";
    private static final Integer IDS = 0;

    private final RoleDaoImpl roleDao = new RoleDaoImpl();
    private final RoleMapper roleMapper = new RoleMapper();

    public String findAll() {
        return roleMapper.findAll(
                roleDao.findAll())
                .stream()
                .map(roleDto -> RoleDto.builder().toJson(roleDto))
                .collect(Collectors.toList())
                .toString();
    }

    public String findById(Integer id) {
        return RoleDto.builder()
                .toJson(roleMapper.toDto(
                        roleDao.findById(id)
                        )
                );
    }

    public String create(RoleDto roleDto) {
        return roleDao.create(roleMapper.fromDto(roleDto)).equals(IDS) ? SORRY_ROLE_NOT_CREATED : SUCCESSFULLY_CREATED;
    }
}
