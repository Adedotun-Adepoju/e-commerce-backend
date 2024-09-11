package com.e_commerce.backend.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserResponseDto {

    private String email;
    private String name;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
