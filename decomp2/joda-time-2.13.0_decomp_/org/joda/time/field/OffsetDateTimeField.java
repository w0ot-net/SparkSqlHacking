package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

public class OffsetDateTimeField extends DecoratedDateTimeField {
   private static final long serialVersionUID = 3145790132623583142L;
   private final int iOffset;
   private final int iMin;
   private final int iMax;

   public OffsetDateTimeField(DateTimeField var1, int var2) {
      this(var1, var1 == null ? null : var1.getType(), var2, Integer.MIN_VALUE, Integer.MAX_VALUE);
   }

   public OffsetDateTimeField(DateTimeField var1, DateTimeFieldType var2, int var3) {
      this(var1, var2, var3, Integer.MIN_VALUE, Integer.MAX_VALUE);
   }

   public OffsetDateTimeField(DateTimeField var1, DateTimeFieldType var2, int var3, int var4, int var5) {
      super(var1, var2);
      if (var3 == 0) {
         throw new IllegalArgumentException("The offset cannot be zero");
      } else {
         this.iOffset = var3;
         if (var4 < var1.getMinimumValue() + var3) {
            this.iMin = var1.getMinimumValue() + var3;
         } else {
            this.iMin = var4;
         }

         if (var5 > var1.getMaximumValue() + var3) {
            this.iMax = var1.getMaximumValue() + var3;
         } else {
            this.iMax = var5;
         }

      }
   }

   public int get(long var1) {
      return super.get(var1) + this.iOffset;
   }

   public long add(long var1, int var3) {
      var1 = super.add(var1, var3);
      FieldUtils.verifyValueBounds((DateTimeField)this, this.get(var1), this.iMin, this.iMax);
      return var1;
   }

   public long add(long var1, long var3) {
      var1 = super.add(var1, var3);
      FieldUtils.verifyValueBounds((DateTimeField)this, this.get(var1), this.iMin, this.iMax);
      return var1;
   }

   public long addWrapField(long var1, int var3) {
      return this.set(var1, FieldUtils.getWrappedValue(this.get(var1), var3, this.iMin, this.iMax));
   }

   public long set(long var1, int var3) {
      FieldUtils.verifyValueBounds((DateTimeField)this, var3, this.iMin, this.iMax);
      return super.set(var1, var3 - this.iOffset);
   }

   public boolean isLeap(long var1) {
      return this.getWrappedField().isLeap(var1);
   }

   public int getLeapAmount(long var1) {
      return this.getWrappedField().getLeapAmount(var1);
   }

   public DurationField getLeapDurationField() {
      return this.getWrappedField().getLeapDurationField();
   }

   public int getMinimumValue() {
      return this.iMin;
   }

   public int getMaximumValue() {
      return this.iMax;
   }

   public long roundFloor(long var1) {
      return this.getWrappedField().roundFloor(var1);
   }

   public long roundCeiling(long var1) {
      return this.getWrappedField().roundCeiling(var1);
   }

   public long roundHalfFloor(long var1) {
      return this.getWrappedField().roundHalfFloor(var1);
   }

   public long roundHalfCeiling(long var1) {
      return this.getWrappedField().roundHalfCeiling(var1);
   }

   public long roundHalfEven(long var1) {
      return this.getWrappedField().roundHalfEven(var1);
   }

   public long remainder(long var1) {
      return this.getWrappedField().remainder(var1);
   }

   public int getOffset() {
      return this.iOffset;
   }
}
