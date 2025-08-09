package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.util.FastMath;

public class NormalApproximationInterval implements BinomialConfidenceInterval {
   public ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
      IntervalUtils.checkParameters(numberOfTrials, numberOfSuccesses, confidenceLevel);
      double mean = (double)numberOfSuccesses / (double)numberOfTrials;
      double alpha = ((double)1.0F - confidenceLevel) / (double)2.0F;
      NormalDistribution normalDistribution = new NormalDistribution();
      double difference = normalDistribution.inverseCumulativeProbability((double)1.0F - alpha) * FastMath.sqrt((double)1.0F / (double)numberOfTrials * mean * ((double)1.0F - mean));
      return new ConfidenceInterval(mean - difference, mean + difference, confidenceLevel);
   }
}
