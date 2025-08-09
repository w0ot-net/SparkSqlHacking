package org.joda.time.convert;

import org.joda.time.Chronology;

class LongConverter extends AbstractConverter implements InstantConverter, PartialConverter, DurationConverter {
   static final LongConverter INSTANCE = new LongConverter();

   protected LongConverter() {
   }

   public long getInstantMillis(Object var1, Chronology var2) {
      return (Long)var1;
   }

   public long getDurationMillis(Object var1) {
      return (Long)var1;
   }

   public Class getSupportedType() {
      return Long.class;
   }
}
