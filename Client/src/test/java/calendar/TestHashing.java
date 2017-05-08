package calendar;

import org.junit.Test;
import uk.ac.brighton.uni.na3.utils.HashingUtils;

import static org.assertj.core.api.Assertions.assertThat;


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
}
