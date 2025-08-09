package org.apache.datasketches.theta;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.hash.MurmurHash3;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.ThetaUtil;

public abstract class UpdateSketch extends Sketch {
   UpdateSketch() {
   }

   public static UpdateSketch wrap(WritableMemory srcMem) {
      return wrap(srcMem, 9001L);
   }

   public static UpdateSketch wrap(WritableMemory srcMem, long expectedSeed) {
      Objects.requireNonNull(srcMem, "Source Memory must not be null");
      Util.checkBounds(0L, 24L, srcMem.getCapacity());
      int preLongs = srcMem.getByte(0L) & 63;
      int serVer = srcMem.getByte(1L) & 255;
      int familyID = srcMem.getByte(2L) & 255;
      Family family = Family.idToFamily(familyID);
      if (family != Family.QUICKSELECT) {
         throw new SketchesArgumentException("A " + family + " sketch cannot be wrapped as an UpdateSketch.");
      } else if (serVer == 3 && preLongs == 3) {
         return DirectQuickSelectSketch.writableWrap(srcMem, expectedSeed);
      } else {
         throw new SketchesArgumentException("Corrupted: An UpdateSketch image must have SerVer = 3 and preLongs = 3");
      }
   }

   public static UpdateSketch heapify(Memory srcMem) {
      return heapify(srcMem, 9001L);
   }

   public static UpdateSketch heapify(Memory srcMem, long expectedSeed) {
      Objects.requireNonNull(srcMem, "Source Memory must not be null");
      Util.checkBounds(0L, 24L, srcMem.getCapacity());
      Family family = Family.idToFamily(srcMem.getByte(2L));
      return (UpdateSketch)(family.equals(Family.ALPHA) ? HeapAlphaSketch.heapifyInstance(srcMem, expectedSeed) : HeapQuickSelectSketch.heapifyInstance(srcMem, expectedSeed));
   }

   public CompactSketch compact(boolean dstOrdered, WritableMemory dstMem) {
      return CompactOperations.componentsToCompact(this.getThetaLong(), this.getRetainedEntries(true), this.getSeedHash(), this.isEmpty(), false, false, dstOrdered, dstMem, this.getCache());
   }

   public int getCompactBytes() {
      int preLongs = this.getCompactPreambleLongs();
      int dataLongs = this.getRetainedEntries(true);
      return preLongs + dataLongs << 3;
   }

   int getCurrentDataLongs() {
      return 1 << this.getLgArrLongs();
   }

   public boolean isCompact() {
      return false;
   }

   public boolean isOrdered() {
      return false;
   }

   public static final UpdateSketchBuilder builder() {
      return new UpdateSketchBuilder();
   }

   public abstract ResizeFactor getResizeFactor();

   abstract float getP();

   abstract long getSeed();

   public abstract void reset();

   public abstract UpdateSketch rebuild();

   public UpdateReturnState update(long datum) {
      long[] data = new long[]{datum};
      return this.hashUpdate(MurmurHash3.hash(data, this.getSeed())[0] >>> 1);
   }

   public UpdateReturnState update(double datum) {
      double d = datum == (double)0.0F ? (double)0.0F : datum;
      long[] data = new long[]{Double.doubleToLongBits(d)};
      return this.hashUpdate(MurmurHash3.hash(data, this.getSeed())[0] >>> 1);
   }

   public UpdateReturnState update(String datum) {
      if (datum != null && !datum.isEmpty()) {
         byte[] data = datum.getBytes(StandardCharsets.UTF_8);
         return this.hashUpdate(MurmurHash3.hash(data, this.getSeed())[0] >>> 1);
      } else {
         return UpdateReturnState.RejectedNullOrEmpty;
      }
   }

   public UpdateReturnState update(byte[] data) {
      return data != null && data.length != 0 ? this.hashUpdate(MurmurHash3.hash(data, this.getSeed())[0] >>> 1) : UpdateReturnState.RejectedNullOrEmpty;
   }

   public UpdateReturnState update(ByteBuffer buffer) {
      return buffer != null && buffer.hasRemaining() ? this.hashUpdate(MurmurHash3.hash(buffer, this.getSeed())[0] >>> 1) : UpdateReturnState.RejectedNullOrEmpty;
   }

   public UpdateReturnState update(char[] data) {
      return data != null && data.length != 0 ? this.hashUpdate(MurmurHash3.hash(data, this.getSeed())[0] >>> 1) : UpdateReturnState.RejectedNullOrEmpty;
   }

   public UpdateReturnState update(int[] data) {
      return data != null && data.length != 0 ? this.hashUpdate(MurmurHash3.hash(data, this.getSeed())[0] >>> 1) : UpdateReturnState.RejectedNullOrEmpty;
   }

   public UpdateReturnState update(long[] data) {
      return data != null && data.length != 0 ? this.hashUpdate(MurmurHash3.hash(data, this.getSeed())[0] >>> 1) : UpdateReturnState.RejectedNullOrEmpty;
   }

   abstract UpdateReturnState hashUpdate(long var1);

   abstract int getLgArrLongs();

   public abstract int getLgNomLongs();

   abstract boolean isDirty();

   abstract boolean isOutOfSpace(int var1);

   static void checkUnionQuickSelectFamily(Memory mem, int preambleLongs, int lgNomLongs) {
      int familyID = PreambleUtil.extractFamilyID(mem);
      Family family = Family.idToFamily(familyID);
      if (family.equals(Family.UNION)) {
         if (preambleLongs != Family.UNION.getMinPreLongs()) {
            throw new SketchesArgumentException("Possible corruption: Invalid PreambleLongs value for UNION: " + preambleLongs);
         }
      } else {
         if (!family.equals(Family.QUICKSELECT)) {
            throw new SketchesArgumentException("Possible corruption: Invalid Family: " + family.toString());
         }

         if (preambleLongs != Family.QUICKSELECT.getMinPreLongs()) {
            throw new SketchesArgumentException("Possible corruption: Invalid PreambleLongs value for QUICKSELECT: " + preambleLongs);
         }
      }

      if (lgNomLongs < 4) {
         throw new SketchesArgumentException("Possible corruption: Current Memory lgNomLongs < min required size: " + lgNomLongs + " < " + 4);
      }
   }

   static void checkMemIntegrity(Memory srcMem, long expectedSeed, int preambleLongs, int lgNomLongs, int lgArrLongs) {
      int serVer = PreambleUtil.extractSerVer(srcMem);
      if (serVer != 3) {
         throw new SketchesArgumentException("Possible corruption: Invalid Serialization Version: " + serVer);
      } else {
         int flags = PreambleUtil.extractFlags(srcMem);
         int flagsMask = 27;
         if ((flags & 27) > 0) {
            throw new SketchesArgumentException("Possible corruption: Input srcMem cannot be: big-endian, compact, ordered, or read-only");
         } else {
            short seedHash = PreambleUtil.checkMemorySeedHash(srcMem, expectedSeed);
            ThetaUtil.checkSeedHashes(seedHash, ThetaUtil.computeSeedHash(expectedSeed));
            long curCapBytes = srcMem.getCapacity();
            int minReqBytes = PreambleUtil.getMemBytes(lgArrLongs, preambleLongs);
            if (curCapBytes < (long)minReqBytes) {
               throw new SketchesArgumentException("Possible corruption: Current Memory size < min required size: " + curCapBytes + " < " + minReqBytes);
            } else {
               float p = PreambleUtil.extractP(srcMem);
               long thetaLong = PreambleUtil.extractThetaLong(srcMem);
               double theta = (double)thetaLong / (double)Long.MAX_VALUE;
               if (lgArrLongs <= lgNomLongs && theta < (double)p) {
                  throw new SketchesArgumentException("Possible corruption: Theta cannot be < p and lgArrLongs <= lgNomLongs. " + lgArrLongs + " <= " + lgNomLongs + ", Theta: " + theta + ", p: " + p);
               }
            }
         }
      }
   }

   static boolean isResizeFactorIncorrect(Memory srcMem, int lgNomLongs, int lgArrLongs) {
      int lgT = lgNomLongs + 1;
      int lgR = PreambleUtil.extractLgResizeFactor(srcMem);
      if (lgR == 0) {
         return lgArrLongs != lgT;
      } else {
         return (lgT - lgArrLongs) % lgR != 0;
      }
   }
}
