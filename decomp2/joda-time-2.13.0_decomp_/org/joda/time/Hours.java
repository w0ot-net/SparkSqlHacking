package org.joda.time;

import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Hours extends BaseSingleFieldPeriod {
   public static final Hours ZERO = new Hours(0);
   public static final Hours ONE = new Hours(1);
   public static final Hours TWO = new Hours(2);
   public static final Hours THREE = new Hours(3);
   public static final Hours FOUR = new Hours(4);
   public static final Hours FIVE = new Hours(5);
   public static final Hours SIX = new Hours(6);
   public static final Hours SEVEN = new Hours(7);
   public static final Hours EIGHT = new Hours(8);
   public static final Hours MAX_VALUE = new Hours(Integer.MAX_VALUE);
   public static final Hours MIN_VALUE = new Hours(Integer.MIN_VALUE);
   private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.hours());
   private static final long serialVersionUID = 87525275727380864L;

   public static Hours hours(int var0) {
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
         case 4:
            return FOUR;
         case 5:
            return FIVE;
         case 6:
            return SIX;
         case 7:
            return SEVEN;
         case 8:
            return EIGHT;
         case Integer.MAX_VALUE:
            return MAX_VALUE;
         default:
            return new Hours(var0);
      }
   }

   public static Hours hoursBetween(ReadableInstant var0, ReadableInstant var1) {
      int var2 = BaseSingleFieldPeriod.between(var0, var1, DurationFieldType.hours());
      return hours(var2);
   }

   public static Hours hoursBetween(ReadablePartial var0, ReadablePartial var1) {
      if (var0 instanceof LocalTime && var1 instanceof LocalTime) {
         Chronology var4 = DateTimeUtils.getChronology(var0.getChronology());
         int var3 = var4.hours().getDifference(((LocalTime)var1).getLocalMillis(), ((LocalTime)var0).getLocalMillis());
         return hours(var3);
      } else {
         int var2 = BaseSingleFieldPeriod.between((ReadablePartial)var0, (ReadablePartial)var1, (ReadablePeriod)ZERO);
         return hours(var2);
      }
   }

   public static Hours hoursIn(ReadableInterval var0) {
      if (var0 == null) {
         return ZERO;
      } else {
         int var1 = BaseSingleFieldPeriod.between((ReadableInstant)var0.getStart(), (ReadableInstant)var0.getEnd(), (DurationFieldType)DurationFieldType.hours());
         return hours(var1);
      }
   }

   public static Hours standardHoursIn(ReadablePeriod var0) {
      int var1 = BaseSingleFieldPeriod.standardPeriodIn(var0, 3600000L);
      return hours(var1);
   }

   @FromString
   public static Hours parseHours(String var0) {
      if (var0 == null) {
         return ZERO;
      } else {
         Period var1 = PARSER.parsePeriod(var0);
         return hours(var1.getHours());
      }
   }

   private Hours(int var1) {
      super(var1);
   }

   private Object readResolve() {
      return hours(this.getValue());
   }

   public DurationFieldType getFieldType() {
      return DurationFieldType.hours();
   }

   public PeriodType getPeriodType() {
      return PeriodType.hours();
   }

   public Weeks toStandardWeeks() {
      return Weeks.weeks(this.getValue() / 168);
   }

   public Days toStandardDays() {
      return Days.days(this.getValue() / 24);
   }

   public Minutes toStandardMinutes() {
      return Minutes.minutes(FieldUtils.safeMultiply(this.getValue(), 60));
   }

   public Seconds toStandardSeconds() {
      return Seconds.seconds(FieldUtils.safeMultiply(this.getValue(), 3600));
   }

   public Duration toStandardDuration() {
      long var1 = (long)this.getValue();
      return new Duration(var1 * 3600000L);
   }

   public int getHours() {
      return this.getValue();
   }

   public Hours plus(int var1) {
      return var1 == 0 ? this : hours(FieldUtils.safeAdd(this.getValue(), var1));
   }

   public Hours plus(Hours var1) {
      return var1 == null ? this : this.plus(var1.getValue());
   }

   public Hours minus(int var1) {
      return this.plus(FieldUtils.safeNegate(var1));
   }

   public Hours minus(Hours var1) {
      return var1 == null ? this : this.minus(var1.getValue());
   }

   public Hours multipliedBy(int var1) {
      return hours(FieldUtils.safeMultiply(this.getValue(), var1));
   }

   public Hours dividedBy(int var1) {
      return var1 == 1 ? this : hours(this.getValue() / var1);
   }

   public Hours negated() {
      return hours(FieldUtils.safeNegate(this.getValue()));
   }

   public boolean isGreaterThan(Hours var1) {
      if (var1 == null) {
         return this.getValue() > 0;
      } else {
         return this.getValue() > var1.getValue();
      }
   }

   public boolean isLessThan(Hours var1) {
      if (var1 == null) {
         return this.getValue() < 0;
      } else {
         return this.getValue() < var1.getValue();
      }
   }

   @ToString
   public String toString() {
      return "PT" + String.valueOf(this.getValue()) + "H";
   }
}
