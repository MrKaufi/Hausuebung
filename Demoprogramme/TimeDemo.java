/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twelsch.demo.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

public class TimeDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        playAround();

//        final Date date = new Date();
//        final LocalDateTime temp1 = dateToLocalDateTime(date);
//        System.out.println("DAT -> LDT: " + temp1.toString());
//        
//        final LocalDateTime ldt = LocalDateTime.now();
//        final Date temp2 = localDateTimeToDate(ldt);
//        System.out.println("LDT -> DAT: " + temp2.toString());
//        
//        final int intTime = getIntDateNow();
//        final Calendar temp3 = intToCalendar(intTime);
//        System.out.println("INT -> CAL: " + temp3.getTime().toString());
    }

    public static void playAround() {

        final LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("current: " + currentTime.toString());

        final LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1); // .toString()

        final Month month = currentTime.getMonth();
        final int day = currentTime.getDayOfMonth();
        final int seconds = currentTime.getSecond();
        System.out.println("month: " + month + ", day: " + day + ", seconds: " + seconds);

        final LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        final LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        final LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        final LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);
    }

    public static LocalDateTime dateToLocalDateTime(final Date date) {

        // get the instant of date
        final Instant instant = date.toInstant();
        final ZoneId currentZone = ZoneId.systemDefault();

        // transform the instant to an LocalDateTime object
        final LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, currentZone);

        return localDateTime;
    }

    public static Date localDateTimeToDate(final LocalDateTime localDateTime) {

        // get the instant of localDateTime
        final ZoneId currentZone = ZoneId.systemDefault();
        final Instant instant = localDateTime.atZone(currentZone).toInstant();

        // transform the instant to a Date object
        final Date date = Date.from(instant);

        return date;
    }

    public static int getIntDateNow() {
        int result;

        final DateFormat df = new SimpleDateFormat("yyyyMMdd");
        final Calendar c = Calendar.getInstance();

        final String transform = df.format(c.getTime());
        //final Date reverte = df.parse("19900801");

        result = Integer.valueOf(transform);
        return result;
    }

    @SuppressWarnings("deprecation")
    public static Calendar intToCalendar(final int intDate) {
        Calendar result;

        int temp = intDate;
        int day = temp % 100;
        temp = temp / 100;
        int month = temp % 100;
        temp = temp / 100;
        int year = temp;
        final Date date = new Date(year - 1900, month - 1, day - 0);

        result = Calendar.getInstance();
        result.setTime(date);

        return result;
    }

}
