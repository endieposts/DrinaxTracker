package com.endie.drinaxtracker.controller;

import com.endie.drinaxtracker.model.User;
import com.endie.drinaxtracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.endie.drinaxtracker.exception.ResourceNotFoundException;

import jakarta.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody User userRequest) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(userRequest.getUsername());
            user.setPassword(userRequest.getPassword());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @GetMapping("/showusers")
    public String showShowUsersPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "showusers";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userRepository.save(user);
        return "redirect:/index";
    }

    @GetMapping("/edituser/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/updateuser/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }

        userRepository.save(user);
        return "redirect:/showusers";
    }

    @GetMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "redirect:/showusers";
    }
}
