package org.joda.time.chrono;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;
import org.joda.time.field.DecoratedDateTimeField;
import org.joda.time.field.FieldUtils;

final class GJYearOfEraDateTimeField extends DecoratedDateTimeField {
   private static final long serialVersionUID = -5961050944769862059L;
   private final BasicChronology iChronology;

   GJYearOfEraDateTimeField(DateTimeField var1, BasicChronology var2) {
      super(var1, DateTimeFieldType.yearOfEra());
      this.iChronology = var2;
   }

   public DurationField getRangeDurationField() {
      return this.iChronology.eras();
   }

   public int get(long var1) {
      int var3 = this.getWrappedField().get(var1);
      if (var3 <= 0) {
         var3 = 1 - var3;
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
      FieldUtils.verifyValueBounds((DateTimeField)this, var3, 1, this.getMaximumValue());
      if (this.iChronology.getYear(var1) <= 0) {
         var3 = 1 - var3;
      }

      return super.set(var1, var3);
   }

   public int getMinimumValue() {
      return 1;
   }

   public int getMaximumValue() {
      return this.getWrappedField().getMaximumValue();
   }

   public long roundFloor(long var1) {
      return this.getWrappedField().roundFloor(var1);
   }

   public long roundCeiling(long var1) {
      return this.getWrappedField().roundCeiling(var1);
   }

   public long remainder(long var1) {
      return this.getWrappedField().remainder(var1);
   }

   private Object readResolve() {
      return this.iChronology.yearOfEra();
   }
}
