package com.dapp.outng.common.security;

import java.security.SignatureException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.dapp.outng.common.configs.JwtConfig;
import com.dapp.outng.common.http.SecurityConstants;
import io.jsonwebtoken.*;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

	@Autowired
	private JwtConfig jwtConfig;

	public String generateToken(Long appUserId) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtConfig.getJwtExpirationInMs());

		return Jwts.builder().setSubject(Long.toString(appUserId)).setIssuedAt(new Date()).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, jwtConfig.getJwtSecret()).compact();
	}

	public String getUserIdFromJwt(String token) {
		Claims claims = getJwtParser().parseClaimsJws(token).getBody();

		return claims.getSubject();
	}

	public boolean validateToken(String authToken) {
		try {
			getJwtParser().parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException ex) {
			LOGGER.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			LOGGER.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			LOGGER.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			LOGGER.error("JWT claims string is empty.");
		}
		return false;
	}

	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(SecurityConstants.TOKEN_HEADER);

		if (StringUtils.isNotEmpty(bearerToken) && bearerToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			return bearerToken.substring(7);
		}

		return null;
	}

	public List<SimpleGrantedAuthority> getUserAuthoritiesFromJwt(String jwtToken) {
		val authorities = ((List<String>) getJwtParser().parseClaimsJws(jwtToken).getBody()
				.get(SecurityConstants.TOKEN_ROLE_KEY))
				.stream().map(authority -> new SimpleGrantedAuthority(authority))
				.collect(Collectors.toList());

		return authorities;
	}

	private JwtParser getJwtParser() {
		return Jwts.parser().setSigningKey(jwtConfig.getJwtSecret());
	}
}
