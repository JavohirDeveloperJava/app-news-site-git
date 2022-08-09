package uz.pdp.appnewssitegit.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssitegit.entity.Lavozim;


import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {
    private static final long expireTime=100*60*60*24;
    private static final String secret="mahfiysoz";
    public String generateToken(String username, Lavozim lavozims){
        Date expireDate=new Date(System.currentTimeMillis()+expireTime);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("roles", lavozims.getName())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return token;

    }

    public String getUserNameFromToken(String token){
        try{
            String email = Jwts
                    .parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            return email;

        }catch (Exception e){
            return null;
        }
    }
}
