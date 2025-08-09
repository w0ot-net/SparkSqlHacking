package org.apache.parquet.internal.column.columnindex;

import java.util.Formatter;
import java.util.Optional;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntArrayList;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntList;
import shaded.parquet.it.unimi.dsi.fastutil.longs.LongArrayList;
import shaded.parquet.it.unimi.dsi.fastutil.longs.LongList;

public class OffsetIndexBuilder {
   private static final OffsetIndexBuilder NO_OP_BUILDER = new OffsetIndexBuilder() {
      public void add(int compressedPageSize, long rowCount) {
      }

      public void add(long offset, int compressedPageSize, long rowCount) {
      }

      public OffsetIndex build() {
         return null;
      }

      public OffsetIndex build(long shift) {
         return null;
      }
   };
   private final LongList offsets;
   private final IntList compressedPageSizes;
   private final LongList firstRowIndexes;
   private final LongList unencodedDataBytes;
   private long previousOffset;
   private int previousPageSize;
   private long previousRowIndex;
   private long previousRowCount;

   public static OffsetIndexBuilder getNoOpBuilder() {
      return NO_OP_BUILDER;
   }

   public static OffsetIndexBuilder getBuilder() {
      return new OffsetIndexBuilder();
   }

   private OffsetIndexBuilder() {
      this.offsets = new LongArrayList();
      this.compressedPageSizes = new IntArrayList();
      this.firstRowIndexes = new LongArrayList();
      this.unencodedDataBytes = new LongArrayList();
   }

   public void add(int compressedPageSize, long rowCount) {
      this.add(compressedPageSize, rowCount, Optional.empty());
   }

   public void add(int compressedPageSize, long rowCount, Optional unencodedDataBytes) {
      this.add(this.previousOffset + (long)this.previousPageSize, compressedPageSize, this.previousRowIndex + this.previousRowCount, unencodedDataBytes);
      this.previousRowCount = rowCount;
   }

   public void add(long offset, int compressedPageSize, long firstRowIndex) {
      this.add(offset, compressedPageSize, firstRowIndex, Optional.empty());
   }

   public void add(long offset, int compressedPageSize, long firstRowIndex, Optional unencodedDataBytes) {
      this.previousOffset = offset;
      this.offsets.add(offset);
      this.previousPageSize = compressedPageSize;
      this.compressedPageSizes.add(compressedPageSize);
      this.previousRowIndex = firstRowIndex;
      this.firstRowIndexes.add(firstRowIndex);
      if (unencodedDataBytes.isPresent()) {
         this.unencodedDataBytes.add((Long)unencodedDataBytes.get());
      }

   }

   public OffsetIndex build() {
      return this.build(0L);
   }

   public OffsetIndexBuilder fromOffsetIndex(OffsetIndex offsetIndex) {
      assert offsetIndex instanceof OffsetIndexImpl;

      OffsetIndexImpl offsetIndexImpl = (OffsetIndexImpl)offsetIndex;
      this.offsets.addAll(new LongArrayList(offsetIndexImpl.offsets));
      this.compressedPageSizes.addAll(new IntArrayList(offsetIndexImpl.compressedPageSizes));
      this.firstRowIndexes.addAll(new LongArrayList(offsetIndexImpl.firstRowIndexes));
      if (offsetIndexImpl.unencodedByteArrayDataBytes != null) {
         this.unencodedDataBytes.addAll(new LongArrayList(offsetIndexImpl.unencodedByteArrayDataBytes));
      }

      this.previousOffset = 0L;
      this.previousPageSize = 0;
      this.previousRowIndex = 0L;
      this.previousRowCount = 0L;
      return this;
   }

   public OffsetIndex build(long shift) {
      if (this.compressedPageSizes.isEmpty()) {
         return null;
      } else {
         long[] offsets = this.offsets.toLongArray();
         if (shift != 0L) {
            int i = 0;

            for(int n = offsets.length; i < n; ++i) {
               offsets[i] += shift;
            }
         }

         OffsetIndexImpl offsetIndex = new OffsetIndexImpl();
         offsetIndex.offsets = offsets;
         offsetIndex.compressedPageSizes = this.compressedPageSizes.toIntArray();
         offsetIndex.firstRowIndexes = this.firstRowIndexes.toLongArray();
         if (!this.unencodedDataBytes.isEmpty()) {
            if (this.unencodedDataBytes.size() != this.offsets.size()) {
               throw new IllegalStateException("unencodedDataBytes does not have the same size as offsets");
            }

            offsetIndex.unencodedByteArrayDataBytes = this.unencodedDataBytes.toLongArray();
         }

         return offsetIndex;
      }
   }

   private static class OffsetIndexImpl implements OffsetIndex {
      private long[] offsets;
      private int[] compressedPageSizes;
      private long[] firstRowIndexes;
      private long[] unencodedByteArrayDataBytes;

      private OffsetIndexImpl() {
      }

      public String toString() {
         Formatter formatter = new Formatter();
         Throwable var2 = null;

         String var14;
         try {
            formatter.format("%-10s  %20s  %16s  %20s\n", "", "offset", "compressed size", "first row index");
            int i = 0;

            for(int n = this.offsets.length; i < n; ++i) {
               formatter.format("page-%-5d  %20d  %16d  %20d\n", i, this.offsets[i], this.compressedPageSizes[i], this.firstRowIndexes[i]);
            }

            var14 = formatter.toString();
         } catch (Throwable var12) {
            var2 = var12;
            throw var12;
         } finally {
            if (formatter != null) {
               if (var2 != null) {
                  try {
                     formatter.close();
                  } catch (Throwable var11) {
                     var2.addSuppressed(var11);
                  }
               } else {
                  formatter.close();
               }
            }

         }

         return var14;
      }

      public int getPageCount() {
         return this.offsets.length;
      }

      public long getOffset(int pageIndex) {
         return this.offsets[pageIndex];
      }

      public int getCompressedPageSize(int pageIndex) {
         return this.compressedPageSizes[pageIndex];
      }

      public long getFirstRowIndex(int pageIndex) {
         return this.firstRowIndexes[pageIndex];
      }

      public int getPageOrdinal(int pageIndex) {
         return pageIndex;
      }

      public Optional getUnencodedByteArrayDataBytes(int pageIndex) {
         return this.unencodedByteArrayDataBytes != null && this.unencodedByteArrayDataBytes.length != 0 ? Optional.of(this.unencodedByteArrayDataBytes[pageIndex]) : Optional.empty();
      }
   }
}
