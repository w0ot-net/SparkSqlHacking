package org.apache.datasketches.theta;

import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.MemoryStatus;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.BinomialBoundsN;
import org.apache.datasketches.thetacommon.HashOperations;

public abstract class Sketch implements MemoryStatus {
   static final int DEFAULT_LG_RESIZE_FACTOR = 3;

   Sketch() {
   }

   public static Sketch heapify(Memory srcMem) {
      byte familyID = srcMem.getByte(2L);
      Family family = Family.idToFamily(familyID);
      return (Sketch)(family == Family.COMPACT ? CompactSketch.heapify(srcMem) : heapifyUpdateFromMemory(srcMem, 9001L));
   }

   public static Sketch heapify(Memory srcMem, long expectedSeed) {
      byte familyID = srcMem.getByte(2L);
      Family family = Family.idToFamily(familyID);
      return (Sketch)(family == Family.COMPACT ? CompactSketch.heapify(srcMem, expectedSeed) : heapifyUpdateFromMemory(srcMem, expectedSeed));
   }

   public static Sketch wrap(Memory srcMem) {
      int preLongs = srcMem.getByte(0L) & 63;
      int serVer = srcMem.getByte(1L) & 255;
      int familyID = srcMem.getByte(2L) & 255;
      Family family = Family.idToFamily(familyID);
      if (family == Family.QUICKSELECT) {
         if (serVer == 3 && preLongs == 3) {
            return DirectQuickSelectSketchR.readOnlyWrap(srcMem, 9001L);
         } else {
            throw new SketchesArgumentException("Corrupted: " + family + " family image: must have SerVer = 3 and preLongs = 3");
         }
      } else if (family == Family.COMPACT) {
         return CompactSketch.wrap(srcMem);
      } else {
         throw new SketchesArgumentException("Cannot wrap family: " + family + " as a Sketch");
      }
   }

   public static Sketch wrap(Memory srcMem, long expectedSeed) {
      int preLongs = srcMem.getByte(0L) & 63;
      int serVer = srcMem.getByte(1L) & 255;
      int familyID = srcMem.getByte(2L) & 255;
      Family family = Family.idToFamily(familyID);
      if (family == Family.QUICKSELECT) {
         if (serVer == 3 && preLongs == 3) {
            return DirectQuickSelectSketchR.readOnlyWrap(srcMem, expectedSeed);
         } else {
            throw new SketchesArgumentException("Corrupted: " + family + " family image: must have SerVer = 3 and preLongs = 3");
         }
      } else if (family == Family.COMPACT) {
         return CompactSketch.wrap(srcMem, expectedSeed);
      } else {
         throw new SketchesArgumentException("Cannot wrap family: " + family + " as a Sketch");
      }
   }

   public CompactSketch compact() {
      return this.isCompact() ? (CompactSketch)this : this.compact(true, (WritableMemory)null);
   }

   public abstract CompactSketch compact(boolean var1, WritableMemory var2);

   public abstract int getCompactBytes();

   public int getCountLessThanThetaLong(long thetaLong) {
      return HashOperations.count(this.getCache(), thetaLong);
   }

   public abstract int getCurrentBytes();

   public abstract double getEstimate();

   public abstract Family getFamily();

   public double getLowerBound(int numStdDev) {
      return this.isEstimationMode() ? lowerBound(this.getRetainedEntries(true), this.getThetaLong(), numStdDev, this.isEmpty()) : (double)this.getRetainedEntries(true);
   }

   public static int getMaxCompactSketchBytes(int numberOfEntries) {
      if (numberOfEntries == 0) {
         return 8;
      } else {
         return numberOfEntries == 1 ? 16 : (numberOfEntries << 3) + 24;
      }
   }

   public static int getCompactSketchMaxBytes(int lgNomEntries) {
      return (int)((double)(2 << lgNomEntries) * (double)0.9375F + (double)Family.QUICKSELECT.getMaxPreLongs()) * 8;
   }

   public static int getMaxUpdateSketchBytes(int nomEntries) {
      int nomEnt = Util.ceilingPowerOf2(nomEntries);
      return (nomEnt << 4) + (Family.QUICKSELECT.getMaxPreLongs() << 3);
   }

   public int getRetainedEntries() {
      return this.getRetainedEntries(true);
   }

   public abstract int getRetainedEntries(boolean var1);

   public static int getSerializationVersion(Memory mem) {
      return mem.getByte(1L);
   }

   public double getTheta() {
      return (double)this.getThetaLong() / (double)Long.MAX_VALUE;
   }

   public abstract long getThetaLong();

   public double getUpperBound(int numStdDev) {
      return this.isEstimationMode() ? upperBound(this.getRetainedEntries(true), this.getThetaLong(), numStdDev, this.isEmpty()) : (double)this.getRetainedEntries(true);
   }

   public abstract boolean isCompact();

   public abstract boolean isEmpty();

   public boolean isEstimationMode() {
      return estMode(this.getThetaLong(), this.isEmpty());
   }

   public abstract boolean isOrdered();

   public abstract HashIterator iterator();

   public abstract byte[] toByteArray();

   public String toString() {
      return this.toString(true, false, 8, true);
   }

   public String toString(boolean sketchSummary, boolean dataDetail, int width, boolean hexMode) {
      StringBuilder sb = new StringBuilder();
      long[] cache = this.getCache();
      int nomLongs = 0;
      int arrLongs = cache.length;
      float p = 0.0F;
      int rf = 0;
      boolean updateSketch = this instanceof UpdateSketch;
      long thetaLong = this.getThetaLong();
      int curCount = this.getRetainedEntries(true);
      if (updateSketch) {
         UpdateSketch uis = (UpdateSketch)this;
         nomLongs = 1 << uis.getLgNomLongs();
         arrLongs = 1 << uis.getLgArrLongs();
         p = uis.getP();
         rf = uis.getResizeFactor().getValue();
      }

      if (dataDetail) {
         int w = width > 0 ? width : 8;
         if (curCount > 0) {
            sb.append("### SKETCH DATA DETAIL");
            int i = 0;

            for(int j = 0; i < arrLongs; ++i) {
               long h = cache[i];
               if (h > 0L && h < thetaLong) {
                  if (j % w == 0) {
                     sb.append(Util.LS).append(String.format("   %6d", j + 1));
                  }

                  if (hexMode) {
                     sb.append(" " + Util.zeroPad(Long.toHexString(h), 16) + ",");
                  } else {
                     sb.append(String.format(" %20d,", h));
                  }

                  ++j;
               }
            }

            sb.append(Util.LS).append("### END DATA DETAIL").append(Util.LS + Util.LS);
         }
      }

      if (sketchSummary) {
         double thetaDbl = (double)thetaLong / (double)Long.MAX_VALUE;
         String thetaHex = Util.zeroPad(Long.toHexString(thetaLong), 16);
         String thisSimpleName = this.getClass().getSimpleName();
         int seedHash = Short.toUnsignedInt(this.getSeedHash());
         sb.append(Util.LS);
         sb.append("### ").append(thisSimpleName).append(" SUMMARY: ").append(Util.LS);
         if (updateSketch) {
            sb.append("   Nominal Entries (k)     : ").append(nomLongs).append(Util.LS);
         }

         sb.append("   Estimate                : ").append(this.getEstimate()).append(Util.LS);
         sb.append("   Upper Bound, 95% conf   : ").append(this.getUpperBound(2)).append(Util.LS);
         sb.append("   Lower Bound, 95% conf   : ").append(this.getLowerBound(2)).append(Util.LS);
         if (updateSketch) {
            sb.append("   p                       : ").append(p).append(Util.LS);
         }

         sb.append("   Theta (double)          : ").append(thetaDbl).append(Util.LS);
         sb.append("   Theta (long)            : ").append(thetaLong).append(Util.LS);
         sb.append("   Theta (long) hex        : ").append(thetaHex).append(Util.LS);
         sb.append("   EstMode?                : ").append(this.isEstimationMode()).append(Util.LS);
         sb.append("   Empty?                  : ").append(this.isEmpty()).append(Util.LS);
         sb.append("   Ordered?                : ").append(this.isOrdered()).append(Util.LS);
         if (updateSketch) {
            sb.append("   Resize Factor           : ").append(rf).append(Util.LS);
            sb.append("   Array Size Entries      : ").append(arrLongs).append(Util.LS);
         }

         sb.append("   Retained Entries        : ").append(curCount).append(Util.LS);
         sb.append("   Seed Hash               : ").append(Integer.toHexString(seedHash)).append(" | ").append(seedHash).append(Util.LS);
         sb.append("### END SKETCH SUMMARY").append(Util.LS);
      }

      return sb.toString();
   }

   public static String toString(byte[] byteArr) {
      return PreambleUtil.preambleToString(byteArr);
   }

   public static String toString(Memory mem) {
      return PreambleUtil.preambleToString(mem);
   }

   abstract long[] getCache();

   abstract int getCompactPreambleLongs();

   abstract int getCurrentDataLongs();

   abstract int getCurrentPreambleLongs();

   abstract Memory getMemory();

   abstract short getSeedHash();

   static final boolean isValidSketchID(int id) {
      return id == Family.ALPHA.getID() || id == Family.QUICKSELECT.getID() || id == Family.COMPACT.getID();
   }

   static final void checkSketchAndMemoryFlags(Sketch sketch) {
      Memory mem = sketch.getMemory();
      if (mem != null) {
         int flags = PreambleUtil.extractFlags(mem);
         if ((flags & 8) > 0 ^ sketch.isCompact()) {
            throw new SketchesArgumentException("Possible corruption: Memory Compact Flag inconsistent with Sketch");
         } else if ((flags & 16) > 0 ^ sketch.isOrdered()) {
            throw new SketchesArgumentException("Possible corruption: Memory Ordered Flag inconsistent with Sketch");
         }
      }
   }

   static final double estimate(long thetaLong, int curCount) {
      return (double)curCount * ((double)Long.MAX_VALUE / (double)thetaLong);
   }

   static final double lowerBound(int curCount, long thetaLong, int numStdDev, boolean empty) {
      double theta = (double)thetaLong / (double)Long.MAX_VALUE;
      return BinomialBoundsN.getLowerBound((long)curCount, theta, numStdDev, empty);
   }

   static final double upperBound(int curCount, long thetaLong, int numStdDev, boolean empty) {
      double theta = (double)thetaLong / (double)Long.MAX_VALUE;
      return BinomialBoundsN.getUpperBound((long)curCount, theta, numStdDev, empty);
   }

   private static final boolean estMode(long thetaLong, boolean empty) {
      return thetaLong < Long.MAX_VALUE && !empty;
   }

   private static final Sketch heapifyUpdateFromMemory(Memory srcMem, long expectedSeed) {
      long cap = srcMem.getCapacity();
      if (cap < 8L) {
         throw new SketchesArgumentException("Corrupted: valid sketch must be at least 8 bytes.");
      } else {
         byte familyID = srcMem.getByte(2L);
         Family family = Family.idToFamily(familyID);
         if (family == Family.ALPHA) {
            int flags = PreambleUtil.extractFlags(srcMem);
            boolean compactFlag = (flags & 8) != 0;
            if (compactFlag) {
               throw new SketchesArgumentException("Corrupted: ALPHA family image: cannot be compact");
            } else {
               return HeapAlphaSketch.heapifyInstance(srcMem, expectedSeed);
            }
         } else if (family == Family.QUICKSELECT) {
            return HeapQuickSelectSketch.heapifyInstance(srcMem, expectedSeed);
         } else {
            throw new SketchesArgumentException("Sketch cannot heapify family: " + family + " as a Sketch");
         }
      }
   }
}
