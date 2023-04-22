package com.spring.jwt.service;

import com.spring.jwt.entity.Role;
import com.spring.jwt.entity.User;
import com.spring.jwt.repository.RoleRepository;
import com.spring.jwt.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService{
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
//    @Override
//    public List<User> getAllUser(){
//        return userRepository.findAll();
//    }
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }


    public void addToUser(String userName, String roleName) {
        User user = userRepository.findByUsername(userName).get();
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }
//    @Override
//    public String deleteUser(Integer userId) {
//        boolean exists = userRepository.existsById(userId);
//        if(!exists){
//            throw new IllegalStateException("User with id "+ userId +" does not exists");
//        }
//        userRepository.deleteById(userId);
//        return "delete success";
//    }
//    @Override
//    public String updateUser(Integer userid, String fullname, String email, String password) {
//        User user = userRepository.findById(userid)
//                .orElseThrow(()-> new IllegalStateException("User with id" + userid+ "does not exists"));
//        if(fullname != null && fullname.length() > 0 && !Objects.equals(user.getFullname(),fullname)){
//            user.setFullname(fullname);
//        }
//        if(password != null && password.length() > 0 && !Objects.equals(user.getPassword(),password)){
//            user.setFullname(password);
//        }
//        if(email != null && email.length() > 0 && !Objects.equals(user.getEmail(),email)){
//
//            if(userRepository.findUserByEmail(email).isPresent()){
//                throw new IllegalStateException("username taken");
//            }
//            user.setEmail(email);
//            return "Update success";
//        }
//        return "Update fail";
//    }


}
