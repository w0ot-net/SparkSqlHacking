package org.apache.datasketches.theta;

import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.datasketches.common.MemoryStatus;
import org.apache.datasketches.memory.WritableMemory;

interface ConcurrentSharedThetaSketch extends MemoryStatus {
   long NOT_SINGLE_HASH = -1L;
   double MIN_ERROR = 1.0E-7;

   static long computeExactLimit(long k, double error) {
      return 2L * Math.min(k, (long)Math.ceil((double)1.0F / Math.pow(Math.max(error, 1.0E-7), (double)2.0F)));
   }

   long getExactLimit();

   boolean startEagerPropagation();

   void endPropagation(AtomicBoolean var1, boolean var2);

   long getVolatileTheta();

   void awaitBgPropagationTermination();

   void initBgPropagationService();

   boolean propagate(AtomicBoolean var1, Sketch var2, long var3);

   void propagate(long var1);

   void updateEstimationSnapshot();

   void updateVolatileTheta();

   boolean validateEpoch(long var1);

   int getCompactBytes();

   int getCurrentBytes();

   double getEstimate();

   double getLowerBound(int var1);

   double getUpperBound(int var1);

   boolean isEmpty();

   boolean isEstimationMode();

   byte[] toByteArray();

   int getRetainedEntries(boolean var1);

   CompactSketch compact();

   CompactSketch compact(boolean var1, WritableMemory var2);

   UpdateSketch rebuild();

   void reset();
}
