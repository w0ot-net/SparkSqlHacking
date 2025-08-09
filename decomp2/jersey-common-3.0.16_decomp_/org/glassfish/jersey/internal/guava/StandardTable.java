package org.glassfish.jersey.internal.guava;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

class StandardTable extends AbstractTable implements Serializable {
   private static final long serialVersionUID = 0L;
   private final Map backingMap;
   private final Supplier factory;
   private transient Set columnKeySet;
   private transient Map rowMap;
   private transient ColumnMap columnMap;

   StandardTable(Map backingMap, Supplier factory) {
      this.backingMap = backingMap;
      this.factory = factory;
   }

   public boolean contains(Object rowKey, Object columnKey) {
      return rowKey != null && columnKey != null && super.contains(rowKey, columnKey);
   }

   public boolean containsColumn(Object columnKey) {
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

   public boolean containsRow(Object rowKey) {
      return rowKey != null && Maps.safeContainsKey(this.backingMap, rowKey);
   }

   public boolean containsValue(Object value) {
      return value != null && super.containsValue(value);
   }

   public Object get(Object rowKey, Object columnKey) {
      return rowKey != null && columnKey != null ? super.get(rowKey, columnKey) : null;
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

   public Object put(Object rowKey, Object columnKey, Object value) {
      Preconditions.checkNotNull(rowKey);
      Preconditions.checkNotNull(columnKey);
      Preconditions.checkNotNull(value);
      return this.getOrCreate(rowKey).put(columnKey, value);
   }

   public Object remove(Object rowKey, Object columnKey) {
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

   private Map removeColumn(Object column) {
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

   private boolean containsMapping(Object rowKey, Object columnKey, Object value) {
      return value != null && value.equals(this.get(rowKey, columnKey));
   }

   private boolean removeMapping(Object rowKey, Object columnKey, Object value) {
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

   private Iterator createColumnKeyIterator() {
      return new ColumnKeyIterator();
   }

   public Map rowMap() {
      Map<R, Map<C, V>> result = this.rowMap;
      return result == null ? (this.rowMap = this.createRowMap()) : result;
   }

   private Map createRowMap() {
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

         Map.Entry<C, V> columnEntry = (Map.Entry)this.columnIterator.next();
         return Tables.immutableCell(this.rowEntry.getKey(), columnEntry.getKey(), columnEntry.getValue());
      }

      public void remove() {
         this.columnIterator.remove();
         if (((Map)this.rowEntry.getValue()).isEmpty()) {
            this.rowIterator.remove();
         }

      }
   }

   class Row extends Maps.ImprovedAbstractMap {
      final Object rowKey;
      Map backingRowMap;

      Row(Object rowKey) {
         this.rowKey = Preconditions.checkNotNull(rowKey);
      }

      Map backingRowMap() {
         return this.backingRowMap != null && (!this.backingRowMap.isEmpty() || !StandardTable.this.backingMap.containsKey(this.rowKey)) ? this.backingRowMap : (this.backingRowMap = this.computeBackingRowMap());
      }

      Map computeBackingRowMap() {
         return (Map)StandardTable.this.backingMap.get(this.rowKey);
      }

      void maintainEmptyInvariant() {
         if (this.backingRowMap() != null && this.backingRowMap.isEmpty()) {
            StandardTable.this.backingMap.remove(this.rowKey);
            this.backingRowMap = null;
         }

      }

      public boolean containsKey(Object key) {
         Map<C, V> backingRowMap = this.backingRowMap();
         return key != null && backingRowMap != null && Maps.safeContainsKey(backingRowMap, key);
      }

      public Object get(Object key) {
         Map<C, V> backingRowMap = this.backingRowMap();
         return key != null && backingRowMap != null ? Maps.safeGet(backingRowMap, key) : null;
      }

      public Object put(Object key, Object value) {
         Preconditions.checkNotNull(key);
         Preconditions.checkNotNull(value);
         return this.backingRowMap != null && !this.backingRowMap.isEmpty() ? this.backingRowMap.put(key, value) : StandardTable.this.put(this.rowKey, key, value);
      }

      public Object remove(Object key) {
         Map<C, V> backingRowMap = this.backingRowMap();
         if (backingRowMap == null) {
            return null;
         } else {
            V result = (V)Maps.safeRemove(backingRowMap, key);
            this.maintainEmptyInvariant();
            return result;
         }
      }

      public void clear() {
         Map<C, V> backingRowMap = this.backingRowMap();
         if (backingRowMap != null) {
            backingRowMap.clear();
         }

         this.maintainEmptyInvariant();
      }

      protected Set createEntrySet() {
         return new RowEntrySet();
      }

      private final class RowEntrySet extends Maps.EntrySet {
         private RowEntrySet() {
         }

         Map map() {
            return Row.this;
         }

         public int size() {
            Map<C, V> map = Row.this.backingRowMap();
            return map == null ? 0 : map.size();
         }

         public Iterator iterator() {
            Map<C, V> map = Row.this.backingRowMap();
            if (map == null) {
               return Iterators.emptyModifiableIterator();
            } else {
               final Iterator<Map.Entry<C, V>> iterator = map.entrySet().iterator();
               return new Iterator() {
                  public boolean hasNext() {
                     return iterator.hasNext();
                  }

                  public Map.Entry next() {
                     final Map.Entry<C, V> entry = (Map.Entry)iterator.next();
                     return new ForwardingMapEntry() {
                        protected Map.Entry delegate() {
                           return entry;
                        }

                        public Object setValue(Object value) {
                           return super.setValue(Preconditions.checkNotNull(value));
                        }

                        public boolean equals(Object object) {
                           return this.standardEquals(object);
                        }
                     };
                  }

                  public void remove() {
                     iterator.remove();
                     Row.this.maintainEmptyInvariant();
                  }
               };
            }
         }
      }
   }

   private class Column extends Maps.ImprovedAbstractMap {
      final Object columnKey;

      Column(Object columnKey) {
         this.columnKey = Preconditions.checkNotNull(columnKey);
      }

      public Object put(Object key, Object value) {
         return StandardTable.this.put(key, this.columnKey, value);
      }

      public Object get(Object key) {
         return StandardTable.this.get(key, this.columnKey);
      }

      public boolean containsKey(Object key) {
         return StandardTable.this.contains(key, this.columnKey);
      }

      public Object remove(Object key) {
         return StandardTable.this.remove(key, this.columnKey);
      }

      boolean removeFromColumnIf(Predicate predicate) {
         boolean changed = false;
         Iterator<Map.Entry<R, Map<C, V>>> iterator = StandardTable.this.backingMap.entrySet().iterator();

         while(iterator.hasNext()) {
            Map.Entry<R, Map<C, V>> entry = (Map.Entry)iterator.next();
            Map<C, V> map = (Map)entry.getValue();
            V value = (V)map.get(this.columnKey);
            if (value != null && predicate.test(Maps.immutableEntry(entry.getKey(), value))) {
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

         public boolean contains(Object o) {
            if (o instanceof Map.Entry) {
               Map.Entry<?, ?> entry = (Map.Entry)o;
               return StandardTable.this.containsMapping(entry.getKey(), Column.this.columnKey, entry.getValue());
            } else {
               return false;
            }
         }

         public boolean remove(Object obj) {
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

         protected Map.Entry computeNext() {
            while(true) {
               if (this.iterator.hasNext()) {
                  final Map.Entry<R, Map<C, V>> entry = (Map.Entry)this.iterator.next();
                  if (!((Map)entry.getValue()).containsKey(Column.this.columnKey)) {
                     continue;
                  }

                  return new AbstractMapEntry() {
                     public Object getKey() {
                        return entry.getKey();
                     }

                     public Object getValue() {
                        return ((Map)entry.getValue()).get(Column.this.columnKey);
                     }

                     public Object setValue(Object value) {
                        return ((Map)entry.getValue()).put(Column.this.columnKey, Preconditions.checkNotNull(value));
                     }
                  };
               }

               return (Map.Entry)this.endOfData();
            }
         }
      }

      private class KeySet extends Maps.KeySet {
         KeySet() {
            super(Column.this);
         }

         public boolean contains(Object obj) {
            return StandardTable.this.contains(obj, Column.this.columnKey);
         }

         public boolean remove(Object obj) {
            return StandardTable.this.remove(obj, Column.this.columnKey) != null;
         }

         public boolean retainAll(Collection c) {
            return Column.this.removeFromColumnIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(c))));
         }
      }

      private class Values extends Maps.Values {
         Values() {
            super(Column.this);
         }

         public boolean remove(Object obj) {
            return obj != null && Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.equalTo(obj)));
         }

         public boolean removeAll(Collection c) {
            return Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.in(c)));
         }

         public boolean retainAll(Collection c) {
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

      public boolean remove(Object obj) {
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

      public boolean contains(Object obj) {
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

   class RowMap extends Maps.ImprovedAbstractMap {
      public boolean containsKey(Object key) {
         return StandardTable.this.containsRow(key);
      }

      public Map get(Object key) {
         return StandardTable.this.containsRow(key) ? StandardTable.this.row(key) : null;
      }

      public Map remove(Object key) {
         return key == null ? null : (Map)StandardTable.this.backingMap.remove(key);
      }

      protected Set createEntrySet() {
         return new EntrySet();
      }

      class EntrySet extends TableSet {
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

         public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
               return false;
            } else {
               Map.Entry<?, ?> entry = (Map.Entry)obj;
               return entry.getKey() != null && entry.getValue() instanceof Map && Collections2.safeContains(StandardTable.this.backingMap.entrySet(), entry);
            }
         }

         public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
               return false;
            } else {
               Map.Entry<?, ?> entry = (Map.Entry)obj;
               return entry.getKey() != null && entry.getValue() instanceof Map && StandardTable.this.backingMap.entrySet().remove(entry);
            }
         }
      }
   }

   private class ColumnMap extends Maps.ImprovedAbstractMap {
      private ColumnMap() {
      }

      public Map get(Object key) {
         return StandardTable.this.containsColumn(key) ? StandardTable.this.column(key) : null;
      }

      public boolean containsKey(Object key) {
         return StandardTable.this.containsColumn(key);
      }

      public Map remove(Object key) {
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

      class ColumnMapEntrySet extends TableSet {
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

         public boolean contains(Object obj) {
            if (obj instanceof Map.Entry) {
               Map.Entry<?, ?> entry = (Map.Entry)obj;
               if (StandardTable.this.containsColumn(entry.getKey())) {
                  C columnKey = (C)entry.getKey();
                  return ColumnMap.this.get(columnKey).equals(entry.getValue());
               }
            }

            return false;
         }

         public boolean remove(Object obj) {
            if (this.contains(obj)) {
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

         public boolean remove(Object obj) {
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
