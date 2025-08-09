package org.joda.time;

public interface ReadWritableInterval extends ReadableInterval {
   void setInterval(long var1, long var3);

   void setInterval(ReadableInterval var1);

   void setInterval(ReadableInstant var1, ReadableInstant var2);

   void setChronology(Chronology var1);

   void setStartMillis(long var1);

   void setStart(ReadableInstant var1);

   void setEndMillis(long var1);

   void setEnd(ReadableInstant var1);

   void setDurationAfterStart(ReadableDuration var1);

   void setDurationBeforeEnd(ReadableDuration var1);

   void setPeriodAfterStart(ReadablePeriod var1);

   void setPeriodBeforeEnd(ReadablePeriod var1);
}
