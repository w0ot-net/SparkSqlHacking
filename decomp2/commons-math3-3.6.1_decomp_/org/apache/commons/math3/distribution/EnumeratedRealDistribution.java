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
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.Pair;

public class EnumeratedRealDistribution extends AbstractRealDistribution {
   private static final long serialVersionUID = 20130308L;
   protected final EnumeratedDistribution innerDistribution;

   public EnumeratedRealDistribution(double[] singletons, double[] probabilities) throws DimensionMismatchException, NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
      this(new Well19937c(), singletons, probabilities);
   }

   public EnumeratedRealDistribution(RandomGenerator rng, double[] singletons, double[] probabilities) throws DimensionMismatchException, NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
      super(rng);
      this.innerDistribution = new EnumeratedDistribution(rng, createDistribution(singletons, probabilities));
   }

   public EnumeratedRealDistribution(RandomGenerator rng, double[] data) {
      super(rng);
      Map<Double, Integer> dataMap = new HashMap();

      for(double value : data) {
         Integer count = (Integer)dataMap.get(value);
         if (count == null) {
            count = 0;
         }

         dataMap.put(value, count + 1);
      }

      int massPoints = dataMap.size();
      double denom = (double)data.length;
      double[] values = new double[massPoints];
      double[] probabilities = new double[massPoints];
      int index = 0;

      for(Map.Entry entry : dataMap.entrySet()) {
         values[index] = (Double)entry.getKey();
         probabilities[index] = (double)(Integer)entry.getValue() / denom;
         ++index;
      }

      this.innerDistribution = new EnumeratedDistribution(rng, createDistribution(values, probabilities));
   }

   public EnumeratedRealDistribution(double[] data) {
      this((RandomGenerator)(new Well19937c()), data);
   }

   private static List createDistribution(double[] singletons, double[] probabilities) {
      if (singletons.length != probabilities.length) {
         throw new DimensionMismatchException(probabilities.length, singletons.length);
      } else {
         List<Pair<Double, Double>> samples = new ArrayList(singletons.length);

         for(int i = 0; i < singletons.length; ++i) {
            samples.add(new Pair(singletons[i], probabilities[i]));
         }

         return samples;
      }
   }

   public double probability(double x) {
      return this.innerDistribution.probability(x);
   }

   public double density(double x) {
      return this.probability(x);
   }

   public double cumulativeProbability(double x) {
      double probability = (double)0.0F;

      for(Pair sample : this.innerDistribution.getPmf()) {
         if ((Double)sample.getKey() <= x) {
            probability += (Double)sample.getValue();
         }
      }

      return probability;
   }

   public double inverseCumulativeProbability(double p) throws OutOfRangeException {
      if (!(p < (double)0.0F) && !(p > (double)1.0F)) {
         double probability = (double)0.0F;
         double x = this.getSupportLowerBound();

         for(Pair sample : this.innerDistribution.getPmf()) {
            if ((Double)sample.getValue() != (double)0.0F) {
               probability += (Double)sample.getValue();
               x = (Double)sample.getKey();
               if (probability >= p) {
                  break;
               }
            }
         }

         return x;
      } else {
         throw new OutOfRangeException(p, 0, 1);
      }
   }

   public double getNumericalMean() {
      double mean = (double)0.0F;

      for(Pair sample : this.innerDistribution.getPmf()) {
         mean += (Double)sample.getValue() * (Double)sample.getKey();
      }

      return mean;
   }

   public double getNumericalVariance() {
      double mean = (double)0.0F;
      double meanOfSquares = (double)0.0F;

      for(Pair sample : this.innerDistribution.getPmf()) {
         mean += (Double)sample.getValue() * (Double)sample.getKey();
         meanOfSquares += (Double)sample.getValue() * (Double)sample.getKey() * (Double)sample.getKey();
      }

      return meanOfSquares - mean * mean;
   }

   public double getSupportLowerBound() {
      double min = Double.POSITIVE_INFINITY;

      for(Pair sample : this.innerDistribution.getPmf()) {
         if ((Double)sample.getKey() < min && (Double)sample.getValue() > (double)0.0F) {
            min = (Double)sample.getKey();
         }
      }

      return min;
   }

   public double getSupportUpperBound() {
      double max = Double.NEGATIVE_INFINITY;

      for(Pair sample : this.innerDistribution.getPmf()) {
         if ((Double)sample.getKey() > max && (Double)sample.getValue() > (double)0.0F) {
            max = (Double)sample.getKey();
         }
      }

      return max;
   }

   public boolean isSupportLowerBoundInclusive() {
      return true;
   }

   public boolean isSupportUpperBoundInclusive() {
      return true;
   }

   public boolean isSupportConnected() {
      return true;
   }

   public double sample() {
      return (Double)this.innerDistribution.sample();
   }
}
