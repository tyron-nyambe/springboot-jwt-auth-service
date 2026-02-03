package com.lumara.springsecurity.services.impl;


import com.lumara.springsecurity.services.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service //Create this class as a bean so that I can inject it anywhere.

//Creating JWT tokens
//Reading data from JWT tokens
//Validating tokens using a secret key

//When a user logs in → generate token
//When a request comes with a token → read & verify token

//This class is responsible for creating signed JWT tokens and later verifying and extracting user
// information from those tokens using a secret key.
public class JWTServiceImpl implements JWTService {


    //Creates a JWT token for a logged-in user.
    public String generateToken(UserDetails userDetails)
    {
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +1000  *60 *60 *24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Generic helper method saying give me a token and tell me which piece of data you want and I will return it.
    private <T> T extractClaim(String token , Function<Claims, T> claimsResolvers){
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    public String extractUsername(String token)
    {
        return extractClaim(token, Claims::getSubject);
    }

    //Return all the claims from our token
    //Takes the JWT
    //Verifies its signature using your secret key
    //If valid → extracts the payload (claims)
    private Claims extractAllClaims(String token) {

        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignKey() {

        byte[] key = Decoders.BASE64.decode("bXlzZWNyZXRrZXlteXNlY3JldGtleW15c2VjcmV0a2V5MTIz");

        return Keys.hmacShaKeyFor(key);

    }


    //Token is valid if username inside token matches DB user & token is not expired.
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token)
    {
        return extractClaim(token,Claims::getExpiration).before(new Date());
    }
}
