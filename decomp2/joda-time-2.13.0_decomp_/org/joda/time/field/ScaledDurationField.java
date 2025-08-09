package org.joda.time.field;

import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public class ScaledDurationField extends DecoratedDurationField {
   private static final long serialVersionUID = -3205227092378684157L;
   private final int iScalar;

   public ScaledDurationField(DurationField var1, DurationFieldType var2, int var3) {
      super(var1, var2);
      if (var3 != 0 && var3 != 1) {
         this.iScalar = var3;
      } else {
         throw new IllegalArgumentException("The scalar must not be 0 or 1");
      }
   }

   public int getValue(long var1) {
      return this.getWrappedField().getValue(var1) / this.iScalar;
   }

   public long getValueAsLong(long var1) {
      return this.getWrappedField().getValueAsLong(var1) / (long)this.iScalar;
   }

   public int getValue(long var1, long var3) {
      return this.getWrappedField().getValue(var1, var3) / this.iScalar;
   }

   public long getValueAsLong(long var1, long var3) {
      return this.getWrappedField().getValueAsLong(var1, var3) / (long)this.iScalar;
   }

   public long getMillis(int var1) {
      long var2 = (long)var1 * (long)this.iScalar;
      return this.getWrappedField().getMillis(var2);
   }

   public long getMillis(long var1) {
      long var3 = FieldUtils.safeMultiply(var1, this.iScalar);
      return this.getWrappedField().getMillis(var3);
   }

   public long getMillis(int var1, long var2) {
      long var4 = (long)var1 * (long)this.iScalar;
      return this.getWrappedField().getMillis(var4, var2);
   }

   public long getMillis(long var1, long var3) {
      long var5 = FieldUtils.safeMultiply(var1, this.iScalar);
      return this.getWrappedField().getMillis(var5, var3);
   }

   public long add(long var1, int var3) {
      long var4 = (long)var3 * (long)this.iScalar;
      return this.getWrappedField().add(var1, var4);
   }

   public long add(long var1, long var3) {
      long var5 = FieldUtils.safeMultiply(var3, this.iScalar);
      return this.getWrappedField().add(var1, var5);
   }

   public int getDifference(long var1, long var3) {
      return this.getWrappedField().getDifference(var1, var3) / this.iScalar;
   }

   public long getDifferenceAsLong(long var1, long var3) {
      return this.getWrappedField().getDifferenceAsLong(var1, var3) / (long)this.iScalar;
   }

   public long getUnitMillis() {
      return this.getWrappedField().getUnitMillis() * (long)this.iScalar;
   }

   public int getScalar() {
      return this.iScalar;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof ScaledDurationField)) {
         return false;
      } else {
         ScaledDurationField var2 = (ScaledDurationField)var1;
         return this.getWrappedField().equals(var2.getWrappedField()) && this.getType() == var2.getType() && this.iScalar == var2.iScalar;
      }
   }

   public int hashCode() {
      long var1 = (long)this.iScalar;
      int var3 = (int)(var1 ^ var1 >>> 32);
      var3 += this.getType().hashCode();
      var3 += this.getWrappedField().hashCode();
      return var3;
   }
}
