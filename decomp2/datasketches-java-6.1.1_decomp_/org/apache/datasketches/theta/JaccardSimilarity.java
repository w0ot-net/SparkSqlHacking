package org.apache.datasketches.theta;

import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.BoundsOnRatiosInThetaSketchedSets;

public final class JaccardSimilarity {
   private static final double[] ZEROS = new double[]{(double)0.0F, (double)0.0F, (double)0.0F};
   private static final double[] ONES = new double[]{(double)1.0F, (double)1.0F, (double)1.0F};

   public static double[] jaccard(Sketch sketchA, Sketch sketchB) {
      if (sketchA != null && sketchB != null) {
         if (sketchA == sketchB) {
            return (double[])ONES.clone();
         } else if (sketchA.isEmpty() && sketchB.isEmpty()) {
            return (double[])ONES.clone();
         } else if (!sketchA.isEmpty() && !sketchB.isEmpty()) {
            int countA = sketchA.getRetainedEntries(true);
            int countB = sketchB.getRetainedEntries(true);
            int minK = 16;
            int maxK = 67108864;
            int newK = Math.max(Math.min(Util.ceilingPowerOf2(countA + countB), 67108864), 16);
            Union union = SetOperation.builder().setNominalEntries(newK).buildUnion();
            union.union(sketchA);
            union.union(sketchB);
            Sketch unionAB = union.getResult(false, (WritableMemory)null);
            long thetaLongUAB = unionAB.getThetaLong();
            long thetaLongA = sketchA.getThetaLong();
            long thetaLongB = sketchB.getThetaLong();
            int countUAB = unionAB.getRetainedEntries(true);
            if (countUAB == countA && countUAB == countB && thetaLongUAB == thetaLongA && thetaLongUAB == thetaLongB) {
               return (double[])ONES.clone();
            } else {
               Intersection inter = SetOperation.builder().buildIntersection();
               inter.intersect(sketchA);
               inter.intersect(sketchB);
               inter.intersect(unionAB);
               Sketch interABU = inter.getResult(false, (WritableMemory)null);
               double lb = BoundsOnRatiosInThetaSketchedSets.getLowerBoundForBoverA(unionAB, interABU);
               double est = BoundsOnRatiosInThetaSketchedSets.getEstimateOfBoverA(unionAB, interABU);
               double ub = BoundsOnRatiosInThetaSketchedSets.getUpperBoundForBoverA(unionAB, interABU);
               return new double[]{lb, est, ub};
            }
         } else {
            return (double[])ZEROS.clone();
         }
      } else {
         return (double[])ZEROS.clone();
      }
   }

   public static boolean exactlyEqual(Sketch sketchA, Sketch sketchB) {
      if (sketchA != null && sketchB != null) {
         if (sketchA == sketchB) {
            return true;
         } else if (sketchA.isEmpty() && sketchB.isEmpty()) {
            return true;
         } else if (!sketchA.isEmpty() && !sketchB.isEmpty()) {
            int countA = sketchA.getRetainedEntries(true);
            int countB = sketchB.getRetainedEntries(true);
            Union union = SetOperation.builder().setNominalEntries(Util.ceilingPowerOf2(countA + countB)).buildUnion();
            union.union(sketchA);
            union.union(sketchB);
            Sketch unionAB = union.getResult();
            long thetaLongUAB = unionAB.getThetaLong();
            long thetaLongA = sketchA.getThetaLong();
            long thetaLongB = sketchB.getThetaLong();
            int countUAB = unionAB.getRetainedEntries(true);
            return countUAB == countA && countUAB == countB && thetaLongUAB == thetaLongA && thetaLongUAB == thetaLongB;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static boolean similarityTest(Sketch measured, Sketch expected, double threshold) {
      double jRatioLB = jaccard(measured, expected)[0];
      return jRatioLB >= threshold;
   }

   public static boolean dissimilarityTest(Sketch measured, Sketch expected, double threshold) {
      double jRatioUB = jaccard(measured, expected)[2];
      return jRatioUB <= threshold;
   }
}
