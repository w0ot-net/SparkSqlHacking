package org.apache.spark.util.sketch;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

class BloomFilterImpl extends BloomFilter implements Serializable {
   private int numHashFunctions;
   private BitArray bits;

   BloomFilterImpl(int numHashFunctions, long numBits) {
      this(new BitArray(numBits), numHashFunctions);
   }

   private BloomFilterImpl(BitArray bits, int numHashFunctions) {
      this.bits = bits;
      this.numHashFunctions = numHashFunctions;
   }

   private BloomFilterImpl() {
   }

   public boolean equals(Object other) {
      if (other == this) {
         return true;
      } else if (!(other instanceof BloomFilterImpl)) {
         return false;
      } else {
         BloomFilterImpl that = (BloomFilterImpl)other;
         return this.numHashFunctions == that.numHashFunctions && this.bits.equals(that.bits);
      }
   }

   public int hashCode() {
      return this.bits.hashCode() * 31 + this.numHashFunctions;
   }

   public double expectedFpp() {
      return Math.pow((double)this.bits.cardinality() / (double)this.bits.bitSize(), (double)this.numHashFunctions);
   }

   public long bitSize() {
      return this.bits.bitSize();
   }

   public boolean put(Object item) {
      if (item instanceof String str) {
         return this.putString(str);
      } else if (item instanceof byte[] bytes) {
         return this.putBinary(bytes);
      } else {
         return this.putLong(Utils.integralToLong(item));
      }
   }

   public boolean putString(String item) {
      return this.putBinary(Utils.getBytesFromUTF8String(item));
   }

   public boolean putBinary(byte[] item) {
      int h1 = Murmur3_x86_32.hashUnsafeBytes(item, (long)Platform.BYTE_ARRAY_OFFSET, item.length, 0);
      int h2 = Murmur3_x86_32.hashUnsafeBytes(item, (long)Platform.BYTE_ARRAY_OFFSET, item.length, h1);
      long bitSize = this.bits.bitSize();
      boolean bitsChanged = false;

      for(int i = 1; i <= this.numHashFunctions; ++i) {
         int combinedHash = h1 + i * h2;
         if (combinedHash < 0) {
            combinedHash = ~combinedHash;
         }

         bitsChanged |= this.bits.set((long)combinedHash % bitSize);
      }

      return bitsChanged;
   }

   public boolean mightContainString(String item) {
      return this.mightContainBinary(Utils.getBytesFromUTF8String(item));
   }

   public boolean mightContainBinary(byte[] item) {
      int h1 = Murmur3_x86_32.hashUnsafeBytes(item, (long)Platform.BYTE_ARRAY_OFFSET, item.length, 0);
      int h2 = Murmur3_x86_32.hashUnsafeBytes(item, (long)Platform.BYTE_ARRAY_OFFSET, item.length, h1);
      long bitSize = this.bits.bitSize();

      for(int i = 1; i <= this.numHashFunctions; ++i) {
         int combinedHash = h1 + i * h2;
         if (combinedHash < 0) {
            combinedHash = ~combinedHash;
         }

         if (!this.bits.get((long)combinedHash % bitSize)) {
            return false;
         }
      }

      return true;
   }

   public boolean putLong(long item) {
      int h1 = Murmur3_x86_32.hashLong(item, 0);
      int h2 = Murmur3_x86_32.hashLong(item, h1);
      long bitSize = this.bits.bitSize();
      boolean bitsChanged = false;

      for(int i = 1; i <= this.numHashFunctions; ++i) {
         int combinedHash = h1 + i * h2;
         if (combinedHash < 0) {
            combinedHash = ~combinedHash;
         }

         bitsChanged |= this.bits.set((long)combinedHash % bitSize);
      }

      return bitsChanged;
   }

   public boolean mightContainLong(long item) {
      int h1 = Murmur3_x86_32.hashLong(item, 0);
      int h2 = Murmur3_x86_32.hashLong(item, h1);
      long bitSize = this.bits.bitSize();

      for(int i = 1; i <= this.numHashFunctions; ++i) {
         int combinedHash = h1 + i * h2;
         if (combinedHash < 0) {
            combinedHash = ~combinedHash;
         }

         if (!this.bits.get((long)combinedHash % bitSize)) {
            return false;
         }
      }

      return true;
   }

   public boolean mightContain(Object item) {
      if (item instanceof String str) {
         return this.mightContainString(str);
      } else if (item instanceof byte[] bytes) {
         return this.mightContainBinary(bytes);
      } else {
         return this.mightContainLong(Utils.integralToLong(item));
      }
   }

   public boolean isCompatible(BloomFilter other) {
      if (other == null) {
         return false;
      } else if (!(other instanceof BloomFilterImpl)) {
         return false;
      } else {
         BloomFilterImpl that = (BloomFilterImpl)other;
         return this.bitSize() == that.bitSize() && this.numHashFunctions == that.numHashFunctions;
      }
   }

   public BloomFilter mergeInPlace(BloomFilter other) throws IncompatibleMergeException {
      BloomFilterImpl otherImplInstance = this.checkCompatibilityForMerge(other);
      this.bits.putAll(otherImplInstance.bits);
      return this;
   }

   public BloomFilter intersectInPlace(BloomFilter other) throws IncompatibleMergeException {
      BloomFilterImpl otherImplInstance = this.checkCompatibilityForMerge(other);
      this.bits.and(otherImplInstance.bits);
      return this;
   }

   public long cardinality() {
      return this.bits.cardinality();
   }

   private BloomFilterImpl checkCompatibilityForMerge(BloomFilter other) throws IncompatibleMergeException {
      if (other == null) {
         throw new IncompatibleMergeException("Cannot merge null bloom filter");
      } else if (other instanceof BloomFilterImpl) {
         BloomFilterImpl that = (BloomFilterImpl)other;
         if (this.bitSize() != that.bitSize()) {
            throw new IncompatibleMergeException("Cannot merge bloom filters with different bit size");
         } else if (this.numHashFunctions != that.numHashFunctions) {
            throw new IncompatibleMergeException("Cannot merge bloom filters with different number of hash functions");
         } else {
            return that;
         }
      } else {
         throw new IncompatibleMergeException("Cannot merge bloom filter of class " + other.getClass().getName());
      }
   }

   public void writeTo(OutputStream out) throws IOException {
      DataOutputStream dos = new DataOutputStream(out);
      dos.writeInt(BloomFilter.Version.V1.getVersionNumber());
      dos.writeInt(this.numHashFunctions);
      this.bits.writeTo(dos);
   }

   private void readFrom0(InputStream in) throws IOException {
      DataInputStream dis = new DataInputStream(in);
      int version = dis.readInt();
      if (version != BloomFilter.Version.V1.getVersionNumber()) {
         throw new IOException("Unexpected Bloom filter version number (" + version + ")");
      } else {
         this.numHashFunctions = dis.readInt();
         this.bits = BitArray.readFrom(dis);
      }
   }

   public static BloomFilterImpl readFrom(InputStream in) throws IOException {
      BloomFilterImpl filter = new BloomFilterImpl();
      filter.readFrom0(in);
      return filter;
   }

   public static BloomFilterImpl readFrom(byte[] bytes) throws IOException {
      ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

      BloomFilterImpl var2;
      try {
         var2 = readFrom((InputStream)bis);
      } catch (Throwable var5) {
         try {
            bis.close();
         } catch (Throwable var4) {
            var5.addSuppressed(var4);
         }

         throw var5;
      }

      bis.close();
      return var2;
   }

   private void writeObject(ObjectOutputStream out) throws IOException {
      this.writeTo(out);
   }

   private void readObject(ObjectInputStream in) throws IOException {
      this.readFrom0(in);
   }
}
