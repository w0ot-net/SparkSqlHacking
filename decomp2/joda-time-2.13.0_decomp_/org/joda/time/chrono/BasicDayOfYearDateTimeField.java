package org.joda.time.chrono;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;
import org.joda.time.field.PreciseDurationDateTimeField;

final class BasicDayOfYearDateTimeField extends PreciseDurationDateTimeField {
   private static final long serialVersionUID = -6821236822336841037L;
   private final BasicChronology iChronology;

   BasicDayOfYearDateTimeField(BasicChronology var1, DurationField var2) {
      super(DateTimeFieldType.dayOfYear(), var2);
      this.iChronology = var1;
   }

   public int get(long var1) {
      return this.iChronology.getDayOfYear(var1);
   }

   public DurationField getRangeDurationField() {
      return this.iChronology.years();
   }

   public int getMinimumValue() {
      return 1;
   }

   public int getMaximumValue() {
      return this.iChronology.getDaysInYearMax();
   }

   public int getMaximumValue(long var1) {
      int var3 = this.iChronology.getYear(var1);
      return this.iChronology.getDaysInYear(var3);
   }

   public int getMaximumValue(ReadablePartial var1) {
      if (var1.isSupported(DateTimeFieldType.year())) {
         int var2 = var1.get(DateTimeFieldType.year());
         return this.iChronology.getDaysInYear(var2);
      } else {
         return this.iChronology.getDaysInYearMax();
      }
   }

   public int getMaximumValue(ReadablePartial var1, int[] var2) {
      int var3 = var1.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         if (var1.getFieldType(var4) == DateTimeFieldType.year()) {
            int var5 = var2[var4];
            return this.iChronology.getDaysInYear(var5);
         }
      }

      return this.iChronology.getDaysInYearMax();
   }

   protected int getMaximumValueForSet(long var1, int var3) {
      int var4 = this.iChronology.getDaysInYearMax() - 1;
      return var3 <= var4 && var3 >= 1 ? var4 : this.getMaximumValue(var1);
   }

   public boolean isLeap(long var1) {
      return this.iChronology.isLeapDay(var1);
   }

   private Object readResolve() {
      return this.iChronology.dayOfYear();
   }
}
