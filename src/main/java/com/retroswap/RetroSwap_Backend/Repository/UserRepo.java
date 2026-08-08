package com.retroswap.RetroSwap_Backend.Repository;


import com.retroswap.RetroSwap_Backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String username);

}
