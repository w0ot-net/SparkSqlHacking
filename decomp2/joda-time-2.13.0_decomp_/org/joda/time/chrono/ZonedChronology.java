package org.joda.time.chrono;

import java.util.HashMap;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.IllegalInstantException;
import org.joda.time.ReadablePartial;
import org.joda.time.field.BaseDateTimeField;
import org.joda.time.field.BaseDurationField;

public final class ZonedChronology extends AssembledChronology {
   private static final long serialVersionUID = -1079258847191166848L;
   private static final long NEAR_ZERO = 604800000L;

   public static ZonedChronology getInstance(Chronology var0, DateTimeZone var1) {
      if (var0 == null) {
         throw new IllegalArgumentException("Must supply a chronology");
      } else {
         var0 = var0.withUTC();
         if (var0 == null) {
            throw new IllegalArgumentException("UTC chronology must not be null");
         } else if (var1 == null) {
            throw new IllegalArgumentException("DateTimeZone must not be null");
         } else {
            return new ZonedChronology(var0, var1);
         }
      }
   }

   static boolean useTimeArithmetic(DurationField var0) {
      return var0 != null && var0.getUnitMillis() < 43200000L;
   }

   private ZonedChronology(Chronology var1, DateTimeZone var2) {
      super(var1, var2);
   }

   public DateTimeZone getZone() {
      return (DateTimeZone)this.getParam();
   }

   public Chronology withUTC() {
      return this.getBase();
   }

   public Chronology withZone(DateTimeZone var1) {
      if (var1 == null) {
         var1 = DateTimeZone.getDefault();
      }

      if (var1 == this.getParam()) {
         return this;
      } else {
         return (Chronology)(var1 == DateTimeZone.UTC ? this.getBase() : new ZonedChronology(this.getBase(), var1));
      }
   }

   public long getDateTimeMillis(int var1, int var2, int var3, int var4) throws IllegalArgumentException {
      return this.localToUTC(this.getBase().getDateTimeMillis(var1, var2, var3, var4));
   }

   public long getDateTimeMillis(int var1, int var2, int var3, int var4, int var5, int var6, int var7) throws IllegalArgumentException {
      return this.localToUTC(this.getBase().getDateTimeMillis(var1, var2, var3, var4, var5, var6, var7));
   }

   public long getDateTimeMillis(long var1, int var3, int var4, int var5, int var6) throws IllegalArgumentException {
      return this.localToUTC(this.getBase().getDateTimeMillis(var1 + (long)this.getZone().getOffset(var1), var3, var4, var5, var6));
   }

   private long localToUTC(long var1) {
      if (var1 == Long.MAX_VALUE) {
         return Long.MAX_VALUE;
      } else if (var1 == Long.MIN_VALUE) {
         return Long.MIN_VALUE;
      } else {
         DateTimeZone var3 = this.getZone();
         int var4 = var3.getOffsetFromLocal(var1);
         long var5 = var1 - (long)var4;
         if (var1 > 604800000L && var5 < 0L) {
            return Long.MAX_VALUE;
         } else if (var1 < -604800000L && var5 > 0L) {
            return Long.MIN_VALUE;
         } else {
            int var7 = var3.getOffset(var5);
            if (var4 != var7) {
               throw new IllegalInstantException(var1, var3.getID());
            } else {
               return var5;
            }
         }
      }
   }

   protected void assemble(AssembledChronology.Fields var1) {
      HashMap var2 = new HashMap();
      var1.eras = this.convertField(var1.eras, var2);
      var1.centuries = this.convertField(var1.centuries, var2);
      var1.years = this.convertField(var1.years, var2);
      var1.months = this.convertField(var1.months, var2);
      var1.weekyears = this.convertField(var1.weekyears, var2);
      var1.weeks = this.convertField(var1.weeks, var2);
      var1.days = this.convertField(var1.days, var2);
      var1.halfdays = this.convertField(var1.halfdays, var2);
      var1.hours = this.convertField(var1.hours, var2);
      var1.minutes = this.convertField(var1.minutes, var2);
      var1.seconds = this.convertField(var1.seconds, var2);
      var1.millis = this.convertField(var1.millis, var2);
      var1.year = this.convertField(var1.year, var2);
      var1.yearOfEra = this.convertField(var1.yearOfEra, var2);
      var1.yearOfCentury = this.convertField(var1.yearOfCentury, var2);
      var1.centuryOfEra = this.convertField(var1.centuryOfEra, var2);
      var1.era = this.convertField(var1.era, var2);
      var1.dayOfWeek = this.convertField(var1.dayOfWeek, var2);
      var1.dayOfMonth = this.convertField(var1.dayOfMonth, var2);
      var1.dayOfYear = this.convertField(var1.dayOfYear, var2);
      var1.monthOfYear = this.convertField(var1.monthOfYear, var2);
      var1.weekOfWeekyear = this.convertField(var1.weekOfWeekyear, var2);
      var1.weekyear = this.convertField(var1.weekyear, var2);
      var1.weekyearOfCentury = this.convertField(var1.weekyearOfCentury, var2);
      var1.millisOfSecond = this.convertField(var1.millisOfSecond, var2);
      var1.millisOfDay = this.convertField(var1.millisOfDay, var2);
      var1.secondOfMinute = this.convertField(var1.secondOfMinute, var2);
      var1.secondOfDay = this.convertField(var1.secondOfDay, var2);
      var1.minuteOfHour = this.convertField(var1.minuteOfHour, var2);
      var1.minuteOfDay = this.convertField(var1.minuteOfDay, var2);
      var1.hourOfDay = this.convertField(var1.hourOfDay, var2);
      var1.hourOfHalfday = this.convertField(var1.hourOfHalfday, var2);
      var1.clockhourOfDay = this.convertField(var1.clockhourOfDay, var2);
      var1.clockhourOfHalfday = this.convertField(var1.clockhourOfHalfday, var2);
      var1.halfdayOfDay = this.convertField(var1.halfdayOfDay, var2);
   }

   private DurationField convertField(DurationField var1, HashMap var2) {
      if (var1 != null && var1.isSupported()) {
         if (var2.containsKey(var1)) {
            return (DurationField)var2.get(var1);
         } else {
            ZonedDurationField var3 = new ZonedDurationField(var1, this.getZone());
            var2.put(var1, var3);
            return var3;
         }
      } else {
         return var1;
      }
   }

   private DateTimeField convertField(DateTimeField var1, HashMap var2) {
      if (var1 != null && var1.isSupported()) {
         if (var2.containsKey(var1)) {
            return (DateTimeField)var2.get(var1);
         } else {
            ZonedDateTimeField var3 = new ZonedDateTimeField(var1, this.getZone(), this.convertField(var1.getDurationField(), var2), this.convertField(var1.getRangeDurationField(), var2), this.convertField(var1.getLeapDurationField(), var2));
            var2.put(var1, var3);
            return var3;
         }
      } else {
         return var1;
      }
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof ZonedChronology)) {
         return false;
      } else {
         ZonedChronology var2 = (ZonedChronology)var1;
         return this.getBase().equals(var2.getBase()) && this.getZone().equals(var2.getZone());
      }
   }

   public int hashCode() {
      return 326565 + this.getZone().hashCode() * 11 + this.getBase().hashCode() * 7;
   }

   public String toString() {
      return "ZonedChronology[" + this.getBase() + ", " + this.getZone().getID() + ']';
   }

   static class ZonedDurationField extends BaseDurationField {
      private static final long serialVersionUID = -485345310999208286L;
      final DurationField iField;
      final boolean iTimeField;
      final DateTimeZone iZone;

      ZonedDurationField(DurationField var1, DateTimeZone var2) {
         super(var1.getType());
         if (!var1.isSupported()) {
            throw new IllegalArgumentException();
         } else {
            this.iField = var1;
            this.iTimeField = ZonedChronology.useTimeArithmetic(var1);
            this.iZone = var2;
         }
      }

      public boolean isPrecise() {
         return this.iTimeField ? this.iField.isPrecise() : this.iField.isPrecise() && this.iZone.isFixed();
      }

      public long getUnitMillis() {
         return this.iField.getUnitMillis();
      }

      public int getValue(long var1, long var3) {
         return this.iField.getValue(var1, this.addOffset(var3));
      }

      public long getValueAsLong(long var1, long var3) {
         return this.iField.getValueAsLong(var1, this.addOffset(var3));
      }

      public long getMillis(int var1, long var2) {
         return this.iField.getMillis(var1, this.addOffset(var2));
      }

      public long getMillis(long var1, long var3) {
         return this.iField.getMillis(var1, this.addOffset(var3));
      }

      public long add(long var1, int var3) {
         int var4 = this.getOffsetToAdd(var1);
         var1 = this.iField.add(var1 + (long)var4, var3);
         return var1 - (long)(this.iTimeField ? var4 : this.getOffsetFromLocalToSubtract(var1));
      }

      public long add(long var1, long var3) {
         int var5 = this.getOffsetToAdd(var1);
         var1 = this.iField.add(var1 + (long)var5, var3);
         return var1 - (long)(this.iTimeField ? var5 : this.getOffsetFromLocalToSubtract(var1));
      }

      public int getDifference(long var1, long var3) {
         int var5 = this.getOffsetToAdd(var3);
         return this.iField.getDifference(var1 + (long)(this.iTimeField ? var5 : this.getOffsetToAdd(var1)), var3 + (long)var5);
      }

      public long getDifferenceAsLong(long var1, long var3) {
         int var5 = this.getOffsetToAdd(var3);
         return this.iField.getDifferenceAsLong(var1 + (long)(this.iTimeField ? var5 : this.getOffsetToAdd(var1)), var3 + (long)var5);
      }

      private int getOffsetToAdd(long var1) {
         int var3 = this.iZone.getOffset(var1);
         long var4 = var1 + (long)var3;
         if ((var1 ^ var4) < 0L && (var1 ^ (long)var3) >= 0L) {
            throw new ArithmeticException("Adding time zone offset caused overflow");
         } else {
            return var3;
         }
      }

      private int getOffsetFromLocalToSubtract(long var1) {
         int var3 = this.iZone.getOffsetFromLocal(var1);
         long var4 = var1 - (long)var3;
         if ((var1 ^ var4) < 0L && (var1 ^ (long)var3) < 0L) {
            throw new ArithmeticException("Subtracting time zone offset caused overflow");
         } else {
            return var3;
         }
      }

      private long addOffset(long var1) {
         return this.iZone.convertUTCToLocal(var1);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof ZonedDurationField)) {
            return false;
         } else {
            ZonedDurationField var2 = (ZonedDurationField)var1;
            return this.iField.equals(var2.iField) && this.iZone.equals(var2.iZone);
         }
      }

      public int hashCode() {
         return this.iField.hashCode() ^ this.iZone.hashCode();
      }
   }

   static final class ZonedDateTimeField extends BaseDateTimeField {
      private static final long serialVersionUID = -3968986277775529794L;
      final DateTimeField iField;
      final DateTimeZone iZone;
      final DurationField iDurationField;
      final boolean iTimeField;
      final DurationField iRangeDurationField;
      final DurationField iLeapDurationField;

      ZonedDateTimeField(DateTimeField var1, DateTimeZone var2, DurationField var3, DurationField var4, DurationField var5) {
         super(var1.getType());
         if (!var1.isSupported()) {
            throw new IllegalArgumentException();
         } else {
            this.iField = var1;
            this.iZone = var2;
            this.iDurationField = var3;
            this.iTimeField = ZonedChronology.useTimeArithmetic(var3);
            this.iRangeDurationField = var4;
            this.iLeapDurationField = var5;
         }
      }

      public boolean isLenient() {
         return this.iField.isLenient();
      }

      public int get(long var1) {
         long var3 = this.iZone.convertUTCToLocal(var1);
         return this.iField.get(var3);
      }

      public String getAsText(long var1, Locale var3) {
         long var4 = this.iZone.convertUTCToLocal(var1);
         return this.iField.getAsText(var4, var3);
      }

      public String getAsShortText(long var1, Locale var3) {
         long var4 = this.iZone.convertUTCToLocal(var1);
         return this.iField.getAsShortText(var4, var3);
      }

      public String getAsText(int var1, Locale var2) {
         return this.iField.getAsText(var1, var2);
      }

      public String getAsShortText(int var1, Locale var2) {
         return this.iField.getAsShortText(var1, var2);
      }

      public long add(long var1, int var3) {
         if (this.iTimeField) {
            int var8 = this.getOffsetToAdd(var1);
            long var5 = this.iField.add(var1 + (long)var8, var3);
            return var5 - (long)var8;
         } else {
            long var4 = this.iZone.convertUTCToLocal(var1);
            var4 = this.iField.add(var4, var3);
            return this.iZone.convertLocalToUTC(var4, false, var1);
         }
      }

      public long add(long var1, long var3) {
         if (this.iTimeField) {
            int var9 = this.getOffsetToAdd(var1);
            long var6 = this.iField.add(var1 + (long)var9, var3);
            return var6 - (long)var9;
         } else {
            long var5 = this.iZone.convertUTCToLocal(var1);
            var5 = this.iField.add(var5, var3);
            return this.iZone.convertLocalToUTC(var5, false, var1);
         }
      }

      public long addWrapField(long var1, int var3) {
         if (this.iTimeField) {
            int var8 = this.getOffsetToAdd(var1);
            long var5 = this.iField.addWrapField(var1 + (long)var8, var3);
            return var5 - (long)var8;
         } else {
            long var4 = this.iZone.convertUTCToLocal(var1);
            var4 = this.iField.addWrapField(var4, var3);
            return this.iZone.convertLocalToUTC(var4, false, var1);
         }
      }

      public long set(long var1, int var3) {
         long var4 = this.iZone.convertUTCToLocal(var1);
         var4 = this.iField.set(var4, var3);
         long var6 = this.iZone.convertLocalToUTC(var4, false, var1);
         if (this.get(var6) != var3) {
            IllegalInstantException var8 = new IllegalInstantException(var4, this.iZone.getID());
            IllegalFieldValueException var9 = new IllegalFieldValueException(this.iField.getType(), var3, var8.getMessage());
            var9.initCause(var8);
            throw var9;
         } else {
            return var6;
         }
      }

      public long set(long var1, String var3, Locale var4) {
         long var5 = this.iZone.convertUTCToLocal(var1);
         var5 = this.iField.set(var5, var3, var4);
         return this.iZone.convertLocalToUTC(var5, false, var1);
      }

      public int getDifference(long var1, long var3) {
         int var5 = this.getOffsetToAdd(var3);
         return this.iField.getDifference(var1 + (long)(this.iTimeField ? var5 : this.getOffsetToAdd(var1)), var3 + (long)var5);
      }

      public long getDifferenceAsLong(long var1, long var3) {
         int var5 = this.getOffsetToAdd(var3);
         return this.iField.getDifferenceAsLong(var1 + (long)(this.iTimeField ? var5 : this.getOffsetToAdd(var1)), var3 + (long)var5);
      }

      public final DurationField getDurationField() {
         return this.iDurationField;
      }

      public final DurationField getRangeDurationField() {
         return this.iRangeDurationField;
      }

      public boolean isLeap(long var1) {
         long var3 = this.iZone.convertUTCToLocal(var1);
         return this.iField.isLeap(var3);
      }

      public int getLeapAmount(long var1) {
         long var3 = this.iZone.convertUTCToLocal(var1);
         return this.iField.getLeapAmount(var3);
      }

      public final DurationField getLeapDurationField() {
         return this.iLeapDurationField;
      }

      public long roundFloor(long var1) {
         if (this.iTimeField) {
            int var7 = this.getOffsetToAdd(var1);
            var1 = this.iField.roundFloor(var1 + (long)var7);
            return var1 - (long)var7;
         } else {
            long var3 = this.iZone.convertUTCToLocal(var1);
            var3 = this.iField.roundFloor(var3);
            return this.iZone.convertLocalToUTC(var3, false, var1);
         }
      }

      public long roundCeiling(long var1) {
         if (this.iTimeField) {
            int var7 = this.getOffsetToAdd(var1);
            var1 = this.iField.roundCeiling(var1 + (long)var7);
            return var1 - (long)var7;
         } else {
            long var3 = this.iZone.convertUTCToLocal(var1);
            var3 = this.iField.roundCeiling(var3);
            return this.iZone.convertLocalToUTC(var3, false, var1);
         }
      }

      public long remainder(long var1) {
         long var3 = this.iZone.convertUTCToLocal(var1);
         return this.iField.remainder(var3);
      }

      public int getMinimumValue() {
         return this.iField.getMinimumValue();
      }

      public int getMinimumValue(long var1) {
         long var3 = this.iZone.convertUTCToLocal(var1);
         return this.iField.getMinimumValue(var3);
      }

      public int getMinimumValue(ReadablePartial var1) {
         return this.iField.getMinimumValue(var1);
      }

      public int getMinimumValue(ReadablePartial var1, int[] var2) {
         return this.iField.getMinimumValue(var1, var2);
      }

      public int getMaximumValue() {
         return this.iField.getMaximumValue();
      }

      public int getMaximumValue(long var1) {
         long var3 = this.iZone.convertUTCToLocal(var1);
         return this.iField.getMaximumValue(var3);
      }

      public int getMaximumValue(ReadablePartial var1) {
         return this.iField.getMaximumValue(var1);
      }

      public int getMaximumValue(ReadablePartial var1, int[] var2) {
         return this.iField.getMaximumValue(var1, var2);
      }

      public int getMaximumTextLength(Locale var1) {
         return this.iField.getMaximumTextLength(var1);
      }

      public int getMaximumShortTextLength(Locale var1) {
         return this.iField.getMaximumShortTextLength(var1);
      }

      private int getOffsetToAdd(long var1) {
         int var3 = this.iZone.getOffset(var1);
         long var4 = var1 + (long)var3;
         if ((var1 ^ var4) < 0L && (var1 ^ (long)var3) >= 0L) {
            throw new ArithmeticException("Adding time zone offset caused overflow");
         } else {
            return var3;
         }
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof ZonedDateTimeField)) {
            return false;
         } else {
            ZonedDateTimeField var2 = (ZonedDateTimeField)var1;
            return this.iField.equals(var2.iField) && this.iZone.equals(var2.iZone) && this.iDurationField.equals(var2.iDurationField) && this.iRangeDurationField.equals(var2.iRangeDurationField);
         }
      }

      public int hashCode() {
         return this.iField.hashCode() ^ this.iZone.hashCode();
      }
   }
}
