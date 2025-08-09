package jodd.datetime;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import jodd.datetime.format.JdtFormat;
import jodd.datetime.format.JdtFormatter;
import jodd.util.HashCode;

public class JDateTime implements Comparable, Cloneable {
   public static final String DEFAULT_FORMAT = "YYYY-MM-DD hh:mm:ss.mss";
   public static final int MONDAY = 1;
   public static final int TUESDAY = 2;
   public static final int WEDNESDAY = 3;
   public static final int THURSDAY = 4;
   public static final int FRIDAY = 5;
   public static final int SATURDAY = 6;
   public static final int SUNDAY = 7;
   public static final int JANUARY = 1;
   public static final int FEBRUARY = 2;
   public static final int MARCH = 3;
   public static final int APRIL = 4;
   public static final int MAY = 5;
   public static final int JUNE = 6;
   public static final int JULY = 7;
   public static final int AUGUST = 8;
   public static final int SEPTEMBER = 9;
   public static final int OCTOBER = 10;
   public static final int NOVEMBER = 11;
   public static final int DECEMBER = 12;
   protected DateTimeStamp time = new DateTimeStamp();
   protected int dayofweek;
   protected int dayofyear;
   protected boolean leap;
   protected int weekofyear;
   protected int weekofmonth;
   protected JulianDateStamp jdate;
   public static final JulianDateStamp JD_1970 = new JulianDateStamp(2440587, (double)0.5F);
   public static final JulianDateStamp JD_2001 = new JulianDateStamp(2451910, (double)0.5F);
   private static final int[] NUM_DAYS = new int[]{-1, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
   private static final int[] LEAP_NUM_DAYS = new int[]{-1, 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
   private static final int[] MONTH_LENGTH = new int[]{0, 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
   protected boolean trackDST;
   protected boolean monthFix;
   protected TimeZone timezone;
   protected Locale locale;
   protected String format;
   protected JdtFormatter jdtFormatter;
   protected int firstDayOfWeek;
   protected int mustHaveDayOfFirstWeek;
   protected int minDaysInFirstWeek;

   public DateTimeStamp getDateTimeStamp() {
      return this.time;
   }

   public void setDateTimeStamp(DateTimeStamp dts) {
      this.set(dts.year, dts.month, dts.day, dts.hour, dts.minute, dts.second, dts.millisecond);
   }

   public void setJulianDate(JulianDateStamp jds) {
      this.setJdOnly(jds.clone());
      this.calculateAdditionalData();
   }

   public JulianDateStamp getJulianDate() {
      return this.jdate;
   }

   public int getJulianDayNumber() {
      return this.jdate.getJulianDayNumber();
   }

   private void calculateAdditionalData() {
      this.leap = TimeUtil.isLeapYear(this.time.year);
      this.dayofweek = this.calcDayOfWeek();
      this.dayofyear = this.calcDayOfYear();
      this.weekofyear = this.calcWeekOfYear(this.firstDayOfWeek, this.mustHaveDayOfFirstWeek);
      this.weekofmonth = this.calcWeekNumber(this.time.day, this.dayofweek);
   }

   private void setJdOnly(JulianDateStamp jds) {
      this.jdate = jds;
      this.time = TimeUtil.fromJulianDate(jds);
   }

   public void set(int year, int month, int day, int hour, int minute, int second, int millisecond) {
      this.jdate = TimeUtil.toJulianDate(year, month, day, hour, minute, second, millisecond);
      if (TimeUtil.isValidDateTime(year, month, day, hour, minute, second, millisecond)) {
         this.time.year = year;
         this.time.month = month;
         this.time.day = day;
         this.time.hour = hour;
         this.time.minute = minute;
         this.time.second = second;
         this.time.millisecond = millisecond;
         this.calculateAdditionalData();
      } else {
         this.setJulianDate(this.jdate);
      }

   }

   private void setJdOnly(int year, int month, int day, int hour, int minute, int second, int millisecond) {
      this.setJdOnly(TimeUtil.toJulianDate(year, month, day, hour, minute, second, millisecond));
   }

   private int calcDayOfWeek() {
      int jd = (int)(this.jdate.doubleValue() + (double)0.5F);
      return jd % 7 + 1;
   }

   private int calcDayOfYear() {
      return this.leap ? LEAP_NUM_DAYS[this.time.month] + this.time.day : NUM_DAYS[this.time.month] + this.time.day;
   }

   private int calcWeekOfYear(int start, int must) {
      int delta = 0;
      if (start <= this.dayofweek) {
         if (must < start) {
            delta = 7;
         }
      } else if (must >= start) {
         delta = -7;
      }

      int jd = (int)(this.jdate.doubleValue() + (double)0.5F) + delta;
      int WeekDay = jd % 7 + 1;
      int time_year = this.time.year;
      int DayOfYearNumber = this.dayofyear + delta;
      if (DayOfYearNumber < 1) {
         --time_year;
         DayOfYearNumber = TimeUtil.isLeapYear(time_year) ? 366 + DayOfYearNumber : 365 + DayOfYearNumber;
      } else if (DayOfYearNumber > (this.leap ? 366 : 365)) {
         DayOfYearNumber = this.leap ? DayOfYearNumber - 366 : DayOfYearNumber - 365;
         ++time_year;
      }

      int firstDay = jd - DayOfYearNumber + 1;
      int Jan1WeekDay = firstDay % 7 + 1;
      int YearNumber = time_year;
      int WeekNumber = 52;
      if (DayOfYearNumber <= 8 - Jan1WeekDay && Jan1WeekDay > must) {
         YearNumber = time_year - 1;
         if (Jan1WeekDay == must + 1 || Jan1WeekDay == must + 2 && TimeUtil.isLeapYear(YearNumber)) {
            WeekNumber = 53;
         }
      }

      int m = 365;
      if (YearNumber == time_year) {
         if (TimeUtil.isLeapYear(time_year)) {
            m = 366;
         }

         if (m - DayOfYearNumber < must - WeekDay) {
            YearNumber = time_year + 1;
            WeekNumber = 1;
         }
      }

      if (YearNumber == time_year) {
         int n = DayOfYearNumber + (7 - WeekDay) + (Jan1WeekDay - 1);
         WeekNumber = n / 7;
         if (Jan1WeekDay > must) {
            --WeekNumber;
         }
      }

      return WeekNumber;
   }

   private int calcWeekNumber(int dayOfPeriod, int dayOfWeek) {
      int periodStartDayOfWeek = (dayOfWeek - this.firstDayOfWeek - dayOfPeriod + 1) % 7;
      if (periodStartDayOfWeek < 0) {
         periodStartDayOfWeek += 7;
      }

      int weekNo = (dayOfPeriod + periodStartDayOfWeek - 1) / 7;
      if (7 - periodStartDayOfWeek >= this.minDaysInFirstWeek) {
         ++weekNo;
      }

      return weekNo;
   }

   public void add(int year, int month, int day, int hour, int minute, int second, int millisecond, boolean monthFix) {
      int difference = 0;
      if (this.trackDST) {
         difference = TimeZoneUtil.getOffset(this, this.timezone);
      }

      this.addNoDST(year, month, day, hour, minute, second, millisecond, monthFix);
      if (this.trackDST) {
         difference = TimeZoneUtil.getOffset(this, this.timezone) - difference;
         if (difference != 0) {
            this.addNoDST(0, 0, 0, 0, 0, 0, difference, false);
         }
      }

   }

   protected void addNoDST(int year, int month, int day, int hour, int minute, int second, int millisecond, boolean monthFix) {
      millisecond += this.time.millisecond;
      second += this.time.second;
      minute += this.time.minute;
      hour += this.time.hour;
      day += this.time.day;
      if (!monthFix) {
         month += this.time.month;
         year += this.time.year;
         this.set(year, month, day, hour, minute, second, millisecond);
      } else {
         this.setJdOnly(this.time.year, this.time.month, day, hour, minute, second, millisecond);
         int from = this.time.day;
         month += this.time.month + year * 12;
         this.setJdOnly(this.time.year, month, this.time.day, this.time.hour, this.time.minute, this.time.second, this.time.millisecond);
         if (this.time.day < from) {
            this.set(this.time.year, this.time.month, 0, this.time.hour, this.time.minute, this.time.second, this.time.millisecond);
         } else {
            this.calculateAdditionalData();
         }
      }

   }

   public void sub(int year, int month, int day, int hour, int minute, int second, int millisecond, boolean monthFix) {
      this.add(-year, -month, -day, -hour, -minute, -second, -millisecond, monthFix);
   }

   public void add(int year, int month, int day, int hour, int minute, int second, int millisecond) {
      this.add(year, month, day, hour, minute, second, millisecond, this.monthFix);
   }

   public void sub(int year, int month, int day, int hour, int minute, int second, int millisecond) {
      this.add(-year, -month, -day, -hour, -minute, -second, millisecond, this.monthFix);
   }

   public void add(int year, int month, int day, boolean monthFix) {
      this.add(year, month, day, 0, 0, 0, 0, monthFix);
   }

   public void sub(int year, int month, int day, boolean monthFix) {
      this.add(-year, -month, -day, 0, 0, 0, 0, monthFix);
   }

   public void add(int year, int month, int day) {
      this.add(year, month, day, this.monthFix);
   }

   public void sub(int year, int month, int day) {
      this.add(-year, -month, -day, this.monthFix);
   }

   public void addTime(int hour, int minute, int second, int millisecond, boolean monthFix) {
      this.add(0, 0, 0, hour, minute, second, millisecond, monthFix);
   }

   public void subTime(int hour, int minute, int second, int millisecond, boolean monthFix) {
      this.add(0, 0, 0, -hour, -minute, -second, -millisecond, monthFix);
   }

   public void addTime(int hour, int minute, int second, boolean monthFix) {
      this.add(0, 0, 0, hour, minute, second, 0, monthFix);
   }

   public void subTime(int hour, int minute, int second, boolean monthFix) {
      this.add(0, 0, 0, -hour, -minute, -second, 0, monthFix);
   }

   public void addTime(int hour, int minute, int second, int millisecond) {
      this.addTime(hour, minute, second, millisecond, this.monthFix);
   }

   public void subTime(int hour, int minute, int second, int millisecond) {
      this.addTime(-hour, -minute, -second, -millisecond, this.monthFix);
   }

   public void addTime(int hour, int minute, int second) {
      this.addTime(hour, minute, second, 0, this.monthFix);
   }

   public void subTime(int hour, int minute, int second) {
      this.addTime(-hour, -minute, -second, 0, this.monthFix);
   }

   public void addYear(int y, boolean monthFix) {
      this.add(y, 0, 0, monthFix);
   }

   public void subYear(int y, boolean monthFix) {
      this.add(-y, 0, 0, monthFix);
   }

   public void addYear(int y) {
      this.addYear(y, this.monthFix);
   }

   public void subYear(int y) {
      this.addYear(-y, this.monthFix);
   }

   public void addMonth(int m, boolean monthFix) {
      this.add(0, m, 0, monthFix);
   }

   public void subMonth(int m, boolean monthFix) {
      this.add(0, -m, 0, monthFix);
   }

   public void addMonth(int m) {
      this.addMonth(m, this.monthFix);
   }

   public void subMonth(int m) {
      this.addMonth(-m, this.monthFix);
   }

   public void addDay(int d, boolean monthFix) {
      this.add(0, 0, d, monthFix);
   }

   public void subDay(int d, boolean monthFix) {
      this.add(0, 0, -d, monthFix);
   }

   public void addDay(int d) {
      this.addDay(d, this.monthFix);
   }

   public void subDay(int d) {
      this.addDay(-d, this.monthFix);
   }

   public void addHour(int h, boolean monthFix) {
      this.addTime(h, 0, 0, 0, monthFix);
   }

   public void subHour(int h, boolean monthFix) {
      this.addTime(-h, 0, 0, 0, monthFix);
   }

   public void addHour(int h) {
      this.addHour(h, this.monthFix);
   }

   public void subHour(int h) {
      this.addHour(-h, this.monthFix);
   }

   public void addMinute(int m, boolean monthFix) {
      this.addTime(0, m, 0, 0, monthFix);
   }

   public void subMinute(int m, boolean monthFix) {
      this.addTime(0, -m, 0, 0, monthFix);
   }

   public void addMinute(int m) {
      this.addMinute(m, this.monthFix);
   }

   public void subMinute(int m) {
      this.addMinute(-m, this.monthFix);
   }

   public void addSecond(int s, boolean monthFix) {
      this.addTime(0, 0, s, 0, monthFix);
   }

   public void subSecond(int s, boolean monthFix) {
      this.addTime(0, 0, -s, 0, monthFix);
   }

   public void addSecond(int s) {
      this.addSecond(s, this.monthFix);
   }

   public void subSecond(int s) {
      this.addSecond(-s, this.monthFix);
   }

   public void addMillisecond(int ms, boolean monthFix) {
      this.addTime(0, 0, 0, ms, monthFix);
   }

   public void subMillisecond(int ms, boolean monthFix) {
      this.addTime(0, 0, 0, -ms, monthFix);
   }

   public void addMillisecond(int ms) {
      this.addMillisecond(ms, this.monthFix);
   }

   public void subMillisecond(int ms) {
      this.addMillisecond(-ms, this.monthFix);
   }

   public JDateTime(int year, int month, int day, int hour, int minute, int second, int millisecond) {
      this.trackDST = JDateTimeDefault.trackDST;
      this.monthFix = JDateTimeDefault.monthFix;
      this.timezone = JDateTimeDefault.timeZone == null ? TimeZone.getDefault() : JDateTimeDefault.timeZone;
      this.locale = JDateTimeDefault.locale == null ? Locale.getDefault() : JDateTimeDefault.locale;
      this.format = JDateTimeDefault.format;
      this.jdtFormatter = JDateTimeDefault.formatter;
      this.firstDayOfWeek = JDateTimeDefault.firstDayOfWeek;
      this.mustHaveDayOfFirstWeek = JDateTimeDefault.mustHaveDayOfFirstWeek;
      this.minDaysInFirstWeek = JDateTimeDefault.minDaysInFirstWeek;
      this.set(year, month, day, hour, minute, second, millisecond);
   }

   public void set(int year, int month, int day) {
      this.set(year, month, day, 0, 0, 0, 0);
   }

   public JDateTime(int year, int month, int day) {
      this.trackDST = JDateTimeDefault.trackDST;
      this.monthFix = JDateTimeDefault.monthFix;
      this.timezone = JDateTimeDefault.timeZone == null ? TimeZone.getDefault() : JDateTimeDefault.timeZone;
      this.locale = JDateTimeDefault.locale == null ? Locale.getDefault() : JDateTimeDefault.locale;
      this.format = JDateTimeDefault.format;
      this.jdtFormatter = JDateTimeDefault.formatter;
      this.firstDayOfWeek = JDateTimeDefault.firstDayOfWeek;
      this.mustHaveDayOfFirstWeek = JDateTimeDefault.mustHaveDayOfFirstWeek;
      this.minDaysInFirstWeek = JDateTimeDefault.minDaysInFirstWeek;
      this.set(year, month, day);
   }

   public void setTime(int hour, int minute, int second, int millisecond) {
      this.set(this.time.year, this.time.month, this.time.day, hour, minute, second, millisecond);
   }

   public void setDate(int year, int month, int day) {
      this.set(year, month, day, this.time.hour, this.time.minute, this.time.second, this.time.millisecond);
   }

   public JDateTime(long millis) {
      this.trackDST = JDateTimeDefault.trackDST;
      this.monthFix = JDateTimeDefault.monthFix;
      this.timezone = JDateTimeDefault.timeZone == null ? TimeZone.getDefault() : JDateTimeDefault.timeZone;
      this.locale = JDateTimeDefault.locale == null ? Locale.getDefault() : JDateTimeDefault.locale;
      this.format = JDateTimeDefault.format;
      this.jdtFormatter = JDateTimeDefault.formatter;
      this.firstDayOfWeek = JDateTimeDefault.firstDayOfWeek;
      this.mustHaveDayOfFirstWeek = JDateTimeDefault.mustHaveDayOfFirstWeek;
      this.minDaysInFirstWeek = JDateTimeDefault.minDaysInFirstWeek;
      this.setTimeInMillis(millis);
   }

   public void setTimeInMillis(long millis) {
      millis += (long)this.timezone.getOffset(millis);
      int integer = (int)(millis / 86400000L);
      double fraction = (double)(millis % 86400000L) / (double)8.64E7F;
      integer += JD_1970.integer;
      fraction += JD_1970.fraction;
      this.setJulianDate(new JulianDateStamp(integer, fraction));
   }

   public long getTimeInMillis() {
      double then = (this.jdate.fraction - JD_1970.fraction) * (double)8.64E7F;
      then += (double)((long)(this.jdate.integer - JD_1970.integer) * 86400000L);
      then -= (double)this.timezone.getOffset((long)then);
      then += then > (double)0.0F ? 1.0E-6 : -1.0E-6;
      return (long)then;
   }

   public void setYear(int y) {
      this.setDate(y, this.time.month, this.time.day);
   }

   public void setMonth(int m) {
      this.setDate(this.time.year, m, this.time.day);
   }

   public void setDay(int d) {
      this.setDate(this.time.year, this.time.month, d);
   }

   public void setHour(int h) {
      this.setTime(h, this.time.minute, this.time.second, this.time.millisecond);
   }

   public void setMinute(int m) {
      this.setTime(this.time.hour, m, this.time.second, this.time.millisecond);
   }

   public void setSecond(int s) {
      this.setTime(this.time.hour, this.time.minute, s, this.time.millisecond);
   }

   public void setSecond(int s, int m) {
      this.setTime(this.time.hour, this.time.minute, s, m);
   }

   public void setMillisecond(int m) {
      this.setTime(this.time.hour, this.time.minute, this.time.second, m);
   }

   public int getYear() {
      return this.time.year;
   }

   public int getMonth() {
      return this.time.month;
   }

   public int getDay() {
      return this.time.day;
   }

   public int getDayOfMonth() {
      return this.time.day;
   }

   public int getHour() {
      return this.time.hour;
   }

   public int getMinute() {
      return this.time.minute;
   }

   public int getSecond() {
      return this.time.second;
   }

   public int getMillisecond() {
      return this.time.millisecond;
   }

   public int getDayOfWeek() {
      return this.dayofweek;
   }

   public int getDayOfYear() {
      return this.dayofyear;
   }

   public boolean isLeapYear() {
      return this.leap;
   }

   public int getWeekOfYear() {
      return this.weekofyear;
   }

   public int getWeekOfMonth() {
      return this.weekofmonth;
   }

   public int getMonthLength(int m) {
      if (m >= 1 && m <= 12) {
         if (m == 2) {
            return this.leap ? 29 : 28;
         } else {
            return this.time.year == 1582 && this.time.month == 10 ? 21 : MONTH_LENGTH[m];
         }
      } else {
         throw new IllegalArgumentException("Invalid month: " + m);
      }
   }

   public int getMonthLength() {
      return this.getMonthLength(this.time.month);
   }

   public int getEra() {
      return this.time.year > 0 ? 1 : 0;
   }

   public int getMillisOfDay() {
      return ((this.time.hour * 60 + this.time.minute) * 60 + this.time.second) * 1000 + this.time.millisecond;
   }

   public void setCurrentTime() {
      this.setTimeInMillis(System.currentTimeMillis());
   }

   public JDateTime() {
      this.trackDST = JDateTimeDefault.trackDST;
      this.monthFix = JDateTimeDefault.monthFix;
      this.timezone = JDateTimeDefault.timeZone == null ? TimeZone.getDefault() : JDateTimeDefault.timeZone;
      this.locale = JDateTimeDefault.locale == null ? Locale.getDefault() : JDateTimeDefault.locale;
      this.format = JDateTimeDefault.format;
      this.jdtFormatter = JDateTimeDefault.formatter;
      this.firstDayOfWeek = JDateTimeDefault.firstDayOfWeek;
      this.mustHaveDayOfFirstWeek = JDateTimeDefault.mustHaveDayOfFirstWeek;
      this.minDaysInFirstWeek = JDateTimeDefault.minDaysInFirstWeek;
      this.setCurrentTime();
   }

   public JDateTime(Calendar calendar) {
      this.trackDST = JDateTimeDefault.trackDST;
      this.monthFix = JDateTimeDefault.monthFix;
      this.timezone = JDateTimeDefault.timeZone == null ? TimeZone.getDefault() : JDateTimeDefault.timeZone;
      this.locale = JDateTimeDefault.locale == null ? Locale.getDefault() : JDateTimeDefault.locale;
      this.format = JDateTimeDefault.format;
      this.jdtFormatter = JDateTimeDefault.formatter;
      this.firstDayOfWeek = JDateTimeDefault.firstDayOfWeek;
      this.mustHaveDayOfFirstWeek = JDateTimeDefault.mustHaveDayOfFirstWeek;
      this.minDaysInFirstWeek = JDateTimeDefault.minDaysInFirstWeek;
      this.setDateTime(calendar);
   }

   public void setDateTime(Calendar calendar) {
      this.setTimeInMillis(calendar.getTimeInMillis());
      this.changeTimeZone(calendar.getTimeZone());
   }

   public JDateTime(Date date) {
      this.trackDST = JDateTimeDefault.trackDST;
      this.monthFix = JDateTimeDefault.monthFix;
      this.timezone = JDateTimeDefault.timeZone == null ? TimeZone.getDefault() : JDateTimeDefault.timeZone;
      this.locale = JDateTimeDefault.locale == null ? Locale.getDefault() : JDateTimeDefault.locale;
      this.format = JDateTimeDefault.format;
      this.jdtFormatter = JDateTimeDefault.formatter;
      this.firstDayOfWeek = JDateTimeDefault.firstDayOfWeek;
      this.mustHaveDayOfFirstWeek = JDateTimeDefault.mustHaveDayOfFirstWeek;
      this.minDaysInFirstWeek = JDateTimeDefault.minDaysInFirstWeek;
      this.setDateTime(date);
   }

   public void setDateTime(Date date) {
      this.setTimeInMillis(date.getTime());
   }

   public Date convertToDate() {
      return new Date(this.getTimeInMillis());
   }

   public Calendar convertToCalendar() {
      Calendar calendar = Calendar.getInstance(this.getTimeZone());
      calendar.setTimeInMillis(this.getTimeInMillis());
      return calendar;
   }

   public java.sql.Date convertToSqlDate() {
      return new java.sql.Date(this.getTimeInMillis());
   }

   public Time convertToSqlTime() {
      return new Time(this.getTimeInMillis());
   }

   public Timestamp convertToSqlTimestamp() {
      return new Timestamp(this.getTimeInMillis());
   }

   public JDateTime(DateTimeStamp dts) {
      this.trackDST = JDateTimeDefault.trackDST;
      this.monthFix = JDateTimeDefault.monthFix;
      this.timezone = JDateTimeDefault.timeZone == null ? TimeZone.getDefault() : JDateTimeDefault.timeZone;
      this.locale = JDateTimeDefault.locale == null ? Locale.getDefault() : JDateTimeDefault.locale;
      this.format = JDateTimeDefault.format;
      this.jdtFormatter = JDateTimeDefault.formatter;
      this.firstDayOfWeek = JDateTimeDefault.firstDayOfWeek;
      this.mustHaveDayOfFirstWeek = JDateTimeDefault.mustHaveDayOfFirstWeek;
      this.minDaysInFirstWeek = JDateTimeDefault.minDaysInFirstWeek;
      this.setDateTimeStamp(dts);
   }

   public JDateTime(JulianDateStamp jds) {
      this.trackDST = JDateTimeDefault.trackDST;
      this.monthFix = JDateTimeDefault.monthFix;
      this.timezone = JDateTimeDefault.timeZone == null ? TimeZone.getDefault() : JDateTimeDefault.timeZone;
      this.locale = JDateTimeDefault.locale == null ? Locale.getDefault() : JDateTimeDefault.locale;
      this.format = JDateTimeDefault.format;
      this.jdtFormatter = JDateTimeDefault.formatter;
      this.firstDayOfWeek = JDateTimeDefault.firstDayOfWeek;
      this.mustHaveDayOfFirstWeek = JDateTimeDefault.mustHaveDayOfFirstWeek;
      this.minDaysInFirstWeek = JDateTimeDefault.minDaysInFirstWeek;
      this.setJulianDate(jds);
   }

   public JDateTime(double jd) {
      this.trackDST = JDateTimeDefault.trackDST;
      this.monthFix = JDateTimeDefault.monthFix;
      this.timezone = JDateTimeDefault.timeZone == null ? TimeZone.getDefault() : JDateTimeDefault.timeZone;
      this.locale = JDateTimeDefault.locale == null ? Locale.getDefault() : JDateTimeDefault.locale;
      this.format = JDateTimeDefault.format;
      this.jdtFormatter = JDateTimeDefault.formatter;
      this.firstDayOfWeek = JDateTimeDefault.firstDayOfWeek;
      this.mustHaveDayOfFirstWeek = JDateTimeDefault.mustHaveDayOfFirstWeek;
      this.minDaysInFirstWeek = JDateTimeDefault.minDaysInFirstWeek;
      this.setJulianDate(new JulianDateStamp(jd));
   }

   public JDateTime(String src) {
      this.trackDST = JDateTimeDefault.trackDST;
      this.monthFix = JDateTimeDefault.monthFix;
      this.timezone = JDateTimeDefault.timeZone == null ? TimeZone.getDefault() : JDateTimeDefault.timeZone;
      this.locale = JDateTimeDefault.locale == null ? Locale.getDefault() : JDateTimeDefault.locale;
      this.format = JDateTimeDefault.format;
      this.jdtFormatter = JDateTimeDefault.formatter;
      this.firstDayOfWeek = JDateTimeDefault.firstDayOfWeek;
      this.mustHaveDayOfFirstWeek = JDateTimeDefault.mustHaveDayOfFirstWeek;
      this.minDaysInFirstWeek = JDateTimeDefault.minDaysInFirstWeek;
      this.parse(src);
   }

   public JDateTime(String src, String template) {
      this.trackDST = JDateTimeDefault.trackDST;
      this.monthFix = JDateTimeDefault.monthFix;
      this.timezone = JDateTimeDefault.timeZone == null ? TimeZone.getDefault() : JDateTimeDefault.timeZone;
      this.locale = JDateTimeDefault.locale == null ? Locale.getDefault() : JDateTimeDefault.locale;
      this.format = JDateTimeDefault.format;
      this.jdtFormatter = JDateTimeDefault.formatter;
      this.firstDayOfWeek = JDateTimeDefault.firstDayOfWeek;
      this.mustHaveDayOfFirstWeek = JDateTimeDefault.mustHaveDayOfFirstWeek;
      this.minDaysInFirstWeek = JDateTimeDefault.minDaysInFirstWeek;
      this.parse(src, template);
   }

   public JDateTime(String src, JdtFormat jdtFormat) {
      this.trackDST = JDateTimeDefault.trackDST;
      this.monthFix = JDateTimeDefault.monthFix;
      this.timezone = JDateTimeDefault.timeZone == null ? TimeZone.getDefault() : JDateTimeDefault.timeZone;
      this.locale = JDateTimeDefault.locale == null ? Locale.getDefault() : JDateTimeDefault.locale;
      this.format = JDateTimeDefault.format;
      this.jdtFormatter = JDateTimeDefault.formatter;
      this.firstDayOfWeek = JDateTimeDefault.firstDayOfWeek;
      this.mustHaveDayOfFirstWeek = JDateTimeDefault.mustHaveDayOfFirstWeek;
      this.minDaysInFirstWeek = JDateTimeDefault.minDaysInFirstWeek;
      this.parse(src, jdtFormat);
   }

   public boolean isTrackDST() {
      return this.trackDST;
   }

   public void setTrackDST(boolean trackDST) {
      this.trackDST = trackDST;
   }

   public boolean isMonthFix() {
      return this.monthFix;
   }

   public void setMonthFix(boolean monthFix) {
      this.monthFix = monthFix;
   }

   public void changeTimeZone(TimeZone timezone) {
      long now = this.getTimeInMillis();
      int difference = TimeZoneUtil.getOffsetDifference(now, this.timezone, timezone);
      this.timezone = timezone;
      if (difference != 0) {
         this.addMillisecond(difference);
      }

   }

   public void changeTimeZone(TimeZone from, TimeZone to) {
      this.timezone = from;
      this.changeTimeZone(to);
   }

   public void setTimeZone(TimeZone timezone) {
      this.timezone = timezone;
   }

   public TimeZone getTimeZone() {
      return this.timezone;
   }

   public boolean isInDaylightTime() {
      long now = this.getTimeInMillis();
      int offset = this.timezone.getOffset(now);
      int rawOffset = this.timezone.getRawOffset();
      return offset != rawOffset;
   }

   public void setLocale(Locale locale) {
      this.locale = locale;
   }

   public Locale getLocale() {
      return this.locale;
   }

   public void setFormat(String format) {
      this.format = format;
   }

   public String getFormat() {
      return this.format;
   }

   public void setJdtFormatter(JdtFormatter jdtFormatter) {
      this.jdtFormatter = jdtFormatter;
   }

   public JdtFormatter getJdtFormatter() {
      return this.jdtFormatter;
   }

   public void setJdtFormat(JdtFormat jdtFormat) {
      this.format = jdtFormat.getFormat();
      this.jdtFormatter = jdtFormat.getFormatter();
   }

   public String toString(String format) {
      return this.jdtFormatter.convert(this, format);
   }

   public String toString() {
      return this.jdtFormatter.convert(this, this.format);
   }

   public String toString(JdtFormat jdtFormat) {
      return jdtFormat.convert(this);
   }

   public void parse(String src, String format) {
      this.setDateTimeStamp(this.jdtFormatter.parse(src, format));
   }

   public void parse(String src) {
      this.setDateTimeStamp(this.jdtFormatter.parse(src, this.format));
   }

   public void parse(String src, JdtFormat jdtFormat) {
      this.setDateTimeStamp(jdtFormat.parse(src));
   }

   public boolean isValid(String s) {
      return this.isValid(s, this.format);
   }

   public boolean isValid(String s, String template) {
      DateTimeStamp dtsOriginal;
      try {
         dtsOriginal = this.jdtFormatter.parse(s, template);
      } catch (Exception var5) {
         return false;
      }

      return dtsOriginal == null ? false : TimeUtil.isValidDateTime(dtsOriginal);
   }

   public void setWeekDefinition(int start, int must) {
      if (start >= 1 && start <= 7) {
         this.firstDayOfWeek = start;
      }

      if (must >= 1 && must <= 7) {
         this.mustHaveDayOfFirstWeek = must;
         this.minDaysInFirstWeek = convertMin2Must(this.firstDayOfWeek, must);
      }

   }

   public int getFirstDayOfWeek() {
      return this.firstDayOfWeek;
   }

   public int getMustHaveDayOfFirstWeek() {
      return this.mustHaveDayOfFirstWeek;
   }

   public int getMinDaysInFirstWeek() {
      return this.minDaysInFirstWeek;
   }

   public void setWeekDefinitionAlt(int start, int min) {
      if (start >= 1 && start <= 7) {
         this.firstDayOfWeek = start;
      }

      if (min >= 1 && min <= 7) {
         this.mustHaveDayOfFirstWeek = convertMin2Must(this.firstDayOfWeek, min);
         this.minDaysInFirstWeek = min;
      }

   }

   private static int convertMin2Must(int start, int min) {
      int must = 8 - min + (start - 1);
      if (must > 7) {
         must -= 7;
      }

      return must;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof JDateTime)) {
         return false;
      } else {
         JDateTime jdt = (JDateTime)obj;
         return this.monthFix == jdt.monthFix && this.firstDayOfWeek == jdt.firstDayOfWeek && this.mustHaveDayOfFirstWeek == jdt.mustHaveDayOfFirstWeek && this.time.equals(jdt.time) && this.timezone.equals(jdt.timezone);
      }
   }

   public int hashCode() {
      int result = 173;
      result = HashCode.hash(result, (Object)this.time);
      result = HashCode.hash(result, (Object)this.timezone);
      result = HashCode.hash(result, this.monthFix);
      result = HashCode.hash(result, this.firstDayOfWeek);
      result = HashCode.hash(result, this.mustHaveDayOfFirstWeek);
      return result;
   }

   public JDateTime clone() {
      JDateTime jdt = new JDateTime(this.jdate);
      jdt.monthFix = this.monthFix;
      jdt.timezone = this.timezone;
      jdt.locale = this.locale;
      jdt.format = this.format;
      jdt.jdtFormatter = this.jdtFormatter;
      jdt.firstDayOfWeek = this.firstDayOfWeek;
      jdt.mustHaveDayOfFirstWeek = this.mustHaveDayOfFirstWeek;
      jdt.trackDST = this.trackDST;
      return jdt;
   }

   public int compareTo(Object o) {
      return this.time.compareTo(((JDateTime)o).getDateTimeStamp());
   }

   public int compareTo(JDateTime jd) {
      return this.time.compareTo(jd.getDateTimeStamp());
   }

   public int compareDateTo(JDateTime jd) {
      return this.time.compareDateTo(jd.getDateTimeStamp());
   }

   public boolean isAfter(JDateTime then) {
      return this.time.compareTo(then.getDateTimeStamp()) > 0;
   }

   public boolean isBefore(JDateTime then) {
      return this.time.compareTo(then.getDateTimeStamp()) < 0;
   }

   public boolean isAfterDate(JDateTime then) {
      return this.time.compareDateTo(then.getDateTimeStamp()) > 0;
   }

   public boolean isBeforeDate(JDateTime then) {
      return this.time.compareDateTo(then.getDateTimeStamp()) < 0;
   }

   public int daysBetween(JDateTime then) {
      return this.jdate.daysBetween(then.jdate);
   }

   public int daysBetween(JulianDateStamp then) {
      return this.jdate.daysBetween(then);
   }

   public double getJulianDateDouble() {
      return this.jdate.doubleValue();
   }

   public void setJulianDate(double jd) {
      this.setJulianDate(new JulianDateStamp(jd));
   }

   public boolean equalsDate(int year, int month, int day) {
      return this.time.year == year && this.time.month == month && this.time.day == day;
   }

   public boolean equalsDate(JDateTime date) {
      return this.time.isEqualDate(date.time);
   }

   public boolean equalsTime(JDateTime date) {
      return this.time.isEqualTime(date.time);
   }
}
