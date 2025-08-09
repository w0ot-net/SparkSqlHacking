package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;
import org.joda.time.format.DateTimeFormatter;

public interface PartialConverter extends Converter {
   Chronology getChronology(Object var1, DateTimeZone var2);

   Chronology getChronology(Object var1, Chronology var2);

   int[] getPartialValues(ReadablePartial var1, Object var2, Chronology var3);

   int[] getPartialValues(ReadablePartial var1, Object var2, Chronology var3, DateTimeFormatter var4);
}
