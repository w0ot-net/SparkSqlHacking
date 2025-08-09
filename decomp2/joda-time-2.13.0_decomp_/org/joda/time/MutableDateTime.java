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
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class MutableDateTime extends BaseDateTime implements ReadWritableDateTime, Cloneable, Serializable {
   private static final long serialVersionUID = 2852608688135209575L;
   public static final int ROUND_NONE = 0;
   public static final int ROUND_FLOOR = 1;
   public static final int ROUND_CEILING = 2;
   public static final int ROUND_HALF_FLOOR = 3;
   public static final int ROUND_HALF_CEILING = 4;
   public static final int ROUND_HALF_EVEN = 5;
   private DateTimeField iRoundingField;
   private int iRoundingMode;

   public static MutableDateTime now() {
      return new MutableDateTime();
   }

   public static MutableDateTime now(DateTimeZone var0) {
      if (var0 == null) {
         throw new NullPointerException("Zone must not be null");
      } else {
         return new MutableDateTime(var0);
      }
   }

   public static MutableDateTime now(Chronology var0) {
      if (var0 == null) {
         throw new NullPointerException("Chronology must not be null");
      } else {
         return new MutableDateTime(var0);
      }
   }

   @FromString
   public static MutableDateTime parse(String var0) {
      return parse(var0, ISODateTimeFormat.dateTimeParser().withOffsetParsed());
   }

   public static MutableDateTime parse(String var0, DateTimeFormatter var1) {
      return var1.parseDateTime(var0).toMutableDateTime();
   }

   public MutableDateTime() {
   }

   public MutableDateTime(DateTimeZone var1) {
      super(var1);
   }

   public MutableDateTime(Chronology var1) {
      super(var1);
   }

   public MutableDateTime(long var1) {
      super(var1);
   }

   public MutableDateTime(long var1, DateTimeZone var3) {
      super(var1, var3);
   }

   public MutableDateTime(long var1, Chronology var3) {
      super(var1, var3);
   }

   public MutableDateTime(Object var1) {
      super(var1, (Chronology)null);
   }

   public MutableDateTime(Object var1, DateTimeZone var2) {
      super(var1, var2);
   }

   public MutableDateTime(Object var1, Chronology var2) {
      super(var1, DateTimeUtils.getChronology(var2));
   }

   public MutableDateTime(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      super(var1, var2, var3, var4, var5, var6, var7);
   }

   public MutableDateTime(int var1, int var2, int var3, int var4, int var5, int var6, int var7, DateTimeZone var8) {
      super(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public MutableDateTime(int var1, int var2, int var3, int var4, int var5, int var6, int var7, Chronology var8) {
      super(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public DateTimeField getRoundingField() {
      return this.iRoundingField;
   }

   public int getRoundingMode() {
      return this.iRoundingMode;
   }

   public void setRounding(DateTimeField var1) {
      this.setRounding(var1, 1);
   }

   public void setRounding(DateTimeField var1, int var2) {
      if (var1 == null || var2 >= 0 && var2 <= 5) {
         this.iRoundingField = var2 == 0 ? null : var1;
         this.iRoundingMode = var1 == null ? 0 : var2;
         this.setMillis(this.getMillis());
      } else {
         throw new IllegalArgumentException("Illegal rounding mode: " + var2);
      }
   }

   public void setMillis(long var1) {
      switch (this.iRoundingMode) {
         case 0:
         default:
            break;
         case 1:
            var1 = this.iRoundingField.roundFloor(var1);
            break;
         case 2:
            var1 = this.iRoundingField.roundCeiling(var1);
            break;
         case 3:
            var1 = this.iRoundingField.roundHalfFloor(var1);
            break;
         case 4:
            var1 = this.iRoundingField.roundHalfCeiling(var1);
            break;
         case 5:
            var1 = this.iRoundingField.roundHalfEven(var1);
      }

      super.setMillis(var1);
   }

   public void setMillis(ReadableInstant var1) {
      long var2 = DateTimeUtils.getInstantMillis(var1);
      this.setMillis(var2);
   }

   public void add(long var1) {
      this.setMillis(FieldUtils.safeAdd(this.getMillis(), var1));
   }

   public void add(ReadableDuration var1) {
      this.add((ReadableDuration)var1, 1);
   }

   public void add(ReadableDuration var1, int var2) {
      if (var1 != null) {
         this.add(FieldUtils.safeMultiply(var1.getMillis(), var2));
      }

   }

   public void add(ReadablePeriod var1) {
      this.add((ReadablePeriod)var1, 1);
   }

   public void add(ReadablePeriod var1, int var2) {
      if (var1 != null) {
         this.setMillis(this.getChronology().add(var1, this.getMillis(), var2));
      }

   }

   public void setChronology(Chronology var1) {
      super.setChronology(var1);
   }

   public void setZone(DateTimeZone var1) {
      var1 = DateTimeUtils.getZone(var1);
      Chronology var2 = this.getChronology();
      if (var2.getZone() != var1) {
         this.setChronology(var2.withZone(var1));
      }

   }

   public void setZoneRetainFields(DateTimeZone var1) {
      var1 = DateTimeUtils.getZone(var1);
      DateTimeZone var2 = DateTimeUtils.getZone(this.getZone());
      if (var1 != var2) {
         long var3 = var2.getMillisKeepLocal(var1, this.getMillis());
         this.setChronology(this.getChronology().withZone(var1));
         this.setMillis(var3);
      }
   }

   public void set(DateTimeFieldType var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Field must not be null");
      } else {
         this.setMillis(var1.getField(this.getChronology()).set(this.getMillis(), var2));
      }
   }

   public void add(DurationFieldType var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Field must not be null");
      } else {
         if (var2 != 0) {
            this.setMillis(var1.getField(this.getChronology()).add(this.getMillis(), var2));
         }

      }
   }

   public void setYear(int var1) {
      this.setMillis(this.getChronology().year().set(this.getMillis(), var1));
   }

   public void addYears(int var1) {
      if (var1 != 0) {
         this.setMillis(this.getChronology().years().add(this.getMillis(), var1));
      }

   }

   public void setWeekyear(int var1) {
      this.setMillis(this.getChronology().weekyear().set(this.getMillis(), var1));
   }

   public void addWeekyears(int var1) {
      if (var1 != 0) {
         this.setMillis(this.getChronology().weekyears().add(this.getMillis(), var1));
      }

   }

   public void setMonthOfYear(int var1) {
      this.setMillis(this.getChronology().monthOfYear().set(this.getMillis(), var1));
   }

   public void addMonths(int var1) {
      if (var1 != 0) {
         this.setMillis(this.getChronology().months().add(this.getMillis(), var1));
      }

   }

   public void setWeekOfWeekyear(int var1) {
      this.setMillis(this.getChronology().weekOfWeekyear().set(this.getMillis(), var1));
   }

   public void addWeeks(int var1) {
      if (var1 != 0) {
         this.setMillis(this.getChronology().weeks().add(this.getMillis(), var1));
      }

   }

   public void setDayOfYear(int var1) {
      this.setMillis(this.getChronology().dayOfYear().set(this.getMillis(), var1));
   }

   public void setDayOfMonth(int var1) {
      this.setMillis(this.getChronology().dayOfMonth().set(this.getMillis(), var1));
   }

   public void setDayOfWeek(int var1) {
      this.setMillis(this.getChronology().dayOfWeek().set(this.getMillis(), var1));
   }

   public void addDays(int var1) {
      if (var1 != 0) {
         this.setMillis(this.getChronology().days().add(this.getMillis(), var1));
      }

   }

   public void setHourOfDay(int var1) {
      this.setMillis(this.getChronology().hourOfDay().set(this.getMillis(), var1));
   }

   public void addHours(int var1) {
      if (var1 != 0) {
         this.setMillis(this.getChronology().hours().add(this.getMillis(), var1));
      }

   }

   public void setMinuteOfDay(int var1) {
      this.setMillis(this.getChronology().minuteOfDay().set(this.getMillis(), var1));
   }

   public void setMinuteOfHour(int var1) {
      this.setMillis(this.getChronology().minuteOfHour().set(this.getMillis(), var1));
   }

   public void addMinutes(int var1) {
      if (var1 != 0) {
         this.setMillis(this.getChronology().minutes().add(this.getMillis(), var1));
      }

   }

   public void setSecondOfDay(int var1) {
      this.setMillis(this.getChronology().secondOfDay().set(this.getMillis(), var1));
   }

   public void setSecondOfMinute(int var1) {
      this.setMillis(this.getChronology().secondOfMinute().set(this.getMillis(), var1));
   }

   public void addSeconds(int var1) {
      if (var1 != 0) {
         this.setMillis(this.getChronology().seconds().add(this.getMillis(), var1));
      }

   }

   public void setMillisOfDay(int var1) {
      this.setMillis(this.getChronology().millisOfDay().set(this.getMillis(), var1));
   }

   public void setMillisOfSecond(int var1) {
      this.setMillis(this.getChronology().millisOfSecond().set(this.getMillis(), var1));
   }

   public void addMillis(int var1) {
      if (var1 != 0) {
         this.setMillis(this.getChronology().millis().add(this.getMillis(), var1));
      }

   }

   public void setDate(long var1) {
      this.setMillis(this.getChronology().millisOfDay().set(var1, this.getMillisOfDay()));
   }

   public void setDate(ReadableInstant var1) {
      long var2 = DateTimeUtils.getInstantMillis(var1);
      if (var1 instanceof ReadableDateTime) {
         ReadableDateTime var4 = (ReadableDateTime)var1;
         Chronology var5 = DateTimeUtils.getChronology(var4.getChronology());
         DateTimeZone var6 = var5.getZone();
         if (var6 != null) {
            var2 = var6.getMillisKeepLocal(this.getZone(), var2);
         }
      }

      this.setDate(var2);
   }

   public void setDate(int var1, int var2, int var3) {
      Chronology var4 = this.getChronology();
      long var5 = var4.getDateTimeMillis(var1, var2, var3, 0);
      this.setDate(var5);
   }

   public void setTime(long var1) {
      int var3 = ISOChronology.getInstanceUTC().millisOfDay().get(var1);
      this.setMillis(this.getChronology().millisOfDay().set(this.getMillis(), var3));
   }

   public void setTime(ReadableInstant var1) {
      long var2 = DateTimeUtils.getInstantMillis(var1);
      Chronology var4 = DateTimeUtils.getInstantChronology(var1);
      DateTimeZone var5 = var4.getZone();
      if (var5 != null) {
         var2 = var5.getMillisKeepLocal(DateTimeZone.UTC, var2);
      }

      this.setTime(var2);
   }

   public void setTime(int var1, int var2, int var3, int var4) {
      long var5 = this.getChronology().getDateTimeMillis(this.getMillis(), var1, var2, var3, var4);
      this.setMillis(var5);
   }

   public void setDateTime(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      long var8 = this.getChronology().getDateTimeMillis(var1, var2, var3, var4, var5, var6, var7);
      this.setMillis(var8);
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

   public MutableDateTime copy() {
      return (MutableDateTime)this.clone();
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError("Clone error");
      }
   }

   public static final class Property extends AbstractReadableInstantFieldProperty {
      private static final long serialVersionUID = -4481126543819298617L;
      private MutableDateTime iInstant;
      private DateTimeField iField;

      Property(MutableDateTime var1, DateTimeField var2) {
         this.iInstant = var1;
         this.iField = var2;
      }

      private void writeObject(ObjectOutputStream var1) throws IOException {
         var1.writeObject(this.iInstant);
         var1.writeObject(this.iField.getType());
      }

      private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
         this.iInstant = (MutableDateTime)var1.readObject();
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

      public MutableDateTime getMutableDateTime() {
         return this.iInstant;
      }

      public MutableDateTime add(int var1) {
         this.iInstant.setMillis(this.getField().add(this.iInstant.getMillis(), var1));
         return this.iInstant;
      }

      public MutableDateTime add(long var1) {
         this.iInstant.setMillis(this.getField().add(this.iInstant.getMillis(), var1));
         return this.iInstant;
      }

      public MutableDateTime addWrapField(int var1) {
         this.iInstant.setMillis(this.getField().addWrapField(this.iInstant.getMillis(), var1));
         return this.iInstant;
      }

      public MutableDateTime set(int var1) {
         this.iInstant.setMillis(this.getField().set(this.iInstant.getMillis(), var1));
         return this.iInstant;
      }

      public MutableDateTime set(String var1, Locale var2) {
         this.iInstant.setMillis(this.getField().set(this.iInstant.getMillis(), var1, var2));
         return this.iInstant;
      }

      public MutableDateTime set(String var1) {
         this.set(var1, (Locale)null);
         return this.iInstant;
      }

      public MutableDateTime roundFloor() {
         this.iInstant.setMillis(this.getField().roundFloor(this.iInstant.getMillis()));
         return this.iInstant;
      }

      public MutableDateTime roundCeiling() {
         this.iInstant.setMillis(this.getField().roundCeiling(this.iInstant.getMillis()));
         return this.iInstant;
      }

      public MutableDateTime roundHalfFloor() {
         this.iInstant.setMillis(this.getField().roundHalfFloor(this.iInstant.getMillis()));
         return this.iInstant;
      }

      public MutableDateTime roundHalfCeiling() {
         this.iInstant.setMillis(this.getField().roundHalfCeiling(this.iInstant.getMillis()));
         return this.iInstant;
      }

      public MutableDateTime roundHalfEven() {
         this.iInstant.setMillis(this.getField().roundHalfEven(this.iInstant.getMillis()));
         return this.iInstant;
      }
   }
}
