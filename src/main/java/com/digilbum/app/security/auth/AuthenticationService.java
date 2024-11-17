package com.digilbum.app.security.auth;


import com.digilbum.app.security.config.JwtService;
import com.digilbum.app.security.token.Token;
import com.digilbum.app.security.token.TokenRepository;
import com.digilbum.app.security.token.TokenType;
import com.digilbum.app.security.user.User;
import com.digilbum.app.security.user.UserRepository;
import com.digilbum.app.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final GroupService groupService;
  private final AuthorizationService authorizationService;

  public AuthenticationResponse register(RegisterRequest request) {

    UserRegisterRequest userRequest = request.user();
    User user = User.builder()
        .firstname(userRequest.getFirstname())
        .lastname(userRequest.getLastname())
        .email(userRequest.getEmail())
        .password(passwordEncoder.encode(userRequest.getPassword()))
        .build();

    authorizationService.giveUserBasicRole(user);
    User savedUser = repository.save(user);

    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                userRequest.getPassword(), user.getAuthorities()
            )
        );
    groupService.create(request.group(), savedUser);

    String jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request)
  {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    User user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    String jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    Token token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }
}
