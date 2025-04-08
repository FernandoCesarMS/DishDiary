package org.dishdiary.utils;

import lombok.experimental.UtilityClass;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@UtilityClass
public class HashUtils {

    private static final String SALT_KEY = System.getProperty("SALT_KEY");

    public String encryptSHA256(String input) {
        if (SALT_KEY == null) {
            throw new IllegalStateException("Variável de ambiente SALT_KEY não está definida");
        }

        String saltedInput = input + SALT_KEY;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(saltedInput.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash SHA-256", e);
        }
    }
}