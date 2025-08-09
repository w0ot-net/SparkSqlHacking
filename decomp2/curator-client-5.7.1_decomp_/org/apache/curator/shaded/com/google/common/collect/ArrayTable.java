package org.apache.curator.shaded.com.google.common.collect;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.base.Objects;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotCall;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public final class ArrayTable extends AbstractTable implements Serializable {
   private final ImmutableList rowList;
   private final ImmutableList columnList;
   private final ImmutableMap rowKeyToIndex;
   private final ImmutableMap columnKeyToIndex;
   private final @Nullable Object[][] array;
   @CheckForNull
   private transient ColumnMap columnMap;
   @CheckForNull
   private transient RowMap rowMap;
   private static final long serialVersionUID = 0L;

   public static ArrayTable create(Iterable rowKeys, Iterable columnKeys) {
      return new ArrayTable(rowKeys, columnKeys);
   }

   public static ArrayTable create(Table table) {
      return table instanceof ArrayTable ? new ArrayTable((ArrayTable)table) : new ArrayTable(table);
   }

   private ArrayTable(Iterable rowKeys, Iterable columnKeys) {
      this.rowList = ImmutableList.copyOf(rowKeys);
      this.columnList = ImmutableList.copyOf(columnKeys);
      Preconditions.checkArgument(this.rowList.isEmpty() == this.columnList.isEmpty());
      this.rowKeyToIndex = Maps.indexMap(this.rowList);
      this.columnKeyToIndex = Maps.indexMap(this.columnList);
      V[][] tmpArray = (V[][])(new Object[this.rowList.size()][this.columnList.size()]);
      this.array = tmpArray;
      this.eraseAll();
   }

   private ArrayTable(Table table) {
      this(table.rowKeySet(), table.columnKeySet());
      this.putAll(table);
   }

   private ArrayTable(ArrayTable table) {
      this.rowList = table.rowList;
      this.columnList = table.columnList;
      this.rowKeyToIndex = table.rowKeyToIndex;
      this.columnKeyToIndex = table.columnKeyToIndex;
      V[][] copy = (V[][])(new Object[this.rowList.size()][this.columnList.size()]);
      this.array = copy;

      for(int i = 0; i < this.rowList.size(); ++i) {
         System.arraycopy(table.array[i], 0, copy[i], 0, table.array[i].length);
      }

   }

   public ImmutableList rowKeyList() {
      return this.rowList;
   }

   public ImmutableList columnKeyList() {
      return this.columnList;
   }

   @CheckForNull
   public Object at(int rowIndex, int columnIndex) {
      Preconditions.checkElementIndex(rowIndex, this.rowList.size());
      Preconditions.checkElementIndex(columnIndex, this.columnList.size());
      return this.array[rowIndex][columnIndex];
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object set(int rowIndex, int columnIndex, @CheckForNull Object value) {
      Preconditions.checkElementIndex(rowIndex, this.rowList.size());
      Preconditions.checkElementIndex(columnIndex, this.columnList.size());
      V oldValue = (V)this.array[rowIndex][columnIndex];
      this.array[rowIndex][columnIndex] = value;
      return oldValue;
   }

   @GwtIncompatible
   public Object[][] toArray(Class valueClass) {
      V[][] copy = (V[][])((Object[][])Array.newInstance(valueClass, new int[]{this.rowList.size(), this.columnList.size()}));

      for(int i = 0; i < this.rowList.size(); ++i) {
         System.arraycopy(this.array[i], 0, copy[i], 0, this.array[i].length);
      }

      return copy;
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public void clear() {
      throw new UnsupportedOperationException();
   }

   public void eraseAll() {
      for(Object[] row : this.array) {
         Arrays.fill(row, (Object)null);
      }

   }

   public boolean contains(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
      return this.containsRow(rowKey) && this.containsColumn(columnKey);
   }

   public boolean containsColumn(@CheckForNull Object columnKey) {
      return this.columnKeyToIndex.containsKey(columnKey);
   }

   public boolean containsRow(@CheckForNull Object rowKey) {
      return this.rowKeyToIndex.containsKey(rowKey);
   }

   public boolean containsValue(@CheckForNull Object value) {
      for(Object[] row : this.array) {
         for(Object element : row) {
            if (Objects.equal(value, element)) {
               return true;
            }
         }
      }

      return false;
   }

   @CheckForNull
   public Object get(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
      Integer rowIndex = (Integer)this.rowKeyToIndex.get(rowKey);
      Integer columnIndex = (Integer)this.columnKeyToIndex.get(columnKey);
      return rowIndex != null && columnIndex != null ? this.at(rowIndex, columnIndex) : null;
   }

   public boolean isEmpty() {
      return this.rowList.isEmpty() || this.columnList.isEmpty();
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object put(Object rowKey, Object columnKey, @CheckForNull Object value) {
      Preconditions.checkNotNull(rowKey);
      Preconditions.checkNotNull(columnKey);
      Integer rowIndex = (Integer)this.rowKeyToIndex.get(rowKey);
      Preconditions.checkArgument(rowIndex != null, "Row %s not in %s", rowKey, this.rowList);
      Integer columnIndex = (Integer)this.columnKeyToIndex.get(columnKey);
      Preconditions.checkArgument(columnIndex != null, "Column %s not in %s", columnKey, this.columnList);
      return this.set(rowIndex, columnIndex, value);
   }

   public void putAll(Table table) {
      super.putAll(table);
   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @DoNotCall("Always throws UnsupportedOperationException")
   @CanIgnoreReturnValue
   public Object remove(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
      throw new UnsupportedOperationException();
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object erase(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
      Integer rowIndex = (Integer)this.rowKeyToIndex.get(rowKey);
      Integer columnIndex = (Integer)this.columnKeyToIndex.get(columnKey);
      return rowIndex != null && columnIndex != null ? this.set(rowIndex, columnIndex, (Object)null) : null;
   }

   public int size() {
      return this.rowList.size() * this.columnList.size();
   }

   public Set cellSet() {
      return super.cellSet();
   }

   Iterator cellIterator() {
      return new AbstractIndexedListIterator(this.size()) {
         protected Table.Cell get(final int index) {
            return ArrayTable.this.getCell(index);
         }
      };
   }

   Spliterator cellSpliterator() {
      return CollectSpliterators.indexed(this.size(), 273, this::getCell);
   }

   private Table.Cell getCell(final int index) {
      return new Tables.AbstractCell() {
         final int rowIndex;
         final int columnIndex;

         {
            this.rowIndex = index / ArrayTable.this.columnList.size();
            this.columnIndex = index % ArrayTable.this.columnList.size();
         }

         public Object getRowKey() {
            return ArrayTable.this.rowList.get(this.rowIndex);
         }

         public Object getColumnKey() {
            return ArrayTable.this.columnList.get(this.columnIndex);
         }

         @CheckForNull
         public Object getValue() {
            return ArrayTable.this.at(this.rowIndex, this.columnIndex);
         }
      };
   }

   @CheckForNull
   private Object getValue(int index) {
      int rowIndex = index / this.columnList.size();
      int columnIndex = index % this.columnList.size();
      return this.at(rowIndex, columnIndex);
   }

   public Map column(Object columnKey) {
      Preconditions.checkNotNull(columnKey);
      Integer columnIndex = (Integer)this.columnKeyToIndex.get(columnKey);
      return (Map)(columnIndex == null ? Collections.emptyMap() : new Column(columnIndex));
   }

   public ImmutableSet columnKeySet() {
      return this.columnKeyToIndex.keySet();
   }

   public Map columnMap() {
      ArrayTable<R, C, V>.ColumnMap map = this.columnMap;
      return map == null ? (this.columnMap = new ColumnMap()) : map;
   }

   public Map row(Object rowKey) {
      Preconditions.checkNotNull(rowKey);
      Integer rowIndex = (Integer)this.rowKeyToIndex.get(rowKey);
      return (Map)(rowIndex == null ? Collections.emptyMap() : new Row(rowIndex));
   }

   public ImmutableSet rowKeySet() {
      return this.rowKeyToIndex.keySet();
   }

   public Map rowMap() {
      ArrayTable<R, C, V>.RowMap map = this.rowMap;
      return map == null ? (this.rowMap = new RowMap()) : map;
   }

   public Collection values() {
      return super.values();
   }

   Iterator valuesIterator() {
      return new AbstractIndexedListIterator(this.size()) {
         @CheckForNull
         protected Object get(int index) {
            return ArrayTable.this.getValue(index);
         }
      };
   }

   Spliterator valuesSpliterator() {
      return CollectSpliterators.indexed(this.size(), 16, this::getValue);
   }

   private abstract static class ArrayMap extends Maps.IteratorBasedAbstractMap {
      private final ImmutableMap keyIndex;

      private ArrayMap(ImmutableMap keyIndex) {
         this.keyIndex = keyIndex;
      }

      public Set keySet() {
         return this.keyIndex.keySet();
      }

      Object getKey(int index) {
         return this.keyIndex.keySet().asList().get(index);
      }

      abstract String getKeyRole();

      @ParametricNullness
      abstract Object getValue(int index);

      @ParametricNullness
      abstract Object setValue(int index, @ParametricNullness Object newValue);

      public int size() {
         return this.keyIndex.size();
      }

      public boolean isEmpty() {
         return this.keyIndex.isEmpty();
      }

      Map.Entry getEntry(final int index) {
         Preconditions.checkElementIndex(index, this.size());
         return new AbstractMapEntry() {
            public Object getKey() {
               return ArrayMap.this.getKey(index);
            }

            @ParametricNullness
            public Object getValue() {
               return ArrayMap.this.getValue(index);
            }

            @ParametricNullness
            public Object setValue(@ParametricNullness Object value) {
               return ArrayMap.this.setValue(index, value);
            }
         };
      }

      Iterator entryIterator() {
         return new AbstractIndexedListIterator(this.size()) {
            protected Map.Entry get(final int index) {
               return ArrayMap.this.getEntry(index);
            }
         };
      }

      Spliterator entrySpliterator() {
         return CollectSpliterators.indexed(this.size(), 16, this::getEntry);
      }

      public boolean containsKey(@CheckForNull Object key) {
         return this.keyIndex.containsKey(key);
      }

      @CheckForNull
      public Object get(@CheckForNull Object key) {
         Integer index = (Integer)this.keyIndex.get(key);
         return index == null ? null : this.getValue(index);
      }

      @CheckForNull
      public Object put(Object key, @ParametricNullness Object value) {
         Integer index = (Integer)this.keyIndex.get(key);
         if (index == null) {
            throw new IllegalArgumentException(this.getKeyRole() + " " + key + " not in " + this.keyIndex.keySet());
         } else {
            return this.setValue(index, value);
         }
      }

      @CheckForNull
      public Object remove(@CheckForNull Object key) {
         throw new UnsupportedOperationException();
      }

      public void clear() {
         throw new UnsupportedOperationException();
      }
   }

   private class Column extends ArrayMap {
      final int columnIndex;

      Column(int columnIndex) {
         super(ArrayTable.this.rowKeyToIndex, null);
         this.columnIndex = columnIndex;
      }

      String getKeyRole() {
         return "Row";
      }

      @CheckForNull
      Object getValue(int index) {
         return ArrayTable.this.at(index, this.columnIndex);
      }

      @CheckForNull
      Object setValue(int index, @CheckForNull Object newValue) {
         return ArrayTable.this.set(index, this.columnIndex, newValue);
      }
   }

   private class ColumnMap extends ArrayMap {
      private ColumnMap() {
         super(ArrayTable.this.columnKeyToIndex, null);
      }

      String getKeyRole() {
         return "Column";
      }

      Map getValue(int index) {
         return ArrayTable.this.new Column(index);
      }

      Map setValue(int index, Map newValue) {
         throw new UnsupportedOperationException();
      }

      @CheckForNull
      public Map put(Object key, Map value) {
         throw new UnsupportedOperationException();
      }
   }

   private class Row extends ArrayMap {
      final int rowIndex;

      Row(int rowIndex) {
         super(ArrayTable.this.columnKeyToIndex, null);
         this.rowIndex = rowIndex;
      }

      String getKeyRole() {
         return "Column";
      }

      @CheckForNull
      Object getValue(int index) {
         return ArrayTable.this.at(this.rowIndex, index);
      }

      @CheckForNull
      Object setValue(int index, @CheckForNull Object newValue) {
         return ArrayTable.this.set(this.rowIndex, index, newValue);
      }
   }

   private class RowMap extends ArrayMap {
      private RowMap() {
         super(ArrayTable.this.rowKeyToIndex, null);
      }

      String getKeyRole() {
         return "Row";
      }

      Map getValue(int index) {
         return ArrayTable.this.new Row(index);
      }

      Map setValue(int index, Map newValue) {
         throw new UnsupportedOperationException();
      }

      @CheckForNull
      public Map put(Object key, Map value) {
         throw new UnsupportedOperationException();
      }
   }
}
