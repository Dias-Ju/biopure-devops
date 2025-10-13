package br.com.fiap.BioPure.config.security;

import br.com.fiap.BioPure.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${minha.chave.secreta}")
    private String palavraSecreta;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);
            String token = JWT
                    .create()
                    .withIssuer("produtos")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(gerarDataDeExpiracao())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException erro){
            throw  new RuntimeException("Não foi possível gerar o token!");
        }
    }



    public String validarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);
            return JWT.require(algorithm)
                    .withIssuer("produtos")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException erro){
            return null;
        }
    }

    public Instant gerarDataDeExpiracao () {
            return LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.of("-03:00"));
        }
}



