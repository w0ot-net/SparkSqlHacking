package org.apache.datasketches.theta;

import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.HashOperations;

final class ConcurrentHeapThetaBuffer extends HeapQuickSelectSketch {
   private final ConcurrentSharedThetaSketch shared;
   private boolean isExactMode;
   private final boolean propagateOrderedCompact;
   private final AtomicBoolean localPropagationInProgress;

   ConcurrentHeapThetaBuffer(int lgNomLongs, long seed, ConcurrentSharedThetaSketch shared, boolean propagateOrderedCompact, int maxNumLocalThreads) {
      super(computeLogBufferSize(lgNomLongs, shared.getExactLimit(), maxNumLocalThreads), seed, 1.0F, ResizeFactor.X1, false);
      this.shared = shared;
      this.isExactMode = true;
      this.propagateOrderedCompact = propagateOrderedCompact;
      this.localPropagationInProgress = new AtomicBoolean(false);
   }

   private static int computeLogBufferSize(int lgNomLongs, long exactSize, int maxNumLocalBuffers) {
      return Math.min(lgNomLongs, (int)Math.log(Math.sqrt((double)exactSize) / (double)(2 * maxNumLocalBuffers)));
   }

   private boolean propagateToSharedSketch(long hash) {
      while(this.localPropagationInProgress.get()) {
      }

      this.localPropagationInProgress.set(true);
      boolean res = this.shared.propagate(this.localPropagationInProgress, (Sketch)null, hash);
      this.thetaLong_ = this.shared.getVolatileTheta();
      return res;
   }

   private void propagateToSharedSketch() {
      while(this.localPropagationInProgress.get()) {
      }

      CompactSketch compactSketch = this.compact(this.propagateOrderedCompact, (WritableMemory)null);
      this.localPropagationInProgress.set(true);
      this.shared.propagate(this.localPropagationInProgress, compactSketch, -1L);
      super.reset();
      this.thetaLong_ = this.shared.getVolatileTheta();
   }

   public int getCompactBytes() {
      return this.shared.getCompactBytes();
   }

   public int getCurrentBytes() {
      return this.shared.getCurrentBytes();
   }

   public double getEstimate() {
      return this.shared.getEstimate();
   }

   public double getLowerBound(int numStdDev) {
      return this.shared.getLowerBound(numStdDev);
   }

   public double getUpperBound(int numStdDev) {
      return this.shared.getUpperBound(numStdDev);
   }

   public boolean hasMemory() {
      return this.shared.hasMemory();
   }

   public boolean isDirect() {
      return this.shared.isDirect();
   }

   public boolean isEmpty() {
      return this.shared.isEmpty();
   }

   public boolean isEstimationMode() {
      return this.shared.isEstimationMode();
   }

   public boolean isSameResource(Memory that) {
      return this.shared.isSameResource(that);
   }

   public byte[] toByteArray() {
      throw new UnsupportedOperationException("Local theta buffer need not be serialized");
   }

   public void reset() {
      super.reset();
      this.isExactMode = true;
      this.localPropagationInProgress.set(false);
   }

   UpdateReturnState hashUpdate(long hash) {
      if (this.isExactMode) {
         this.isExactMode = !this.shared.isEstimationMode();
      }

      HashOperations.checkHashCorruption(hash);
      if (this.getHashTableThreshold() == 0 || this.isExactMode) {
         if (HashOperations.continueCondition(this.getThetaLong(), hash)) {
            return UpdateReturnState.RejectedOverTheta;
         }

         if (this.propagateToSharedSketch(hash)) {
            return UpdateReturnState.ConcurrentPropagated;
         }
      }

      UpdateReturnState state = super.hashUpdate(hash);
      if (this.isOutOfSpace(this.getRetainedEntries(true) + 1)) {
         this.propagateToSharedSketch();
         return UpdateReturnState.ConcurrentPropagated;
      } else {
         return state == UpdateReturnState.InsertedCountIncremented ? UpdateReturnState.ConcurrentBufferInserted : state;
      }
   }
}
