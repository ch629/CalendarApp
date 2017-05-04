package uk.ac.brighton.uni.na3.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

public class HashingUtils {
    public static final HashingUtils instance = new HashingUtils(); //Singleton
    private final Random rand = new Random();
    private MessageDigest md;

    private HashingUtils() {
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ignored) {
        }
    }

    public char[] saltHash(char[] password) {
        return saltHash(password, genSalt());
    }

    public char[] saltHash(char[] password, byte[] uSalt) {
        byte[] hSPassword = getHash(password, uSalt);
        return Arrays.toString(hSPassword).toCharArray();
    }

    public byte[] genSalt() {
        byte[] salt = new byte[256];
        rand.nextBytes(salt);
        return salt;
    }

    private byte[] getHash(char[] password, byte[] salt) {
        byte[] pWChar = Arrays.toString(password).getBytes();
        byte[] pWSalt = new byte[pWChar.length + salt.length];
        System.arraycopy(pWChar, 0, pWSalt, 0, pWChar.length);
        System.arraycopy(salt, 0, pWSalt, pWChar.length, salt.length);

        md.update(pWSalt, 0, pWSalt.length);
        return md.digest(pWSalt);
    }
}
