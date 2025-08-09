package org.apache.datasketches.sampling;

public class SampleSubsetSummary {
   private double lowerBound;
   private double estimate;
   private double upperBound;
   private double totalSketchWeight;

   SampleSubsetSummary(double lowerBound, double estimate, double upperBound, double totalSketchWeight) {
      this.lowerBound = lowerBound;
      this.estimate = estimate;
      this.upperBound = upperBound;
      this.totalSketchWeight = totalSketchWeight;
   }

   public double getLowerBound() {
      return this.lowerBound;
   }

   public double getTotalSketchWeight() {
      return this.totalSketchWeight;
   }

   public double getUpperBound() {
      return this.upperBound;
   }

   public double getEstimate() {
      return this.estimate;
   }
}
