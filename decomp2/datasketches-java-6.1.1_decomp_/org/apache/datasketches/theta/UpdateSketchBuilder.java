package org.apache.datasketches.theta;

import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.common.SuppressFBWarnings;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.DefaultMemoryRequestServer;
import org.apache.datasketches.memory.MemoryRequestServer;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.ThetaUtil;

public class UpdateSketchBuilder {
   private int bLgNomLongs = Integer.numberOfTrailingZeros(4096);
   private long bSeed = 9001L;
   private ResizeFactor bRF;
   private Family bFam;
   private float bP = 1.0F;
   private MemoryRequestServer bMemReqSvr;
   private int bNumPoolThreads;
   private int bLocalLgNomLongs;
   private boolean bPropagateOrderedCompact;
   private double bMaxConcurrencyError;
   private int bMaxNumLocalThreads;

   public UpdateSketchBuilder() {
      this.bRF = ResizeFactor.X8;
      this.bFam = Family.QUICKSELECT;
      this.bMemReqSvr = new DefaultMemoryRequestServer();
      this.bNumPoolThreads = ConcurrentPropagationService.NUM_POOL_THREADS;
      this.bLocalLgNomLongs = 4;
      this.bPropagateOrderedCompact = true;
      this.bMaxConcurrencyError = (double)0.0F;
      this.bMaxNumLocalThreads = 1;
   }

   public UpdateSketchBuilder setNominalEntries(int nomEntries) {
      this.bLgNomLongs = ThetaUtil.checkNomLongs(nomEntries);
      return this;
   }

   public UpdateSketchBuilder setLogNominalEntries(int lgNomEntries) {
      this.bLgNomLongs = ThetaUtil.checkNomLongs(1 << lgNomEntries);
      return this;
   }

   public int getLgNominalEntries() {
      return this.bLgNomLongs;
   }

   public UpdateSketchBuilder setLocalNominalEntries(int nomEntries) {
      this.bLocalLgNomLongs = Integer.numberOfTrailingZeros(Util.ceilingPowerOf2(nomEntries));
      if (this.bLocalLgNomLongs <= 26 && this.bLocalLgNomLongs >= 4) {
         return this;
      } else {
         throw new SketchesArgumentException("Nominal Entries must be >= 16 and <= 67108864: " + nomEntries);
      }
   }

   public UpdateSketchBuilder setLocalLogNominalEntries(int lgNomEntries) {
      this.bLocalLgNomLongs = lgNomEntries;
      if (this.bLocalLgNomLongs <= 26 && this.bLocalLgNomLongs >= 4) {
         return this;
      } else {
         throw new SketchesArgumentException("Log Nominal Entries must be >= 4 and <= 26: " + lgNomEntries);
      }
   }

   public int getLocalLgNominalEntries() {
      return this.bLocalLgNomLongs;
   }

   public UpdateSketchBuilder setSeed(long seed) {
      this.bSeed = seed;
      return this;
   }

   public long getSeed() {
      return this.bSeed;
   }

   public UpdateSketchBuilder setP(float p) {
      if (!((double)p <= (double)0.0F) && !((double)p > (double)1.0F)) {
         this.bP = p;
         return this;
      } else {
         throw new SketchesArgumentException("p must be > 0 and <= 1.0: " + p);
      }
   }

   public float getP() {
      return this.bP;
   }

   public UpdateSketchBuilder setResizeFactor(ResizeFactor rf) {
      this.bRF = rf;
      return this;
   }

   public ResizeFactor getResizeFactor() {
      return this.bRF;
   }

   public UpdateSketchBuilder setFamily(Family family) {
      this.bFam = family;
      return this;
   }

   public Family getFamily() {
      return this.bFam;
   }

   public UpdateSketchBuilder setMemoryRequestServer(MemoryRequestServer memReqSvr) {
      this.bMemReqSvr = memReqSvr;
      return this;
   }

   public MemoryRequestServer getMemoryRequestServer() {
      return this.bMemReqSvr;
   }

   public void setNumPoolThreads(int numPoolThreads) {
      this.bNumPoolThreads = numPoolThreads;
   }

   public int getNumPoolThreads() {
      return this.bNumPoolThreads;
   }

   public UpdateSketchBuilder setPropagateOrderedCompact(boolean prop) {
      this.bPropagateOrderedCompact = prop;
      return this;
   }

   public boolean getPropagateOrderedCompact() {
      return this.bPropagateOrderedCompact;
   }

   public void setMaxConcurrencyError(double maxConcurrencyError) {
      this.bMaxConcurrencyError = maxConcurrencyError;
   }

   public double getMaxConcurrencyError() {
      return this.bMaxConcurrencyError;
   }

   public void setMaxNumLocalThreads(int maxNumLocalThreads) {
      this.bMaxNumLocalThreads = maxNumLocalThreads;
   }

   public int getMaxNumLocalThreads() {
      return this.bMaxNumLocalThreads;
   }

   public UpdateSketch build() {
      return this.build((WritableMemory)null);
   }

   public UpdateSketch build(WritableMemory dstMem) {
      UpdateSketch sketch = null;
      switch (this.bFam) {
         case ALPHA:
            if (dstMem != null) {
               throw new SketchesArgumentException("AlphaSketch cannot be made Direct to Memory.");
            }

            sketch = HeapAlphaSketch.newHeapInstance(this.bLgNomLongs, this.bSeed, this.bP, this.bRF);
            break;
         case QUICKSELECT:
            if (dstMem == null) {
               sketch = new HeapQuickSelectSketch(this.bLgNomLongs, this.bSeed, this.bP, this.bRF, false);
            } else {
               sketch = new DirectQuickSelectSketch(this.bLgNomLongs, this.bSeed, this.bP, this.bRF, this.bMemReqSvr, dstMem, false);
            }
            break;
         default:
            throw new SketchesArgumentException("Given Family cannot be built as a Theta Sketch: " + this.bFam.toString());
      }

      return sketch;
   }

   public UpdateSketch buildShared() {
      return this.buildShared((WritableMemory)null);
   }

   @SuppressFBWarnings(
      value = {"ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD"},
      justification = "Harmless in Builder, fix later"
   )
   public UpdateSketch buildShared(WritableMemory dstMem) {
      ConcurrentPropagationService.NUM_POOL_THREADS = this.bNumPoolThreads;
      return (UpdateSketch)(dstMem == null ? new ConcurrentHeapQuickSelectSketch(this.bLgNomLongs, this.bSeed, this.bMaxConcurrencyError) : new ConcurrentDirectQuickSelectSketch(this.bLgNomLongs, this.bSeed, this.bMaxConcurrencyError, dstMem));
   }

   @SuppressFBWarnings(
      value = {"ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD"},
      justification = "Harmless in Builder, fix later"
   )
   public UpdateSketch buildSharedFromSketch(UpdateSketch sketch, WritableMemory dstMem) {
      ConcurrentPropagationService.NUM_POOL_THREADS = this.bNumPoolThreads;
      return (UpdateSketch)(dstMem == null ? new ConcurrentHeapQuickSelectSketch(sketch, this.bSeed, this.bMaxConcurrencyError) : new ConcurrentDirectQuickSelectSketch(sketch, this.bSeed, this.bMaxConcurrencyError, dstMem));
   }

   public UpdateSketch buildLocal(UpdateSketch shared) {
      if (shared != null && shared instanceof ConcurrentSharedThetaSketch) {
         return new ConcurrentHeapThetaBuffer(this.bLocalLgNomLongs, this.bSeed, (ConcurrentSharedThetaSketch)shared, this.bPropagateOrderedCompact, this.bMaxNumLocalThreads);
      } else {
         throw new SketchesStateException("The concurrent shared sketch must be built first.");
      }
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("UpdateSketchBuilder configuration:").append(Util.LS);
      sb.append("LgK:").append('\t').append(this.bLgNomLongs).append(Util.LS);
      sb.append("K:").append('\t').append(1 << this.bLgNomLongs).append(Util.LS);
      sb.append("LgLocalK:").append('\t').append(this.bLocalLgNomLongs).append(Util.LS);
      sb.append("LocalK:").append('\t').append(1 << this.bLocalLgNomLongs).append(Util.LS);
      sb.append("Seed:").append('\t').append(this.bSeed).append(Util.LS);
      sb.append("p:").append('\t').append(this.bP).append(Util.LS);
      sb.append("ResizeFactor:").append('\t').append(this.bRF).append(Util.LS);
      sb.append("Family:").append('\t').append(this.bFam).append(Util.LS);
      String mrsStr = this.bMemReqSvr.getClass().getSimpleName();
      sb.append("MemoryRequestServer:").append('\t').append(mrsStr).append(Util.LS);
      sb.append("Propagate Ordered Compact").append('\t').append(this.bPropagateOrderedCompact).append(Util.LS);
      sb.append("NumPoolThreads").append('\t').append(this.bNumPoolThreads).append(Util.LS);
      sb.append("MaxConcurrencyError").append('\t').append(this.bMaxConcurrencyError).append(Util.LS);
      sb.append("MaxNumLocalThreads").append('\t').append(this.bMaxNumLocalThreads).append(Util.LS);
      return sb.toString();
   }
}
