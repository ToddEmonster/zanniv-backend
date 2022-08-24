package fr.todd.zanniv.controller;

import fr.todd.zanniv.entity.User;
import fr.todd.zanniv.exception.EmailAlreadyExistsException;
import fr.todd.zanniv.exception.UsernameAlreadyExistsException;
import fr.todd.zanniv.repository.UserRepository;
import fr.todd.zanniv.service.dto.UserAuthDTO;
import fr.todd.zanniv.exception.UserNotFoundException;
import fr.todd.zanniv.service.UserService;
import fr.todd.zanniv.service.dto.UserCreateDTO;
import fr.todd.zanniv.service.dto.UserGetDTO;
import fr.todd.zanniv.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<UserGetDTO>> getUsers() {
        List<UserGetDTO> allUsers = this.userService
                .getAllUsers()
                .stream()
                .map(user -> {
                    return this.userMapper.userToUserGetDto(user);
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    // TODO login error : return 403

    @GetMapping(value = { "/{userId}" })
    public ResponseEntity<UserGetDTO> getUserById(@PathVariable("userId") Long userId) {
        try {
            UserGetDTO userDTO = userMapper.userToUserGetDto(this.userRepository.findById(userId).get());
            return new ResponseEntity<>(userDTO, HttpStatus.FOUND);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            // TODO return 404
            return null;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserGetDTO> authenticate(@Valid @RequestBody UserAuthDTO userAuthDTO) {
        try {
            UserGetDTO userAuthenticatedDTO = userMapper.userToUserGetDto(
                    this.userService.login(userAuthDTO.getUsername(), userAuthDTO.getPassword())
            );
            return new ResponseEntity<>(userAuthenticatedDTO, HttpStatus.FOUND);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } // TODO catch (BadCredentials)
    }

    @PostMapping("/signup")
    public ResponseEntity<UserGetDTO> create(@Valid @RequestBody UserCreateDTO newUserDTO) {
        try {
            User newUser = this.userService.save(userMapper.userCreateDtoToUser(newUserDTO));
            return new ResponseEntity<>(userMapper.userToUserGetDto(newUser), HttpStatus.CREATED);
        } catch (EmailAlreadyExistsException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } catch (UsernameAlreadyExistsException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }


    @PutMapping("/{userId}")
    public ResponseEntity<Boolean> updatePassword(
            @PathVariable("userId") Long userId,
            @RequestBody String newPassword) {
        try {
            this.userService.updatePassword(userId, newPassword);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Boolean> delete(@PathVariable("userId") Long userId) {
        try {
            this.userService.delete(userId);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
