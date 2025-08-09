package org.joda.time.chrono;

import java.util.HashMap;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadableDateTime;
import org.joda.time.field.DecoratedDateTimeField;
import org.joda.time.field.DecoratedDurationField;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class LimitChronology extends AssembledChronology {
   private static final long serialVersionUID = 7670866536893052522L;
   final DateTime iLowerLimit;
   final DateTime iUpperLimit;
   private transient LimitChronology iWithUTC;

   public static LimitChronology getInstance(Chronology var0, ReadableDateTime var1, ReadableDateTime var2) {
      if (var0 == null) {
         throw new IllegalArgumentException("Must supply a chronology");
      } else {
         var1 = var1 == null ? null : var1.toDateTime();
         var2 = var2 == null ? null : var2.toDateTime();
         if (var1 != null && var2 != null && !var1.isBefore(var2)) {
            throw new IllegalArgumentException("The lower limit must be come before than the upper limit");
         } else {
            return new LimitChronology(var0, var1, var2);
         }
      }
   }

   private LimitChronology(Chronology var1, DateTime var2, DateTime var3) {
      super(var1, (Object)null);
      this.iLowerLimit = var2;
      this.iUpperLimit = var3;
   }

   public DateTime getLowerLimit() {
      return this.iLowerLimit;
   }

   public DateTime getUpperLimit() {
      return this.iUpperLimit;
   }

   public Chronology withUTC() {
      return this.withZone(DateTimeZone.UTC);
   }

   public Chronology withZone(DateTimeZone var1) {
      if (var1 == null) {
         var1 = DateTimeZone.getDefault();
      }

      if (var1 == this.getZone()) {
         return this;
      } else if (var1 == DateTimeZone.UTC && this.iWithUTC != null) {
         return this.iWithUTC;
      } else {
         DateTime var2 = this.iLowerLimit;
         if (var2 != null) {
            MutableDateTime var3 = var2.toMutableDateTime();
            var3.setZoneRetainFields(var1);
            var2 = var3.toDateTime();
         }

         DateTime var5 = this.iUpperLimit;
         if (var5 != null) {
            MutableDateTime var4 = var5.toMutableDateTime();
            var4.setZoneRetainFields(var1);
            var5 = var4.toDateTime();
         }

         LimitChronology var6 = getInstance(this.getBase().withZone(var1), var2, var5);
         if (var1 == DateTimeZone.UTC) {
            this.iWithUTC = var6;
         }

         return var6;
      }
   }

   public long getDateTimeMillis(int var1, int var2, int var3, int var4) throws IllegalArgumentException {
      long var5 = this.getBase().getDateTimeMillis(var1, var2, var3, var4);
      this.checkLimits(var5, "resulting");
      return var5;
   }

   public long getDateTimeMillis(int var1, int var2, int var3, int var4, int var5, int var6, int var7) throws IllegalArgumentException {
      long var8 = this.getBase().getDateTimeMillis(var1, var2, var3, var4, var5, var6, var7);
      this.checkLimits(var8, "resulting");
      return var8;
   }

   public long getDateTimeMillis(long var1, int var3, int var4, int var5, int var6) throws IllegalArgumentException {
      this.checkLimits(var1, (String)null);
      var1 = this.getBase().getDateTimeMillis(var1, var3, var4, var5, var6);
      this.checkLimits(var1, "resulting");
      return var1;
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
            LimitDurationField var3 = new LimitDurationField(var1);
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
            LimitDateTimeField var3 = new LimitDateTimeField(var1, this.convertField(var1.getDurationField(), var2), this.convertField(var1.getRangeDurationField(), var2), this.convertField(var1.getLeapDurationField(), var2));
            var2.put(var1, var3);
            return var3;
         }
      } else {
         return var1;
      }
   }

   void checkLimits(long var1, String var3) {
      DateTime var4;
      if ((var4 = this.iLowerLimit) != null && var1 < var4.getMillis()) {
         throw new LimitException(var3, true);
      } else if ((var4 = this.iUpperLimit) != null && var1 >= var4.getMillis()) {
         throw new LimitException(var3, false);
      }
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof LimitChronology)) {
         return false;
      } else {
         LimitChronology var2 = (LimitChronology)var1;
         return this.getBase().equals(var2.getBase()) && FieldUtils.equals(this.getLowerLimit(), var2.getLowerLimit()) && FieldUtils.equals(this.getUpperLimit(), var2.getUpperLimit());
      }
   }

   public int hashCode() {
      int var1 = 317351877;
      var1 += this.getLowerLimit() != null ? this.getLowerLimit().hashCode() : 0;
      var1 += this.getUpperLimit() != null ? this.getUpperLimit().hashCode() : 0;
      var1 += this.getBase().hashCode() * 7;
      return var1;
   }

   public String toString() {
      return "LimitChronology[" + this.getBase().toString() + ", " + (this.getLowerLimit() == null ? "NoLimit" : this.getLowerLimit().toString()) + ", " + (this.getUpperLimit() == null ? "NoLimit" : this.getUpperLimit().toString()) + ']';
   }

   private class LimitException extends IllegalArgumentException {
      private static final long serialVersionUID = -5924689995607498581L;
      private final boolean iIsLow;

      LimitException(String var2, boolean var3) {
         super(var2);
         this.iIsLow = var3;
      }

      public String getMessage() {
         StringBuffer var1 = new StringBuffer(85);
         var1.append("The");
         String var2 = super.getMessage();
         if (var2 != null) {
            var1.append(' ');
            var1.append(var2);
         }

         var1.append(" instant is ");
         DateTimeFormatter var3 = ISODateTimeFormat.dateTime();
         var3 = var3.withChronology(LimitChronology.this.getBase());
         if (this.iIsLow) {
            var1.append("below the supported minimum of ");
            var3.printTo(var1, LimitChronology.this.getLowerLimit().getMillis());
         } else {
            var1.append("above the supported maximum of ");
            var3.printTo(var1, LimitChronology.this.getUpperLimit().getMillis());
         }

         var1.append(" (");
         var1.append(LimitChronology.this.getBase());
         var1.append(')');
         return var1.toString();
      }

      public String toString() {
         return "IllegalArgumentException: " + this.getMessage();
      }
   }

   private class LimitDurationField extends DecoratedDurationField {
      private static final long serialVersionUID = 8049297699408782284L;

      LimitDurationField(DurationField var2) {
         super(var2, var2.getType());
      }

      public int getValue(long var1, long var3) {
         LimitChronology.this.checkLimits(var3, (String)null);
         return this.getWrappedField().getValue(var1, var3);
      }

      public long getValueAsLong(long var1, long var3) {
         LimitChronology.this.checkLimits(var3, (String)null);
         return this.getWrappedField().getValueAsLong(var1, var3);
      }

      public long getMillis(int var1, long var2) {
         LimitChronology.this.checkLimits(var2, (String)null);
         return this.getWrappedField().getMillis(var1, var2);
      }

      public long getMillis(long var1, long var3) {
         LimitChronology.this.checkLimits(var3, (String)null);
         return this.getWrappedField().getMillis(var1, var3);
      }

      public long add(long var1, int var3) {
         LimitChronology.this.checkLimits(var1, (String)null);
         long var4 = this.getWrappedField().add(var1, var3);
         LimitChronology.this.checkLimits(var4, "resulting");
         return var4;
      }

      public long add(long var1, long var3) {
         LimitChronology.this.checkLimits(var1, (String)null);
         long var5 = this.getWrappedField().add(var1, var3);
         LimitChronology.this.checkLimits(var5, "resulting");
         return var5;
      }

      public int getDifference(long var1, long var3) {
         LimitChronology.this.checkLimits(var1, "minuend");
         LimitChronology.this.checkLimits(var3, "subtrahend");
         return this.getWrappedField().getDifference(var1, var3);
      }

      public long getDifferenceAsLong(long var1, long var3) {
         LimitChronology.this.checkLimits(var1, "minuend");
         LimitChronology.this.checkLimits(var3, "subtrahend");
         return this.getWrappedField().getDifferenceAsLong(var1, var3);
      }
   }

   private class LimitDateTimeField extends DecoratedDateTimeField {
      private static final long serialVersionUID = -2435306746995699312L;
      private final DurationField iDurationField;
      private final DurationField iRangeDurationField;
      private final DurationField iLeapDurationField;

      LimitDateTimeField(DateTimeField var2, DurationField var3, DurationField var4, DurationField var5) {
         super(var2, var2.getType());
         this.iDurationField = var3;
         this.iRangeDurationField = var4;
         this.iLeapDurationField = var5;
      }

      public int get(long var1) {
         LimitChronology.this.checkLimits(var1, (String)null);
         return this.getWrappedField().get(var1);
      }

      public String getAsText(long var1, Locale var3) {
         LimitChronology.this.checkLimits(var1, (String)null);
         return this.getWrappedField().getAsText(var1, var3);
      }

      public String getAsShortText(long var1, Locale var3) {
         LimitChronology.this.checkLimits(var1, (String)null);
         return this.getWrappedField().getAsShortText(var1, var3);
      }

      public long add(long var1, int var3) {
         LimitChronology.this.checkLimits(var1, (String)null);
         long var4 = this.getWrappedField().add(var1, var3);
         LimitChronology.this.checkLimits(var4, "resulting");
         return var4;
      }

      public long add(long var1, long var3) {
         LimitChronology.this.checkLimits(var1, (String)null);
         long var5 = this.getWrappedField().add(var1, var3);
         LimitChronology.this.checkLimits(var5, "resulting");
         return var5;
      }

      public long addWrapField(long var1, int var3) {
         LimitChronology.this.checkLimits(var1, (String)null);
         long var4 = this.getWrappedField().addWrapField(var1, var3);
         LimitChronology.this.checkLimits(var4, "resulting");
         return var4;
      }

      public int getDifference(long var1, long var3) {
         LimitChronology.this.checkLimits(var1, "minuend");
         LimitChronology.this.checkLimits(var3, "subtrahend");
         return this.getWrappedField().getDifference(var1, var3);
      }

      public long getDifferenceAsLong(long var1, long var3) {
         LimitChronology.this.checkLimits(var1, "minuend");
         LimitChronology.this.checkLimits(var3, "subtrahend");
         return this.getWrappedField().getDifferenceAsLong(var1, var3);
      }

      public long set(long var1, int var3) {
         LimitChronology.this.checkLimits(var1, (String)null);
         long var4 = this.getWrappedField().set(var1, var3);
         LimitChronology.this.checkLimits(var4, "resulting");
         return var4;
      }

      public long set(long var1, String var3, Locale var4) {
         LimitChronology.this.checkLimits(var1, (String)null);
         long var5 = this.getWrappedField().set(var1, var3, var4);
         LimitChronology.this.checkLimits(var5, "resulting");
         return var5;
      }

      public final DurationField getDurationField() {
         return this.iDurationField;
      }

      public final DurationField getRangeDurationField() {
         return this.iRangeDurationField;
      }

      public boolean isLeap(long var1) {
         LimitChronology.this.checkLimits(var1, (String)null);
         return this.getWrappedField().isLeap(var1);
      }

      public int getLeapAmount(long var1) {
         LimitChronology.this.checkLimits(var1, (String)null);
         return this.getWrappedField().getLeapAmount(var1);
      }

      public final DurationField getLeapDurationField() {
         return this.iLeapDurationField;
      }

      public long roundFloor(long var1) {
         LimitChronology.this.checkLimits(var1, (String)null);
         long var3 = this.getWrappedField().roundFloor(var1);
         LimitChronology.this.checkLimits(var3, "resulting");
         return var3;
      }

      public long roundCeiling(long var1) {
         LimitChronology.this.checkLimits(var1, (String)null);
         long var3 = this.getWrappedField().roundCeiling(var1);
         LimitChronology.this.checkLimits(var3, "resulting");
         return var3;
      }

      public long roundHalfFloor(long var1) {
         LimitChronology.this.checkLimits(var1, (String)null);
         long var3 = this.getWrappedField().roundHalfFloor(var1);
         LimitChronology.this.checkLimits(var3, "resulting");
         return var3;
      }

      public long roundHalfCeiling(long var1) {
         LimitChronology.this.checkLimits(var1, (String)null);
         long var3 = this.getWrappedField().roundHalfCeiling(var1);
         LimitChronology.this.checkLimits(var3, "resulting");
         return var3;
      }

      public long roundHalfEven(long var1) {
         LimitChronology.this.checkLimits(var1, (String)null);
         long var3 = this.getWrappedField().roundHalfEven(var1);
         LimitChronology.this.checkLimits(var3, "resulting");
         return var3;
      }

      public long remainder(long var1) {
         LimitChronology.this.checkLimits(var1, (String)null);
         long var3 = this.getWrappedField().remainder(var1);
         LimitChronology.this.checkLimits(var3, "resulting");
         return var3;
      }

      public int getMinimumValue(long var1) {
         LimitChronology.this.checkLimits(var1, (String)null);
         return this.getWrappedField().getMinimumValue(var1);
      }

      public int getMaximumValue(long var1) {
         LimitChronology.this.checkLimits(var1, (String)null);
         return this.getWrappedField().getMaximumValue(var1);
      }

      public int getMaximumTextLength(Locale var1) {
         return this.getWrappedField().getMaximumTextLength(var1);
      }

      public int getMaximumShortTextLength(Locale var1) {
         return this.getWrappedField().getMaximumShortTextLength(var1);
      }
   }
}
