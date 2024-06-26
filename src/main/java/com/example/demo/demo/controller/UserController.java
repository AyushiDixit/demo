package com.example.demo.demo.controller;

import com.example.demo.demo.model.User;
import com.example.demo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository; // Autowire UserRepository

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/welcome")
    public String welcome(Model model, Principal principal) {
        // Fetch the username from the principal
        String username = principal.getName();

        // Fetch the user details from the repository
        User user = userRepository.findByUsername(username); // Use userRepository instance

        // Add user details to the model
        model.addAttribute("name", user.getName());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("roles", user.getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.toList()));

        return "welcome";
    }

    @GetMapping("/manager/restricted")
    public String restricted() {
        return "restricted";
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalidate session if it exists
        }
        response.sendRedirect("/login"); // Redirect to login page after logout
    }
}
