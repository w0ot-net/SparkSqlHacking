package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

public class DividedDateTimeField extends DecoratedDateTimeField {
   private static final long serialVersionUID = 8318475124230605365L;
   final int iDivisor;
   final DurationField iDurationField;
   final DurationField iRangeDurationField;
   private final int iMin;
   private final int iMax;

   public DividedDateTimeField(DateTimeField var1, DateTimeFieldType var2, int var3) {
      this(var1, var1.getRangeDurationField(), var2, var3);
   }

   public DividedDateTimeField(DateTimeField var1, DurationField var2, DateTimeFieldType var3, int var4) {
      super(var1, var3);
      if (var4 < 2) {
         throw new IllegalArgumentException("The divisor must be at least 2");
      } else {
         DurationField var5 = var1.getDurationField();
         if (var5 == null) {
            this.iDurationField = null;
         } else {
            this.iDurationField = new ScaledDurationField(var5, var3.getDurationType(), var4);
         }

         this.iRangeDurationField = var2;
         this.iDivisor = var4;
         int var6 = var1.getMinimumValue();
         int var7 = var6 >= 0 ? var6 / var4 : (var6 + 1) / var4 - 1;
         int var8 = var1.getMaximumValue();
         int var9 = var8 >= 0 ? var8 / var4 : (var8 + 1) / var4 - 1;
         this.iMin = var7;
         this.iMax = var9;
      }
   }

   public DividedDateTimeField(RemainderDateTimeField var1, DateTimeFieldType var2) {
      this(var1, (DurationField)null, var2);
   }

   public DividedDateTimeField(RemainderDateTimeField var1, DurationField var2, DateTimeFieldType var3) {
      super(var1.getWrappedField(), var3);
      int var4 = this.iDivisor = var1.iDivisor;
      this.iDurationField = var1.iRangeField;
      this.iRangeDurationField = var2;
      DateTimeField var5 = this.getWrappedField();
      int var6 = var5.getMinimumValue();
      int var7 = var6 >= 0 ? var6 / var4 : (var6 + 1) / var4 - 1;
      int var8 = var5.getMaximumValue();
      int var9 = var8 >= 0 ? var8 / var4 : (var8 + 1) / var4 - 1;
      this.iMin = var7;
      this.iMax = var9;
   }

   public DurationField getRangeDurationField() {
      return this.iRangeDurationField != null ? this.iRangeDurationField : super.getRangeDurationField();
   }

   public int get(long var1) {
      int var3 = this.getWrappedField().get(var1);
      return var3 >= 0 ? var3 / this.iDivisor : (var3 + 1) / this.iDivisor - 1;
   }

   public long add(long var1, int var3) {
      return this.getWrappedField().add(var1, var3 * this.iDivisor);
   }

   public long add(long var1, long var3) {
      return this.getWrappedField().add(var1, var3 * (long)this.iDivisor);
   }

   public long addWrapField(long var1, int var3) {
      return this.set(var1, FieldUtils.getWrappedValue(this.get(var1), var3, this.iMin, this.iMax));
   }

   public int getDifference(long var1, long var3) {
      return this.getWrappedField().getDifference(var1, var3) / this.iDivisor;
   }

   public long getDifferenceAsLong(long var1, long var3) {
      return this.getWrappedField().getDifferenceAsLong(var1, var3) / (long)this.iDivisor;
   }

   public long set(long var1, int var3) {
      FieldUtils.verifyValueBounds((DateTimeField)this, var3, this.iMin, this.iMax);
      int var4 = this.getRemainder(this.getWrappedField().get(var1));
      return this.getWrappedField().set(var1, var3 * this.iDivisor + var4);
   }

   public DurationField getDurationField() {
      return this.iDurationField;
   }

   public int getMinimumValue() {
      return this.iMin;
   }

   public int getMaximumValue() {
      return this.iMax;
   }

   public long roundFloor(long var1) {
      DateTimeField var3 = this.getWrappedField();
      return var3.roundFloor(var3.set(var1, this.get(var1) * this.iDivisor));
   }

   public long remainder(long var1) {
      return this.set(var1, this.get(this.getWrappedField().remainder(var1)));
   }

   public int getDivisor() {
      return this.iDivisor;
   }

   private int getRemainder(int var1) {
      return var1 >= 0 ? var1 % this.iDivisor : this.iDivisor - 1 + (var1 + 1) % this.iDivisor;
   }
}
