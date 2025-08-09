package org.joda.time;

public interface ReadableDuration extends Comparable {
   long getMillis();

   Duration toDuration();

   Period toPeriod();

   boolean isEqual(ReadableDuration var1);

   boolean isLongerThan(ReadableDuration var1);

   boolean isShorterThan(ReadableDuration var1);

   boolean equals(Object var1);

   int hashCode();

   String toString();
}
