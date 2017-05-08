package uk.ac.brighton.uni.na3.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

public class HashingUtils {
    private static final Random rand = new Random();
    private static MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ignored) {
        }
    }

    public static char[] saltHash(char[] password) {
        return saltHash(password, genSalt());
    }

    public static char[] saltHash(char[] password, byte[] uSalt) {
        byte[] hSPassword = getHash(password, uSalt);
        return Arrays.toString(hSPassword).toCharArray();
    }

    public static byte[] genSalt() {
        byte[] salt = new byte[256];
        rand.nextBytes(salt);
        return salt;
    }

    private static byte[] getHash(char[] password, byte[] salt) {
        byte[] pWChar = Arrays.toString(password).getBytes();
        byte[] pWSalt = new byte[pWChar.length + salt.length];
        System.arraycopy(pWChar, 0, pWSalt, 0, pWChar.length);
        System.arraycopy(salt, 0, pWSalt, pWChar.length, salt.length);

        md.update(pWSalt, 0, pWSalt.length);
        return md.digest(pWSalt);
    }
}
