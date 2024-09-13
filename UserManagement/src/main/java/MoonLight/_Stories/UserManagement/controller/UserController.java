package MoonLight._Stories.UserManagement.controller;


import MoonLight._Stories.UserManagement.data.User;
import MoonLight._Stories.UserManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping(path = "/users")
    public List<User> findAllUsers()
    {
        return userService.getUsers();
    }

    @GetMapping(path="/users/{user_id}")
    public User findUserById(@PathVariable long user_id)
    {
        return userService.getUsersById(user_id);
    }

    @PostMapping(path="/users")
    public User createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }

    @PutMapping(path = "/users")
    public User updateUser(@RequestBody User user)
    {
        return userService.updateUser(user);
    }

    @DeleteMapping(path = "/users/{user_id}")
    public User deleteUserById(@PathVariable int user_id)
    {
        return userService.deleteUserById(user_id);
    }

    // Register a new user
    @PostMapping(path = "/users/register")
    public User registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String role)
    {
        return userService.registerUser(username, password, role);
    }

    //Login a user
    @PostMapping(path="/users/login")
    public User loginUser(@RequestParam String username, @RequestParam String password)
    {
         return userService.loginUser(username,password);
    }

    @PostMapping(path="users/{userId}/roles/{roleName}")
    public User assignRoleToUser(@PathVariable Long userId, @PathVariable String roleName)
    {
        return userService.assignRoleToUser(userId, roleName);
    }

}
