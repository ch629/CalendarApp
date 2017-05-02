//package uk.ac.brighton.uni.na3.utils;

//import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class HashingUtils {

    public static char[] saltHash(char[] password, boolean newPW, byte[] uSalt) { //NOTE: The password is better as a char[] as it wont be stored in one location in RAM
        //TODO: Ben - Implement (Rename this or add any methods as necessary)
        //throw new NotImplementedException();
        if (newPW == true) {
          byte[] salt = genSalt();
          byte[] hSPassword = getHash(password, salt);
          return hSPassword.toString().toCharArray();
        } else {
        	byte[] hSPassword = getHash(password, uSalt);
            return hSPassword.toString().toCharArray();
        }
    }

    public static byte[] genSalt() {
      byte[] salt = new byte[256];
      Random randNo = new Random();
      randNo.nextBytes(salt);
      return salt;
    }

    public static byte[] getHash(char[] password, byte[] salt) {

      String temp = password.toString() + salt.toString();
      byte[] pWChar = password.toString().getBytes();
      byte[] pWSalt = new byte[pWChar.length + salt.length];
      System.arraycopy(pWChar, 0, pWSalt, 0, pWChar.length);
      System.arraycopy(salt, 0, pWSalt, pWChar.length, salt.length);

      try {
    	  MessageDigest md = MessageDigest.getInstance("SHA-1");
    	  md.update(pWSalt, 0, pWSalt.length);
    	  return md.digest(temp.getBytes());
      }catch (NoSuchAlgorithmException e){

      }

      return pWChar;
    }
}
