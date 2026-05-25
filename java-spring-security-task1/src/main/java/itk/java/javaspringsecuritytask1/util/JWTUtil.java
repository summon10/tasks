package itk.java.javaspringsecuritytask1.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import itk.java.javaspringsecuritytask1.entity.Users;
import itk.java.javaspringsecuritytask1.model.UserPrincipal;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class JWTUtil {

    @Value("${jwt.access.secret}")
    private String accessSecret;

    @Value("${jwt.access.expiration}")
    private long accessExpiration;

    @Value("${jwt.refresh.secret}")
    private String refreshSecret;

    @Value("${jwt.refresh.expiration}")
    private long refreshExpiration;

    public SecretKey getAccessSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecret));
    }

    public SecretKey getRefreshSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshSecret));
    }

    public static String extractUserName(String token) {
        return null;
    }

    public String generateAccessToken(@NonNull Users user){
               return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(getAccessSigningKey())
                .claim("roles", user.getRole())
                .compact();

    }

    public String generateRefreshToken(@NonNull String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + refreshExpiration))
                .signWith(getRefreshSigningKey())
                .compact();
    }

    public boolean validateAccessToken(@NonNull String accessToken) {
        return validateToken(accessToken, getAccessSigningKey());
    }
    public boolean validateRefreshToken(@NonNull String refreshToken) {
        return validateToken(refreshToken, getRefreshSigningKey());
    }
    private boolean validateToken(@NonNull String token, @NonNull SecretKey secret) {
        try {
            Jwts.parser()
                    .verifyWith(secret)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired", expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt", unsEx);
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt", mjEx);
        } catch (SignatureException sEx) {
            log.error("Invalid signature", sEx);
        } catch (Exception e) {
            log.error("invalid token", e);
        }
        return false;
    }

    public Claims getClaims(@NonNull String token, @NonNull SecretKey secret) {
        return Jwts.parser()
                .verifyWith(secret)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
