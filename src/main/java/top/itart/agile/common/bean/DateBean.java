package top.itart.agile.common.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateBean {
    private int year;
    private int month;
    private int dayOfMonth;
    private int hourOfDay;
    private int minute;
    private int second;
    private int millisecond;

    public DateBean(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second, int millisecond) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        this.second = second;
        this.millisecond = millisecond;
    }

    public DateBean(Calendar calendar) {
        init(calendar);
    }

    private void init(Calendar calendar) {
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        this.hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);
        this.millisecond = calendar.get(Calendar.MILLISECOND);
    }

    public DateBean(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        init(calendar);

    }

    public Date getDate() {
        Calendar calendar = new GregorianCalendar(year, month -1, dayOfMonth, hourOfDay, minute, second);
        calendar.set(java.util.Calendar.MILLISECOND, millisecond);
        return calendar.getTime();
    }
    
    public Date getDateNoTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, dayOfMonth, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMillisecond() {
        return millisecond;
    }

    public void setMillisecond(int millisecond) {
        this.millisecond = millisecond;
    }
}
