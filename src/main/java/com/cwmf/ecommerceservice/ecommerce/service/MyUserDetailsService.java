package com.cwmf.ecommerceservice.ecommerce.service;
//import com.cwmf.ecommerceservice.ecommerce.model.UserDetailsImpl;
import com.cwmf.ecommerceservice.ecommerce.model.UserEntity;
import com.cwmf.ecommerceservice.ecommerce.repo.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> user = userEntityRepository.findUserEntityByEmail(email);

        if(user.isEmpty()){
            throw new UsernameNotFoundException("user not found");
        }

        return new User(user.get().getEmail(), new BCryptPasswordEncoder().encode(user.get().getPassword()), user.get().getRoles());
    }

}
