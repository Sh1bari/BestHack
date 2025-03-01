package ru.noxly.efs.utils;

import io.jsonwebtoken.*;
import ru.noxly.efs.exceptions.GeneralException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class JwtUtil {
    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    static {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // Чтение открытого ключа из файла
            byte[] publicKeyBytes = Files.readAllBytes(Paths.get("public_key.pem"));
            String publicKeyPEM = new String(publicKeyBytes);
            publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----", "");
            publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");
            publicKeyPEM = publicKeyPEM.replaceAll("\\s+", "");

            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyPEM));
            publicKey = keyFactory.generatePublic(publicKeySpec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }


    public static Jws<Claims> getClaims(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token);
            return claimsJws;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature");
            throw new GeneralException(409, "Invalid JWT signature");
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token");
            throw new GeneralException(409, "Expired JWT token");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token");
            throw new GeneralException(409, "Invalid JWT token");
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token");
            throw new GeneralException(409, "Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty");
            throw new GeneralException(409, "JWT claims string is empty");
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException(409, "Invalid token");
        }
    }
}
