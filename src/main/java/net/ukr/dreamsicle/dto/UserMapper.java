package net.ukr.dreamsicle.dto;

import net.ukr.dreamsicle.entity.User;
import net.ukr.dreamsicle.exception.MyOwnException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class UserMapper implements Factory<User, UserDto> {

    String INPUT_PARAMETER_NOT_FOUND = "Input parameter not found";

    @Override
    public UserDto toDto(User user) {
        Optional.ofNullable(user).orElseThrow(() -> new MyOwnException(INPUT_PARAMETER_NOT_FOUND));
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
        Optional.ofNullable(userDto).orElseThrow(() -> new MyOwnException(INPUT_PARAMETER_NOT_FOUND));
        return new User(
                userDto.getUserName(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getRoleId()
        );
    }

    @Override
    public List<UserDto> findAll(List<User> users) {
        Optional.ofNullable(users).orElseThrow(() -> new MyOwnException(INPUT_PARAMETER_NOT_FOUND));

        return users.stream().map(this::toDto).collect(Collectors.toList());
    }
}
