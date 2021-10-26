package com.quane.irish_railroad_network_api.security;

import com.quane.irish_railroad_network_api.exception.RailroadNetworkException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.sql.Date;
import java.time.Instant;


import static io.jsonwebtoken.Jwts.parserBuilder;
import static java.util.Date.from;

@Service
public class JwtProvider {

    private KeyStore keyStore;

    //this annotation calls this code after normal constructor
    //only gets called once. Before this, Bean not fully initialised
    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
            keyStore.load(resourceAsStream, "secret".toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new RailroadNetworkException("Exception occurred while loading keystore", e);
        }

    }

    public String generateToken(Authentication authentication) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(3600000)))
                .compact();
        //expiration 1 hour
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new RailroadNetworkException("Exception occurred while retrieving private key from keystore", e);
        }
    }

    private PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate("springblog").getPublicKey();
        } catch (KeyStoreException e) {
            throw new RailroadNetworkException("Exception occurred while " +
                    "retrieving public key from keystore", e);
        }
    }


    public boolean validateToken(String jwt) {
        parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(jwt);
        return true;
    }

    public String getUsernameFromJwt(String token) {
        //claims are the individual parts of the jwt token
        Claims claims = parserBuilder()
                .setSigningKey(getPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
