package com.digilbum.app.controller;

import com.digilbum.app.security.auth.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  Logger logger = Logger.getLogger(getClass().getCanonicalName());

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request )
  {
    try {
      return new ResponseEntity<>(service.register(request), HttpStatus.OK);
    }
    catch (Exception e){
      logger.log(Level.SEVERE, e.getMessage());
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
      logger.log(Level.SEVERE, e.getMessage());
      return new ResponseEntity<>(new AuthenticationResponse("Authentification échouée, vérifier vos identifiants"), HttpStatus.UNAUTHORIZED);
    }
    //pas bonne pratique apparemment, à voir comment mieux gérer plusieurs exceptions. avec instanceOf ?
    catch (RuntimeException e){
      logger.log(Level.SEVERE, e.getMessage());
      return new ResponseEntity<>(new AuthenticationResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }


}
