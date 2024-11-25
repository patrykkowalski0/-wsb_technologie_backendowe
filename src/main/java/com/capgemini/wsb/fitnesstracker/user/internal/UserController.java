package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;
    private final UserSimpleMapper userSimpleMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/simple")
    public List<UserSimpleDto> getAllUsersSimple() {
        return userService.findAllUsers()
                .stream()
                .map(userSimpleMapper::toSimpleDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping(params = "email")
    public UserDto getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/older/{date}")
    public List<UserDto> getUsersOlderThan(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return userService.findAllUsers().stream()
                .filter(user -> user.getBirthdate().isBefore(date))
                .map(userMapper::toDto)
                .toList();
    }
}
