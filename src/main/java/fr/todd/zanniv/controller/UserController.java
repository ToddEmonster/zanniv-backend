package fr.todd.zanniv.controller;

import fr.todd.zanniv.entity.Birthday;
import fr.todd.zanniv.entity.User;
import fr.todd.zanniv.exception.EmailAlreadyExistsException;
import fr.todd.zanniv.exception.UsernameAlreadyExistsException;
import fr.todd.zanniv.service.BirthdayService;
import fr.todd.zanniv.service.dto.*;
import fr.todd.zanniv.exception.UserNotFoundException;
import fr.todd.zanniv.service.UserService;
import fr.todd.zanniv.service.mapper.BirthdayMapper;
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
    private BirthdayMapper birthdayMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private BirthdayService birthdayService;

    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<UserGetDTO>> getUsers() {
        List<UserGetDTO> allUsers = this.userService
                .getAllUsers()
                .stream()
                .map(this.userMapper::toGetDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping(value = { "/{userId}" })
    public ResponseEntity<UserGetDTO> getUserById(@PathVariable("userId") Long userId) {
        try {
            UserGetDTO userDTO = userMapper.toGetDto(this.userService.getUserById(userId));
            return new ResponseEntity<>(userDTO, HttpStatus.FOUND);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            // TODO return 404
            return null;
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<UserGetDTO> authenticate(@Valid @RequestBody UserAuthDTO userAuthDTO) {
//        try {
//            UserGetDTO userAuthenticatedDTO = userMapper.toGetDto(
//                    this.userService.login(userAuthDTO.getUsername(), userAuthDTO.getPassword())
//            );
//            return new ResponseEntity<>(userAuthenticatedDTO, HttpStatus.FOUND);
//        } catch (UserNotFoundException e) {
//            System.out.println(e.getMessage());
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        } // TODO catch (BadCredentials) return 403 Forbidden
//    }

    @PostMapping("/signup")
    public ResponseEntity<UserGetDTO> create(@Valid @RequestBody UserCreateDTO newUserDTO) {
        try {
            User savedUser = this.userService.save(userMapper.toEntity(newUserDTO));
            return new ResponseEntity<>(userMapper.toGetDto(savedUser), HttpStatus.CREATED);
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
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value= "/{userId}/birthdays")
    public ResponseEntity<BirthdayDTO> createBirthday(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody BirthdayCreateDTO newBirthdayDTO) {
        if (!this.userService.existsById(userId)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        User user = this.userService.getUserById(userId);
        Birthday savedBirthday = this.birthdayService.save(birthdayMapper.createDtoToEntity(newBirthdayDTO, user));
        return new ResponseEntity<>(birthdayMapper.toDto(savedBirthday), HttpStatus.CREATED);
    }
}
