package org.apache.datasketches.tuple;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.thetacommon.BoundsOnRatiosInTupleSketchedSets;

public final class JaccardSimilarity {
   private static final double[] ZEROS = new double[]{(double)0.0F, (double)0.0F, (double)0.0F};
   private static final double[] ONES = new double[]{(double)1.0F, (double)1.0F, (double)1.0F};

   public static double[] jaccard(Sketch sketchA, Sketch sketchB, SummarySetOperations summarySetOps) {
      if (sketchA != null && sketchB != null) {
         if (sketchA.isEmpty() && sketchB.isEmpty()) {
            return (double[])ONES.clone();
         } else if (!sketchA.isEmpty() && !sketchB.isEmpty()) {
            int countA = sketchA.getRetainedEntries();
            int countB = sketchB.getRetainedEntries();
            int minK = 16;
            int maxK = 67108864;
            int newK = Math.max(Math.min(org.apache.datasketches.common.Util.ceilingPowerOf2(countA + countB), 67108864), 16);
            Union<S> union = new Union(newK, summarySetOps);
            union.union(sketchA);
            union.union(sketchB);
            Sketch<S> unionAB = union.getResult();
            long thetaLongUAB = unionAB.getThetaLong();
            long thetaLongA = sketchA.getThetaLong();
            long thetaLongB = sketchB.getThetaLong();
            int countUAB = unionAB.getRetainedEntries();
            if (countUAB == countA && countUAB == countB && thetaLongUAB == thetaLongA && thetaLongUAB == thetaLongB) {
               return (double[])ONES.clone();
            } else {
               Intersection<S> inter = new Intersection(summarySetOps);
               inter.intersect(sketchA);
               inter.intersect(sketchB);
               inter.intersect(unionAB);
               Sketch<S> interABU = inter.getResult();
               double lb = BoundsOnRatiosInTupleSketchedSets.getLowerBoundForBoverA(unionAB, interABU);
               double est = BoundsOnRatiosInTupleSketchedSets.getEstimateOfBoverA(unionAB, interABU);
               double ub = BoundsOnRatiosInTupleSketchedSets.getUpperBoundForBoverA(unionAB, interABU);
               return new double[]{lb, est, ub};
            }
         } else {
            return (double[])ZEROS.clone();
         }
      } else {
         return (double[])ZEROS.clone();
      }
   }

   public static double[] jaccard(Sketch sketchA, org.apache.datasketches.theta.Sketch sketchB, Summary summary, SummarySetOperations summarySetOps) {
      if (summary == null) {
         throw new SketchesArgumentException("Summary cannot be null.");
      } else if (sketchA != null && sketchB != null) {
         if (sketchA.isEmpty() && sketchB.isEmpty()) {
            return (double[])ONES.clone();
         } else if (!sketchA.isEmpty() && !sketchB.isEmpty()) {
            int countA = sketchA.getRetainedEntries();
            int countB = sketchB.getRetainedEntries(true);
            int minK = 16;
            int maxK = 67108864;
            int newK = Math.max(Math.min(org.apache.datasketches.common.Util.ceilingPowerOf2(countA + countB), 67108864), 16);
            Union<S> union = new Union(newK, summarySetOps);
            union.union(sketchA);
            union.union(sketchB, summary);
            Sketch<S> unionAB = union.getResult();
            long thetaLongUAB = unionAB.getThetaLong();
            long thetaLongA = sketchA.getThetaLong();
            long thetaLongB = sketchB.getThetaLong();
            int countUAB = unionAB.getRetainedEntries();
            if (countUAB == countA && countUAB == countB && thetaLongUAB == thetaLongA && thetaLongUAB == thetaLongB) {
               return (double[])ONES.clone();
            } else {
               Intersection<S> inter = new Intersection(summarySetOps);
               inter.intersect(sketchA);
               inter.intersect(sketchB, summary);
               inter.intersect(unionAB);
               Sketch<S> interABU = inter.getResult();
               double lb = BoundsOnRatiosInTupleSketchedSets.getLowerBoundForBoverA(unionAB, interABU);
               double est = BoundsOnRatiosInTupleSketchedSets.getEstimateOfBoverA(unionAB, interABU);
               double ub = BoundsOnRatiosInTupleSketchedSets.getUpperBoundForBoverA(unionAB, interABU);
               return new double[]{lb, est, ub};
            }
         } else {
            return (double[])ZEROS.clone();
         }
      } else {
         return (double[])ZEROS.clone();
      }
   }

   public static boolean exactlyEqual(Sketch sketchA, Sketch sketchB, SummarySetOperations summarySetOps) {
      if (sketchA != null && sketchB != null) {
         if (sketchA == sketchB) {
            return true;
         } else if (sketchA.isEmpty() && sketchB.isEmpty()) {
            return true;
         } else if (!sketchA.isEmpty() && !sketchB.isEmpty()) {
            int countA = sketchA.getRetainedEntries();
            int countB = sketchB.getRetainedEntries();
            Union<S> union = new Union(org.apache.datasketches.common.Util.ceilingPowerOf2(countA + countB), summarySetOps);
            union.union(sketchA);
            union.union(sketchB);
            Sketch<S> unionAB = union.getResult();
            long thetaLongUAB = unionAB.getThetaLong();
            long thetaLongA = sketchA.getThetaLong();
            long thetaLongB = sketchB.getThetaLong();
            int countUAB = unionAB.getRetainedEntries();
            return countUAB == countA && countUAB == countB && thetaLongUAB == thetaLongA && thetaLongUAB == thetaLongB;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static boolean exactlyEqual(Sketch sketchA, org.apache.datasketches.theta.Sketch sketchB, Summary summary, SummarySetOperations summarySetOps) {
      if (summary == null) {
         throw new SketchesArgumentException("Summary cannot be null.");
      } else if (sketchA != null && sketchB != null) {
         if (sketchA.isEmpty() && sketchB.isEmpty()) {
            return true;
         } else if (!sketchA.isEmpty() && !sketchB.isEmpty()) {
            int countA = sketchA.getRetainedEntries();
            int countB = sketchB.getRetainedEntries(true);
            Union<S> union = new Union(org.apache.datasketches.common.Util.ceilingPowerOf2(countA + countB), summarySetOps);
            union.union(sketchA);
            union.union(sketchB, summary);
            Sketch<S> unionAB = union.getResult();
            long thetaLongUAB = unionAB.getThetaLong();
            long thetaLongA = sketchA.getThetaLong();
            long thetaLongB = sketchB.getThetaLong();
            int countUAB = unionAB.getRetainedEntries();
            return countUAB == countA && countUAB == countB && thetaLongUAB == thetaLongA && thetaLongUAB == thetaLongB;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static boolean similarityTest(Sketch measured, Sketch expected, SummarySetOperations summarySetOps, double threshold) {
      double jRatioLB = jaccard(measured, expected, summarySetOps)[0];
      return jRatioLB >= threshold;
   }

   public static boolean similarityTest(Sketch measured, org.apache.datasketches.theta.Sketch expected, Summary summary, SummarySetOperations summarySetOps, double threshold) {
      double jRatioLB = jaccard(measured, expected, summary, summarySetOps)[0];
      return jRatioLB >= threshold;
   }

   public static boolean dissimilarityTest(Sketch measured, Sketch expected, SummarySetOperations summarySetOps, double threshold) {
      double jRatioUB = jaccard(measured, expected, summarySetOps)[2];
      return jRatioUB <= threshold;
   }

   public static boolean dissimilarityTest(Sketch measured, org.apache.datasketches.theta.Sketch expected, Summary summary, SummarySetOperations summarySetOps, double threshold) {
      double jRatioUB = jaccard(measured, expected, summary, summarySetOps)[2];
      return jRatioUB <= threshold;
   }
}
