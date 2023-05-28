package com.pucminas.cbea.global.util.date;

import com.pucminas.cbea.global.util.Validation;
import lombok.extern.java.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.pucminas.cbea.global.util.date.SplitMode.*;

@Log
public class DateUtil {

    public static Date minHoursDay() {
        return minHoursDay(new Date());
    }

    public static Date minHoursDay(Date date) {
        return truncateDate(date);
    }

    public static Date maxHoursDay() {
        return maxHoursDay(new Date());
    }

    public static String dateToString(Date date, String pattern) {
        if (Validation.isNull(date))
            return "";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static Date maxHoursDay(Date date) {
        Calendar calendar = toCalendar(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date maxMinutesHour(Date date) {
        Calendar calendar = toCalendar(date);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date minMinutesHour(Date date) {
        Calendar calendar = toCalendar(date);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date addDays(int days) {
        return addDays(new Date(), days);
    }

    public static Date addDays(Date date, int days) {
        Calendar calendar = toCalendar(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    public static Date removeDays(int days) {
        return removeDays(new Date(), days);
    }

    public static Date removeMonths(int months) {
        return removeMonths(new Date(), months);
    }

    public static Date removeMonths(Date date, int months) {
        Calendar calendar = toCalendar(date);
        calendar.add(Calendar.MONTH, months * -1);
        return calendar.getTime();
    }

    public static Date removeDays(Date date, int days) {
        Calendar calendar = toCalendar(date);
        calendar.add(Calendar.DAY_OF_YEAR, days * -1);
        return calendar.getTime();
    }

    public static Date removeHours(Date date, int hours) {
        Calendar calendar = toCalendar(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours * -1);
        return calendar.getTime();
    }

    public static Date fixUTCDiff(Date date) {
        return addHours(date, 3);
    }

    public static Date addHours(Date date, int hours) {
        Calendar calendar = toCalendar(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    public static Date addMinutes(int minutes) {
        return addMinutes(new Date(), minutes);
    }

    public static Date addMinutes(Date date, int minutes) {
        Calendar calendar = toCalendar(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    public static Date removeMinutes(int minutes) {
        return removeMinutes(new Date(), minutes);
    }

    public static Date greatest(Date first, Date second) {
        if (first != null && second == null) return first;
        if (first == null && second != null) return second;
        return first != null && first.after(second) ? first : second;
    }

    public static Date least(Date first, Date second) {
        if (first != null && second == null) return first;
        if (first == null && second != null) return second;
        return first != null && first.before(second) ? first : second;
    }

    public static Date removeMinutes(Date date, int minutes) {
        Calendar calendar = toCalendar(date);
        calendar.add(Calendar.MINUTE, minutes * -1);
        return calendar.getTime();
    }

    private static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private static Date truncateDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateFormat.format(date));
        } catch (Exception ex) {
            log.severe(ex.getMessage());
        }
        return null;
    }

    public static String format(String pattern, Date date) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String formatUTC() {
        return formatUTC(new Date());
    }

    public static String formatUTC(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }

    public static Date formatUTCAndParseTruncate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return truncateDate(parse(dateFormat.format(date), dateFormat));
    }

    public static List<Date> parseDateRangeToList(Date startDate, Date endDate) {
        return parseDateRangeToList(startDate, endDate, DAY);
    }

    public static List<Date> parseDateRangeToList(Date startDate, Date endDate, SplitMode splitMode) {
        boolean splitByDay = DAY.equals(splitMode);
        boolean splitByMonth = MONTH.equals(splitMode);
        boolean splitByHour = HOUR.equals(splitMode);

        List<Date> dates = new ArrayList<>();
        Calendar calendarStar = toCalendar(minHoursDay(startDate));
        Calendar calendarEnd = splitByDay || splitByMonth ? toCalendar(minHoursDay(endDate)) : toCalendar(maxHoursDay(endDate));

        while (calendarStar.getTime().before(calendarEnd.getTime()) || calendarStar.getTime().equals(calendarEnd.getTime())) {
            Date result = calendarStar.getTime();
            dates.add(result);

            if (splitByMonth) calendarStar.add(Calendar.MONTH, 1);
            if (splitByDay) calendarStar.add(Calendar.DAY_OF_YEAR, 1);
            if (splitByHour) calendarStar.add(Calendar.HOUR_OF_DAY, 1);
        }

        return dates;
    }

    public static long diffInDays(Date start, Date end) {
        long diff = end.getTime() - start.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static LocalDate toLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static boolean isToday(Date date) {
        return Objects.equals(truncateDate(new Date()), truncateDate(date));
    }

    public static Date parse(String data, SimpleDateFormat simpleDateFormat) {
        try {
            return simpleDateFormat.parse(data);
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean isRushTime() {
        Date now = new Date();
        return betweenHours(now, "08:00", "19:00");
    }

    public static boolean betweenHours(Date data, String horaInicio, String horaFim) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        try {

            Date dataInicio = dateFormat.parse(horaInicio);
            Date dataFim = dateFormat.parse(horaFim);
            Date target = dateFormat.parse(dateFormat.format(data));

            return (target.after(dataInicio) && target.before(dataFim))
                    || dataInicio.equals(target)
                    || dataFim.equals(target);

        } catch (Exception ex) {
            return false;
        }
    }

    public static Date now() {
        return new Date();
    }

    public static Date parseDatetime(String text) {
        return parse(text, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"));
    }

    public static Date parseDate(String text) {
        return parse(text, new SimpleDateFormat("dd/MM/yyyy"));
    }

    public static Date minDayOfMonth(Date date) {
        Calendar calendar = toCalendar(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return minHoursDay(calendar.getTime());
    }

    public static boolean before(Date date) {
        return date != null && now().before(date);
    }

    public static boolean after(Date date) {
        return date != null && now().after(date);
    }

    public static Date parseLocalDateToDate(LocalDate date) {
        return date != null ? Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
    }
}