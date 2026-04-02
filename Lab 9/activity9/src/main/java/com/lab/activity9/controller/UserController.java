package com.lab.activity9.controller;

import com.lab.activity9.dto.SignupRequest;
import com.lab.activity9.entity.User;
import com.lab.activity9.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String showLoginPage(Model model,
                                @RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "success", required = false) String success) {
        model.addAttribute("error", error);
        model.addAttribute("success", success);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        Optional<User> userOptional = userService.login(username, password);

        if (userOptional.isPresent()) {
            session.setAttribute("loggedInUser", userOptional.get());
            return "redirect:/home";
        }

        model.addAttribute("error", "Invalid Username/Password");
        return "login";
    }

    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        model.addAttribute("signupRequest", new SignupRequest());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("signupRequest") SignupRequest signupRequest,
                         BindingResult bindingResult,
                         Model model) {

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        String result = userService.registerUser(signupRequest);

        if (!result.equals("SUCCESS")) {
            model.addAttribute("error", result);
            return "signup";
        }

        return "redirect:/?success=Please login now !!";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("name", user.getFirstName() + " " + user.getLastName());
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
