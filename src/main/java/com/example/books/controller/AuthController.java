package com.example.books.controller;

import com.example.books.dto.DataUserDto;
import com.example.books.security.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;

  private final TokenProvider tokenProvider;

  @PostMapping(value = "/token")
  public ResponseEntity<?> authenticate(@RequestBody DataUserDto dataUser){
    final Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            dataUser.getUsername(),
            dataUser.getPassword()
        )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    final String token = tokenProvider.generateToken(authentication);
    return ResponseEntity.ok(token);
  }
}
