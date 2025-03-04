package JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.security.auth.Subject;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import static jdk.internal.org.jline.keymap.KeyMap.key;

public class JwtUtil {

    private String secretKey="";

    public JwtUtil() {
    try{
        KeyGenerator generator = KeyGenerator.getInstance("HmacSHA256");
        SecretKey key = generator.generateKey();
        secretKey=Base64.getEncoder().encodeToString(key.getEncoded());
    }
    catch(NoSuchAlgorithmException e){
  throw new RuntimeException();
    }
    }


    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<String, Object>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 *30))
                .and()
                .signWith(getKey())
                .compact();

    }
        private Key getKey(){
        byte[] keyBytes= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static String secret_key="Just_12_Random_34_Secret_word";



}
