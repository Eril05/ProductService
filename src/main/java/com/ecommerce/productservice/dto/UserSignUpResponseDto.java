package com.ecommerce.productservice.dto;


import com.ecommerce.productservice.model.Role;
import com.ecommerce.productservice.model.User;
import lombok.Data;

import java.util.List;

@Data
public class UserSignUpResponseDto {

    private String username;
    private String email;
    private List<Role>roles;
    private boolean isVerified;

    public static UserSignUpResponseDto from(User user){
        UserSignUpResponseDto userSignUpResponseDto = new UserSignUpResponseDto();
        userSignUpResponseDto.setUsername(user.getUsername());
        userSignUpResponseDto.setEmail(user.getEmail());
        userSignUpResponseDto.setRoles(user.getUserRoles());
        userSignUpResponseDto.setVerified(user.isVerified());
        return userSignUpResponseDto;
    }
}
