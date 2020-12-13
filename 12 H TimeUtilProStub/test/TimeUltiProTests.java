
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import net.htlgrieskirchen.pos3.timeutil.TimeUtilPro;
import org.junit.Assert;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Florian
 */
public class TimeUltiProTests {

    TimeUtilPro tester = new TimeUtilPro();
    int testInt = 20030101;
    long testLong = 200301010000L;
    Date testDate = new Date(103, 0, 1);
    LocalDate testLocalDate = LocalDate.of(2003, 1, 1);
    LocalDateTime testLocalDateTime = LocalDateTime.of(2003, 1, 1, 0, 0);

    LocalDate localDateResult = LocalDate.of(2003, 1, 1);
    LocalDateTime localDateTimeResult = LocalDateTime.of(2003, 1, 1, 0, 0);
    Calendar calendarTest = Calendar.getInstance();

    @Test
    public void intToLocalDateTest() {
        LocalDate expResult = tester.intToLocalDate(testInt);

        Assert.assertEquals(expResult, localDateResult);
    }

    @Test
    public void longToLocalDateTest() {
        LocalDate expResult = tester.longToLocalDate(testLong);

        Assert.assertEquals(expResult, localDateResult);
    }

    @Test
    public void dateToLocalDateTest() {
        LocalDate expResult = tester.dateToLocalDate(testDate);

        Assert.assertEquals(expResult, localDateResult);
    }

    @Test
    public void calendarToLocalDateTest() {
        calendarTest.set(2003, 0, 1);
        LocalDate expResult = tester.calendarToLocalDate(calendarTest);

        Assert.assertEquals(expResult, localDateResult);
    }

    @Test
    public void intToLocalDateTimeTest() {
        LocalDateTime expResult = tester.intToLocalDateTime(testInt);

        Assert.assertEquals(expResult, localDateTimeResult);
    }

    @Test
    public void longToLocalDateTimeTest() {
        LocalDateTime expResult = tester.longToLocalDateTime(testLong);

        Assert.assertEquals(expResult, localDateTimeResult);
    }

    @Test
    public void dateToLocalDateTimeTest() {
        LocalDateTime expResult = tester.dateToLocalDateTime(testDate);

        Assert.assertEquals(expResult, localDateTimeResult);
    }

    @Test
    public void calendarToLocalDateTimeTest() {
        calendarTest.set(2003, 0, 1);
        LocalDateTime expResult = tester.calendarToLocalDateTime(calendarTest);

        Assert.assertEquals(expResult, localDateTimeResult);
    }

    @Test
    public void localDateToIntTest() {
        int expResult = tester.localDateToInt(testLocalDate);

        Assert.assertEquals(expResult, testInt);
    }

    @Test
    public void localDateTimeToIntTest() {
        int expResult = tester.localDateTimeToInt(testLocalDateTime);

        Assert.assertEquals(expResult, testInt);
    }

    @Test
    public void localDateToLongTest() {
        long expResult = tester.localDateToLong(testLocalDate);

        Assert.assertEquals(expResult, testLong);
    }

    @Test
    public void localDateTimeToLongTest() {
        long expResult = tester.localDateTimeToLong(testLocalDateTime);

        Assert.assertEquals(expResult, testLong);
    }

    @Test
    public void localDateToDateTest() {
        Date expResult = tester.localDateToDate(testLocalDate);

        Assert.assertEquals(expResult, testDate);
    }

    @Test
    public void localDateTimeToDateTest() {
        Date expResult = tester.localDateTimeToDate(testLocalDateTime);

        Assert.assertEquals(expResult, testDate);
    }

    @Test
    public void localDateToCalendarTest() {
        Calendar expResult = tester.localDateToCalendar(testLocalDate);
        calendarTest.set(2003, 0, 1);
        
        Assert.assertEquals(expResult, calendarTest);
    }

    @Test
    public void localDateTimeToCalendarTest() {
        calendarTest.set(2003, 0, 1);
        Calendar expResult = tester.localDateTimeToCalendar(testLocalDateTime);

        Assert.assertEquals(expResult, calendarTest);
    }
}
