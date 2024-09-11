package com.e_commerce.backend.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDto {
    private String email;
    private String name;
    private String password;
    private String phoneNumber;
}
