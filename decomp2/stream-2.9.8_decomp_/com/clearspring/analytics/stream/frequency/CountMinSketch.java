package com.clearspring.analytics.stream.frequency;

import com.clearspring.analytics.stream.membership.Filter;
import com.clearspring.analytics.util.Preconditions;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class CountMinSketch implements IFrequency, Serializable {
   public static final long PRIME_MODULUS = 2147483647L;
   private static final long serialVersionUID = -5084982213094657923L;
   int depth;
   int width;
   long[][] table;
   long[] hashA;
   long size;
   double eps;
   double confidence;

   CountMinSketch() {
   }

   public CountMinSketch(int depth, int width, int seed) {
      this.depth = depth;
      this.width = width;
      this.eps = (double)2.0F / (double)width;
      this.confidence = (double)1.0F - (double)1.0F / Math.pow((double)2.0F, (double)depth);
      this.initTablesWith(depth, width, seed);
   }

   public CountMinSketch(double epsOfTotalCount, double confidence, int seed) {
      this.eps = epsOfTotalCount;
      this.confidence = confidence;
      this.width = (int)Math.ceil((double)2.0F / epsOfTotalCount);
      this.depth = (int)Math.ceil(-Math.log((double)1.0F - confidence) / Math.log((double)2.0F));
      this.initTablesWith(this.depth, this.width, seed);
   }

   CountMinSketch(int depth, int width, long size, long[] hashA, long[][] table) {
      this.depth = depth;
      this.width = width;
      this.eps = (double)2.0F / (double)width;
      this.confidence = (double)1.0F - (double)1.0F / Math.pow((double)2.0F, (double)depth);
      this.hashA = hashA;
      this.table = table;
      Preconditions.checkState(size >= 0L, "The size cannot be smaller than ZER0: " + size);
      this.size = size;
   }

   public String toString() {
      return "CountMinSketch{eps=" + this.eps + ", confidence=" + this.confidence + ", depth=" + this.depth + ", width=" + this.width + ", size=" + this.size + '}';
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         CountMinSketch that = (CountMinSketch)o;
         if (this.depth != that.depth) {
            return false;
         } else if (this.width != that.width) {
            return false;
         } else if (Double.compare(that.eps, this.eps) != 0) {
            return false;
         } else if (Double.compare(that.confidence, this.confidence) != 0) {
            return false;
         } else if (this.size != that.size) {
            return false;
         } else {
            return !Arrays.deepEquals(this.table, that.table) ? false : Arrays.equals(this.hashA, that.hashA);
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = this.depth;
      result = 31 * result + this.width;
      result = 31 * result + Arrays.deepHashCode(this.table);
      result = 31 * result + Arrays.hashCode(this.hashA);
      result = 31 * result + (int)(this.size ^ this.size >>> 32);
      long temp = Double.doubleToLongBits(this.eps);
      result = 31 * result + (int)(temp ^ temp >>> 32);
      temp = Double.doubleToLongBits(this.confidence);
      result = 31 * result + (int)(temp ^ temp >>> 32);
      return result;
   }

   private void initTablesWith(int depth, int width, int seed) {
      this.table = new long[depth][width];
      this.hashA = new long[depth];
      Random r = new Random((long)seed);

      for(int i = 0; i < depth; ++i) {
         this.hashA[i] = (long)r.nextInt(Integer.MAX_VALUE);
      }

   }

   public double getRelativeError() {
      return this.eps;
   }

   public double getConfidence() {
      return this.confidence;
   }

   int hash(long item, int i) {
      long hash = this.hashA[i] * item;
      hash += hash >> 32;
      hash &= 2147483647L;
      return (int)hash % this.width;
   }

   private static void checkSizeAfterOperation(long previousSize, String operation, long newSize) {
      if (newSize < previousSize) {
         throw new IllegalStateException("Overflow error: the size after calling `" + operation + "` is smaller than the previous size. Previous size: " + previousSize + ", New size: " + newSize);
      }
   }

   private void checkSizeAfterAdd(String item, long count) {
      long previousSize = this.size;
      this.size += count;
      checkSizeAfterOperation(previousSize, "add(" + item + "," + count + ")", this.size);
   }

   public void add(long item, long count) {
      if (count < 0L) {
         throw new IllegalArgumentException("Negative increments not implemented");
      } else {
         for(int i = 0; i < this.depth; ++i) {
            long[] var10000 = this.table[i];
            int var10001 = this.hash(item, i);
            var10000[var10001] += count;
         }

         this.checkSizeAfterAdd(String.valueOf(item), count);
      }
   }

   public void add(String item, long count) {
      if (count < 0L) {
         throw new IllegalArgumentException("Negative increments not implemented");
      } else {
         int[] buckets = Filter.getHashBuckets(item, this.depth, this.width);

         for(int i = 0; i < this.depth; ++i) {
            long[] var10000 = this.table[i];
            var10000[buckets[i]] += count;
         }

         this.checkSizeAfterAdd(item, count);
      }
   }

   public long size() {
      return this.size;
   }

   public long estimateCount(long item) {
      long res = Long.MAX_VALUE;

      for(int i = 0; i < this.depth; ++i) {
         res = Math.min(res, this.table[i][this.hash(item, i)]);
      }

      return res;
   }

   public long estimateCount(String item) {
      long res = Long.MAX_VALUE;
      int[] buckets = Filter.getHashBuckets(item, this.depth, this.width);

      for(int i = 0; i < this.depth; ++i) {
         res = Math.min(res, this.table[i][buckets[i]]);
      }

      return res;
   }

   public static CountMinSketch merge(CountMinSketch... estimators) throws CMSMergeException {
      CountMinSketch merged = null;
      if (estimators != null && estimators.length > 0) {
         int depth = estimators[0].depth;
         int width = estimators[0].width;
         long[] hashA = Arrays.copyOf(estimators[0].hashA, estimators[0].hashA.length);
         long[][] table = new long[depth][width];
         long size = 0L;

         for(CountMinSketch estimator : estimators) {
            if (estimator.depth != depth) {
               throw new CMSMergeException("Cannot merge estimators of different depth");
            }

            if (estimator.width != width) {
               throw new CMSMergeException("Cannot merge estimators of different width");
            }

            if (!Arrays.equals(estimator.hashA, hashA)) {
               throw new CMSMergeException("Cannot merge estimators of different seed");
            }

            for(int i = 0; i < table.length; ++i) {
               for(int j = 0; j < table[i].length; ++j) {
                  table[i][j] += estimator.table[i][j];
               }
            }

            long previousSize = size;
            size += estimator.size;
            checkSizeAfterOperation(previousSize, "merge(" + estimator + ")", size);
         }

         merged = new CountMinSketch(depth, width, size, hashA, table);
      }

      return merged;
   }

   public static byte[] serialize(CountMinSketch sketch) {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      DataOutputStream s = new DataOutputStream(bos);

      try {
         s.writeLong(sketch.size);
         s.writeInt(sketch.depth);
         s.writeInt(sketch.width);

         for(int i = 0; i < sketch.depth; ++i) {
            s.writeLong(sketch.hashA[i]);

            for(int j = 0; j < sketch.width; ++j) {
               s.writeLong(sketch.table[i][j]);
            }
         }

         s.close();
         return bos.toByteArray();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public static CountMinSketch deserialize(byte[] data) {
      ByteArrayInputStream bis = new ByteArrayInputStream(data);
      DataInputStream s = new DataInputStream(bis);

      try {
         CountMinSketch sketch = new CountMinSketch();
         sketch.size = s.readLong();
         sketch.depth = s.readInt();
         sketch.width = s.readInt();
         sketch.eps = (double)2.0F / (double)sketch.width;
         sketch.confidence = (double)1.0F - (double)1.0F / Math.pow((double)2.0F, (double)sketch.depth);
         sketch.hashA = new long[sketch.depth];
         sketch.table = new long[sketch.depth][sketch.width];

         for(int i = 0; i < sketch.depth; ++i) {
            sketch.hashA[i] = s.readLong();

            for(int j = 0; j < sketch.width; ++j) {
               sketch.table[i][j] = s.readLong();
            }
         }

         return sketch;
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   protected static class CMSMergeException extends FrequencyMergeException {
      public CMSMergeException(String message) {
         super(message);
      }
   }
}
