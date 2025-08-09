package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AbstractTable implements Table {
   @LazyInit
   @CheckForNull
   private transient Set cellSet;
   @LazyInit
   @CheckForNull
   private transient Collection values;

   public boolean containsRow(@CheckForNull Object rowKey) {
      return Maps.safeContainsKey(this.rowMap(), rowKey);
   }

   public boolean containsColumn(@CheckForNull Object columnKey) {
      return Maps.safeContainsKey(this.columnMap(), columnKey);
   }

   public Set rowKeySet() {
      return this.rowMap().keySet();
   }

   public Set columnKeySet() {
      return this.columnMap().keySet();
   }

   public boolean containsValue(@CheckForNull Object value) {
      for(Map row : this.rowMap().values()) {
         if (row.containsValue(value)) {
            return true;
         }
      }

      return false;
   }

   public boolean contains(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
      Map<C, V> row = (Map)Maps.safeGet(this.rowMap(), rowKey);
      return row != null && Maps.safeContainsKey(row, columnKey);
   }

   @CheckForNull
   public Object get(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
      Map<C, V> row = (Map)Maps.safeGet(this.rowMap(), rowKey);
      return row == null ? null : Maps.safeGet(row, columnKey);
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public void clear() {
      Iterators.clear(this.cellSet().iterator());
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object remove(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
      Map<C, V> row = (Map)Maps.safeGet(this.rowMap(), rowKey);
      return row == null ? null : Maps.safeRemove(row, columnKey);
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object put(@ParametricNullness Object rowKey, @ParametricNullness Object columnKey, @ParametricNullness Object value) {
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

   Set createCellSet() {
      return new CellSet();
   }

   abstract Iterator cellIterator();

   abstract Spliterator cellSpliterator();

   public Collection values() {
      Collection<V> result = this.values;
      return result == null ? (this.values = this.createValues()) : result;
   }

   Collection createValues() {
      return new Values();
   }

   Iterator valuesIterator() {
      return new TransformedIterator(this.cellSet().iterator()) {
         @ParametricNullness
         Object transform(Table.Cell cell) {
            return cell.getValue();
         }
      };
   }

   Spliterator valuesSpliterator() {
      return CollectSpliterators.map(this.cellSpliterator(), Table.Cell::getValue);
   }

   public boolean equals(@CheckForNull Object obj) {
      return Tables.equalsImpl(this, obj);
   }

   public int hashCode() {
      return this.cellSet().hashCode();
   }

   public String toString() {
      return this.rowMap().toString();
   }

   class CellSet extends AbstractSet {
      public boolean contains(@CheckForNull Object o) {
         if (!(o instanceof Table.Cell)) {
            return false;
         } else {
            Table.Cell<?, ?, ?> cell = (Table.Cell)o;
            Map<C, V> row = (Map)Maps.safeGet(AbstractTable.this.rowMap(), cell.getRowKey());
            return row != null && Collections2.safeContains(row.entrySet(), Maps.immutableEntry(cell.getColumnKey(), cell.getValue()));
         }
      }

      public boolean remove(@CheckForNull Object o) {
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

      public Spliterator spliterator() {
         return AbstractTable.this.cellSpliterator();
      }

      public int size() {
         return AbstractTable.this.size();
      }
   }

   class Values extends AbstractCollection {
      public Iterator iterator() {
         return AbstractTable.this.valuesIterator();
      }

      public Spliterator spliterator() {
         return AbstractTable.this.valuesSpliterator();
      }

      public boolean contains(@CheckForNull Object o) {
         return AbstractTable.this.containsValue(o);
      }

      public void clear() {
         AbstractTable.this.clear();
      }

      public int size() {
         return AbstractTable.this.size();
      }
   }
}
