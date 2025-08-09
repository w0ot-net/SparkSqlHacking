package org.joda.time.chrono;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.ImpreciseDateTimeField;

class BasicMonthOfYearDateTimeField extends ImpreciseDateTimeField {
   private static final long serialVersionUID = -8258715387168736L;
   private static final int MIN = 1;
   private final BasicChronology iChronology;
   private final int iMax;
   private final int iLeapMonth;

   BasicMonthOfYearDateTimeField(BasicChronology var1, int var2) {
      super(DateTimeFieldType.monthOfYear(), var1.getAverageMillisPerMonth());
      this.iChronology = var1;
      this.iMax = this.iChronology.getMaxMonth();
      this.iLeapMonth = var2;
   }

   public boolean isLenient() {
      return false;
   }

   public int get(long var1) {
      return this.iChronology.getMonthOfYear(var1);
   }

   public long add(long var1, int var3) {
      if (var3 == 0) {
         return var1;
      } else {
         long var4 = (long)this.iChronology.getMillisOfDay(var1);
         int var6 = this.iChronology.getYear(var1);
         int var7 = this.iChronology.getMonthOfYear(var1, var6);
         int var8 = var6;
         int var9 = var7 - 1 + var3;
         if (var7 > 0 && var9 < 0) {
            if (Math.signum((float)(var3 + this.iMax)) == Math.signum((float)var3)) {
               var8 = var6 - 1;
               var3 += this.iMax;
            } else {
               var8 = var6 + 1;
               var3 -= this.iMax;
            }

            var9 = var7 - 1 + var3;
         }

         if (var9 >= 0) {
            var8 += var9 / this.iMax;
            var9 = var9 % this.iMax + 1;
         } else {
            var8 = var8 + var9 / this.iMax - 1;
            var9 = Math.abs(var9);
            int var10 = var9 % this.iMax;
            if (var10 == 0) {
               var10 = this.iMax;
            }

            var9 = this.iMax - var10 + 1;
            if (var9 == 1) {
               ++var8;
            }
         }

         int var18 = this.iChronology.getDayOfMonth(var1, var6, var7);
         int var11 = this.iChronology.getDaysInYearMonth(var8, var9);
         if (var18 > var11) {
            var18 = var11;
         }

         long var12 = this.iChronology.getYearMonthDayMillis(var8, var9, var18);
         return var12 + var4;
      }
   }

   public long add(long var1, long var3) {
      int var5 = (int)var3;
      if ((long)var5 == var3) {
         return this.add(var1, var5);
      } else {
         long var6 = (long)this.iChronology.getMillisOfDay(var1);
         int var8 = this.iChronology.getYear(var1);
         int var9 = this.iChronology.getMonthOfYear(var1, var8);
         long var12 = (long)(var9 - 1) + var3;
         long var10;
         if (var12 >= 0L) {
            var10 = (long)var8 + var12 / (long)this.iMax;
            var12 = var12 % (long)this.iMax + 1L;
         } else {
            var10 = (long)var8 + var12 / (long)this.iMax - 1L;
            var12 = Math.abs(var12);
            int var14 = (int)(var12 % (long)this.iMax);
            if (var14 == 0) {
               var14 = this.iMax;
            }

            var12 = (long)(this.iMax - var14 + 1);
            if (var12 == 1L) {
               ++var10;
            }
         }

         if (var10 >= (long)this.iChronology.getMinYear() && var10 <= (long)this.iChronology.getMaxYear()) {
            int var22 = (int)var10;
            int var15 = (int)var12;
            int var16 = this.iChronology.getDayOfMonth(var1, var8, var9);
            int var17 = this.iChronology.getDaysInYearMonth(var22, var15);
            if (var16 > var17) {
               var16 = var17;
            }

            long var18 = this.iChronology.getYearMonthDayMillis(var22, var15, var16);
            return var18 + var6;
         } else {
            throw new IllegalArgumentException("Magnitude of add amount is too large: " + var3);
         }
      }
   }

   public int[] add(ReadablePartial var1, int var2, int[] var3, int var4) {
      if (var4 == 0) {
         return var3;
      } else if (var1.size() > 0 && var1.getFieldType(0).equals(DateTimeFieldType.monthOfYear()) && var2 == 0) {
         int var10 = var3[0] - 1;
         int var6 = (var10 + var4 % 12 + 12) % 12 + 1;
         return this.set(var1, 0, var3, var6);
      } else if (!DateTimeUtils.isContiguous(var1)) {
         return super.add(var1, var2, var3, var4);
      } else {
         long var5 = 0L;
         int var7 = 0;

         for(int var8 = var1.size(); var7 < var8; ++var7) {
            var5 = var1.getFieldType(var7).getField(this.iChronology).set(var5, var3[var7]);
         }

         var5 = this.add(var5, var4);
         return this.iChronology.get(var1, var5);
      }
   }

   public long addWrapField(long var1, int var3) {
      return this.set(var1, FieldUtils.getWrappedValue(this.get(var1), var3, 1, this.iMax));
   }

   public long getDifferenceAsLong(long var1, long var3) {
      if (var1 < var3) {
         return (long)(-this.getDifference(var3, var1));
      } else {
         int var5 = this.iChronology.getYear(var1);
         int var6 = this.iChronology.getMonthOfYear(var1, var5);
         int var7 = this.iChronology.getYear(var3);
         int var8 = this.iChronology.getMonthOfYear(var3, var7);
         long var9 = (long)(var5 - var7) * (long)this.iMax + (long)var6 - (long)var8;
         int var11 = this.iChronology.getDayOfMonth(var1, var5, var6);
         if (var11 == this.iChronology.getDaysInYearMonth(var5, var6)) {
            int var12 = this.iChronology.getDayOfMonth(var3, var7, var8);
            if (var12 > var11) {
               var3 = this.iChronology.dayOfMonth().set(var3, var11);
            }
         }

         long var16 = var1 - this.iChronology.getYearMonthMillis(var5, var6);
         long var14 = var3 - this.iChronology.getYearMonthMillis(var7, var8);
         if (var16 < var14) {
            --var9;
         }

         return var9;
      }
   }

   public long set(long var1, int var3) {
      FieldUtils.verifyValueBounds((DateTimeField)this, var3, 1, this.iMax);
      int var4 = this.iChronology.getYear(var1);
      int var5 = this.iChronology.getDayOfMonth(var1, var4);
      int var6 = this.iChronology.getDaysInYearMonth(var4, var3);
      if (var5 > var6) {
         var5 = var6;
      }

      return this.iChronology.getYearMonthDayMillis(var4, var3, var5) + (long)this.iChronology.getMillisOfDay(var1);
   }

   public DurationField getRangeDurationField() {
      return this.iChronology.years();
   }

   public boolean isLeap(long var1) {
      int var3 = this.iChronology.getYear(var1);
      if (this.iChronology.isLeapYear(var3)) {
         return this.iChronology.getMonthOfYear(var1, var3) == this.iLeapMonth;
      } else {
         return false;
      }
   }

   public int getLeapAmount(long var1) {
      return this.isLeap(var1) ? 1 : 0;
   }

   public DurationField getLeapDurationField() {
      return this.iChronology.days();
   }

   public int getMinimumValue() {
      return 1;
   }

   public int getMaximumValue() {
      return this.iMax;
   }

   public long roundFloor(long var1) {
      int var3 = this.iChronology.getYear(var1);
      int var4 = this.iChronology.getMonthOfYear(var1, var3);
      return this.iChronology.getYearMonthMillis(var3, var4);
   }

   public long remainder(long var1) {
      return var1 - this.roundFloor(var1);
   }

   private Object readResolve() {
      return this.iChronology.monthOfYear();
   }
}
