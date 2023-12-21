package com.example.demo.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JWTResponse {

    private String jwtToken;

    private String username;
}
