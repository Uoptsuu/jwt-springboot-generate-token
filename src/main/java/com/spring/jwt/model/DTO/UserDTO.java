package com.spring.jwt.model.DTO;

import com.spring.jwt.entity.User;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private boolean isActive;


    public UserDTO toUserDTO(User user){
        return new UserDTO(user.getId(), user.getUsername(), user.isActive());
    }
}
