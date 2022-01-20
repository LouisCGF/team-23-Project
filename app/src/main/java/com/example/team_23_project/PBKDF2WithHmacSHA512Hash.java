package com.example.team_23_project;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Class for hashing passwords and validating a password against a hashed password.
 * Uses the PBKDF2-HMAC-SHA512 hashing algorithm and salted using a 16-byte salt
 *
 * @author Louis Ware
 * @version 1.0
 *
 */
public class PBKDF2WithHmacSHA512Hash {

    /**
     * Hashes a given password using the PBKDF2-HMAC-SHA512 hashing algorithm
     *
     * @author Louis Ware
     *
     * @param plainText plain text password to be hashed
     * @return hashed password in the format [iterations]:[salt]:[hash output in hexadecimal]
     * @throws NoSuchAlgorithmException if the hashing algorithm has failed
     * @throws InvalidKeySpecException if the hashing algorithm has failed
     */
    protected String hashPBKDF2WithHmacSHA512Password(String plainText) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 1000;
        char[] chars = plainText.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");

        byte[] hash = secretKeyFactory.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    /**
     * Checks a plain text password is equal to the hashed password stored in the database
     *
     * @author Louis Ware
     *
     * @param inputtedPassword plain text password
     * @param storedPassword hashed stored password
     * @return true is passwords are equal, false if not
     * @throws NoSuchAlgorithmException if the hashing algorithm has failed
     * @throws InvalidKeySpecException if the hashing algorithm has failed
     */
    protected boolean validatePBKDF2WithHmacSHA512Password(String inputtedPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException{
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);

        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(inputtedPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] testHash = secretKeyFactory.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++){
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }

    /**
     * Generates a random 16-byte salt using a secure pseudo-random function
     *
     * @author Louis Ware
     *
     * @return 16-byte salt in a byte array
     * @throws NoSuchAlgorithmException if the algorithm has failed
     */
    protected byte[] getSalt() throws NoSuchAlgorithmException{
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    /**
     * Converts a 16-byte salt to hexadecimal
     *
     * @author Louis Ware
     *
     * @param array 16-byte salt to be converted
     * @return hexadecimal representation of the 16-byte salt
     */
    protected static String toHex(byte[] array){
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);

        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0){
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else{
            return hex;
        }
    }

    /**
     * Converts a hexadecimal representation of a 16-byte salt back into a 16-byte salt
     *
     * @author Louis Ware
     *
     * @param hex hexadecimal representation of the 16-byte salt
     * @return 16-byte salt in a byte array
     */
    protected static byte[] fromHex(String hex){
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i < bytes.length ;i++){
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }

        return bytes;
    }
}
