package com.retroswap.RetroSwap_Backend.Service.Security;


import com.retroswap.RetroSwap_Backend.Model.User;
import com.retroswap.RetroSwap_Backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyuserdetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String emailID) throws UsernameNotFoundException {
        User users= (User) userRepo.findByEmail(emailID);
        if(users==null)
        {
            throw  new UsernameNotFoundException("Not Found");
        }


        return new UserPrinciple(users);
    }
}
