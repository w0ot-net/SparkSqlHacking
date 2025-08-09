package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.util.FastMath;

public class WilsonScoreInterval implements BinomialConfidenceInterval {
   public ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
      IntervalUtils.checkParameters(numberOfTrials, numberOfSuccesses, confidenceLevel);
      double alpha = ((double)1.0F - confidenceLevel) / (double)2.0F;
      NormalDistribution normalDistribution = new NormalDistribution();
      double z = normalDistribution.inverseCumulativeProbability((double)1.0F - alpha);
      double zSquared = FastMath.pow(z, 2);
      double mean = (double)numberOfSuccesses / (double)numberOfTrials;
      double factor = (double)1.0F / ((double)1.0F + (double)1.0F / (double)numberOfTrials * zSquared);
      double modifiedSuccessRatio = mean + (double)1.0F / (double)(2 * numberOfTrials) * zSquared;
      double difference = z * FastMath.sqrt((double)1.0F / (double)numberOfTrials * mean * ((double)1.0F - mean) + (double)1.0F / ((double)4.0F * FastMath.pow((double)numberOfTrials, 2)) * zSquared);
      double lowerBound = factor * (modifiedSuccessRatio - difference);
      double upperBound = factor * (modifiedSuccessRatio + difference);
      return new ConfidenceInterval(lowerBound, upperBound, confidenceLevel);
   }
}
