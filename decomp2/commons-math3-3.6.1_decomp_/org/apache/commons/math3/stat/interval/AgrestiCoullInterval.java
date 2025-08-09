package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.util.FastMath;

public class AgrestiCoullInterval implements BinomialConfidenceInterval {
   public ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
      IntervalUtils.checkParameters(numberOfTrials, numberOfSuccesses, confidenceLevel);
      double alpha = ((double)1.0F - confidenceLevel) / (double)2.0F;
      NormalDistribution normalDistribution = new NormalDistribution();
      double z = normalDistribution.inverseCumulativeProbability((double)1.0F - alpha);
      double zSquared = FastMath.pow(z, 2);
      double modifiedNumberOfTrials = (double)numberOfTrials + zSquared;
      double modifiedSuccessesRatio = (double)1.0F / modifiedNumberOfTrials * ((double)numberOfSuccesses + (double)0.5F * zSquared);
      double difference = z * FastMath.sqrt((double)1.0F / modifiedNumberOfTrials * modifiedSuccessesRatio * ((double)1.0F - modifiedSuccessesRatio));
      return new ConfidenceInterval(modifiedSuccessesRatio - difference, modifiedSuccessesRatio + difference, confidenceLevel);
   }
}
