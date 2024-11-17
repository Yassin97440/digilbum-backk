package com.digilbum.app.controller;

import com.digilbum.app.security.auth.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://159.89.0.150:3000")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request )
  {
    try {
      return new ResponseEntity<>(service.register(request), HttpStatus.OK);
    }
    catch (Exception e){
      return new ResponseEntity<>(new AuthenticationResponse("Erreur lors de la création de compte"), HttpStatus. INTERNAL_SERVER_ERROR);
    }
  }
  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request  )
  {
    try {
      return new ResponseEntity<>(service.authenticate(request), HttpStatus.OK);
    }
    catch (AuthenticationException e){
      return new ResponseEntity<>(new AuthenticationResponse("Authentification échouée, vérifier vos identifiants"), HttpStatus.UNAUTHORIZED);
    }
    //pas bonne pratique apparemment, à voir comment mieux gérer plusieurs exceptions. avec instanceOf ?
    catch (RuntimeException e){
      return new ResponseEntity<>(new AuthenticationResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }


}
