package org.joda.time.convert;

import java.util.Date;
import org.joda.time.Chronology;

final class DateConverter extends AbstractConverter implements InstantConverter, PartialConverter {
   static final DateConverter INSTANCE = new DateConverter();

   protected DateConverter() {
   }

   public long getInstantMillis(Object var1, Chronology var2) {
      Date var3 = (Date)var1;
      return var3.getTime();
   }

   public Class getSupportedType() {
      return Date.class;
   }
}
