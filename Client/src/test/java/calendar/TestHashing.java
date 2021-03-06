package calendar;

import org.junit.Test;
import uk.ac.brighton.uni.na3.utils.HashingUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;


public class TestHashing {
    private final char[] PASS = "password".toCharArray();

    @Test
    public void testSamePasswordHashUnique() {
        assertThat(HashingUtils.saltHash(PASS)).isNotEqualTo(HashingUtils.saltHash(PASS));
    }

    @Test
    public void testSameSaltSame() {
        final byte[] salt = HashingUtils.genSalt();

        assertThat(HashingUtils.saltHash(PASS, salt)).isEqualTo(HashingUtils.saltHash(PASS, salt));
    }

    @Test
    public void testUnique() {
        String[] hashedPasswords = new String[256];
        byte[][] salt = new byte[256][];

        Boolean collision = false;
        Boolean encode = true;

        for (int i = 0; i < hashedPasswords.length; i++) {
            salt[i] = HashingUtils.genSalt();
            hashedPasswords[i] = String.valueOf(HashingUtils.saltHash(PASS, salt[i]));
        }

        for (int n = 0; n < hashedPasswords.length; n++) {
            if (!hashedPasswords[n].equals(String.valueOf(HashingUtils.saltHash(PASS, salt[n]))))
                encode = false;

            for (int m = n + 1; m < hashedPasswords.length; m++) {
                if (m != n && hashedPasswords[m].equals(hashedPasswords[n])) collision = true;
            }
        }
        assertTrue(!collision && encode);
    }
}
