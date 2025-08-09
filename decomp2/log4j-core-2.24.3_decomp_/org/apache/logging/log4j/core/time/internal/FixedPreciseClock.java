package org.apache.logging.log4j.core.time.internal;

import org.apache.logging.log4j.core.time.MutableInstant;
import org.apache.logging.log4j.core.time.PreciseClock;

public class FixedPreciseClock implements PreciseClock {
   private final long currentTimeMillis;
   private final int nanosOfMillisecond;

   public FixedPreciseClock() {
      this(0L);
   }

   public FixedPreciseClock(final long currentTimeMillis) {
      this(currentTimeMillis, 0);
   }

   public FixedPreciseClock(final long currentTimeMillis, final int nanosOfMillisecond) {
      this.currentTimeMillis = currentTimeMillis;
      this.nanosOfMillisecond = nanosOfMillisecond;
   }

   public void init(final MutableInstant instant) {
      instant.initFromEpochMilli(this.currentTimeMillis, this.nanosOfMillisecond);
   }

   public long currentTimeMillis() {
      return this.currentTimeMillis;
   }
}
