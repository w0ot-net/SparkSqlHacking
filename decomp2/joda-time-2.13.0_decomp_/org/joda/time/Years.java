package org.joda.time;

import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Years extends BaseSingleFieldPeriod {
   public static final Years ZERO = new Years(0);
   public static final Years ONE = new Years(1);
   public static final Years TWO = new Years(2);
   public static final Years THREE = new Years(3);
   public static final Years MAX_VALUE = new Years(Integer.MAX_VALUE);
   public static final Years MIN_VALUE = new Years(Integer.MIN_VALUE);
   private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.years());
   private static final long serialVersionUID = 87525275727380868L;

   public static Years years(int var0) {
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
            return new Years(var0);
      }
   }

   public static Years yearsBetween(ReadableInstant var0, ReadableInstant var1) {
      int var2 = BaseSingleFieldPeriod.between(var0, var1, DurationFieldType.years());
      return years(var2);
   }

   public static Years yearsBetween(ReadablePartial var0, ReadablePartial var1) {
      if (var0 instanceof LocalDate && var1 instanceof LocalDate) {
         Chronology var4 = DateTimeUtils.getChronology(var0.getChronology());
         int var3 = var4.years().getDifference(((LocalDate)var1).getLocalMillis(), ((LocalDate)var0).getLocalMillis());
         return years(var3);
      } else {
         int var2 = BaseSingleFieldPeriod.between((ReadablePartial)var0, (ReadablePartial)var1, (ReadablePeriod)ZERO);
         return years(var2);
      }
   }

   public static Years yearsIn(ReadableInterval var0) {
      if (var0 == null) {
         return ZERO;
      } else {
         int var1 = BaseSingleFieldPeriod.between((ReadableInstant)var0.getStart(), (ReadableInstant)var0.getEnd(), (DurationFieldType)DurationFieldType.years());
         return years(var1);
      }
   }

   @FromString
   public static Years parseYears(String var0) {
      if (var0 == null) {
         return ZERO;
      } else {
         Period var1 = PARSER.parsePeriod(var0);
         return years(var1.getYears());
      }
   }

   private Years(int var1) {
      super(var1);
   }

   private Object readResolve() {
      return years(this.getValue());
   }

   public DurationFieldType getFieldType() {
      return DurationFieldType.years();
   }

   public PeriodType getPeriodType() {
      return PeriodType.years();
   }

   public int getYears() {
      return this.getValue();
   }

   public Years plus(int var1) {
      return var1 == 0 ? this : years(FieldUtils.safeAdd(this.getValue(), var1));
   }

   public Years plus(Years var1) {
      return var1 == null ? this : this.plus(var1.getValue());
   }

   public Years minus(int var1) {
      return this.plus(FieldUtils.safeNegate(var1));
   }

   public Years minus(Years var1) {
      return var1 == null ? this : this.minus(var1.getValue());
   }

   public Years multipliedBy(int var1) {
      return years(FieldUtils.safeMultiply(this.getValue(), var1));
   }

   public Years dividedBy(int var1) {
      return var1 == 1 ? this : years(this.getValue() / var1);
   }

   public Years negated() {
      return years(FieldUtils.safeNegate(this.getValue()));
   }

   public boolean isGreaterThan(Years var1) {
      if (var1 == null) {
         return this.getValue() > 0;
      } else {
         return this.getValue() > var1.getValue();
      }
   }

   public boolean isLessThan(Years var1) {
      if (var1 == null) {
         return this.getValue() < 0;
      } else {
         return this.getValue() < var1.getValue();
      }
   }

   @ToString
   public String toString() {
      return "P" + String.valueOf(this.getValue()) + "Y";
   }
}
