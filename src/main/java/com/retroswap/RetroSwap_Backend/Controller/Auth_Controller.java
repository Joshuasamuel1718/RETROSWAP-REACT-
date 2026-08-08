package com.retroswap.RetroSwap_Backend.Controller;


import com.retroswap.RetroSwap_Backend.Model.User;
import com.retroswap.RetroSwap_Backend.Service.Security.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/retroswap/auth")
public class Auth_Controller {
    @Autowired
    private AuthService userService;


    @PostMapping("/register")
    public User register(@RequestBody User users)
    {
        return userService.register(users);
    }
    @PostMapping("/login")
    public String login(@RequestBody User users)
    {
        return userService.login(users);
    }




}