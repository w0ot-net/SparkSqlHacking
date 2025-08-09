package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BaseLocal;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.PartialConverter;
import org.joda.time.field.AbstractReadableInstantFieldProperty;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class LocalDateTime extends BaseLocal implements ReadablePartial, Serializable {
   private static final long serialVersionUID = -268716875315837168L;
   private static final int YEAR = 0;
   private static final int MONTH_OF_YEAR = 1;
   private static final int DAY_OF_MONTH = 2;
   private static final int MILLIS_OF_DAY = 3;
   private final long iLocalMillis;
   private final Chronology iChronology;

   public static LocalDateTime now() {
      return new LocalDateTime();
   }

   public static LocalDateTime now(DateTimeZone var0) {
      if (var0 == null) {
         throw new NullPointerException("Zone must not be null");
      } else {
         return new LocalDateTime(var0);
      }
   }

   public static LocalDateTime now(Chronology var0) {
      if (var0 == null) {
         throw new NullPointerException("Chronology must not be null");
      } else {
         return new LocalDateTime(var0);
      }
   }

   @FromString
   public static LocalDateTime parse(String var0) {
      return parse(var0, ISODateTimeFormat.localDateOptionalTimeParser());
   }

   public static LocalDateTime parse(String var0, DateTimeFormatter var1) {
      return var1.parseLocalDateTime(var0);
   }

   public static LocalDateTime fromCalendarFields(Calendar var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("The calendar must not be null");
      } else {
         int var1 = var0.get(0);
         int var2 = var0.get(1);
         return new LocalDateTime(var1 == 1 ? var2 : 1 - var2, var0.get(2) + 1, var0.get(5), var0.get(11), var0.get(12), var0.get(13), var0.get(14));
      }
   }

   public static LocalDateTime fromDateFields(Date var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("The date must not be null");
      } else if (var0.getTime() < 0L) {
         GregorianCalendar var1 = new GregorianCalendar();
         var1.setTime(var0);
         return fromCalendarFields(var1);
      } else {
         return new LocalDateTime(var0.getYear() + 1900, var0.getMonth() + 1, var0.getDate(), var0.getHours(), var0.getMinutes(), var0.getSeconds(), ((int)(var0.getTime() % 1000L) + 1000) % 1000);
      }
   }

   public LocalDateTime() {
      this(DateTimeUtils.currentTimeMillis(), (Chronology)ISOChronology.getInstance());
   }

   public LocalDateTime(DateTimeZone var1) {
      this(DateTimeUtils.currentTimeMillis(), (Chronology)ISOChronology.getInstance(var1));
   }

   public LocalDateTime(Chronology var1) {
      this(DateTimeUtils.currentTimeMillis(), var1);
   }

   public LocalDateTime(long var1) {
      this(var1, (Chronology)ISOChronology.getInstance());
   }

   public LocalDateTime(long var1, DateTimeZone var3) {
      this(var1, (Chronology)ISOChronology.getInstance(var3));
   }

   public LocalDateTime(long var1, Chronology var3) {
      var3 = DateTimeUtils.getChronology(var3);
      long var4 = var3.getZone().getMillisKeepLocal(DateTimeZone.UTC, var1);
      this.iLocalMillis = var4;
      this.iChronology = var3.withUTC();
   }

   public LocalDateTime(Object var1) {
      this(var1, (Chronology)null);
   }

   public LocalDateTime(Object var1, DateTimeZone var2) {
      PartialConverter var3 = ConverterManager.getInstance().getPartialConverter(var1);
      Chronology var4 = var3.getChronology(var1, var2);
      var4 = DateTimeUtils.getChronology(var4);
      this.iChronology = var4.withUTC();
      int[] var5 = var3.getPartialValues(this, var1, var4, ISODateTimeFormat.localDateOptionalTimeParser());
      this.iLocalMillis = this.iChronology.getDateTimeMillis(var5[0], var5[1], var5[2], var5[3]);
   }

   public LocalDateTime(Object var1, Chronology var2) {
      PartialConverter var3 = ConverterManager.getInstance().getPartialConverter(var1);
      var2 = var3.getChronology(var1, var2);
      var2 = DateTimeUtils.getChronology(var2);
      this.iChronology = var2.withUTC();
      int[] var4 = var3.getPartialValues(this, var1, var2, ISODateTimeFormat.localDateOptionalTimeParser());
      this.iLocalMillis = this.iChronology.getDateTimeMillis(var4[0], var4[1], var4[2], var4[3]);
   }

   public LocalDateTime(int var1, int var2, int var3, int var4, int var5) {
      this(var1, var2, var3, var4, var5, 0, 0, ISOChronology.getInstanceUTC());
   }

   public LocalDateTime(int var1, int var2, int var3, int var4, int var5, int var6) {
      this(var1, var2, var3, var4, var5, var6, 0, ISOChronology.getInstanceUTC());
   }

   public LocalDateTime(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      this(var1, var2, var3, var4, var5, var6, var7, ISOChronology.getInstanceUTC());
   }

   public LocalDateTime(int var1, int var2, int var3, int var4, int var5, int var6, int var7, Chronology var8) {
      var8 = DateTimeUtils.getChronology(var8).withUTC();
      long var9 = var8.getDateTimeMillis(var1, var2, var3, var4, var5, var6, var7);
      this.iChronology = var8;
      this.iLocalMillis = var9;
   }

   private Object readResolve() {
      if (this.iChronology == null) {
         return new LocalDateTime(this.iLocalMillis, ISOChronology.getInstanceUTC());
      } else {
         return !DateTimeZone.UTC.equals(this.iChronology.getZone()) ? new LocalDateTime(this.iLocalMillis, this.iChronology.withUTC()) : this;
      }
   }

   public int size() {
      return 4;
   }

   protected DateTimeField getField(int var1, Chronology var2) {
      switch (var1) {
         case 0:
            return var2.year();
         case 1:
            return var2.monthOfYear();
         case 2:
            return var2.dayOfMonth();
         case 3:
            return var2.millisOfDay();
         default:
            throw new IndexOutOfBoundsException("Invalid index: " + var1);
      }
   }

   public int getValue(int var1) {
      switch (var1) {
         case 0:
            return this.getChronology().year().get(this.getLocalMillis());
         case 1:
            return this.getChronology().monthOfYear().get(this.getLocalMillis());
         case 2:
            return this.getChronology().dayOfMonth().get(this.getLocalMillis());
         case 3:
            return this.getChronology().millisOfDay().get(this.getLocalMillis());
         default:
            throw new IndexOutOfBoundsException("Invalid index: " + var1);
      }
   }

   public int get(DateTimeFieldType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("The DateTimeFieldType must not be null");
      } else {
         return var1.getField(this.getChronology()).get(this.getLocalMillis());
      }
   }

   public boolean isSupported(DateTimeFieldType var1) {
      return var1 == null ? false : var1.getField(this.getChronology()).isSupported();
   }

   public boolean isSupported(DurationFieldType var1) {
      return var1 == null ? false : var1.getField(this.getChronology()).isSupported();
   }

   protected long getLocalMillis() {
      return this.iLocalMillis;
   }

   public Chronology getChronology() {
      return this.iChronology;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else {
         if (var1 instanceof LocalDateTime) {
            LocalDateTime var2 = (LocalDateTime)var1;
            if (this.iChronology.equals(var2.iChronology)) {
               return this.iLocalMillis == var2.iLocalMillis;
            }
         }

         return super.equals(var1);
      }
   }

   public int hashCode() {
      int var1 = 157;
      var1 = 23 * var1 + this.iChronology.year().get(this.iLocalMillis);
      var1 = 23 * var1 + this.iChronology.year().getType().hashCode();
      var1 = 23 * var1 + this.iChronology.monthOfYear().get(this.iLocalMillis);
      var1 = 23 * var1 + this.iChronology.monthOfYear().getType().hashCode();
      var1 = 23 * var1 + this.iChronology.dayOfMonth().get(this.iLocalMillis);
      var1 = 23 * var1 + this.iChronology.dayOfMonth().getType().hashCode();
      var1 = 23 * var1 + this.iChronology.millisOfDay().get(this.iLocalMillis);
      var1 = 23 * var1 + this.iChronology.millisOfDay().getType().hashCode();
      var1 += this.getChronology().hashCode();
      return var1;
   }

   public int compareTo(ReadablePartial var1) {
      if (this == var1) {
         return 0;
      } else {
         if (var1 instanceof LocalDateTime) {
            LocalDateTime var2 = (LocalDateTime)var1;
            if (this.iChronology.equals(var2.iChronology)) {
               return this.iLocalMillis < var2.iLocalMillis ? -1 : (this.iLocalMillis == var2.iLocalMillis ? 0 : 1);
            }
         }

         return super.compareTo(var1);
      }
   }

   public DateTime toDateTime() {
      return this.toDateTime((DateTimeZone)null);
   }

   public DateTime toDateTime(DateTimeZone var1) {
      var1 = DateTimeUtils.getZone(var1);
      Chronology var2 = this.iChronology.withZone(var1);
      return new DateTime(this.getYear(), this.getMonthOfYear(), this.getDayOfMonth(), this.getHourOfDay(), this.getMinuteOfHour(), this.getSecondOfMinute(), this.getMillisOfSecond(), var2);
   }

   public LocalDate toLocalDate() {
      return new LocalDate(this.getLocalMillis(), this.getChronology());
   }

   public LocalTime toLocalTime() {
      return new LocalTime(this.getLocalMillis(), this.getChronology());
   }

   public Date toDate() {
      int var1 = this.getDayOfMonth();
      Date var2 = new Date(this.getYear() - 1900, this.getMonthOfYear() - 1, var1, this.getHourOfDay(), this.getMinuteOfHour(), this.getSecondOfMinute());
      var2.setTime(var2.getTime() + (long)this.getMillisOfSecond());
      return this.correctDstTransition(var2, TimeZone.getDefault());
   }

   public Date toDate(TimeZone var1) {
      Calendar var2 = Calendar.getInstance(var1);
      var2.clear();
      var2.set(this.getYear(), this.getMonthOfYear() - 1, this.getDayOfMonth(), this.getHourOfDay(), this.getMinuteOfHour(), this.getSecondOfMinute());
      Date var3 = var2.getTime();
      var3.setTime(var3.getTime() + (long)this.getMillisOfSecond());
      return this.correctDstTransition(var3, var1);
   }

   private Date correctDstTransition(Date var1, TimeZone var2) {
      Calendar var3 = Calendar.getInstance(var2);
      var3.setTime(var1);
      LocalDateTime var4 = fromCalendarFields(var3);
      if (!var4.isBefore(this)) {
         if (var4.equals(this)) {
            Calendar var5 = Calendar.getInstance(var2);
            var5.setTimeInMillis(var3.getTimeInMillis() - (long)var2.getDSTSavings());
            var4 = fromCalendarFields(var5);
            if (var4.equals(this)) {
               var3 = var5;
            }
         }
      } else {
         while(var4.isBefore(this)) {
            var3.setTimeInMillis(var3.getTimeInMillis() + 60000L);
            var4 = fromCalendarFields(var3);
         }

         while(!var4.isBefore(this)) {
            var3.setTimeInMillis(var3.getTimeInMillis() - 1000L);
            var4 = fromCalendarFields(var3);
         }

         var3.setTimeInMillis(var3.getTimeInMillis() + 1000L);
      }

      return var3.getTime();
   }

   LocalDateTime withLocalMillis(long var1) {
      return var1 == this.getLocalMillis() ? this : new LocalDateTime(var1, this.getChronology());
   }

   public LocalDateTime withDate(int var1, int var2, int var3) {
      Chronology var4 = this.getChronology();
      long var5 = this.getLocalMillis();
      var5 = var4.year().set(var5, var1);
      var5 = var4.monthOfYear().set(var5, var2);
      var5 = var4.dayOfMonth().set(var5, var3);
      return this.withLocalMillis(var5);
   }

   public LocalDateTime withTime(int var1, int var2, int var3, int var4) {
      Chronology var5 = this.getChronology();
      long var6 = this.getLocalMillis();
      var6 = var5.hourOfDay().set(var6, var1);
      var6 = var5.minuteOfHour().set(var6, var2);
      var6 = var5.secondOfMinute().set(var6, var3);
      var6 = var5.millisOfSecond().set(var6, var4);
      return this.withLocalMillis(var6);
   }

   public LocalDateTime withFields(ReadablePartial var1) {
      return var1 == null ? this : this.withLocalMillis(this.getChronology().set(var1, this.getLocalMillis()));
   }

   public LocalDateTime withField(DateTimeFieldType var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Field must not be null");
      } else {
         long var3 = var1.getField(this.getChronology()).set(this.getLocalMillis(), var2);
         return this.withLocalMillis(var3);
      }
   }

   public LocalDateTime withFieldAdded(DurationFieldType var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Field must not be null");
      } else if (var2 == 0) {
         return this;
      } else {
         long var3 = var1.getField(this.getChronology()).add(this.getLocalMillis(), var2);
         return this.withLocalMillis(var3);
      }
   }

   public LocalDateTime withDurationAdded(ReadableDuration var1, int var2) {
      if (var1 != null && var2 != 0) {
         long var3 = this.getChronology().add(this.getLocalMillis(), var1.getMillis(), var2);
         return this.withLocalMillis(var3);
      } else {
         return this;
      }
   }

   public LocalDateTime withPeriodAdded(ReadablePeriod var1, int var2) {
      if (var1 != null && var2 != 0) {
         long var3 = this.getChronology().add(var1, this.getLocalMillis(), var2);
         return this.withLocalMillis(var3);
      } else {
         return this;
      }
   }

   public LocalDateTime plus(ReadableDuration var1) {
      return this.withDurationAdded(var1, 1);
   }

   public LocalDateTime plus(ReadablePeriod var1) {
      return this.withPeriodAdded(var1, 1);
   }

   public LocalDateTime plusYears(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().years().add(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalDateTime plusMonths(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().months().add(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalDateTime plusWeeks(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().weeks().add(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalDateTime plusDays(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().days().add(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalDateTime plusHours(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().hours().add(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalDateTime plusMinutes(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().minutes().add(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalDateTime plusSeconds(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().seconds().add(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalDateTime plusMillis(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().millis().add(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalDateTime minus(ReadableDuration var1) {
      return this.withDurationAdded(var1, -1);
   }

   public LocalDateTime minus(ReadablePeriod var1) {
      return this.withPeriodAdded(var1, -1);
   }

   public LocalDateTime minusYears(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().years().subtract(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalDateTime minusMonths(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().months().subtract(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalDateTime minusWeeks(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().weeks().subtract(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalDateTime minusDays(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().days().subtract(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalDateTime minusHours(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().hours().subtract(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalDateTime minusMinutes(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().minutes().subtract(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalDateTime minusSeconds(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().seconds().subtract(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalDateTime minusMillis(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().millis().subtract(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public Property property(DateTimeFieldType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("The DateTimeFieldType must not be null");
      } else if (!this.isSupported(var1)) {
         throw new IllegalArgumentException("Field '" + var1 + "' is not supported");
      } else {
         return new Property(this, var1.getField(this.getChronology()));
      }
   }

   public int getEra() {
      return this.getChronology().era().get(this.getLocalMillis());
   }

   public int getCenturyOfEra() {
      return this.getChronology().centuryOfEra().get(this.getLocalMillis());
   }

   public int getYearOfEra() {
      return this.getChronology().yearOfEra().get(this.getLocalMillis());
   }

   public int getYearOfCentury() {
      return this.getChronology().yearOfCentury().get(this.getLocalMillis());
   }

   public int getYear() {
      return this.getChronology().year().get(this.getLocalMillis());
   }

   public int getWeekyear() {
      return this.getChronology().weekyear().get(this.getLocalMillis());
   }

   public int getMonthOfYear() {
      return this.getChronology().monthOfYear().get(this.getLocalMillis());
   }

   public int getWeekOfWeekyear() {
      return this.getChronology().weekOfWeekyear().get(this.getLocalMillis());
   }

   public int getDayOfYear() {
      return this.getChronology().dayOfYear().get(this.getLocalMillis());
   }

   public int getDayOfMonth() {
      return this.getChronology().dayOfMonth().get(this.getLocalMillis());
   }

   public int getDayOfWeek() {
      return this.getChronology().dayOfWeek().get(this.getLocalMillis());
   }

   public int getHourOfDay() {
      return this.getChronology().hourOfDay().get(this.getLocalMillis());
   }

   public int getMinuteOfHour() {
      return this.getChronology().minuteOfHour().get(this.getLocalMillis());
   }

   public int getSecondOfMinute() {
      return this.getChronology().secondOfMinute().get(this.getLocalMillis());
   }

   public int getMillisOfSecond() {
      return this.getChronology().millisOfSecond().get(this.getLocalMillis());
   }

   public int getMillisOfDay() {
      return this.getChronology().millisOfDay().get(this.getLocalMillis());
   }

   public LocalDateTime withEra(int var1) {
      return this.withLocalMillis(this.getChronology().era().set(this.getLocalMillis(), var1));
   }

   public LocalDateTime withCenturyOfEra(int var1) {
      return this.withLocalMillis(this.getChronology().centuryOfEra().set(this.getLocalMillis(), var1));
   }

   public LocalDateTime withYearOfEra(int var1) {
      return this.withLocalMillis(this.getChronology().yearOfEra().set(this.getLocalMillis(), var1));
   }

   public LocalDateTime withYearOfCentury(int var1) {
      return this.withLocalMillis(this.getChronology().yearOfCentury().set(this.getLocalMillis(), var1));
   }

   public LocalDateTime withYear(int var1) {
      return this.withLocalMillis(this.getChronology().year().set(this.getLocalMillis(), var1));
   }

   public LocalDateTime withWeekyear(int var1) {
      return this.withLocalMillis(this.getChronology().weekyear().set(this.getLocalMillis(), var1));
   }

   public LocalDateTime withMonthOfYear(int var1) {
      return this.withLocalMillis(this.getChronology().monthOfYear().set(this.getLocalMillis(), var1));
   }

   public LocalDateTime withWeekOfWeekyear(int var1) {
      return this.withLocalMillis(this.getChronology().weekOfWeekyear().set(this.getLocalMillis(), var1));
   }

   public LocalDateTime withDayOfYear(int var1) {
      return this.withLocalMillis(this.getChronology().dayOfYear().set(this.getLocalMillis(), var1));
   }

   public LocalDateTime withDayOfMonth(int var1) {
      return this.withLocalMillis(this.getChronology().dayOfMonth().set(this.getLocalMillis(), var1));
   }

   public LocalDateTime withDayOfWeek(int var1) {
      return this.withLocalMillis(this.getChronology().dayOfWeek().set(this.getLocalMillis(), var1));
   }

   public LocalDateTime withHourOfDay(int var1) {
      return this.withLocalMillis(this.getChronology().hourOfDay().set(this.getLocalMillis(), var1));
   }

   public LocalDateTime withMinuteOfHour(int var1) {
      return this.withLocalMillis(this.getChronology().minuteOfHour().set(this.getLocalMillis(), var1));
   }

   public LocalDateTime withSecondOfMinute(int var1) {
      return this.withLocalMillis(this.getChronology().secondOfMinute().set(this.getLocalMillis(), var1));
   }

   public LocalDateTime withMillisOfSecond(int var1) {
      return this.withLocalMillis(this.getChronology().millisOfSecond().set(this.getLocalMillis(), var1));
   }

   public LocalDateTime withMillisOfDay(int var1) {
      return this.withLocalMillis(this.getChronology().millisOfDay().set(this.getLocalMillis(), var1));
   }

   public Property era() {
      return new Property(this, this.getChronology().era());
   }

   public Property centuryOfEra() {
      return new Property(this, this.getChronology().centuryOfEra());
   }

   public Property yearOfCentury() {
      return new Property(this, this.getChronology().yearOfCentury());
   }

   public Property yearOfEra() {
      return new Property(this, this.getChronology().yearOfEra());
   }

   public Property year() {
      return new Property(this, this.getChronology().year());
   }

   public Property weekyear() {
      return new Property(this, this.getChronology().weekyear());
   }

   public Property monthOfYear() {
      return new Property(this, this.getChronology().monthOfYear());
   }

   public Property weekOfWeekyear() {
      return new Property(this, this.getChronology().weekOfWeekyear());
   }

   public Property dayOfYear() {
      return new Property(this, this.getChronology().dayOfYear());
   }

   public Property dayOfMonth() {
      return new Property(this, this.getChronology().dayOfMonth());
   }

   public Property dayOfWeek() {
      return new Property(this, this.getChronology().dayOfWeek());
   }

   public Property hourOfDay() {
      return new Property(this, this.getChronology().hourOfDay());
   }

   public Property minuteOfHour() {
      return new Property(this, this.getChronology().minuteOfHour());
   }

   public Property secondOfMinute() {
      return new Property(this, this.getChronology().secondOfMinute());
   }

   public Property millisOfSecond() {
      return new Property(this, this.getChronology().millisOfSecond());
   }

   public Property millisOfDay() {
      return new Property(this, this.getChronology().millisOfDay());
   }

   @ToString
   public String toString() {
      return ISODateTimeFormat.dateTime().print((ReadablePartial)this);
   }

   public String toString(String var1) {
      return var1 == null ? this.toString() : DateTimeFormat.forPattern(var1).print((ReadablePartial)this);
   }

   public String toString(String var1, Locale var2) throws IllegalArgumentException {
      return var1 == null ? this.toString() : DateTimeFormat.forPattern(var1).withLocale(var2).print((ReadablePartial)this);
   }

   public static final class Property extends AbstractReadableInstantFieldProperty {
      private static final long serialVersionUID = -358138762846288L;
      private transient LocalDateTime iInstant;
      private transient DateTimeField iField;

      Property(LocalDateTime var1, DateTimeField var2) {
         this.iInstant = var1;
         this.iField = var2;
      }

      private void writeObject(ObjectOutputStream var1) throws IOException {
         var1.writeObject(this.iInstant);
         var1.writeObject(this.iField.getType());
      }

      private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
         this.iInstant = (LocalDateTime)var1.readObject();
         DateTimeFieldType var2 = (DateTimeFieldType)var1.readObject();
         this.iField = var2.getField(this.iInstant.getChronology());
      }

      public DateTimeField getField() {
         return this.iField;
      }

      protected long getMillis() {
         return this.iInstant.getLocalMillis();
      }

      protected Chronology getChronology() {
         return this.iInstant.getChronology();
      }

      public LocalDateTime getLocalDateTime() {
         return this.iInstant;
      }

      public LocalDateTime addToCopy(int var1) {
         return this.iInstant.withLocalMillis(this.iField.add(this.iInstant.getLocalMillis(), var1));
      }

      public LocalDateTime addToCopy(long var1) {
         return this.iInstant.withLocalMillis(this.iField.add(this.iInstant.getLocalMillis(), var1));
      }

      public LocalDateTime addWrapFieldToCopy(int var1) {
         return this.iInstant.withLocalMillis(this.iField.addWrapField(this.iInstant.getLocalMillis(), var1));
      }

      public LocalDateTime setCopy(int var1) {
         return this.iInstant.withLocalMillis(this.iField.set(this.iInstant.getLocalMillis(), var1));
      }

      public LocalDateTime setCopy(String var1, Locale var2) {
         return this.iInstant.withLocalMillis(this.iField.set(this.iInstant.getLocalMillis(), var1, var2));
      }

      public LocalDateTime setCopy(String var1) {
         return this.setCopy(var1, (Locale)null);
      }

      public LocalDateTime withMaximumValue() {
         return this.setCopy(this.getMaximumValue());
      }

      public LocalDateTime withMinimumValue() {
         return this.setCopy(this.getMinimumValue());
      }

      public LocalDateTime roundFloorCopy() {
         return this.iInstant.withLocalMillis(this.iField.roundFloor(this.iInstant.getLocalMillis()));
      }

      public LocalDateTime roundCeilingCopy() {
         return this.iInstant.withLocalMillis(this.iField.roundCeiling(this.iInstant.getLocalMillis()));
      }

      public LocalDateTime roundHalfFloorCopy() {
         return this.iInstant.withLocalMillis(this.iField.roundHalfFloor(this.iInstant.getLocalMillis()));
      }

      public LocalDateTime roundHalfCeilingCopy() {
         return this.iInstant.withLocalMillis(this.iField.roundHalfCeiling(this.iInstant.getLocalMillis()));
      }

      public LocalDateTime roundHalfEvenCopy() {
         return this.iInstant.withLocalMillis(this.iField.roundHalfEven(this.iInstant.getLocalMillis()));
      }
   }
}
