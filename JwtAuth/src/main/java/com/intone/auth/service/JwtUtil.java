package com.intone.auth.service;

import java.util.Date;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
@SuppressWarnings({ "deprecation", "unused" })
public class JwtUtil {
	private long expirationTime = 10*60*1000;
	private final String secretKey="DWbQkyuTKVZFQ9ipiy8cO4/Z+FElMO1qT/OioAK6ljIKwJsJa77BBpPjg6A0wJhxLnDveTEsumqK\r\n"
			+ "CCF99YKlzA==";
	byte[] secret = secretKey.getBytes();
	public String creatToken(String userName) {
		String token = null;
		try {
			 token = Jwts.builder()
					.setSubject(userName)
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date( System.currentTimeMillis()+expirationTime))
					.signWith(SignatureAlgorithm.HS512, secretKey)
					.compact();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return token;
				
	}
	public boolean isValidToken(String userName, String token ) {
		String tokenUserName = getUserNameFromToken(token);
		return false;
	}
	public String getUserNameFromToken(String token) {
		String tokenUserName = null;
		try{
			tokenUserName= Jwts.parser().setSigningKey(secret)
					.build().parseClaimsJws(token)
					.getBody()
					.getSubject();
			return tokenUserName;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Inavlid Jwt token");
			
		}
	}
	
}
