package org.apache.curator.shaded.com.google.common.collect;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Objects;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class Tables {
   private static final org.apache.curator.shaded.com.google.common.base.Function UNMODIFIABLE_WRAPPER = new org.apache.curator.shaded.com.google.common.base.Function() {
      public Map apply(Map input) {
         return Collections.unmodifiableMap(input);
      }
   };

   private Tables() {
   }

   public static Collector toTable(Function rowFunction, Function columnFunction, Function valueFunction, Supplier tableSupplier) {
      return TableCollectors.toTable(rowFunction, columnFunction, valueFunction, tableSupplier);
   }

   public static Collector toTable(Function rowFunction, Function columnFunction, Function valueFunction, BinaryOperator mergeFunction, Supplier tableSupplier) {
      return TableCollectors.toTable(rowFunction, columnFunction, valueFunction, mergeFunction, tableSupplier);
   }

   public static Table.Cell immutableCell(@ParametricNullness Object rowKey, @ParametricNullness Object columnKey, @ParametricNullness Object value) {
      return new ImmutableCell(rowKey, columnKey, value);
   }

   public static Table transpose(Table table) {
      return (Table)(table instanceof TransposeTable ? ((TransposeTable)table).original : new TransposeTable(table));
   }

   public static Table newCustomTable(Map backingMap, org.apache.curator.shaded.com.google.common.base.Supplier factory) {
      Preconditions.checkArgument(backingMap.isEmpty());
      Preconditions.checkNotNull(factory);
      return new StandardTable(backingMap, factory);
   }

   public static Table transformValues(Table fromTable, org.apache.curator.shaded.com.google.common.base.Function function) {
      return new TransformedTable(fromTable, function);
   }

   public static Table unmodifiableTable(Table table) {
      return new UnmodifiableTable(table);
   }

   public static RowSortedTable unmodifiableRowSortedTable(RowSortedTable table) {
      return new UnmodifiableRowSortedMap(table);
   }

   private static org.apache.curator.shaded.com.google.common.base.Function unmodifiableWrapper() {
      return UNMODIFIABLE_WRAPPER;
   }

   public static Table synchronizedTable(Table table) {
      return Synchronized.table(table, (Object)null);
   }

   static boolean equalsImpl(Table table, @CheckForNull Object obj) {
      if (obj == table) {
         return true;
      } else if (obj instanceof Table) {
         Table<?, ?, ?> that = (Table)obj;
         return table.cellSet().equals(that.cellSet());
      } else {
         return false;
      }
   }

   static final class ImmutableCell extends AbstractCell implements Serializable {
      @ParametricNullness
      private final Object rowKey;
      @ParametricNullness
      private final Object columnKey;
      @ParametricNullness
      private final Object value;
      private static final long serialVersionUID = 0L;

      ImmutableCell(@ParametricNullness Object rowKey, @ParametricNullness Object columnKey, @ParametricNullness Object value) {
         this.rowKey = rowKey;
         this.columnKey = columnKey;
         this.value = value;
      }

      @ParametricNullness
      public Object getRowKey() {
         return this.rowKey;
      }

      @ParametricNullness
      public Object getColumnKey() {
         return this.columnKey;
      }

      @ParametricNullness
      public Object getValue() {
         return this.value;
      }
   }

   abstract static class AbstractCell implements Table.Cell {
      public boolean equals(@CheckForNull Object obj) {
         if (obj == this) {
            return true;
         } else if (!(obj instanceof Table.Cell)) {
            return false;
         } else {
            Table.Cell<?, ?, ?> other = (Table.Cell)obj;
            return Objects.equal(this.getRowKey(), other.getRowKey()) && Objects.equal(this.getColumnKey(), other.getColumnKey()) && Objects.equal(this.getValue(), other.getValue());
         }
      }

      public int hashCode() {
         return Objects.hashCode(this.getRowKey(), this.getColumnKey(), this.getValue());
      }

      public String toString() {
         return "(" + this.getRowKey() + "," + this.getColumnKey() + ")=" + this.getValue();
      }
   }

   private static class TransposeTable extends AbstractTable {
      final Table original;
      private static final org.apache.curator.shaded.com.google.common.base.Function TRANSPOSE_CELL = new org.apache.curator.shaded.com.google.common.base.Function() {
         public Table.Cell apply(Table.Cell cell) {
            return Tables.immutableCell(cell.getColumnKey(), cell.getRowKey(), cell.getValue());
         }
      };

      TransposeTable(Table original) {
         this.original = (Table)Preconditions.checkNotNull(original);
      }

      public void clear() {
         this.original.clear();
      }

      public Map column(@ParametricNullness Object columnKey) {
         return this.original.row(columnKey);
      }

      public Set columnKeySet() {
         return this.original.rowKeySet();
      }

      public Map columnMap() {
         return this.original.rowMap();
      }

      public boolean contains(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
         return this.original.contains(columnKey, rowKey);
      }

      public boolean containsColumn(@CheckForNull Object columnKey) {
         return this.original.containsRow(columnKey);
      }

      public boolean containsRow(@CheckForNull Object rowKey) {
         return this.original.containsColumn(rowKey);
      }

      public boolean containsValue(@CheckForNull Object value) {
         return this.original.containsValue(value);
      }

      @CheckForNull
      public Object get(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
         return this.original.get(columnKey, rowKey);
      }

      @CheckForNull
      public Object put(@ParametricNullness Object rowKey, @ParametricNullness Object columnKey, @ParametricNullness Object value) {
         return this.original.put(columnKey, rowKey, value);
      }

      public void putAll(Table table) {
         this.original.putAll(Tables.transpose(table));
      }

      @CheckForNull
      public Object remove(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
         return this.original.remove(columnKey, rowKey);
      }

      public Map row(@ParametricNullness Object rowKey) {
         return this.original.column(rowKey);
      }

      public Set rowKeySet() {
         return this.original.columnKeySet();
      }

      public Map rowMap() {
         return this.original.columnMap();
      }

      public int size() {
         return this.original.size();
      }

      public Collection values() {
         return this.original.values();
      }

      Iterator cellIterator() {
         return Iterators.transform(this.original.cellSet().iterator(), TRANSPOSE_CELL);
      }

      Spliterator cellSpliterator() {
         return CollectSpliterators.map(this.original.cellSet().spliterator(), TRANSPOSE_CELL);
      }
   }

   private static class TransformedTable extends AbstractTable {
      final Table fromTable;
      final org.apache.curator.shaded.com.google.common.base.Function function;

      TransformedTable(Table fromTable, org.apache.curator.shaded.com.google.common.base.Function function) {
         this.fromTable = (Table)Preconditions.checkNotNull(fromTable);
         this.function = (org.apache.curator.shaded.com.google.common.base.Function)Preconditions.checkNotNull(function);
      }

      public boolean contains(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
         return this.fromTable.contains(rowKey, columnKey);
      }

      @CheckForNull
      public Object get(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
         return this.contains(rowKey, columnKey) ? this.function.apply(NullnessCasts.uncheckedCastNullableTToT(this.fromTable.get(rowKey, columnKey))) : null;
      }

      public int size() {
         return this.fromTable.size();
      }

      public void clear() {
         this.fromTable.clear();
      }

      @CheckForNull
      public Object put(@ParametricNullness Object rowKey, @ParametricNullness Object columnKey, @ParametricNullness Object value) {
         throw new UnsupportedOperationException();
      }

      public void putAll(Table table) {
         throw new UnsupportedOperationException();
      }

      @CheckForNull
      public Object remove(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
         return this.contains(rowKey, columnKey) ? this.function.apply(NullnessCasts.uncheckedCastNullableTToT(this.fromTable.remove(rowKey, columnKey))) : null;
      }

      public Map row(@ParametricNullness Object rowKey) {
         return Maps.transformValues(this.fromTable.row(rowKey), this.function);
      }

      public Map column(@ParametricNullness Object columnKey) {
         return Maps.transformValues(this.fromTable.column(columnKey), this.function);
      }

      org.apache.curator.shaded.com.google.common.base.Function cellFunction() {
         return new org.apache.curator.shaded.com.google.common.base.Function() {
            public Table.Cell apply(Table.Cell cell) {
               return Tables.immutableCell(cell.getRowKey(), cell.getColumnKey(), TransformedTable.this.function.apply(cell.getValue()));
            }
         };
      }

      Iterator cellIterator() {
         return Iterators.transform(this.fromTable.cellSet().iterator(), this.cellFunction());
      }

      Spliterator cellSpliterator() {
         return CollectSpliterators.map(this.fromTable.cellSet().spliterator(), this.cellFunction());
      }

      public Set rowKeySet() {
         return this.fromTable.rowKeySet();
      }

      public Set columnKeySet() {
         return this.fromTable.columnKeySet();
      }

      Collection createValues() {
         return Collections2.transform(this.fromTable.values(), this.function);
      }

      public Map rowMap() {
         org.apache.curator.shaded.com.google.common.base.Function<Map<C, V1>, Map<C, V2>> rowFunction = new org.apache.curator.shaded.com.google.common.base.Function() {
            public Map apply(Map row) {
               return Maps.transformValues(row, TransformedTable.this.function);
            }
         };
         return Maps.transformValues(this.fromTable.rowMap(), rowFunction);
      }

      public Map columnMap() {
         org.apache.curator.shaded.com.google.common.base.Function<Map<R, V1>, Map<R, V2>> columnFunction = new org.apache.curator.shaded.com.google.common.base.Function() {
            public Map apply(Map column) {
               return Maps.transformValues(column, TransformedTable.this.function);
            }
         };
         return Maps.transformValues(this.fromTable.columnMap(), columnFunction);
      }
   }

   private static class UnmodifiableTable extends ForwardingTable implements Serializable {
      final Table delegate;
      private static final long serialVersionUID = 0L;

      UnmodifiableTable(Table delegate) {
         this.delegate = (Table)Preconditions.checkNotNull(delegate);
      }

      protected Table delegate() {
         return this.delegate;
      }

      public Set cellSet() {
         return Collections.unmodifiableSet(super.cellSet());
      }

      public void clear() {
         throw new UnsupportedOperationException();
      }

      public Map column(@ParametricNullness Object columnKey) {
         return Collections.unmodifiableMap(super.column(columnKey));
      }

      public Set columnKeySet() {
         return Collections.unmodifiableSet(super.columnKeySet());
      }

      public Map columnMap() {
         org.apache.curator.shaded.com.google.common.base.Function<Map<R, V>, Map<R, V>> wrapper = Tables.unmodifiableWrapper();
         return Collections.unmodifiableMap(Maps.transformValues(super.columnMap(), wrapper));
      }

      @CheckForNull
      public Object put(@ParametricNullness Object rowKey, @ParametricNullness Object columnKey, @ParametricNullness Object value) {
         throw new UnsupportedOperationException();
      }

      public void putAll(Table table) {
         throw new UnsupportedOperationException();
      }

      @CheckForNull
      public Object remove(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
         throw new UnsupportedOperationException();
      }

      public Map row(@ParametricNullness Object rowKey) {
         return Collections.unmodifiableMap(super.row(rowKey));
      }

      public Set rowKeySet() {
         return Collections.unmodifiableSet(super.rowKeySet());
      }

      public Map rowMap() {
         org.apache.curator.shaded.com.google.common.base.Function<Map<C, V>, Map<C, V>> wrapper = Tables.unmodifiableWrapper();
         return Collections.unmodifiableMap(Maps.transformValues(super.rowMap(), wrapper));
      }

      public Collection values() {
         return Collections.unmodifiableCollection(super.values());
      }
   }

   static final class UnmodifiableRowSortedMap extends UnmodifiableTable implements RowSortedTable {
      private static final long serialVersionUID = 0L;

      public UnmodifiableRowSortedMap(RowSortedTable delegate) {
         super(delegate);
      }

      protected RowSortedTable delegate() {
         return (RowSortedTable)super.delegate();
      }

      public SortedMap rowMap() {
         org.apache.curator.shaded.com.google.common.base.Function<Map<C, V>, Map<C, V>> wrapper = Tables.unmodifiableWrapper();
         return Collections.unmodifiableSortedMap(Maps.transformValues(this.delegate().rowMap(), wrapper));
      }

      public SortedSet rowKeySet() {
         return Collections.unmodifiableSortedSet(this.delegate().rowKeySet());
      }
   }
}
