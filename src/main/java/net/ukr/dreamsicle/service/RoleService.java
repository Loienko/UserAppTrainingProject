package net.ukr.dreamsicle.service;

import net.ukr.dreamsicle.dao.imp.RoleDaoImpl;
import net.ukr.dreamsicle.dto.RoleDto;
import net.ukr.dreamsicle.dto.RoleMapper;

import java.util.stream.Collectors;

import static net.ukr.dreamsicle.util.Constants.*;

public class RoleService {

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
        return roleDao.create(roleMapper.fromDto(roleDto)).equals(IDS) ? SORRY_ROLE_NOT_FOUND : ROLE_SUCCESSFULLY_CREATED;
    }

    public String delete(Integer id) {
        return roleDao.delete(id).equals(IDS) ? SORRY_ROLE_NOT_FOUND : ROLE_SUCCESSFULLY_DELETED;
    }

    public String update(Integer id, RoleDto roleDto) {
        return roleDao.update(id, roleMapper.fromDto(roleDto)).equals(IDS) ? SORRY_ROLE_NOT_FOUND : ROLE_SUCCESSFULLY_UPDATED;
    }
}
