package fr.todd.zanniv.service;

import fr.todd.zanniv.entity.User;
import fr.todd.zanniv.exception.EmailAlreadyExistsException;
import fr.todd.zanniv.exception.UserNotFoundException;
import fr.todd.zanniv.exception.UsernameAlreadyExistsException;
import fr.todd.zanniv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(Long userId) throws UserNotFoundException {
        Optional<User> optionalUser = this.userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException();
        }
        return optionalUser.get();
    }

    @Override
    public User login(String username, String password) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByUsernameAndPassword(username, password);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException();
        }
        return optionalUser.get();
    }

    @Override
    public User save(User newUser) throws EmailAlreadyExistsException, UsernameAlreadyExistsException {
        if (this.userRepository.existsByUsername(newUser.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }
        if (this.userRepository.existsByEmail(newUser.getEmail())) {
            throw new EmailAlreadyExistsException();
        }
        return this.userRepository.save(newUser);
    }

    @Override
    public boolean updatePassword(Long userId, String newPassword) throws UserNotFoundException {
        if (this.userRepository.existsById(userId)) {
            return this.userRepository.updatePassword(userId, newPassword) > 0;
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public void delete(Long userId) {
        if (this.userRepository.existsById(userId)) {
            this.userRepository.deleteById(userId);
        } else {
            throw new UserNotFoundException();
        }
    }
}
