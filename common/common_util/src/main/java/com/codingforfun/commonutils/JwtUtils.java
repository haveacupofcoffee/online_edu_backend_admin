package com.codingforfun.commonutils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author qdl
 * @since 2019/11/20
 */
public class JwtUtils {


    public static final long EXPIRE = 1000 * 60 * 60 * 24;  // 24 hours
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    //JWT Token
    public static String getJwtToken(String id, String nickname){

        String JwtToken = Jwts.builder()
                //header The header typically consists of two parts:
                // the type of the token, which is JWT, and the signing algorithm being used,
                // such as HMAC SHA256 or RSA.
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //play load
                //Registered claims
                .setSubject("codingforfun")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))  //24 hours
                //public or private claim
                .claim("id", id)
                .claim("nickname", nickname)

                //Signature
                //To create the signature part you have to take the encoded header,
                // the encoded payload, a secret, the algorithm specified in the header,
                // and sign that.
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();

        return JwtToken;
    }

    /**
     * check if token expired
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        //if token is null or empty, return false
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            //parse token
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * check if token expired
     * @param request, client request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        return checkToken(jwtToken);
    }

    /**
     * get id from token
     * @param request
     * @return
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("id");
    }
}
