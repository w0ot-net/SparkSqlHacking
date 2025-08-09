package org.glassfish.jersey.internal.guava;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

public final class Tables {
   private Tables() {
   }

   public static Table.Cell immutableCell(Object rowKey, Object columnKey, Object value) {
      return new ImmutableCell(rowKey, columnKey, value);
   }

   private static Table transpose(Table table) {
      return (Table)(table instanceof TransposeTable ? ((TransposeTable)table).original : new TransposeTable(table));
   }

   static boolean equalsImpl(Table table, Object obj) {
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
      private static final long serialVersionUID = 0L;
      private final Object rowKey;
      private final Object columnKey;
      private final Object value;

      ImmutableCell(Object rowKey, Object columnKey, Object value) {
         this.rowKey = rowKey;
         this.columnKey = columnKey;
         this.value = value;
      }

      public Object getRowKey() {
         return this.rowKey;
      }

      public Object getColumnKey() {
         return this.columnKey;
      }

      public Object getValue() {
         return this.value;
      }
   }

   abstract static class AbstractCell implements Table.Cell {
      public boolean equals(Object obj) {
         if (obj == this) {
            return true;
         } else if (!(obj instanceof Table.Cell)) {
            return false;
         } else {
            Table.Cell<?, ?, ?> other = (Table.Cell)obj;
            return Objects.equals(this.getRowKey(), other.getRowKey()) && Objects.equals(this.getColumnKey(), other.getColumnKey()) && Objects.equals(this.getValue(), other.getValue());
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.getRowKey(), this.getColumnKey(), this.getValue()});
      }

      public String toString() {
         return "(" + this.getRowKey() + "," + this.getColumnKey() + ")=" + this.getValue();
      }
   }

   private static class TransposeTable extends AbstractTable {
      private static final Function TRANSPOSE_CELL = new Function() {
         public Table.Cell apply(Table.Cell cell) {
            return Tables.immutableCell(cell.getColumnKey(), cell.getRowKey(), cell.getValue());
         }
      };
      final Table original;

      TransposeTable(Table original) {
         this.original = (Table)Preconditions.checkNotNull(original);
      }

      public void clear() {
         this.original.clear();
      }

      public Map column(Object columnKey) {
         return this.original.row(columnKey);
      }

      public Set columnKeySet() {
         return this.original.rowKeySet();
      }

      public Map columnMap() {
         return this.original.rowMap();
      }

      public boolean contains(Object rowKey, Object columnKey) {
         return this.original.contains(columnKey, rowKey);
      }

      public boolean containsColumn(Object columnKey) {
         return this.original.containsRow(columnKey);
      }

      public boolean containsRow(Object rowKey) {
         return this.original.containsColumn(rowKey);
      }

      public boolean containsValue(Object value) {
         return this.original.containsValue(value);
      }

      public Object get(Object rowKey, Object columnKey) {
         return this.original.get(columnKey, rowKey);
      }

      public Object put(Object rowKey, Object columnKey, Object value) {
         return this.original.put(columnKey, rowKey, value);
      }

      public void putAll(Table table) {
         this.original.putAll(Tables.transpose(table));
      }

      public Object remove(Object rowKey, Object columnKey) {
         return this.original.remove(columnKey, rowKey);
      }

      public Map row(Object rowKey) {
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

      Iterator cellIterator() {
         return Iterators.transform(this.original.cellSet().iterator(), TRANSPOSE_CELL);
      }
   }
}
