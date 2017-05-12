package calendar;

import org.junit.Test;
import uk.ac.brighton.uni.na3.utils.AuthUtils;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.*;

public class testLogin {
	
	@Test
    public void testLoginSuccessful() {
        assertTrue(AuthUtils.login("root","root".toCharArray()));
    }
	
	@Test
    public void testLoginUnsuccessful() {
        assertFalse(AuthUtils.login("abc","abc".toCharArray()));
    }
}
