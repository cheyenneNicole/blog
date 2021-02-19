package com.example.blog.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;

import com.example.blog.model.User;
import com.example.blog.service.UserService;

@Component
public class JwtTokenProvider {

	    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

	    @Value("${app.jwtSecret}")
	    private String jwtSecret;

	    @Value("${app.jwtExpirationInMs}")
	    private int jwtExpirationInMs;

	    @Autowired
	    private UserService userService;

	    public String generateToken(Authentication authentication) {

	        System.out.println(authentication.getPrincipal());

	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

	        User currentUser = userService.findByUserName(userDetails.getUsername());

	        Date now = new Date();
	        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

	        return Jwts.builder()
	                .setSubject(Long.toString(currentUser.getId()))
	                .setIssuedAt(new Date())
	                .setExpiration(expiryDate)
	                .signWith(SignatureAlgorithm.HS512, jwtSecret)
	                .compact();
	    }

	    public Long getUserIdFromJWT(String token) {
	        Claims claims = Jwts.parser()
	                .setSigningKey(jwtSecret)
	                .parseClaimsJws(token)
	                .getBody();

	        return Long.parseLong(claims.getSubject());
	    }

	    public boolean validateToken(String authToken) {
	        try {
	            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
	            return true;
	        } catch (SignatureException ex) {
	            logger.error("Invalid JWT signature");
	        } catch (MalformedJwtException ex) {
	            logger.error("Invalid JWT token");
	        } catch (ExpiredJwtException ex) {
	            logger.error("Expired JWT token");
	        } catch (UnsupportedJwtException ex) {
	            logger.error("Unsupported JWT token");
	        } catch (IllegalArgumentException ex) {
	            logger.error("JWT claims string is empty.");
	        }
	        return false;
	    }
}