package org.joda.time;

public interface ReadablePeriod {
   PeriodType getPeriodType();

   int size();

   DurationFieldType getFieldType(int var1);

   int getValue(int var1);

   int get(DurationFieldType var1);

   boolean isSupported(DurationFieldType var1);

   Period toPeriod();

   MutablePeriod toMutablePeriod();

   boolean equals(Object var1);

   int hashCode();

   String toString();
}
