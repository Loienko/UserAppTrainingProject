package net.ukr.dreamsicle.dto;

import net.ukr.dreamsicle.entity.User;
import net.ukr.dreamsicle.exception.DreamsicleException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserMapper implements Factory<User, UserDto>, DreamsicleUtilDto {
    private static final String USER = "User";

    @Override
    public UserDto toDto(User user) {
        Optional.ofNullable(user).orElseThrow(() -> new DreamsicleException(String.format(INPUT_PARAMETER_NOT_FOUND, USER), 404));
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
        Optional.ofNullable(userDto).orElseThrow(() -> new DreamsicleException(String.format(INPUT_PARAMETER_NOT_FOUND, USER), 404));
        return User.builder()
                .userName(userDto.getUserName())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .roleId(userDto.getRoleId())
                .build();
    }

    @Override
    public List<UserDto> findAll(List<User> users) {
        Optional.ofNullable(users).orElseThrow(() -> new DreamsicleException(String.format(INPUT_PARAMETER_NOT_FOUND, USER), 404));

        return users.stream().map(this::toDto).collect(Collectors.toList());
    }
}
