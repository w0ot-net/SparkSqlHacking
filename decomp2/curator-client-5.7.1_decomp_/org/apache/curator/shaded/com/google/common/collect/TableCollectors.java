package org.apache.curator.shaded.com.google.common.collect;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class TableCollectors {
   static Collector toImmutableTable(Function rowFunction, Function columnFunction, Function valueFunction) {
      Preconditions.checkNotNull(rowFunction, "rowFunction");
      Preconditions.checkNotNull(columnFunction, "columnFunction");
      Preconditions.checkNotNull(valueFunction, "valueFunction");
      return Collector.of(ImmutableTable.Builder::new, (builder, t) -> builder.put(rowFunction.apply(t), columnFunction.apply(t), valueFunction.apply(t)), ImmutableTable.Builder::combine, ImmutableTable.Builder::build);
   }

   static Collector toImmutableTable(Function rowFunction, Function columnFunction, Function valueFunction, BinaryOperator mergeFunction) {
      Preconditions.checkNotNull(rowFunction, "rowFunction");
      Preconditions.checkNotNull(columnFunction, "columnFunction");
      Preconditions.checkNotNull(valueFunction, "valueFunction");
      Preconditions.checkNotNull(mergeFunction, "mergeFunction");
      return Collector.of(() -> new ImmutableTableCollectorState(), (state, input) -> state.put(rowFunction.apply(input), columnFunction.apply(input), valueFunction.apply(input), mergeFunction), (s1, s2) -> s1.combine(s2, mergeFunction), (state) -> state.toTable());
   }

   static Collector toTable(Function rowFunction, Function columnFunction, Function valueFunction, Supplier tableSupplier) {
      return toTable(rowFunction, columnFunction, valueFunction, (v1, v2) -> {
         throw new IllegalStateException("Conflicting values " + v1 + " and " + v2);
      }, tableSupplier);
   }

   static Collector toTable(Function rowFunction, Function columnFunction, Function valueFunction, BinaryOperator mergeFunction, Supplier tableSupplier) {
      Preconditions.checkNotNull(rowFunction);
      Preconditions.checkNotNull(columnFunction);
      Preconditions.checkNotNull(valueFunction);
      Preconditions.checkNotNull(mergeFunction);
      Preconditions.checkNotNull(tableSupplier);
      return Collector.of(tableSupplier, (table, input) -> mergeTables(table, rowFunction.apply(input), columnFunction.apply(input), valueFunction.apply(input), mergeFunction), (table1, table2) -> {
         for(Table.Cell cell2 : table2.cellSet()) {
            mergeTables(table1, cell2.getRowKey(), cell2.getColumnKey(), cell2.getValue(), mergeFunction);
         }

         return table1;
      });
   }

   private static void mergeTables(Table table, @ParametricNullness Object row, @ParametricNullness Object column, @ParametricNullness Object value, BinaryOperator mergeFunction) {
      Preconditions.checkNotNull(value);
      V oldValue = (V)table.get(row, column);
      if (oldValue == null) {
         table.put(row, column, value);
      } else {
         V newValue = (V)mergeFunction.apply(oldValue, value);
         if (newValue == null) {
            table.remove(row, column);
         } else {
            table.put(row, column, newValue);
         }
      }

   }

   private TableCollectors() {
   }

   private static final class ImmutableTableCollectorState {
      final List insertionOrder;
      final Table table;

      private ImmutableTableCollectorState() {
         this.insertionOrder = new ArrayList();
         this.table = HashBasedTable.create();
      }

      void put(Object row, Object column, Object value, BinaryOperator merger) {
         MutableCell<R, C, V> oldCell = (MutableCell)this.table.get(row, column);
         if (oldCell == null) {
            MutableCell<R, C, V> cell = new MutableCell(row, column, value);
            this.insertionOrder.add(cell);
            this.table.put(row, column, cell);
         } else {
            oldCell.merge(value, merger);
         }

      }

      ImmutableTableCollectorState combine(ImmutableTableCollectorState other, BinaryOperator merger) {
         for(MutableCell cell : other.insertionOrder) {
            this.put(cell.getRowKey(), cell.getColumnKey(), cell.getValue(), merger);
         }

         return this;
      }

      ImmutableTable toTable() {
         return ImmutableTable.copyOf((Iterable)this.insertionOrder);
      }
   }

   private static final class MutableCell extends Tables.AbstractCell {
      private final Object row;
      private final Object column;
      private Object value;

      MutableCell(Object row, Object column, Object value) {
         this.row = Preconditions.checkNotNull(row, "row");
         this.column = Preconditions.checkNotNull(column, "column");
         this.value = Preconditions.checkNotNull(value, "value");
      }

      public Object getRowKey() {
         return this.row;
      }

      public Object getColumnKey() {
         return this.column;
      }

      public Object getValue() {
         return this.value;
      }

      void merge(Object value, BinaryOperator mergeFunction) {
         Preconditions.checkNotNull(value, "value");
         this.value = Preconditions.checkNotNull(mergeFunction.apply(this.value, value), "mergeFunction.apply");
      }
   }
}
