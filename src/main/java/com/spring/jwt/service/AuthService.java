package com.spring.jwt.service;

import com.spring.jwt.model.login.LoginRequest;
import com.spring.jwt.model.login.LoginResponse;
import com.spring.jwt.entity.Role;
import com.spring.jwt.entity.User;
import com.spring.jwt.repository.RoleCustomRepo;
import com.spring.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    //private final UserServiceImp userServiceImp;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleCustomRepo roleCustomRepo;

//    public AuthResponse register(RegisterRequest request) {
//
//        if(userRepository.findUserByEmail(request.getEmail()).isPresent()){
//            throw new IllegalStateException("username taken");
//        }
//        Role role = new Role("ROLE_USER");
//        User user = new User(null,request.getFullname(), request.getEmail(), request.getPassword(),new HashSet<>());
//        userServiceImp.saveUser(user);
//        userServiceImp.addToUser(user.getEmail(),role.getName());
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        Set<Role> set = new HashSet<>();
//        set.add(new Role(role.getName()));
//        set.forEach(i->authorities.add(new SimpleGrantedAuthority(i.getName())));
//        user.setRoles(set);
//        var jwtToken = jwtService.generateToken(user,authorities);
//        var jwtRefreshToken = jwtService.generateRefreshToken(user,authorities);
//        return AuthenticationResponse.builder().token(jwtToken).refreshToken(jwtRefreshToken).build();
//    }

    public LoginResponse authenticateLogin(LoginRequest loginRequest) {
        try {
            Authentication authenticate = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
            if (authenticate.isAuthenticated()) {
                User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
                List<Role> role = roleCustomRepo.getRole(user);
                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                Set<Role> set = new HashSet<>();
                role.forEach(c->set.add(new Role(c.getName())));
                user.setRoles(set);
                set.forEach(i->authorities.add(new SimpleGrantedAuthority(i.getName())));
                var jwtToken = jwtService.generateToken(user,authorities);
                return LoginResponse.builder().token(jwtToken).build();
            } else return new LoginResponse();
        } catch (AuthenticationException authenticationException){
            return new LoginResponse();
        }
    }
}