package org.apache.datasketches.theta;

import java.util.concurrent.atomic.AtomicBoolean;

class ConcurrentBackgroundThetaPropagation implements Runnable {
   private final ConcurrentSharedThetaSketch sharedThetaSketch;
   private final AtomicBoolean localPropagationInProgress;
   private final Sketch sketchIn;
   private final long singleHash;
   private final long epoch;

   ConcurrentBackgroundThetaPropagation(ConcurrentSharedThetaSketch sharedThetaSketch, AtomicBoolean localPropagationInProgress, Sketch sketchIn, long singleHash, long epoch) {
      this.sharedThetaSketch = sharedThetaSketch;
      this.localPropagationInProgress = localPropagationInProgress;
      this.sketchIn = sketchIn;
      this.singleHash = singleHash;
      this.epoch = epoch;
   }

   public void run() {
      if (!this.sharedThetaSketch.validateEpoch(this.epoch)) {
         this.sharedThetaSketch.endPropagation((AtomicBoolean)null, false);
      } else {
         if (this.singleHash != -1L) {
            this.sharedThetaSketch.propagate(this.singleHash);
         } else if (this.sketchIn != null) {
            long volTheta = this.sharedThetaSketch.getVolatileTheta();

            assert volTheta <= this.sketchIn.getThetaLong() : "volTheta = " + volTheta + ", bufTheta = " + this.sketchIn.getThetaLong();

            long[] cacheIn = this.sketchIn.getCache();
            if (this.sketchIn.isOrdered()) {
               for(long hashIn : cacheIn) {
                  if (hashIn >= volTheta) {
                     break;
                  }

                  this.sharedThetaSketch.propagate(hashIn);
               }
            } else {
               for(long hashIn : cacheIn) {
                  if (hashIn > 0L) {
                     this.sharedThetaSketch.propagate(hashIn);
                  }
               }
            }
         }

         this.sharedThetaSketch.endPropagation(this.localPropagationInProgress, false);
      }
   }
}
