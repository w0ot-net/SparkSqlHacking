package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

class ReadablePartialConverter extends AbstractConverter implements PartialConverter {
   static final ReadablePartialConverter INSTANCE = new ReadablePartialConverter();

   protected ReadablePartialConverter() {
   }

   public Chronology getChronology(Object var1, DateTimeZone var2) {
      return this.getChronology(var1, (Chronology)null).withZone(var2);
   }

   public Chronology getChronology(Object var1, Chronology var2) {
      if (var2 == null) {
         var2 = ((ReadablePartial)var1).getChronology();
         var2 = DateTimeUtils.getChronology(var2);
      }

      return var2;
   }

   public int[] getPartialValues(ReadablePartial var1, Object var2, Chronology var3) {
      ReadablePartial var4 = (ReadablePartial)var2;
      int var5 = var1.size();
      int[] var6 = new int[var5];

      for(int var7 = 0; var7 < var5; ++var7) {
         var6[var7] = var4.get(var1.getFieldType(var7));
      }

      var3.validate(var1, var6);
      return var6;
   }

   public Class getSupportedType() {
      return ReadablePartial.class;
   }
}
