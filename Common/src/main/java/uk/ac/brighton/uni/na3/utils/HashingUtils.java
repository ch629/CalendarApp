//package uk.ac.brighton.uni.na3.utils;

//import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class HashingUtils {
    public static char[] saltHash(char[] password, boolean newPW) { //NOTE: The password is better as a char[] as it wont be stored in one location in RAM
        //TODO: Ben - Implement (Rename this or add any methods as necessary)
        //throw new NotImplementedException();
        if (newPW == true) {
          byte[] salt = genSalt();
          char[] hSPassword = getHash(password, salt);
        }
        return password;
    }

    public static byte[] genSalt() {
      byte[] salt = new byte[256];
      Random randNo = new Random();
      randNo.nextBytes(salt);
      return salt;
    }

    public static char[] getHash(char[] password, byte[] salt) {
      try {
    	  MessageDigest md = MessageDigest.getInstance("SHA-256");
      }catch (NoSuchAlgorithmException e){

      }
      int length = password.length + salt.length;
      StringBuilder s = new StringBuilder(length);
      for (char c : password) {
    	  s.append(c);
      }
      return password;
    }
}
