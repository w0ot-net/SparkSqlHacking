package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
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

public final class LocalTime extends BaseLocal implements ReadablePartial, Serializable {
   private static final long serialVersionUID = -12873158713873L;
   public static final LocalTime MIDNIGHT = new LocalTime(0, 0, 0, 0);
   private static final int HOUR_OF_DAY = 0;
   private static final int MINUTE_OF_HOUR = 1;
   private static final int SECOND_OF_MINUTE = 2;
   private static final int MILLIS_OF_SECOND = 3;
   private static final Set TIME_DURATION_TYPES = new HashSet();
   private final long iLocalMillis;
   private final Chronology iChronology;

   public static LocalTime now() {
      return new LocalTime();
   }

   public static LocalTime now(DateTimeZone var0) {
      if (var0 == null) {
         throw new NullPointerException("Zone must not be null");
      } else {
         return new LocalTime(var0);
      }
   }

   public static LocalTime now(Chronology var0) {
      if (var0 == null) {
         throw new NullPointerException("Chronology must not be null");
      } else {
         return new LocalTime(var0);
      }
   }

   @FromString
   public static LocalTime parse(String var0) {
      return parse(var0, ISODateTimeFormat.localTimeParser());
   }

   public static LocalTime parse(String var0, DateTimeFormatter var1) {
      return var1.parseLocalTime(var0);
   }

   public static LocalTime fromMillisOfDay(long var0) {
      return fromMillisOfDay(var0, (Chronology)null);
   }

   public static LocalTime fromMillisOfDay(long var0, Chronology var2) {
      var2 = DateTimeUtils.getChronology(var2).withUTC();
      return new LocalTime(var0, var2);
   }

   public static LocalTime fromCalendarFields(Calendar var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("The calendar must not be null");
      } else {
         return new LocalTime(var0.get(11), var0.get(12), var0.get(13), var0.get(14));
      }
   }

   public static LocalTime fromDateFields(Date var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("The date must not be null");
      } else {
         return new LocalTime(var0.getHours(), var0.getMinutes(), var0.getSeconds(), ((int)(var0.getTime() % 1000L) + 1000) % 1000);
      }
   }

   public LocalTime() {
      this(DateTimeUtils.currentTimeMillis(), (Chronology)ISOChronology.getInstance());
   }

   public LocalTime(DateTimeZone var1) {
      this(DateTimeUtils.currentTimeMillis(), (Chronology)ISOChronology.getInstance(var1));
   }

   public LocalTime(Chronology var1) {
      this(DateTimeUtils.currentTimeMillis(), var1);
   }

   public LocalTime(long var1) {
      this(var1, (Chronology)ISOChronology.getInstance());
   }

   public LocalTime(long var1, DateTimeZone var3) {
      this(var1, (Chronology)ISOChronology.getInstance(var3));
   }

   public LocalTime(long var1, Chronology var3) {
      var3 = DateTimeUtils.getChronology(var3);
      long var4 = var3.getZone().getMillisKeepLocal(DateTimeZone.UTC, var1);
      var3 = var3.withUTC();
      this.iLocalMillis = (long)var3.millisOfDay().get(var4);
      this.iChronology = var3;
   }

   public LocalTime(Object var1) {
      this(var1, (Chronology)null);
   }

   public LocalTime(Object var1, DateTimeZone var2) {
      PartialConverter var3 = ConverterManager.getInstance().getPartialConverter(var1);
      Chronology var4 = var3.getChronology(var1, var2);
      var4 = DateTimeUtils.getChronology(var4);
      this.iChronology = var4.withUTC();
      int[] var5 = var3.getPartialValues(this, var1, var4, ISODateTimeFormat.localTimeParser());
      this.iLocalMillis = this.iChronology.getDateTimeMillis(0L, var5[0], var5[1], var5[2], var5[3]);
   }

   public LocalTime(Object var1, Chronology var2) {
      PartialConverter var3 = ConverterManager.getInstance().getPartialConverter(var1);
      var2 = var3.getChronology(var1, var2);
      var2 = DateTimeUtils.getChronology(var2);
      this.iChronology = var2.withUTC();
      int[] var4 = var3.getPartialValues(this, var1, var2, ISODateTimeFormat.localTimeParser());
      this.iLocalMillis = this.iChronology.getDateTimeMillis(0L, var4[0], var4[1], var4[2], var4[3]);
   }

   public LocalTime(int var1, int var2) {
      this(var1, var2, 0, 0, ISOChronology.getInstanceUTC());
   }

   public LocalTime(int var1, int var2, int var3) {
      this(var1, var2, var3, 0, ISOChronology.getInstanceUTC());
   }

   public LocalTime(int var1, int var2, int var3, int var4) {
      this(var1, var2, var3, var4, ISOChronology.getInstanceUTC());
   }

   public LocalTime(int var1, int var2, int var3, int var4, Chronology var5) {
      var5 = DateTimeUtils.getChronology(var5).withUTC();
      long var6 = var5.getDateTimeMillis(0L, var1, var2, var3, var4);
      this.iChronology = var5;
      this.iLocalMillis = var6;
   }

   private Object readResolve() {
      if (this.iChronology == null) {
         return new LocalTime(this.iLocalMillis, ISOChronology.getInstanceUTC());
      } else {
         return !DateTimeZone.UTC.equals(this.iChronology.getZone()) ? new LocalTime(this.iLocalMillis, this.iChronology.withUTC()) : this;
      }
   }

   public int size() {
      return 4;
   }

   protected DateTimeField getField(int var1, Chronology var2) {
      switch (var1) {
         case 0:
            return var2.hourOfDay();
         case 1:
            return var2.minuteOfHour();
         case 2:
            return var2.secondOfMinute();
         case 3:
            return var2.millisOfSecond();
         default:
            throw new IndexOutOfBoundsException("Invalid index: " + var1);
      }
   }

   public int getValue(int var1) {
      switch (var1) {
         case 0:
            return this.getChronology().hourOfDay().get(this.getLocalMillis());
         case 1:
            return this.getChronology().minuteOfHour().get(this.getLocalMillis());
         case 2:
            return this.getChronology().secondOfMinute().get(this.getLocalMillis());
         case 3:
            return this.getChronology().millisOfSecond().get(this.getLocalMillis());
         default:
            throw new IndexOutOfBoundsException("Invalid index: " + var1);
      }
   }

   public int get(DateTimeFieldType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("The DateTimeFieldType must not be null");
      } else if (!this.isSupported(var1)) {
         throw new IllegalArgumentException("Field '" + var1 + "' is not supported");
      } else {
         return var1.getField(this.getChronology()).get(this.getLocalMillis());
      }
   }

   public boolean isSupported(DateTimeFieldType var1) {
      if (var1 == null) {
         return false;
      } else if (!this.isSupported(var1.getDurationType())) {
         return false;
      } else {
         DurationFieldType var2 = var1.getRangeDurationType();
         return this.isSupported(var2) || var2 == DurationFieldType.days();
      }
   }

   public boolean isSupported(DurationFieldType var1) {
      if (var1 == null) {
         return false;
      } else {
         DurationField var2 = var1.getField(this.getChronology());
         return !TIME_DURATION_TYPES.contains(var1) && var2.getUnitMillis() >= this.getChronology().days().getUnitMillis() ? false : var2.isSupported();
      }
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
         if (var1 instanceof LocalTime) {
            LocalTime var2 = (LocalTime)var1;
            if (this.iChronology.equals(var2.iChronology)) {
               return this.iLocalMillis == var2.iLocalMillis;
            }
         }

         return super.equals(var1);
      }
   }

   public int hashCode() {
      int var1 = 157;
      var1 = 23 * var1 + this.iChronology.hourOfDay().get(this.iLocalMillis);
      var1 = 23 * var1 + this.iChronology.hourOfDay().getType().hashCode();
      var1 = 23 * var1 + this.iChronology.minuteOfHour().get(this.iLocalMillis);
      var1 = 23 * var1 + this.iChronology.minuteOfHour().getType().hashCode();
      var1 = 23 * var1 + this.iChronology.secondOfMinute().get(this.iLocalMillis);
      var1 = 23 * var1 + this.iChronology.secondOfMinute().getType().hashCode();
      var1 = 23 * var1 + this.iChronology.millisOfSecond().get(this.iLocalMillis);
      var1 = 23 * var1 + this.iChronology.millisOfSecond().getType().hashCode();
      var1 += this.getChronology().hashCode();
      return var1;
   }

   public int compareTo(ReadablePartial var1) {
      if (this == var1) {
         return 0;
      } else {
         if (var1 instanceof LocalTime) {
            LocalTime var2 = (LocalTime)var1;
            if (this.iChronology.equals(var2.iChronology)) {
               return this.iLocalMillis < var2.iLocalMillis ? -1 : (this.iLocalMillis == var2.iLocalMillis ? 0 : 1);
            }
         }

         return super.compareTo(var1);
      }
   }

   LocalTime withLocalMillis(long var1) {
      return var1 == this.getLocalMillis() ? this : new LocalTime(var1, this.getChronology());
   }

   public LocalTime withFields(ReadablePartial var1) {
      return var1 == null ? this : this.withLocalMillis(this.getChronology().set(var1, this.getLocalMillis()));
   }

   public LocalTime withField(DateTimeFieldType var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Field must not be null");
      } else if (!this.isSupported(var1)) {
         throw new IllegalArgumentException("Field '" + var1 + "' is not supported");
      } else {
         long var3 = var1.getField(this.getChronology()).set(this.getLocalMillis(), var2);
         return this.withLocalMillis(var3);
      }
   }

   public LocalTime withFieldAdded(DurationFieldType var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Field must not be null");
      } else if (!this.isSupported(var1)) {
         throw new IllegalArgumentException("Field '" + var1 + "' is not supported");
      } else if (var2 == 0) {
         return this;
      } else {
         long var3 = var1.getField(this.getChronology()).add(this.getLocalMillis(), var2);
         return this.withLocalMillis(var3);
      }
   }

   public LocalTime withPeriodAdded(ReadablePeriod var1, int var2) {
      if (var1 != null && var2 != 0) {
         long var3 = this.getChronology().add(var1, this.getLocalMillis(), var2);
         return this.withLocalMillis(var3);
      } else {
         return this;
      }
   }

   public LocalTime plus(ReadablePeriod var1) {
      return this.withPeriodAdded(var1, 1);
   }

   public LocalTime plusHours(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().hours().add(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalTime plusMinutes(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().minutes().add(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalTime plusSeconds(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().seconds().add(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalTime plusMillis(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().millis().add(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalTime minus(ReadablePeriod var1) {
      return this.withPeriodAdded(var1, -1);
   }

   public LocalTime minusHours(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().hours().subtract(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalTime minusMinutes(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().minutes().subtract(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalTime minusSeconds(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         long var2 = this.getChronology().seconds().subtract(this.getLocalMillis(), var1);
         return this.withLocalMillis(var2);
      }
   }

   public LocalTime minusMillis(int var1) {
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

   public LocalTime withHourOfDay(int var1) {
      return this.withLocalMillis(this.getChronology().hourOfDay().set(this.getLocalMillis(), var1));
   }

   public LocalTime withMinuteOfHour(int var1) {
      return this.withLocalMillis(this.getChronology().minuteOfHour().set(this.getLocalMillis(), var1));
   }

   public LocalTime withSecondOfMinute(int var1) {
      return this.withLocalMillis(this.getChronology().secondOfMinute().set(this.getLocalMillis(), var1));
   }

   public LocalTime withMillisOfSecond(int var1) {
      return this.withLocalMillis(this.getChronology().millisOfSecond().set(this.getLocalMillis(), var1));
   }

   public LocalTime withMillisOfDay(int var1) {
      return this.withLocalMillis(this.getChronology().millisOfDay().set(this.getLocalMillis(), var1));
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

   public DateTime toDateTimeToday() {
      return this.toDateTimeToday((DateTimeZone)null);
   }

   public DateTime toDateTimeToday(DateTimeZone var1) {
      Chronology var2 = this.getChronology().withZone(var1);
      long var3 = DateTimeUtils.currentTimeMillis();
      long var5 = var2.set(this, var3);
      return new DateTime(var5, var2);
   }

   @ToString
   public String toString() {
      return ISODateTimeFormat.time().print((ReadablePartial)this);
   }

   public String toString(String var1) {
      return var1 == null ? this.toString() : DateTimeFormat.forPattern(var1).print((ReadablePartial)this);
   }

   public String toString(String var1, Locale var2) throws IllegalArgumentException {
      return var1 == null ? this.toString() : DateTimeFormat.forPattern(var1).withLocale(var2).print((ReadablePartial)this);
   }

   static {
      TIME_DURATION_TYPES.add(DurationFieldType.millis());
      TIME_DURATION_TYPES.add(DurationFieldType.seconds());
      TIME_DURATION_TYPES.add(DurationFieldType.minutes());
      TIME_DURATION_TYPES.add(DurationFieldType.hours());
   }

   public static final class Property extends AbstractReadableInstantFieldProperty {
      private static final long serialVersionUID = -325842547277223L;
      private transient LocalTime iInstant;
      private transient DateTimeField iField;

      Property(LocalTime var1, DateTimeField var2) {
         this.iInstant = var1;
         this.iField = var2;
      }

      private void writeObject(ObjectOutputStream var1) throws IOException {
         var1.writeObject(this.iInstant);
         var1.writeObject(this.iField.getType());
      }

      private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
         this.iInstant = (LocalTime)var1.readObject();
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

      public LocalTime getLocalTime() {
         return this.iInstant;
      }

      public LocalTime addCopy(int var1) {
         return this.iInstant.withLocalMillis(this.iField.add(this.iInstant.getLocalMillis(), var1));
      }

      public LocalTime addCopy(long var1) {
         return this.iInstant.withLocalMillis(this.iField.add(this.iInstant.getLocalMillis(), var1));
      }

      public LocalTime addNoWrapToCopy(int var1) {
         long var2 = this.iField.add(this.iInstant.getLocalMillis(), var1);
         long var4 = (long)this.iInstant.getChronology().millisOfDay().get(var2);
         if (var4 != var2) {
            throw new IllegalArgumentException("The addition exceeded the boundaries of LocalTime");
         } else {
            return this.iInstant.withLocalMillis(var2);
         }
      }

      public LocalTime addWrapFieldToCopy(int var1) {
         return this.iInstant.withLocalMillis(this.iField.addWrapField(this.iInstant.getLocalMillis(), var1));
      }

      public LocalTime setCopy(int var1) {
         return this.iInstant.withLocalMillis(this.iField.set(this.iInstant.getLocalMillis(), var1));
      }

      public LocalTime setCopy(String var1, Locale var2) {
         return this.iInstant.withLocalMillis(this.iField.set(this.iInstant.getLocalMillis(), var1, var2));
      }

      public LocalTime setCopy(String var1) {
         return this.setCopy(var1, (Locale)null);
      }

      public LocalTime withMaximumValue() {
         return this.setCopy(this.getMaximumValue());
      }

      public LocalTime withMinimumValue() {
         return this.setCopy(this.getMinimumValue());
      }

      public LocalTime roundFloorCopy() {
         return this.iInstant.withLocalMillis(this.iField.roundFloor(this.iInstant.getLocalMillis()));
      }

      public LocalTime roundCeilingCopy() {
         return this.iInstant.withLocalMillis(this.iField.roundCeiling(this.iInstant.getLocalMillis()));
      }

      public LocalTime roundHalfFloorCopy() {
         return this.iInstant.withLocalMillis(this.iField.roundHalfFloor(this.iInstant.getLocalMillis()));
      }

      public LocalTime roundHalfCeilingCopy() {
         return this.iInstant.withLocalMillis(this.iField.roundHalfCeiling(this.iInstant.getLocalMillis()));
      }

      public LocalTime roundHalfEvenCopy() {
         return this.iInstant.withLocalMillis(this.iField.roundHalfEven(this.iInstant.getLocalMillis()));
      }
   }
}
