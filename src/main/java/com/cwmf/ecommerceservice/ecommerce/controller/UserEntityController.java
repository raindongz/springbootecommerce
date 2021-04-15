package com.cwmf.ecommerceservice.ecommerce.controller;

import com.cwmf.ecommerceservice.ecommerce.model.UserEntity;
import com.cwmf.ecommerceservice.ecommerce.service.UserEntityService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserEntityController {

   private final UserEntityService userEntityService;
    public UserEntityController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

   // @GetMapping
    //@PreAuthorize("hasAuthority('EMPLOYEE')")
   // public boolean userHasActiveOrder(Principal principal){
   //     return this.userEntityService.userHasActiveOrder(principal.getName());
    //}

    @PostMapping("/signup")
    public boolean userSignUp(@RequestBody UserEntity user){
        return this.userEntityService.userSignUp(user);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUserById(@PathVariable String id){
        return this.userEntityService.deleteUserById(id);
    }

    @PutMapping
    public boolean updateUser(@RequestBody UserEntity user){
        return this.userEntityService.updateUser(user);
    }

    @GetMapping("/login")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public String login(Principal principal) {
        // return user's role
        return this.userEntityService.login(principal.getName());
    }
}
