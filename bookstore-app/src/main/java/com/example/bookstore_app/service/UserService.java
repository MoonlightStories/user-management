package com.example.bookstore_app.service;

import com.example.bookstore_app.data.Role;
import com.example.bookstore_app.data.RoleRepository;
import com.example.bookstore_app.data.User;
import com.example.bookstore_app.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    public UserRepository userRepository;

    // Retrieve all users
    public List<User> getUsers()
    {
        return userRepository.findAll();
    }

    // Retrieve a user by ID
    public User getUsersById(long user_id)
    {
        Optional<User> user = userRepository.findById(user_id);
        return user.orElse(null);
    }

    //Create a user
    public User createUser(User user)
    {
        return userRepository.save(user);
    }

    //Update an excisting user
    public User updateUser(User user)
    {
        return userRepository.save(user);
    }

    //Delete user by ID
    public User deleteUserById(long user_id)
    {
        Optional<User> user = userRepository.findById(user_id);
        if (user.isPresent()) {
            userRepository.deleteById(user_id);
            return user.get(); // Return the deleted user details
        }
        return null;

    }

    // Register a new user
    public User registerUser(String username, String password, String role) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password); // Storing plain text password
        newUser.setRole(role);
        return userRepository.save(newUser);
    }


    // Login a user
    public User loginUser(String username, String password)
    {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password))
        {
            return user.get(); // Successful login
        }
        return null; // Failed login
    }

    // Assign role to a user
    public User assignRoleToUser(Long user_id, String roleName) {
        Optional<User> user = userRepository.findById(user_id);

        if (user.isPresent()) {
            User u = user.get();
            u.addRole(roleName); // Use the addRole method to append the role
            return userRepository.save(u);
        }

        return null; //User not found
    }
}
