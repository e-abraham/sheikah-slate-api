package com.zelda.sheikahslateapi.services;
import com.zelda.sheikahslateapi.exceptions.ResourceNotFoundException;
import com.zelda.sheikahslateapi.models.User;
import com.zelda.sheikahslateapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    //Spring injects repository
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " does not exist"));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //new user w/plaintext password
    public User saveUser(User user) {
        user.setPassword(Hasher.hash(user.getPassword()));
        return userRepository.save(user);
    }

    //new users w/plaintext passwords
    public List<User> saveUsers(List<User> users) {
        for (User user : users) {
            user.setPassword(Hasher.hash(user.getPassword()));
        }
        return userRepository.saveAll(users);
    }

    //existing user w/hashed password
    public User updateUser(Integer id, User userUpdate) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " does not exist"));
        user.setUsername(userUpdate.getUsername());
        user.setPassword(userUpdate.getPassword());
        user.setRole(userUpdate.getRole());

        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " does not exist"));
//        userRepository.delete(user);
        userRepository.deleteById(id);
    }
}
