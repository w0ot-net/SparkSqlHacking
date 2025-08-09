package org.apache.datasketches.cpc;

import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SuppressFBWarnings;
import org.apache.datasketches.memory.Memory;

public final class CpcWrapper {
   Memory mem;

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP2"},
      justification = "This is OK here"
   )
   public CpcWrapper(Memory mem) {
      this.mem = mem;
      PreambleUtil.checkLoPreamble(mem);
      RuntimeAsserts.rtAssert(PreambleUtil.isCompressed(mem));
   }

   public CpcWrapper(byte[] byteArray) {
      this(Memory.wrap(byteArray));
   }

   public double getEstimate() {
      return !PreambleUtil.hasHip(this.mem) ? IconEstimator.getIconEstimate(PreambleUtil.getLgK(this.mem), (long)PreambleUtil.getNumCoupons(this.mem)) : PreambleUtil.getHipAccum(this.mem);
   }

   public static Family getFamily() {
      return Family.CPC;
   }

   public int getLgK() {
      return PreambleUtil.getLgK(this.mem);
   }

   public double getLowerBound(int kappa) {
      return !PreambleUtil.hasHip(this.mem) ? CpcConfidence.getIconConfidenceLB(PreambleUtil.getLgK(this.mem), (long)PreambleUtil.getNumCoupons(this.mem), kappa) : CpcConfidence.getHipConfidenceLB(PreambleUtil.getLgK(this.mem), (long)PreambleUtil.getNumCoupons(this.mem), PreambleUtil.getHipAccum(this.mem), kappa);
   }

   public double getUpperBound(int kappa) {
      return !PreambleUtil.hasHip(this.mem) ? CpcConfidence.getIconConfidenceUB(PreambleUtil.getLgK(this.mem), (long)PreambleUtil.getNumCoupons(this.mem), kappa) : CpcConfidence.getHipConfidenceUB(PreambleUtil.getLgK(this.mem), (long)PreambleUtil.getNumCoupons(this.mem), PreambleUtil.getHipAccum(this.mem), kappa);
   }
}
