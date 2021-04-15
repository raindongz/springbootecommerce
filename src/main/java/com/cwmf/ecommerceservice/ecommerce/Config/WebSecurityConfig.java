
package com.cwmf.ecommerceservice.ecommerce.Config;

import com.cwmf.ecommerceservice.ecommerce.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }

/*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
            auth.inMemoryAuthentication()
                .withUser("aaa").password(password().encode("123"))
                .roles("user");
    }
 */

    @Bean
    PasswordEncoder password(){
        //return new NoOpPasswordEncoder();
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
       // http.exceptionHandling().accessDeniedPage("/accessdenied");
       // http.logout().logoutUrl("/logout").logoutSuccessUrl("/users/signOut").permitAll();

        // Set session management to stateless
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        http.cors().and().csrf().disable().
                //formLogin()
            // .loginPage("/login.html")
            // .loginProcessingUrl("/user/login")
            // .defaultSuccessUrl("/success.html").permitAll()
            // .and()
              authorizeRequests()
                .antMatchers("/user/signup").permitAll()
            // .antMatchers("/user/login").permitAll()
            //.antMatchers("/user").hasAuthority("EMPLOYEE")
            .anyRequest().authenticated()
            .and().csrf().disable()
            .httpBasic();
    }

}


