package fr.todd.zanniv.service;

import fr.todd.zanniv.entity.User;
import fr.todd.zanniv.exception.EmailAlreadyExistsException;
import fr.todd.zanniv.exception.UserNotFoundException;
import fr.todd.zanniv.exception.UsernameAlreadyExistsException;

import java.util.List;

public interface UserService {

    public User login(String username, String password) throws UserNotFoundException;
    public List<User> getAllUsers();
    public User save(User user) throws EmailAlreadyExistsException, UsernameAlreadyExistsException;
    public User getUserById(Long userId) throws UserNotFoundException;
    public boolean updatePassword(Long userId, String newPassword) throws UserNotFoundException;
    public void delete(Long userId);
    // TOEVOL updateEmail
}
