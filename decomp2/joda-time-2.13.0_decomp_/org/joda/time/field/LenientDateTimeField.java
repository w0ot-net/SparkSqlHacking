package org.joda.time.field;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;

public class LenientDateTimeField extends DelegatedDateTimeField {
   private static final long serialVersionUID = 8714085824173290599L;
   private final Chronology iBase;

   public static DateTimeField getInstance(DateTimeField var0, Chronology var1) {
      if (var0 == null) {
         return null;
      } else {
         if (var0 instanceof StrictDateTimeField) {
            var0 = ((StrictDateTimeField)var0).getWrappedField();
         }

         return (DateTimeField)(var0.isLenient() ? var0 : new LenientDateTimeField(var0, var1));
      }
   }

   protected LenientDateTimeField(DateTimeField var1, Chronology var2) {
      super(var1);
      this.iBase = var2;
   }

   public final boolean isLenient() {
      return true;
   }

   public long set(long var1, int var3) {
      long var4 = this.iBase.getZone().convertUTCToLocal(var1);
      long var6 = FieldUtils.safeSubtract((long)var3, (long)this.get(var1));
      var4 = this.getType().getField(this.iBase.withUTC()).add(var4, var6);
      return this.iBase.getZone().convertLocalToUTC(var4, false, var1);
   }
}
