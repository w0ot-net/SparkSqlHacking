package org.joda.time.field;

import org.joda.time.DateTimeField;

public class StrictDateTimeField extends DelegatedDateTimeField {
   private static final long serialVersionUID = 3154803964207950910L;

   public static DateTimeField getInstance(DateTimeField var0) {
      if (var0 == null) {
         return null;
      } else {
         if (var0 instanceof LenientDateTimeField) {
            var0 = ((LenientDateTimeField)var0).getWrappedField();
         }

         return (DateTimeField)(!var0.isLenient() ? var0 : new StrictDateTimeField(var0));
      }
   }

   protected StrictDateTimeField(DateTimeField var1) {
      super(var1);
   }

   public final boolean isLenient() {
      return false;
   }

   public long set(long var1, int var3) {
      FieldUtils.verifyValueBounds((DateTimeField)this, var3, this.getMinimumValue(var1), this.getMaximumValue(var1));
      return super.set(var1, var3);
   }
}
