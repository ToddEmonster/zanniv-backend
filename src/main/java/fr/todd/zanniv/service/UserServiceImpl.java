package fr.todd.zanniv.service;

import fr.todd.zanniv.entity.User;
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
    public User login(String username, String password) {
        Optional<User> optionalUser = userRepository.findUserByUsernameAndPassword(username, password);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            // TODO 403 throw UserNotFoundError;
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User save(User user) {
        // TODO : convert UserDTO to User (or in Controller)
        return this.userRepository.save(user);
    }

    public User findById(Long userId) {
        Optional<User> userOpt = this.userRepository.findById(userId);
        if (userOpt.isPresent()) {
            return userOpt.get();
        } return null; // TODO throw error "user not found with id"
    }
}
