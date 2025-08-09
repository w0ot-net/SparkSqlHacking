package org.apache.commons.math3.stat.inference;

import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;

public class BinomialTest {
   public boolean binomialTest(int numberOfTrials, int numberOfSuccesses, double probability, AlternativeHypothesis alternativeHypothesis, double alpha) {
      double pValue = this.binomialTest(numberOfTrials, numberOfSuccesses, probability, alternativeHypothesis);
      return pValue < alpha;
   }

   public double binomialTest(int numberOfTrials, int numberOfSuccesses, double probability, AlternativeHypothesis alternativeHypothesis) {
      if (numberOfTrials < 0) {
         throw new NotPositiveException(numberOfTrials);
      } else if (numberOfSuccesses < 0) {
         throw new NotPositiveException(numberOfSuccesses);
      } else if (!(probability < (double)0.0F) && !(probability > (double)1.0F)) {
         if (numberOfTrials < numberOfSuccesses) {
            throw new MathIllegalArgumentException(LocalizedFormats.BINOMIAL_INVALID_PARAMETERS_ORDER, new Object[]{numberOfTrials, numberOfSuccesses});
         } else if (alternativeHypothesis == null) {
            throw new NullArgumentException();
         } else {
            BinomialDistribution distribution = new BinomialDistribution((RandomGenerator)null, numberOfTrials, probability);
            switch (alternativeHypothesis) {
               case GREATER_THAN:
                  return (double)1.0F - distribution.cumulativeProbability(numberOfSuccesses - 1);
               case LESS_THAN:
                  return distribution.cumulativeProbability(numberOfSuccesses);
               case TWO_SIDED:
                  int criticalValueLow = 0;
                  int criticalValueHigh = numberOfTrials;
                  double pTotal = (double)0.0F;

                  do {
                     double pLow = distribution.probability(criticalValueLow);
                     double pHigh = distribution.probability(criticalValueHigh);
                     if (pLow == pHigh) {
                        pTotal += (double)2.0F * pLow;
                        ++criticalValueLow;
                        --criticalValueHigh;
                     } else if (pLow < pHigh) {
                        pTotal += pLow;
                        ++criticalValueLow;
                     } else {
                        pTotal += pHigh;
                        --criticalValueHigh;
                     }
                  } while(criticalValueLow <= numberOfSuccesses && criticalValueHigh >= numberOfSuccesses);

                  return pTotal;
               default:
                  throw new MathInternalError(LocalizedFormats.OUT_OF_RANGE_SIMPLE, new Object[]{alternativeHypothesis, AlternativeHypothesis.TWO_SIDED, AlternativeHypothesis.LESS_THAN});
            }
         }
      } else {
         throw new OutOfRangeException(probability, 0, 1);
      }
   }
}
