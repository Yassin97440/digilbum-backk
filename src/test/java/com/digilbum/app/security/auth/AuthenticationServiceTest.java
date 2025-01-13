package com.digilbum.app.security.auth;

import com.digilbum.app.security.config.JwtService;
import com.digilbum.app.security.token.TokenRepository;
import com.digilbum.app.security.user.UserRepository;
import com.digilbum.app.service.GroupService;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class AuthenticationServiceTest {
	@Autowired
	private AuthenticationService authenticationService;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private TokenRepository tokenRepository;

	@MockBean
	private JwtService jwtService;

	@MockBean
	private GroupService groupService;

	@MockBean
	private AuthorizationService authorizationService;

	@Test
	public void register() {
		RegisterRequest request = null;
		AuthenticationResponse expected = new AuthenticationResponse();
		AuthenticationResponse actual = authenticationService.register(request);

		assertEquals(expected, actual);
	}

	@Test
	public void registerTODO() {
		RegisterRequest request = null;
		AuthenticationResponse expected = new AuthenticationResponse();
		AuthenticationResponse actual = authenticationService.register(request);

		assertEquals(expected, actual);
	}
}
