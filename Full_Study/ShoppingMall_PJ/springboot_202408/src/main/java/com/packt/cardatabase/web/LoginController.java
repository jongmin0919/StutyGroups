package com.packt.cardatabase.web;

import com.packt.cardatabase.domain.User;
import com.packt.cardatabase.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.packt.cardatabase.domain.AccountCredentials;
import com.packt.cardatabase.service.JwtService;

@RestController
public class LoginController {
	@Autowired
	private JwtService jwtService;

	@Autowired	
	AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials) {
		UsernamePasswordAuthenticationToken creds =
				new UsernamePasswordAuthenticationToken(
						credentials.getUserid(),
						credentials.getPassword());	
		//role 정보.
		Authentication auth = authenticationManager.authenticate(creds);

		String role = auth.getAuthorities().stream()
				.map(grantedAuthority -> grantedAuthority.getAuthority())
				.findFirst().orElse("ROLE_USER"); // Default role

		// Generate token
		String jwts = jwtService.getToken(auth.getName(), role);

		// Build response with the generated token
		return ResponseEntity.ok()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
				.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
				.build();

	}

	//회원가입
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public ResponseEntity<?> join(@RequestBody AccountCredentials credentials) {
		// Check if the username or userId already exists
		if (userRepository.findByUserid(credentials.getUserid()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("userId is already taken");
	}

		// Create a new User entity
		User newUser = new User();
		newUser.setUserid(credentials.getUserid());
		newUser.setUsername(credentials.getUsername());
		newUser.setPassword(passwordEncoder.encode(credentials.getPassword())); // Encrypt the password
		newUser.setRole(credentials.getRole());

		// Save the user to the database
		userRepository.save(newUser);

		return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
	}

}
