package study.security.security.common.auth.security.jwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Getter
public class JwtService {

    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long accessPeriod = 1000L * 60L * 60L;
    private final long refreshPeriod = 1000L * 60L * 60L * 24L * 14;


    public String createAccessToken(String email) {

        Claims claims = Jwts.claims();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessPeriod))
                .signWith(key)
                .compact();
    }

    public String createRefreshToken() {

        Claims claims = Jwts.claims();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshPeriod))
                .signWith(key)
                .compact();
    }

    public void setAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader("Authorization", accessToken);
    }
    


}
