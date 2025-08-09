package org.joda.time;

import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Weeks extends BaseSingleFieldPeriod {
   public static final Weeks ZERO = new Weeks(0);
   public static final Weeks ONE = new Weeks(1);
   public static final Weeks TWO = new Weeks(2);
   public static final Weeks THREE = new Weeks(3);
   public static final Weeks MAX_VALUE = new Weeks(Integer.MAX_VALUE);
   public static final Weeks MIN_VALUE = new Weeks(Integer.MIN_VALUE);
   private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.weeks());
   private static final long serialVersionUID = 87525275727380866L;

   public static Weeks weeks(int var0) {
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
            return new Weeks(var0);
      }
   }

   public static Weeks weeksBetween(ReadableInstant var0, ReadableInstant var1) {
      int var2 = BaseSingleFieldPeriod.between(var0, var1, DurationFieldType.weeks());
      return weeks(var2);
   }

   public static Weeks weeksBetween(ReadablePartial var0, ReadablePartial var1) {
      if (var0 instanceof LocalDate && var1 instanceof LocalDate) {
         Chronology var4 = DateTimeUtils.getChronology(var0.getChronology());
         int var3 = var4.weeks().getDifference(((LocalDate)var1).getLocalMillis(), ((LocalDate)var0).getLocalMillis());
         return weeks(var3);
      } else {
         int var2 = BaseSingleFieldPeriod.between((ReadablePartial)var0, (ReadablePartial)var1, (ReadablePeriod)ZERO);
         return weeks(var2);
      }
   }

   public static Weeks weeksIn(ReadableInterval var0) {
      if (var0 == null) {
         return ZERO;
      } else {
         int var1 = BaseSingleFieldPeriod.between((ReadableInstant)var0.getStart(), (ReadableInstant)var0.getEnd(), (DurationFieldType)DurationFieldType.weeks());
         return weeks(var1);
      }
   }

   public static Weeks standardWeeksIn(ReadablePeriod var0) {
      int var1 = BaseSingleFieldPeriod.standardPeriodIn(var0, 604800000L);
      return weeks(var1);
   }

   @FromString
   public static Weeks parseWeeks(String var0) {
      if (var0 == null) {
         return ZERO;
      } else {
         Period var1 = PARSER.parsePeriod(var0);
         return weeks(var1.getWeeks());
      }
   }

   private Weeks(int var1) {
      super(var1);
   }

   private Object readResolve() {
      return weeks(this.getValue());
   }

   public DurationFieldType getFieldType() {
      return DurationFieldType.weeks();
   }

   public PeriodType getPeriodType() {
      return PeriodType.weeks();
   }

   public Days toStandardDays() {
      return Days.days(FieldUtils.safeMultiply(this.getValue(), 7));
   }

   public Hours toStandardHours() {
      return Hours.hours(FieldUtils.safeMultiply(this.getValue(), 168));
   }

   public Minutes toStandardMinutes() {
      return Minutes.minutes(FieldUtils.safeMultiply(this.getValue(), 10080));
   }

   public Seconds toStandardSeconds() {
      return Seconds.seconds(FieldUtils.safeMultiply(this.getValue(), 604800));
   }

   public Duration toStandardDuration() {
      long var1 = (long)this.getValue();
      return new Duration(var1 * 604800000L);
   }

   public int getWeeks() {
      return this.getValue();
   }

   public Weeks plus(int var1) {
      return var1 == 0 ? this : weeks(FieldUtils.safeAdd(this.getValue(), var1));
   }

   public Weeks plus(Weeks var1) {
      return var1 == null ? this : this.plus(var1.getValue());
   }

   public Weeks minus(int var1) {
      return this.plus(FieldUtils.safeNegate(var1));
   }

   public Weeks minus(Weeks var1) {
      return var1 == null ? this : this.minus(var1.getValue());
   }

   public Weeks multipliedBy(int var1) {
      return weeks(FieldUtils.safeMultiply(this.getValue(), var1));
   }

   public Weeks dividedBy(int var1) {
      return var1 == 1 ? this : weeks(this.getValue() / var1);
   }

   public Weeks negated() {
      return weeks(FieldUtils.safeNegate(this.getValue()));
   }

   public boolean isGreaterThan(Weeks var1) {
      if (var1 == null) {
         return this.getValue() > 0;
      } else {
         return this.getValue() > var1.getValue();
      }
   }

   public boolean isLessThan(Weeks var1) {
      if (var1 == null) {
         return this.getValue() < 0;
      } else {
         return this.getValue() < var1.getValue();
      }
   }

   @ToString
   public String toString() {
      return "P" + String.valueOf(this.getValue()) + "W";
   }
}
