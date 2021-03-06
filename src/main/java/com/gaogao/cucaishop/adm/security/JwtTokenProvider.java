package com.gaogao.cucaishop.adm.security;

import com.gaogao.cucaishop.adm.config.AppProperties;
import io.jsonwebtoken.*;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    private static final String jwtSecret = new BigInteger(130, new SecureRandom()).toString(32);

    private AppProperties appProperties;

    @Value("${jwt.expire.hours}")
    private int jwtExpireHrs;

    public JwtTokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String generateToken(Authentication authentication) {
        Date expireDate = new DateTime().plus(appProperties.getAuth().getTokenExpiration()).toDate();
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(jwtUser.getEmail())
                .claim("name",jwtUser.getName())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();
    }

    public String generateTokenLocal(JwtUser jwtUser){
        Date expireDate = new DateTime().plusHours(jwtExpireHrs).toDate();
        return Jwts.builder()
                .setSubject(jwtUser.getEmail())

                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getJwtUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            LOGGER.error("Invalid JWT signature");
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

    public String generatePassword(String oldPassword) {
        return BCrypt.hashpw(oldPassword, BCrypt.gensalt(12));
    }

    public boolean validatePassword(String oldPassword, String dbPassword) {
        try {
            return BCrypt.checkpw(oldPassword, dbPassword);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return false;
    }
}
