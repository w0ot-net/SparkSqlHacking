package org.apache.datasketches.frequencies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.common.SuppressFBWarnings;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

@SuppressFBWarnings(
   value = {"SIC_INNER_SHOULD_BE_STATIC_ANON"},
   justification = "Harmless, fix later"
)
public class LongsSketch {
   private static final int STR_PREAMBLE_TOKENS = 6;
   private int lgMaxMapSize;
   private int curMapCap;
   private long offset;
   private long streamWeight;
   private int sampleSize;
   private ReversePurgeLongHashMap hashMap;

   public LongsSketch(int maxMapSize) {
      this(org.apache.datasketches.common.Util.exactLog2OfInt(maxMapSize, "maxMapSize"), 3);
   }

   LongsSketch(int lgMaxMapSize, int lgCurMapSize) {
      this.streamWeight = 0L;
      this.lgMaxMapSize = Math.max(lgMaxMapSize, 3);
      int lgCurMapSz = Math.max(lgCurMapSize, 3);
      this.hashMap = new ReversePurgeLongHashMap(1 << lgCurMapSz);
      this.curMapCap = this.hashMap.getCapacity();
      int maxMapCap = (int)((double)(1 << lgMaxMapSize) * ReversePurgeLongHashMap.getLoadFactor());
      this.offset = 0L;
      this.sampleSize = Math.min(1024, maxMapCap);
   }

   public static LongsSketch getInstance(Memory srcMem) {
      Objects.requireNonNull(srcMem, "Source Memory must not be null.");
      long pre0 = PreambleUtil.checkPreambleSize(srcMem);
      int maxPreLongs = Family.FREQUENCY.getMaxPreLongs();
      int preLongs = PreambleUtil.extractPreLongs(pre0);
      int serVer = PreambleUtil.extractSerVer(pre0);
      int familyID = PreambleUtil.extractFamilyID(pre0);
      int lgMaxMapSize = PreambleUtil.extractLgMaxMapSize(pre0);
      int lgCurMapSize = PreambleUtil.extractLgCurMapSize(pre0);
      boolean empty = (PreambleUtil.extractFlags(pre0) & 5) != 0;
      boolean preLongsEq1 = preLongs == 1;
      boolean preLongsEqMax = preLongs == maxPreLongs;
      if (!preLongsEq1 && !preLongsEqMax) {
         throw new SketchesArgumentException("Possible Corruption: PreLongs must be 1 or " + maxPreLongs + ": " + preLongs);
      } else if (serVer != 1) {
         throw new SketchesArgumentException("Possible Corruption: Ser Ver must be 1: " + serVer);
      } else {
         int actFamID = Family.FREQUENCY.getID();
         if (familyID != actFamID) {
            throw new SketchesArgumentException("Possible Corruption: FamilyID must be " + actFamID + ": " + familyID);
         } else if (empty ^ preLongsEq1) {
            throw new SketchesArgumentException("Possible Corruption: (PreLongs == 1) ^ Empty == True.");
         } else if (empty) {
            return new LongsSketch(lgMaxMapSize, 3);
         } else {
            long[] preArr = new long[preLongs];
            srcMem.getLongArray(0L, preArr, 0, preLongs);
            LongsSketch fls = new LongsSketch(lgMaxMapSize, lgCurMapSize);
            fls.streamWeight = 0L;
            fls.offset = preArr[3];
            int preBytes = preLongs << 3;
            int activeItems = PreambleUtil.extractActiveItems(preArr[1]);
            long[] countArray = new long[activeItems];
            int reqBytes = preBytes + 2 * activeItems * 8;
            org.apache.datasketches.common.Util.checkBounds(0L, (long)reqBytes, srcMem.getCapacity());
            srcMem.getLongArray((long)preBytes, countArray, 0, activeItems);
            int itemsOffset = preBytes + 8 * activeItems;
            long[] itemArray = new long[activeItems];
            srcMem.getLongArray((long)itemsOffset, itemArray, 0, activeItems);

            for(int i = 0; i < activeItems; ++i) {
               fls.update(itemArray[i], countArray[i]);
            }

            fls.streamWeight = preArr[2];
            return fls;
         }
      }
   }

   public static LongsSketch getInstance(String string) {
      Objects.requireNonNull(string, "string must not be null.");
      String[] tokens = string.split(",");
      if (tokens.length < 8) {
         throw new SketchesArgumentException("String not long enough: " + tokens.length);
      } else {
         int serVer = Integer.parseInt(tokens[0]);
         int famID = Integer.parseInt(tokens[1]);
         int lgMax = Integer.parseInt(tokens[2]);
         int flags = Integer.parseInt(tokens[3]);
         long streamWt = Long.parseLong(tokens[4]);
         long offset = Long.parseLong(tokens[5]);
         int numActive = Integer.parseInt(tokens[6]);
         int lgCur = Integer.numberOfTrailingZeros(Integer.parseInt(tokens[7]));
         if (serVer != 1) {
            throw new SketchesArgumentException("Possible Corruption: Bad SerVer: " + serVer);
         } else {
            Family.FREQUENCY.checkFamilyID(famID);
            boolean empty = flags > 0;
            if (!empty && numActive == 0) {
               throw new SketchesArgumentException("Possible Corruption: !Empty && NumActive=0;  strLen: " + numActive);
            } else {
               int numTokens = tokens.length;
               if (2 * numActive != numTokens - 6 - 2) {
                  throw new SketchesArgumentException("Possible Corruption: Incorrect # of tokens: " + numTokens + ", numActive: " + numActive);
               } else {
                  LongsSketch sketch = new LongsSketch(lgMax, lgCur);
                  sketch.streamWeight = streamWt;
                  sketch.offset = offset;
                  sketch.hashMap = deserializeFromStringArray(tokens);
                  return sketch;
               }
            }
         }
      }
   }

   public static double getAprioriError(int maxMapSize, long estimatedTotalStreamWeight) {
      return getEpsilon(maxMapSize) * (double)estimatedTotalStreamWeight;
   }

   public int getCurrentMapCapacity() {
      return this.curMapCap;
   }

   public static double getEpsilon(int maxMapSize) {
      if (!org.apache.datasketches.common.Util.isPowerOf2((long)maxMapSize)) {
         throw new SketchesArgumentException("maxMapSize is not a power of 2.");
      } else {
         return (double)3.5F / (double)maxMapSize;
      }
   }

   public long getEstimate(long item) {
      long itemCount = this.hashMap.get(item);
      return itemCount > 0L ? itemCount + this.offset : 0L;
   }

   public long getLowerBound(long item) {
      return this.hashMap.get(item);
   }

   public Row[] getFrequentItems(long threshold, ErrorType errorType) {
      return this.sortItems(threshold > this.getMaximumError() ? threshold : this.getMaximumError(), errorType);
   }

   public Row[] getFrequentItems(ErrorType errorType) {
      return this.sortItems(this.getMaximumError(), errorType);
   }

   public long getMaximumError() {
      return this.offset;
   }

   public int getMaximumMapCapacity() {
      return (int)((double)(1 << this.lgMaxMapSize) * ReversePurgeLongHashMap.getLoadFactor());
   }

   public int getNumActiveItems() {
      return this.hashMap.getNumActive();
   }

   public int getStorageBytes() {
      return this.isEmpty() ? 8 : 32 + 16 * this.getNumActiveItems();
   }

   public long getStreamLength() {
      return this.streamWeight;
   }

   public long getUpperBound(long item) {
      return this.hashMap.get(item) + this.offset;
   }

   public boolean isEmpty() {
      return this.getNumActiveItems() == 0;
   }

   public LongsSketch merge(LongsSketch other) {
      if (other == null) {
         return this;
      } else if (other.isEmpty()) {
         return this;
      } else {
         long streamWt = this.streamWeight + other.streamWeight;
         ReversePurgeLongHashMap.Iterator iter = other.hashMap.iterator();

         while(iter.next()) {
            this.update(iter.getKey(), iter.getValue());
         }

         this.offset += other.offset;
         this.streamWeight = streamWt;
         return this;
      }
   }

   public void reset() {
      this.hashMap = new ReversePurgeLongHashMap(8);
      this.curMapCap = this.hashMap.getCapacity();
      this.offset = 0L;
      this.streamWeight = 0L;
   }

   public String serializeToString() {
      StringBuilder sb = new StringBuilder();
      int serVer = 1;
      int famID = Family.FREQUENCY.getID();
      int lgMaxMapSz = this.lgMaxMapSize;
      int flags = this.hashMap.getNumActive() == 0 ? 5 : 0;
      String fmt = "%d,%d,%d,%d,%d,%d,";
      String s = String.format("%d,%d,%d,%d,%d,%d,", 1, famID, lgMaxMapSz, flags, this.streamWeight, this.offset);
      sb.append(s);
      sb.append(this.hashMap.serializeToString());
      return sb.toString();
   }

   public byte[] toByteArray() {
      boolean empty = this.isEmpty();
      int activeItems = this.getNumActiveItems();
      int preLongs;
      int outBytes;
      if (empty) {
         preLongs = 1;
         outBytes = 8;
      } else {
         preLongs = Family.FREQUENCY.getMaxPreLongs();
         outBytes = preLongs + 2 * activeItems << 3;
      }

      byte[] outArr = new byte[outBytes];
      WritableMemory mem = WritableMemory.writableWrap(outArr);
      long pre0 = 0L;
      pre0 = PreambleUtil.insertPreLongs(preLongs, pre0);
      pre0 = PreambleUtil.insertSerVer(1, pre0);
      pre0 = PreambleUtil.insertFamilyID(Family.FREQUENCY.getID(), pre0);
      pre0 = PreambleUtil.insertLgMaxMapSize(this.lgMaxMapSize, pre0);
      pre0 = PreambleUtil.insertLgCurMapSize(this.hashMap.getLgLength(), pre0);
      pre0 = empty ? PreambleUtil.insertFlags(5, pre0) : PreambleUtil.insertFlags(0, pre0);
      if (empty) {
         mem.putLong(0L, pre0);
      } else {
         long pre = 0L;
         long[] preArr = new long[preLongs];
         preArr[0] = pre0;
         preArr[1] = PreambleUtil.insertActiveItems(activeItems, 0L);
         preArr[2] = this.streamWeight;
         preArr[3] = this.offset;
         mem.putLongArray(0L, preArr, 0, preLongs);
         int preBytes = preLongs << 3;
         mem.putLongArray((long)preBytes, this.hashMap.getActiveValues(), 0, activeItems);
         mem.putLongArray((long)(preBytes + (activeItems << 3)), this.hashMap.getActiveKeys(), 0, activeItems);
      }

      return outArr;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("FrequentLongsSketch:").append(org.apache.datasketches.common.Util.LS);
      sb.append("  Stream Length    : " + this.streamWeight).append(org.apache.datasketches.common.Util.LS);
      sb.append("  Max Error Offset : " + this.offset).append(org.apache.datasketches.common.Util.LS);
      sb.append(this.hashMap.toString());
      return sb.toString();
   }

   public static String toString(byte[] byteArr) {
      return toString(Memory.wrap(byteArr));
   }

   public static String toString(Memory mem) {
      return PreambleUtil.preambleToString(mem);
   }

   public void update(long item) {
      this.update(item, 1L);
   }

   public void update(long item, long count) {
      if (count != 0L) {
         if (count < 0L) {
            throw new SketchesArgumentException("Count may not be negative");
         } else {
            this.streamWeight += count;
            this.hashMap.adjustOrPutValue(item, count);
            if (this.getNumActiveItems() > this.curMapCap) {
               if (this.hashMap.getLgLength() < this.lgMaxMapSize) {
                  this.hashMap.resize(2 * this.hashMap.getLength());
                  this.curMapCap = this.hashMap.getCapacity();
               } else {
                  this.offset += this.hashMap.purge(this.sampleSize);
                  if (this.getNumActiveItems() > this.getMaximumMapCapacity()) {
                     throw new SketchesStateException("Purge did not reduce active items.");
                  }
               }
            }

         }
      }
   }

   Row[] sortItems(long threshold, ErrorType errorType) {
      ArrayList<Row> rowList = new ArrayList();
      ReversePurgeLongHashMap.Iterator iter = this.hashMap.iterator();
      if (errorType == ErrorType.NO_FALSE_NEGATIVES) {
         while(iter.next()) {
            long est = this.getEstimate(iter.getKey());
            long ub = this.getUpperBound(iter.getKey());
            long lb = this.getLowerBound(iter.getKey());
            if (ub >= threshold) {
               Row row = new Row(iter.getKey(), est, ub, lb);
               rowList.add(row);
            }
         }
      } else {
         while(iter.next()) {
            long est = this.getEstimate(iter.getKey());
            long ub = this.getUpperBound(iter.getKey());
            long lb = this.getLowerBound(iter.getKey());
            if (lb >= threshold) {
               Row row = new Row(iter.getKey(), est, ub, lb);
               rowList.add(row);
            }
         }
      }

      rowList.sort(new Comparator() {
         public int compare(Row r1, Row r2) {
            return r2.compareTo(r1);
         }
      });
      Row[] rowsArr = (Row[])rowList.toArray(new Row[rowList.size()]);
      return rowsArr;
   }

   static ReversePurgeLongHashMap deserializeFromStringArray(String[] tokens) {
      int ignore = 6;
      int numActive = Integer.parseInt(tokens[6]);
      int length = Integer.parseInt(tokens[7]);
      ReversePurgeLongHashMap hashMap = new ReversePurgeLongHashMap(length);
      int j = 8;

      for(int i = 0; i < numActive; ++i) {
         long key = Long.parseLong(tokens[j++]);
         long value = Long.parseLong(tokens[j++]);
         hashMap.adjustOrPutValue(key, value);
      }

      return hashMap;
   }

   public static class Row implements Comparable {
      final long item;
      final long est;
      final long ub;
      final long lb;
      private static final String fmt = "  %20d%20d%20d %d";
      private static final String hfmt = "  %20s%20s%20s %s";

      Row(long item, long estimate, long ub, long lb) {
         this.item = item;
         this.est = estimate;
         this.ub = ub;
         this.lb = lb;
      }

      public long getItem() {
         return this.item;
      }

      public long getEstimate() {
         return this.est;
      }

      public long getUpperBound() {
         return this.ub;
      }

      public long getLowerBound() {
         return this.lb;
      }

      public static String getRowHeader() {
         return String.format("  %20s%20s%20s %s", "Est", "UB", "LB", "Item");
      }

      public String toString() {
         return String.format("  %20d%20d%20d %d", this.est, this.ub, this.lb, this.item);
      }

      public int compareTo(Row that) {
         return this.est < that.est ? -1 : (this.est > that.est ? 1 : 0);
      }

      public int hashCode() {
         int prime = 31;
         int result = 1;
         result = 31 * result + (int)(this.est ^ this.est >>> 32);
         return result;
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (obj == null) {
            return false;
         } else if (!(obj instanceof Row)) {
            return false;
         } else {
            Row that = (Row)obj;
            return this.est == that.est;
         }
      }
   }
}
