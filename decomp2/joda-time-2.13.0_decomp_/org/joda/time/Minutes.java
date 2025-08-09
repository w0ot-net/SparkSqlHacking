package org.joda.time;

import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Minutes extends BaseSingleFieldPeriod {
   public static final Minutes ZERO = new Minutes(0);
   public static final Minutes ONE = new Minutes(1);
   public static final Minutes TWO = new Minutes(2);
   public static final Minutes THREE = new Minutes(3);
   public static final Minutes MAX_VALUE = new Minutes(Integer.MAX_VALUE);
   public static final Minutes MIN_VALUE = new Minutes(Integer.MIN_VALUE);
   private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.minutes());
   private static final long serialVersionUID = 87525275727380863L;

   public static Minutes minutes(int var0) {
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
            return new Minutes(var0);
      }
   }

   public static Minutes minutesBetween(ReadableInstant var0, ReadableInstant var1) {
      int var2 = BaseSingleFieldPeriod.between(var0, var1, DurationFieldType.minutes());
      return minutes(var2);
   }

   public static Minutes minutesBetween(ReadablePartial var0, ReadablePartial var1) {
      if (var0 instanceof LocalTime && var1 instanceof LocalTime) {
         Chronology var4 = DateTimeUtils.getChronology(var0.getChronology());
         int var3 = var4.minutes().getDifference(((LocalTime)var1).getLocalMillis(), ((LocalTime)var0).getLocalMillis());
         return minutes(var3);
      } else {
         int var2 = BaseSingleFieldPeriod.between((ReadablePartial)var0, (ReadablePartial)var1, (ReadablePeriod)ZERO);
         return minutes(var2);
      }
   }

   public static Minutes minutesIn(ReadableInterval var0) {
      if (var0 == null) {
         return ZERO;
      } else {
         int var1 = BaseSingleFieldPeriod.between((ReadableInstant)var0.getStart(), (ReadableInstant)var0.getEnd(), (DurationFieldType)DurationFieldType.minutes());
         return minutes(var1);
      }
   }

   public static Minutes standardMinutesIn(ReadablePeriod var0) {
      int var1 = BaseSingleFieldPeriod.standardPeriodIn(var0, 60000L);
      return minutes(var1);
   }

   @FromString
   public static Minutes parseMinutes(String var0) {
      if (var0 == null) {
         return ZERO;
      } else {
         Period var1 = PARSER.parsePeriod(var0);
         return minutes(var1.getMinutes());
      }
   }

   private Minutes(int var1) {
      super(var1);
   }

   private Object readResolve() {
      return minutes(this.getValue());
   }

   public DurationFieldType getFieldType() {
      return DurationFieldType.minutes();
   }

   public PeriodType getPeriodType() {
      return PeriodType.minutes();
   }

   public Weeks toStandardWeeks() {
      return Weeks.weeks(this.getValue() / 10080);
   }

   public Days toStandardDays() {
      return Days.days(this.getValue() / 1440);
   }

   public Hours toStandardHours() {
      return Hours.hours(this.getValue() / 60);
   }

   public Seconds toStandardSeconds() {
      return Seconds.seconds(FieldUtils.safeMultiply(this.getValue(), 60));
   }

   public Duration toStandardDuration() {
      long var1 = (long)this.getValue();
      return new Duration(var1 * 60000L);
   }

   public int getMinutes() {
      return this.getValue();
   }

   public Minutes plus(int var1) {
      return var1 == 0 ? this : minutes(FieldUtils.safeAdd(this.getValue(), var1));
   }

   public Minutes plus(Minutes var1) {
      return var1 == null ? this : this.plus(var1.getValue());
   }

   public Minutes minus(int var1) {
      return this.plus(FieldUtils.safeNegate(var1));
   }

   public Minutes minus(Minutes var1) {
      return var1 == null ? this : this.minus(var1.getValue());
   }

   public Minutes multipliedBy(int var1) {
      return minutes(FieldUtils.safeMultiply(this.getValue(), var1));
   }

   public Minutes dividedBy(int var1) {
      return var1 == 1 ? this : minutes(this.getValue() / var1);
   }

   public Minutes negated() {
      return minutes(FieldUtils.safeNegate(this.getValue()));
   }

   public boolean isGreaterThan(Minutes var1) {
      if (var1 == null) {
         return this.getValue() > 0;
      } else {
         return this.getValue() > var1.getValue();
      }
   }

   public boolean isLessThan(Minutes var1) {
      if (var1 == null) {
         return this.getValue() < 0;
      } else {
         return this.getValue() < var1.getValue();
      }
   }

   @ToString
   public String toString() {
      return "PT" + String.valueOf(this.getValue()) + "M";
   }
}
