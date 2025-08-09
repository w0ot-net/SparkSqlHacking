package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;

public final class ZeroIsMaxDateTimeField extends DecoratedDateTimeField {
   private static final long serialVersionUID = 961749798233026866L;

   public ZeroIsMaxDateTimeField(DateTimeField var1, DateTimeFieldType var2) {
      super(var1, var2);
      if (var1.getMinimumValue() != 0) {
         throw new IllegalArgumentException("Wrapped field's minumum value must be zero");
      }
   }

   public int get(long var1) {
      int var3 = this.getWrappedField().get(var1);
      if (var3 == 0) {
         var3 = this.getMaximumValue();
      }

      return var3;
   }

   public long add(long var1, int var3) {
      return this.getWrappedField().add(var1, var3);
   }

   public long add(long var1, long var3) {
      return this.getWrappedField().add(var1, var3);
   }

   public long addWrapField(long var1, int var3) {
      return this.getWrappedField().addWrapField(var1, var3);
   }

   public int[] addWrapField(ReadablePartial var1, int var2, int[] var3, int var4) {
      return this.getWrappedField().addWrapField(var1, var2, var3, var4);
   }

   public int getDifference(long var1, long var3) {
      return this.getWrappedField().getDifference(var1, var3);
   }

   public long getDifferenceAsLong(long var1, long var3) {
      return this.getWrappedField().getDifferenceAsLong(var1, var3);
   }

   public long set(long var1, int var3) {
      int var4 = this.getMaximumValue();
      FieldUtils.verifyValueBounds((DateTimeField)this, var3, 1, var4);
      if (var3 == var4) {
         var3 = 0;
      }

      return this.getWrappedField().set(var1, var3);
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
      return 1;
   }

   public int getMinimumValue(long var1) {
      return 1;
   }

   public int getMinimumValue(ReadablePartial var1) {
      return 1;
   }

   public int getMinimumValue(ReadablePartial var1, int[] var2) {
      return 1;
   }

   public int getMaximumValue() {
      return this.getWrappedField().getMaximumValue() + 1;
   }

   public int getMaximumValue(long var1) {
      return this.getWrappedField().getMaximumValue(var1) + 1;
   }

   public int getMaximumValue(ReadablePartial var1) {
      return this.getWrappedField().getMaximumValue(var1) + 1;
   }

   public int getMaximumValue(ReadablePartial var1, int[] var2) {
      return this.getWrappedField().getMaximumValue(var1, var2) + 1;
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
}
