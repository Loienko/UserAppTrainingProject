package net.ukr.dreamsicle.service;

import net.ukr.dreamsicle.dao.imp.UserDaoImpl;
import net.ukr.dreamsicle.dto.UserDto;
import net.ukr.dreamsicle.dto.UserMapper;

import java.util.stream.Collectors;

import static net.ukr.dreamsicle.util.Constants.*;

public class UserService {

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
        return userDao.create(userMapper.fromDto(userDto)).equals(IDS) ? SORRY_USER_NOT_FOUND : USER_SUCCESSFULLY_CREATED;
    }

    public String delete(Integer id) {
        return userDao.delete(id).equals(IDS) ? SORRY_USER_NOT_FOUND : USER_SUCCESSFULLY_DELETED;
    }

    public String update(Integer id, UserDto userDto) {
        return userDao.update(id, userMapper.fromDto(userDto)).equals(IDS) ? SORRY_USER_NOT_FOUND : USER_SUCCESSFULLY_UPDATED;
    }
}
