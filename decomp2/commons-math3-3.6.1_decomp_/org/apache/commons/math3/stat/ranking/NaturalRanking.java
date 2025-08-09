package org.apache.commons.math3.stat.ranking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

public class NaturalRanking implements RankingAlgorithm {
   public static final NaNStrategy DEFAULT_NAN_STRATEGY;
   public static final TiesStrategy DEFAULT_TIES_STRATEGY;
   private final NaNStrategy nanStrategy;
   private final TiesStrategy tiesStrategy;
   private final RandomDataGenerator randomData;

   public NaturalRanking() {
      this.tiesStrategy = DEFAULT_TIES_STRATEGY;
      this.nanStrategy = DEFAULT_NAN_STRATEGY;
      this.randomData = null;
   }

   public NaturalRanking(TiesStrategy tiesStrategy) {
      this.tiesStrategy = tiesStrategy;
      this.nanStrategy = DEFAULT_NAN_STRATEGY;
      this.randomData = new RandomDataGenerator();
   }

   public NaturalRanking(NaNStrategy nanStrategy) {
      this.nanStrategy = nanStrategy;
      this.tiesStrategy = DEFAULT_TIES_STRATEGY;
      this.randomData = null;
   }

   public NaturalRanking(NaNStrategy nanStrategy, TiesStrategy tiesStrategy) {
      this.nanStrategy = nanStrategy;
      this.tiesStrategy = tiesStrategy;
      this.randomData = new RandomDataGenerator();
   }

   public NaturalRanking(RandomGenerator randomGenerator) {
      this.tiesStrategy = TiesStrategy.RANDOM;
      this.nanStrategy = DEFAULT_NAN_STRATEGY;
      this.randomData = new RandomDataGenerator(randomGenerator);
   }

   public NaturalRanking(NaNStrategy nanStrategy, RandomGenerator randomGenerator) {
      this.nanStrategy = nanStrategy;
      this.tiesStrategy = TiesStrategy.RANDOM;
      this.randomData = new RandomDataGenerator(randomGenerator);
   }

   public NaNStrategy getNanStrategy() {
      return this.nanStrategy;
   }

   public TiesStrategy getTiesStrategy() {
      return this.tiesStrategy;
   }

   public double[] rank(double[] data) {
      IntDoublePair[] ranks = new IntDoublePair[data.length];

      for(int i = 0; i < data.length; ++i) {
         ranks[i] = new IntDoublePair(data[i], i);
      }

      List<Integer> nanPositions = null;
      switch (this.nanStrategy) {
         case MAXIMAL:
            this.recodeNaNs(ranks, Double.POSITIVE_INFINITY);
            break;
         case MINIMAL:
            this.recodeNaNs(ranks, Double.NEGATIVE_INFINITY);
            break;
         case REMOVED:
            ranks = this.removeNaNs(ranks);
            break;
         case FIXED:
            nanPositions = this.getNanPositions(ranks);
            break;
         case FAILED:
            nanPositions = this.getNanPositions(ranks);
            if (nanPositions.size() > 0) {
               throw new NotANumberException();
            }
            break;
         default:
            throw new MathInternalError();
      }

      Arrays.sort(ranks);
      double[] out = new double[ranks.length];
      int pos = 1;
      out[ranks[0].getPosition()] = (double)pos;
      List<Integer> tiesTrace = new ArrayList();
      tiesTrace.add(ranks[0].getPosition());

      for(int i = 1; i < ranks.length; ++i) {
         if (Double.compare(ranks[i].getValue(), ranks[i - 1].getValue()) > 0) {
            pos = i + 1;
            if (tiesTrace.size() > 1) {
               this.resolveTie(out, tiesTrace);
            }

            tiesTrace = new ArrayList();
            tiesTrace.add(ranks[i].getPosition());
         } else {
            tiesTrace.add(ranks[i].getPosition());
         }

         out[ranks[i].getPosition()] = (double)pos;
      }

      if (tiesTrace.size() > 1) {
         this.resolveTie(out, tiesTrace);
      }

      if (this.nanStrategy == NaNStrategy.FIXED) {
         this.restoreNaNs(out, nanPositions);
      }

      return out;
   }

   private IntDoublePair[] removeNaNs(IntDoublePair[] ranks) {
      if (!this.containsNaNs(ranks)) {
         return ranks;
      } else {
         IntDoublePair[] outRanks = new IntDoublePair[ranks.length];
         int j = 0;

         for(int i = 0; i < ranks.length; ++i) {
            if (Double.isNaN(ranks[i].getValue())) {
               for(int k = i + 1; k < ranks.length; ++k) {
                  ranks[k] = new IntDoublePair(ranks[k].getValue(), ranks[k].getPosition() - 1);
               }
            } else {
               outRanks[j] = new IntDoublePair(ranks[i].getValue(), ranks[i].getPosition());
               ++j;
            }
         }

         IntDoublePair[] returnRanks = new IntDoublePair[j];
         System.arraycopy(outRanks, 0, returnRanks, 0, j);
         return returnRanks;
      }
   }

   private void recodeNaNs(IntDoublePair[] ranks, double value) {
      for(int i = 0; i < ranks.length; ++i) {
         if (Double.isNaN(ranks[i].getValue())) {
            ranks[i] = new IntDoublePair(value, ranks[i].getPosition());
         }
      }

   }

   private boolean containsNaNs(IntDoublePair[] ranks) {
      for(int i = 0; i < ranks.length; ++i) {
         if (Double.isNaN(ranks[i].getValue())) {
            return true;
         }
      }

      return false;
   }

   private void resolveTie(double[] ranks, List tiesTrace) {
      double c = ranks[(Integer)tiesTrace.get(0)];
      int length = tiesTrace.size();
      switch (this.tiesStrategy) {
         case AVERAGE:
            this.fill(ranks, tiesTrace, ((double)2.0F * c + (double)length - (double)1.0F) / (double)2.0F);
            break;
         case MAXIMUM:
            this.fill(ranks, tiesTrace, c + (double)length - (double)1.0F);
            break;
         case MINIMUM:
            this.fill(ranks, tiesTrace, c);
            break;
         case RANDOM:
            Iterator<Integer> iterator = tiesTrace.iterator();

            for(long f = FastMath.round(c); iterator.hasNext(); ranks[(Integer)iterator.next()] = (double)this.randomData.nextLong(f, f + (long)length - 1L)) {
            }
            break;
         case SEQUENTIAL:
            Iterator<Integer> iterator = tiesTrace.iterator();
            long f = FastMath.round(c);

            for(int i = 0; iterator.hasNext(); ranks[(Integer)iterator.next()] = (double)(f + (long)(i++))) {
            }
            break;
         default:
            throw new MathInternalError();
      }

   }

   private void fill(double[] data, List tiesTrace, double value) {
      for(Iterator<Integer> iterator = tiesTrace.iterator(); iterator.hasNext(); data[(Integer)iterator.next()] = value) {
      }

   }

   private void restoreNaNs(double[] ranks, List nanPositions) {
      if (nanPositions.size() != 0) {
         for(Iterator<Integer> iterator = nanPositions.iterator(); iterator.hasNext(); ranks[(Integer)iterator.next()] = Double.NaN) {
         }

      }
   }

   private List getNanPositions(IntDoublePair[] ranks) {
      ArrayList<Integer> out = new ArrayList();

      for(int i = 0; i < ranks.length; ++i) {
         if (Double.isNaN(ranks[i].getValue())) {
            out.add(i);
         }
      }

      return out;
   }

   static {
      DEFAULT_NAN_STRATEGY = NaNStrategy.FAILED;
      DEFAULT_TIES_STRATEGY = TiesStrategy.AVERAGE;
   }

   private static class IntDoublePair implements Comparable {
      private final double value;
      private final int position;

      IntDoublePair(double value, int position) {
         this.value = value;
         this.position = position;
      }

      public int compareTo(IntDoublePair other) {
         return Double.compare(this.value, other.value);
      }

      public double getValue() {
         return this.value;
      }

      public int getPosition() {
         return this.position;
      }
   }
}
