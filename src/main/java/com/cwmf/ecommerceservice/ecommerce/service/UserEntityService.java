package com.cwmf.ecommerceservice.ecommerce.service;

import com.cwmf.ecommerceservice.ecommerce.exception.UserEntityException;
import com.cwmf.ecommerceservice.ecommerce.model.Role;
import com.cwmf.ecommerceservice.ecommerce.model.UserEntity;
import com.cwmf.ecommerceservice.ecommerce.repo.UserEntityRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserEntityService {
    private final UserEntityRepository userEntityRepository;
    public UserEntityService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    //CRUD
    //createUser
    public boolean userSignUp(UserEntity user){
        if(StringUtils.isNotBlank(user.getId())){
            throw new UserEntityException("User id must be empty");
        }
        if(StringUtils.isBlank(user.getName())){
            throw new UserEntityException("User must have a name");
        }
        if(!isPasswordSafe(user.getPassword())){
            throw new UserEntityException("Password too simple");
        }
        if(userEntityRepository.findUserEntityByEmail(user.getEmail()).isPresent()){
            throw new UserEntityException("user" + user.getEmail() +" already exist");
        }
        user.setId(null);
        userEntityRepository.save(user);
        return true;
    }
    //password complexity check helper method
    private boolean isPasswordSafe(String passWord){
        if(passWord.matches("(?=.*[0-9]).*") && passWord.matches("(?=.*[a-z]).*") && passWord.matches("(?=.*[A-Z]).*") && passWord.matches("(?=.*[~!@#$%^&*()_-]).*")){
            return true;
        }else{
            return false;
        }
    }

    //delete
    public boolean deleteUserById(String id){
        if(userEntityRepository.findById(id).isEmpty()){
            throw new UserEntityException("User "+id+" does not exist");
        }
        userEntityRepository.deleteById(id);
        return true;
    }

    //update
    public boolean updateUser(UserEntity user){
        if(StringUtils.isBlank(user.getId())){
            throw new UserEntityException("User must have an id");
        }
        if(StringUtils.isBlank(user.getName())){
            throw new UserEntityException("User must have a name");
        }
        if(!isPasswordSafe(user.getPassword())){
            throw new UserEntityException("Password too simple");
        }
        if(userEntityRepository.findUserEntityByEmail(user.getEmail()).isEmpty()){
            throw new UserEntityException("user" + user.getEmail() +" Not exist");
        }
        userEntityRepository.save(user);
        return true;
    }

    //login
    public String login(String token){
        /*
        byte[] result = Base64.getDecoder().decode(token);
        String decoded=new String(result);
        String userName= "";
        for(int i=0; i<decoded.length();i++){
            if(decoded.charAt(i) != ':' ) {
                userName = userName + decoded.charAt(i);
            }else{
                break;
            }
        }*/
        UserEntity user=userEntityRepository.findUserEntityByEmail(token).get();
        Role role=new Role("CUSTOMER");
        boolean isCustomer=user.getRoles().contains(role);
        if(isCustomer){
            return "CUSTOMER";
        }
        return "EMPLOYEE";
        //throw new UserEntityException(rolesArray[0]);
       // return rolesArray[0];

    }

    //check if user has active order
   // public boolean userHasActiveOrder(String userEmail){
   //     UserEntity user=userEntityRepository.findUserEntityByEmail(userEmail).get();
  //      return user.isHasActiveOrder();
   // }
}
