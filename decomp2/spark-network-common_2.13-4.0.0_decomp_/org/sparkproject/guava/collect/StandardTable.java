package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Function;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.base.Predicate;
import org.sparkproject.guava.base.Predicates;
import org.sparkproject.guava.base.Supplier;

@ElementTypesAreNonnullByDefault
@GwtCompatible
class StandardTable extends AbstractTable implements Serializable {
   @GwtTransient
   final Map backingMap;
   @GwtTransient
   final Supplier factory;
   @LazyInit
   @CheckForNull
   private transient Set columnKeySet;
   @LazyInit
   @CheckForNull
   private transient Map rowMap;
   @LazyInit
   @CheckForNull
   private transient ColumnMap columnMap;
   private static final long serialVersionUID = 0L;

   StandardTable(Map backingMap, Supplier factory) {
      this.backingMap = backingMap;
      this.factory = factory;
   }

   public boolean contains(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
      return rowKey != null && columnKey != null && super.contains(rowKey, columnKey);
   }

   public boolean containsColumn(@CheckForNull Object columnKey) {
      if (columnKey == null) {
         return false;
      } else {
         for(Map map : this.backingMap.values()) {
            if (Maps.safeContainsKey(map, columnKey)) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean containsRow(@CheckForNull Object rowKey) {
      return rowKey != null && Maps.safeContainsKey(this.backingMap, rowKey);
   }

   public boolean containsValue(@CheckForNull Object value) {
      return value != null && super.containsValue(value);
   }

   @CheckForNull
   public Object get(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
      return rowKey != null && columnKey != null ? super.get(rowKey, columnKey) : null;
   }

   public boolean isEmpty() {
      return this.backingMap.isEmpty();
   }

   public int size() {
      int size = 0;

      for(Map map : this.backingMap.values()) {
         size += map.size();
      }

      return size;
   }

   public void clear() {
      this.backingMap.clear();
   }

   private Map getOrCreate(Object rowKey) {
      Map<C, V> map = (Map)this.backingMap.get(rowKey);
      if (map == null) {
         map = (Map)this.factory.get();
         this.backingMap.put(rowKey, map);
      }

      return map;
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object put(Object rowKey, Object columnKey, Object value) {
      Preconditions.checkNotNull(rowKey);
      Preconditions.checkNotNull(columnKey);
      Preconditions.checkNotNull(value);
      return this.getOrCreate(rowKey).put(columnKey, value);
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object remove(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
      if (rowKey != null && columnKey != null) {
         Map<C, V> map = (Map)Maps.safeGet(this.backingMap, rowKey);
         if (map == null) {
            return null;
         } else {
            V value = (V)map.remove(columnKey);
            if (map.isEmpty()) {
               this.backingMap.remove(rowKey);
            }

            return value;
         }
      } else {
         return null;
      }
   }

   @CanIgnoreReturnValue
   private Map removeColumn(@CheckForNull Object column) {
      Map<R, V> output = new LinkedHashMap();
      Iterator<Map.Entry<R, Map<C, V>>> iterator = this.backingMap.entrySet().iterator();

      while(iterator.hasNext()) {
         Map.Entry<R, Map<C, V>> entry = (Map.Entry)iterator.next();
         V value = (V)((Map)entry.getValue()).remove(column);
         if (value != null) {
            output.put(entry.getKey(), value);
            if (((Map)entry.getValue()).isEmpty()) {
               iterator.remove();
            }
         }
      }

      return output;
   }

   private boolean containsMapping(@CheckForNull Object rowKey, @CheckForNull Object columnKey, @CheckForNull Object value) {
      return value != null && value.equals(this.get(rowKey, columnKey));
   }

   private boolean removeMapping(@CheckForNull Object rowKey, @CheckForNull Object columnKey, @CheckForNull Object value) {
      if (this.containsMapping(rowKey, columnKey, value)) {
         this.remove(rowKey, columnKey);
         return true;
      } else {
         return false;
      }
   }

   public Set cellSet() {
      return super.cellSet();
   }

   Iterator cellIterator() {
      return new CellIterator();
   }

   Spliterator cellSpliterator() {
      return CollectSpliterators.flatMap(this.backingMap.entrySet().spliterator(), (rowEntry) -> CollectSpliterators.map(((Map)rowEntry.getValue()).entrySet().spliterator(), (columnEntry) -> Tables.immutableCell(rowEntry.getKey(), columnEntry.getKey(), columnEntry.getValue())), 65, (long)this.size());
   }

   public Map row(Object rowKey) {
      return new Row(rowKey);
   }

   public Map column(Object columnKey) {
      return new Column(columnKey);
   }

   public Set rowKeySet() {
      return this.rowMap().keySet();
   }

   public Set columnKeySet() {
      Set<C> result = this.columnKeySet;
      return result == null ? (this.columnKeySet = new ColumnKeySet()) : result;
   }

   Iterator createColumnKeyIterator() {
      return new ColumnKeyIterator();
   }

   public Collection values() {
      return super.values();
   }

   public Map rowMap() {
      Map<R, Map<C, V>> result = this.rowMap;
      return result == null ? (this.rowMap = this.createRowMap()) : result;
   }

   Map createRowMap() {
      return new RowMap();
   }

   public Map columnMap() {
      StandardTable<R, C, V>.ColumnMap result = this.columnMap;
      return result == null ? (this.columnMap = new ColumnMap()) : result;
   }

   private abstract class TableSet extends Sets.ImprovedAbstractSet {
      private TableSet() {
      }

      public boolean isEmpty() {
         return StandardTable.this.backingMap.isEmpty();
      }

      public void clear() {
         StandardTable.this.backingMap.clear();
      }
   }

   private class CellIterator implements Iterator {
      final Iterator rowIterator;
      @CheckForNull
      Map.Entry rowEntry;
      Iterator columnIterator;

      private CellIterator() {
         this.rowIterator = StandardTable.this.backingMap.entrySet().iterator();
         this.columnIterator = Iterators.emptyModifiableIterator();
      }

      public boolean hasNext() {
         return this.rowIterator.hasNext() || this.columnIterator.hasNext();
      }

      public Table.Cell next() {
         if (!this.columnIterator.hasNext()) {
            this.rowEntry = (Map.Entry)this.rowIterator.next();
            this.columnIterator = ((Map)this.rowEntry.getValue()).entrySet().iterator();
         }

         Objects.requireNonNull(this.rowEntry);
         Map.Entry<C, V> columnEntry = (Map.Entry)this.columnIterator.next();
         return Tables.immutableCell(this.rowEntry.getKey(), columnEntry.getKey(), columnEntry.getValue());
      }

      public void remove() {
         this.columnIterator.remove();
         if (((Map)((Map.Entry)Objects.requireNonNull(this.rowEntry)).getValue()).isEmpty()) {
            this.rowIterator.remove();
            this.rowEntry = null;
         }

      }
   }

   class Row extends Maps.IteratorBasedAbstractMap {
      final Object rowKey;
      @CheckForNull
      Map backingRowMap;

      Row(Object rowKey) {
         this.rowKey = Preconditions.checkNotNull(rowKey);
      }

      final void updateBackingRowMapField() {
         if (this.backingRowMap == null || this.backingRowMap.isEmpty() && StandardTable.this.backingMap.containsKey(this.rowKey)) {
            this.backingRowMap = this.computeBackingRowMap();
         }

      }

      @CheckForNull
      Map computeBackingRowMap() {
         return (Map)StandardTable.this.backingMap.get(this.rowKey);
      }

      void maintainEmptyInvariant() {
         this.updateBackingRowMapField();
         if (this.backingRowMap != null && this.backingRowMap.isEmpty()) {
            StandardTable.this.backingMap.remove(this.rowKey);
            this.backingRowMap = null;
         }

      }

      public boolean containsKey(@CheckForNull Object key) {
         this.updateBackingRowMapField();
         return key != null && this.backingRowMap != null && Maps.safeContainsKey(this.backingRowMap, key);
      }

      @CheckForNull
      public Object get(@CheckForNull Object key) {
         this.updateBackingRowMapField();
         return key != null && this.backingRowMap != null ? Maps.safeGet(this.backingRowMap, key) : null;
      }

      @CheckForNull
      public Object put(Object key, Object value) {
         Preconditions.checkNotNull(key);
         Preconditions.checkNotNull(value);
         return this.backingRowMap != null && !this.backingRowMap.isEmpty() ? this.backingRowMap.put(key, value) : StandardTable.this.put(this.rowKey, key, value);
      }

      @CheckForNull
      public Object remove(@CheckForNull Object key) {
         this.updateBackingRowMapField();
         if (this.backingRowMap == null) {
            return null;
         } else {
            V result = (V)Maps.safeRemove(this.backingRowMap, key);
            this.maintainEmptyInvariant();
            return result;
         }
      }

      public void clear() {
         this.updateBackingRowMapField();
         if (this.backingRowMap != null) {
            this.backingRowMap.clear();
         }

         this.maintainEmptyInvariant();
      }

      public int size() {
         this.updateBackingRowMapField();
         return this.backingRowMap == null ? 0 : this.backingRowMap.size();
      }

      Iterator entryIterator() {
         this.updateBackingRowMapField();
         if (this.backingRowMap == null) {
            return Iterators.emptyModifiableIterator();
         } else {
            final Iterator<Map.Entry<C, V>> iterator = this.backingRowMap.entrySet().iterator();
            return new Iterator() {
               public boolean hasNext() {
                  return iterator.hasNext();
               }

               public Map.Entry next() {
                  return Row.this.wrapEntry((Map.Entry)iterator.next());
               }

               public void remove() {
                  iterator.remove();
                  Row.this.maintainEmptyInvariant();
               }
            };
         }
      }

      Spliterator entrySpliterator() {
         this.updateBackingRowMapField();
         return this.backingRowMap == null ? Spliterators.emptySpliterator() : CollectSpliterators.map(this.backingRowMap.entrySet().spliterator(), this::wrapEntry);
      }

      Map.Entry wrapEntry(final Map.Entry entry) {
         return new ForwardingMapEntry() {
            protected Map.Entry delegate() {
               return entry;
            }

            public Object setValue(Object value) {
               return super.setValue(Preconditions.checkNotNull(value));
            }

            public boolean equals(@CheckForNull Object object) {
               return this.standardEquals(object);
            }
         };
      }
   }

   private class Column extends Maps.ViewCachingAbstractMap {
      final Object columnKey;

      Column(Object columnKey) {
         this.columnKey = Preconditions.checkNotNull(columnKey);
      }

      @CheckForNull
      public Object put(Object key, Object value) {
         return StandardTable.this.put(key, this.columnKey, value);
      }

      @CheckForNull
      public Object get(@CheckForNull Object key) {
         return StandardTable.this.get(key, this.columnKey);
      }

      public boolean containsKey(@CheckForNull Object key) {
         return StandardTable.this.contains(key, this.columnKey);
      }

      @CheckForNull
      public Object remove(@CheckForNull Object key) {
         return StandardTable.this.remove(key, this.columnKey);
      }

      @CanIgnoreReturnValue
      boolean removeFromColumnIf(Predicate predicate) {
         boolean changed = false;
         Iterator<Map.Entry<R, Map<C, V>>> iterator = StandardTable.this.backingMap.entrySet().iterator();

         while(iterator.hasNext()) {
            Map.Entry<R, Map<C, V>> entry = (Map.Entry)iterator.next();
            Map<C, V> map = (Map)entry.getValue();
            V value = (V)map.get(this.columnKey);
            if (value != null && predicate.apply(Maps.immutableEntry(entry.getKey(), value))) {
               map.remove(this.columnKey);
               changed = true;
               if (map.isEmpty()) {
                  iterator.remove();
               }
            }
         }

         return changed;
      }

      Set createEntrySet() {
         return new EntrySet();
      }

      Set createKeySet() {
         return new KeySet();
      }

      Collection createValues() {
         return new Values();
      }

      private class EntrySet extends Sets.ImprovedAbstractSet {
         private EntrySet() {
         }

         public Iterator iterator() {
            return Column.this.new EntrySetIterator();
         }

         public int size() {
            int size = 0;

            for(Map map : StandardTable.this.backingMap.values()) {
               if (map.containsKey(Column.this.columnKey)) {
                  ++size;
               }
            }

            return size;
         }

         public boolean isEmpty() {
            return !StandardTable.this.containsColumn(Column.this.columnKey);
         }

         public void clear() {
            Column.this.removeFromColumnIf(Predicates.alwaysTrue());
         }

         public boolean contains(@CheckForNull Object o) {
            if (o instanceof Map.Entry) {
               Map.Entry<?, ?> entry = (Map.Entry)o;
               return StandardTable.this.containsMapping(entry.getKey(), Column.this.columnKey, entry.getValue());
            } else {
               return false;
            }
         }

         public boolean remove(@CheckForNull Object obj) {
            if (obj instanceof Map.Entry) {
               Map.Entry<?, ?> entry = (Map.Entry)obj;
               return StandardTable.this.removeMapping(entry.getKey(), Column.this.columnKey, entry.getValue());
            } else {
               return false;
            }
         }

         public boolean retainAll(Collection c) {
            return Column.this.removeFromColumnIf(Predicates.not(Predicates.in(c)));
         }
      }

      private class EntrySetIterator extends AbstractIterator {
         final Iterator iterator;

         private EntrySetIterator() {
            this.iterator = StandardTable.this.backingMap.entrySet().iterator();
         }

         @CheckForNull
         protected Map.Entry computeNext() {
            while(true) {
               if (this.iterator.hasNext()) {
                  final Map.Entry<R, Map<C, V>> entry = (Map.Entry)this.iterator.next();
                  if (!((Map)entry.getValue()).containsKey(Column.this.columnKey)) {
                     continue;
                  }

                  class EntryImpl extends AbstractMapEntry {
                     public Object getKey() {
                        return entry.getKey();
                     }

                     public Object getValue() {
                        return ((Map)entry.getValue()).get(Column.this.columnKey);
                     }

                     public Object setValue(Object value) {
                        return NullnessCasts.uncheckedCastNullableTToT(((Map)entry.getValue()).put(Column.this.columnKey, Preconditions.checkNotNull(value)));
                     }
                  }

                  return new EntryImpl();
               }

               return (Map.Entry)this.endOfData();
            }
         }
      }

      private class KeySet extends Maps.KeySet {
         KeySet() {
            super(Column.this);
         }

         public boolean contains(@CheckForNull Object obj) {
            return StandardTable.this.contains(obj, Column.this.columnKey);
         }

         public boolean remove(@CheckForNull Object obj) {
            return StandardTable.this.remove(obj, Column.this.columnKey) != null;
         }

         public boolean retainAll(final Collection c) {
            return Column.this.removeFromColumnIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(c))));
         }
      }

      private class Values extends Maps.Values {
         Values() {
            super(Column.this);
         }

         public boolean remove(@CheckForNull Object obj) {
            return obj != null && Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.equalTo(obj)));
         }

         public boolean removeAll(final Collection c) {
            return Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.in(c)));
         }

         public boolean retainAll(final Collection c) {
            return Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(c))));
         }
      }
   }

   private class ColumnKeySet extends TableSet {
      private ColumnKeySet() {
      }

      public Iterator iterator() {
         return StandardTable.this.createColumnKeyIterator();
      }

      public int size() {
         return Iterators.size(this.iterator());
      }

      public boolean remove(@CheckForNull Object obj) {
         if (obj == null) {
            return false;
         } else {
            boolean changed = false;
            Iterator<Map<C, V>> iterator = StandardTable.this.backingMap.values().iterator();

            while(iterator.hasNext()) {
               Map<C, V> map = (Map)iterator.next();
               if (map.keySet().remove(obj)) {
                  changed = true;
                  if (map.isEmpty()) {
                     iterator.remove();
                  }
               }
            }

            return changed;
         }
      }

      public boolean removeAll(Collection c) {
         Preconditions.checkNotNull(c);
         boolean changed = false;
         Iterator<Map<C, V>> iterator = StandardTable.this.backingMap.values().iterator();

         while(iterator.hasNext()) {
            Map<C, V> map = (Map)iterator.next();
            if (Iterators.removeAll(map.keySet().iterator(), c)) {
               changed = true;
               if (map.isEmpty()) {
                  iterator.remove();
               }
            }
         }

         return changed;
      }

      public boolean retainAll(Collection c) {
         Preconditions.checkNotNull(c);
         boolean changed = false;
         Iterator<Map<C, V>> iterator = StandardTable.this.backingMap.values().iterator();

         while(iterator.hasNext()) {
            Map<C, V> map = (Map)iterator.next();
            if (map.keySet().retainAll(c)) {
               changed = true;
               if (map.isEmpty()) {
                  iterator.remove();
               }
            }
         }

         return changed;
      }

      public boolean contains(@CheckForNull Object obj) {
         return StandardTable.this.containsColumn(obj);
      }
   }

   private class ColumnKeyIterator extends AbstractIterator {
      final Map seen;
      final Iterator mapIterator;
      Iterator entryIterator;

      private ColumnKeyIterator() {
         this.seen = (Map)StandardTable.this.factory.get();
         this.mapIterator = StandardTable.this.backingMap.values().iterator();
         this.entryIterator = Iterators.emptyIterator();
      }

      @CheckForNull
      protected Object computeNext() {
         while(true) {
            if (this.entryIterator.hasNext()) {
               Map.Entry<C, V> entry = (Map.Entry)this.entryIterator.next();
               if (!this.seen.containsKey(entry.getKey())) {
                  this.seen.put(entry.getKey(), entry.getValue());
                  return entry.getKey();
               }
            } else {
               if (!this.mapIterator.hasNext()) {
                  return this.endOfData();
               }

               this.entryIterator = ((Map)this.mapIterator.next()).entrySet().iterator();
            }
         }
      }
   }

   class RowMap extends Maps.ViewCachingAbstractMap {
      public boolean containsKey(@CheckForNull Object key) {
         return StandardTable.this.containsRow(key);
      }

      @CheckForNull
      public Map get(@CheckForNull Object key) {
         return StandardTable.this.containsRow(key) ? StandardTable.this.row(Objects.requireNonNull(key)) : null;
      }

      @CheckForNull
      public Map remove(@CheckForNull Object key) {
         return key == null ? null : (Map)StandardTable.this.backingMap.remove(key);
      }

      protected Set createEntrySet() {
         return new EntrySet();
      }

      private final class EntrySet extends TableSet {
         private EntrySet() {
         }

         public Iterator iterator() {
            return Maps.asMapEntryIterator(StandardTable.this.backingMap.keySet(), new Function() {
               public Map apply(Object rowKey) {
                  return StandardTable.this.row(rowKey);
               }
            });
         }

         public int size() {
            return StandardTable.this.backingMap.size();
         }

         public boolean contains(@CheckForNull Object obj) {
            if (!(obj instanceof Map.Entry)) {
               return false;
            } else {
               Map.Entry<?, ?> entry = (Map.Entry)obj;
               return entry.getKey() != null && entry.getValue() instanceof Map && Collections2.safeContains(StandardTable.this.backingMap.entrySet(), entry);
            }
         }

         public boolean remove(@CheckForNull Object obj) {
            if (!(obj instanceof Map.Entry)) {
               return false;
            } else {
               Map.Entry<?, ?> entry = (Map.Entry)obj;
               return entry.getKey() != null && entry.getValue() instanceof Map && StandardTable.this.backingMap.entrySet().remove(entry);
            }
         }
      }
   }

   private class ColumnMap extends Maps.ViewCachingAbstractMap {
      private ColumnMap() {
      }

      @CheckForNull
      public Map get(@CheckForNull Object key) {
         return StandardTable.this.containsColumn(key) ? StandardTable.this.column(Objects.requireNonNull(key)) : null;
      }

      public boolean containsKey(@CheckForNull Object key) {
         return StandardTable.this.containsColumn(key);
      }

      @CheckForNull
      public Map remove(@CheckForNull Object key) {
         return StandardTable.this.containsColumn(key) ? StandardTable.this.removeColumn(key) : null;
      }

      public Set createEntrySet() {
         return new ColumnMapEntrySet();
      }

      public Set keySet() {
         return StandardTable.this.columnKeySet();
      }

      Collection createValues() {
         return new ColumnMapValues();
      }

      private final class ColumnMapEntrySet extends TableSet {
         private ColumnMapEntrySet() {
         }

         public Iterator iterator() {
            return Maps.asMapEntryIterator(StandardTable.this.columnKeySet(), new Function() {
               public Map apply(Object columnKey) {
                  return StandardTable.this.column(columnKey);
               }
            });
         }

         public int size() {
            return StandardTable.this.columnKeySet().size();
         }

         public boolean contains(@CheckForNull Object obj) {
            if (obj instanceof Map.Entry) {
               Map.Entry<?, ?> entry = (Map.Entry)obj;
               if (StandardTable.this.containsColumn(entry.getKey())) {
                  return ((Map)Objects.requireNonNull(ColumnMap.this.get(entry.getKey()))).equals(entry.getValue());
               }
            }

            return false;
         }

         public boolean remove(@CheckForNull Object obj) {
            if (this.contains(obj) && obj instanceof Map.Entry) {
               Map.Entry<?, ?> entry = (Map.Entry)obj;
               StandardTable.this.removeColumn(entry.getKey());
               return true;
            } else {
               return false;
            }
         }

         public boolean removeAll(Collection c) {
            Preconditions.checkNotNull(c);
            return Sets.removeAllImpl(this, (Iterator)c.iterator());
         }

         public boolean retainAll(Collection c) {
            Preconditions.checkNotNull(c);
            boolean changed = false;

            for(Object columnKey : Lists.newArrayList(StandardTable.this.columnKeySet().iterator())) {
               if (!c.contains(Maps.immutableEntry(columnKey, StandardTable.this.column(columnKey)))) {
                  StandardTable.this.removeColumn(columnKey);
                  changed = true;
               }
            }

            return changed;
         }
      }

      private class ColumnMapValues extends Maps.Values {
         ColumnMapValues() {
            super(ColumnMap.this);
         }

         public boolean remove(@CheckForNull Object obj) {
            for(Map.Entry entry : ColumnMap.this.entrySet()) {
               if (((Map)entry.getValue()).equals(obj)) {
                  StandardTable.this.removeColumn(entry.getKey());
                  return true;
               }
            }

            return false;
         }

         public boolean removeAll(Collection c) {
            Preconditions.checkNotNull(c);
            boolean changed = false;

            for(Object columnKey : Lists.newArrayList(StandardTable.this.columnKeySet().iterator())) {
               if (c.contains(StandardTable.this.column(columnKey))) {
                  StandardTable.this.removeColumn(columnKey);
                  changed = true;
               }
            }

            return changed;
         }

         public boolean retainAll(Collection c) {
            Preconditions.checkNotNull(c);
            boolean changed = false;

            for(Object columnKey : Lists.newArrayList(StandardTable.this.columnKeySet().iterator())) {
               if (!c.contains(StandardTable.this.column(columnKey))) {
                  StandardTable.this.removeColumn(columnKey);
                  changed = true;
               }
            }

            return changed;
         }
      }
   }
}
