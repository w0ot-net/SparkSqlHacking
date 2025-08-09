package org.apache.datasketches.theta;

import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.ThetaUtil;

public abstract class CompactSketch extends Sketch {
   public static CompactSketch heapify(Memory srcMem) {
      return heapify(srcMem, 9001L, false);
   }

   public static CompactSketch heapify(Memory srcMem, long expectedSeed) {
      return heapify(srcMem, expectedSeed, true);
   }

   private static CompactSketch heapify(Memory srcMem, long seed, boolean enforceSeed) {
      int serVer = PreambleUtil.extractSerVer(srcMem);
      int familyID = PreambleUtil.extractFamilyID(srcMem);
      Family family = Family.idToFamily(familyID);
      if (family != Family.COMPACT) {
         throw new IllegalArgumentException("Corrupted: " + family + " is not Compact!");
      } else if (serVer == 4) {
         return heapifyV4(srcMem, seed, enforceSeed);
      } else if (serVer == 3) {
         int flags = PreambleUtil.extractFlags(srcMem);
         boolean srcOrdered = (flags & 16) != 0;
         boolean empty = (flags & 4) != 0;
         if (enforceSeed && !empty) {
            PreambleUtil.checkMemorySeedHash(srcMem, seed);
         }

         return CompactOperations.memoryToCompact(srcMem, srcOrdered, (WritableMemory)null);
      } else {
         short seedHash = ThetaUtil.computeSeedHash(seed);
         if (serVer == 1) {
            return ForwardCompatibility.heapify1to3(srcMem, seedHash);
         } else if (serVer == 2) {
            return ForwardCompatibility.heapify2to3(srcMem, enforceSeed ? seedHash : (short)PreambleUtil.extractSeedHash(srcMem));
         } else {
            throw new SketchesArgumentException("Unknown Serialization Version: " + serVer);
         }
      }
   }

   public static CompactSketch wrap(Memory srcMem) {
      return wrap(srcMem, 9001L, false);
   }

   public static CompactSketch wrap(Memory srcMem, long expectedSeed) {
      return wrap(srcMem, expectedSeed, true);
   }

   private static CompactSketch wrap(Memory srcMem, long seed, boolean enforceSeed) {
      int serVer = PreambleUtil.extractSerVer(srcMem);
      int familyID = PreambleUtil.extractFamilyID(srcMem);
      Family family = Family.idToFamily(familyID);
      if (family != Family.COMPACT) {
         throw new IllegalArgumentException("Corrupted: " + family + " is not Compact!");
      } else {
         short seedHash = ThetaUtil.computeSeedHash(seed);
         if (serVer == 4) {
            return heapifyV4(srcMem, seed, enforceSeed);
         } else if (serVer == 3) {
            if (PreambleUtil.isEmptyFlag(srcMem)) {
               return EmptyCompactSketch.getHeapInstance(srcMem);
            } else if (SingleItemSketch.otherCheckForSingleItem(srcMem)) {
               return SingleItemSketch.heapify(srcMem, enforceSeed ? seedHash : (short)PreambleUtil.extractSeedHash(srcMem));
            } else {
               int flags = PreambleUtil.extractFlags(srcMem);
               boolean compactFlag = (flags & 8) > 0;
               if (!compactFlag) {
                  throw new SketchesArgumentException("Corrupted: COMPACT family sketch image must have compact flag set");
               } else {
                  boolean readOnly = (flags & 2) > 0;
                  if (!readOnly) {
                     throw new SketchesArgumentException("Corrupted: COMPACT family sketch image must have Read-Only flag set");
                  } else {
                     return DirectCompactSketch.wrapInstance(srcMem, enforceSeed ? seedHash : (short)PreambleUtil.extractSeedHash(srcMem));
                  }
               }
            }
         } else if (serVer == 1) {
            return ForwardCompatibility.heapify1to3(srcMem, seedHash);
         } else if (serVer == 2) {
            return ForwardCompatibility.heapify2to3(srcMem, enforceSeed ? seedHash : (short)PreambleUtil.extractSeedHash(srcMem));
         } else {
            throw new SketchesArgumentException("Corrupted: Serialization Version " + serVer + " not recognized.");
         }
      }
   }

   public abstract CompactSketch compact(boolean var1, WritableMemory var2);

   public int getCompactBytes() {
      return this.getCurrentBytes();
   }

   int getCurrentDataLongs() {
      return this.getRetainedEntries(true);
   }

   public Family getFamily() {
      return Family.COMPACT;
   }

   public boolean isCompact() {
      return true;
   }

   public byte[] toByteArrayCompressed() {
      return this.isOrdered() && this.getRetainedEntries() != 0 && (this.getRetainedEntries() != 1 || this.isEstimationMode()) ? this.toByteArrayV4() : this.toByteArray();
   }

   private int computeMinLeadingZeros() {
      long previous = 0L;
      long ored = 0L;

      for(HashIterator it = this.iterator(); it.next(); previous = it.get()) {
         long delta = it.get() - previous;
         ored |= delta;
      }

      return Long.numberOfLeadingZeros(ored);
   }

   private static int wholeBytesToHoldBits(int bits) {
      return (bits >>> 3) + ((bits & 7) > 0 ? 1 : 0);
   }

   private byte[] toByteArrayV4() {
      int preambleLongs = this.isEstimationMode() ? 2 : 1;
      int entryBits = 64 - this.computeMinLeadingZeros();
      int compressedBits = entryBits * this.getRetainedEntries();
      int numEntriesBytes = wholeBytesToHoldBits(32 - Integer.numberOfLeadingZeros(this.getRetainedEntries()));
      int size = preambleLongs * 8 + numEntriesBytes + wholeBytesToHoldBits(compressedBits);
      byte[] bytes = new byte[size];
      WritableMemory mem = WritableMemory.writableWrap(bytes);
      int offsetBytes = 0;
      mem.putByte((long)(offsetBytes++), (byte)preambleLongs);
      mem.putByte((long)(offsetBytes++), (byte)4);
      mem.putByte((long)(offsetBytes++), (byte)Family.COMPACT.getID());
      mem.putByte((long)(offsetBytes++), (byte)entryBits);
      mem.putByte((long)(offsetBytes++), (byte)numEntriesBytes);
      mem.putByte((long)(offsetBytes++), (byte)26);
      mem.putShort((long)offsetBytes, this.getSeedHash());
      offsetBytes += 2;
      if (this.isEstimationMode()) {
         mem.putLong((long)offsetBytes, this.getThetaLong());
         offsetBytes += 8;
      }

      int numEntries = this.getRetainedEntries();

      for(int i = 0; i < numEntriesBytes; ++i) {
         mem.putByte((long)(offsetBytes++), (byte)(numEntries & 255));
         numEntries >>>= 8;
      }

      long previous = 0L;
      long[] deltas = new long[8];
      HashIterator it = this.iterator();

      int i;
      for(i = 0; i + 7 < this.getRetainedEntries(); i += 8) {
         for(int j = 0; j < 8; ++j) {
            it.next();
            deltas[j] = it.get() - previous;
            previous = it.get();
         }

         BitPacking.packBitsBlock8(deltas, 0, bytes, offsetBytes, entryBits);
         offsetBytes += entryBits;
      }

      for(int offsetBits = 0; i < this.getRetainedEntries(); ++i) {
         it.next();
         long delta = it.get() - previous;
         previous = it.get();
         BitPacking.packBits(delta, entryBits, bytes, offsetBytes, offsetBits);
         offsetBytes += offsetBits + entryBits >>> 3;
         offsetBits = offsetBits + entryBits & 7;
      }

      return bytes;
   }

   private static CompactSketch heapifyV4(Memory srcMem, long seed, boolean enforceSeed) {
      int preLongs = PreambleUtil.extractPreLongs(srcMem);
      int flags = PreambleUtil.extractFlags(srcMem);
      int entryBits = PreambleUtil.extractEntryBitsV4(srcMem);
      int numEntriesBytes = PreambleUtil.extractNumEntriesBytesV4(srcMem);
      short seedHash = (short)PreambleUtil.extractSeedHash(srcMem);
      boolean isEmpty = (flags & 4) > 0;
      if (enforceSeed && !isEmpty) {
         PreambleUtil.checkMemorySeedHash(srcMem, seed);
      }

      int offsetBytes = 8;
      long theta = Long.MAX_VALUE;
      if (preLongs > 1) {
         theta = PreambleUtil.extractThetaLongV4(srcMem);
         offsetBytes += 8;
      }

      int numEntries = 0;

      for(int i = 0; i < numEntriesBytes; ++i) {
         numEntries |= Byte.toUnsignedInt(srcMem.getByte((long)(offsetBytes++))) << (i << 3);
      }

      long[] entries = new long[numEntries];
      byte[] bytes = new byte[entryBits];

      int i;
      for(i = 0; i + 7 < numEntries; i += 8) {
         srcMem.getByteArray((long)offsetBytes, bytes, 0, entryBits);
         BitPacking.unpackBitsBlock8(entries, i, bytes, 0, entryBits);
         offsetBytes += entryBits;
      }

      if (i < numEntries) {
         srcMem.getByteArray((long)offsetBytes, bytes, 0, wholeBytesToHoldBits((numEntries - i) * entryBits));
         int offsetBits = 0;

         for(int var19 = 0; i < numEntries; ++i) {
            BitPacking.unpackBits(entries, i, entryBits, bytes, var19, offsetBits);
            var19 += offsetBits + entryBits >>> 3;
            offsetBits = offsetBits + entryBits & 7;
         }
      }

      long previous = 0L;

      for(int var21 = 0; var21 < numEntries; ++var21) {
         entries[var21] += previous;
         previous = entries[var21];
      }

      return new HeapCompactSketch(entries, isEmpty, seedHash, numEntries, theta, true);
   }
}
