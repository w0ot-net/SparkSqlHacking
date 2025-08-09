package org.apache.commons.math3.stat.inference;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.util.MathUtils;

public class OneWayAnova {
   public double anovaFValue(Collection categoryData) throws NullArgumentException, DimensionMismatchException {
      AnovaStats a = this.anovaStats(categoryData);
      return a.F;
   }

   public double anovaPValue(Collection categoryData) throws NullArgumentException, DimensionMismatchException, ConvergenceException, MaxCountExceededException {
      AnovaStats a = this.anovaStats(categoryData);
      FDistribution fdist = new FDistribution((RandomGenerator)null, (double)a.dfbg, (double)a.dfwg);
      return (double)1.0F - fdist.cumulativeProbability(a.F);
   }

   public double anovaPValue(Collection categoryData, boolean allowOneElementData) throws NullArgumentException, DimensionMismatchException, ConvergenceException, MaxCountExceededException {
      AnovaStats a = this.anovaStats(categoryData, allowOneElementData);
      FDistribution fdist = new FDistribution((RandomGenerator)null, (double)a.dfbg, (double)a.dfwg);
      return (double)1.0F - fdist.cumulativeProbability(a.F);
   }

   private AnovaStats anovaStats(Collection categoryData) throws NullArgumentException, DimensionMismatchException {
      MathUtils.checkNotNull(categoryData);
      Collection<SummaryStatistics> categoryDataSummaryStatistics = new ArrayList(categoryData.size());

      for(double[] data : categoryData) {
         SummaryStatistics dataSummaryStatistics = new SummaryStatistics();
         categoryDataSummaryStatistics.add(dataSummaryStatistics);

         for(double val : data) {
            dataSummaryStatistics.addValue(val);
         }
      }

      return this.anovaStats(categoryDataSummaryStatistics, false);
   }

   public boolean anovaTest(Collection categoryData, double alpha) throws NullArgumentException, DimensionMismatchException, OutOfRangeException, ConvergenceException, MaxCountExceededException {
      if (!(alpha <= (double)0.0F) && !(alpha > (double)0.5F)) {
         return this.anovaPValue(categoryData) < alpha;
      } else {
         throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, alpha, 0, (double)0.5F);
      }
   }

   private AnovaStats anovaStats(Collection categoryData, boolean allowOneElementData) throws NullArgumentException, DimensionMismatchException {
      MathUtils.checkNotNull(categoryData);
      if (!allowOneElementData) {
         if (categoryData.size() < 2) {
            throw new DimensionMismatchException(LocalizedFormats.TWO_OR_MORE_CATEGORIES_REQUIRED, categoryData.size(), 2);
         }

         for(SummaryStatistics array : categoryData) {
            if (array.getN() <= 1L) {
               throw new DimensionMismatchException(LocalizedFormats.TWO_OR_MORE_VALUES_IN_CATEGORY_REQUIRED, (int)array.getN(), 2);
            }
         }
      }

      int dfwg = 0;
      double sswg = (double)0.0F;
      double totsum = (double)0.0F;
      double totsumsq = (double)0.0F;
      int totnum = 0;

      for(SummaryStatistics data : categoryData) {
         double sum = data.getSum();
         double sumsq = data.getSumsq();
         int num = (int)data.getN();
         totnum += num;
         totsum += sum;
         totsumsq += sumsq;
         dfwg += num - 1;
         double ss = sumsq - sum * sum / (double)num;
         sswg += ss;
      }

      double sst = totsumsq - totsum * totsum / (double)totnum;
      double ssbg = sst - sswg;
      int dfbg = categoryData.size() - 1;
      double msbg = ssbg / (double)dfbg;
      double mswg = sswg / (double)dfwg;
      double F = msbg / mswg;
      return new AnovaStats(dfbg, dfwg, F);
   }

   private static class AnovaStats {
      private final int dfbg;
      private final int dfwg;
      private final double F;

      private AnovaStats(int dfbg, int dfwg, double F) {
         this.dfbg = dfbg;
         this.dfwg = dfwg;
         this.F = F;
      }
   }
}
