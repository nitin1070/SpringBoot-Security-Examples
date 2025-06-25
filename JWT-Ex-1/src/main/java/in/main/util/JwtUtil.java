package in.main.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")   
	private String secret;
	
	private SecretKey getSigningKey() {
		SecretKey sKey=Keys.hmacShaKeyFor(secret.getBytes());
		return sKey;
	}
	public String generateToken(String email) {
		return Jwts.builder()
		.setSubject(email)
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
		.signWith(getSigningKey())
		.compact();
	}
	public Claims extractClaims(String token) {
		JwtParser parser=Jwts.parserBuilder().setSigningKey(getSigningKey()).build();
		Jws<Claims>jwsClaims=parser.parseClaimsJws(token);
		return jwsClaims.getBody();
	}
	public String extractEmail(String token) {
		Claims claims=extractClaims(token);
		return claims.getSubject();
	}
	public boolean isTokenExpired(String token) {
		return extractClaims(token).getExpiration().before(new Date());
	}
	public boolean validateToken(String token,String email) {
		return (extractEmail(token).equals(email)&& !isTokenExpired(token));
	}
}
