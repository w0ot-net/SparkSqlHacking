package org.apache.spark.util.sketch;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

class CountMinSketchImpl extends CountMinSketch implements Serializable {
   private static final long PRIME_MODULUS = 2147483647L;
   private int depth;
   private int width;
   private long[][] table;
   private long[] hashA;
   private long totalCount;
   private double eps;
   private double confidence;

   private CountMinSketchImpl() {
   }

   CountMinSketchImpl(int depth, int width, int seed) {
      if (depth > 0 && width > 0) {
         this.depth = depth;
         this.width = width;
         this.eps = (double)2.0F / (double)width;
         this.confidence = (double)1.0F - (double)1.0F / Math.pow((double)2.0F, (double)depth);
         this.initTablesWith(depth, width, seed);
      } else {
         throw new IllegalArgumentException("Depth and width must be both positive");
      }
   }

   CountMinSketchImpl(double eps, double confidence, int seed) {
      if (eps <= (double)0.0F) {
         throw new IllegalArgumentException("Relative error must be positive");
      } else if (!(confidence <= (double)0.0F) && !(confidence >= (double)1.0F)) {
         this.eps = eps;
         this.confidence = confidence;
         this.width = (int)Math.ceil((double)2.0F / eps);
         this.depth = (int)Math.ceil(-Math.log1p(-confidence) / Math.log((double)2.0F));
         this.initTablesWith(this.depth, this.width, seed);
      } else {
         throw new IllegalArgumentException("Confidence must be within range (0.0, 1.0)");
      }
   }

   public boolean equals(Object other) {
      if (other == this) {
         return true;
      } else if (!(other instanceof CountMinSketchImpl)) {
         return false;
      } else {
         CountMinSketchImpl that = (CountMinSketchImpl)other;
         return this.depth == that.depth && this.width == that.width && this.totalCount == that.totalCount && Arrays.equals(this.hashA, that.hashA) && Arrays.deepEquals(this.table, that.table);
      }
   }

   public int hashCode() {
      int hash = this.depth;
      hash = hash * 31 + this.width;
      hash = hash * 31 + (int)(this.totalCount ^ this.totalCount >>> 32);
      hash = hash * 31 + Arrays.hashCode(this.hashA);
      hash = hash * 31 + Arrays.deepHashCode(this.table);
      return hash;
   }

   private void initTablesWith(int depth, int width, int seed) {
      this.table = new long[depth][width];
      this.hashA = new long[depth];
      Random r = new Random((long)seed);

      for(int i = 0; i < depth; ++i) {
         this.hashA[i] = (long)r.nextInt(Integer.MAX_VALUE);
      }

   }

   public double relativeError() {
      return this.eps;
   }

   public double confidence() {
      return this.confidence;
   }

   public int depth() {
      return this.depth;
   }

   public int width() {
      return this.width;
   }

   public long totalCount() {
      return this.totalCount;
   }

   public void add(Object item) {
      this.add(item, 1L);
   }

   public void add(Object item, long count) {
      if (item instanceof String str) {
         this.addString(str, count);
      } else if (item instanceof byte[] bytes) {
         this.addBinary(bytes, count);
      } else {
         this.addLong(Utils.integralToLong(item), count);
      }

   }

   public void addString(String item) {
      this.addString(item, 1L);
   }

   public void addString(String item, long count) {
      this.addBinary(Utils.getBytesFromUTF8String(item), count);
   }

   public void addLong(long item) {
      this.addLong(item, 1L);
   }

   public void addLong(long item, long count) {
      if (count < 0L) {
         throw new IllegalArgumentException("Negative increments not implemented");
      } else {
         for(int i = 0; i < this.depth; ++i) {
            long[] var10000 = this.table[i];
            int var10001 = this.hash(item, i);
            var10000[var10001] += count;
         }

         this.totalCount += count;
      }
   }

   public void addBinary(byte[] item) {
      this.addBinary(item, 1L);
   }

   public void addBinary(byte[] item, long count) {
      if (count < 0L) {
         throw new IllegalArgumentException("Negative increments not implemented");
      } else {
         int[] buckets = getHashBuckets(item, this.depth, this.width);

         for(int i = 0; i < this.depth; ++i) {
            long[] var10000 = this.table[i];
            var10000[buckets[i]] += count;
         }

         this.totalCount += count;
      }
   }

   private int hash(long item, int count) {
      long hash = this.hashA[count] * item;
      hash += hash >> 32;
      hash &= 2147483647L;
      return (int)hash % this.width;
   }

   private static int[] getHashBuckets(String key, int hashCount, int max) {
      return getHashBuckets(Utils.getBytesFromUTF8String(key), hashCount, max);
   }

   private static int[] getHashBuckets(byte[] b, int hashCount, int max) {
      int[] result = new int[hashCount];
      int hash1 = Murmur3_x86_32.hashUnsafeBytes(b, (long)Platform.BYTE_ARRAY_OFFSET, b.length, 0);
      int hash2 = Murmur3_x86_32.hashUnsafeBytes(b, (long)Platform.BYTE_ARRAY_OFFSET, b.length, hash1);

      for(int i = 0; i < hashCount; ++i) {
         result[i] = Math.abs((hash1 + i * hash2) % max);
      }

      return result;
   }

   public long estimateCount(Object item) {
      if (item instanceof String str) {
         return this.estimateCountForStringItem(str);
      } else if (item instanceof byte[] bytes) {
         return this.estimateCountForBinaryItem(bytes);
      } else {
         return this.estimateCountForLongItem(Utils.integralToLong(item));
      }
   }

   private long estimateCountForLongItem(long item) {
      long res = Long.MAX_VALUE;

      for(int i = 0; i < this.depth; ++i) {
         res = Math.min(res, this.table[i][this.hash(item, i)]);
      }

      return res;
   }

   private long estimateCountForStringItem(String item) {
      long res = Long.MAX_VALUE;
      int[] buckets = getHashBuckets(item, this.depth, this.width);

      for(int i = 0; i < this.depth; ++i) {
         res = Math.min(res, this.table[i][buckets[i]]);
      }

      return res;
   }

   private long estimateCountForBinaryItem(byte[] item) {
      long res = Long.MAX_VALUE;
      int[] buckets = getHashBuckets(item, this.depth, this.width);

      for(int i = 0; i < this.depth; ++i) {
         res = Math.min(res, this.table[i][buckets[i]]);
      }

      return res;
   }

   public CountMinSketch mergeInPlace(CountMinSketch other) throws IncompatibleMergeException {
      if (other == null) {
         throw new IncompatibleMergeException("Cannot merge null estimator");
      } else if (!(other instanceof CountMinSketchImpl)) {
         throw new IncompatibleMergeException("Cannot merge estimator of class " + other.getClass().getName());
      } else {
         CountMinSketchImpl that = (CountMinSketchImpl)other;
         if (this.depth != that.depth) {
            throw new IncompatibleMergeException("Cannot merge estimators of different depth");
         } else if (this.width != that.width) {
            throw new IncompatibleMergeException("Cannot merge estimators of different width");
         } else if (!Arrays.equals(this.hashA, that.hashA)) {
            throw new IncompatibleMergeException("Cannot merge estimators of different seed");
         } else {
            for(int i = 0; i < this.table.length; ++i) {
               for(int j = 0; j < this.table[i].length; ++j) {
                  this.table[i][j] += that.table[i][j];
               }
            }

            this.totalCount += that.totalCount;
            return this;
         }
      }
   }

   public void writeTo(OutputStream out) throws IOException {
      DataOutputStream dos = new DataOutputStream(out);
      dos.writeInt(CountMinSketch.Version.V1.getVersionNumber());
      dos.writeLong(this.totalCount);
      dos.writeInt(this.depth);
      dos.writeInt(this.width);

      for(int i = 0; i < this.depth; ++i) {
         dos.writeLong(this.hashA[i]);
      }

      for(int i = 0; i < this.depth; ++i) {
         for(int j = 0; j < this.width; ++j) {
            dos.writeLong(this.table[i][j]);
         }
      }

   }

   public byte[] toByteArray() throws IOException {
      ByteArrayOutputStream out = new ByteArrayOutputStream();

      byte[] var2;
      try {
         this.writeTo(out);
         var2 = out.toByteArray();
      } catch (Throwable var5) {
         try {
            out.close();
         } catch (Throwable var4) {
            var5.addSuppressed(var4);
         }

         throw var5;
      }

      out.close();
      return var2;
   }

   public static CountMinSketchImpl readFrom(InputStream in) throws IOException {
      CountMinSketchImpl sketch = new CountMinSketchImpl();
      sketch.readFrom0(in);
      return sketch;
   }

   private void readFrom0(InputStream in) throws IOException {
      DataInputStream dis = new DataInputStream(in);
      int version = dis.readInt();
      if (version != CountMinSketch.Version.V1.getVersionNumber()) {
         throw new IOException("Unexpected Count-Min Sketch version number (" + version + ")");
      } else {
         this.totalCount = dis.readLong();
         this.depth = dis.readInt();
         this.width = dis.readInt();
         this.eps = (double)2.0F / (double)this.width;
         this.confidence = (double)1.0F - (double)1.0F / Math.pow((double)2.0F, (double)this.depth);
         this.hashA = new long[this.depth];

         for(int i = 0; i < this.depth; ++i) {
            this.hashA[i] = dis.readLong();
         }

         this.table = new long[this.depth][this.width];

         for(int i = 0; i < this.depth; ++i) {
            for(int j = 0; j < this.width; ++j) {
               this.table[i][j] = dis.readLong();
            }
         }

      }
   }

   private void writeObject(ObjectOutputStream out) throws IOException {
      this.writeTo(out);
   }

   private void readObject(ObjectInputStream in) throws IOException {
      this.readFrom0(in);
   }
}
