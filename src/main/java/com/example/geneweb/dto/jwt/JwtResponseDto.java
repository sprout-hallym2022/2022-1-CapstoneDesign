package com.example.geneweb.dto.jwt;

import lombok.Data;

@Data
public class JwtResponseDto {
    private String accessToken;
    private String refreshToken;
}