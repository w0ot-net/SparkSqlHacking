package org.glassfish.jersey.server.internal.monitoring;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.glassfish.jersey.server.internal.monitoring.core.AbstractSlidingWindowTimeReservoir;
import org.glassfish.jersey.server.internal.monitoring.core.SlidingWindowTrimmer;
import org.glassfish.jersey.server.internal.monitoring.core.UniformTimeSnapshot;
import org.glassfish.jersey.server.internal.monitoring.core.UniformTimeValuesSnapshot;

class SlidingWindowTimeReservoir extends AbstractSlidingWindowTimeReservoir {
   public SlidingWindowTimeReservoir(long window, TimeUnit windowUnit, long startTime, TimeUnit startTimeUnit, SlidingWindowTrimmer trimmer) {
      super(window, windowUnit, startTime, startTimeUnit, trimmer);
   }

   public SlidingWindowTimeReservoir(long window, TimeUnit windowUnit, long startTime, TimeUnit startTimeUnit) {
      this(window, windowUnit, startTime, startTimeUnit, (SlidingWindowTrimmer)null);
   }

   protected UniformTimeSnapshot snapshot(Collection values, long timeInterval, TimeUnit timeIntervalUnit, long time, TimeUnit timeUnit) {
      return new UniformTimeValuesSnapshot(values, timeInterval, timeIntervalUnit);
   }
}
