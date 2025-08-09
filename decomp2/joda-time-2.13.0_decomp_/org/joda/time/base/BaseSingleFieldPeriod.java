package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.MutablePeriod;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.ReadablePeriod;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.FieldUtils;

public abstract class BaseSingleFieldPeriod implements ReadablePeriod, Comparable, Serializable {
   private static final long serialVersionUID = 9386874258972L;
   private static final long START_1972 = 63072000000L;
   private volatile int iPeriod;

   protected static int between(ReadableInstant var0, ReadableInstant var1, DurationFieldType var2) {
      if (var0 != null && var1 != null) {
         Chronology var3 = DateTimeUtils.getInstantChronology(var0);
         int var4 = var2.getField(var3).getDifference(var1.getMillis(), var0.getMillis());
         return var4;
      } else {
         throw new IllegalArgumentException("ReadableInstant objects must not be null");
      }
   }

   protected static int between(ReadablePartial var0, ReadablePartial var1, ReadablePeriod var2) {
      if (var0 != null && var1 != null) {
         if (var0.size() != var1.size()) {
            throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
         } else {
            int var3 = 0;

            for(int var4 = var0.size(); var3 < var4; ++var3) {
               if (var0.getFieldType(var3) != var1.getFieldType(var3)) {
                  throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
               }
            }

            if (!DateTimeUtils.isContiguous(var0)) {
               throw new IllegalArgumentException("ReadablePartial objects must be contiguous");
            } else {
               Chronology var5 = DateTimeUtils.getChronology(var0.getChronology()).withUTC();
               int[] var6 = var5.get(var2, var5.set(var0, 63072000000L), var5.set(var1, 63072000000L));
               return var6[0];
            }
         }
      } else {
         throw new IllegalArgumentException("ReadablePartial objects must not be null");
      }
   }

   protected static int standardPeriodIn(ReadablePeriod var0, long var1) {
      if (var0 == null) {
         return 0;
      } else {
         ISOChronology var3 = ISOChronology.getInstanceUTC();
         long var4 = 0L;

         for(int var6 = 0; var6 < var0.size(); ++var6) {
            int var7 = var0.getValue(var6);
            if (var7 != 0) {
               DurationField var8 = var0.getFieldType(var6).getField(var3);
               if (!var8.isPrecise()) {
                  throw new IllegalArgumentException("Cannot convert period to duration as " + var8.getName() + " is not precise in the period " + var0);
               }

               var4 = FieldUtils.safeAdd(var4, FieldUtils.safeMultiply(var8.getUnitMillis(), var7));
            }
         }

         return FieldUtils.safeToInt(var4 / var1);
      }
   }

   protected BaseSingleFieldPeriod(int var1) {
      this.iPeriod = var1;
   }

   protected int getValue() {
      return this.iPeriod;
   }

   protected void setValue(int var1) {
      this.iPeriod = var1;
   }

   public abstract DurationFieldType getFieldType();

   public abstract PeriodType getPeriodType();

   public int size() {
      return 1;
   }

   public DurationFieldType getFieldType(int var1) {
      if (var1 != 0) {
         throw new IndexOutOfBoundsException(String.valueOf(var1));
      } else {
         return this.getFieldType();
      }
   }

   public int getValue(int var1) {
      if (var1 != 0) {
         throw new IndexOutOfBoundsException(String.valueOf(var1));
      } else {
         return this.getValue();
      }
   }

   public int get(DurationFieldType var1) {
      return var1 == this.getFieldType() ? this.getValue() : 0;
   }

   public boolean isSupported(DurationFieldType var1) {
      return var1 == this.getFieldType();
   }

   public Period toPeriod() {
      return Period.ZERO.withFields(this);
   }

   public MutablePeriod toMutablePeriod() {
      MutablePeriod var1 = new MutablePeriod();
      var1.add((ReadablePeriod)this);
      return var1;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof ReadablePeriod)) {
         return false;
      } else {
         ReadablePeriod var2 = (ReadablePeriod)var1;
         return var2.getPeriodType() == this.getPeriodType() && var2.getValue(0) == this.getValue();
      }
   }

   public int hashCode() {
      int var1 = 17;
      var1 = 27 * var1 + this.getValue();
      var1 = 27 * var1 + this.getFieldType().hashCode();
      return var1;
   }

   public int compareTo(BaseSingleFieldPeriod var1) {
      if (var1.getClass() != this.getClass()) {
         throw new ClassCastException(this.getClass() + " cannot be compared to " + var1.getClass());
      } else {
         int var2 = var1.getValue();
         int var3 = this.getValue();
         if (var3 > var2) {
            return 1;
         } else {
            return var3 < var2 ? -1 : 0;
         }
      }
   }
}
