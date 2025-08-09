package org.glassfish.jersey.internal.guava;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

abstract class AbstractTable implements Table {
   private transient Set cellSet;

   public boolean containsRow(Object rowKey) {
      return Maps.safeContainsKey(this.rowMap(), rowKey);
   }

   public boolean containsColumn(Object columnKey) {
      return Maps.safeContainsKey(this.columnMap(), columnKey);
   }

   public Set rowKeySet() {
      return this.rowMap().keySet();
   }

   public Set columnKeySet() {
      return this.columnMap().keySet();
   }

   public boolean containsValue(Object value) {
      for(Map row : this.rowMap().values()) {
         if (row.containsValue(value)) {
            return true;
         }
      }

      return false;
   }

   public boolean contains(Object rowKey, Object columnKey) {
      Map<C, V> row = (Map)Maps.safeGet(this.rowMap(), rowKey);
      return row != null && Maps.safeContainsKey(row, columnKey);
   }

   public Object get(Object rowKey, Object columnKey) {
      Map<C, V> row = (Map)Maps.safeGet(this.rowMap(), rowKey);
      return row == null ? null : Maps.safeGet(row, columnKey);
   }

   public void clear() {
      Iterators.clear(this.cellSet().iterator());
   }

   public Object remove(Object rowKey, Object columnKey) {
      Map<C, V> row = (Map)Maps.safeGet(this.rowMap(), rowKey);
      return row == null ? null : Maps.safeRemove(row, columnKey);
   }

   public Object put(Object rowKey, Object columnKey, Object value) {
      return this.row(rowKey).put(columnKey, value);
   }

   public void putAll(Table table) {
      for(Table.Cell cell : table.cellSet()) {
         this.put(cell.getRowKey(), cell.getColumnKey(), cell.getValue());
      }

   }

   public Set cellSet() {
      Set<Table.Cell<R, C, V>> result = this.cellSet;
      return result == null ? (this.cellSet = this.createCellSet()) : result;
   }

   private Set createCellSet() {
      return new CellSet();
   }

   abstract Iterator cellIterator();

   public boolean equals(Object obj) {
      return Tables.equalsImpl(this, obj);
   }

   public int hashCode() {
      return this.cellSet().hashCode();
   }

   public String toString() {
      return this.rowMap().toString();
   }

   private class CellSet extends AbstractSet {
      private CellSet() {
      }

      public boolean contains(Object o) {
         if (!(o instanceof Table.Cell)) {
            return false;
         } else {
            Table.Cell<?, ?, ?> cell = (Table.Cell)o;
            Map<C, V> row = (Map)Maps.safeGet(AbstractTable.this.rowMap(), cell.getRowKey());
            return row != null && Collections2.safeContains(row.entrySet(), Maps.immutableEntry(cell.getColumnKey(), cell.getValue()));
         }
      }

      public boolean remove(Object o) {
         if (!(o instanceof Table.Cell)) {
            return false;
         } else {
            Table.Cell<?, ?, ?> cell = (Table.Cell)o;
            Map<C, V> row = (Map)Maps.safeGet(AbstractTable.this.rowMap(), cell.getRowKey());
            return row != null && Collections2.safeRemove(row.entrySet(), Maps.immutableEntry(cell.getColumnKey(), cell.getValue()));
         }
      }

      public void clear() {
         AbstractTable.this.clear();
      }

      public Iterator iterator() {
         return AbstractTable.this.cellIterator();
      }

      public int size() {
         return AbstractTable.this.size();
      }
   }
}
