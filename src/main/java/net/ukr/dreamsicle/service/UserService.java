package net.ukr.dreamsicle.service;

import net.ukr.dreamsicle.dao.imp.UserDaoImpl;
import net.ukr.dreamsicle.dto.UserDto;
import net.ukr.dreamsicle.dto.UserMapper;

import java.util.stream.Collectors;

public class UserService implements DreamsicleUtilService {

    private static final String USER = "User";
    private final UserDaoImpl userDao = new UserDaoImpl();
    private final UserMapper userMapper = new UserMapper();

    public String findAll() {
        return userMapper.findAll(
                userDao.findAll())
                .stream()
                .map(userDto -> UserDto.builder().toJson(userDto))
                .collect(Collectors.toList())
                .toString();
    }

    public String findById(Integer id) {
        return UserDto.builder()
                .toJson(userMapper.toDto(
                        userDao.findById(id)
                        )
                );
    }

    public String create(UserDto userDto) {
        return userDao.create(userMapper.fromDto(userDto)).equals(IDS) ? String.format(SORRY_ENTITY_NOT_FOUND, USER) : String.format(SUCCESSFULLY_CREATED, USER);
    }

    public String delete(Integer id) {
        return userDao.delete(id).equals(IDS) ? String.format(SORRY_ENTITY_NOT_FOUND, USER) : String.format(SUCCESSFULLY_DELETED, USER);
    }

    public String update(Integer id, UserDto userDto) {
        return userDao.update(id, userMapper.fromDto(userDto)).equals(IDS) ? String.format(SORRY_ENTITY_NOT_FOUND, USER) : String.format(SUCCESSFULLY_UPDATED, USER);
    }
}
