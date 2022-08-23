package fr.todd.zanniv.service;

import fr.todd.zanniv.entity.User;
import fr.todd.zanniv.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    public User login(String username, String password) throws UserNotFoundException;
    public List<User> getAllUsers();
    public User save(User user);
    public User getUserById(Long userId) throws UserNotFoundException;
}
