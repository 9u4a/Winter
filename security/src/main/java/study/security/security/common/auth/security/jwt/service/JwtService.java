package study.security.security.common.auth.security.jwt.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.security.security.common.auth.repository.UserRepository;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class JwtService {

    private final UserRepository userRepository;
    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long accessPeriod = 1000L * 60L * 60L;
    private final long refreshPeriod = 1000L * 60L * 60L * 24L * 14;

    private final String accessHeader = "Authorization";
    private final String refreshHeader = "RefreshToken";
    private static final String BEARER = "Bearer ";

    public String createAccessToken(String email) {
        return Jwts.builder()
                .claim("email", email)
                .setSubject("access-token")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessPeriod))
                .signWith(key)
                .compact();
    }

    public String createRefreshToken() {
        return Jwts.builder()
                .setSubject("refresh-token")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshPeriod))
                .signWith(key)
                .compact();
    }

    public void setAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader(accessHeader, accessToken);
        log.info("SET_ACCESS_TOKEN : {}", accessToken);
    }

    public void setRefreshToken(HttpServletResponse response, String refreshToken) {
        response.setHeader(refreshHeader, refreshToken);
        log.info("SET_REFRESH_TOKEN : {}", refreshToken);
    }

    public Optional<String> getAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(accessHeader))
                .filter(accessToken -> accessToken.startsWith(BEARER))
                .map(accessToken -> accessToken.replace(BEARER, ""));
    }

    public Optional<String> getRefreshToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(refreshHeader))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }

    public boolean validAccessToken(String accessToken) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken);
            return claims.getBody()
                    .getExpiration()
                    .after(new Date());
        } catch(ExpiredJwtException e) {
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean validRefreshToken(String refreshToken) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(refreshToken);
            return claims.getBody()
                    .getExpiration()
                    .after(new Date());
        } catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }


    public Claims getClaims(String token) {
        try{
          return Jwts.parserBuilder()
                  .setSigningKey(key)
                  .build()
                  .parseClaimsJws(token)
                  .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
    public Optional<String> getEmailByToken(String token){
        return Optional.ofNullable(getClaims(token).get("email").toString());
    }

    public  void updateRefreshToken(String email, String refreshToken){
        userRepository.findByEmail(email)
                .ifPresent(user -> user.updateRefreshToken(refreshToken));
    }
}
