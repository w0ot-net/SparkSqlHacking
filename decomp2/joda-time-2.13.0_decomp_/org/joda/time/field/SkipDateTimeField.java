package org.joda.time.field;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.IllegalFieldValueException;

public final class SkipDateTimeField extends DelegatedDateTimeField {
   private static final long serialVersionUID = -8869148464118507846L;
   private final Chronology iChronology;
   private final int iSkip;
   private transient int iMinValue;

   public SkipDateTimeField(Chronology var1, DateTimeField var2) {
      this(var1, var2, 0);
   }

   public SkipDateTimeField(Chronology var1, DateTimeField var2, int var3) {
      super(var2);
      this.iChronology = var1;
      int var4 = super.getMinimumValue();
      if (var4 < var3) {
         this.iMinValue = var4 - 1;
      } else if (var4 == var3) {
         this.iMinValue = var3 + 1;
      } else {
         this.iMinValue = var4;
      }

      this.iSkip = var3;
   }

   public int get(long var1) {
      int var3 = super.get(var1);
      if (var3 <= this.iSkip) {
         --var3;
      }

      return var3;
   }

   public long set(long var1, int var3) {
      FieldUtils.verifyValueBounds((DateTimeField)this, var3, this.iMinValue, this.getMaximumValue());
      if (var3 <= this.iSkip) {
         if (var3 == this.iSkip) {
            throw new IllegalFieldValueException(DateTimeFieldType.year(), var3, (Number)null, (Number)null);
         }

         ++var3;
      }

      return super.set(var1, var3);
   }

   public int getMinimumValue() {
      return this.iMinValue;
   }

   private Object readResolve() {
      return this.getType().getField(this.iChronology);
   }
}
