package org.joda.time.field;

import org.joda.time.DurationFieldType;

public class PreciseDurationField extends BaseDurationField {
   private static final long serialVersionUID = -8346152187724495365L;
   private final long iUnitMillis;

   public PreciseDurationField(DurationFieldType var1, long var2) {
      super(var1);
      this.iUnitMillis = var2;
   }

   public final boolean isPrecise() {
      return true;
   }

   public final long getUnitMillis() {
      return this.iUnitMillis;
   }

   public long getValueAsLong(long var1, long var3) {
      return var1 / this.iUnitMillis;
   }

   public long getMillis(int var1, long var2) {
      return (long)var1 * this.iUnitMillis;
   }

   public long getMillis(long var1, long var3) {
      return FieldUtils.safeMultiply(var1, this.iUnitMillis);
   }

   public long add(long var1, int var3) {
      long var4 = (long)var3 * this.iUnitMillis;
      return FieldUtils.safeAdd(var1, var4);
   }

   public long add(long var1, long var3) {
      long var5 = FieldUtils.safeMultiply(var3, this.iUnitMillis);
      return FieldUtils.safeAdd(var1, var5);
   }

   public long getDifferenceAsLong(long var1, long var3) {
      long var5 = FieldUtils.safeSubtract(var1, var3);
      return var5 / this.iUnitMillis;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof PreciseDurationField)) {
         return false;
      } else {
         PreciseDurationField var2 = (PreciseDurationField)var1;
         return this.getType() == var2.getType() && this.iUnitMillis == var2.iUnitMillis;
      }
   }

   public int hashCode() {
      long var1 = this.iUnitMillis;
      int var3 = (int)(var1 ^ var1 >>> 32);
      var3 += this.getType().hashCode();
      return var3;
   }
}
