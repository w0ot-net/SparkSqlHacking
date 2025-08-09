package org.apache.curator.shaded.com.google.common.collect;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AbstractMapBasedMultimap extends AbstractMultimap implements Serializable {
   private transient Map map;
   private transient int totalSize;
   private static final long serialVersionUID = 2447537837011683357L;

   protected AbstractMapBasedMultimap(Map map) {
      Preconditions.checkArgument(map.isEmpty());
      this.map = map;
   }

   final void setMap(Map map) {
      this.map = map;
      this.totalSize = 0;

      for(Collection values : map.values()) {
         Preconditions.checkArgument(!values.isEmpty());
         this.totalSize += values.size();
      }

   }

   Collection createUnmodifiableEmptyCollection() {
      return this.unmodifiableCollectionSubclass(this.createCollection());
   }

   abstract Collection createCollection();

   Collection createCollection(@ParametricNullness Object key) {
      return this.createCollection();
   }

   Map backingMap() {
      return this.map;
   }

   public int size() {
      return this.totalSize;
   }

   public boolean containsKey(@CheckForNull Object key) {
      return this.map.containsKey(key);
   }

   public boolean put(@ParametricNullness Object key, @ParametricNullness Object value) {
      Collection<V> collection = (Collection)this.map.get(key);
      if (collection == null) {
         collection = this.createCollection(key);
         if (collection.add(value)) {
            ++this.totalSize;
            this.map.put(key, collection);
            return true;
         } else {
            throw new AssertionError("New Collection violated the Collection spec");
         }
      } else if (collection.add(value)) {
         ++this.totalSize;
         return true;
      } else {
         return false;
      }
   }

   private Collection getOrCreateCollection(@ParametricNullness Object key) {
      Collection<V> collection = (Collection)this.map.get(key);
      if (collection == null) {
         collection = this.createCollection(key);
         this.map.put(key, collection);
      }

      return collection;
   }

   public Collection replaceValues(@ParametricNullness Object key, Iterable values) {
      Iterator<? extends V> iterator = values.iterator();
      if (!iterator.hasNext()) {
         return this.removeAll(key);
      } else {
         Collection<V> collection = this.getOrCreateCollection(key);
         Collection<V> oldValues = this.createCollection();
         oldValues.addAll(collection);
         this.totalSize -= collection.size();
         collection.clear();

         while(iterator.hasNext()) {
            if (collection.add(iterator.next())) {
               ++this.totalSize;
            }
         }

         return this.unmodifiableCollectionSubclass(oldValues);
      }
   }

   public Collection removeAll(@CheckForNull Object key) {
      Collection<V> collection = (Collection)this.map.remove(key);
      if (collection == null) {
         return this.createUnmodifiableEmptyCollection();
      } else {
         Collection<V> output = this.createCollection();
         output.addAll(collection);
         this.totalSize -= collection.size();
         collection.clear();
         return this.unmodifiableCollectionSubclass(output);
      }
   }

   Collection unmodifiableCollectionSubclass(Collection collection) {
      return Collections.unmodifiableCollection(collection);
   }

   public void clear() {
      for(Collection collection : this.map.values()) {
         collection.clear();
      }

      this.map.clear();
      this.totalSize = 0;
   }

   public Collection get(@ParametricNullness Object key) {
      Collection<V> collection = (Collection)this.map.get(key);
      if (collection == null) {
         collection = this.createCollection(key);
      }

      return this.wrapCollection(key, collection);
   }

   Collection wrapCollection(@ParametricNullness Object key, Collection collection) {
      return new WrappedCollection(key, collection, (WrappedCollection)null);
   }

   final List wrapList(@ParametricNullness Object key, List list, @CheckForNull WrappedCollection ancestor) {
      return (List)(list instanceof RandomAccess ? new RandomAccessWrappedList(key, list, ancestor) : new WrappedList(key, list, ancestor));
   }

   private static Iterator iteratorOrListIterator(Collection collection) {
      return (Iterator)(collection instanceof List ? ((List)collection).listIterator() : collection.iterator());
   }

   Set createKeySet() {
      return new KeySet(this.map);
   }

   final Set createMaybeNavigableKeySet() {
      if (this.map instanceof NavigableMap) {
         return new NavigableKeySet((NavigableMap)this.map);
      } else {
         return (Set)(this.map instanceof SortedMap ? new SortedKeySet((SortedMap)this.map) : new KeySet(this.map));
      }
   }

   private void removeValuesForKey(@CheckForNull Object key) {
      Collection<V> collection = (Collection)Maps.safeRemove(this.map, key);
      if (collection != null) {
         int count = collection.size();
         collection.clear();
         this.totalSize -= count;
      }

   }

   public Collection values() {
      return super.values();
   }

   Collection createValues() {
      return new AbstractMultimap.Values();
   }

   Iterator valueIterator() {
      return new Itr() {
         @ParametricNullness
         Object output(@ParametricNullness Object key, @ParametricNullness Object value) {
            return value;
         }
      };
   }

   Spliterator valueSpliterator() {
      return CollectSpliterators.flatMap(this.map.values().spliterator(), Collection::spliterator, 64, (long)this.size());
   }

   Multiset createKeys() {
      return new Multimaps.Keys(this);
   }

   public Collection entries() {
      return super.entries();
   }

   Collection createEntries() {
      return (Collection)(this instanceof SetMultimap ? new AbstractMultimap.EntrySet() : new AbstractMultimap.Entries());
   }

   Iterator entryIterator() {
      return new Itr() {
         Map.Entry output(@ParametricNullness Object key, @ParametricNullness Object value) {
            return Maps.immutableEntry(key, value);
         }
      };
   }

   Spliterator entrySpliterator() {
      return CollectSpliterators.flatMap(this.map.entrySet().spliterator(), (keyToValueCollectionEntry) -> {
         K key = (K)keyToValueCollectionEntry.getKey();
         Collection<V> valueCollection = (Collection)keyToValueCollectionEntry.getValue();
         return CollectSpliterators.map(valueCollection.spliterator(), (value) -> Maps.immutableEntry(key, value));
      }, 64, (long)this.size());
   }

   public void forEach(BiConsumer action) {
      Preconditions.checkNotNull(action);
      this.map.forEach((key, valueCollection) -> valueCollection.forEach((value) -> action.accept(key, value)));
   }

   Map createAsMap() {
      return new AsMap(this.map);
   }

   final Map createMaybeNavigableAsMap() {
      if (this.map instanceof NavigableMap) {
         return new NavigableAsMap((NavigableMap)this.map);
      } else {
         return (Map)(this.map instanceof SortedMap ? new SortedAsMap((SortedMap)this.map) : new AsMap(this.map));
      }
   }

   class WrappedCollection extends AbstractCollection {
      @ParametricNullness
      final Object key;
      Collection delegate;
      @CheckForNull
      final WrappedCollection ancestor;
      @CheckForNull
      final Collection ancestorDelegate;

      WrappedCollection(@ParametricNullness Object key, Collection delegate, @CheckForNull WrappedCollection ancestor) {
         this.key = key;
         this.delegate = delegate;
         this.ancestor = ancestor;
         this.ancestorDelegate = ancestor == null ? null : ancestor.getDelegate();
      }

      void refreshIfEmpty() {
         if (this.ancestor != null) {
            this.ancestor.refreshIfEmpty();
            if (this.ancestor.getDelegate() != this.ancestorDelegate) {
               throw new ConcurrentModificationException();
            }
         } else if (this.delegate.isEmpty()) {
            Collection<V> newDelegate = (Collection)AbstractMapBasedMultimap.this.map.get(this.key);
            if (newDelegate != null) {
               this.delegate = newDelegate;
            }
         }

      }

      void removeIfEmpty() {
         if (this.ancestor != null) {
            this.ancestor.removeIfEmpty();
         } else if (this.delegate.isEmpty()) {
            AbstractMapBasedMultimap.this.map.remove(this.key);
         }

      }

      @ParametricNullness
      Object getKey() {
         return this.key;
      }

      void addToMap() {
         if (this.ancestor != null) {
            this.ancestor.addToMap();
         } else {
            AbstractMapBasedMultimap.this.map.put(this.key, this.delegate);
         }

      }

      public int size() {
         this.refreshIfEmpty();
         return this.delegate.size();
      }

      public boolean equals(@CheckForNull Object object) {
         if (object == this) {
            return true;
         } else {
            this.refreshIfEmpty();
            return this.delegate.equals(object);
         }
      }

      public int hashCode() {
         this.refreshIfEmpty();
         return this.delegate.hashCode();
      }

      public String toString() {
         this.refreshIfEmpty();
         return this.delegate.toString();
      }

      Collection getDelegate() {
         return this.delegate;
      }

      public Iterator iterator() {
         this.refreshIfEmpty();
         return new WrappedIterator();
      }

      public Spliterator spliterator() {
         this.refreshIfEmpty();
         return this.delegate.spliterator();
      }

      public boolean add(@ParametricNullness Object value) {
         this.refreshIfEmpty();
         boolean wasEmpty = this.delegate.isEmpty();
         boolean changed = this.delegate.add(value);
         if (changed) {
            AbstractMapBasedMultimap.this.totalSize++;
            if (wasEmpty) {
               this.addToMap();
            }
         }

         return changed;
      }

      @CheckForNull
      WrappedCollection getAncestor() {
         return this.ancestor;
      }

      public boolean addAll(Collection collection) {
         if (collection.isEmpty()) {
            return false;
         } else {
            int oldSize = this.size();
            boolean changed = this.delegate.addAll(collection);
            if (changed) {
               int newSize = this.delegate.size();
               AbstractMapBasedMultimap.this.totalSize = newSize - oldSize;
               if (oldSize == 0) {
                  this.addToMap();
               }
            }

            return changed;
         }
      }

      public boolean contains(@CheckForNull Object o) {
         this.refreshIfEmpty();
         return this.delegate.contains(o);
      }

      public boolean containsAll(Collection c) {
         this.refreshIfEmpty();
         return this.delegate.containsAll(c);
      }

      public void clear() {
         int oldSize = this.size();
         if (oldSize != 0) {
            this.delegate.clear();
            AbstractMapBasedMultimap.this.totalSize = oldSize;
            this.removeIfEmpty();
         }
      }

      public boolean remove(@CheckForNull Object o) {
         this.refreshIfEmpty();
         boolean changed = this.delegate.remove(o);
         if (changed) {
            AbstractMapBasedMultimap.this.totalSize--;
            this.removeIfEmpty();
         }

         return changed;
      }

      public boolean removeAll(Collection c) {
         if (c.isEmpty()) {
            return false;
         } else {
            int oldSize = this.size();
            boolean changed = this.delegate.removeAll(c);
            if (changed) {
               int newSize = this.delegate.size();
               AbstractMapBasedMultimap.this.totalSize = newSize - oldSize;
               this.removeIfEmpty();
            }

            return changed;
         }
      }

      public boolean retainAll(Collection c) {
         Preconditions.checkNotNull(c);
         int oldSize = this.size();
         boolean changed = this.delegate.retainAll(c);
         if (changed) {
            int newSize = this.delegate.size();
            AbstractMapBasedMultimap.this.totalSize = newSize - oldSize;
            this.removeIfEmpty();
         }

         return changed;
      }

      class WrappedIterator implements Iterator {
         final Iterator delegateIterator;
         final Collection originalDelegate;

         WrappedIterator() {
            this.originalDelegate = WrappedCollection.this.delegate;
            this.delegateIterator = AbstractMapBasedMultimap.iteratorOrListIterator(WrappedCollection.this.delegate);
         }

         WrappedIterator(Iterator delegateIterator) {
            this.originalDelegate = WrappedCollection.this.delegate;
            this.delegateIterator = delegateIterator;
         }

         void validateIterator() {
            WrappedCollection.this.refreshIfEmpty();
            if (WrappedCollection.this.delegate != this.originalDelegate) {
               throw new ConcurrentModificationException();
            }
         }

         public boolean hasNext() {
            this.validateIterator();
            return this.delegateIterator.hasNext();
         }

         @ParametricNullness
         public Object next() {
            this.validateIterator();
            return this.delegateIterator.next();
         }

         public void remove() {
            this.delegateIterator.remove();
            AbstractMapBasedMultimap.this.totalSize--;
            WrappedCollection.this.removeIfEmpty();
         }

         Iterator getDelegateIterator() {
            this.validateIterator();
            return this.delegateIterator;
         }
      }
   }

   class WrappedSet extends WrappedCollection implements Set {
      WrappedSet(@ParametricNullness Object key, Set delegate) {
         super(key, delegate, (WrappedCollection)null);
      }

      public boolean removeAll(Collection c) {
         if (c.isEmpty()) {
            return false;
         } else {
            int oldSize = this.size();
            boolean changed = Sets.removeAllImpl((Set)this.delegate, c);
            if (changed) {
               int newSize = this.delegate.size();
               AbstractMapBasedMultimap.this.totalSize = newSize - oldSize;
               this.removeIfEmpty();
            }

            return changed;
         }
      }
   }

   class WrappedSortedSet extends WrappedCollection implements SortedSet {
      WrappedSortedSet(@ParametricNullness Object key, SortedSet delegate, @CheckForNull WrappedCollection ancestor) {
         super(key, delegate, ancestor);
      }

      SortedSet getSortedSetDelegate() {
         return (SortedSet)this.getDelegate();
      }

      @CheckForNull
      public Comparator comparator() {
         return this.getSortedSetDelegate().comparator();
      }

      @ParametricNullness
      public Object first() {
         this.refreshIfEmpty();
         return this.getSortedSetDelegate().first();
      }

      @ParametricNullness
      public Object last() {
         this.refreshIfEmpty();
         return this.getSortedSetDelegate().last();
      }

      public SortedSet headSet(@ParametricNullness Object toElement) {
         this.refreshIfEmpty();
         return AbstractMapBasedMultimap.this.new WrappedSortedSet(this.getKey(), this.getSortedSetDelegate().headSet(toElement), (WrappedCollection)(this.getAncestor() == null ? this : this.getAncestor()));
      }

      public SortedSet subSet(@ParametricNullness Object fromElement, @ParametricNullness Object toElement) {
         this.refreshIfEmpty();
         return AbstractMapBasedMultimap.this.new WrappedSortedSet(this.getKey(), this.getSortedSetDelegate().subSet(fromElement, toElement), (WrappedCollection)(this.getAncestor() == null ? this : this.getAncestor()));
      }

      public SortedSet tailSet(@ParametricNullness Object fromElement) {
         this.refreshIfEmpty();
         return AbstractMapBasedMultimap.this.new WrappedSortedSet(this.getKey(), this.getSortedSetDelegate().tailSet(fromElement), (WrappedCollection)(this.getAncestor() == null ? this : this.getAncestor()));
      }
   }

   class WrappedNavigableSet extends WrappedSortedSet implements NavigableSet {
      WrappedNavigableSet(@ParametricNullness Object key, NavigableSet delegate, @CheckForNull WrappedCollection ancestor) {
         super(key, delegate, ancestor);
      }

      NavigableSet getSortedSetDelegate() {
         return (NavigableSet)super.getSortedSetDelegate();
      }

      @CheckForNull
      public Object lower(@ParametricNullness Object v) {
         return this.getSortedSetDelegate().lower(v);
      }

      @CheckForNull
      public Object floor(@ParametricNullness Object v) {
         return this.getSortedSetDelegate().floor(v);
      }

      @CheckForNull
      public Object ceiling(@ParametricNullness Object v) {
         return this.getSortedSetDelegate().ceiling(v);
      }

      @CheckForNull
      public Object higher(@ParametricNullness Object v) {
         return this.getSortedSetDelegate().higher(v);
      }

      @CheckForNull
      public Object pollFirst() {
         return Iterators.pollNext(this.iterator());
      }

      @CheckForNull
      public Object pollLast() {
         return Iterators.pollNext(this.descendingIterator());
      }

      private NavigableSet wrap(NavigableSet wrapped) {
         return AbstractMapBasedMultimap.this.new WrappedNavigableSet(this.key, wrapped, (WrappedCollection)(this.getAncestor() == null ? this : this.getAncestor()));
      }

      public NavigableSet descendingSet() {
         return this.wrap(this.getSortedSetDelegate().descendingSet());
      }

      public Iterator descendingIterator() {
         return new WrappedCollection.WrappedIterator(this.getSortedSetDelegate().descendingIterator());
      }

      public NavigableSet subSet(@ParametricNullness Object fromElement, boolean fromInclusive, @ParametricNullness Object toElement, boolean toInclusive) {
         return this.wrap(this.getSortedSetDelegate().subSet(fromElement, fromInclusive, toElement, toInclusive));
      }

      public NavigableSet headSet(@ParametricNullness Object toElement, boolean inclusive) {
         return this.wrap(this.getSortedSetDelegate().headSet(toElement, inclusive));
      }

      public NavigableSet tailSet(@ParametricNullness Object fromElement, boolean inclusive) {
         return this.wrap(this.getSortedSetDelegate().tailSet(fromElement, inclusive));
      }
   }

   class WrappedList extends WrappedCollection implements List {
      WrappedList(@ParametricNullness Object key, List delegate, @CheckForNull WrappedCollection ancestor) {
         super(key, delegate, ancestor);
      }

      List getListDelegate() {
         return (List)this.getDelegate();
      }

      public boolean addAll(int index, Collection c) {
         if (c.isEmpty()) {
            return false;
         } else {
            int oldSize = this.size();
            boolean changed = this.getListDelegate().addAll(index, c);
            if (changed) {
               int newSize = this.getDelegate().size();
               AbstractMapBasedMultimap.this.totalSize = newSize - oldSize;
               if (oldSize == 0) {
                  this.addToMap();
               }
            }

            return changed;
         }
      }

      @ParametricNullness
      public Object get(int index) {
         this.refreshIfEmpty();
         return this.getListDelegate().get(index);
      }

      @ParametricNullness
      public Object set(int index, @ParametricNullness Object element) {
         this.refreshIfEmpty();
         return this.getListDelegate().set(index, element);
      }

      public void add(int index, @ParametricNullness Object element) {
         this.refreshIfEmpty();
         boolean wasEmpty = this.getDelegate().isEmpty();
         this.getListDelegate().add(index, element);
         AbstractMapBasedMultimap.this.totalSize++;
         if (wasEmpty) {
            this.addToMap();
         }

      }

      @ParametricNullness
      public Object remove(int index) {
         this.refreshIfEmpty();
         V value = (V)this.getListDelegate().remove(index);
         AbstractMapBasedMultimap.this.totalSize--;
         this.removeIfEmpty();
         return value;
      }

      public int indexOf(@CheckForNull Object o) {
         this.refreshIfEmpty();
         return this.getListDelegate().indexOf(o);
      }

      public int lastIndexOf(@CheckForNull Object o) {
         this.refreshIfEmpty();
         return this.getListDelegate().lastIndexOf(o);
      }

      public ListIterator listIterator() {
         this.refreshIfEmpty();
         return new WrappedListIterator();
      }

      public ListIterator listIterator(int index) {
         this.refreshIfEmpty();
         return new WrappedListIterator(index);
      }

      public List subList(int fromIndex, int toIndex) {
         this.refreshIfEmpty();
         return AbstractMapBasedMultimap.this.wrapList(this.getKey(), this.getListDelegate().subList(fromIndex, toIndex), (WrappedCollection)(this.getAncestor() == null ? this : this.getAncestor()));
      }

      private class WrappedListIterator extends WrappedCollection.WrappedIterator implements ListIterator {
         WrappedListIterator() {
         }

         public WrappedListIterator(int index) {
            super(WrappedList.this.getListDelegate().listIterator(index));
         }

         private ListIterator getDelegateListIterator() {
            return (ListIterator)this.getDelegateIterator();
         }

         public boolean hasPrevious() {
            return this.getDelegateListIterator().hasPrevious();
         }

         @ParametricNullness
         public Object previous() {
            return this.getDelegateListIterator().previous();
         }

         public int nextIndex() {
            return this.getDelegateListIterator().nextIndex();
         }

         public int previousIndex() {
            return this.getDelegateListIterator().previousIndex();
         }

         public void set(@ParametricNullness Object value) {
            this.getDelegateListIterator().set(value);
         }

         public void add(@ParametricNullness Object value) {
            boolean wasEmpty = WrappedList.this.isEmpty();
            this.getDelegateListIterator().add(value);
            AbstractMapBasedMultimap.this.totalSize++;
            if (wasEmpty) {
               WrappedList.this.addToMap();
            }

         }
      }
   }

   private class RandomAccessWrappedList extends WrappedList implements RandomAccess {
      RandomAccessWrappedList(@ParametricNullness Object key, List delegate, @CheckForNull WrappedCollection ancestor) {
         super(key, delegate, ancestor);
      }
   }

   private class KeySet extends Maps.KeySet {
      KeySet(final Map subMap) {
         super(subMap);
      }

      public Iterator iterator() {
         final Iterator<Map.Entry<K, Collection<V>>> entryIterator = this.map().entrySet().iterator();
         return new Iterator() {
            @CheckForNull
            Map.Entry entry;

            public boolean hasNext() {
               return entryIterator.hasNext();
            }

            @ParametricNullness
            public Object next() {
               this.entry = (Map.Entry)entryIterator.next();
               return this.entry.getKey();
            }

            public void remove() {
               Preconditions.checkState(this.entry != null, "no calls to next() since the last call to remove()");
               Collection<V> collection = (Collection)this.entry.getValue();
               entryIterator.remove();
               AbstractMapBasedMultimap.this.totalSize = collection.size();
               collection.clear();
               this.entry = null;
            }
         };
      }

      public Spliterator spliterator() {
         return this.map().keySet().spliterator();
      }

      public boolean remove(@CheckForNull Object key) {
         int count = 0;
         Collection<V> collection = (Collection)this.map().remove(key);
         if (collection != null) {
            count = collection.size();
            collection.clear();
            AbstractMapBasedMultimap.this.totalSize = count;
         }

         return count > 0;
      }

      public void clear() {
         Iterators.clear(this.iterator());
      }

      public boolean containsAll(Collection c) {
         return this.map().keySet().containsAll(c);
      }

      public boolean equals(@CheckForNull Object object) {
         return this == object || this.map().keySet().equals(object);
      }

      public int hashCode() {
         return this.map().keySet().hashCode();
      }
   }

   private class SortedKeySet extends KeySet implements SortedSet {
      SortedKeySet(SortedMap subMap) {
         super(subMap);
      }

      SortedMap sortedMap() {
         return (SortedMap)super.map();
      }

      @CheckForNull
      public Comparator comparator() {
         return this.sortedMap().comparator();
      }

      @ParametricNullness
      public Object first() {
         return this.sortedMap().firstKey();
      }

      public SortedSet headSet(@ParametricNullness Object toElement) {
         return AbstractMapBasedMultimap.this.new SortedKeySet(this.sortedMap().headMap(toElement));
      }

      @ParametricNullness
      public Object last() {
         return this.sortedMap().lastKey();
      }

      public SortedSet subSet(@ParametricNullness Object fromElement, @ParametricNullness Object toElement) {
         return AbstractMapBasedMultimap.this.new SortedKeySet(this.sortedMap().subMap(fromElement, toElement));
      }

      public SortedSet tailSet(@ParametricNullness Object fromElement) {
         return AbstractMapBasedMultimap.this.new SortedKeySet(this.sortedMap().tailMap(fromElement));
      }
   }

   class NavigableKeySet extends SortedKeySet implements NavigableSet {
      NavigableKeySet(NavigableMap subMap) {
         super(subMap);
      }

      NavigableMap sortedMap() {
         return (NavigableMap)super.sortedMap();
      }

      @CheckForNull
      public Object lower(@ParametricNullness Object k) {
         return this.sortedMap().lowerKey(k);
      }

      @CheckForNull
      public Object floor(@ParametricNullness Object k) {
         return this.sortedMap().floorKey(k);
      }

      @CheckForNull
      public Object ceiling(@ParametricNullness Object k) {
         return this.sortedMap().ceilingKey(k);
      }

      @CheckForNull
      public Object higher(@ParametricNullness Object k) {
         return this.sortedMap().higherKey(k);
      }

      @CheckForNull
      public Object pollFirst() {
         return Iterators.pollNext(this.iterator());
      }

      @CheckForNull
      public Object pollLast() {
         return Iterators.pollNext(this.descendingIterator());
      }

      public NavigableSet descendingSet() {
         return AbstractMapBasedMultimap.this.new NavigableKeySet(this.sortedMap().descendingMap());
      }

      public Iterator descendingIterator() {
         return this.descendingSet().iterator();
      }

      public NavigableSet headSet(@ParametricNullness Object toElement) {
         return this.headSet(toElement, false);
      }

      public NavigableSet headSet(@ParametricNullness Object toElement, boolean inclusive) {
         return AbstractMapBasedMultimap.this.new NavigableKeySet(this.sortedMap().headMap(toElement, inclusive));
      }

      public NavigableSet subSet(@ParametricNullness Object fromElement, @ParametricNullness Object toElement) {
         return this.subSet(fromElement, true, toElement, false);
      }

      public NavigableSet subSet(@ParametricNullness Object fromElement, boolean fromInclusive, @ParametricNullness Object toElement, boolean toInclusive) {
         return AbstractMapBasedMultimap.this.new NavigableKeySet(this.sortedMap().subMap(fromElement, fromInclusive, toElement, toInclusive));
      }

      public NavigableSet tailSet(@ParametricNullness Object fromElement) {
         return this.tailSet(fromElement, true);
      }

      public NavigableSet tailSet(@ParametricNullness Object fromElement, boolean inclusive) {
         return AbstractMapBasedMultimap.this.new NavigableKeySet(this.sortedMap().tailMap(fromElement, inclusive));
      }
   }

   private abstract class Itr implements Iterator {
      final Iterator keyIterator;
      @CheckForNull
      Object key;
      @CheckForNull
      Collection collection;
      Iterator valueIterator;

      Itr() {
         this.keyIterator = AbstractMapBasedMultimap.this.map.entrySet().iterator();
         this.key = null;
         this.collection = null;
         this.valueIterator = Iterators.emptyModifiableIterator();
      }

      abstract Object output(@ParametricNullness Object key, @ParametricNullness Object value);

      public boolean hasNext() {
         return this.keyIterator.hasNext() || this.valueIterator.hasNext();
      }

      @ParametricNullness
      public Object next() {
         if (!this.valueIterator.hasNext()) {
            Map.Entry<K, Collection<V>> mapEntry = (Map.Entry)this.keyIterator.next();
            this.key = mapEntry.getKey();
            this.collection = (Collection)mapEntry.getValue();
            this.valueIterator = this.collection.iterator();
         }

         return this.output(NullnessCasts.uncheckedCastNullableTToT(this.key), this.valueIterator.next());
      }

      public void remove() {
         this.valueIterator.remove();
         if (((Collection)Objects.requireNonNull(this.collection)).isEmpty()) {
            this.keyIterator.remove();
         }

         AbstractMapBasedMultimap.this.totalSize--;
      }
   }

   private class AsMap extends Maps.ViewCachingAbstractMap {
      final transient Map submap;

      AsMap(Map submap) {
         this.submap = submap;
      }

      protected Set createEntrySet() {
         return new AsMapEntries();
      }

      public boolean containsKey(@CheckForNull Object key) {
         return Maps.safeContainsKey(this.submap, key);
      }

      @CheckForNull
      public Collection get(@CheckForNull Object key) {
         Collection<V> collection = (Collection)Maps.safeGet(this.submap, key);
         return collection == null ? null : AbstractMapBasedMultimap.this.wrapCollection(key, collection);
      }

      public Set keySet() {
         return AbstractMapBasedMultimap.this.keySet();
      }

      public int size() {
         return this.submap.size();
      }

      @CheckForNull
      public Collection remove(@CheckForNull Object key) {
         Collection<V> collection = (Collection)this.submap.remove(key);
         if (collection == null) {
            return null;
         } else {
            Collection<V> output = AbstractMapBasedMultimap.this.createCollection();
            output.addAll(collection);
            AbstractMapBasedMultimap.this.totalSize = collection.size();
            collection.clear();
            return output;
         }
      }

      public boolean equals(@CheckForNull Object object) {
         return this == object || this.submap.equals(object);
      }

      public int hashCode() {
         return this.submap.hashCode();
      }

      public String toString() {
         return this.submap.toString();
      }

      public void clear() {
         if (this.submap == AbstractMapBasedMultimap.this.map) {
            AbstractMapBasedMultimap.this.clear();
         } else {
            Iterators.clear(new AsMapIterator());
         }

      }

      Map.Entry wrapEntry(Map.Entry entry) {
         K key = (K)entry.getKey();
         return Maps.immutableEntry(key, AbstractMapBasedMultimap.this.wrapCollection(key, (Collection)entry.getValue()));
      }

      class AsMapEntries extends Maps.EntrySet {
         Map map() {
            return AsMap.this;
         }

         public Iterator iterator() {
            return AsMap.this.new AsMapIterator();
         }

         public Spliterator spliterator() {
            return CollectSpliterators.map(AsMap.this.submap.entrySet().spliterator(), AsMap.this::wrapEntry);
         }

         public boolean contains(@CheckForNull Object o) {
            return Collections2.safeContains(AsMap.this.submap.entrySet(), o);
         }

         public boolean remove(@CheckForNull Object o) {
            if (!this.contains(o)) {
               return false;
            } else {
               Map.Entry<?, ?> entry = (Map.Entry)Objects.requireNonNull((Map.Entry)o);
               AbstractMapBasedMultimap.this.removeValuesForKey(entry.getKey());
               return true;
            }
         }
      }

      class AsMapIterator implements Iterator {
         final Iterator delegateIterator;
         @CheckForNull
         Collection collection;

         AsMapIterator() {
            this.delegateIterator = AsMap.this.submap.entrySet().iterator();
         }

         public boolean hasNext() {
            return this.delegateIterator.hasNext();
         }

         public Map.Entry next() {
            Map.Entry<K, Collection<V>> entry = (Map.Entry)this.delegateIterator.next();
            this.collection = (Collection)entry.getValue();
            return AsMap.this.wrapEntry(entry);
         }

         public void remove() {
            Preconditions.checkState(this.collection != null, "no calls to next() since the last call to remove()");
            this.delegateIterator.remove();
            AbstractMapBasedMultimap.this.totalSize = this.collection.size();
            this.collection.clear();
            this.collection = null;
         }
      }
   }

   private class SortedAsMap extends AsMap implements SortedMap {
      @CheckForNull
      SortedSet sortedKeySet;

      SortedAsMap(SortedMap submap) {
         super(submap);
      }

      SortedMap sortedMap() {
         return (SortedMap)this.submap;
      }

      @CheckForNull
      public Comparator comparator() {
         return this.sortedMap().comparator();
      }

      @ParametricNullness
      public Object firstKey() {
         return this.sortedMap().firstKey();
      }

      @ParametricNullness
      public Object lastKey() {
         return this.sortedMap().lastKey();
      }

      public SortedMap headMap(@ParametricNullness Object toKey) {
         return AbstractMapBasedMultimap.this.new SortedAsMap(this.sortedMap().headMap(toKey));
      }

      public SortedMap subMap(@ParametricNullness Object fromKey, @ParametricNullness Object toKey) {
         return AbstractMapBasedMultimap.this.new SortedAsMap(this.sortedMap().subMap(fromKey, toKey));
      }

      public SortedMap tailMap(@ParametricNullness Object fromKey) {
         return AbstractMapBasedMultimap.this.new SortedAsMap(this.sortedMap().tailMap(fromKey));
      }

      public SortedSet keySet() {
         SortedSet<K> result = this.sortedKeySet;
         return result == null ? (this.sortedKeySet = this.createKeySet()) : result;
      }

      SortedSet createKeySet() {
         return AbstractMapBasedMultimap.this.new SortedKeySet(this.sortedMap());
      }
   }

   class NavigableAsMap extends SortedAsMap implements NavigableMap {
      NavigableAsMap(NavigableMap submap) {
         super(submap);
      }

      NavigableMap sortedMap() {
         return (NavigableMap)super.sortedMap();
      }

      @CheckForNull
      public Map.Entry lowerEntry(@ParametricNullness Object key) {
         Map.Entry<K, Collection<V>> entry = this.sortedMap().lowerEntry(key);
         return entry == null ? null : this.wrapEntry(entry);
      }

      @CheckForNull
      public Object lowerKey(@ParametricNullness Object key) {
         return this.sortedMap().lowerKey(key);
      }

      @CheckForNull
      public Map.Entry floorEntry(@ParametricNullness Object key) {
         Map.Entry<K, Collection<V>> entry = this.sortedMap().floorEntry(key);
         return entry == null ? null : this.wrapEntry(entry);
      }

      @CheckForNull
      public Object floorKey(@ParametricNullness Object key) {
         return this.sortedMap().floorKey(key);
      }

      @CheckForNull
      public Map.Entry ceilingEntry(@ParametricNullness Object key) {
         Map.Entry<K, Collection<V>> entry = this.sortedMap().ceilingEntry(key);
         return entry == null ? null : this.wrapEntry(entry);
      }

      @CheckForNull
      public Object ceilingKey(@ParametricNullness Object key) {
         return this.sortedMap().ceilingKey(key);
      }

      @CheckForNull
      public Map.Entry higherEntry(@ParametricNullness Object key) {
         Map.Entry<K, Collection<V>> entry = this.sortedMap().higherEntry(key);
         return entry == null ? null : this.wrapEntry(entry);
      }

      @CheckForNull
      public Object higherKey(@ParametricNullness Object key) {
         return this.sortedMap().higherKey(key);
      }

      @CheckForNull
      public Map.Entry firstEntry() {
         Map.Entry<K, Collection<V>> entry = this.sortedMap().firstEntry();
         return entry == null ? null : this.wrapEntry(entry);
      }

      @CheckForNull
      public Map.Entry lastEntry() {
         Map.Entry<K, Collection<V>> entry = this.sortedMap().lastEntry();
         return entry == null ? null : this.wrapEntry(entry);
      }

      @CheckForNull
      public Map.Entry pollFirstEntry() {
         return this.pollAsMapEntry(this.entrySet().iterator());
      }

      @CheckForNull
      public Map.Entry pollLastEntry() {
         return this.pollAsMapEntry(this.descendingMap().entrySet().iterator());
      }

      @CheckForNull
      Map.Entry pollAsMapEntry(Iterator entryIterator) {
         if (!entryIterator.hasNext()) {
            return null;
         } else {
            Map.Entry<K, Collection<V>> entry = (Map.Entry)entryIterator.next();
            Collection<V> output = AbstractMapBasedMultimap.this.createCollection();
            output.addAll((Collection)entry.getValue());
            entryIterator.remove();
            return Maps.immutableEntry(entry.getKey(), AbstractMapBasedMultimap.this.unmodifiableCollectionSubclass(output));
         }
      }

      public NavigableMap descendingMap() {
         return AbstractMapBasedMultimap.this.new NavigableAsMap(this.sortedMap().descendingMap());
      }

      public NavigableSet keySet() {
         return (NavigableSet)super.keySet();
      }

      NavigableSet createKeySet() {
         return AbstractMapBasedMultimap.this.new NavigableKeySet(this.sortedMap());
      }

      public NavigableSet navigableKeySet() {
         return this.keySet();
      }

      public NavigableSet descendingKeySet() {
         return this.descendingMap().navigableKeySet();
      }

      public NavigableMap subMap(@ParametricNullness Object fromKey, @ParametricNullness Object toKey) {
         return this.subMap(fromKey, true, toKey, false);
      }

      public NavigableMap subMap(@ParametricNullness Object fromKey, boolean fromInclusive, @ParametricNullness Object toKey, boolean toInclusive) {
         return AbstractMapBasedMultimap.this.new NavigableAsMap(this.sortedMap().subMap(fromKey, fromInclusive, toKey, toInclusive));
      }

      public NavigableMap headMap(@ParametricNullness Object toKey) {
         return this.headMap(toKey, false);
      }

      public NavigableMap headMap(@ParametricNullness Object toKey, boolean inclusive) {
         return AbstractMapBasedMultimap.this.new NavigableAsMap(this.sortedMap().headMap(toKey, inclusive));
      }

      public NavigableMap tailMap(@ParametricNullness Object fromKey) {
         return this.tailMap(fromKey, true);
      }

      public NavigableMap tailMap(@ParametricNullness Object fromKey, boolean inclusive) {
         return AbstractMapBasedMultimap.this.new NavigableAsMap(this.sortedMap().tailMap(fromKey, inclusive));
      }
   }
}
