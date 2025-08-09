package org.apache.datasketches.frequencies;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public class ItemsSketch {
   private int lgMaxMapSize;
   private int curMapCap;
   private long offset;
   private long streamWeight;
   private int sampleSize;
   private ReversePurgeItemHashMap hashMap;

   public ItemsSketch(int maxMapSize) {
      this(org.apache.datasketches.common.Util.exactLog2OfInt(maxMapSize, "maxMapSize"), 3);
   }

   ItemsSketch(int lgMaxMapSize, int lgCurMapSize) {
      this.streamWeight = 0L;
      this.lgMaxMapSize = Math.max(lgMaxMapSize, 3);
      int lgCurMapSz = Math.max(lgCurMapSize, 3);
      this.hashMap = new ReversePurgeItemHashMap(1 << lgCurMapSz);
      this.curMapCap = this.hashMap.getCapacity();
      int maxMapCap = (int)((double)(1 << lgMaxMapSize) * ReversePurgeItemHashMap.getLoadFactor());
      this.offset = 0L;
      this.sampleSize = Math.min(1024, maxMapCap);
   }

   public static ItemsSketch getInstance(Memory srcMem, ArrayOfItemsSerDe serDe) {
      Objects.requireNonNull(srcMem, "srcMem must not be null.");
      Objects.requireNonNull(serDe, "serDe must not be null.");
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
            return new ItemsSketch(lgMaxMapSize, 3);
         } else {
            long[] preArr = new long[preLongs];
            srcMem.getLongArray(0L, preArr, 0, preLongs);
            ItemsSketch<T> fis = new ItemsSketch(lgMaxMapSize, lgCurMapSize);
            fis.streamWeight = 0L;
            fis.offset = preArr[3];
            int preBytes = preLongs << 3;
            int activeItems = PreambleUtil.extractActiveItems(preArr[1]);
            long[] countArray = new long[activeItems];
            int reqBytes = preBytes + activeItems * 8;
            org.apache.datasketches.common.Util.checkBounds(0L, (long)reqBytes, srcMem.getCapacity());
            srcMem.getLongArray((long)preBytes, countArray, 0, activeItems);
            int itemsOffset = preBytes + 8 * activeItems;
            T[] itemArray = (T[])serDe.deserializeFromMemory(srcMem.region((long)itemsOffset, srcMem.getCapacity() - (long)itemsOffset), 0L, activeItems);

            for(int i = 0; i < activeItems; ++i) {
               fis.update(itemArray[i], countArray[i]);
            }

            fis.streamWeight = preArr[2];
            return fis;
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

   public long getEstimate(Object item) {
      long itemCount = this.hashMap.get(item);
      return itemCount > 0L ? itemCount + this.offset : 0L;
   }

   public long getLowerBound(Object item) {
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

   public long getStreamLength() {
      return this.streamWeight;
   }

   public long getUpperBound(Object item) {
      return this.hashMap.get(item) + this.offset;
   }

   public boolean isEmpty() {
      return this.getNumActiveItems() == 0;
   }

   public ItemsSketch merge(ItemsSketch other) {
      if (other == null) {
         return this;
      } else if (other.isEmpty()) {
         return this;
      } else {
         long streamLen = this.streamWeight + other.streamWeight;
         ReversePurgeItemHashMap.Iterator<T> iter = other.hashMap.iterator();

         while(iter.next()) {
            this.update(iter.getKey(), iter.getValue());
         }

         this.offset += other.offset;
         this.streamWeight = streamLen;
         return this;
      }
   }

   public void reset() {
      this.hashMap = new ReversePurgeItemHashMap(8);
      this.curMapCap = this.hashMap.getCapacity();
      this.offset = 0L;
      this.streamWeight = 0L;
   }

   public byte[] toByteArray(ArrayOfItemsSerDe serDe) {
      boolean empty = this.isEmpty();
      int activeItems = this.getNumActiveItems();
      byte[] bytes = null;
      int preLongs;
      int outBytes;
      if (empty) {
         preLongs = 1;
         outBytes = 8;
      } else {
         preLongs = Family.FREQUENCY.getMaxPreLongs();
         bytes = serDe.serializeToByteArray(this.hashMap.getActiveKeys());
         outBytes = (preLongs + activeItems << 3) + bytes.length;
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
         mem.putByteArray((long)(preBytes + (this.getNumActiveItems() << 3)), bytes, 0, bytes.length);
      }

      return outArr;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("FrequentItemsSketch<T>:").append(org.apache.datasketches.common.Util.LS);
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

   public void update(Object item) {
      this.update(item, 1L);
   }

   public void update(Object item, long count) {
      if (item != null && count != 0L) {
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
      ArrayList<Row<T>> rowList = new ArrayList();
      ReversePurgeItemHashMap.Iterator<T> iter = this.hashMap.iterator();
      if (errorType == ErrorType.NO_FALSE_NEGATIVES) {
         while(iter.next()) {
            long est = this.getEstimate(iter.getKey());
            long ub = this.getUpperBound(iter.getKey());
            long lb = this.getLowerBound(iter.getKey());
            if (ub >= threshold) {
               Row<T> row = new Row(iter.getKey(), est, ub, lb);
               rowList.add(row);
            }
         }
      } else {
         while(iter.next()) {
            long est = this.getEstimate(iter.getKey());
            long ub = this.getUpperBound(iter.getKey());
            long lb = this.getLowerBound(iter.getKey());
            if (lb >= threshold) {
               Row<T> row = new Row(iter.getKey(), est, ub, lb);
               rowList.add(row);
            }
         }
      }

      rowList.sort(new Comparator() {
         public int compare(Row r1, Row r2) {
            return r2.compareTo(r1);
         }
      });
      Row<T>[] rowsArr = (Row[])rowList.toArray((Row[])Array.newInstance(Row.class, rowList.size()));
      return rowsArr;
   }

   public static class Row implements Comparable {
      final Object item;
      final long est;
      final long ub;
      final long lb;
      private static final String FMT = "  %12d%12d%12d %s";
      private static final String HFMT = "  %12s%12s%12s %s";

      Row(Object item, long estimate, long ub, long lb) {
         this.item = item;
         this.est = estimate;
         this.ub = ub;
         this.lb = lb;
      }

      public Object getItem() {
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
         return String.format("  %12s%12s%12s %s", "Est", "UB", "LB", "Item");
      }

      public String toString() {
         return String.format("  %12d%12d%12d %s", this.est, this.ub, this.lb, this.item.toString());
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
            Row<T> that = (Row)obj;
            return this.est == that.est;
         }
      }
   }
}
