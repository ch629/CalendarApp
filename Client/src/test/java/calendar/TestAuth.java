package calendar;

import org.junit.Test;
import uk.ac.brighton.uni.na3.utils.AuthUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestAuth extends BaseTest {
    @Test
    public void testLoginSuccessful() {
        assertTrue(AuthUtils.login("root","root".toCharArray()));
    }
	
	@Test
    public void testLoginUnsuccessful() {
        assertFalse(AuthUtils.login("abc","abc".toCharArray()));
    }
}