package calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import uk.ac.brighton.uni.na3.CalendarApp;
import uk.ac.brighton.uni.na3.model.Event;
import uk.ac.brighton.uni.na3.model.SimpleDateTime;
import uk.ac.brighton.uni.na3.utils.AuthUtils;
import uk.ac.brighton.uni.na3.utils.EventUtils;

/*
 * Testing for the EventUtilities class. 
 * THE SERVER MUST BE RUNNING TO PERFORM THESE TESTS.
 */
public class TestEvent {
	
	@Before
	/*
	 * Theres an extremely low chance this could generate an already utilised username and pass.
	 */
	public void setUp(){
		CalendarApp.setupUnirest();
		
		Random rng = new Random();
		String possibleChars = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQuRsStTuUvVwWxXyYzZ";
		String username = randomString(rng, possibleChars, 50);
		String password = randomString(rng, possibleChars, 50);
		
		AuthUtils.register(username, password.toCharArray());
		AuthUtils.login(username, password.toCharArray());	
	}

	@Test
	/**
	 * Test creating an event
	 */
	public void createEvent() {
		assertTrue(EventUtils.createEvent("test", "test", "test", new SimpleDateTime(LocalDateTime.now()), new SimpleDateTime(LocalDateTime.now()), false, new String[] {"Hello", "World"}));
		List<Event> e = EventUtils.getEventsOnDay(LocalDate.now());
		assertTrue(e.size() > 0);
	}
	
	@Test
	/**
	 * Test grabbing events for different days
	 */
	public void getEventsOnDay(){
		//Create ten events tomorrow
		for(int i = 0; i < 10; i++){
			assertTrue(EventUtils.createEvent(Integer.toString(i), "test", "test", new SimpleDateTime(LocalDateTime.now().plusDays(1)), new SimpleDateTime(LocalDateTime.now().plusDays(1)), false, new String[] {"Hello", "World"}));
		}
		List<Event> e = EventUtils.getEventsOnDay(LocalDate.now().plusDays(1));
		assertEquals(e.size(), 10);
		
		//Create five events today
		for(int i = 0; i < 5; i++){
			assertTrue(EventUtils.createEvent(Integer.toString(i), "test", "test", new SimpleDateTime(LocalDateTime.now().minusDays(1)), new SimpleDateTime(LocalDateTime.now().minusDays(1)), false, new String[] {"Hello", "World"}));
		}
		e = EventUtils.getEventsOnDay(LocalDate.now().minusDays(1));
		assertEquals(e.size(), 5);
	}
	
	public String randomString(Random random, String chars, int length)
	{
	    char[] string = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        string[i] = chars.charAt(random.nextInt(chars.length()));
	    }
	    return new String(string);
	}

	
}
