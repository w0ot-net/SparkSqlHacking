package org.apache.commons.math3.random;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.ConstantRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class EmpiricalDistribution extends AbstractRealDistribution {
   public static final int DEFAULT_BIN_COUNT = 1000;
   private static final String FILE_CHARSET = "US-ASCII";
   private static final long serialVersionUID = 5729073523949762654L;
   protected final RandomDataGenerator randomData;
   private final List binStats;
   private SummaryStatistics sampleStats;
   private double max;
   private double min;
   private double delta;
   private final int binCount;
   private boolean loaded;
   private double[] upperBounds;

   public EmpiricalDistribution() {
      this(1000);
   }

   public EmpiricalDistribution(int binCount) {
      this(binCount, new RandomDataGenerator());
   }

   public EmpiricalDistribution(int binCount, RandomGenerator generator) {
      this(binCount, new RandomDataGenerator(generator));
   }

   public EmpiricalDistribution(RandomGenerator generator) {
      this(1000, (RandomGenerator)generator);
   }

   /** @deprecated */
   @Deprecated
   public EmpiricalDistribution(int binCount, RandomDataImpl randomData) {
      this(binCount, randomData.getDelegate());
   }

   /** @deprecated */
   @Deprecated
   public EmpiricalDistribution(RandomDataImpl randomData) {
      this(1000, (RandomDataImpl)randomData);
   }

   private EmpiricalDistribution(int binCount, RandomDataGenerator randomData) {
      super(randomData.getRandomGenerator());
      this.sampleStats = null;
      this.max = Double.NEGATIVE_INFINITY;
      this.min = Double.POSITIVE_INFINITY;
      this.delta = (double)0.0F;
      this.loaded = false;
      this.upperBounds = null;
      if (binCount <= 0) {
         throw new NotStrictlyPositiveException(binCount);
      } else {
         this.binCount = binCount;
         this.randomData = randomData;
         this.binStats = new ArrayList();
      }
   }

   public void load(double[] in) throws NullArgumentException {
      DataAdapter da = new ArrayDataAdapter(in);

      try {
         da.computeStats();
         this.fillBinStats(new ArrayDataAdapter(in));
      } catch (IOException var4) {
         throw new MathInternalError();
      }

      this.loaded = true;
   }

   public void load(URL url) throws IOException, NullArgumentException, ZeroException {
      MathUtils.checkNotNull(url);
      Charset charset = Charset.forName("US-ASCII");
      BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), charset));

      try {
         DataAdapter da = new StreamDataAdapter(in);
         da.computeStats();
         if (this.sampleStats.getN() == 0L) {
            throw new ZeroException(LocalizedFormats.URL_CONTAINS_NO_DATA, new Object[]{url});
         }

         in = new BufferedReader(new InputStreamReader(url.openStream(), charset));
         this.fillBinStats(new StreamDataAdapter(in));
         this.loaded = true;
      } finally {
         try {
            in.close();
         } catch (IOException var10) {
         }

      }

   }

   public void load(File file) throws IOException, NullArgumentException {
      MathUtils.checkNotNull(file);
      Charset charset = Charset.forName("US-ASCII");
      InputStream is = new FileInputStream(file);
      BufferedReader in = new BufferedReader(new InputStreamReader(is, charset));

      try {
         DataAdapter da = new StreamDataAdapter(in);
         da.computeStats();
         InputStream var13 = new FileInputStream(file);
         in = new BufferedReader(new InputStreamReader(var13, charset));
         this.fillBinStats(new StreamDataAdapter(in));
         this.loaded = true;
      } finally {
         try {
            in.close();
         } catch (IOException var11) {
         }

      }

   }

   private void fillBinStats(DataAdapter da) throws IOException {
      this.min = this.sampleStats.getMin();
      this.max = this.sampleStats.getMax();
      this.delta = (this.max - this.min) / (double)this.binCount;
      if (!this.binStats.isEmpty()) {
         this.binStats.clear();
      }

      for(int i = 0; i < this.binCount; ++i) {
         SummaryStatistics stats = new SummaryStatistics();
         this.binStats.add(i, stats);
      }

      da.computeBinStats();
      this.upperBounds = new double[this.binCount];
      this.upperBounds[0] = (double)((SummaryStatistics)this.binStats.get(0)).getN() / (double)this.sampleStats.getN();

      for(int i = 1; i < this.binCount - 1; ++i) {
         this.upperBounds[i] = this.upperBounds[i - 1] + (double)((SummaryStatistics)this.binStats.get(i)).getN() / (double)this.sampleStats.getN();
      }

      this.upperBounds[this.binCount - 1] = (double)1.0F;
   }

   private int findBin(double value) {
      return FastMath.min(FastMath.max((int)FastMath.ceil((value - this.min) / this.delta) - 1, 0), this.binCount - 1);
   }

   public double getNextValue() throws MathIllegalStateException {
      if (!this.loaded) {
         throw new MathIllegalStateException(LocalizedFormats.DISTRIBUTION_NOT_LOADED, new Object[0]);
      } else {
         return this.sample();
      }
   }

   public StatisticalSummary getSampleStats() {
      return this.sampleStats;
   }

   public int getBinCount() {
      return this.binCount;
   }

   public List getBinStats() {
      return this.binStats;
   }

   public double[] getUpperBounds() {
      double[] binUpperBounds = new double[this.binCount];

      for(int i = 0; i < this.binCount - 1; ++i) {
         binUpperBounds[i] = this.min + this.delta * (double)(i + 1);
      }

      binUpperBounds[this.binCount - 1] = this.max;
      return binUpperBounds;
   }

   public double[] getGeneratorUpperBounds() {
      int len = this.upperBounds.length;
      double[] out = new double[len];
      System.arraycopy(this.upperBounds, 0, out, 0, len);
      return out;
   }

   public boolean isLoaded() {
      return this.loaded;
   }

   public void reSeed(long seed) {
      this.randomData.reSeed(seed);
   }

   public double probability(double x) {
      return (double)0.0F;
   }

   public double density(double x) {
      if (!(x < this.min) && !(x > this.max)) {
         int binIndex = this.findBin(x);
         RealDistribution kernel = this.getKernel((SummaryStatistics)this.binStats.get(binIndex));
         return kernel.density(x) * this.pB(binIndex) / this.kB(binIndex);
      } else {
         return (double)0.0F;
      }
   }

   public double cumulativeProbability(double x) {
      if (x < this.min) {
         return (double)0.0F;
      } else if (x >= this.max) {
         return (double)1.0F;
      } else {
         int binIndex = this.findBin(x);
         double pBminus = this.pBminus(binIndex);
         double pB = this.pB(binIndex);
         RealDistribution kernel = this.k(x);
         if (kernel instanceof ConstantRealDistribution) {
            return x < kernel.getNumericalMean() ? pBminus : pBminus + pB;
         } else {
            double[] binBounds = this.getUpperBounds();
            double kB = this.kB(binIndex);
            double lower = binIndex == 0 ? this.min : binBounds[binIndex - 1];
            double withinBinCum = (kernel.cumulativeProbability(x) - kernel.cumulativeProbability(lower)) / kB;
            return pBminus + pB * withinBinCum;
         }
      }
   }

   public double inverseCumulativeProbability(double p) throws OutOfRangeException {
      if (!(p < (double)0.0F) && !(p > (double)1.0F)) {
         if (p == (double)0.0F) {
            return this.getSupportLowerBound();
         } else if (p == (double)1.0F) {
            return this.getSupportUpperBound();
         } else {
            int i;
            for(i = 0; this.cumBinP(i) < p; ++i) {
            }

            RealDistribution kernel = this.getKernel((SummaryStatistics)this.binStats.get(i));
            double kB = this.kB(i);
            double[] binBounds = this.getUpperBounds();
            double lower = i == 0 ? this.min : binBounds[i - 1];
            double kBminus = kernel.cumulativeProbability(lower);
            double pB = this.pB(i);
            double pBminus = this.pBminus(i);
            double pCrit = p - pBminus;
            return pCrit <= (double)0.0F ? lower : kernel.inverseCumulativeProbability(kBminus + pCrit * kB / pB);
         }
      } else {
         throw new OutOfRangeException(p, 0, 1);
      }
   }

   public double getNumericalMean() {
      return this.sampleStats.getMean();
   }

   public double getNumericalVariance() {
      return this.sampleStats.getVariance();
   }

   public double getSupportLowerBound() {
      return this.min;
   }

   public double getSupportUpperBound() {
      return this.max;
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

   public void reseedRandomGenerator(long seed) {
      this.randomData.reSeed(seed);
   }

   private double pB(int i) {
      return i == 0 ? this.upperBounds[0] : this.upperBounds[i] - this.upperBounds[i - 1];
   }

   private double pBminus(int i) {
      return i == 0 ? (double)0.0F : this.upperBounds[i - 1];
   }

   private double kB(int i) {
      double[] binBounds = this.getUpperBounds();
      RealDistribution kernel = this.getKernel((SummaryStatistics)this.binStats.get(i));
      return i == 0 ? kernel.cumulativeProbability(this.min, binBounds[0]) : kernel.cumulativeProbability(binBounds[i - 1], binBounds[i]);
   }

   private RealDistribution k(double x) {
      int binIndex = this.findBin(x);
      return this.getKernel((SummaryStatistics)this.binStats.get(binIndex));
   }

   private double cumBinP(int binIndex) {
      return this.upperBounds[binIndex];
   }

   protected RealDistribution getKernel(SummaryStatistics bStats) {
      return (RealDistribution)(bStats.getN() != 1L && bStats.getVariance() != (double)0.0F ? new NormalDistribution(this.randomData.getRandomGenerator(), bStats.getMean(), bStats.getStandardDeviation(), 1.0E-9) : new ConstantRealDistribution(bStats.getMean()));
   }

   private abstract class DataAdapter {
      private DataAdapter() {
      }

      public abstract void computeBinStats() throws IOException;

      public abstract void computeStats() throws IOException;
   }

   private class StreamDataAdapter extends DataAdapter {
      private BufferedReader inputStream;

      StreamDataAdapter(BufferedReader in) {
         this.inputStream = in;
      }

      public void computeBinStats() throws IOException {
         String str = null;
         double val = (double)0.0F;

         while((str = this.inputStream.readLine()) != null) {
            val = Double.parseDouble(str);
            SummaryStatistics stats = (SummaryStatistics)EmpiricalDistribution.this.binStats.get(EmpiricalDistribution.this.findBin(val));
            stats.addValue(val);
         }

         this.inputStream.close();
         this.inputStream = null;
      }

      public void computeStats() throws IOException {
         String str = null;
         double val = (double)0.0F;
         EmpiricalDistribution.this.sampleStats = new SummaryStatistics();

         while((str = this.inputStream.readLine()) != null) {
            val = Double.parseDouble(str);
            EmpiricalDistribution.this.sampleStats.addValue(val);
         }

         this.inputStream.close();
         this.inputStream = null;
      }
   }

   private class ArrayDataAdapter extends DataAdapter {
      private double[] inputArray;

      ArrayDataAdapter(double[] in) throws NullArgumentException {
         MathUtils.checkNotNull(in);
         this.inputArray = in;
      }

      public void computeStats() throws IOException {
         EmpiricalDistribution.this.sampleStats = new SummaryStatistics();

         for(int i = 0; i < this.inputArray.length; ++i) {
            EmpiricalDistribution.this.sampleStats.addValue(this.inputArray[i]);
         }

      }

      public void computeBinStats() throws IOException {
         for(int i = 0; i < this.inputArray.length; ++i) {
            SummaryStatistics stats = (SummaryStatistics)EmpiricalDistribution.this.binStats.get(EmpiricalDistribution.this.findBin(this.inputArray[i]));
            stats.addValue(this.inputArray[i]);
         }

      }
   }
}
