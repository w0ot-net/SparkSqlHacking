package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;

public interface InstantConverter extends Converter {
   Chronology getChronology(Object var1, DateTimeZone var2);

   Chronology getChronology(Object var1, Chronology var2);

   long getInstantMillis(Object var1, Chronology var2);
}
