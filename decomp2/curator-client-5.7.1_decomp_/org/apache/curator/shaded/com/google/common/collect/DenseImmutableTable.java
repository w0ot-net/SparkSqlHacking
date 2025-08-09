package org.apache.curator.shaded.com.google.common.collect;

import java.util.Map;
import java.util.Objects;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.Nullable;

@Immutable(
   containerOf = {"R", "C", "V"}
)
@ElementTypesAreNonnullByDefault
@GwtCompatible
final class DenseImmutableTable extends RegularImmutableTable {
   private final ImmutableMap rowKeyToIndex;
   private final ImmutableMap columnKeyToIndex;
   private final ImmutableMap rowMap;
   private final ImmutableMap columnMap;
   private final int[] rowCounts;
   private final int[] columnCounts;
   private final @Nullable Object[][] values;
   private final int[] cellRowIndices;
   private final int[] cellColumnIndices;

   DenseImmutableTable(ImmutableList cellList, ImmutableSet rowSpace, ImmutableSet columnSpace) {
      V[][] array = (V[][])(new Object[rowSpace.size()][columnSpace.size()]);
      this.values = array;
      this.rowKeyToIndex = Maps.indexMap(rowSpace);
      this.columnKeyToIndex = Maps.indexMap(columnSpace);
      this.rowCounts = new int[this.rowKeyToIndex.size()];
      this.columnCounts = new int[this.columnKeyToIndex.size()];
      int[] cellRowIndices = new int[cellList.size()];
      int[] cellColumnIndices = new int[cellList.size()];

      for(int i = 0; i < cellList.size(); ++i) {
         Table.Cell<R, C, V> cell = (Table.Cell)cellList.get(i);
         R rowKey = (R)cell.getRowKey();
         C columnKey = (C)cell.getColumnKey();
         int rowIndex = (Integer)Objects.requireNonNull((Integer)this.rowKeyToIndex.get(rowKey));
         int columnIndex = (Integer)Objects.requireNonNull((Integer)this.columnKeyToIndex.get(columnKey));
         V existingValue = (V)this.values[rowIndex][columnIndex];
         this.checkNoDuplicate(rowKey, columnKey, existingValue, cell.getValue());
         this.values[rowIndex][columnIndex] = cell.getValue();
         int var10002 = this.rowCounts[rowIndex]++;
         var10002 = this.columnCounts[columnIndex]++;
         cellRowIndices[i] = rowIndex;
         cellColumnIndices[i] = columnIndex;
      }

      this.cellRowIndices = cellRowIndices;
      this.cellColumnIndices = cellColumnIndices;
      this.rowMap = new RowMap();
      this.columnMap = new ColumnMap();
   }

   public ImmutableMap columnMap() {
      ImmutableMap<C, ImmutableMap<R, V>> columnMap = this.columnMap;
      return ImmutableMap.copyOf((Map)columnMap);
   }

   public ImmutableMap rowMap() {
      ImmutableMap<R, ImmutableMap<C, V>> rowMap = this.rowMap;
      return ImmutableMap.copyOf((Map)rowMap);
   }

   @CheckForNull
   public Object get(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
      Integer rowIndex = (Integer)this.rowKeyToIndex.get(rowKey);
      Integer columnIndex = (Integer)this.columnKeyToIndex.get(columnKey);
      return rowIndex != null && columnIndex != null ? this.values[rowIndex][columnIndex] : null;
   }

   public int size() {
      return this.cellRowIndices.length;
   }

   Table.Cell getCell(int index) {
      int rowIndex = this.cellRowIndices[index];
      int columnIndex = this.cellColumnIndices[index];
      R rowKey = (R)this.rowKeySet().asList().get(rowIndex);
      C columnKey = (C)this.columnKeySet().asList().get(columnIndex);
      V value = (V)Objects.requireNonNull(this.values[rowIndex][columnIndex]);
      return cellOf(rowKey, columnKey, value);
   }

   Object getValue(int index) {
      return Objects.requireNonNull(this.values[this.cellRowIndices[index]][this.cellColumnIndices[index]]);
   }

   ImmutableTable.SerializedForm createSerializedForm() {
      return ImmutableTable.SerializedForm.create(this, this.cellRowIndices, this.cellColumnIndices);
   }

   private abstract static class ImmutableArrayMap extends ImmutableMap.IteratorBasedImmutableMap {
      private final int size;

      ImmutableArrayMap(int size) {
         this.size = size;
      }

      abstract ImmutableMap keyToIndex();

      private boolean isFull() {
         return this.size == this.keyToIndex().size();
      }

      Object getKey(int index) {
         return this.keyToIndex().keySet().asList().get(index);
      }

      @CheckForNull
      abstract Object getValue(int keyIndex);

      ImmutableSet createKeySet() {
         return this.isFull() ? this.keyToIndex().keySet() : super.createKeySet();
      }

      public int size() {
         return this.size;
      }

      @CheckForNull
      public Object get(@CheckForNull Object key) {
         Integer keyIndex = (Integer)this.keyToIndex().get(key);
         return keyIndex == null ? null : this.getValue(keyIndex);
      }

      UnmodifiableIterator entryIterator() {
         return new AbstractIterator() {
            private int index = -1;
            private final int maxIndex = ImmutableArrayMap.this.keyToIndex().size();

            @CheckForNull
            protected Map.Entry computeNext() {
               ++this.index;

               while(this.index < this.maxIndex) {
                  V value = (V)ImmutableArrayMap.this.getValue(this.index);
                  if (value != null) {
                     return Maps.immutableEntry(ImmutableArrayMap.this.getKey(this.index), value);
                  }

                  ++this.index;
               }

               return (Map.Entry)this.endOfData();
            }
         };
      }
   }

   private final class Row extends ImmutableArrayMap {
      private final int rowIndex;

      Row(int rowIndex) {
         super(DenseImmutableTable.this.rowCounts[rowIndex]);
         this.rowIndex = rowIndex;
      }

      ImmutableMap keyToIndex() {
         return DenseImmutableTable.this.columnKeyToIndex;
      }

      @CheckForNull
      Object getValue(int keyIndex) {
         return DenseImmutableTable.this.values[this.rowIndex][keyIndex];
      }

      boolean isPartialView() {
         return true;
      }
   }

   private final class Column extends ImmutableArrayMap {
      private final int columnIndex;

      Column(int columnIndex) {
         super(DenseImmutableTable.this.columnCounts[columnIndex]);
         this.columnIndex = columnIndex;
      }

      ImmutableMap keyToIndex() {
         return DenseImmutableTable.this.rowKeyToIndex;
      }

      @CheckForNull
      Object getValue(int keyIndex) {
         return DenseImmutableTable.this.values[keyIndex][this.columnIndex];
      }

      boolean isPartialView() {
         return true;
      }
   }

   private final class RowMap extends ImmutableArrayMap {
      private RowMap() {
         super(DenseImmutableTable.this.rowCounts.length);
      }

      ImmutableMap keyToIndex() {
         return DenseImmutableTable.this.rowKeyToIndex;
      }

      ImmutableMap getValue(int keyIndex) {
         return DenseImmutableTable.this.new Row(keyIndex);
      }

      boolean isPartialView() {
         return false;
      }
   }

   private final class ColumnMap extends ImmutableArrayMap {
      private ColumnMap() {
         super(DenseImmutableTable.this.columnCounts.length);
      }

      ImmutableMap keyToIndex() {
         return DenseImmutableTable.this.columnKeyToIndex;
      }

      ImmutableMap getValue(int keyIndex) {
         return DenseImmutableTable.this.new Column(keyIndex);
      }

      boolean isPartialView() {
         return false;
      }
   }
}
