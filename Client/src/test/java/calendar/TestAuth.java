package calendar;

import org.junit.Test;
import uk.ac.brighton.uni.na3.utils.AuthUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestAuth extends BaseTest {
	
	//Test correct login credentials
	@Test
    public void testLoginSuccessful() {
		String username = "root";
		String password = "root";
		AuthUtils.register(username, password.toCharArray());
        assertTrue(AuthUtils.login("root","root".toCharArray()));
    }
	//Test incorrect login credentials
	@Test
    public void testLoginUnsuccessful() {
		String username = "root";
		String password = "root";
		AuthUtils.register(username, password.toCharArray());
        assertFalse(AuthUtils.login("root","abc".toCharArray()));
    }
}
