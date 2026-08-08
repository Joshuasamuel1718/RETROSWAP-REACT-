package com.retroswap.RetroSwap_Backend.Controller;

import com.retroswap.RetroSwap_Backend.Model.User;
import com.retroswap.RetroSwap_Backend.Service.User_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/retroswap")
@RestController
public class User_Controller {
    @Autowired
    User_service service;
    @GetMapping("/user/profile")
    public ResponseEntity<User>getUser()
    {
        User user=service.getUser();
        if(user==null)
        {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }
    @PutMapping("/user/profile")
   public ResponseEntity<Void> editProfile(@RequestBody  User user)
    {
      service.editProfile(user);
      return  ResponseEntity.ok().build();

    }
    @DeleteMapping("user/profile")
public ResponseEntity<Void> deleteProfile()
    {
        service.deleteProfile();
        return ResponseEntity.ok().build();
    }






}
