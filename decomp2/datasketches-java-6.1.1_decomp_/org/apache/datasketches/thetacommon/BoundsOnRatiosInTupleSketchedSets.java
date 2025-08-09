package org.apache.datasketches.thetacommon;

import org.apache.datasketches.common.BoundsOnRatiosInSampledSets;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.tuple.Sketch;

public final class BoundsOnRatiosInTupleSketchedSets {
   private BoundsOnRatiosInTupleSketchedSets() {
   }

   public static double getLowerBoundForBoverA(Sketch sketchA, Sketch sketchB) {
      long thetaLongA = sketchA.getThetaLong();
      long thetaLongB = sketchB.getThetaLong();
      checkThetas(thetaLongA, thetaLongB);
      int countB = sketchB.getRetainedEntries();
      int countA = thetaLongB == thetaLongA ? sketchA.getRetainedEntries() : sketchA.getCountLessThanThetaLong(thetaLongB);
      if (countA <= 0) {
         return (double)0.0F;
      } else {
         double f = (double)thetaLongB / (double)Long.MAX_VALUE;
         return BoundsOnRatiosInSampledSets.getLowerBoundForBoverA((long)countA, (long)countB, f);
      }
   }

   public static double getLowerBoundForBoverA(Sketch sketchA, org.apache.datasketches.theta.Sketch sketchB) {
      long thetaLongA = sketchA.getThetaLong();
      long thetaLongB = sketchB.getThetaLong();
      checkThetas(thetaLongA, thetaLongB);
      int countB = sketchB.getRetainedEntries();
      int countA = thetaLongB == thetaLongA ? sketchA.getRetainedEntries() : sketchA.getCountLessThanThetaLong(thetaLongB);
      if (countA <= 0) {
         return (double)0.0F;
      } else {
         double f = (double)thetaLongB / (double)Long.MAX_VALUE;
         return BoundsOnRatiosInSampledSets.getLowerBoundForBoverA((long)countA, (long)countB, f);
      }
   }

   public static double getUpperBoundForBoverA(Sketch sketchA, Sketch sketchB) {
      long thetaLongA = sketchA.getThetaLong();
      long thetaLongB = sketchB.getThetaLong();
      checkThetas(thetaLongA, thetaLongB);
      int countB = sketchB.getRetainedEntries();
      int countA = thetaLongB == thetaLongA ? sketchA.getRetainedEntries() : sketchA.getCountLessThanThetaLong(thetaLongB);
      if (countA <= 0) {
         return (double)1.0F;
      } else {
         double f = (double)thetaLongB / (double)Long.MAX_VALUE;
         return BoundsOnRatiosInSampledSets.getUpperBoundForBoverA((long)countA, (long)countB, f);
      }
   }

   public static double getUpperBoundForBoverA(Sketch sketchA, org.apache.datasketches.theta.Sketch sketchB) {
      long thetaLongA = sketchA.getThetaLong();
      long thetaLongB = sketchB.getThetaLong();
      checkThetas(thetaLongA, thetaLongB);
      int countB = sketchB.getRetainedEntries(true);
      int countA = thetaLongB == thetaLongA ? sketchA.getRetainedEntries() : sketchA.getCountLessThanThetaLong(thetaLongB);
      if (countA <= 0) {
         return (double)1.0F;
      } else {
         double f = (double)thetaLongB / (double)Long.MAX_VALUE;
         return BoundsOnRatiosInSampledSets.getUpperBoundForBoverA((long)countA, (long)countB, f);
      }
   }

   public static double getEstimateOfBoverA(Sketch sketchA, Sketch sketchB) {
      long thetaLongA = sketchA.getThetaLong();
      long thetaLongB = sketchB.getThetaLong();
      checkThetas(thetaLongA, thetaLongB);
      int countB = sketchB.getRetainedEntries();
      int countA = thetaLongB == thetaLongA ? sketchA.getRetainedEntries() : sketchA.getCountLessThanThetaLong(thetaLongB);
      return countA <= 0 ? (double)0.5F : (double)countB / (double)countA;
   }

   public static double getEstimateOfBoverA(Sketch sketchA, org.apache.datasketches.theta.Sketch sketchB) {
      long thetaLongA = sketchA.getThetaLong();
      long thetaLongB = sketchB.getThetaLong();
      checkThetas(thetaLongA, thetaLongB);
      int countB = sketchB.getRetainedEntries(true);
      int countA = thetaLongB == thetaLongA ? sketchA.getRetainedEntries() : sketchA.getCountLessThanThetaLong(thetaLongB);
      return countA <= 0 ? (double)0.5F : (double)countB / (double)countA;
   }

   static void checkThetas(long thetaLongA, long thetaLongB) {
      if (thetaLongB > thetaLongA) {
         throw new SketchesArgumentException("ThetaLongB cannot be > ThetaLongA.");
      }
   }
}
