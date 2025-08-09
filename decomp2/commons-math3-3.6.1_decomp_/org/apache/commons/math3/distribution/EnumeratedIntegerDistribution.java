package org.apache.commons.math3.distribution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.Pair;

public class EnumeratedIntegerDistribution extends AbstractIntegerDistribution {
   private static final long serialVersionUID = 20130308L;
   protected final EnumeratedDistribution innerDistribution;

   public EnumeratedIntegerDistribution(int[] singletons, double[] probabilities) throws DimensionMismatchException, NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
      this(new Well19937c(), singletons, probabilities);
   }

   public EnumeratedIntegerDistribution(RandomGenerator rng, int[] singletons, double[] probabilities) throws DimensionMismatchException, NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
      super(rng);
      this.innerDistribution = new EnumeratedDistribution(rng, createDistribution(singletons, probabilities));
   }

   public EnumeratedIntegerDistribution(RandomGenerator rng, int[] data) {
      super(rng);
      Map<Integer, Integer> dataMap = new HashMap();

      for(int value : data) {
         Integer count = (Integer)dataMap.get(value);
         if (count == null) {
            count = 0;
         }

         dataMap.put(value, count + 1);
      }

      int massPoints = dataMap.size();
      double denom = (double)data.length;
      int[] values = new int[massPoints];
      double[] probabilities = new double[massPoints];
      int index = 0;

      for(Map.Entry entry : dataMap.entrySet()) {
         values[index] = (Integer)entry.getKey();
         probabilities[index] = (double)(Integer)entry.getValue() / denom;
         ++index;
      }

      this.innerDistribution = new EnumeratedDistribution(rng, createDistribution(values, probabilities));
   }

   public EnumeratedIntegerDistribution(int[] data) {
      this((RandomGenerator)(new Well19937c()), (int[])data);
   }

   private static List createDistribution(int[] singletons, double[] probabilities) {
      if (singletons.length != probabilities.length) {
         throw new DimensionMismatchException(probabilities.length, singletons.length);
      } else {
         List<Pair<Integer, Double>> samples = new ArrayList(singletons.length);

         for(int i = 0; i < singletons.length; ++i) {
            samples.add(new Pair(singletons[i], probabilities[i]));
         }

         return samples;
      }
   }

   public double probability(int x) {
      return this.innerDistribution.probability(x);
   }

   public double cumulativeProbability(int x) {
      double probability = (double)0.0F;

      for(Pair sample : this.innerDistribution.getPmf()) {
         if ((Integer)sample.getKey() <= x) {
            probability += (Double)sample.getValue();
         }
      }

      return probability;
   }

   public double getNumericalMean() {
      double mean = (double)0.0F;

      for(Pair sample : this.innerDistribution.getPmf()) {
         mean += (Double)sample.getValue() * (double)(Integer)sample.getKey();
      }

      return mean;
   }

   public double getNumericalVariance() {
      double mean = (double)0.0F;
      double meanOfSquares = (double)0.0F;

      for(Pair sample : this.innerDistribution.getPmf()) {
         mean += (Double)sample.getValue() * (double)(Integer)sample.getKey();
         meanOfSquares += (Double)sample.getValue() * (double)(Integer)sample.getKey() * (double)(Integer)sample.getKey();
      }

      return meanOfSquares - mean * mean;
   }

   public int getSupportLowerBound() {
      int min = Integer.MAX_VALUE;

      for(Pair sample : this.innerDistribution.getPmf()) {
         if ((Integer)sample.getKey() < min && (Double)sample.getValue() > (double)0.0F) {
            min = (Integer)sample.getKey();
         }
      }

      return min;
   }

   public int getSupportUpperBound() {
      int max = Integer.MIN_VALUE;

      for(Pair sample : this.innerDistribution.getPmf()) {
         if ((Integer)sample.getKey() > max && (Double)sample.getValue() > (double)0.0F) {
            max = (Integer)sample.getKey();
         }
      }

      return max;
   }

   public boolean isSupportConnected() {
      return true;
   }

   public int sample() {
      return (Integer)this.innerDistribution.sample();
   }
}
