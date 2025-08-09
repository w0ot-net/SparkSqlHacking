package org.apache.hadoop.hive.metastore;

import java.util.Random;
import javolution.util.FastBitSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.io.Text;

public class NumDistinctValueEstimator {
   static final Log LOG = LogFactory.getLog(NumDistinctValueEstimator.class.getName());
   private static final int BIT_VECTOR_SIZE = 31;
   private final int numBitVectors;
   private static final double PHI = 0.77351;
   private final int[] a;
   private final int[] b;
   private final FastBitSet[] bitVector;
   private final Random aValue;
   private final Random bValue;

   public NumDistinctValueEstimator(int numBitVectors) {
      this.numBitVectors = numBitVectors;
      this.bitVector = new FastBitSet[numBitVectors];

      for(int i = 0; i < numBitVectors; ++i) {
         this.bitVector[i] = new FastBitSet(31);
      }

      this.a = new int[numBitVectors];
      this.b = new int[numBitVectors];
      this.aValue = new Random(99397L);
      this.bValue = new Random(9876413L);

      for(int i = 0; i < numBitVectors; ++i) {
         int randVal;
         do {
            randVal = this.aValue.nextInt();
         } while(randVal % 2 == 0);

         this.a[i] = randVal;

         do {
            randVal = this.bValue.nextInt();
         } while(randVal % 2 == 0);

         this.b[i] = randVal;
         if (this.a[i] < 0) {
            this.a[i] += 1073741824;
         }

         if (this.b[i] < 0) {
            this.b[i] += 1073741824;
         }
      }

   }

   public NumDistinctValueEstimator(String s, int numBitVectors) {
      this.numBitVectors = numBitVectors;
      FastBitSet[] bitVectorDeser = this.deserialize(s, numBitVectors);
      this.bitVector = new FastBitSet[numBitVectors];

      for(int i = 0; i < numBitVectors; ++i) {
         this.bitVector[i] = new FastBitSet(31);
         this.bitVector[i].clear();
         this.bitVector[i].or(bitVectorDeser[i]);
      }

      this.a = null;
      this.b = null;
      this.aValue = null;
      this.bValue = null;
   }

   public void reset() {
      for(int i = 0; i < this.numBitVectors; ++i) {
         this.bitVector[i].clear();
      }

   }

   public FastBitSet getBitVector(int index) {
      return this.bitVector[index];
   }

   public int getnumBitVectors() {
      return this.numBitVectors;
   }

   public int getBitVectorSize() {
      return 31;
   }

   public void printNumDistinctValueEstimator() {
      String t = new String();
      LOG.debug("NumDistinctValueEstimator");
      LOG.debug("Number of Vectors:");
      LOG.debug(this.numBitVectors);
      LOG.debug("Vector Size: ");
      LOG.debug(31);

      for(int i = 0; i < this.numBitVectors; ++i) {
         t = t + this.bitVector[i].toString();
      }

      LOG.debug("Serialized Vectors: ");
      LOG.debug(t);
   }

   public Text serialize() {
      String s = new String();

      for(int i = 0; i < this.numBitVectors; ++i) {
         s = s + this.bitVector[i].toString();
      }

      return new Text(s);
   }

   private FastBitSet[] deserialize(String s, int numBitVectors) {
      FastBitSet[] b = new FastBitSet[numBitVectors];

      for(int j = 0; j < numBitVectors; ++j) {
         b[j] = new FastBitSet(31);
         b[j].clear();
      }

      int vectorIndex = 0;
      int i = 1;

      while(i < s.length() - 1) {
         char c = s.charAt(i);
         ++i;
         if (c == '}') {
            ++vectorIndex;
         }

         if (c >= '0' && c <= '9') {
            String t = new String();
            t = t + c;
            c = s.charAt(i);
            ++i;

            while(c != ',' && c != '}') {
               t = t + c;
               c = s.charAt(i);
               ++i;
            }

            int bitIndex = Integer.parseInt(t);

            assert bitIndex >= 0;

            assert vectorIndex < numBitVectors;

            b[vectorIndex].set(bitIndex);
            if (c == '}') {
               ++vectorIndex;
            }
         }
      }

      return b;
   }

   private int generateHash(long v, int hashNum) {
      int mod = Integer.MAX_VALUE;
      long tempHash = (long)this.a[hashNum] * v + (long)this.b[hashNum];
      tempHash %= (long)mod;
      int hash = (int)tempHash;
      if (hash < 0) {
         hash += mod;
      }

      return hash;
   }

   private int generateHashForPCSA(long v) {
      return this.generateHash(v, 0);
   }

   public void addToEstimator(long v) {
      for(int i = 0; i < this.numBitVectors; ++i) {
         int hash = this.generateHash(v, i);

         int index;
         for(index = 0; index < 31 && hash % 2 == 0; ++index) {
            hash >>= 1;
         }

         this.bitVector[i].set(index);
      }

   }

   public void addToEstimatorPCSA(long v) {
      int hash = this.generateHashForPCSA(v);
      int rho = hash / this.numBitVectors;

      int index;
      for(index = 0; index < 31 && rho % 2 == 0; ++index) {
         rho >>= 1;
      }

      this.bitVector[hash % this.numBitVectors].set(index);
   }

   public void addToEstimator(double d) {
      int v = (new Double(d)).hashCode();
      this.addToEstimator((long)v);
   }

   public void addToEstimatorPCSA(double d) {
      int v = (new Double(d)).hashCode();
      this.addToEstimatorPCSA((long)v);
   }

   public void addToEstimator(HiveDecimal decimal) {
      int v = decimal.hashCode();
      this.addToEstimator((long)v);
   }

   public void addToEstimatorPCSA(HiveDecimal decimal) {
      int v = decimal.hashCode();
      this.addToEstimatorPCSA((long)v);
   }

   public void mergeEstimators(NumDistinctValueEstimator o) {
      for(int i = 0; i < this.numBitVectors; ++i) {
         this.bitVector[i].or(o.getBitVector(i));
      }

   }

   public long estimateNumDistinctValuesPCSA() {
      double numDistinctValues = (double)0.0F;
      long S = 0L;

      for(int i = 0; i < this.numBitVectors; ++i) {
         int index;
         for(index = 0; this.bitVector[i].get(index) && index < 31; ++index) {
         }

         S += (long)index;
      }

      numDistinctValues = (double)this.numBitVectors / 0.77351 * Math.pow((double)2.0F, (double)(S / (long)this.numBitVectors));
      return (long)numDistinctValues;
   }

   public long estimateNumDistinctValues() {
      int sumLeastSigZero = 0;

      for(int i = 0; i < this.numBitVectors; ++i) {
         int leastSigZero = this.bitVector[i].nextClearBit(0);
         sumLeastSigZero += leastSigZero;
      }

      double avgLeastSigZero = (double)sumLeastSigZero / ((double)this.numBitVectors * (double)1.0F) - Math.log(0.77351) / Math.log((double)2.0F);
      double numDistinctValues = Math.pow((double)2.0F, avgLeastSigZero);
      return (long)numDistinctValues;
   }
}
