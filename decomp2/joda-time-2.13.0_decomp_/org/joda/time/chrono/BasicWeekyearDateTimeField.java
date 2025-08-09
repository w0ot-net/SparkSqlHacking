package org.joda.time.chrono;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.ImpreciseDateTimeField;

final class BasicWeekyearDateTimeField extends ImpreciseDateTimeField {
   private static final long serialVersionUID = 6215066916806820644L;
   private static final long WEEK_53 = 31449600000L;
   private final BasicChronology iChronology;

   BasicWeekyearDateTimeField(BasicChronology var1) {
      super(DateTimeFieldType.weekyear(), var1.getAverageMillisPerYear());
      this.iChronology = var1;
   }

   public boolean isLenient() {
      return false;
   }

   public int get(long var1) {
      return this.iChronology.getWeekyear(var1);
   }

   public long add(long var1, int var3) {
      return var3 == 0 ? var1 : this.set(var1, this.get(var1) + var3);
   }

   public long add(long var1, long var3) {
      return this.add(var1, FieldUtils.safeToInt(var3));
   }

   public long addWrapField(long var1, int var3) {
      return this.add(var1, var3);
   }

   public long getDifferenceAsLong(long var1, long var3) {
      if (var1 < var3) {
         return (long)(-this.getDifference(var3, var1));
      } else {
         int var5 = this.get(var1);
         int var6 = this.get(var3);
         long var7 = this.remainder(var1);
         long var9 = this.remainder(var3);
         if (var9 >= 31449600000L && this.iChronology.getWeeksInYear(var5) <= 52) {
            var9 -= 604800000L;
         }

         int var11 = var5 - var6;
         if (var7 < var9) {
            --var11;
         }

         return (long)var11;
      }
   }

   public long set(long var1, int var3) {
      FieldUtils.verifyValueBounds((DateTimeField)this, Math.abs(var3), this.iChronology.getMinYear(), this.iChronology.getMaxYear());
      int var4 = this.get(var1);
      if (var4 == var3) {
         return var1;
      } else {
         int var5 = this.iChronology.getDayOfWeek(var1);
         int var6 = this.iChronology.getWeeksInYear(var4);
         int var7 = this.iChronology.getWeeksInYear(var3);
         int var8 = var7 < var6 ? var7 : var6;
         int var9 = this.iChronology.getWeekOfWeekyear(var1);
         if (var9 > var8) {
            var9 = var8;
         }

         long var10 = this.iChronology.setYear(var1, var3);
         int var12 = this.get(var10);
         if (var12 < var3) {
            var10 += 604800000L;
         } else if (var12 > var3) {
            var10 -= 604800000L;
         }

         int var13 = this.iChronology.getWeekOfWeekyear(var10);
         var10 += (long)(var9 - var13) * 604800000L;
         var10 = this.iChronology.dayOfWeek().set(var10, var5);
         return var10;
      }
   }

   public DurationField getRangeDurationField() {
      return null;
   }

   public boolean isLeap(long var1) {
      return this.iChronology.getWeeksInYear(this.iChronology.getWeekyear(var1)) > 52;
   }

   public int getLeapAmount(long var1) {
      return this.iChronology.getWeeksInYear(this.iChronology.getWeekyear(var1)) - 52;
   }

   public DurationField getLeapDurationField() {
      return this.iChronology.weeks();
   }

   public int getMinimumValue() {
      return this.iChronology.getMinYear();
   }

   public int getMaximumValue() {
      return this.iChronology.getMaxYear();
   }

   public long roundFloor(long var1) {
      var1 = this.iChronology.weekOfWeekyear().roundFloor(var1);
      int var3 = this.iChronology.getWeekOfWeekyear(var1);
      if (var3 > 1) {
         var1 -= 604800000L * (long)(var3 - 1);
      }

      return var1;
   }

   public long remainder(long var1) {
      return var1 - this.roundFloor(var1);
   }

   private Object readResolve() {
      return this.iChronology.weekyear();
   }
}
