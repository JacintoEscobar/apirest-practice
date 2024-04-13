package com.restapi.practice.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Jacinto Escobar Quezada
 * @version 1.0.0
 */

public abstract class ApiHashBuilder {
    public String makeHash(String ts, String privateKey, String publicKey) {
        String input = String.format(ts + privateKey + publicKey);

        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(input.getBytes());
            byte[] bytes = md.digest();
            
            for (byte aByte : bytes) {
                hash.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            return hash.toString();
        } catch (NoSuchAlgorithmException ex) {

        }

        return hash.toString();
    }
}
