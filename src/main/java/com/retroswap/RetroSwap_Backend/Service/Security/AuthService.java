package com.retroswap.RetroSwap_Backend.Service.Security;


import com.retroswap.RetroSwap_Backend.Model.User;
import com.retroswap.RetroSwap_Backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepo userRepo;
    BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);



    public User register(User users)
    {
        users.setPassword(encoder.encode(users.getPassword()));
        return userRepo.save(users);
    }
    @Autowired
    AuthenticationManager manager;
    @Autowired
    JWT_service jwtService;
    public String login(User users) {

        Authentication authentication=manager.
                authenticate( new UsernamePasswordAuthenticationToken(users.getEmail(),users.getPassword()));
        if(authentication.isAuthenticated())
        {
            return jwtService.generateToken(users.getEmail());
        }

        return "Failure";
    }
}
