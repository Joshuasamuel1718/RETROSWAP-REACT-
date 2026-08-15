package com.retroswap.RetroSwap_Backend.Service;

import com.retroswap.RetroSwap_Backend.Model.User;
import com.retroswap.RetroSwap_Backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class User_service {
    @Autowired
    UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUser() {
   String email=getEmail();
        return userRepo.findByEmail(email);
    }

    public void editProfile(User user) {
      String email=getEmail();
      User curruser=userRepo.findByEmail(email);
      if(user.getEmail()!=null)
      {
          curruser.setEmail(user.getEmail());
      }
      if(user.getName()!=null)
      {
          curruser.setName(user.getName());
      }
      if(user.getMobileno()!=null)
      {
          curruser.setMobileno(user.getMobileno());
      }
      if(user.getPassword()!=null)
      {
          curruser.setPassword(passwordEncoder.encode(user.getPassword()));
      }
      userRepo.save(curruser);

    }

    public void deleteProfile() {
        String email=getEmail();
       User user=userRepo.findByEmail(email);
       userRepo.deleteById(user.getId());

    }
    private String getEmail()
    {
        String email= SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return email;
    }

}
