package org.apache.parquet.internal.column.columnindex;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.Set;
import java.util.function.IntPredicate;
import org.apache.parquet.column.MinMax;
import org.apache.parquet.column.statistics.SizeStatistics;
import org.apache.parquet.column.statistics.Statistics;
import org.apache.parquet.filter2.predicate.Operators;
import org.apache.parquet.filter2.predicate.UserDefinedPredicate;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.schema.PrimitiveComparator;
import org.apache.parquet.schema.PrimitiveStringifier;
import org.apache.parquet.schema.PrimitiveType;
import shaded.parquet.it.unimi.dsi.fastutil.booleans.BooleanArrayList;
import shaded.parquet.it.unimi.dsi.fastutil.booleans.BooleanList;
import shaded.parquet.it.unimi.dsi.fastutil.booleans.BooleanLists;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntArrayList;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntList;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntSet;
import shaded.parquet.it.unimi.dsi.fastutil.longs.LongArrayList;
import shaded.parquet.it.unimi.dsi.fastutil.longs.LongList;
import shaded.parquet.it.unimi.dsi.fastutil.longs.LongLists;

public abstract class ColumnIndexBuilder {
   private static final ColumnIndexBuilder NO_OP_BUILDER = new ColumnIndexBuilder() {
      public ColumnIndex build() {
         return null;
      }

      public void add(Statistics stats) {
      }

      public void add(Statistics stats, SizeStatistics sizeStats) {
      }

      void addMinMax(Object min, Object max) {
      }

      ColumnIndexBase createColumnIndex(PrimitiveType type) {
         return null;
      }

      void clearMinMax() {
      }

      void addMinMaxFromBytes(ByteBuffer min, ByteBuffer max) {
      }

      int compareMinValues(PrimitiveComparator comparator, int index1, int index2) {
         return 0;
      }

      int compareMaxValues(PrimitiveComparator comparator, int index1, int index2) {
         return 0;
      }

      int sizeOf(Object value) {
         return 0;
      }

      public long getMinMaxSize() {
         return 0L;
      }
   };
   private PrimitiveType type;
   private final BooleanList nullPages = new BooleanArrayList();
   private final LongList nullCounts = new LongArrayList();
   private final IntList pageIndexes = new IntArrayList();
   private int nextPageIndex;
   private LongList repLevelHistogram = new LongArrayList();
   private LongList defLevelHistogram = new LongArrayList();

   public static ColumnIndexBuilder getNoOpBuilder() {
      return NO_OP_BUILDER;
   }

   public static ColumnIndexBuilder getBuilder(PrimitiveType type, int truncateLength) {
      ColumnIndexBuilder builder = createNewBuilder(type, truncateLength);
      builder.type = type;
      return builder;
   }

   private static ColumnIndexBuilder createNewBuilder(PrimitiveType type, int truncateLength) {
      switch (type.getPrimitiveTypeName()) {
         case BINARY:
         case FIXED_LEN_BYTE_ARRAY:
         case INT96:
            return new BinaryColumnIndexBuilder(type, truncateLength);
         case BOOLEAN:
            return new BooleanColumnIndexBuilder();
         case DOUBLE:
            return new DoubleColumnIndexBuilder();
         case FLOAT:
            return new FloatColumnIndexBuilder();
         case INT32:
            return new IntColumnIndexBuilder();
         case INT64:
            return new LongColumnIndexBuilder();
         default:
            throw new IllegalArgumentException("Unsupported type for column index: " + type);
      }
   }

   public static ColumnIndex build(PrimitiveType type, BoundaryOrder boundaryOrder, List nullPages, List nullCounts, List minValues, List maxValues) {
      return build(type, boundaryOrder, nullPages, nullCounts, minValues, maxValues, (List)null, (List)null);
   }

   public static ColumnIndex build(PrimitiveType type, BoundaryOrder boundaryOrder, List nullPages, List nullCounts, List minValues, List maxValues, List repLevelHistogram, List defLevelHistogram) {
      ColumnIndexBuilder builder = createNewBuilder(type, Integer.MAX_VALUE);
      builder.fill(nullPages, nullCounts, minValues, maxValues, repLevelHistogram, defLevelHistogram);
      ColumnIndexBase<?> columnIndex = builder.build(type);
      columnIndex.boundaryOrder = (BoundaryOrder)Objects.requireNonNull(boundaryOrder);
      return columnIndex;
   }

   ColumnIndexBuilder() {
   }

   public void add(Statistics stats) {
      this.add(stats, (SizeStatistics)null);
   }

   public void add(Statistics stats, SizeStatistics sizeStats) {
      if (stats.hasNonNullValue()) {
         this.nullPages.add(false);
         Object min = stats.genericGetMin();
         Object max = stats.genericGetMax();
         this.addMinMax(min, max);
         this.pageIndexes.add(this.nextPageIndex);
      } else {
         this.nullPages.add(true);
      }

      this.nullCounts.add(stats.getNumNulls());
      if (sizeStats != null && sizeStats.isValid() && this.repLevelHistogram != null && this.defLevelHistogram != null) {
         this.repLevelHistogram.addAll(sizeStats.getRepetitionLevelHistogram());
         this.defLevelHistogram.addAll(sizeStats.getDefinitionLevelHistogram());
      } else {
         this.repLevelHistogram = null;
         this.defLevelHistogram = null;
      }

      ++this.nextPageIndex;
   }

   abstract void addMinMaxFromBytes(ByteBuffer var1, ByteBuffer var2);

   abstract void addMinMax(Object var1, Object var2);

   private void fill(List nullPages, List nullCounts, List minValues, List maxValues, List repLevelHistogram, List defLevelHistogram) {
      this.clear();
      int pageCount = nullPages.size();
      if ((nullCounts == null || nullCounts.size() == pageCount) && minValues.size() == pageCount && maxValues.size() == pageCount) {
         if (repLevelHistogram != null && repLevelHistogram.size() % pageCount != 0) {
            throw new IllegalArgumentException(String.format("Size of repLevelHistogram:%d is not a multiply of pageCount:%d, ", repLevelHistogram.size(), pageCount));
         } else if (defLevelHistogram != null && defLevelHistogram.size() % pageCount != 0) {
            throw new IllegalArgumentException(String.format("Size of defLevelHistogram:%d is not a multiply of pageCount:%d, ", defLevelHistogram.size(), pageCount));
         } else {
            this.nullPages.addAll(nullPages);
            if (nullCounts != null) {
               this.nullCounts.addAll(nullCounts);
            }

            for(int i = 0; i < pageCount; ++i) {
               if (!(Boolean)nullPages.get(i)) {
                  ByteBuffer min = (ByteBuffer)minValues.get(i);
                  ByteBuffer max = (ByteBuffer)maxValues.get(i);
                  this.addMinMaxFromBytes(min, max);
                  this.pageIndexes.add(i);
               }
            }

            if (repLevelHistogram != null) {
               this.repLevelHistogram.addAll(repLevelHistogram);
            }

            if (defLevelHistogram != null) {
               this.defLevelHistogram.addAll(defLevelHistogram);
            }

         }
      } else {
         throw new IllegalArgumentException(String.format("Not all sizes are equal (nullPages:%d, nullCounts:%s, minValues:%d, maxValues:%d", nullPages.size(), nullCounts == null ? "null" : nullCounts.size(), minValues.size(), maxValues.size()));
      }
   }

   public ColumnIndex build() {
      ColumnIndexBase<?> columnIndex = this.build(this.type);
      if (columnIndex == null) {
         return null;
      } else {
         columnIndex.boundaryOrder = this.calculateBoundaryOrder(this.type.comparator());
         return columnIndex;
      }
   }

   private ColumnIndexBase build(PrimitiveType type) {
      if (this.nullPages.isEmpty()) {
         return null;
      } else {
         ColumnIndexBase<?> columnIndex = this.createColumnIndex(type);
         if (columnIndex == null) {
            return null;
         } else {
            columnIndex.nullPages = this.nullPages.toBooleanArray();
            if (!this.nullCounts.isEmpty()) {
               columnIndex.nullCounts = this.nullCounts.toLongArray();
            }

            columnIndex.pageIndexes = this.pageIndexes.toIntArray();
            if (this.repLevelHistogram != null && !this.repLevelHistogram.isEmpty()) {
               columnIndex.repLevelHistogram = this.repLevelHistogram.toLongArray();
            }

            if (this.defLevelHistogram != null && !this.defLevelHistogram.isEmpty()) {
               columnIndex.defLevelHistogram = this.defLevelHistogram.toLongArray();
            }

            return columnIndex;
         }
      }
   }

   private BoundaryOrder calculateBoundaryOrder(PrimitiveComparator comparator) {
      if (this.isAscending(comparator)) {
         return BoundaryOrder.ASCENDING;
      } else {
         return this.isDescending(comparator) ? BoundaryOrder.DESCENDING : BoundaryOrder.UNORDERED;
      }
   }

   private boolean isAscending(PrimitiveComparator comparator) {
      int i = 1;

      for(int n = this.pageIndexes.size(); i < n; ++i) {
         if (this.compareMinValues(comparator, i - 1, i) > 0 || this.compareMaxValues(comparator, i - 1, i) > 0) {
            return false;
         }
      }

      return true;
   }

   private boolean isDescending(PrimitiveComparator comparator) {
      int i = 1;

      for(int n = this.pageIndexes.size(); i < n; ++i) {
         if (this.compareMinValues(comparator, i - 1, i) < 0 || this.compareMaxValues(comparator, i - 1, i) < 0) {
            return false;
         }
      }

      return true;
   }

   abstract int compareMinValues(PrimitiveComparator var1, int var2, int var3);

   abstract int compareMaxValues(PrimitiveComparator var1, int var2, int var3);

   private void clear() {
      this.nullPages.clear();
      this.nullCounts.clear();
      this.clearMinMax();
      this.nextPageIndex = 0;
      this.pageIndexes.clear();
      this.repLevelHistogram.clear();
      this.defLevelHistogram.clear();
   }

   abstract void clearMinMax();

   abstract ColumnIndexBase createColumnIndex(PrimitiveType var1);

   abstract int sizeOf(Object var1);

   public int getPageCount() {
      return this.nullPages.size();
   }

   public long getMinMaxSize() {
      throw new UnsupportedOperationException("Not implemented");
   }

   abstract static class ColumnIndexBase implements ColumnIndex {
      private static final ByteBuffer EMPTY_BYTE_BUFFER = ByteBuffer.allocate(0);
      private static final int MAX_VALUE_LENGTH_FOR_TOSTRING = 40;
      private static final String TOSTRING_TRUNCATION_MARKER = "(...)";
      private static final int TOSTRING_TRUNCATION_START_POS = (40 - "(...)".length()) / 2;
      private static final int TOSTRING_TRUNCATION_END_POS;
      private static final String TOSTRING_MISSING_VALUE_MARKER = "<none>";
      final PrimitiveStringifier stringifier;
      final PrimitiveComparator comparator;
      private boolean[] nullPages;
      private BoundaryOrder boundaryOrder;
      private int[] pageIndexes;
      private long[] nullCounts;
      private long[] repLevelHistogram;
      private long[] defLevelHistogram;

      static String truncate(String str) {
         return str.length() <= 40 ? str : str.substring(0, TOSTRING_TRUNCATION_START_POS) + "(...)" + str.substring(str.length() - TOSTRING_TRUNCATION_END_POS);
      }

      ColumnIndexBase(PrimitiveType type) {
         this.comparator = type.comparator();
         this.stringifier = type.stringifier();
      }

      public BoundaryOrder getBoundaryOrder() {
         return this.boundaryOrder;
      }

      public List getNullCounts() {
         return this.nullCounts == null ? null : LongLists.unmodifiable(LongArrayList.wrap(this.nullCounts));
      }

      public List getNullPages() {
         return BooleanLists.unmodifiable(BooleanArrayList.wrap(this.nullPages));
      }

      public List getMinValues() {
         List<ByteBuffer> list = new ArrayList(this.getPageCount());
         int arrayIndex = 0;
         int i = 0;

         for(int n = this.getPageCount(); i < n; ++i) {
            if (this.isNullPage(i)) {
               list.add(EMPTY_BYTE_BUFFER);
            } else {
               list.add(this.getMinValueAsBytes(arrayIndex++));
            }
         }

         return list;
      }

      public List getMaxValues() {
         List<ByteBuffer> list = new ArrayList(this.getPageCount());
         int arrayIndex = 0;
         int i = 0;

         for(int n = this.getPageCount(); i < n; ++i) {
            if (this.isNullPage(i)) {
               list.add(EMPTY_BYTE_BUFFER);
            } else {
               list.add(this.getMaxValueAsBytes(arrayIndex++));
            }
         }

         return list;
      }

      public List getRepetitionLevelHistogram() {
         return this.repLevelHistogram == null ? LongList.of() : LongLists.unmodifiable(LongArrayList.wrap(this.repLevelHistogram));
      }

      public List getDefinitionLevelHistogram() {
         return this.defLevelHistogram == null ? LongList.of() : LongLists.unmodifiable(LongArrayList.wrap(this.defLevelHistogram));
      }

      public String toString() {
         Formatter formatter = new Formatter();
         Throwable var2 = null;

         String var20;
         try {
            formatter.format("Boundary order: %s\n", this.boundaryOrder);
            String minMaxPart = "  %-40s  %-40s\n";
            formatter.format("%-10s  %20s" + minMaxPart, "", "null count", "min", "max");
            String format = "page-%-5d  %20s" + minMaxPart;
            int arrayIndex = 0;
            int i = 0;

            for(int n = this.nullPages.length; i < n; ++i) {
               String nullCount = this.nullCounts == null ? "<none>" : Long.toString(this.nullCounts[i]);
               String min;
               String max;
               if (this.nullPages[i]) {
                  max = "<none>";
                  min = "<none>";
               } else {
                  min = truncate(this.getMinValueAsString(arrayIndex));
                  max = truncate(this.getMaxValueAsString(arrayIndex++));
               }

               formatter.format(format, i, nullCount, min, max);
            }

            var20 = formatter.toString();
         } catch (Throwable var18) {
            var2 = var18;
            throw var18;
         } finally {
            if (formatter != null) {
               if (var2 != null) {
                  try {
                     formatter.close();
                  } catch (Throwable var17) {
                     var2.addSuppressed(var17);
                  }
               } else {
                  formatter.close();
               }
            }

         }

         return var20;
      }

      int getPageCount() {
         return this.nullPages.length;
      }

      boolean isNullPage(int pageIndex) {
         return this.nullPages[pageIndex];
      }

      abstract ByteBuffer getMinValueAsBytes(int var1);

      abstract ByteBuffer getMaxValueAsBytes(int var1);

      abstract String getMinValueAsString(int var1);

      abstract String getMaxValueAsString(int var1);

      abstract org.apache.parquet.filter2.predicate.Statistics createStats(int var1);

      abstract ValueComparator createValueComparator(Object var1);

      public PrimitiveIterator.OfInt visit(Operators.And and) {
         throw new UnsupportedOperationException("AND shall not be used on column index directly");
      }

      public PrimitiveIterator.OfInt visit(Operators.Not not) {
         throw new UnsupportedOperationException("NOT shall not be used on column index directly");
      }

      public PrimitiveIterator.OfInt visit(Operators.Or or) {
         throw new UnsupportedOperationException("OR shall not be used on column index directly");
      }

      public PrimitiveIterator.OfInt visit(Operators.Eq eq) {
         T value = (T)eq.getValue();
         if (value == null) {
            return this.nullCounts == null ? IndexIterator.all(this.getPageCount()) : IndexIterator.filter(this.getPageCount(), (pageIndex) -> this.nullCounts[pageIndex] > 0L);
         } else {
            return this.getBoundaryOrder().eq(this.createValueComparator(value));
         }
      }

      public PrimitiveIterator.OfInt visit(Operators.Gt gt) {
         return this.getBoundaryOrder().gt(this.createValueComparator(gt.getValue()));
      }

      public PrimitiveIterator.OfInt visit(Operators.GtEq gtEq) {
         return this.getBoundaryOrder().gtEq(this.createValueComparator(gtEq.getValue()));
      }

      public PrimitiveIterator.OfInt visit(Operators.Lt lt) {
         return this.getBoundaryOrder().lt(this.createValueComparator(lt.getValue()));
      }

      public PrimitiveIterator.OfInt visit(Operators.LtEq ltEq) {
         return this.getBoundaryOrder().ltEq(this.createValueComparator(ltEq.getValue()));
      }

      public PrimitiveIterator.OfInt visit(Operators.NotEq notEq) {
         T value = (T)notEq.getValue();
         if (value == null) {
            return IndexIterator.filter(this.getPageCount(), (pageIndex) -> !this.nullPages[pageIndex]);
         } else if (this.nullCounts == null) {
            return IndexIterator.all(this.getPageCount());
         } else {
            IntSet matchingIndexes = new IntOpenHashSet();
            this.getBoundaryOrder().notEq(this.createValueComparator(value)).forEachRemaining((index) -> matchingIndexes.add(index));
            return IndexIterator.filter(this.getPageCount(), (pageIndex) -> this.nullCounts[pageIndex] > 0L || matchingIndexes.contains(pageIndex));
         }
      }

      public PrimitiveIterator.OfInt visit(Operators.In in) {
         Set<T> values = in.getValues();
         IntSet matchingIndexesForNull = new IntOpenHashSet();

         for(Comparable value : values) {
            if (value == null) {
               if (this.nullCounts == null) {
                  return IndexIterator.all(this.getPageCount());
               }

               for(int i = 0; i < this.nullCounts.length; ++i) {
                  if (this.nullCounts[i] > 0L) {
                     matchingIndexesForNull.add(i);
                  }
               }

               if (values.size() == 1) {
                  int var10000 = this.getPageCount();
                  matchingIndexesForNull.getClass();
                  return IndexIterator.filter(var10000, matchingIndexesForNull::contains);
               }
            }
         }

         IntSet matchingIndexesLessThanMax = new IntOpenHashSet();
         IntSet matchingIndexesGreaterThanMin = new IntOpenHashSet();
         MinMax<T> minMax = new MinMax(this.comparator, values);
         T min = (T)((Comparable)minMax.getMin());
         T max = (T)((Comparable)minMax.getMax());
         this.getBoundaryOrder().ltEq(this.createValueComparator(max)).forEachRemaining((index) -> matchingIndexesLessThanMax.add(index));
         this.getBoundaryOrder().gtEq(this.createValueComparator(min)).forEachRemaining((index) -> matchingIndexesGreaterThanMin.add(index));
         matchingIndexesLessThanMax.retainAll(matchingIndexesGreaterThanMin);
         matchingIndexesLessThanMax.addAll(matchingIndexesForNull);
         int var13 = this.getPageCount();
         matchingIndexesLessThanMax.getClass();
         return IndexIterator.filter(var13, matchingIndexesLessThanMax::contains);
      }

      public PrimitiveIterator.OfInt visit(Operators.NotIn notIn) {
         return IndexIterator.all(this.getPageCount());
      }

      public PrimitiveIterator.OfInt visit(Operators.Contains contains) {
         return (PrimitiveIterator.OfInt)contains.filter(this, IndexIterator::intersection, IndexIterator::union, (indices) -> IndexIterator.all(this.getPageCount()));
      }

      public PrimitiveIterator.OfInt visit(Operators.UserDefined udp) {
         final UserDefinedPredicate<T> predicate = udp.getUserDefinedPredicate();
         final boolean acceptNulls = predicate.acceptsNullValue();
         return acceptNulls && this.nullCounts == null ? IndexIterator.all(this.getPageCount()) : IndexIterator.filter(this.getPageCount(), new IntPredicate() {
            private int arrayIndex = -1;

            public boolean test(int pageIndex) {
               if (ColumnIndexBase.this.isNullPage(pageIndex)) {
                  return acceptNulls;
               } else {
                  ++this.arrayIndex;
                  if (acceptNulls && ColumnIndexBase.this.nullCounts[pageIndex] > 0L) {
                     return true;
                  } else {
                     org.apache.parquet.filter2.predicate.Statistics<T> stats = ColumnIndexBase.this.createStats(this.arrayIndex);
                     return !predicate.canDrop(stats);
                  }
               }
            }
         });
      }

      public PrimitiveIterator.OfInt visit(Operators.LogicalNotUserDefined udp) {
         final UserDefinedPredicate<T> inversePredicate = udp.getUserDefined().getUserDefinedPredicate();
         final boolean acceptNulls = !inversePredicate.acceptsNullValue();
         return acceptNulls && this.nullCounts == null ? IndexIterator.all(this.getPageCount()) : IndexIterator.filter(this.getPageCount(), new IntPredicate() {
            private int arrayIndex = -1;

            public boolean test(int pageIndex) {
               if (ColumnIndexBase.this.isNullPage(pageIndex)) {
                  return acceptNulls;
               } else {
                  ++this.arrayIndex;
                  if (acceptNulls && ColumnIndexBase.this.nullCounts[pageIndex] > 0L) {
                     return true;
                  } else {
                     org.apache.parquet.filter2.predicate.Statistics<T> stats = ColumnIndexBase.this.createStats(this.arrayIndex);
                     return !inversePredicate.inverseCanDrop(stats);
                  }
               }
            }
         });
      }

      static {
         TOSTRING_TRUNCATION_END_POS = 40 - "(...)".length() - TOSTRING_TRUNCATION_START_POS;
      }

      abstract class ValueComparator {
         abstract int compareValueToMin(int var1);

         abstract int compareValueToMax(int var1);

         int arrayLength() {
            return ColumnIndexBase.this.pageIndexes.length;
         }

         int translate(int arrayIndex) {
            return ColumnIndexBase.this.pageIndexes[arrayIndex];
         }
      }
   }
}
