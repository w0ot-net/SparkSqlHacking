package org.joda.time.chrono;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;
import org.joda.time.field.PreciseDurationDateTimeField;

final class BasicDayOfMonthDateTimeField extends PreciseDurationDateTimeField {
   private static final long serialVersionUID = -4677223814028011723L;
   private final BasicChronology iChronology;

   BasicDayOfMonthDateTimeField(BasicChronology var1, DurationField var2) {
      super(DateTimeFieldType.dayOfMonth(), var2);
      this.iChronology = var1;
   }

   public int get(long var1) {
      return this.iChronology.getDayOfMonth(var1);
   }

   public DurationField getRangeDurationField() {
      return this.iChronology.months();
   }

   public int getMinimumValue() {
      return 1;
   }

   public int getMaximumValue() {
      return this.iChronology.getDaysInMonthMax();
   }

   public int getMaximumValue(long var1) {
      return this.iChronology.getDaysInMonthMax(var1);
   }

   public int getMaximumValue(ReadablePartial var1) {
      if (var1.isSupported(DateTimeFieldType.monthOfYear())) {
         int var2 = var1.get(DateTimeFieldType.monthOfYear());
         if (var1.isSupported(DateTimeFieldType.year())) {
            int var3 = var1.get(DateTimeFieldType.year());
            return this.iChronology.getDaysInYearMonth(var3, var2);
         } else {
            return this.iChronology.getDaysInMonthMax(var2);
         }
      } else {
         return this.getMaximumValue();
      }
   }

   public int getMaximumValue(ReadablePartial var1, int[] var2) {
      int var3 = var1.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         if (var1.getFieldType(var4) == DateTimeFieldType.monthOfYear()) {
            int var5 = var2[var4];

            for(int var6 = 0; var6 < var3; ++var6) {
               if (var1.getFieldType(var6) == DateTimeFieldType.year()) {
                  int var7 = var2[var6];
                  return this.iChronology.getDaysInYearMonth(var7, var5);
               }
            }

            return this.iChronology.getDaysInMonthMax(var5);
         }
      }

      return this.getMaximumValue();
   }

   protected int getMaximumValueForSet(long var1, int var3) {
      return this.iChronology.getDaysInMonthMaxForSet(var1, var3);
   }

   public boolean isLeap(long var1) {
      return this.iChronology.isLeapDay(var1);
   }

   private Object readResolve() {
      return this.iChronology.dayOfMonth();
   }
}
