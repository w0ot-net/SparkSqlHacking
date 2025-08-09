package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.errorprone.annotations.Immutable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Immutable(
   containerOf = {"R", "C", "V"}
)
@ElementTypesAreNonnullByDefault
@GwtCompatible
final class SparseImmutableTable extends RegularImmutableTable {
   static final ImmutableTable EMPTY = new SparseImmutableTable(ImmutableList.of(), ImmutableSet.of(), ImmutableSet.of());
   private final ImmutableMap rowMap;
   private final ImmutableMap columnMap;
   private final int[] cellRowIndices;
   private final int[] cellColumnInRowIndices;

   SparseImmutableTable(ImmutableList cellList, ImmutableSet rowSpace, ImmutableSet columnSpace) {
      Map<R, Integer> rowIndex = Maps.indexMap(rowSpace);
      Map<R, Map<C, V>> rows = Maps.newLinkedHashMap();

      for(Object row : rowSpace) {
         rows.put(row, new LinkedHashMap());
      }

      Map<C, Map<R, V>> columns = Maps.newLinkedHashMap();

      for(Object col : columnSpace) {
         columns.put(col, new LinkedHashMap());
      }

      int[] cellRowIndices = new int[cellList.size()];
      int[] cellColumnInRowIndices = new int[cellList.size()];

      for(int i = 0; i < cellList.size(); ++i) {
         Table.Cell<R, C, V> cell = (Table.Cell)cellList.get(i);
         R rowKey = (R)cell.getRowKey();
         C columnKey = (C)cell.getColumnKey();
         V value = (V)cell.getValue();
         cellRowIndices[i] = (Integer)Objects.requireNonNull((Integer)rowIndex.get(rowKey));
         Map<C, V> thisRow = (Map)Objects.requireNonNull((Map)rows.get(rowKey));
         cellColumnInRowIndices[i] = thisRow.size();
         V oldValue = (V)thisRow.put(columnKey, value);
         this.checkNoDuplicate(rowKey, columnKey, oldValue, value);
         ((Map)Objects.requireNonNull((Map)columns.get(columnKey))).put(rowKey, value);
      }

      this.cellRowIndices = cellRowIndices;
      this.cellColumnInRowIndices = cellColumnInRowIndices;
      ImmutableMap.Builder<R, ImmutableMap<C, V>> rowBuilder = new ImmutableMap.Builder(rows.size());

      for(Map.Entry row : rows.entrySet()) {
         rowBuilder.put(row.getKey(), ImmutableMap.copyOf((Map)row.getValue()));
      }

      this.rowMap = rowBuilder.buildOrThrow();
      ImmutableMap.Builder<C, ImmutableMap<R, V>> columnBuilder = new ImmutableMap.Builder(columns.size());

      for(Map.Entry col : columns.entrySet()) {
         columnBuilder.put(col.getKey(), ImmutableMap.copyOf((Map)col.getValue()));
      }

      this.columnMap = columnBuilder.buildOrThrow();
   }

   public ImmutableMap columnMap() {
      ImmutableMap<C, ImmutableMap<R, V>> columnMap = this.columnMap;
      return ImmutableMap.copyOf((Map)columnMap);
   }

   public ImmutableMap rowMap() {
      ImmutableMap<R, ImmutableMap<C, V>> rowMap = this.rowMap;
      return ImmutableMap.copyOf((Map)rowMap);
   }

   public int size() {
      return this.cellRowIndices.length;
   }

   Table.Cell getCell(int index) {
      int rowIndex = this.cellRowIndices[index];
      Map.Entry<R, ImmutableMap<C, V>> rowEntry = (Map.Entry)this.rowMap.entrySet().asList().get(rowIndex);
      ImmutableMap<C, V> row = (ImmutableMap)rowEntry.getValue();
      int columnIndex = this.cellColumnInRowIndices[index];
      Map.Entry<C, V> colEntry = (Map.Entry)row.entrySet().asList().get(columnIndex);
      return cellOf(rowEntry.getKey(), colEntry.getKey(), colEntry.getValue());
   }

   Object getValue(int index) {
      int rowIndex = this.cellRowIndices[index];
      ImmutableMap<C, V> row = (ImmutableMap)this.rowMap.values().asList().get(rowIndex);
      int columnIndex = this.cellColumnInRowIndices[index];
      return row.values().asList().get(columnIndex);
   }

   @J2ktIncompatible
   @GwtIncompatible
   Object writeReplace() {
      Map<C, Integer> columnKeyToIndex = Maps.indexMap(this.columnKeySet());
      int[] cellColumnIndices = new int[this.cellSet().size()];
      int i = 0;

      for(Table.Cell cell : this.cellSet()) {
         cellColumnIndices[i++] = (Integer)Objects.requireNonNull((Integer)columnKeyToIndex.get(cell.getColumnKey()));
      }

      return ImmutableTable.SerializedForm.create(this, this.cellRowIndices, cellColumnIndices);
   }
}
