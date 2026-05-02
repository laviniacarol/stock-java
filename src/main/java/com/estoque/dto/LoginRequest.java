package com.estoque.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String senha;
}
