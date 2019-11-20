package net.ukr.dreamsicle.service;

import net.ukr.dreamsicle.dao.imp.UserDaoImpl;
import net.ukr.dreamsicle.dto.UserDto;
import net.ukr.dreamsicle.dto.UserMapper;

import java.util.stream.Collectors;

public class UserService {

    private static final String SORRY_USER_NOT_CREATED = "Sorry, user not created";
    private static final String USER_SUCCESSFULLY_CREATED = "User successfully created";
    private static final String SORRY_ROLE_NOT_DELETED = "Sorry, role not deleted";
    private static final String ROLE_SUCCESSFULLY_DELETED = "Role successfully deleted";
    private static final String ROLE_SUCCESSFULLY_UPDATED = "Role successfully updated";
    private static final String SORRY_ROLE_NOT_UPDATED = "Sorry, role not updated";
    private static final Integer IDS = 0;

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
        return userDao.create(userMapper.fromDto(userDto)).equals(IDS) ? SORRY_USER_NOT_CREATED : USER_SUCCESSFULLY_CREATED;
    }
}
