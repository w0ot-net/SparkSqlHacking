package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

public abstract class PreciseDurationDateTimeField extends BaseDateTimeField {
   private static final long serialVersionUID = 5004523158306266035L;
   final long iUnitMillis;
   private final DurationField iUnitField;

   public PreciseDurationDateTimeField(DateTimeFieldType var1, DurationField var2) {
      super(var1);
      if (!var2.isPrecise()) {
         throw new IllegalArgumentException("Unit duration field must be precise");
      } else {
         this.iUnitMillis = var2.getUnitMillis();
         if (this.iUnitMillis < 1L) {
            throw new IllegalArgumentException("The unit milliseconds must be at least 1");
         } else {
            this.iUnitField = var2;
         }
      }
   }

   public boolean isLenient() {
      return false;
   }

   public long set(long var1, int var3) {
      FieldUtils.verifyValueBounds((DateTimeField)this, var3, this.getMinimumValue(), this.getMaximumValueForSet(var1, var3));
      return var1 + (long)(var3 - this.get(var1)) * this.iUnitMillis;
   }

   public long roundFloor(long var1) {
      if (var1 >= 0L) {
         return var1 - var1 % this.iUnitMillis;
      } else {
         ++var1;
         return var1 - var1 % this.iUnitMillis - this.iUnitMillis;
      }
   }

   public long roundCeiling(long var1) {
      if (var1 > 0L) {
         --var1;
         return var1 - var1 % this.iUnitMillis + this.iUnitMillis;
      } else {
         return var1 - var1 % this.iUnitMillis;
      }
   }

   public long remainder(long var1) {
      return var1 >= 0L ? var1 % this.iUnitMillis : (var1 + 1L) % this.iUnitMillis + this.iUnitMillis - 1L;
   }

   public DurationField getDurationField() {
      return this.iUnitField;
   }

   public int getMinimumValue() {
      return 0;
   }

   public final long getUnitMillis() {
      return this.iUnitMillis;
   }

   protected int getMaximumValueForSet(long var1, int var3) {
      return this.getMaximumValue(var1);
   }
}
