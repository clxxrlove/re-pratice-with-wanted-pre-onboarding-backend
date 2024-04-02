package xyz.jiyong.wantedpreonboarding.global.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import xyz.jiyong.wantedpreonboarding.global.dto.JwtToken;
import xyz.jiyong.wantedpreonboarding.global.exception.InvalidTokenException;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final Key key;
    private final long tokenValidityInSeconds;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.token-validity-in-seconds") long tokenValidityInSeconds
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.tokenValidityInSeconds = tokenValidityInSeconds * 1000;
    }

    public JwtToken generateToken(Authentication authentication, Long userId) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date tokenExpiresIn = new Date(now + tokenValidityInSeconds);

        // AccessToken 생성
        String accessToken = Jwts.builder()
                .subject(authentication.getName())
                .claim("auth", authorities)
                .claim("userId", userId)
                .expiration(tokenExpiresIn)
                .signWith(key)
                .compact();

        // RefreshToken 생성
        String refreshToken = Jwts.builder()
                .expiration(tokenExpiresIn)
                .signWith(this.key)
                .compact();

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(new SecretKeySpec(key.getEncoded(), key.getAlgorithm()))
                    .build()
                    .parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser()
                    .verifyWith(new SecretKeySpec(key.getEncoded(), key.getAlgorithm()))
                    .build()
                    .parseUnsecuredClaims(accessToken)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public Long getUserIdFromClaims(String accessToken) {
        Claims claims = parseClaims(accessToken);
        Object userIdObject = claims.get("userId");

        if (userIdObject instanceof Long userId) {
            return userId;
        } else {
            throw new InvalidTokenException("Invalid Token.");
        }
    }
}
