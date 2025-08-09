package org.apache.datasketches.req;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.quantilescommon.FloatsSketchSortedView;
import org.apache.datasketches.quantilescommon.QuantileSearchCriteria;
import org.apache.datasketches.quantilescommon.QuantilesFloatsSketchIterator;

public final class ReqSketch extends BaseReqSketch {
   static final byte MIN_K = 4;
   static final byte NOM_CAP_MULT = 2;
   private final int k;
   private final boolean hra;
   private long totalN = 0L;
   private float minItem = Float.NaN;
   private float maxItem = Float.NaN;
   private int retItems = 0;
   private int maxNomSize = 0;
   private FloatsSketchSortedView reqSV = null;
   private List compactors = new ArrayList();
   private ReqDebug reqDebug = null;
   private final CompactorReturn cReturn = new CompactorReturn();
   private final Random rand;

   ReqSketch(int k, boolean hra, long totalN, float minItem, float maxItem, List compactors) {
      checkK(k);
      this.k = k;
      this.hra = hra;
      this.totalN = totalN;
      this.minItem = minItem;
      this.maxItem = maxItem;
      this.compactors = compactors;
      this.rand = new Random();
   }

   ReqSketch(int k, boolean highRankAccuracy, ReqDebug reqDebug) {
      checkK(k);
      this.k = k;
      this.hra = highRankAccuracy;
      this.reqDebug = reqDebug;
      this.rand = reqDebug == null ? new Random() : new Random(1L);
      this.grow();
   }

   ReqSketch(ReqSketch other) {
      this.k = other.k;
      this.hra = other.hra;
      this.totalN = other.totalN;
      this.retItems = other.retItems;
      this.maxNomSize = other.maxNomSize;
      this.minItem = other.minItem;
      this.maxItem = other.maxItem;
      this.reqDebug = other.reqDebug;
      this.reqSV = null;
      this.rand = this.reqDebug == null ? new Random() : new Random(1L);

      for(int i = 0; i < other.getNumLevels(); ++i) {
         this.compactors.add(new ReqCompactor((ReqCompactor)other.compactors.get(i)));
      }

   }

   public static final ReqSketchBuilder builder() {
      return new ReqSketchBuilder();
   }

   public static ReqSketch heapify(Memory mem) {
      return ReqSerDe.heapify(mem);
   }

   public int getK() {
      return this.k;
   }

   static void validateSplits(float[] splits) {
      int len = splits.length;

      for(int i = 0; i < len; ++i) {
         float v = splits[i];
         if (!Float.isFinite(v)) {
            throw new SketchesArgumentException("Numbers must be finite");
         }

         if (i < len - 1 && v >= splits[i + 1]) {
            throw new SketchesArgumentException("Numbers must be unique and monotonically increasing");
         }
      }

   }

   public double[] getCDF(float[] splitPoints, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.reqSV.getCDF(splitPoints, searchCrit);
      }
   }

   public boolean getHighRankAccuracyMode() {
      return this.hra;
   }

   public float getMaxItem() {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         return this.maxItem;
      }
   }

   public float getMinItem() {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         return this.minItem;
      }
   }

   public long getN() {
      return this.totalN;
   }

   public double getNormalizedRankError(boolean pmf) {
      throw new UnsupportedOperationException("Unsupported operation for this Sketch Type. ");
   }

   public double[] getPMF(float[] splitPoints, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.reqSV.getPMF(splitPoints, searchCrit);
      }
   }

   public float getQuantile(double normRank, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else if (!(normRank < (double)0.0F) && !(normRank > (double)1.0F)) {
         this.refreshSortedView();
         return this.reqSV.getQuantile(normRank, searchCrit);
      } else {
         throw new SketchesArgumentException("Normalized rank must be in the range [0.0, 1.0]: " + normRank);
      }
   }

   public float[] getQuantiles(double[] normRanks, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         int len = normRanks.length;
         float[] qArr = new float[len];

         for(int i = 0; i < len; ++i) {
            qArr[i] = this.reqSV.getQuantile(normRanks[i], searchCrit);
         }

         return qArr;
      }
   }

   public float getQuantileLowerBound(double rank) {
      return this.getQuantile(this.getRankLowerBound(rank, 2), QuantileSearchCriteria.INCLUSIVE);
   }

   public float getQuantileLowerBound(double rank, int numStdDev) {
      return this.getQuantile(this.getRankLowerBound(rank, numStdDev), QuantileSearchCriteria.INCLUSIVE);
   }

   public float getQuantileUpperBound(double rank) {
      return this.getQuantile(this.getRankUpperBound(rank, 2), QuantileSearchCriteria.INCLUSIVE);
   }

   public float getQuantileUpperBound(double rank, int numStdDev) {
      return this.getQuantile(this.getRankUpperBound(rank, numStdDev), QuantileSearchCriteria.INCLUSIVE);
   }

   public double getRank(float quantile, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.reqSV.getRank(quantile, searchCrit);
      }
   }

   public double getRankLowerBound(double rank) {
      return getRankLB(this.k, this.getNumLevels(), rank, 2, this.hra, this.getN());
   }

   public double getRankLowerBound(double rank, int numStdDev) {
      return getRankLB(this.k, this.getNumLevels(), rank, numStdDev, this.hra, this.getN());
   }

   public double[] getRanks(float[] quantiles, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         int numQuantiles = quantiles.length;
         double[] retArr = new double[numQuantiles];

         for(int i = 0; i < numQuantiles; ++i) {
            retArr[i] = this.reqSV.getRank(quantiles[i], searchCrit);
         }

         return retArr;
      }
   }

   public double getRankUpperBound(double rank) {
      return getRankUB(this.k, this.getNumLevels(), rank, 2, this.hra, this.getN());
   }

   public double getRankUpperBound(double rank, int numStdDev) {
      return getRankUB(this.k, this.getNumLevels(), rank, numStdDev, this.hra, this.getN());
   }

   public int getNumRetained() {
      return this.retItems;
   }

   public int getSerializedSizeBytes() {
      ReqSerDe.SerDeFormat serDeFormat = ReqSerDe.getSerFormat(this);
      return ReqSerDe.getSerBytes(this, serDeFormat);
   }

   public boolean isEmpty() {
      return this.totalN == 0L;
   }

   public boolean isEstimationMode() {
      return this.getNumLevels() > 1;
   }

   public QuantilesFloatsSketchIterator iterator() {
      return new ReqSketchIterator(this);
   }

   public ReqSketch merge(ReqSketch other) {
      if (other != null && !other.isEmpty()) {
         if (other.hra != this.hra) {
            throw new SketchesArgumentException("Both sketches must have the same HighRankAccuracy setting.");
         } else {
            this.totalN += other.totalN;
            if (Float.isNaN(this.minItem) || other.minItem < this.minItem) {
               this.minItem = other.minItem;
            }

            if (Float.isNaN(this.maxItem) || other.maxItem > this.maxItem) {
               this.maxItem = other.maxItem;
            }

            while(this.getNumLevels() < other.getNumLevels()) {
               this.grow();
            }

            for(int i = 0; i < other.getNumLevels(); ++i) {
               ((ReqCompactor)this.compactors.get(i)).merge((ReqCompactor)other.compactors.get(i));
            }

            this.maxNomSize = this.computeMaxNomSize();
            this.retItems = this.computeTotalRetainedItems();
            if (this.retItems >= this.maxNomSize) {
               this.compress();
            }

            assert this.retItems < this.maxNomSize;

            this.reqSV = null;
            return this;
         }
      } else {
         return this;
      }
   }

   public void reset() {
      this.totalN = 0L;
      this.retItems = 0;
      this.maxNomSize = 0;
      this.minItem = Float.NaN;
      this.maxItem = Float.NaN;
      this.reqSV = null;
      this.compactors = new ArrayList();
      this.grow();
   }

   public byte[] toByteArray() {
      return ReqSerDe.toByteArray(this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("**********Relative Error Quantiles Sketch Summary**********").append(Util.LS);
      sb.append("  K               : " + this.k).append(Util.LS);
      sb.append("  N               : " + this.totalN).append(Util.LS);
      sb.append("  Retained Items  : " + this.retItems).append(Util.LS);
      sb.append("  Min Item        : " + this.minItem).append(Util.LS);
      sb.append("  Max Item        : " + this.maxItem).append(Util.LS);
      sb.append("  Estimation Mode : " + this.isEstimationMode()).append(Util.LS);
      sb.append("  High Rank Acc   : " + this.hra).append(Util.LS);
      sb.append("  Levels          : " + this.compactors.size()).append(Util.LS);
      sb.append("************************End Summary************************").append(Util.LS);
      return sb.toString();
   }

   public void update(float item) {
      if (!Float.isNaN(item)) {
         if (this.isEmpty()) {
            this.minItem = item;
            this.maxItem = item;
         } else {
            if (item < this.minItem) {
               this.minItem = item;
            }

            if (item > this.maxItem) {
               this.maxItem = item;
            }
         }

         FloatBuffer buf = ((ReqCompactor)this.compactors.get(0)).getBuffer();
         buf.append(item);
         ++this.retItems;
         ++this.totalN;
         if (this.retItems >= this.maxNomSize) {
            buf.sort();
            this.compress();
         }

         this.reqSV = null;
      }
   }

   public String viewCompactorDetail(String fmt, boolean allData) {
      StringBuilder sb = new StringBuilder();
      sb.append("*********Relative Error Quantiles Compactor Detail*********").append(Util.LS);
      sb.append("Compactor Detail: Ret Items: ").append(this.getNumRetained()).append("  N: ").append(this.getN());
      sb.append(Util.LS);

      for(int i = 0; i < this.getNumLevels(); ++i) {
         ReqCompactor c = (ReqCompactor)this.compactors.get(i);
         sb.append(c.toListPrefix()).append(Util.LS);
         if (allData) {
            sb.append(c.getBuffer().toHorizList(fmt, 20)).append(Util.LS);
         }
      }

      sb.append("************************End Detail*************************").append(Util.LS);
      return sb.toString();
   }

   int computeMaxNomSize() {
      int cap = 0;

      for(ReqCompactor c : this.compactors) {
         cap += c.getNomCapacity();
      }

      return cap;
   }

   int computeTotalRetainedItems() {
      int count = 0;

      for(ReqCompactor c : this.compactors) {
         count += c.getBuffer().getCount();
      }

      return count;
   }

   List getCompactors() {
      return this.compactors;
   }

   int getMaxNomSize() {
      return this.maxNomSize;
   }

   int getNumLevels() {
      return this.compactors.size();
   }

   void setMaxNomSize(int maxNomSize) {
      this.maxNomSize = maxNomSize;
   }

   void setRetainedItems(int retItems) {
      this.retItems = retItems;
   }

   private static void checkK(int k) {
      if ((k & 1) > 0 || k < 4 || k > 1024) {
         throw new SketchesArgumentException("<i>K</i> must be even and in the range [4, 1024]: " + k);
      }
   }

   private void compress() {
      if (this.reqDebug != null) {
         this.reqDebug.emitStartCompress();
      }

      for(int h = 0; h < this.compactors.size(); ++h) {
         ReqCompactor c = (ReqCompactor)this.compactors.get(h);
         int compRetItems = c.getBuffer().getCount();
         int compNomCap = c.getNomCapacity();
         if (compRetItems >= compNomCap) {
            if (h + 1 >= this.getNumLevels()) {
               if (this.reqDebug != null) {
                  this.reqDebug.emitMustAddCompactor();
               }

               this.grow();
            }

            FloatBuffer promoted = c.compact(this.cReturn, this.rand);
            ((ReqCompactor)this.compactors.get(h + 1)).getBuffer().mergeSortIn(promoted);
            this.retItems += this.cReturn.deltaRetItems;
            this.maxNomSize += this.cReturn.deltaNomSize;
         }
      }

      this.reqSV = null;
      if (this.reqDebug != null) {
         this.reqDebug.emitCompressDone();
      }

   }

   private void grow() {
      byte lgWeight = (byte)this.getNumLevels();
      if (lgWeight == 0 && this.reqDebug != null) {
         this.reqDebug.emitStart(this);
      }

      this.compactors.add(new ReqCompactor(lgWeight, this.hra, this.k, this.reqDebug));
      this.maxNomSize = this.computeMaxNomSize();
      if (this.reqDebug != null) {
         this.reqDebug.emitNewCompactor(lgWeight);
      }

   }

   public FloatsSketchSortedView getSortedView() {
      this.refreshSortedView();
      return this.reqSV;
   }

   private final FloatsSketchSortedView refreshSortedView() {
      if (this.reqSV == null) {
         CreateSortedView csv = new CreateSortedView();
         this.reqSV = csv.getSV();
      }

      return this.reqSV;
   }

   static class CompactorReturn {
      int deltaRetItems;
      int deltaNomSize;
   }

   private final class CreateSortedView {
      float[] quantiles;
      long[] cumWeights;

      private CreateSortedView() {
      }

      FloatsSketchSortedView getSV() {
         if (ReqSketch.this.isEmpty()) {
            throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
         } else {
            List<ReqCompactor> compactors = ReqSketch.this.getCompactors();
            int numComp = compactors.size();
            int totalQuantiles = ReqSketch.this.getNumRetained();
            this.quantiles = new float[totalQuantiles];
            this.cumWeights = new long[totalQuantiles];
            int count = 0;

            for(int i = 0; i < numComp; ++i) {
               ReqCompactor c = (ReqCompactor)compactors.get(i);
               FloatBuffer bufIn = c.getBuffer();
               long bufWeight = (long)(1 << c.getLgWeight());
               int bufInLen = bufIn.getCount();
               this.mergeSortIn(bufIn, bufWeight, count, ReqSketch.this.getHighRankAccuracyMode());
               count += bufInLen;
            }

            this.createCumulativeNativeRanks();
            return new FloatsSketchSortedView(this.quantiles, this.cumWeights, ReqSketch.this);
         }
      }

      private void mergeSortIn(FloatBuffer bufIn, long bufWeight, int count, boolean hra) {
         if (!bufIn.isSorted()) {
            bufIn.sort();
         }

         float[] arrIn = bufIn.getArray();
         int bufInLen = bufIn.getCount();
         int totLen = count + bufInLen;
         int i = count - 1;
         int j = bufInLen - 1;
         int h = hra ? bufIn.getCapacity() - 1 : bufInLen - 1;
         int k = totLen;

         while(k-- > 0) {
            if (i >= 0 && j >= 0) {
               if (this.quantiles[i] >= arrIn[h]) {
                  this.quantiles[k] = this.quantiles[i];
                  this.cumWeights[k] = this.cumWeights[i--];
               } else {
                  this.quantiles[k] = arrIn[h--];
                  --j;
                  this.cumWeights[k] = bufWeight;
               }
            } else if (i >= 0) {
               this.quantiles[k] = this.quantiles[i];
               this.cumWeights[k] = this.cumWeights[i--];
            } else {
               if (j < 0) {
                  break;
               }

               this.quantiles[k] = arrIn[h--];
               --j;
               this.cumWeights[k] = bufWeight;
            }
         }

      }

      private void createCumulativeNativeRanks() {
         int len = this.quantiles.length;

         for(int i = 1; i < len; ++i) {
            long[] var10000 = this.cumWeights;
            var10000[i] += this.cumWeights[i - 1];
         }

         assert ReqSketch.this.totalN <= 0L || this.cumWeights[len - 1] == ReqSketch.this.totalN;

      }
   }
}
