package net.ukr.dreamsicle.service;

import net.ukr.dreamsicle.dao.imp.UserDaoImpl;
import net.ukr.dreamsicle.dto.UserDto;
import net.ukr.dreamsicle.dto.UserMapper;

import java.util.stream.Collectors;

public class UserService {

    private final UserDaoImpl user = new UserDaoImpl();
    private final UserMapper userMapper = new UserMapper();

    public String findAll() {
        return userMapper.findAll(
                user.findAll())
                .stream()
                .map(userDto -> UserDto.builder().toJson(userDto))
                .collect(Collectors.toList())
                .toString();
    }

    public String findById(Integer id) {
        return UserDto.builder()
                .toJson(userMapper.toDto(
                        user.findById(id)
                        )
                );
    }
}
