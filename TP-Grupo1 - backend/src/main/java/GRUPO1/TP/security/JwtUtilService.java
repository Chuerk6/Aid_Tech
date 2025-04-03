package GRUPO1.TP.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtUtilService {
    // WEB_DEV_UPC_202301 => [Base64] => V0VCX0RFVl9VUENfMjAyMzAx
    private static final String JWT_SIGNATURE_KEY = "V0VCX0RFVl9VUENfMjAyMzAx";
    /*
    JWT_SIGNATURE_KEY: se utiliza en el proceso de creación y verificación de tokens
     JWT para garantizar su seguridad.
     */
    private static Long JWT_TOKEN_VALIDITY = 1000 * 60 * 60 * (long)3; // 3 horas de validez del token
    /*
        1000 representa 1 segundo en milisegundos.
        60 representa 1 minuto.
        60 representa 1 hora.
    */

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(JWT_SIGNATURE_KEY).parseClaimsJws(token).getBody();
    /*
    getBody(): Este método se utiliza para obtener el cuerpo del token JWT, que contiene las
     reclamaciones del token.
    -parseClaimsJws(token): Este método se utiliza para analizar el token JWT dado y devolver
     un objeto de tipo Jws<Claims>, que contiene el token JWT y sus reclamaciones.
    setSigningKey(JWT_SIGNATURE_KEY): Este método se utiliza para establecer la clave de
    firma que se utilizará para verificar la autenticidad del token JWT.
    Java JWT (jjwt) que se utiliza para analizar y validar tokens JWT.
    este método extractAllClaims toma un token JWT como entrada, lo analiza utilizando
    la clave de firma especificada en JWT_SIGNATURE_KEY, y devuelve todas las reclamaciones
    (claims) del token en forma de un objeto Claims. Las reclamaciones son simplemente pares
     de clave-valor que contienen información adicional sobre el usuario autenticado, como
     el nombre de usuario, los roles, etc.*/
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsFunction) {
        return claimsFunction.apply(extractAllClaims(token));
        /*Así que en este ejemplo, el método extractClaim devuelve el nombre de usuario
        ("admin") que está almacenado en la reclamación sub (sujeto) del token JWT
        proporcionado*/
    }
    //extractClaim: extraer cualquier reclamación que necesites, de una manera genérica y reutilizable.
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public boolean validateToken(String token, SecurityUser user) {
        String username = extractUsername(token);
        return (!isTokenExpired(token)) && (username.equals(user.getUsername()));
    }

    public String createToken(String subject, Map<String, Object> claims) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, JWT_SIGNATURE_KEY)
                .compact();
    }

    public String generateToken(SecurityUser securityUser) {
        Map<String, Object> claims=new HashMap<>();
        Object roles = securityUser.getAuthorities().stream().collect(Collectors.toList()).get(0);
        claims.put("rol",roles);
        return createToken(securityUser.getUsername(), claims);
    }
}