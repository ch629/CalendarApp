package uk.ac.brighton.na3.calendar;

import org.junit.Test;
import uk.ac.brighton.uni.na3.utils.HashingUtils;

import static org.assertj.core.api.Assertions.assertThat;


public class TestHashing {
    private final HashingUtils hash = HashingUtils.instance;
    private final char[] PASS = "password".toCharArray();

    @Test
    public void testSamePasswordHashUnique() {
        assertThat(hash.saltHash(PASS)).isNotEqualTo(hash.saltHash(PASS));
    }

    @Test
    public void testSameSaltSame() {
        final byte[] salt = hash.genSalt();

        assertThat(hash.saltHash(PASS, salt)).isEqualTo(hash.saltHash(PASS, salt));
    }
}
