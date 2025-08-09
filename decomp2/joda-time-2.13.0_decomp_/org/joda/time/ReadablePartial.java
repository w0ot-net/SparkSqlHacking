package org.joda.time;

public interface ReadablePartial extends Comparable {
   int size();

   DateTimeFieldType getFieldType(int var1);

   DateTimeField getField(int var1);

   int getValue(int var1);

   Chronology getChronology();

   int get(DateTimeFieldType var1);

   boolean isSupported(DateTimeFieldType var1);

   DateTime toDateTime(ReadableInstant var1);

   boolean equals(Object var1);

   int hashCode();

   String toString();
}
