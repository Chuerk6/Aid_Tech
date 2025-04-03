package GRUPO1.TP.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;
    //Json web tokens, para interectuar con un servicio
    @Override//clase hija
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
//HttpServletRequest request; solicitud HTTP que se desea filtrar
//HttpServletResponse response; solicitud HTTP que se enviara al cliente
//FilterChain chain, filtros de servlets; manipular solicitudes HTTP
/*
throws ServletException, IOException; excepciones de esos tipos
Servlet, excepcion pa indicar q ha ocuriido un error durante la solicitud
IOException, excepcion E/Salida , indicar error durante la lectura de datos
*/

//final, declarar una clase q no puede ser modificado
        final String authorizationHeader = request.getHeader("Authorization");
/*
  request.getHeader("Authorization"); encabezado de autorizacion de la solicitud HTTP,
  enviar credenciales:tokens.

  La cadena "Authorization" es el nombre del encabezado del que se desea obtener el valor
  final String authorizationHeader = ...: El resultado del método getHeader("Authorization")
  se asigna a una variable llamada authorizationHeader. La palabra clave final aquí significa
  que esta variable es una constante una vez que se le asigna un valor. En este caso, se utiliza
  final probablemente porque el valor del encabezado de autorización no debería cambiar una vez
   que se obtiene de la solicitud.
 */

        String username = null;
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            token = authorizationHeader.substring(7);
// token JWT: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
            //Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9 ---> el 7 es "e", 8: "y", etc
            username = jwtUtilService.extractUsername(token);
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
/*
    SecurityContextHolder es una clase que proporciona acceso al contexto de seguridad de la aplicación
    SecurityContextHolder devuelve el contexto de seguridad actual.
    getAuthentication(): Este método del contexto de seguridad devuelve el objeto de autenticación actual
                      contiene detalles sobre la identidad del usuario autenticado, como su nombre de usuario, roles, etc.
       "Si no hay una autenticación actualmente en curso".
*/
            SecurityUser objectsecurityUser = (SecurityUser) this.userDetailsService.loadUserByUsername(username);
//UserDetailsService: cargar los detalles del usuario: name, pasword, rol
// loadUserByUsername(usermae): En otras palabras, busca y carga la información del usuario correspondiente al nombre de usuario dado.
//SecurityUser: clase represente un usuario en el sistema

            if (jwtUtilService.validateToken(token, objectsecurityUser)) {

                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken
                        (objectsecurityUser, null, objectsecurityUser.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //Agregar detalles a la autenticacion del usuarui, como direccion IP del cliente
            /*
               WebAuthenticationDetailsSource es una fuente de detalles de autenticación específicos
               para aplicaciones web.
             */
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
            /*
            permite que la solicitud continúe su procesamiento normal después de que el filtro haya
            realizado su trabajo.
            * */
    }

}
