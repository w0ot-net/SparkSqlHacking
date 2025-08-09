package org.apache.datasketches.tuple;

import org.apache.datasketches.thetacommon.BinomialBoundsN;

public abstract class Sketch {
   protected static final byte PREAMBLE_LONGS = 1;
   long thetaLong_;
   boolean empty_ = true;
   protected SummaryFactory summaryFactory_ = null;

   Sketch(long thetaLong, boolean empty, SummaryFactory summaryFactory) {
      this.thetaLong_ = thetaLong;
      this.empty_ = empty;
      this.summaryFactory_ = summaryFactory;
   }

   public abstract CompactSketch compact();

   public double getEstimate() {
      return !this.isEstimationMode() ? (double)this.getRetainedEntries() : (double)this.getRetainedEntries() / this.getTheta();
   }

   public double getUpperBound(int numStdDev) {
      return !this.isEstimationMode() ? (double)this.getRetainedEntries() : BinomialBoundsN.getUpperBound((long)this.getRetainedEntries(), this.getTheta(), numStdDev, this.empty_);
   }

   public double getLowerBound(int numStdDev) {
      return !this.isEstimationMode() ? (double)this.getRetainedEntries() : BinomialBoundsN.getLowerBound((long)this.getRetainedEntries(), this.getTheta(), numStdDev, this.empty_);
   }

   public double getEstimate(int numSubsetEntries) {
      return !this.isEstimationMode() ? (double)numSubsetEntries : (double)numSubsetEntries / this.getTheta();
   }

   public double getLowerBound(int numStdDev, int numSubsetEntries) {
      return !this.isEstimationMode() ? (double)numSubsetEntries : BinomialBoundsN.getLowerBound((long)numSubsetEntries, this.getTheta(), numStdDev, this.isEmpty());
   }

   public double getUpperBound(int numStdDev, int numSubsetEntries) {
      return !this.isEstimationMode() ? (double)numSubsetEntries : BinomialBoundsN.getUpperBound((long)numSubsetEntries, this.getTheta(), numStdDev, this.isEmpty());
   }

   public boolean isEmpty() {
      return this.empty_;
   }

   public boolean isEstimationMode() {
      return this.thetaLong_ < Long.MAX_VALUE && !this.isEmpty();
   }

   public abstract int getRetainedEntries();

   public abstract int getCountLessThanThetaLong(long var1);

   public SummaryFactory getSummaryFactory() {
      return this.summaryFactory_;
   }

   public double getTheta() {
      return (double)this.getThetaLong() / (double)Long.MAX_VALUE;
   }

   public abstract byte[] toByteArray();

   public abstract TupleSketchIterator iterator();

   public long getThetaLong() {
      return this.isEmpty() ? Long.MAX_VALUE : this.thetaLong_;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("### ").append(this.getClass().getSimpleName()).append(" SUMMARY: ").append(org.apache.datasketches.common.Util.LS);
      sb.append("   Estimate                : ").append(this.getEstimate()).append(org.apache.datasketches.common.Util.LS);
      sb.append("   Upper Bound, 95% conf   : ").append(this.getUpperBound(2)).append(org.apache.datasketches.common.Util.LS);
      sb.append("   Lower Bound, 95% conf   : ").append(this.getLowerBound(2)).append(org.apache.datasketches.common.Util.LS);
      sb.append("   Theta (double)          : ").append(this.getTheta()).append(org.apache.datasketches.common.Util.LS);
      sb.append("   Theta (long)            : ").append(this.getThetaLong()).append(org.apache.datasketches.common.Util.LS);
      sb.append("   EstMode?                : ").append(this.isEstimationMode()).append(org.apache.datasketches.common.Util.LS);
      sb.append("   Empty?                  : ").append(this.isEmpty()).append(org.apache.datasketches.common.Util.LS);
      sb.append("   Retained Entries        : ").append(this.getRetainedEntries()).append(org.apache.datasketches.common.Util.LS);
      if (this instanceof UpdatableSketch) {
         UpdatableSketch updatable = (UpdatableSketch)this;
         sb.append("   Nominal Entries (k)     : ").append(updatable.getNominalEntries()).append(org.apache.datasketches.common.Util.LS);
         sb.append("   Current Capacity        : ").append(updatable.getCurrentCapacity()).append(org.apache.datasketches.common.Util.LS);
         sb.append("   Resize Factor           : ").append(updatable.getResizeFactor().getValue()).append(org.apache.datasketches.common.Util.LS);
         sb.append("   Sampling Probability (p): ").append(updatable.getSamplingProbability()).append(org.apache.datasketches.common.Util.LS);
      }

      sb.append("### END SKETCH SUMMARY").append(org.apache.datasketches.common.Util.LS);
      return sb.toString();
   }
}
