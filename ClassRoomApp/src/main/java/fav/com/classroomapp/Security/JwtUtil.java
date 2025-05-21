package fav.com.classroomapp.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    @Autowired
    UserDetailsService service;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    private final String secretKey = "FH5g7HeVQU1fRHe0POvG6G8MLqDAJFLuMY4ySDdpEB0=";
    private final long jwtExpiration = 1000 * 60 * 60 * 2; // 2 horas


    public String buildToken(final DTODtails dto){
        UserDetails userDetails = service.loadUserByUsername(dto.getInstitutionalId());
        if (!passwordEncoder.matches(dto.getPassword(), userDetails.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        if (!userDetails.isAccountNonExpired() || !userDetails.isAccountNonLocked() || !userDetails.isCredentialsNonExpired() || !userDetails.isEnabled())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Cuenta bloqueada");
        return Jwts.builder()
                .setClaims(Map.of("name",userDetails.getUsername()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration) )
                .signWith(getSingInKey(secretKey))
                .compact();
    }
    private SecretKey getSingInKey(String key){
        byte[] keyBytes = Base64.getDecoder().decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSingInKey(secretKey))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Date expiration = claims.getExpiration();
            return expiration.after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    public UserDetails getDetailsFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSingInKey(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();


        return service.loadUserByUsername(claims.get("name",String.class));

    }


}
