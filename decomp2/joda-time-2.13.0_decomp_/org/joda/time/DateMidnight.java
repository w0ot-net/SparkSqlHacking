package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Locale;
import org.joda.convert.FromString;
import org.joda.time.base.BaseDateTime;
import org.joda.time.field.AbstractReadableInstantFieldProperty;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/** @deprecated */
@Deprecated
public final class DateMidnight extends BaseDateTime implements ReadableDateTime, Serializable {
   private static final long serialVersionUID = 156371964018738L;

   public static DateMidnight now() {
      return new DateMidnight();
   }

   public static DateMidnight now(DateTimeZone var0) {
      if (var0 == null) {
         throw new NullPointerException("Zone must not be null");
      } else {
         return new DateMidnight(var0);
      }
   }

   public static DateMidnight now(Chronology var0) {
      if (var0 == null) {
         throw new NullPointerException("Chronology must not be null");
      } else {
         return new DateMidnight(var0);
      }
   }

   @FromString
   public static DateMidnight parse(String var0) {
      return parse(var0, ISODateTimeFormat.dateTimeParser().withOffsetParsed());
   }

   public static DateMidnight parse(String var0, DateTimeFormatter var1) {
      return var1.parseDateTime(var0).toDateMidnight();
   }

   public DateMidnight() {
   }

   public DateMidnight(DateTimeZone var1) {
      super(var1);
   }

   public DateMidnight(Chronology var1) {
      super(var1);
   }

   public DateMidnight(long var1) {
      super(var1);
   }

   public DateMidnight(long var1, DateTimeZone var3) {
      super(var1, var3);
   }

   public DateMidnight(long var1, Chronology var3) {
      super(var1, var3);
   }

   public DateMidnight(Object var1) {
      super(var1, (Chronology)null);
   }

   public DateMidnight(Object var1, DateTimeZone var2) {
      super(var1, var2);
   }

   public DateMidnight(Object var1, Chronology var2) {
      super(var1, DateTimeUtils.getChronology(var2));
   }

   public DateMidnight(int var1, int var2, int var3) {
      super(var1, var2, var3, 0, 0, 0, 0);
   }

   public DateMidnight(int var1, int var2, int var3, DateTimeZone var4) {
      super(var1, var2, var3, 0, 0, 0, 0, (DateTimeZone)var4);
   }

   public DateMidnight(int var1, int var2, int var3, Chronology var4) {
      super(var1, var2, var3, 0, 0, 0, 0, (Chronology)var4);
   }

   protected long checkInstant(long var1, Chronology var3) {
      return var3.dayOfMonth().roundFloor(var1);
   }

   public DateMidnight withMillis(long var1) {
      Chronology var3 = this.getChronology();
      var1 = this.checkInstant(var1, var3);
      return var1 == this.getMillis() ? this : new DateMidnight(var1, var3);
   }

   public DateMidnight withChronology(Chronology var1) {
      return var1 == this.getChronology() ? this : new DateMidnight(this.getMillis(), var1);
   }

   public DateMidnight withZoneRetainFields(DateTimeZone var1) {
      var1 = DateTimeUtils.getZone(var1);
      DateTimeZone var2 = DateTimeUtils.getZone(this.getZone());
      if (var1 == var2) {
         return this;
      } else {
         long var3 = var2.getMillisKeepLocal(var1, this.getMillis());
         return new DateMidnight(var3, this.getChronology().withZone(var1));
      }
   }

   public DateMidnight withFields(ReadablePartial var1) {
      return var1 == null ? this : this.withMillis(this.getChronology().set(var1, this.getMillis()));
   }

   public DateMidnight withField(DateTimeFieldType var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Field must not be null");
      } else {
         long var3 = var1.getField(this.getChronology()).set(this.getMillis(), var2);
         return this.withMillis(var3);
      }
   }

   public DateMidnight withFieldAdded(DurationFieldType var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Field must not be null");
      } else if (var2 == 0) {
         return this;
      } else {
         long var3 = var1.getField(this.getChronology()).add(this.getMillis(), var2);
         return this.withMillis(var3);
      }
   }

   public DateMidnight withDurationAdded(long var1, int var3) {
      if (var1 != 0L && var3 != 0) {
         long var4 = this.getChronology().add(this.getMillis(), var1, var3);
         return this.withMillis(var4);
      } else {
         return this;
      }
   }

   public DateMidnight withDurationAdded(ReadableDuration var1, int var2) {
      return var1 != null && var2 != 0 ? this.withDurationAdded(var1.getMillis(), var2) : this;
   }

   public DateMidnight withPeriodAdded(ReadablePeriod var1, int var2) {
      if (var1 != null && var2 != 0) {
         long var3 = this.getChronology().add(var1, this.getMillis(), var2);
         return this.withMillis(var3);
      } else {
         return this;
      }
   }

   public DateMidnight plus(long var1) {
      return this.withDurationAdded(var1, 1);
   }

   public DateMidnight plus(ReadableDuration var1) {
      return this.withDurationAdded(var1, 1);
   }

   public DateMidnight plus(ReadablePeriod var1) {
      return this.withPeriodAdded(var1, 1);
   }

   public DateMidnight plusYears(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().years().add(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateMidnight plusMonths(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().months().add(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateMidnight plusWeeks(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().weeks().add(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateMidnight plusDays(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().days().add(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateMidnight minus(long var1) {
      return this.withDurationAdded(var1, -1);
   }

   public DateMidnight minus(ReadableDuration var1) {
      return this.withDurationAdded(var1, -1);
   }

   public DateMidnight minus(ReadablePeriod var1) {
      return this.withPeriodAdded(var1, -1);
   }

   public DateMidnight minusYears(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().years().subtract(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateMidnight minusMonths(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().months().subtract(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateMidnight minusWeeks(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().weeks().subtract(this.getMillis(), var1);
         return this.withMillis(var2);
      }
   }

   public DateMidnight minusDays(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().days().subtract(this.getMillis(), var1);
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
   public YearMonthDay toYearMonthDay() {
      return new YearMonthDay(this.getMillis(), this.getChronology());
   }

   public LocalDate toLocalDate() {
      return new LocalDate(this.getMillis(), this.getChronology());
   }

   public Interval toInterval() {
      Chronology var1 = this.getChronology();
      long var2 = this.getMillis();
      long var4 = DurationFieldType.days().getField(var1).add(var2, 1);
      return new Interval(var2, var4, var1);
   }

   public DateMidnight withEra(int var1) {
      return this.withMillis(this.getChronology().era().set(this.getMillis(), var1));
   }

   public DateMidnight withCenturyOfEra(int var1) {
      return this.withMillis(this.getChronology().centuryOfEra().set(this.getMillis(), var1));
   }

   public DateMidnight withYearOfEra(int var1) {
      return this.withMillis(this.getChronology().yearOfEra().set(this.getMillis(), var1));
   }

   public DateMidnight withYearOfCentury(int var1) {
      return this.withMillis(this.getChronology().yearOfCentury().set(this.getMillis(), var1));
   }

   public DateMidnight withYear(int var1) {
      return this.withMillis(this.getChronology().year().set(this.getMillis(), var1));
   }

   public DateMidnight withWeekyear(int var1) {
      return this.withMillis(this.getChronology().weekyear().set(this.getMillis(), var1));
   }

   public DateMidnight withMonthOfYear(int var1) {
      return this.withMillis(this.getChronology().monthOfYear().set(this.getMillis(), var1));
   }

   public DateMidnight withWeekOfWeekyear(int var1) {
      return this.withMillis(this.getChronology().weekOfWeekyear().set(this.getMillis(), var1));
   }

   public DateMidnight withDayOfYear(int var1) {
      return this.withMillis(this.getChronology().dayOfYear().set(this.getMillis(), var1));
   }

   public DateMidnight withDayOfMonth(int var1) {
      return this.withMillis(this.getChronology().dayOfMonth().set(this.getMillis(), var1));
   }

   public DateMidnight withDayOfWeek(int var1) {
      return this.withMillis(this.getChronology().dayOfWeek().set(this.getMillis(), var1));
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

   public static final class Property extends AbstractReadableInstantFieldProperty {
      private static final long serialVersionUID = 257629620L;
      private DateMidnight iInstant;
      private DateTimeField iField;

      Property(DateMidnight var1, DateTimeField var2) {
         this.iInstant = var1;
         this.iField = var2;
      }

      private void writeObject(ObjectOutputStream var1) throws IOException {
         var1.writeObject(this.iInstant);
         var1.writeObject(this.iField.getType());
      }

      private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
         this.iInstant = (DateMidnight)var1.readObject();
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

      public DateMidnight getDateMidnight() {
         return this.iInstant;
      }

      public DateMidnight addToCopy(int var1) {
         return this.iInstant.withMillis(this.iField.add(this.iInstant.getMillis(), var1));
      }

      public DateMidnight addToCopy(long var1) {
         return this.iInstant.withMillis(this.iField.add(this.iInstant.getMillis(), var1));
      }

      public DateMidnight addWrapFieldToCopy(int var1) {
         return this.iInstant.withMillis(this.iField.addWrapField(this.iInstant.getMillis(), var1));
      }

      public DateMidnight setCopy(int var1) {
         return this.iInstant.withMillis(this.iField.set(this.iInstant.getMillis(), var1));
      }

      public DateMidnight setCopy(String var1, Locale var2) {
         return this.iInstant.withMillis(this.iField.set(this.iInstant.getMillis(), var1, var2));
      }

      public DateMidnight setCopy(String var1) {
         return this.setCopy(var1, (Locale)null);
      }

      public DateMidnight withMaximumValue() {
         return this.setCopy(this.getMaximumValue());
      }

      public DateMidnight withMinimumValue() {
         return this.setCopy(this.getMinimumValue());
      }

      public DateMidnight roundFloorCopy() {
         return this.iInstant.withMillis(this.iField.roundFloor(this.iInstant.getMillis()));
      }

      public DateMidnight roundCeilingCopy() {
         return this.iInstant.withMillis(this.iField.roundCeiling(this.iInstant.getMillis()));
      }

      public DateMidnight roundHalfFloorCopy() {
         return this.iInstant.withMillis(this.iField.roundHalfFloor(this.iInstant.getMillis()));
      }

      public DateMidnight roundHalfCeilingCopy() {
         return this.iInstant.withMillis(this.iField.roundHalfCeiling(this.iInstant.getMillis()));
      }

      public DateMidnight roundHalfEvenCopy() {
         return this.iInstant.withMillis(this.iField.roundHalfEven(this.iInstant.getMillis()));
      }
   }
}
