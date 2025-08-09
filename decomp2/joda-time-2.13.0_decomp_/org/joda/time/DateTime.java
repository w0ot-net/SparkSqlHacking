package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Locale;
import org.joda.convert.FromString;
import org.joda.time.base.BaseDateTime;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.AbstractReadableInstantFieldProperty;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class DateTime extends BaseDateTime implements ReadableDateTime, Serializable {
   private static final long serialVersionUID = -5171125899451703815L;

   public static DateTime now() {
      return new DateTime();
   }

   public static DateTime now(DateTimeZone var0) {
      if (var0 == null) {
         throw new NullPointerException("Zone must not be null");
      } else {
         return new DateTime(var0);
      }
   }

   public static DateTime now(Chronology var0) {
      if (var0 == null) {
         throw new NullPointerException("Chronology must not be null");
      } else {
         return new DateTime(var0);
      }
   }

   @FromString
   public static DateTime parse(String var0) {
      return parse(var0, ISODateTimeFormat.dateTimeParser().withOffsetParsed());
   }

   public static DateTime parse(String var0, DateTimeFormatter var1) {
      return var1.parseDateTime(var0);
   }

   public DateTime() {
   }

   public DateTime(DateTimeZone var1) {
      super(var1);
   }

   public DateTime(Chronology var1) {
      super(var1);
   }

   public DateTime(long var1) {
      super(var1);
   }

   public DateTime(long var1, DateTimeZone var3) {
      super(var1, var3);
   }

   public DateTime(long var1, Chronology var3) {
      super(var1, var3);
   }

   public DateTime(Object var1) {
      super(var1, (Chronology)null);
   }

   public DateTime(Object var1, DateTimeZone var2) {
      super(var1, var2);
   }

   public DateTime(Object var1, Chronology var2) {
      super(var1, DateTimeUtils.getChronology(var2));
   }

   public DateTime(int var1, int var2, int var3, int var4, int var5) {
      super(var1, var2, var3, var4, var5, 0, 0);
   }

   public DateTime(int var1, int var2, int var3, int var4, int var5, DateTimeZone var6) {
      super(var1, var2, var3, var4, var5, 0, 0, (DateTimeZone)var6);
   }

   public DateTime(int var1, int var2, int var3, int var4, int var5, Chronology var6) {
      super(var1, var2, var3, var4, var5, 0, 0, (Chronology)var6);
   }

   public DateTime(int var1, int var2, int var3, int var4, int var5, int var6) {
      super(var1, var2, var3, var4, var5, var6, 0);
   }

   public DateTime(int var1, int var2, int var3, int var4, int var5, int var6, DateTimeZone var7) {
      super(var1, var2, var3, var4, var5, var6, 0, (DateTimeZone)var7);
   }

   public DateTime(int var1, int var2, int var3, int var4, int var5, int var6, Chronology var7) {
      super(var1, var2, var3, var4, var5, var6, 0, (Chronology)var7);
   }

   public DateTime(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      super(var1, var2, var3, var4, var5, var6, var7);
   }

   public DateTime(int var1, int var2, int var3, int var4, int var5, int var6, int var7, DateTimeZone var8) {
      super(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public DateTime(int var1, int var2, int var3, int var4, int var5, int var6, int var7, Chronology var8) {
      super(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public DateTime toDateTime() {
      return this;
   }

   public DateTime toDateTimeISO() {
      return this.getChronology() == ISOChronology.getInstance() ? this : super.toDateTimeISO();
   }

   public DateTime toDateTime(DateTimeZone var1) {
      var1 = DateTimeUtils.getZone(var1);
      return this.getZone() == var1 ? this : super.toDateTime(var1);
   }

   public DateTime toDateTime(Chronology var1) {
      var1 = DateTimeUtils.getChronology(var1);
      return this.getChronology() == var1 ? this : super.toDateTime(var1);
   }

   public DateTime withMillis(long var1) {
      return var1 == this.getMillis() ? this : new DateTime(var1, this.getChronology());
   }

   public DateTime withChronology(Chronology var1) {
      var1 = DateTimeUtils.getChronology(var1);
      return var1 == this.getChronology() ? this : new DateTime(this.getMillis(), var1);
   }

   public DateTime withZone(DateTimeZone var1) {
      return this.withChronology(this.getChronology().withZone(var1));
   }

   public DateTime withZoneRetainFields(DateTimeZone var1) {
      var1 = DateTimeUtils.getZone(var1);
      DateTimeZone var2 = DateTimeUtils.getZone(this.getZone());
      if (var1 == var2) {
         return this;
      } else {
         long var3 = var2.getMillisKeepLocal(var1, this.getMillis());
         return new DateTime(var3, this.getChronology().withZone(var1));
      }
   }

   public DateTime withEarlierOffsetAtOverlap() {
      long var1 = this.getZone().adjustOffset(this.getMillis(), false);
      return this.withMillis(var1);
   }

   public DateTime withLaterOffsetAtOverlap() {
      long var1 = this.getZone().adjustOffset(this.getMillis(), true);
      return this.withMillis(var1);
   }

   public DateTime withDate(int var1, int var2, int var3) {
      Chronology var4 = this.getChronology();
      long var5 = var4.withUTC().getDateTimeMillis(var1, var2, var3, this.getMillisOfDay());
      return this.withMillis(var4.getZone().convertLocalToUTC(var5, false, this.getMillis()));
   }

   public DateTime withDate(LocalDate var1) {
      return this.withDate(var1.getYear(), var1.getMonthOfYear(), var1.getDayOfMonth());
   }

   public DateTime withTime(int var1, int var2, int var3, int var4) {
      Chronology var5 = this.getChronology();
      long var6 = var5.withUTC().getDateTimeMillis(this.getYear(), this.getMonthOfYear(), this.getDayOfMonth(), var1, var2, var3, var4);
      return this.withMillis(var5.getZone().convertLocalToUTC(var6, false, this.getMillis()));
   }

   public DateTime withTime(LocalTime var1) {
      return this.withTime(var1.getHourOfDay(), var1.getMinuteOfHour(), var1.getSecondOfMinute(), var1.getMillisOfSecond());
   }

   public DateTime withTimeAtStartOfDay() {
      return this.toLocalDate().toDateTimeAtStartOfDay(this.getZone());
   }

   public DateTime withFields(ReadablePartial var1) {
      return var1 == null ? this : this.withMillis(this.getChronology().set(var1, this.getMillis()));
   }

   public DateTime withField(DateTimeFieldType var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Field must not be null");
      } else {
         long var3 = var1.getField(this.getChronology()).set(this.getMillis(), var2);
         return this.withMillis(var3);
      }
   }

   public DateTime withFieldAdded(DurationFieldType var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Field must not be null");
      } else if (var2 == 0) {
         return this;
      } else {
         long var3 = var1.getField(this.getChronology()).add(this.getMillis(), var2);
         return this.withMillis(var3);
      }
   }

   public DateTime withDurationAdded(long var1, int var3) {
      if (var1 != 0L && var3 != 0) {
         long var4 = this.getChronology().add(this.getMillis(), var1, var3);
         return this.withMillis(var4);
      } else {
         return this;
      }
   }

   public DateTime withDurationAdded(ReadableDuration var1, int var2) {
      return var1 != null && var2 != 0 ? this.withDurationAdded(var1.getMillis(), var2) : this;
   }

   public DateTime withPeriodAdded(ReadablePeriod var1, int var2) {
      if (var1 != null && var2 != 0) {
         long var3 = this.getChronology().add(var1, this.getMillis(), var2);
         return this.withMillis(var3);
      } else {
         return this;
      }
   }

   public DateTime plus(long var1) {
      return this.withDurationAdded(var1, 1);
   }

   public DateTime plus(ReadableDuration var1) {
      return this.withDurationAdded(var1, 1);
   }

   public DateTime plus(ReadablePeriod var1) {
      return this.withPeriodAdded(var1, 1);
   }

   public DateTime plusYears(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().years().add(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateTime plusMonths(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().months().add(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateTime plusWeeks(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().weeks().add(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateTime plusDays(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().days().add(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateTime plusHours(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().hours().add(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateTime plusMinutes(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().minutes().add(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateTime plusSeconds(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().seconds().add(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateTime plusMillis(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().millis().add(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateTime minus(long var1) {
      return this.withDurationAdded(var1, -1);
   }

   public DateTime minus(ReadableDuration var1) {
      return this.withDurationAdded(var1, -1);
   }

   public DateTime minus(ReadablePeriod var1) {
      return this.withPeriodAdded(var1, -1);
   }

   public DateTime minusYears(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().years().subtract(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateTime minusMonths(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().months().subtract(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateTime minusWeeks(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().weeks().subtract(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateTime minusDays(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().days().subtract(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateTime minusHours(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().hours().subtract(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateTime minusMinutes(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().minutes().subtract(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateTime minusSeconds(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().seconds().subtract(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateTime minusMillis(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().millis().subtract(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public Property property(DateTimeFieldType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("The DateTimeFieldType must not be null");
      } else {
         DateTimeField var2 = var1.getField(this.getChronology());
         if (!var2.isSupported()) {
            throw new IllegalArgumentException("Field '" + var1 + "' is not supported");
         } else {
            return new Property(this, var2);
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public DateMidnight toDateMidnight() {
      return new DateMidnight(this.getMillis(), this.getChronology());
   }

   /** @deprecated */
   @Deprecated
   public YearMonthDay toYearMonthDay() {
      return new YearMonthDay(this.getMillis(), this.getChronology());
   }

   /** @deprecated */
   @Deprecated
   public TimeOfDay toTimeOfDay() {
      return new TimeOfDay(this.getMillis(), this.getChronology());
   }

   public LocalDateTime toLocalDateTime() {
      return new LocalDateTime(this.getMillis(), this.getChronology());
   }

   public LocalDate toLocalDate() {
      return new LocalDate(this.getMillis(), this.getChronology());
   }

   public LocalTime toLocalTime() {
      return new LocalTime(this.getMillis(), this.getChronology());
   }

   public DateTime withEra(int var1) {
      return this.withMillis(this.getChronology().era().set(this.getMillis(), var1));
   }

   public DateTime withCenturyOfEra(int var1) {
      return this.withMillis(this.getChronology().centuryOfEra().set(this.getMillis(), var1));
   }

   public DateTime withYearOfEra(int var1) {
      return this.withMillis(this.getChronology().yearOfEra().set(this.getMillis(), var1));
   }

   public DateTime withYearOfCentury(int var1) {
      return this.withMillis(this.getChronology().yearOfCentury().set(this.getMillis(), var1));
   }

   public DateTime withYear(int var1) {
      return this.withMillis(this.getChronology().year().set(this.getMillis(), var1));
   }

   public DateTime withWeekyear(int var1) {
      return this.withMillis(this.getChronology().weekyear().set(this.getMillis(), var1));
   }

   public DateTime withMonthOfYear(int var1) {
      return this.withMillis(this.getChronology().monthOfYear().set(this.getMillis(), var1));
   }

   public DateTime withWeekOfWeekyear(int var1) {
      return this.withMillis(this.getChronology().weekOfWeekyear().set(this.getMillis(), var1));
   }

   public DateTime withDayOfYear(int var1) {
      return this.withMillis(this.getChronology().dayOfYear().set(this.getMillis(), var1));
   }

   public DateTime withDayOfMonth(int var1) {
      return this.withMillis(this.getChronology().dayOfMonth().set(this.getMillis(), var1));
   }

   public DateTime withDayOfWeek(int var1) {
      return this.withMillis(this.getChronology().dayOfWeek().set(this.getMillis(), var1));
   }

   public DateTime withHourOfDay(int var1) {
      return this.withMillis(this.getChronology().hourOfDay().set(this.getMillis(), var1));
   }

   public DateTime withMinuteOfHour(int var1) {
      return this.withMillis(this.getChronology().minuteOfHour().set(this.getMillis(), var1));
   }

   public DateTime withSecondOfMinute(int var1) {
      return this.withMillis(this.getChronology().secondOfMinute().set(this.getMillis(), var1));
   }

   public DateTime withMillisOfSecond(int var1) {
      return this.withMillis(this.getChronology().millisOfSecond().set(this.getMillis(), var1));
   }

   public DateTime withMillisOfDay(int var1) {
      return this.withMillis(this.getChronology().millisOfDay().set(this.getMillis(), var1));
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

   public Property minuteOfDay() {
      return new Property(this, this.getChronology().minuteOfDay());
   }

   public Property minuteOfHour() {
      return new Property(this, this.getChronology().minuteOfHour());
   }

   public Property secondOfDay() {
      return new Property(this, this.getChronology().secondOfDay());
   }

   public Property secondOfMinute() {
      return new Property(this, this.getChronology().secondOfMinute());
   }

   public Property millisOfDay() {
      return new Property(this, this.getChronology().millisOfDay());
   }

   public Property millisOfSecond() {
      return new Property(this, this.getChronology().millisOfSecond());
   }

   public static final class Property extends AbstractReadableInstantFieldProperty {
      private static final long serialVersionUID = -6983323811635733510L;
      private DateTime iInstant;
      private DateTimeField iField;

      Property(DateTime var1, DateTimeField var2) {
         this.iInstant = var1;
         this.iField = var2;
      }

      private void writeObject(ObjectOutputStream var1) throws IOException {
         var1.writeObject(this.iInstant);
         var1.writeObject(this.iField.getType());
      }

      private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
         this.iInstant = (DateTime)var1.readObject();
         DateTimeFieldType var2 = (DateTimeFieldType)var1.readObject();
         this.iField = var2.getField(this.iInstant.getChronology());
      }

      public DateTimeField getField() {
         return this.iField;
      }

      protected long getMillis() {
         return this.iInstant.getMillis();
      }

      protected Chronology getChronology() {
         return this.iInstant.getChronology();
      }

      public DateTime getDateTime() {
         return this.iInstant;
      }

      public DateTime addToCopy(int var1) {
         return this.iInstant.withMillis(this.iField.add(this.iInstant.getMillis(), var1));
      }

      public DateTime addToCopy(long var1) {
         return this.iInstant.withMillis(this.iField.add(this.iInstant.getMillis(), var1));
      }

      public DateTime addWrapFieldToCopy(int var1) {
         return this.iInstant.withMillis(this.iField.addWrapField(this.iInstant.getMillis(), var1));
      }

      public DateTime setCopy(int var1) {
         return this.iInstant.withMillis(this.iField.set(this.iInstant.getMillis(), var1));
      }

      public DateTime setCopy(String var1, Locale var2) {
         return this.iInstant.withMillis(this.iField.set(this.iInstant.getMillis(), var1, var2));
      }

      public DateTime setCopy(String var1) {
         return this.setCopy(var1, (Locale)null);
      }

      public DateTime withMaximumValue() {
         try {
            return this.setCopy(this.getMaximumValue());
         } catch (RuntimeException var4) {
            if (IllegalInstantException.isIllegalInstant(var4)) {
               long var2 = this.getChronology().getZone().previousTransition(this.getMillis() + 86400000L);
               return new DateTime(var2, this.getChronology());
            } else {
               throw var4;
            }
         }
      }

      public DateTime withMinimumValue() {
         try {
            return this.setCopy(this.getMinimumValue());
         } catch (RuntimeException var4) {
            if (IllegalInstantException.isIllegalInstant(var4)) {
               long var2 = this.getChronology().getZone().nextTransition(this.getMillis() - 86400000L);
               return new DateTime(var2, this.getChronology());
            } else {
               throw var4;
            }
         }
      }

      public DateTime roundFloorCopy() {
         return this.iInstant.withMillis(this.iField.roundFloor(this.iInstant.getMillis()));
      }

      public DateTime roundCeilingCopy() {
         return this.iInstant.withMillis(this.iField.roundCeiling(this.iInstant.getMillis()));
      }

      public DateTime roundHalfFloorCopy() {
         return this.iInstant.withMillis(this.iField.roundHalfFloor(this.iInstant.getMillis()));
      }

      public DateTime roundHalfCeilingCopy() {
         return this.iInstant.withMillis(this.iField.roundHalfCeiling(this.iInstant.getMillis()));
      }

      public DateTime roundHalfEvenCopy() {
         return this.iInstant.withMillis(this.iField.roundHalfEven(this.iInstant.getMillis()));
      }
   }
}
