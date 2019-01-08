package com.yador.meetingmanagerbe.token.jwt;

import com.yador.meetingmanagerbe.token.TokenService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.io.Decoders;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.substringBeforeLast;

@Service
public class JWTTokenService implements Clock, TokenService {

    private static final String DOT = ".";
    private static final CompressionCodec COMPRESSION_CODEC = CompressionCodecs.GZIP;

    //private DateService dates;
    private String issuer;
    private int expirationSec;
    private int clockSkewSec;
    private byte[] secretKey;

    JWTTokenService(//final DateService dates,
                    @Value("${jwt.issuer:octoperf}") String issuer,
                    @Value("${jwt.expiration-sec:86400}") Integer expirationSec,
                    @Value("${jwt.clock-skew-sec:300}") Integer clockSkewSec,
                    @Value("${jwt.secret}") String secret) {
        super();
        //this.dates = requireNonNull(dates);
        this.issuer = requireNonNull(issuer);
        this.expirationSec = requireNonNull(expirationSec);
        this.clockSkewSec = requireNonNull(clockSkewSec);
        this.secretKey = Decoders.BASE64.decode(requireNonNull(secret));
    }

    @Override
    public String permanent(final Map<String, String> attributes) {
        return newToken(attributes, 0);
    }

    @Override
    public String expiring(final Map<String, String> attributes) {
        return newToken(attributes, expirationSec);
    }

    private String newToken(final Map<String, String> attributes, final int expiresInSec) {
       Date now = now();
        final Claims claims = Jwts
                .claims()
                .setIssuer(issuer)
                .setIssuedAt(now);

        if (expiresInSec > 0) {
            Date expiresAt = new Date(now.getTime() + expiresInSec);
            claims.setExpiration(expiresAt);
        }
        claims.putAll(attributes);

        return Jwts
                .builder()
                .setClaims(claims)
                .signWith(Keys.hmacShaKeyFor(secretKey))
                .compressWith(COMPRESSION_CODEC)
                .compact();
    }

    @Override
    public Map<String, String> verify(final String token) {
        JwtParser parser = Jwts
                .parser()
                .requireIssuer(issuer)
                .setClock(this)
                .setAllowedClockSkewSeconds(clockSkewSec)
                .setSigningKey(secretKey);
        return parseClaims(() -> parser.parseClaimsJws(token).getBody());
    }

    @Override
    public Map<String, String> untrusted(final String token) {
        JwtParser parser = Jwts
                .parser()
                .requireIssuer(issuer)
                .setClock(this)
                .setAllowedClockSkewSeconds(clockSkewSec);

        String withoutSignature = substringBeforeLast(token, DOT) + DOT;
        return parseClaims(() -> parser.parseClaimsJwt(withoutSignature).getBody());
    }

    private static Map<String, String> parseClaims(final Supplier<Claims> toClaims) {
        try {
            final Claims claims = toClaims.get();
            final Map<String, String> map = new HashMap<>();
            for (Map.Entry<String, Object> e: claims.entrySet()) {
                map.put(e.getKey(), String.valueOf(e.getValue()));
            }
            return map;
        } catch (IllegalArgumentException | JwtException e) {
            return Map.of();
        }
    }

    @Override
    public Date now() {
        return new Date();
    }
}
