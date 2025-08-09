package org.joda.time;

public interface ReadableInterval {
   Chronology getChronology();

   long getStartMillis();

   DateTime getStart();

   long getEndMillis();

   DateTime getEnd();

   boolean contains(ReadableInstant var1);

   boolean contains(ReadableInterval var1);

   boolean overlaps(ReadableInterval var1);

   boolean isAfter(ReadableInstant var1);

   boolean isAfter(ReadableInterval var1);

   boolean isBefore(ReadableInstant var1);

   boolean isBefore(ReadableInterval var1);

   Interval toInterval();

   MutableInterval toMutableInterval();

   Duration toDuration();

   long toDurationMillis();

   Period toPeriod();

   Period toPeriod(PeriodType var1);

   boolean equals(Object var1);

   int hashCode();

   String toString();
}
