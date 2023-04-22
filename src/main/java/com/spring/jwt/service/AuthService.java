package com.spring.jwt.service;

import com.spring.jwt.auth.AuthRequest;
import com.spring.jwt.auth.AuthResponse;
import com.spring.jwt.entity.Role;
import com.spring.jwt.entity.User;
import com.spring.jwt.repository.RoleCustomRepo;
import com.spring.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        List<Role> role = null;
        if(user!=null){
            role = roleCustomRepo.getRole(user);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Set<Role> set = new HashSet<>();
        role.stream().forEach(c->set.add(new Role(c.getName())));
        user.setRoles(set);
        set.stream().forEach(i->authorities.add(new SimpleGrantedAuthority(i.getName())));
        var jwtToken = jwtService.generateToken(user,authorities);
        return AuthResponse.builder().token(jwtToken).build();
    }
}