package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadableInstant;
import org.joda.time.chrono.ISOChronology;

class ReadableInstantConverter extends AbstractConverter implements InstantConverter, PartialConverter {
   static final ReadableInstantConverter INSTANCE = new ReadableInstantConverter();

   protected ReadableInstantConverter() {
   }

   public Chronology getChronology(Object var1, DateTimeZone var2) {
      Chronology var3 = ((ReadableInstant)var1).getChronology();
      if (var3 == null) {
         return ISOChronology.getInstance(var2);
      } else {
         DateTimeZone var4 = var3.getZone();
         if (var4 != var2) {
            var3 = var3.withZone(var2);
            if (var3 == null) {
               return ISOChronology.getInstance(var2);
            }
         }

         return var3;
      }
   }

   public Chronology getChronology(Object var1, Chronology var2) {
      if (var2 == null) {
         var2 = ((ReadableInstant)var1).getChronology();
         var2 = DateTimeUtils.getChronology(var2);
      }

      return var2;
   }

   public long getInstantMillis(Object var1, Chronology var2) {
      return ((ReadableInstant)var1).getMillis();
   }

   public Class getSupportedType() {
      return ReadableInstant.class;
   }
}
