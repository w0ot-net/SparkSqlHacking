package org.apache.datasketches.theta;

import java.nio.charset.StandardCharsets;
import org.apache.datasketches.common.ByteArrayUtil;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.hash.MurmurHash3;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.ThetaUtil;

final class SingleItemSketch extends CompactSketch {
   private static final long DEFAULT_SEED_HASH = (long)ThetaUtil.computeSeedHash(9001L) & 65535L;
   private static final long PRE0_LO6_SI = 63771674608385L;
   private long pre0_ = 0L;
   private long hash_ = 0L;

   private SingleItemSketch(long hash) {
      this.pre0_ = DEFAULT_SEED_HASH << 48 | 63771674608385L;
      this.hash_ = hash;
   }

   SingleItemSketch(long hash, long seed) {
      long seedHash = (long)ThetaUtil.computeSeedHash(seed) & 65535L;
      this.pre0_ = seedHash << 48 | 63771674608385L;
      this.hash_ = hash;
   }

   SingleItemSketch(long hash, short seedHash) {
      long seedH = (long)seedHash & 65535L;
      this.pre0_ = seedH << 48 | 63771674608385L;
      this.hash_ = hash;
   }

   static SingleItemSketch heapify(Memory srcMem, short expectedSeedHash) {
      ThetaUtil.checkSeedHashes((short)PreambleUtil.extractSeedHash(srcMem), expectedSeedHash);
      boolean singleItem = otherCheckForSingleItem(srcMem);
      if (singleItem) {
         return new SingleItemSketch(srcMem.getLong(8L), expectedSeedHash);
      } else {
         throw new SketchesArgumentException("Input Memory is not a SingleItemSketch.");
      }
   }

   public CompactSketch compact(boolean dstOrdered, WritableMemory dstMem) {
      if (dstMem == null) {
         return this;
      } else {
         dstMem.putLong(0L, this.pre0_);
         dstMem.putLong(8L, this.hash_);
         return new DirectCompactSketch(dstMem);
      }
   }

   static SingleItemSketch create(long datum) {
      long[] data = new long[]{datum};
      return new SingleItemSketch(MurmurHash3.hash(data, 9001L)[0] >>> 1);
   }

   static SingleItemSketch create(double datum) {
      double d = datum == (double)0.0F ? (double)0.0F : datum;
      long[] data = new long[]{Double.doubleToLongBits(d)};
      return new SingleItemSketch(MurmurHash3.hash(data, 9001L)[0] >>> 1);
   }

   static SingleItemSketch create(String datum) {
      if (datum != null && !datum.isEmpty()) {
         byte[] data = datum.getBytes(StandardCharsets.UTF_8);
         return new SingleItemSketch(MurmurHash3.hash(data, 9001L)[0] >>> 1);
      } else {
         return null;
      }
   }

   static SingleItemSketch create(byte[] data) {
      return data != null && data.length != 0 ? new SingleItemSketch(MurmurHash3.hash(data, 9001L)[0] >>> 1) : null;
   }

   static SingleItemSketch create(char[] data) {
      return data != null && data.length != 0 ? new SingleItemSketch(MurmurHash3.hash(data, 9001L)[0] >>> 1) : null;
   }

   static SingleItemSketch create(int[] data) {
      return data != null && data.length != 0 ? new SingleItemSketch(MurmurHash3.hash(data, 9001L)[0] >>> 1) : null;
   }

   static SingleItemSketch create(long[] data) {
      return data != null && data.length != 0 ? new SingleItemSketch(MurmurHash3.hash(data, 9001L)[0] >>> 1) : null;
   }

   static SingleItemSketch create(long datum, long seed) {
      long[] data = new long[]{datum};
      return new SingleItemSketch(MurmurHash3.hash(data, seed)[0] >>> 1);
   }

   static SingleItemSketch create(double datum, long seed) {
      double d = datum == (double)0.0F ? (double)0.0F : datum;
      long[] data = new long[]{Double.doubleToLongBits(d)};
      return new SingleItemSketch(MurmurHash3.hash(data, seed)[0] >>> 1, seed);
   }

   static SingleItemSketch create(String datum, long seed) {
      if (datum != null && !datum.isEmpty()) {
         byte[] data = datum.getBytes(StandardCharsets.UTF_8);
         return new SingleItemSketch(MurmurHash3.hash(data, seed)[0] >>> 1, seed);
      } else {
         return null;
      }
   }

   static SingleItemSketch create(byte[] data, long seed) {
      return data != null && data.length != 0 ? new SingleItemSketch(MurmurHash3.hash(data, seed)[0] >>> 1, seed) : null;
   }

   static SingleItemSketch create(char[] data, long seed) {
      return data != null && data.length != 0 ? new SingleItemSketch(MurmurHash3.hash(data, seed)[0] >>> 1, seed) : null;
   }

   static SingleItemSketch create(int[] data, long seed) {
      return data != null && data.length != 0 ? new SingleItemSketch(MurmurHash3.hash(data, seed)[0] >>> 1, seed) : null;
   }

   static SingleItemSketch create(long[] data, long seed) {
      return data != null && data.length != 0 ? new SingleItemSketch(MurmurHash3.hash(data, seed)[0] >>> 1, seed) : null;
   }

   public int getCountLessThanThetaLong(long thetaLong) {
      return this.hash_ < thetaLong ? 1 : 0;
   }

   public int getCurrentBytes() {
      return 16;
   }

   public double getEstimate() {
      return (double)1.0F;
   }

   public HashIterator iterator() {
      return new HeapCompactHashIterator(new long[]{this.hash_});
   }

   public double getLowerBound(int numStdDev) {
      return (double)1.0F;
   }

   public int getRetainedEntries(boolean valid) {
      return 1;
   }

   public long getThetaLong() {
      return Long.MAX_VALUE;
   }

   public double getUpperBound(int numStdDev) {
      return (double)1.0F;
   }

   public boolean isEmpty() {
      return false;
   }

   public boolean isOrdered() {
      return true;
   }

   public byte[] toByteArray() {
      byte[] out = new byte[16];
      ByteArrayUtil.putLongLE(out, 0, this.pre0_);
      ByteArrayUtil.putLongLE(out, 8, this.hash_);
      return out;
   }

   long[] getCache() {
      return new long[]{this.hash_};
   }

   int getCompactPreambleLongs() {
      return 1;
   }

   int getCurrentPreambleLongs() {
      return 1;
   }

   Memory getMemory() {
      return null;
   }

   short getSeedHash() {
      return (short)((int)(this.pre0_ >>> 48));
   }

   static final boolean otherCheckForSingleItem(Memory mem) {
      return otherCheckForSingleItem(PreambleUtil.extractPreLongs(mem), PreambleUtil.extractSerVer(mem), PreambleUtil.extractFamilyID(mem), PreambleUtil.extractFlags(mem));
   }

   static final boolean otherCheckForSingleItem(int preLongs, int serVer, int famId, int flags) {
      boolean numPreLongs = preLongs == 1;
      boolean numSerVer = serVer >= 3;
      boolean numFamId = famId == Family.COMPACT.getID();
      boolean numFlags = (flags & 31) == 26;
      boolean singleFlag = (flags & 32) > 0;
      return numPreLongs && numSerVer && numFamId && numFlags || singleFlag;
   }
}
