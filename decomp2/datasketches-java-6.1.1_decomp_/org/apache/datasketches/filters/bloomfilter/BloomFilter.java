package org.apache.datasketches.filters.bloomfilter;

import java.nio.charset.StandardCharsets;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Buffer;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableBuffer;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.memory.XxHash;

public final class BloomFilter {
   public static final long MAX_SIZE_BITS;
   private static final int SER_VER = 1;
   private static final int EMPTY_FLAG_MASK = 4;
   private static final long BIT_ARRAY_OFFSET = 16L;
   private static final int FLAGS_BYTE = 3;
   private final long seed_;
   private final short numHashes_;
   private final BitArray bitArray_;
   private final WritableMemory wmem_;

   BloomFilter(long numBits, int numHashes, long seed) {
      this.seed_ = seed;
      this.numHashes_ = (short)numHashes;
      this.bitArray_ = new HeapBitArray(numBits);
      this.wmem_ = null;
   }

   BloomFilter(long numBits, int numHashes, long seed, WritableMemory wmem) {
      if (wmem.getCapacity() < (long)Family.BLOOMFILTER.getMaxPreLongs()) {
         throw new SketchesArgumentException("Provided WritableMemory capacity insufficient to initialize BloomFilter");
      } else {
         WritableBuffer wbuf = wmem.asWritableBuffer();
         wbuf.putByte((byte)Family.BLOOMFILTER.getMaxPreLongs());
         wbuf.putByte((byte)1);
         wbuf.putByte((byte)Family.BLOOMFILTER.getID());
         wbuf.putByte((byte)0);
         wbuf.putShort((short)numHashes);
         wbuf.putShort((short)0);
         wbuf.putLong(seed);
         this.seed_ = seed;
         this.numHashes_ = (short)numHashes;
         this.bitArray_ = DirectBitArray.initialize(numBits, wmem.writableRegion(16L, wmem.getCapacity() - 16L));
         this.wmem_ = wmem;
      }
   }

   BloomFilter(short numHashes, long seed, BitArray bitArray, WritableMemory wmem) {
      this.seed_ = seed;
      this.numHashes_ = numHashes;
      this.bitArray_ = bitArray;
      this.wmem_ = wmem;
   }

   public static BloomFilter heapify(Memory mem) {
      return internalHeapifyOrWrap((WritableMemory)mem, false, false);
   }

   public static BloomFilter wrap(Memory mem) {
      return internalHeapifyOrWrap((WritableMemory)mem, true, false);
   }

   public static BloomFilter writableWrap(WritableMemory wmem) {
      return internalHeapifyOrWrap(wmem, true, true);
   }

   private static BloomFilter internalHeapifyOrWrap(WritableMemory wmem, boolean isWrap, boolean isWritable) {
      Buffer buf = wmem.asBuffer();
      int preLongs = buf.getByte();
      int serVer = buf.getByte();
      int familyID = buf.getByte();
      int flags = buf.getByte();
      checkArgument(preLongs < Family.BLOOMFILTER.getMinPreLongs() || preLongs > Family.BLOOMFILTER.getMaxPreLongs(), "Possible corruption: Incorrect number of preamble bytes specified in header");
      checkArgument(serVer != 1, "Possible corruption: Unrecognized serialization version: " + serVer);
      checkArgument(familyID != Family.BLOOMFILTER.getID(), "Possible corruption: Incorrect FamilyID for bloom filter. Found: " + familyID);
      short numHashes = buf.getShort();
      buf.getShort();
      checkArgument(numHashes < 1, "Possible corruption: Need strictly positive number of hash functions. Found: " + numHashes);
      long seed = buf.getLong();
      boolean isEmpty = (flags & 4) != 0;
      if (isWrap) {
         BitArray bitArray;
         if (isWritable) {
            bitArray = BitArray.writableWrap(wmem.writableRegion(16L, wmem.getCapacity() - 16L), isEmpty);
         } else {
            bitArray = BitArray.wrap(wmem.region(16L, wmem.getCapacity() - 16L), isEmpty);
         }

         return new BloomFilter(numHashes, seed, bitArray, wmem);
      } else {
         BitArray bitArray = BitArray.heapify(buf, isEmpty);
         return new BloomFilter(numHashes, seed, bitArray, (WritableMemory)null);
      }
   }

   public void reset() {
      this.bitArray_.reset();
   }

   public boolean isEmpty() {
      return this.bitArray_.isEmpty();
   }

   public long getBitsUsed() {
      return this.bitArray_.getNumBitsSet();
   }

   public long getCapacity() {
      return this.bitArray_.getCapacity();
   }

   public short getNumHashes() {
      return this.numHashes_;
   }

   public long getSeed() {
      return this.seed_;
   }

   public boolean hasMemory() {
      return this.wmem_ != null;
   }

   public boolean isReadOnly() {
      return this.wmem_ != null && this.bitArray_.isReadOnly();
   }

   public boolean isDirect() {
      return this.wmem_ != null && this.bitArray_.isDirect();
   }

   public double getFillPercentage() {
      return (double)this.bitArray_.getNumBitsSet() / (double)this.bitArray_.getCapacity();
   }

   public void update(long item) {
      long h0 = XxHash.hashLong(item, this.seed_);
      long h1 = XxHash.hashLong(item, h0);
      this.updateInternal(h0, h1);
   }

   public void update(double item) {
      long[] data = new long[]{Double.doubleToLongBits(item)};
      long h0 = XxHash.hashLongArr(data, 0L, 1L, this.seed_);
      long h1 = XxHash.hashLongArr(data, 0L, 1L, h0);
      this.updateInternal(h0, h1);
   }

   public void update(String item) {
      if (item != null && !item.isEmpty()) {
         byte[] strBytes = item.getBytes(StandardCharsets.UTF_8);
         long h0 = XxHash.hashByteArr(strBytes, 0L, (long)strBytes.length, this.seed_);
         long h1 = XxHash.hashByteArr(strBytes, 0L, (long)strBytes.length, h0);
         this.updateInternal(h0, h1);
      }
   }

   public void update(byte[] data) {
      if (data != null) {
         long h0 = XxHash.hashByteArr(data, 0L, (long)data.length, this.seed_);
         long h1 = XxHash.hashByteArr(data, 0L, (long)data.length, h0);
         this.updateInternal(h0, h1);
      }
   }

   public void update(char[] data) {
      if (data != null) {
         long h0 = XxHash.hashCharArr(data, 0L, (long)data.length, this.seed_);
         long h1 = XxHash.hashCharArr(data, 0L, (long)data.length, h0);
         this.updateInternal(h0, h1);
      }
   }

   public void update(short[] data) {
      if (data != null) {
         long h0 = XxHash.hashShortArr(data, 0L, (long)data.length, this.seed_);
         long h1 = XxHash.hashShortArr(data, 0L, (long)data.length, h0);
         this.updateInternal(h0, h1);
      }
   }

   public void update(int[] data) {
      if (data != null) {
         long h0 = XxHash.hashIntArr(data, 0L, (long)data.length, this.seed_);
         long h1 = XxHash.hashIntArr(data, 0L, (long)data.length, h0);
         this.updateInternal(h0, h1);
      }
   }

   public void update(long[] data) {
      if (data != null) {
         long h0 = XxHash.hashLongArr(data, 0L, (long)data.length, this.seed_);
         long h1 = XxHash.hashLongArr(data, 0L, (long)data.length, h0);
         this.updateInternal(h0, h1);
      }
   }

   public void update(Memory mem) {
      if (mem != null) {
         long h0 = mem.xxHash64(0L, mem.getCapacity(), this.seed_);
         long h1 = mem.xxHash64(0L, mem.getCapacity(), h0);
         this.updateInternal(h0, h1);
      }
   }

   private void updateInternal(long h0, long h1) {
      long numBits = this.bitArray_.getCapacity();

      for(int i = 1; i <= this.numHashes_; ++i) {
         long hashIndex = (h0 + (long)i * h1 >>> 1) % numBits;
         this.bitArray_.setBit(hashIndex);
      }

   }

   public boolean queryAndUpdate(long item) {
      long h0 = XxHash.hashLong(item, this.seed_);
      long h1 = XxHash.hashLong(item, h0);
      return this.queryAndUpdateInternal(h0, h1);
   }

   public boolean queryAndUpdate(double item) {
      long[] data = new long[]{Double.doubleToLongBits(item)};
      long h0 = XxHash.hashLongArr(data, 0L, 1L, this.seed_);
      long h1 = XxHash.hashLongArr(data, 0L, 1L, h0);
      return this.queryAndUpdateInternal(h0, h1);
   }

   public boolean queryAndUpdate(String item) {
      if (item != null && !item.isEmpty()) {
         byte[] strBytes = item.getBytes(StandardCharsets.UTF_8);
         long h0 = XxHash.hashByteArr(strBytes, 0L, (long)strBytes.length, this.seed_);
         long h1 = XxHash.hashByteArr(strBytes, 0L, (long)strBytes.length, h0);
         return this.queryAndUpdateInternal(h0, h1);
      } else {
         return false;
      }
   }

   public boolean queryAndUpdate(byte[] data) {
      long h0 = XxHash.hashByteArr(data, 0L, (long)data.length, this.seed_);
      long h1 = XxHash.hashByteArr(data, 0L, (long)data.length, h0);
      return this.queryAndUpdateInternal(h0, h1);
   }

   public boolean queryAndUpdate(char[] data) {
      if (data == null) {
         return false;
      } else {
         long h0 = XxHash.hashCharArr(data, 0L, (long)data.length, this.seed_);
         long h1 = XxHash.hashCharArr(data, 0L, (long)data.length, h0);
         return this.queryAndUpdateInternal(h0, h1);
      }
   }

   public boolean queryAndUpdate(short[] data) {
      if (data == null) {
         return false;
      } else {
         long h0 = XxHash.hashShortArr(data, 0L, (long)data.length, this.seed_);
         long h1 = XxHash.hashShortArr(data, 0L, (long)data.length, h0);
         return this.queryAndUpdateInternal(h0, h1);
      }
   }

   public boolean queryAndUpdate(int[] data) {
      if (data == null) {
         return false;
      } else {
         long h0 = XxHash.hashIntArr(data, 0L, (long)data.length, this.seed_);
         long h1 = XxHash.hashIntArr(data, 0L, (long)data.length, h0);
         return this.queryAndUpdateInternal(h0, h1);
      }
   }

   public boolean queryAndUpdate(long[] data) {
      if (data == null) {
         return false;
      } else {
         long h0 = XxHash.hashLongArr(data, 0L, (long)data.length, this.seed_);
         long h1 = XxHash.hashLongArr(data, 0L, (long)data.length, h0);
         return this.queryAndUpdateInternal(h0, h1);
      }
   }

   public boolean queryAndUpdate(Memory mem) {
      if (mem == null) {
         return false;
      } else {
         long h0 = mem.xxHash64(0L, mem.getCapacity(), this.seed_);
         long h1 = mem.xxHash64(0L, mem.getCapacity(), h0);
         return this.queryAndUpdateInternal(h0, h1);
      }
   }

   private boolean queryAndUpdateInternal(long h0, long h1) {
      long numBits = this.bitArray_.getCapacity();
      boolean valueAlreadyExists = true;

      for(int i = 1; i <= this.numHashes_; ++i) {
         long hashIndex = (h0 + (long)i * h1 >>> 1) % numBits;
         valueAlreadyExists &= this.bitArray_.getAndSetBit(hashIndex);
      }

      return valueAlreadyExists;
   }

   public boolean query(long item) {
      long h0 = XxHash.hashLong(item, this.seed_);
      long h1 = XxHash.hashLong(item, h0);
      return this.queryInternal(h0, h1);
   }

   public boolean query(double item) {
      long[] data = new long[]{Double.doubleToLongBits(item)};
      long h0 = XxHash.hashLongArr(data, 0L, 1L, this.seed_);
      long h1 = XxHash.hashLongArr(data, 0L, 1L, h0);
      return this.queryInternal(h0, h1);
   }

   public boolean query(String item) {
      if (item != null && !item.isEmpty()) {
         byte[] strBytes = item.getBytes(StandardCharsets.UTF_8);
         long h0 = XxHash.hashByteArr(strBytes, 0L, (long)strBytes.length, this.seed_);
         long h1 = XxHash.hashByteArr(strBytes, 0L, (long)strBytes.length, h0);
         return this.queryInternal(h0, h1);
      } else {
         return false;
      }
   }

   public boolean query(byte[] data) {
      if (data == null) {
         return false;
      } else {
         long h0 = XxHash.hashByteArr(data, 0L, (long)data.length, this.seed_);
         long h1 = XxHash.hashByteArr(data, 0L, (long)data.length, h0);
         return this.queryInternal(h0, h1);
      }
   }

   public boolean query(char[] data) {
      if (data == null) {
         return false;
      } else {
         long h0 = XxHash.hashCharArr(data, 0L, (long)data.length, this.seed_);
         long h1 = XxHash.hashCharArr(data, 0L, (long)data.length, h0);
         return this.queryInternal(h0, h1);
      }
   }

   public boolean query(short[] data) {
      if (data == null) {
         return false;
      } else {
         long h0 = XxHash.hashShortArr(data, 0L, (long)data.length, this.seed_);
         long h1 = XxHash.hashShortArr(data, 0L, (long)data.length, h0);
         return this.queryInternal(h0, h1);
      }
   }

   public boolean query(int[] data) {
      if (data == null) {
         return false;
      } else {
         long h0 = XxHash.hashIntArr(data, 0L, (long)data.length, this.seed_);
         long h1 = XxHash.hashIntArr(data, 0L, (long)data.length, h0);
         return this.queryInternal(h0, h1);
      }
   }

   public boolean query(long[] data) {
      if (data == null) {
         return false;
      } else {
         long h0 = XxHash.hashLongArr(data, 0L, (long)data.length, this.seed_);
         long h1 = XxHash.hashLongArr(data, 0L, (long)data.length, h0);
         return this.queryInternal(h0, h1);
      }
   }

   public boolean query(Memory mem) {
      if (mem == null) {
         return false;
      } else {
         long h0 = mem.xxHash64(0L, mem.getCapacity(), this.seed_);
         long h1 = mem.xxHash64(0L, mem.getCapacity(), h0);
         return this.queryInternal(h0, h1);
      }
   }

   private boolean queryInternal(long h0, long h1) {
      long numBits = this.bitArray_.getCapacity();

      for(int i = 1; i <= this.numHashes_; ++i) {
         long hashIndex = (h0 + (long)i * h1 >>> 1) % numBits;
         if (!this.bitArray_.getBit(hashIndex)) {
            return false;
         }
      }

      return true;
   }

   public void union(BloomFilter other) {
      if (other != null) {
         if (!this.isCompatible(other)) {
            throw new SketchesArgumentException("Cannot union sketches with different seeds, hash functions, or sizes");
         } else {
            this.bitArray_.union(other.bitArray_);
         }
      }
   }

   public void intersect(BloomFilter other) {
      if (other != null) {
         if (!this.isCompatible(other)) {
            throw new SketchesArgumentException("Cannot union sketches with different seeds, hash functions, or sizes");
         } else {
            this.bitArray_.intersect(other.bitArray_);
         }
      }
   }

   public void invert() {
      this.bitArray_.invert();
   }

   public boolean isCompatible(BloomFilter other) {
      return other != null && this.seed_ == other.seed_ && this.numHashes_ == other.numHashes_ && this.bitArray_.getArrayLength() == other.bitArray_.getArrayLength();
   }

   public long getSerializedSizeBytes() {
      long sizeBytes = 16L;
      sizeBytes += this.bitArray_.getSerializedSizeBytes();
      return sizeBytes;
   }

   public static long getSerializedSize(long numBits) {
      return 16L + BitArray.getSerializedSizeBytes(numBits);
   }

   public byte[] toByteArray() {
      long sizeBytes = this.getSerializedSizeBytes();
      if (sizeBytes > 2147483647L) {
         throw new SketchesStateException("Cannot serialize a BloomFilter of this size using toByteArray(); use toLongArray() instead.");
      } else {
         byte[] bytes = new byte[(int)sizeBytes];
         if (this.wmem_ == null) {
            WritableBuffer wbuf = WritableMemory.writableWrap(bytes).asWritableBuffer();
            int numPreLongs = this.isEmpty() ? Family.BLOOMFILTER.getMinPreLongs() : Family.BLOOMFILTER.getMaxPreLongs();
            wbuf.putByte((byte)numPreLongs);
            wbuf.putByte((byte)1);
            wbuf.putByte((byte)Family.BLOOMFILTER.getID());
            wbuf.putByte((byte)(this.bitArray_.isEmpty() ? 4 : 0));
            wbuf.putShort(this.numHashes_);
            wbuf.putShort((short)0);
            wbuf.putLong(this.seed_);
            ((HeapBitArray)this.bitArray_).writeToBuffer(wbuf);
         } else {
            this.wmem_.getByteArray(0L, bytes, 0, (int)sizeBytes);
            if (this.isEmpty()) {
               bytes[3] = (byte)(bytes[3] | 4);
            }
         }

         return bytes;
      }
   }

   public long[] toLongArray() {
      long sizeBytes = this.getSerializedSizeBytes();
      long[] longs = new long[(int)(sizeBytes >> 3)];
      if (this.wmem_ == null) {
         WritableBuffer wbuf = WritableMemory.writableWrap(longs).asWritableBuffer();
         int numPreLongs = this.isEmpty() ? Family.BLOOMFILTER.getMinPreLongs() : Family.BLOOMFILTER.getMaxPreLongs();
         wbuf.putByte((byte)numPreLongs);
         wbuf.putByte((byte)1);
         wbuf.putByte((byte)Family.BLOOMFILTER.getID());
         wbuf.putByte((byte)(this.bitArray_.isEmpty() ? 4 : 0));
         wbuf.putShort(this.numHashes_);
         wbuf.putShort((short)0);
         wbuf.putLong(this.seed_);
         ((HeapBitArray)this.bitArray_).writeToBuffer(wbuf);
      } else {
         this.wmem_.getLongArray(0L, longs, 0, (int)(sizeBytes >>> 3));
         if (this.isEmpty()) {
            longs[0] |= 67108864L;
         }
      }

      return longs;
   }

   private static void checkArgument(boolean condition, String message) {
      if (condition) {
         throw new SketchesArgumentException(message);
      }
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(Util.LS);
      String thisSimpleName = this.getClass().getSimpleName();
      sb.append("### ").append(thisSimpleName).append(" SUMMARY: ").append(Util.LS);
      sb.append("   numBits      : ").append(this.bitArray_.getCapacity()).append(Util.LS);
      sb.append("   numHashes    : ").append(this.numHashes_).append(Util.LS);
      sb.append("   seed         : ").append(this.seed_).append(Util.LS);
      sb.append("   bitsUsed     : ").append(this.bitArray_.getNumBitsSet()).append(Util.LS);
      sb.append("   fill %       : ").append(this.getFillPercentage()).append(Util.LS);
      sb.append("### END SKETCH SUMMARY").append(Util.LS);
      return sb.toString();
   }

   static {
      MAX_SIZE_BITS = (long)(Integer.MAX_VALUE - Family.BLOOMFILTER.getMaxPreLongs()) * 64L;
   }
}
