package net.htlgrieskirchen.pos3.timeutil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class TimeUtilPro {

    public TimeUtilPro() {
    }

    // ########## LOCALDATE METHODS ##########
    public LocalDate intToLocalDate(int date) {
        String dateString = String.valueOf(date);
        int year = Integer.valueOf(dateString.substring(0, 4));
        int month = Integer.valueOf(dateString.substring(4, 6));
        int day = Integer.valueOf(dateString.substring(6, 8));

        return LocalDate.of(year, month, day);
    }

    public LocalDate longToLocalDate(long dateTime) {
        String dateString = String.valueOf(dateTime);
        int year = Integer.valueOf(dateString.substring(0, 4));
        int month = Integer.valueOf(dateString.substring(4, 6));
        int day = Integer.valueOf(dateString.substring(6, 8));

        return LocalDate.of(year, month, day);
    }

    public LocalDate dateToLocalDate(Date dateTime) {
        return dateTime.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public LocalDate calendarToLocalDate(Calendar dateTime) {
        return dateTime.getTime()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    // ########## LOCALDATETIME METHODS ##########
    public LocalDateTime intToLocalDateTime(int date) {
        String dateString = String.valueOf(date);
        int year = Integer.valueOf(dateString.substring(0, 4));
        int month = Integer.valueOf(dateString.substring(4, 6));
        int day = Integer.valueOf(dateString.substring(6, 8));

        return LocalDateTime.of(year, month, day, 0, 0);
    }

    public LocalDateTime longToLocalDateTime(long dateTime) {
        String dateString = String.valueOf(dateTime);
        int year = Integer.valueOf(dateString.substring(0, 4));
        int month = Integer.valueOf(dateString.substring(4, 6));
        int day = Integer.valueOf(dateString.substring(6, 8));
        int hour = Integer.valueOf(dateString.substring(8, 10));
        int minute = Integer.valueOf(dateString.substring(10, 12));

        return LocalDateTime.of(year, month, day, hour, minute);
    }

    public LocalDateTime dateToLocalDateTime(Date dateTime) {
        return dateTime.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public LocalDateTime calendarToLocalDateTime(Calendar dateTime) {
        return dateTime.getTime()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    // ########## INT METHODS ##########
    public int localDateToInt(LocalDate date) {
        String dateString = date.toString();
        String[] arrayString = dateString.split("-");
        dateString = arrayString[0] + arrayString[1] + arrayString[2];
        return Integer.valueOf(dateString);
    }

    public int localDateTimeToInt(LocalDateTime dateTime) {
        String dateString = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm"));
        dateString = dateString.replace("-", "").substring(0, 8);
        return Integer.valueOf(dateString);
    }

    // ########## LONG METHODS ##########
    public long localDateToLong(LocalDate date) {
        String dateString = date.toString();
        String[] arrayString = dateString.split("-");
        dateString = arrayString[0] + arrayString[1] + arrayString[2];
        return Long.valueOf(dateString);
    }

    public long localDateTimeToLong(LocalDateTime dateTime) {
        String dateString = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm"));
        dateString = dateString.replace("-", "");
        return Integer.valueOf(dateString);
    }

    // ########## DATE METHODS ##########
    @SuppressWarnings("deprecation")
    public Date localDateToDate(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @SuppressWarnings("deprecation")
    public Date localDateTimeToDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    // ########## CALENDAR METHODS ##########
    public Calendar localDateToCalendar(LocalDate date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
        return calendar;
    }

    public Calendar localDateTimeToCalendar(LocalDateTime dateTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(dateTime.getYear(), dateTime.getMonthValue() - 1, dateTime.getDayOfMonth(), dateTime.getHour(), dateTime.getMinute(), dateTime.getSecond());
        return calendar;
    }

}
