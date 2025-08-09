package org.joda.time.field;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;

public final class SkipUndoDateTimeField extends DelegatedDateTimeField {
   private static final long serialVersionUID = -5875876968979L;
   private final Chronology iChronology;
   private final int iSkip;
   private transient int iMinValue;

   public SkipUndoDateTimeField(Chronology var1, DateTimeField var2) {
      this(var1, var2, 0);
   }

   public SkipUndoDateTimeField(Chronology var1, DateTimeField var2, int var3) {
      super(var2);
      this.iChronology = var1;
      int var4 = super.getMinimumValue();
      if (var4 < var3) {
         this.iMinValue = var4 + 1;
      } else if (var4 == var3 + 1) {
         this.iMinValue = var3;
      } else {
         this.iMinValue = var4;
      }

      this.iSkip = var3;
   }

   public int get(long var1) {
      int var3 = super.get(var1);
      if (var3 < this.iSkip) {
         ++var3;
      }

      return var3;
   }

   public long set(long var1, int var3) {
      FieldUtils.verifyValueBounds((DateTimeField)this, var3, this.iMinValue, this.getMaximumValue());
      if (var3 <= this.iSkip) {
         --var3;
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
