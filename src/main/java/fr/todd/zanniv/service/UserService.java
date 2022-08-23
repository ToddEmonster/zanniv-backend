package fr.todd.zanniv.service;

import fr.todd.zanniv.entity.User;

import java.util.List;

public interface UserService {

    public User login(String username, String password);
    public List<User> getAllUsers();
    public User save(User user);
}
