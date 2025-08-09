package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.distribution.FDistribution;

public class ClopperPearsonInterval implements BinomialConfidenceInterval {
   public ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
      IntervalUtils.checkParameters(numberOfTrials, numberOfSuccesses, confidenceLevel);
      double lowerBound = (double)0.0F;
      double upperBound = (double)0.0F;
      double alpha = ((double)1.0F - confidenceLevel) / (double)2.0F;
      FDistribution distributionLowerBound = new FDistribution((double)(2 * (numberOfTrials - numberOfSuccesses + 1)), (double)(2 * numberOfSuccesses));
      double fValueLowerBound = distributionLowerBound.inverseCumulativeProbability((double)1.0F - alpha);
      if (numberOfSuccesses > 0) {
         lowerBound = (double)numberOfSuccesses / ((double)numberOfSuccesses + (double)(numberOfTrials - numberOfSuccesses + 1) * fValueLowerBound);
      }

      FDistribution distributionUpperBound = new FDistribution((double)(2 * (numberOfSuccesses + 1)), (double)(2 * (numberOfTrials - numberOfSuccesses)));
      double fValueUpperBound = distributionUpperBound.inverseCumulativeProbability((double)1.0F - alpha);
      if (numberOfSuccesses > 0) {
         upperBound = (double)(numberOfSuccesses + 1) * fValueUpperBound / ((double)(numberOfTrials - numberOfSuccesses) + (double)(numberOfSuccesses + 1) * fValueUpperBound);
      }

      return new ConfidenceInterval(lowerBound, upperBound, confidenceLevel);
   }
}
