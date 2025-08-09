package org.glassfish.jersey.server.internal.monitoring.core;

import java.util.concurrent.ConcurrentNavigableMap;

public interface SlidingWindowTrimmer {
   void trim(ConcurrentNavigableMap var1, long var2);

   void setTimeReservoir(TimeReservoir var1);
}
