package net.ukr.dreamsicle.dto;

import net.ukr.dreamsicle.entity.User;
import net.ukr.dreamsicle.exception.ApplicationException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class UserMapper implements Factory<User, UserDto> {
    private static final String INPUT_PARAMETER_FOR_USER_NOT_FOUND = "Input parameter for User not found";

    @Override
    public UserDto toDto(User user) {
        Optional.ofNullable(user).orElseThrow(() -> new ApplicationException(INPUT_PARAMETER_FOR_USER_NOT_FOUND));
        return new UserDto.UserDtoBuilder()
                .id(user.getId())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roleId(user.getRoleId())
                .build();

    }

    @Override
    public User fromDto(UserDto userDto) {
        Optional.ofNullable(userDto).orElseThrow(() -> new ApplicationException(INPUT_PARAMETER_FOR_USER_NOT_FOUND));
        return User.builder()
                .userName(userDto.getUserName())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .roleId(userDto.getRoleId())
                .build();
    }

    @Override
    public List<UserDto> findAll(List<User> users) {
        Optional.ofNullable(users).orElseThrow(() -> new ApplicationException(INPUT_PARAMETER_FOR_USER_NOT_FOUND));

        return users.stream().map(this::toDto).collect(Collectors.toList());
    }
}
