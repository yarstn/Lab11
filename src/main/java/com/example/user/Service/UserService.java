package com.example.user.Service;

import com.example.user.ApiException.ApiException;
import com.example.user.Model.User;
import com.example.user.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void add(User user) {
        userRepository.save(user);
    }
    public void update(Integer id,User user) {
        User user1 = userRepository.findUserById(id);
        if(user1 == null) {
            throw  new ApiException("no user found");
        }
        user.setUsername(user1.getUsername());
        user.setEmail(user1.getEmail());
        user.setPassword(user1.getPassword());
        userRepository.save(user);
    }
    public void delete(Integer id) {
        User user = userRepository.findUserById(id);
        if(user == null) {
            throw  new ApiException("no user found");
        }
        userRepository.delete(user);
    }

    public User getUserByEmail(String email) {
User user = userRepository.findUserByEmail(email);
if (user == null) {
    throw  new ApiException("no user found");
}
return user;
    }


    public User getUserByUserNameAndPassword(String username, String password) {
        User user = userRepository.findUserByUsernameAndPassword(username, password);
        if (user == null) {
            throw  new ApiException("no user found");
        }
        return user;
    }

}
