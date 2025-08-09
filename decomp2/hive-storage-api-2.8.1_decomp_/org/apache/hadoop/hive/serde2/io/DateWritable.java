package org.apache.hadoop.hive.serde2.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

public class DateWritable implements WritableComparable {
   private static final long MILLIS_PER_DAY;
   private static final ThreadLocal LOCAL_TIMEZONE;
   private static final ThreadLocal UTC_CALENDAR;
   private static final ThreadLocal LOCAL_CALENDAR;
   private int daysSinceEpoch = 0;

   public DateWritable() {
   }

   public DateWritable(DateWritable d) {
      this.set(d);
   }

   public DateWritable(Date d) {
      this.set(d);
   }

   public DateWritable(int d) {
      this.set(d);
   }

   public void set(int d) {
      this.daysSinceEpoch = d;
   }

   public void set(Date d) {
      if (d == null) {
         this.daysSinceEpoch = 0;
      } else {
         this.set(dateToDays(d));
      }
   }

   public void set(DateWritable d) {
      this.set(d.daysSinceEpoch);
   }

   public Date get() {
      return this.get(true);
   }

   public Date get(boolean doesTimeMatter) {
      return new Date(daysToMillis(this.daysSinceEpoch, doesTimeMatter));
   }

   public int getDays() {
      return this.daysSinceEpoch;
   }

   public long getTimeInSeconds() {
      return this.get().getTime() / 1000L;
   }

   public static Date timeToDate(long l) {
      return new Date(l * 1000L);
   }

   public static long daysToMillis(int d) {
      return daysToMillis(d, true);
   }

   public static long daysToMillis(int d, boolean doesTimeMatter) {
      long utcMidnight = (long)d * MILLIS_PER_DAY;
      long utcMidnightOffset = (long)((TimeZone)LOCAL_TIMEZONE.get()).getOffset(utcMidnight);
      long hopefullyMidnight = utcMidnight - utcMidnightOffset;
      long offsetAtHM = (long)((TimeZone)LOCAL_TIMEZONE.get()).getOffset(hopefullyMidnight);
      if (utcMidnightOffset == offsetAtHM) {
         return hopefullyMidnight;
      } else if (!doesTimeMatter) {
         return daysToMillis(d + 1) - (MILLIS_PER_DAY >> 1);
      } else {
         Calendar utc = (Calendar)UTC_CALENDAR.get();
         Calendar local = (Calendar)LOCAL_CALENDAR.get();
         utc.setTimeInMillis(utcMidnight);
         local.set(utc.get(1), utc.get(2), utc.get(5));
         return local.getTimeInMillis();
      }
   }

   public static int millisToDays(long millisLocal) {
      long millisUtc = millisLocal + (long)((TimeZone)LOCAL_TIMEZONE.get()).getOffset(millisLocal);
      int days;
      if (millisUtc >= 0L) {
         days = (int)(millisUtc / MILLIS_PER_DAY);
      } else {
         days = (int)((millisUtc - 86399999L) / MILLIS_PER_DAY);
      }

      return days;
   }

   public static int dateToDays(Date d) {
      long millisLocal = d.getTime();
      return millisToDays(millisLocal);
   }

   public void readFields(DataInput in) throws IOException {
      this.daysSinceEpoch = WritableUtils.readVInt(in);
   }

   public void write(DataOutput out) throws IOException {
      WritableUtils.writeVInt(out, this.daysSinceEpoch);
   }

   public int compareTo(DateWritable d) {
      return this.daysSinceEpoch - d.daysSinceEpoch;
   }

   public boolean equals(Object o) {
      if (!(o instanceof DateWritable)) {
         return false;
      } else {
         return this.compareTo((DateWritable)o) == 0;
      }
   }

   public String toString() {
      return this.get(false).toString();
   }

   public int hashCode() {
      return this.daysSinceEpoch;
   }

   static {
      MILLIS_PER_DAY = TimeUnit.DAYS.toMillis(1L);
      LOCAL_TIMEZONE = new ThreadLocal() {
         protected TimeZone initialValue() {
            return Calendar.getInstance().getTimeZone();
         }
      };
      UTC_CALENDAR = new ThreadLocal() {
         protected Calendar initialValue() {
            return new GregorianCalendar(TimeZone.getTimeZone("UTC"));
         }
      };
      LOCAL_CALENDAR = new ThreadLocal() {
         protected Calendar initialValue() {
            return Calendar.getInstance();
         }
      };
   }
}
