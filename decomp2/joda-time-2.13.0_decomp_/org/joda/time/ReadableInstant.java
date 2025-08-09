package org.joda.time;

public interface ReadableInstant extends Comparable {
   long getMillis();

   Chronology getChronology();

   DateTimeZone getZone();

   int get(DateTimeFieldType var1);

   boolean isSupported(DateTimeFieldType var1);

   Instant toInstant();

   boolean isEqual(ReadableInstant var1);

   boolean isAfter(ReadableInstant var1);

   boolean isBefore(ReadableInstant var1);

   boolean equals(Object var1);

   int hashCode();

   String toString();
}
