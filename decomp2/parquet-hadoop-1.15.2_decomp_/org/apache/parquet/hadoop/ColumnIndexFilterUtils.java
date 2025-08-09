package org.apache.parquet.hadoop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.Optional;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.internal.column.columnindex.OffsetIndex;
import org.apache.parquet.internal.filter2.columnindex.RowRanges;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntArrayList;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntList;

class ColumnIndexFilterUtils {
   static OffsetIndex filterOffsetIndex(OffsetIndex offsetIndex, RowRanges rowRanges, long totalRowCount) {
      IntList indexMap = new IntArrayList();
      int i = 0;

      for(int n = offsetIndex.getPageCount(); i < n; ++i) {
         long from = offsetIndex.getFirstRowIndex(i);
         if (rowRanges.isOverlapping(from, offsetIndex.getLastRowIndex(i, totalRowCount))) {
            indexMap.add(i);
         }
      }

      return new FilteredOffsetIndex(offsetIndex, indexMap.toIntArray());
   }

   static List calculateOffsetRanges(OffsetIndex offsetIndex, ColumnChunkMetaData cm, long firstPageOffset) {
      List<OffsetRange> ranges = new ArrayList();
      int n = offsetIndex.getPageCount();
      if (n > 0) {
         OffsetRange currentRange = null;
         long rowGroupOffset = cm.getStartingPos();
         if (rowGroupOffset < firstPageOffset) {
            currentRange = new OffsetRange(rowGroupOffset, (int)(firstPageOffset - rowGroupOffset));
            ranges.add(currentRange);
         }

         for(int i = 0; i < n; ++i) {
            long offset = offsetIndex.getOffset(i);
            int length = offsetIndex.getCompressedPageSize(i);
            if (currentRange == null || !currentRange.extend(offset, length)) {
               currentRange = new OffsetRange(offset, length);
               ranges.add(currentRange);
            }
         }
      }

      return ranges;
   }

   static class OffsetRange {
      private final long offset;
      private long length;

      private OffsetRange(long offset, int length) {
         this.offset = offset;
         this.length = (long)length;
      }

      long getOffset() {
         return this.offset;
      }

      long getLength() {
         return this.length;
      }

      private boolean extend(long offset, int length) {
         if (this.offset + this.length == offset) {
            this.length += (long)length;
            return true;
         } else {
            return false;
         }
      }
   }

   private static class FilteredOffsetIndex implements OffsetIndex {
      private final OffsetIndex offsetIndex;
      private final int[] indexMap;

      private FilteredOffsetIndex(OffsetIndex offsetIndex, int[] indexMap) {
         this.offsetIndex = offsetIndex;
         this.indexMap = indexMap;
      }

      public int getPageOrdinal(int pageIndex) {
         return this.indexMap[pageIndex];
      }

      public int getPageCount() {
         return this.indexMap.length;
      }

      public long getOffset(int pageIndex) {
         return this.offsetIndex.getOffset(this.indexMap[pageIndex]);
      }

      public int getCompressedPageSize(int pageIndex) {
         return this.offsetIndex.getCompressedPageSize(this.indexMap[pageIndex]);
      }

      public long getFirstRowIndex(int pageIndex) {
         return this.offsetIndex.getFirstRowIndex(this.indexMap[pageIndex]);
      }

      public long getLastRowIndex(int pageIndex, long totalRowCount) {
         int nextIndex = this.indexMap[pageIndex] + 1;
         return (nextIndex >= this.offsetIndex.getPageCount() ? totalRowCount : this.offsetIndex.getFirstRowIndex(nextIndex)) - 1L;
      }

      public Optional getUnencodedByteArrayDataBytes(int pageIndex) {
         return this.offsetIndex.getUnencodedByteArrayDataBytes(this.indexMap[pageIndex]);
      }

      public String toString() {
         Formatter formatter = new Formatter();
         Throwable var2 = null;

         String var16;
         try {
            formatter.format("%-12s  %20s  %16s  %20s\n", "", "offset", "compressed size", "first row index");
            int i = 0;

            for(int n = this.offsetIndex.getPageCount(); i < n; ++i) {
               int index = Arrays.binarySearch(this.indexMap, i);
               boolean isHidden = index < 0;
               formatter.format("%spage-%-5d  %20d  %16d  %20d\n", isHidden ? "- " : "  ", isHidden ? i : index, this.offsetIndex.getOffset(i), this.offsetIndex.getCompressedPageSize(i), this.offsetIndex.getFirstRowIndex(i));
            }

            var16 = formatter.toString();
         } catch (Throwable var14) {
            var2 = var14;
            throw var14;
         } finally {
            if (formatter != null) {
               if (var2 != null) {
                  try {
                     formatter.close();
                  } catch (Throwable var13) {
                     var2.addSuppressed(var13);
                  }
               } else {
                  formatter.close();
               }
            }

         }

         return var16;
      }
   }
}
