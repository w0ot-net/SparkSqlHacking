package org.apache.datasketches.theta;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.common.SuppressFBWarnings;
import org.apache.datasketches.memory.MemoryRequestServer;
import org.apache.datasketches.memory.WritableMemory;

final class ConcurrentDirectQuickSelectSketch extends DirectQuickSelectSketch implements ConcurrentSharedThetaSketch {
   private ExecutorService executorService_;
   private final AtomicBoolean sharedPropagationInProgress_;
   private volatile long volatileThetaLong_;
   private volatile double volatileEstimate_;
   private final long exactLimit_;
   private volatile long epoch_;

   ConcurrentDirectQuickSelectSketch(int lgNomLongs, long seed, double maxConcurrencyError, WritableMemory dstMem) {
      super(lgNomLongs, seed, 1.0F, ResizeFactor.X1, (MemoryRequestServer)null, dstMem, false);
      this.volatileThetaLong_ = Long.MAX_VALUE;
      this.volatileEstimate_ = (double)0.0F;
      this.exactLimit_ = ConcurrentSharedThetaSketch.computeExactLimit(1L << this.getLgNomLongs(), maxConcurrencyError);
      this.sharedPropagationInProgress_ = new AtomicBoolean(false);
      this.epoch_ = 0L;
      this.initBgPropagationService();
   }

   ConcurrentDirectQuickSelectSketch(UpdateSketch sketch, long seed, double maxConcurrencyError, WritableMemory dstMem) {
      super(sketch.getLgNomLongs(), seed, 1.0F, ResizeFactor.X1, (MemoryRequestServer)null, dstMem, false);
      this.exactLimit_ = ConcurrentSharedThetaSketch.computeExactLimit(1L << this.getLgNomLongs(), maxConcurrencyError);
      this.sharedPropagationInProgress_ = new AtomicBoolean(false);
      this.epoch_ = 0L;
      this.initBgPropagationService();

      for(long hashIn : sketch.getCache()) {
         this.propagate(hashIn);
      }

      this.wmem_.putLong(16L, sketch.getThetaLong());
      this.updateVolatileTheta();
      this.updateEstimationSnapshot();
   }

   public double getEstimate() {
      return this.volatileEstimate_;
   }

   public boolean isEstimationMode() {
      return (long)this.getRetainedEntries(false) > this.exactLimit_ || super.isEstimationMode();
   }

   public byte[] toByteArray() {
      while(!this.sharedPropagationInProgress_.compareAndSet(false, true)) {
      }

      byte[] res = super.toByteArray();
      this.sharedPropagationInProgress_.set(false);
      return res;
   }

   public UpdateSketch rebuild() {
      super.rebuild();
      this.updateEstimationSnapshot();
      return this;
   }

   public void reset() {
      this.advanceEpoch();
      super.reset();
      this.volatileThetaLong_ = Long.MAX_VALUE;
      this.volatileEstimate_ = (double)0.0F;
   }

   UpdateReturnState hashUpdate(long hash) {
      String msg = "No update method should be called directly to a shared theta sketch. Updating the shared sketch is only permitted through propagation from local sketches.";
      throw new UnsupportedOperationException("No update method should be called directly to a shared theta sketch. Updating the shared sketch is only permitted through propagation from local sketches.");
   }

   public long getExactLimit() {
      return this.exactLimit_;
   }

   public boolean startEagerPropagation() {
      while(!this.sharedPropagationInProgress_.compareAndSet(false, true)) {
      }

      return !this.isEstimationMode();
   }

   public void endPropagation(AtomicBoolean localPropagationInProgress, boolean isEager) {
      this.updateVolatileTheta();
      this.updateEstimationSnapshot();
      if (isEager) {
         this.sharedPropagationInProgress_.set(false);
      }

      if (localPropagationInProgress != null) {
         localPropagationInProgress.set(false);
      }

   }

   public long getVolatileTheta() {
      return this.volatileThetaLong_;
   }

   public void awaitBgPropagationTermination() {
      try {
         this.executorService_.shutdown();

         while(!this.executorService_.awaitTermination(1L, TimeUnit.MILLISECONDS)) {
            Thread.sleep(1L);
         }
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

   }

   public final void initBgPropagationService() {
      this.executorService_ = ConcurrentPropagationService.getExecutorService(Thread.currentThread().getId());
   }

   public boolean propagate(AtomicBoolean localPropagationInProgress, Sketch sketchIn, long singleHash) {
      long epoch = this.epoch_;
      if (singleHash != -1L && (long)this.getRetainedEntries(false) < this.exactLimit_) {
         if (!this.startEagerPropagation()) {
            this.endPropagation(localPropagationInProgress, true);
            return false;
         } else if (!this.validateEpoch(epoch)) {
            this.endPropagation((AtomicBoolean)null, true);
            return true;
         } else {
            this.propagate(singleHash);
            this.endPropagation(localPropagationInProgress, true);
            return true;
         }
      } else {
         ConcurrentBackgroundThetaPropagation job = new ConcurrentBackgroundThetaPropagation(this, localPropagationInProgress, sketchIn, singleHash, epoch);
         this.executorService_.execute(job);
         return true;
      }
   }

   public void propagate(long singleHash) {
      super.hashUpdate(singleHash);
   }

   public void updateEstimationSnapshot() {
      this.volatileEstimate_ = super.getEstimate();
   }

   public void updateVolatileTheta() {
      this.volatileThetaLong_ = this.getThetaLong();
   }

   public boolean validateEpoch(long epoch) {
      return this.epoch_ == epoch;
   }

   @SuppressFBWarnings(
      value = {"VO_VOLATILE_INCREMENT"},
      justification = "Likely False Positive, Fix Later"
   )
   private void advanceEpoch() {
      this.awaitBgPropagationTermination();
      this.startEagerPropagation();
      ConcurrentPropagationService.resetExecutorService(Thread.currentThread().getId());
      ++this.epoch_;
      this.endPropagation((AtomicBoolean)null, true);
      this.initBgPropagationService();
   }
}
