package com.example.lifcaremarvel.api.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class <i>HashHelper</i> is used to calculate a message digest from data and convert it to cryptographic hash function
 * <p>
 *
 * @author Tanmeet Singh Bhalla
 * @version %1%, %09/04/19%
 * @since 1.0
 */
class HashHelper {
    /**
     * generate a cryptographic hash function
     */
    static String generate(String s) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = messageDigest.digest(s.getBytes());
            StringBuilder md5 = new StringBuilder();
            for (byte value : digest) {
                md5.append(Integer.toHexString((value & 0xFF) | 0x100).substring(1, 3));
            }
            return md5.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
