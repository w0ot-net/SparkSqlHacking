package org.joda.time.chrono;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;
import org.joda.time.field.PreciseDurationDateTimeField;

final class BasicWeekOfWeekyearDateTimeField extends PreciseDurationDateTimeField {
   private static final long serialVersionUID = -1587436826395135328L;
   private final BasicChronology iChronology;

   BasicWeekOfWeekyearDateTimeField(BasicChronology var1, DurationField var2) {
      super(DateTimeFieldType.weekOfWeekyear(), var2);
      this.iChronology = var1;
   }

   public int get(long var1) {
      return this.iChronology.getWeekOfWeekyear(var1);
   }

   public DurationField getRangeDurationField() {
      return this.iChronology.weekyears();
   }

   public long roundFloor(long var1) {
      return super.roundFloor(var1 + 259200000L) - 259200000L;
   }

   public long roundCeiling(long var1) {
      return super.roundCeiling(var1 + 259200000L) - 259200000L;
   }

   public long remainder(long var1) {
      return super.remainder(var1 + 259200000L);
   }

   public int getMinimumValue() {
      return 1;
   }

   public int getMaximumValue() {
      return 53;
   }

   public int getMaximumValue(long var1) {
      int var3 = this.iChronology.getWeekyear(var1);
      return this.iChronology.getWeeksInYear(var3);
   }

   public int getMaximumValue(ReadablePartial var1) {
      if (var1.isSupported(DateTimeFieldType.weekyear())) {
         int var2 = var1.get(DateTimeFieldType.weekyear());
         return this.iChronology.getWeeksInYear(var2);
      } else {
         return 53;
      }
   }

   public int getMaximumValue(ReadablePartial var1, int[] var2) {
      int var3 = var1.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         if (var1.getFieldType(var4) == DateTimeFieldType.weekyear()) {
            int var5 = var2[var4];
            return this.iChronology.getWeeksInYear(var5);
         }
      }

      return 53;
   }

   protected int getMaximumValueForSet(long var1, int var3) {
      return var3 > 52 ? this.getMaximumValue(var1) : 52;
   }

   private Object readResolve() {
      return this.iChronology.weekOfWeekyear();
   }
}
