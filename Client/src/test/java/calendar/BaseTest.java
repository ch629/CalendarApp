package calendar;

import org.junit.BeforeClass;
import org.junit.Ignore;
import uk.ac.brighton.uni.na3.CalendarApp;

@Ignore
public class BaseTest {
    @BeforeClass
    public static void setupUnirest() {
        CalendarApp.setupUnirest();
    }
}
