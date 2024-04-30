package xyz.jiyong.wantedpreonboarding.global.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import xyz.jiyong.wantedpreonboarding.global.dto.JwtToken;
import xyz.jiyong.wantedpreonboarding.global.exception.InvalidTokenException;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private final Key key;
    private final long tokenValidityInSeconds;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.tokenValidityInSeconds = tokenValidityInSeconds * 1000;
    }

    public JwtToken generateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date tokenExpiresIn = new Date(now + tokenValidityInSeconds);

        // AccessToken 생성
        String accessToken = Jwts.builder()
                .subject(authentication.getName())
                .claim("auth", authorities)
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

    public void validateToken(String token) throws JwtException {
        Jwts.parser()
                .verifyWith(new SecretKeySpec(key.getEncoded(), key.getAlgorithm()))
                .build()
                .parse(token);
    }

    private Claims parseClaims(String accessToken) throws JwtException {
        return Jwts.parser()
                .verifyWith(new SecretKeySpec(key.getEncoded(), key.getAlgorithm()))
                .build()
                .parseSignedClaims(accessToken)
                .getPayload();
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new InvalidTokenException("Invalid Token.");
        }

        // 권한 없이 SimpleGrantedAuthority 사용시 InternalAuthenticationServiceException 발생
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(
                claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }
}
