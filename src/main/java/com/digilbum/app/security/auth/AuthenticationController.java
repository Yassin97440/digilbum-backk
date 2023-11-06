package com.digilbum.app.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public @ResponseBody ResponseEntity<AuthenticationResponse> register( @RequestBody RegisterRequest request )
  {
    return new ResponseEntity<AuthenticationResponse>(service.register(request), HttpStatus.OK); // ResponseEntity.ok(service.register(request))
  }
  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request  )
  {
    return new ResponseEntity<AuthenticationResponse>(service.authenticate(request), HttpStatus.OK);
  }


}
