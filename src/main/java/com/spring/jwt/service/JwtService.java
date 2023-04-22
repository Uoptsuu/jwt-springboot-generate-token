package com.spring.jwt.service;

import com.auth0.jwt.JWT;
import com.spring.jwt.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private final static String key = "ma_hoa_123";

    public String generateToken(User user, Collection<SimpleGrantedAuthority> authorities){
        Algorithm algorithm = Algorithm.HMAC256(key.getBytes());
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date((System.currentTimeMillis()) + 60*60*1000))
                .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

}
