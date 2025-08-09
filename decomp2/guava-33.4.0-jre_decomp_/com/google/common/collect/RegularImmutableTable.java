package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class RegularImmutableTable extends ImmutableTable {
   abstract Table.Cell getCell(int iterationIndex);

   final ImmutableSet createCellSet() {
      return (ImmutableSet)(this.isEmpty() ? ImmutableSet.of() : new CellSet());
   }

   abstract Object getValue(int iterationIndex);

   final ImmutableCollection createValues() {
      return (ImmutableCollection)(this.isEmpty() ? ImmutableList.of() : new Values());
   }

   static RegularImmutableTable forCells(List cells, @CheckForNull Comparator rowComparator, @CheckForNull Comparator columnComparator) {
      Preconditions.checkNotNull(cells);
      if (rowComparator != null || columnComparator != null) {
         Comparator<Table.Cell<R, C, V>> comparator = (cell1, cell2) -> {
            int rowCompare = rowComparator == null ? 0 : rowComparator.compare(cell1.getRowKey(), cell2.getRowKey());
            if (rowCompare != 0) {
               return rowCompare;
            } else {
               return columnComparator == null ? 0 : columnComparator.compare(cell1.getColumnKey(), cell2.getColumnKey());
            }
         };
         Collections.sort(cells, comparator);
      }

      return forCellsInternal(cells, rowComparator, columnComparator);
   }

   static RegularImmutableTable forCells(Iterable cells) {
      return forCellsInternal(cells, (Comparator)null, (Comparator)null);
   }

   private static RegularImmutableTable forCellsInternal(Iterable cells, @CheckForNull Comparator rowComparator, @CheckForNull Comparator columnComparator) {
      Set<R> rowSpaceBuilder = new LinkedHashSet();
      Set<C> columnSpaceBuilder = new LinkedHashSet();
      ImmutableList<Table.Cell<R, C, V>> cellList = ImmutableList.copyOf(cells);

      for(Table.Cell cell : cells) {
         rowSpaceBuilder.add(cell.getRowKey());
         columnSpaceBuilder.add(cell.getColumnKey());
      }

      ImmutableSet<R> rowSpace = rowComparator == null ? ImmutableSet.copyOf((Collection)rowSpaceBuilder) : ImmutableSet.copyOf((Collection)ImmutableList.sortedCopyOf(rowComparator, rowSpaceBuilder));
      ImmutableSet<C> columnSpace = columnComparator == null ? ImmutableSet.copyOf((Collection)columnSpaceBuilder) : ImmutableSet.copyOf((Collection)ImmutableList.sortedCopyOf(columnComparator, columnSpaceBuilder));
      return forOrderedComponents(cellList, rowSpace, columnSpace);
   }

   static RegularImmutableTable forOrderedComponents(ImmutableList cellList, ImmutableSet rowSpace, ImmutableSet columnSpace) {
      return (RegularImmutableTable)((long)cellList.size() > (long)rowSpace.size() * (long)columnSpace.size() / 2L ? new DenseImmutableTable(cellList, rowSpace, columnSpace) : new SparseImmutableTable(cellList, rowSpace, columnSpace));
   }

   final void checkNoDuplicate(Object rowKey, Object columnKey, @CheckForNull Object existingValue, Object newValue) {
      Preconditions.checkArgument(existingValue == null, "Duplicate key: (row=%s, column=%s), values: [%s, %s].", rowKey, columnKey, newValue, existingValue);
   }

   @J2ktIncompatible
   @GwtIncompatible
   abstract Object writeReplace();

   private final class CellSet extends IndexedImmutableSet {
      private CellSet() {
      }

      public int size() {
         return RegularImmutableTable.this.size();
      }

      Table.Cell get(int index) {
         return RegularImmutableTable.this.getCell(index);
      }

      public boolean contains(@CheckForNull Object object) {
         if (!(object instanceof Table.Cell)) {
            return false;
         } else {
            Table.Cell<?, ?, ?> cell = (Table.Cell)object;
            Object value = RegularImmutableTable.this.get(cell.getRowKey(), cell.getColumnKey());
            return value != null && value.equals(cell.getValue());
         }
      }

      boolean isPartialView() {
         return false;
      }

      @J2ktIncompatible
      @GwtIncompatible
      Object writeReplace() {
         return super.writeReplace();
      }
   }

   private final class Values extends ImmutableList {
      private Values() {
      }

      public int size() {
         return RegularImmutableTable.this.size();
      }

      public Object get(int index) {
         return RegularImmutableTable.this.getValue(index);
      }

      boolean isPartialView() {
         return true;
      }

      @J2ktIncompatible
      @GwtIncompatible
      Object writeReplace() {
         return super.writeReplace();
      }
   }
}
