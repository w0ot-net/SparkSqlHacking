package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

public class PreciseDateTimeField extends PreciseDurationDateTimeField {
   private static final long serialVersionUID = -5586801265774496376L;
   private final int iRange;
   private final DurationField iRangeField;

   public PreciseDateTimeField(DateTimeFieldType var1, DurationField var2, DurationField var3) {
      super(var1, var2);
      if (!var3.isPrecise()) {
         throw new IllegalArgumentException("Range duration field must be precise");
      } else {
         long var4 = var3.getUnitMillis();
         this.iRange = (int)(var4 / this.getUnitMillis());
         if (this.iRange < 2) {
            throw new IllegalArgumentException("The effective range must be at least 2");
         } else {
            this.iRangeField = var3;
         }
      }
   }

   public int get(long var1) {
      return var1 >= 0L ? (int)(var1 / this.getUnitMillis() % (long)this.iRange) : this.iRange - 1 + (int)((var1 + 1L) / this.getUnitMillis() % (long)this.iRange);
   }

   public long addWrapField(long var1, int var3) {
      int var4 = this.get(var1);
      int var5 = FieldUtils.getWrappedValue(var4, var3, this.getMinimumValue(), this.getMaximumValue());
      return var1 + (long)(var5 - var4) * this.getUnitMillis();
   }

   public long set(long var1, int var3) {
      FieldUtils.verifyValueBounds((DateTimeField)this, var3, this.getMinimumValue(), this.getMaximumValue());
      return var1 + (long)(var3 - this.get(var1)) * this.iUnitMillis;
   }

   public DurationField getRangeDurationField() {
      return this.iRangeField;
   }

   public int getMaximumValue() {
      return this.iRange - 1;
   }

   public int getRange() {
      return this.iRange;
   }
}
