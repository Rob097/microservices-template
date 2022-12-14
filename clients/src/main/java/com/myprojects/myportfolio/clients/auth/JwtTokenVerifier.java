package com.myprojects.myportfolio.clients.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.assertj.core.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class JwtTokenVerifier {

    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;
    private RouterValidator routerValidator;

    public JwtTokenVerifier(SecretKey secretKey,
                            JwtConfig jwtConfig,
                            RouterValidator routerValidator) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
        this.routerValidator = routerValidator;
    }

    public List<String> validateToken(HttpServletRequest request) throws ResponseStatusException{

        if(request == null){
            throw new IllegalStateException("Request cannot be null.");
        }

        if(!routerValidator.isHttpServletRequestSecured.test(request)) {

            String internalAuthorizationHeader =  request.getHeader(jwtConfig.getInternalAuthorizationHeader());
            String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());
            String message = null;
            List<String> result = new ArrayList<>();

            if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
                message = "You need to be authenticated to access this resource.";
            } else if (Strings.isNullOrEmpty(internalAuthorizationHeader) || !internalAuthorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
                message = "You're trying to access this microservice without passing through the load balancer.";
            } else {

                String token = internalAuthorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
                try {
                    jwtConfig.isInvalid(token);
                } catch (ExpiredJwtException e) {
                    message = "Authorization header is expired";
                }

                Jws<Claims> claimsJws = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(token);

                Claims body = claimsJws.getBody();

                String username = body.getSubject();

                List<String> authorities = (List<String>) body.get("authorities");
                List<String> roles = (List<String>) body.get("roles");
                result.add(username);
                result.addAll(Stream.concat(roles.stream(), authorities.stream()).toList());

            }

            if (message != null)
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, message);

            return result;

        }

        return null;

    }

}
