package com.diploma.knowledgebase.services.registrationService;

import com.diploma.knowledgebase.entities.User;
import com.diploma.knowledgebase.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private String reg = "\\A(?=\\S*?[0-9])(?=\\S*?[a-zа-я])(?=\\S*?[A-ZА-Я])\\S{8,}\\z";
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public User getUserByEmailAuth(String username) {
        return userRepository.getUserByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean checkUser(String password) {
        return password.matches(reg);
    }

    public boolean addUser(User user) {
        if (userRepository.getUserByUserNameCheck(user.getUsername()))
            return false;
        else {
            user.setReg_id(3L);
            userRepository.save(user);
            return true;
        }

    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

}
