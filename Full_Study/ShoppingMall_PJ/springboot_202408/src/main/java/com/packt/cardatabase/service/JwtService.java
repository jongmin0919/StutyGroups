package com.packt.cardatabase.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtService {

	static final long EXPIRATIONTIME = 86400000; // 1일
	static final String PREFIX = "Bearer ";

	// 설정 파일에서 비밀 키를 읽어옴
	@Value("${jwt.secret}")
	private String secret;

	private Key key;

	// 비밀 키 초기화
	@PostConstruct
	public void init() {
		byte[] decodedKey = Base64.getDecoder().decode(secret);
		this.key = Keys.hmacShaKeyFor(decodedKey); // 비밀 키 생성
	}

	// JWT 토큰 생성
	public String getToken(String username, String role) {
		String token = Jwts.builder()
				.setSubject(username)
				.claim("role", role)  // 역할 정보 추가
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(key)
				.compact();
		return token;
	}

	// JWT 토큰에서 역할(role) 추출
	public String getAuthRole(HttpServletRequest request) {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (token != null) {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token.replace(PREFIX, ""))
					.getBody();

			return claims.get("role", String.class);
		}

		return null;
	}

	// JWT 토큰에서 사용자 정보 추출
	public String getAuthUser(HttpServletRequest request) {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (token != null) {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token.replace(PREFIX, ""))
					.getBody();

			String user = claims.getSubject();
			String role = claims.get("role", String.class); // 역할 추출

			if (user != null)
				return user;
		}

		return null;
	}
}
