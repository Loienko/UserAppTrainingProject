package net.ukr.dreamsicle.service;

import net.ukr.dreamsicle.dao.imp.RoleDaoImpl;
import net.ukr.dreamsicle.dto.RoleDto;
import net.ukr.dreamsicle.dto.RoleMapper;

import java.util.stream.Collectors;

public class RoleService implements DreamsicleUtilService {

    private static final String ROLE = "Role";

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
        return roleDao.create(roleMapper.fromDto(roleDto)).equals(IDS) ? String.format(SORRY_ENTITY_NOT_FOUND, ROLE) : String.format(SUCCESSFULLY_CREATED, ROLE);
    }

    public String delete(Integer id) {
        return roleDao.delete(id).equals(IDS) ? String.format(SORRY_ENTITY_NOT_FOUND, ROLE) : String.format(SUCCESSFULLY_DELETED, ROLE);
    }

    public String update(Integer id, RoleDto roleDto) {
        return roleDao.update(id, roleMapper.fromDto(roleDto)).equals(IDS) ? String.format(SORRY_ENTITY_NOT_FOUND, ROLE) : String.format(SUCCESSFULLY_UPDATED, ROLE);
    }
}
