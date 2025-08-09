package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.DoNotMock;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.MoreObjects;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ImmutableTable extends AbstractTable implements Serializable {
   private static final long serialVersionUID = -889275714L;

   public static Collector toImmutableTable(Function rowFunction, Function columnFunction, Function valueFunction) {
      return TableCollectors.toImmutableTable(rowFunction, columnFunction, valueFunction);
   }

   public static Collector toImmutableTable(Function rowFunction, Function columnFunction, Function valueFunction, BinaryOperator mergeFunction) {
      return TableCollectors.toImmutableTable(rowFunction, columnFunction, valueFunction, mergeFunction);
   }

   public static ImmutableTable of() {
      return SparseImmutableTable.EMPTY;
   }

   public static ImmutableTable of(Object rowKey, Object columnKey, Object value) {
      return new SingletonImmutableTable(rowKey, columnKey, value);
   }

   public static ImmutableTable copyOf(Table table) {
      if (table instanceof ImmutableTable) {
         ImmutableTable<R, C, V> parameterizedTable = (ImmutableTable)table;
         return parameterizedTable;
      } else {
         return copyOf((Iterable)table.cellSet());
      }
   }

   static ImmutableTable copyOf(Iterable cells) {
      Builder<R, C, V> builder = builder();

      for(Table.Cell cell : cells) {
         builder.put(cell);
      }

      return builder.build();
   }

   public static Builder builder() {
      return new Builder();
   }

   static Table.Cell cellOf(Object rowKey, Object columnKey, Object value) {
      return Tables.immutableCell(Preconditions.checkNotNull(rowKey, "rowKey"), Preconditions.checkNotNull(columnKey, "columnKey"), Preconditions.checkNotNull(value, "value"));
   }

   ImmutableTable() {
   }

   public ImmutableSet cellSet() {
      return (ImmutableSet)super.cellSet();
   }

   abstract ImmutableSet createCellSet();

   final UnmodifiableIterator cellIterator() {
      throw new AssertionError("should never be called");
   }

   final Spliterator cellSpliterator() {
      throw new AssertionError("should never be called");
   }

   public ImmutableCollection values() {
      return (ImmutableCollection)super.values();
   }

   abstract ImmutableCollection createValues();

   final Iterator valuesIterator() {
      throw new AssertionError("should never be called");
   }

   public ImmutableMap column(Object columnKey) {
      Preconditions.checkNotNull(columnKey, "columnKey");
      return (ImmutableMap)MoreObjects.firstNonNull((ImmutableMap)this.columnMap().get(columnKey), ImmutableMap.of());
   }

   public ImmutableSet columnKeySet() {
      return this.columnMap().keySet();
   }

   public abstract ImmutableMap columnMap();

   public ImmutableMap row(Object rowKey) {
      Preconditions.checkNotNull(rowKey, "rowKey");
      return (ImmutableMap)MoreObjects.firstNonNull((ImmutableMap)this.rowMap().get(rowKey), ImmutableMap.of());
   }

   public ImmutableSet rowKeySet() {
      return this.rowMap().keySet();
   }

   public abstract ImmutableMap rowMap();

   public boolean contains(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
      return this.get(rowKey, columnKey) != null;
   }

   public boolean containsValue(@CheckForNull Object value) {
      return this.values().contains(value);
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final void clear() {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final Object put(Object rowKey, Object columnKey, Object value) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final void putAll(Table table) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final Object remove(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
      throw new UnsupportedOperationException();
   }

   @J2ktIncompatible
   @GwtIncompatible
   abstract Object writeReplace();

   @GwtIncompatible
   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Use SerializedForm");
   }

   @DoNotMock
   public static final class Builder {
      private final List cells = Lists.newArrayList();
      @CheckForNull
      private Comparator rowComparator;
      @CheckForNull
      private Comparator columnComparator;

      @CanIgnoreReturnValue
      public Builder orderRowsBy(Comparator rowComparator) {
         this.rowComparator = (Comparator)Preconditions.checkNotNull(rowComparator, "rowComparator");
         return this;
      }

      @CanIgnoreReturnValue
      public Builder orderColumnsBy(Comparator columnComparator) {
         this.columnComparator = (Comparator)Preconditions.checkNotNull(columnComparator, "columnComparator");
         return this;
      }

      @CanIgnoreReturnValue
      public Builder put(Object rowKey, Object columnKey, Object value) {
         this.cells.add(ImmutableTable.cellOf(rowKey, columnKey, value));
         return this;
      }

      @CanIgnoreReturnValue
      public Builder put(Table.Cell cell) {
         if (cell instanceof Tables.ImmutableCell) {
            Preconditions.checkNotNull(cell.getRowKey(), "row");
            Preconditions.checkNotNull(cell.getColumnKey(), "column");
            Preconditions.checkNotNull(cell.getValue(), "value");
            this.cells.add(cell);
         } else {
            this.put(cell.getRowKey(), cell.getColumnKey(), cell.getValue());
         }

         return this;
      }

      @CanIgnoreReturnValue
      public Builder putAll(Table table) {
         for(Table.Cell cell : table.cellSet()) {
            this.put(cell);
         }

         return this;
      }

      @CanIgnoreReturnValue
      Builder combine(Builder other) {
         this.cells.addAll(other.cells);
         return this;
      }

      public ImmutableTable build() {
         return this.buildOrThrow();
      }

      public ImmutableTable buildOrThrow() {
         int size = this.cells.size();
         switch (size) {
            case 0:
               return ImmutableTable.of();
            case 1:
               return new SingletonImmutableTable((Table.Cell)Iterables.getOnlyElement(this.cells));
            default:
               return RegularImmutableTable.forCells(this.cells, this.rowComparator, this.columnComparator);
         }
      }
   }

   static final class SerializedForm implements Serializable {
      private final Object[] rowKeys;
      private final Object[] columnKeys;
      private final Object[] cellValues;
      private final int[] cellRowIndices;
      private final int[] cellColumnIndices;
      private static final long serialVersionUID = 0L;

      private SerializedForm(Object[] rowKeys, Object[] columnKeys, Object[] cellValues, int[] cellRowIndices, int[] cellColumnIndices) {
         this.rowKeys = rowKeys;
         this.columnKeys = columnKeys;
         this.cellValues = cellValues;
         this.cellRowIndices = cellRowIndices;
         this.cellColumnIndices = cellColumnIndices;
      }

      static SerializedForm create(ImmutableTable table, int[] cellRowIndices, int[] cellColumnIndices) {
         return new SerializedForm(table.rowKeySet().toArray(), table.columnKeySet().toArray(), table.values().toArray(), cellRowIndices, cellColumnIndices);
      }

      Object readResolve() {
         if (this.cellValues.length == 0) {
            return ImmutableTable.of();
         } else if (this.cellValues.length == 1) {
            return ImmutableTable.of(this.rowKeys[0], this.columnKeys[0], this.cellValues[0]);
         } else {
            ImmutableList.Builder<Table.Cell<Object, Object, Object>> cellListBuilder = new ImmutableList.Builder(this.cellValues.length);

            for(int i = 0; i < this.cellValues.length; ++i) {
               cellListBuilder.add((Object)ImmutableTable.cellOf(this.rowKeys[this.cellRowIndices[i]], this.columnKeys[this.cellColumnIndices[i]], this.cellValues[i]));
            }

            return RegularImmutableTable.forOrderedComponents(cellListBuilder.build(), ImmutableSet.copyOf(this.rowKeys), ImmutableSet.copyOf(this.columnKeys));
         }
      }
   }
}
