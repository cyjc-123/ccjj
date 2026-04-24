package org.example;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


public class JWTtest {
    @Test
    public void test(){
        Map<String, Object> dataMap=new HashMap<>();

        Jwts.builder().signWith(SignatureAlgorithm.HS256,"d2N5")
                .addClaims(dataMap);
    }
}
