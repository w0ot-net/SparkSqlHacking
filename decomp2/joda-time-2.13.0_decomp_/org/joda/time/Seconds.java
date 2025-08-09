package org.joda.time;

import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Seconds extends BaseSingleFieldPeriod {
   public static final Seconds ZERO = new Seconds(0);
   public static final Seconds ONE = new Seconds(1);
   public static final Seconds TWO = new Seconds(2);
   public static final Seconds THREE = new Seconds(3);
   public static final Seconds MAX_VALUE = new Seconds(Integer.MAX_VALUE);
   public static final Seconds MIN_VALUE = new Seconds(Integer.MIN_VALUE);
   private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.seconds());
   private static final long serialVersionUID = 87525275727380862L;

   public static Seconds seconds(int var0) {
      switch (var0) {
         case Integer.MIN_VALUE:
            return MIN_VALUE;
         case 0:
            return ZERO;
         case 1:
            return ONE;
         case 2:
            return TWO;
         case 3:
            return THREE;
         case Integer.MAX_VALUE:
            return MAX_VALUE;
         default:
            return new Seconds(var0);
      }
   }

   public static Seconds secondsBetween(ReadableInstant var0, ReadableInstant var1) {
      int var2 = BaseSingleFieldPeriod.between(var0, var1, DurationFieldType.seconds());
      return seconds(var2);
   }

   public static Seconds secondsBetween(ReadablePartial var0, ReadablePartial var1) {
      if (var0 instanceof LocalTime && var1 instanceof LocalTime) {
         Chronology var4 = DateTimeUtils.getChronology(var0.getChronology());
         int var3 = var4.seconds().getDifference(((LocalTime)var1).getLocalMillis(), ((LocalTime)var0).getLocalMillis());
         return seconds(var3);
      } else {
         int var2 = BaseSingleFieldPeriod.between((ReadablePartial)var0, (ReadablePartial)var1, (ReadablePeriod)ZERO);
         return seconds(var2);
      }
   }

   public static Seconds secondsIn(ReadableInterval var0) {
      if (var0 == null) {
         return ZERO;
      } else {
         int var1 = BaseSingleFieldPeriod.between((ReadableInstant)var0.getStart(), (ReadableInstant)var0.getEnd(), (DurationFieldType)DurationFieldType.seconds());
         return seconds(var1);
      }
   }

   public static Seconds standardSecondsIn(ReadablePeriod var0) {
      int var1 = BaseSingleFieldPeriod.standardPeriodIn(var0, 1000L);
      return seconds(var1);
   }

   @FromString
   public static Seconds parseSeconds(String var0) {
      if (var0 == null) {
         return ZERO;
      } else {
         Period var1 = PARSER.parsePeriod(var0);
         return seconds(var1.getSeconds());
      }
   }

   private Seconds(int var1) {
      super(var1);
   }

   private Object readResolve() {
      return seconds(this.getValue());
   }

   public DurationFieldType getFieldType() {
      return DurationFieldType.seconds();
   }

   public PeriodType getPeriodType() {
      return PeriodType.seconds();
   }

   public Weeks toStandardWeeks() {
      return Weeks.weeks(this.getValue() / 604800);
   }

   public Days toStandardDays() {
      return Days.days(this.getValue() / 86400);
   }

   public Hours toStandardHours() {
      return Hours.hours(this.getValue() / 3600);
   }

   public Minutes toStandardMinutes() {
      return Minutes.minutes(this.getValue() / 60);
   }

   public Duration toStandardDuration() {
      long var1 = (long)this.getValue();
      return new Duration(var1 * 1000L);
   }

   public int getSeconds() {
      return this.getValue();
   }

   public Seconds plus(int var1) {
      return var1 == 0 ? this : seconds(FieldUtils.safeAdd(this.getValue(), var1));
   }

   public Seconds plus(Seconds var1) {
      return var1 == null ? this : this.plus(var1.getValue());
   }

   public Seconds minus(int var1) {
      return this.plus(FieldUtils.safeNegate(var1));
   }

   public Seconds minus(Seconds var1) {
      return var1 == null ? this : this.minus(var1.getValue());
   }

   public Seconds multipliedBy(int var1) {
      return seconds(FieldUtils.safeMultiply(this.getValue(), var1));
   }

   public Seconds dividedBy(int var1) {
      return var1 == 1 ? this : seconds(this.getValue() / var1);
   }

   public Seconds negated() {
      return seconds(FieldUtils.safeNegate(this.getValue()));
   }

   public boolean isGreaterThan(Seconds var1) {
      if (var1 == null) {
         return this.getValue() > 0;
      } else {
         return this.getValue() > var1.getValue();
      }
   }

   public boolean isLessThan(Seconds var1) {
      if (var1 == null) {
         return this.getValue() < 0;
      } else {
         return this.getValue() < var1.getValue();
      }
   }

   @ToString
   public String toString() {
      return "PT" + String.valueOf(this.getValue()) + "S";
   }
}
