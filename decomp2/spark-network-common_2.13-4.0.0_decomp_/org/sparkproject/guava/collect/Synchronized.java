package org.sparkproject.guava.collect;

import com.google.j2objc.annotations.RetainedWith;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtCompatible(
   emulated = true
)
final class Synchronized {
   private Synchronized() {
   }

   private static Collection collection(Collection collection, @CheckForNull Object mutex) {
      return new SynchronizedCollection(collection, mutex);
   }

   @VisibleForTesting
   static Set set(Set set, @CheckForNull Object mutex) {
      return new SynchronizedSet(set, mutex);
   }

   private static SortedSet sortedSet(SortedSet set, @CheckForNull Object mutex) {
      return new SynchronizedSortedSet(set, mutex);
   }

   private static List list(List list, @CheckForNull Object mutex) {
      return (List)(list instanceof RandomAccess ? new SynchronizedRandomAccessList(list, mutex) : new SynchronizedList(list, mutex));
   }

   static Multiset multiset(Multiset multiset, @CheckForNull Object mutex) {
      return (Multiset)(!(multiset instanceof SynchronizedMultiset) && !(multiset instanceof ImmutableMultiset) ? new SynchronizedMultiset(multiset, mutex) : multiset);
   }

   static Multimap multimap(Multimap multimap, @CheckForNull Object mutex) {
      return (Multimap)(!(multimap instanceof SynchronizedMultimap) && !(multimap instanceof BaseImmutableMultimap) ? new SynchronizedMultimap(multimap, mutex) : multimap);
   }

   static ListMultimap listMultimap(ListMultimap multimap, @CheckForNull Object mutex) {
      return (ListMultimap)(!(multimap instanceof SynchronizedListMultimap) && !(multimap instanceof BaseImmutableMultimap) ? new SynchronizedListMultimap(multimap, mutex) : multimap);
   }

   static SetMultimap setMultimap(SetMultimap multimap, @CheckForNull Object mutex) {
      return (SetMultimap)(!(multimap instanceof SynchronizedSetMultimap) && !(multimap instanceof BaseImmutableMultimap) ? new SynchronizedSetMultimap(multimap, mutex) : multimap);
   }

   static SortedSetMultimap sortedSetMultimap(SortedSetMultimap multimap, @CheckForNull Object mutex) {
      return (SortedSetMultimap)(multimap instanceof SynchronizedSortedSetMultimap ? multimap : new SynchronizedSortedSetMultimap(multimap, mutex));
   }

   private static Collection typePreservingCollection(Collection collection, @CheckForNull Object mutex) {
      if (collection instanceof SortedSet) {
         return sortedSet((SortedSet)collection, mutex);
      } else if (collection instanceof Set) {
         return set((Set)collection, mutex);
      } else {
         return (Collection)(collection instanceof List ? list((List)collection, mutex) : collection(collection, mutex));
      }
   }

   private static Set typePreservingSet(Set set, @CheckForNull Object mutex) {
      return (Set)(set instanceof SortedSet ? sortedSet((SortedSet)set, mutex) : set(set, mutex));
   }

   @VisibleForTesting
   static Map map(Map map, @CheckForNull Object mutex) {
      return new SynchronizedMap(map, mutex);
   }

   static SortedMap sortedMap(SortedMap sortedMap, @CheckForNull Object mutex) {
      return new SynchronizedSortedMap(sortedMap, mutex);
   }

   static BiMap biMap(BiMap bimap, @CheckForNull Object mutex) {
      return (BiMap)(!(bimap instanceof SynchronizedBiMap) && !(bimap instanceof ImmutableBiMap) ? new SynchronizedBiMap(bimap, mutex, (BiMap)null) : bimap);
   }

   @GwtIncompatible
   static NavigableSet navigableSet(NavigableSet navigableSet, @CheckForNull Object mutex) {
      return new SynchronizedNavigableSet(navigableSet, mutex);
   }

   @GwtIncompatible
   static NavigableSet navigableSet(NavigableSet navigableSet) {
      return navigableSet(navigableSet, (Object)null);
   }

   @GwtIncompatible
   static NavigableMap navigableMap(NavigableMap navigableMap) {
      return navigableMap(navigableMap, (Object)null);
   }

   @GwtIncompatible
   static NavigableMap navigableMap(NavigableMap navigableMap, @CheckForNull Object mutex) {
      return new SynchronizedNavigableMap(navigableMap, mutex);
   }

   @CheckForNull
   @GwtIncompatible
   private static Map.Entry nullableSynchronizedEntry(@CheckForNull Map.Entry entry, @CheckForNull Object mutex) {
      return entry == null ? null : new SynchronizedEntry(entry, mutex);
   }

   static Queue queue(Queue queue, @CheckForNull Object mutex) {
      return (Queue)(queue instanceof SynchronizedQueue ? queue : new SynchronizedQueue(queue, mutex));
   }

   static Deque deque(Deque deque, @CheckForNull Object mutex) {
      return new SynchronizedDeque(deque, mutex);
   }

   static Table table(Table table, @CheckForNull Object mutex) {
      return new SynchronizedTable(table, mutex);
   }

   static class SynchronizedObject implements Serializable {
      final Object delegate;
      final Object mutex;
      @GwtIncompatible
      @J2ktIncompatible
      private static final long serialVersionUID = 0L;

      SynchronizedObject(Object delegate, @CheckForNull Object mutex) {
         this.delegate = Preconditions.checkNotNull(delegate);
         this.mutex = mutex == null ? this : mutex;
      }

      Object delegate() {
         return this.delegate;
      }

      public String toString() {
         synchronized(this.mutex) {
            return this.delegate.toString();
         }
      }

      @GwtIncompatible
      @J2ktIncompatible
      private void writeObject(ObjectOutputStream stream) throws IOException {
         synchronized(this.mutex) {
            stream.defaultWriteObject();
         }
      }
   }

   @VisibleForTesting
   static class SynchronizedCollection extends SynchronizedObject implements Collection {
      private static final long serialVersionUID = 0L;

      private SynchronizedCollection(Collection delegate, @CheckForNull Object mutex) {
         super(delegate, mutex);
      }

      Collection delegate() {
         return (Collection)super.delegate();
      }

      public boolean add(Object e) {
         synchronized(this.mutex) {
            return this.delegate().add(e);
         }
      }

      public boolean addAll(Collection c) {
         synchronized(this.mutex) {
            return this.delegate().addAll(c);
         }
      }

      public void clear() {
         synchronized(this.mutex) {
            this.delegate().clear();
         }
      }

      public boolean contains(@CheckForNull Object o) {
         synchronized(this.mutex) {
            return this.delegate().contains(o);
         }
      }

      public boolean containsAll(Collection c) {
         synchronized(this.mutex) {
            return this.delegate().containsAll(c);
         }
      }

      public boolean isEmpty() {
         synchronized(this.mutex) {
            return this.delegate().isEmpty();
         }
      }

      public Iterator iterator() {
         return this.delegate().iterator();
      }

      public Spliterator spliterator() {
         synchronized(this.mutex) {
            return this.delegate().spliterator();
         }
      }

      public Stream stream() {
         synchronized(this.mutex) {
            return this.delegate().stream();
         }
      }

      public Stream parallelStream() {
         synchronized(this.mutex) {
            return this.delegate().parallelStream();
         }
      }

      public void forEach(Consumer action) {
         synchronized(this.mutex) {
            this.delegate().forEach(action);
         }
      }

      public boolean remove(@CheckForNull Object o) {
         synchronized(this.mutex) {
            return this.delegate().remove(o);
         }
      }

      public boolean removeAll(Collection c) {
         synchronized(this.mutex) {
            return this.delegate().removeAll(c);
         }
      }

      public boolean retainAll(Collection c) {
         synchronized(this.mutex) {
            return this.delegate().retainAll(c);
         }
      }

      public boolean removeIf(Predicate filter) {
         synchronized(this.mutex) {
            return this.delegate().removeIf(filter);
         }
      }

      public int size() {
         synchronized(this.mutex) {
            return this.delegate().size();
         }
      }

      public @Nullable Object[] toArray() {
         synchronized(this.mutex) {
            return this.delegate().toArray();
         }
      }

      public Object[] toArray(Object[] a) {
         synchronized(this.mutex) {
            return this.delegate().toArray(a);
         }
      }
   }

   static class SynchronizedSet extends SynchronizedCollection implements Set {
      private static final long serialVersionUID = 0L;

      SynchronizedSet(Set delegate, @CheckForNull Object mutex) {
         super(delegate, mutex, null);
      }

      Set delegate() {
         return (Set)super.delegate();
      }

      public boolean equals(@CheckForNull Object o) {
         if (o == this) {
            return true;
         } else {
            synchronized(this.mutex) {
               return this.delegate().equals(o);
            }
         }
      }

      public int hashCode() {
         synchronized(this.mutex) {
            return this.delegate().hashCode();
         }
      }
   }

   static class SynchronizedSortedSet extends SynchronizedSet implements SortedSet {
      private static final long serialVersionUID = 0L;

      SynchronizedSortedSet(SortedSet delegate, @CheckForNull Object mutex) {
         super(delegate, mutex);
      }

      SortedSet delegate() {
         return (SortedSet)super.delegate();
      }

      @CheckForNull
      public Comparator comparator() {
         synchronized(this.mutex) {
            return this.delegate().comparator();
         }
      }

      public SortedSet subSet(Object fromElement, Object toElement) {
         synchronized(this.mutex) {
            return Synchronized.sortedSet(this.delegate().subSet(fromElement, toElement), this.mutex);
         }
      }

      public SortedSet headSet(Object toElement) {
         synchronized(this.mutex) {
            return Synchronized.sortedSet(this.delegate().headSet(toElement), this.mutex);
         }
      }

      public SortedSet tailSet(Object fromElement) {
         synchronized(this.mutex) {
            return Synchronized.sortedSet(this.delegate().tailSet(fromElement), this.mutex);
         }
      }

      public Object first() {
         synchronized(this.mutex) {
            return this.delegate().first();
         }
      }

      public Object last() {
         synchronized(this.mutex) {
            return this.delegate().last();
         }
      }
   }

   static class SynchronizedList extends SynchronizedCollection implements List {
      private static final long serialVersionUID = 0L;

      SynchronizedList(List delegate, @CheckForNull Object mutex) {
         super(delegate, mutex, null);
      }

      List delegate() {
         return (List)super.delegate();
      }

      public void add(int index, Object element) {
         synchronized(this.mutex) {
            this.delegate().add(index, element);
         }
      }

      public boolean addAll(int index, Collection c) {
         synchronized(this.mutex) {
            return this.delegate().addAll(index, c);
         }
      }

      public Object get(int index) {
         synchronized(this.mutex) {
            return this.delegate().get(index);
         }
      }

      public int indexOf(@CheckForNull Object o) {
         synchronized(this.mutex) {
            return this.delegate().indexOf(o);
         }
      }

      public int lastIndexOf(@CheckForNull Object o) {
         synchronized(this.mutex) {
            return this.delegate().lastIndexOf(o);
         }
      }

      public ListIterator listIterator() {
         return this.delegate().listIterator();
      }

      public ListIterator listIterator(int index) {
         return this.delegate().listIterator(index);
      }

      public Object remove(int index) {
         synchronized(this.mutex) {
            return this.delegate().remove(index);
         }
      }

      public Object set(int index, Object element) {
         synchronized(this.mutex) {
            return this.delegate().set(index, element);
         }
      }

      public void replaceAll(UnaryOperator operator) {
         synchronized(this.mutex) {
            this.delegate().replaceAll(operator);
         }
      }

      public void sort(@Nullable Comparator c) {
         synchronized(this.mutex) {
            this.delegate().sort(c);
         }
      }

      public List subList(int fromIndex, int toIndex) {
         synchronized(this.mutex) {
            return Synchronized.list(this.delegate().subList(fromIndex, toIndex), this.mutex);
         }
      }

      public boolean equals(@CheckForNull Object o) {
         if (o == this) {
            return true;
         } else {
            synchronized(this.mutex) {
               return this.delegate().equals(o);
            }
         }
      }

      public int hashCode() {
         synchronized(this.mutex) {
            return this.delegate().hashCode();
         }
      }
   }

   static final class SynchronizedRandomAccessList extends SynchronizedList implements RandomAccess {
      private static final long serialVersionUID = 0L;

      SynchronizedRandomAccessList(List list, @CheckForNull Object mutex) {
         super(list, mutex);
      }
   }

   static final class SynchronizedMultiset extends SynchronizedCollection implements Multiset {
      @CheckForNull
      transient Set elementSet;
      @CheckForNull
      transient Set entrySet;
      private static final long serialVersionUID = 0L;

      SynchronizedMultiset(Multiset delegate, @CheckForNull Object mutex) {
         super(delegate, mutex, null);
      }

      Multiset delegate() {
         return (Multiset)super.delegate();
      }

      public int count(@CheckForNull Object o) {
         synchronized(this.mutex) {
            return this.delegate().count(o);
         }
      }

      public int add(@ParametricNullness Object e, int n) {
         synchronized(this.mutex) {
            return this.delegate().add(e, n);
         }
      }

      public int remove(@CheckForNull Object o, int n) {
         synchronized(this.mutex) {
            return this.delegate().remove(o, n);
         }
      }

      public int setCount(@ParametricNullness Object element, int count) {
         synchronized(this.mutex) {
            return this.delegate().setCount(element, count);
         }
      }

      public boolean setCount(@ParametricNullness Object element, int oldCount, int newCount) {
         synchronized(this.mutex) {
            return this.delegate().setCount(element, oldCount, newCount);
         }
      }

      public Set elementSet() {
         synchronized(this.mutex) {
            if (this.elementSet == null) {
               this.elementSet = Synchronized.typePreservingSet(this.delegate().elementSet(), this.mutex);
            }

            return this.elementSet;
         }
      }

      public Set entrySet() {
         synchronized(this.mutex) {
            if (this.entrySet == null) {
               this.entrySet = Synchronized.typePreservingSet(this.delegate().entrySet(), this.mutex);
            }

            return this.entrySet;
         }
      }

      public boolean equals(@CheckForNull Object o) {
         if (o == this) {
            return true;
         } else {
            synchronized(this.mutex) {
               return this.delegate().equals(o);
            }
         }
      }

      public int hashCode() {
         synchronized(this.mutex) {
            return this.delegate().hashCode();
         }
      }
   }

   static class SynchronizedMultimap extends SynchronizedObject implements Multimap {
      @CheckForNull
      transient Set keySet;
      @CheckForNull
      transient Collection valuesCollection;
      @CheckForNull
      transient Collection entries;
      @CheckForNull
      transient Map asMap;
      @CheckForNull
      transient Multiset keys;
      private static final long serialVersionUID = 0L;

      Multimap delegate() {
         return (Multimap)super.delegate();
      }

      SynchronizedMultimap(Multimap delegate, @CheckForNull Object mutex) {
         super(delegate, mutex);
      }

      public int size() {
         synchronized(this.mutex) {
            return this.delegate().size();
         }
      }

      public boolean isEmpty() {
         synchronized(this.mutex) {
            return this.delegate().isEmpty();
         }
      }

      public boolean containsKey(@CheckForNull Object key) {
         synchronized(this.mutex) {
            return this.delegate().containsKey(key);
         }
      }

      public boolean containsValue(@CheckForNull Object value) {
         synchronized(this.mutex) {
            return this.delegate().containsValue(value);
         }
      }

      public boolean containsEntry(@CheckForNull Object key, @CheckForNull Object value) {
         synchronized(this.mutex) {
            return this.delegate().containsEntry(key, value);
         }
      }

      public Collection get(@ParametricNullness Object key) {
         synchronized(this.mutex) {
            return Synchronized.typePreservingCollection(this.delegate().get(key), this.mutex);
         }
      }

      public boolean put(@ParametricNullness Object key, @ParametricNullness Object value) {
         synchronized(this.mutex) {
            return this.delegate().put(key, value);
         }
      }

      public boolean putAll(@ParametricNullness Object key, Iterable values) {
         synchronized(this.mutex) {
            return this.delegate().putAll(key, values);
         }
      }

      public boolean putAll(Multimap multimap) {
         synchronized(this.mutex) {
            return this.delegate().putAll(multimap);
         }
      }

      public Collection replaceValues(@ParametricNullness Object key, Iterable values) {
         synchronized(this.mutex) {
            return this.delegate().replaceValues(key, values);
         }
      }

      public boolean remove(@CheckForNull Object key, @CheckForNull Object value) {
         synchronized(this.mutex) {
            return this.delegate().remove(key, value);
         }
      }

      public Collection removeAll(@CheckForNull Object key) {
         synchronized(this.mutex) {
            return this.delegate().removeAll(key);
         }
      }

      public void clear() {
         synchronized(this.mutex) {
            this.delegate().clear();
         }
      }

      public Set keySet() {
         synchronized(this.mutex) {
            if (this.keySet == null) {
               this.keySet = Synchronized.typePreservingSet(this.delegate().keySet(), this.mutex);
            }

            return this.keySet;
         }
      }

      public Collection values() {
         synchronized(this.mutex) {
            if (this.valuesCollection == null) {
               this.valuesCollection = Synchronized.collection(this.delegate().values(), this.mutex);
            }

            return this.valuesCollection;
         }
      }

      public Collection entries() {
         synchronized(this.mutex) {
            if (this.entries == null) {
               this.entries = Synchronized.typePreservingCollection(this.delegate().entries(), this.mutex);
            }

            return this.entries;
         }
      }

      public void forEach(BiConsumer action) {
         synchronized(this.mutex) {
            this.delegate().forEach(action);
         }
      }

      public Map asMap() {
         synchronized(this.mutex) {
            if (this.asMap == null) {
               this.asMap = new SynchronizedAsMap(this.delegate().asMap(), this.mutex);
            }

            return this.asMap;
         }
      }

      public Multiset keys() {
         synchronized(this.mutex) {
            if (this.keys == null) {
               this.keys = Synchronized.multiset(this.delegate().keys(), this.mutex);
            }

            return this.keys;
         }
      }

      public boolean equals(@CheckForNull Object o) {
         if (o == this) {
            return true;
         } else {
            synchronized(this.mutex) {
               return this.delegate().equals(o);
            }
         }
      }

      public int hashCode() {
         synchronized(this.mutex) {
            return this.delegate().hashCode();
         }
      }
   }

   static final class SynchronizedListMultimap extends SynchronizedMultimap implements ListMultimap {
      private static final long serialVersionUID = 0L;

      SynchronizedListMultimap(ListMultimap delegate, @CheckForNull Object mutex) {
         super(delegate, mutex);
      }

      ListMultimap delegate() {
         return (ListMultimap)super.delegate();
      }

      public List get(Object key) {
         synchronized(this.mutex) {
            return Synchronized.list(this.delegate().get(key), this.mutex);
         }
      }

      public List removeAll(@CheckForNull Object key) {
         synchronized(this.mutex) {
            return this.delegate().removeAll(key);
         }
      }

      public List replaceValues(Object key, Iterable values) {
         synchronized(this.mutex) {
            return this.delegate().replaceValues(key, values);
         }
      }
   }

   static class SynchronizedSetMultimap extends SynchronizedMultimap implements SetMultimap {
      @CheckForNull
      transient Set entrySet;
      private static final long serialVersionUID = 0L;

      SynchronizedSetMultimap(SetMultimap delegate, @CheckForNull Object mutex) {
         super(delegate, mutex);
      }

      SetMultimap delegate() {
         return (SetMultimap)super.delegate();
      }

      public Set get(Object key) {
         synchronized(this.mutex) {
            return Synchronized.set(this.delegate().get(key), this.mutex);
         }
      }

      public Set removeAll(@CheckForNull Object key) {
         synchronized(this.mutex) {
            return this.delegate().removeAll(key);
         }
      }

      public Set replaceValues(Object key, Iterable values) {
         synchronized(this.mutex) {
            return this.delegate().replaceValues(key, values);
         }
      }

      public Set entries() {
         synchronized(this.mutex) {
            if (this.entrySet == null) {
               this.entrySet = Synchronized.set(this.delegate().entries(), this.mutex);
            }

            return this.entrySet;
         }
      }
   }

   static final class SynchronizedSortedSetMultimap extends SynchronizedSetMultimap implements SortedSetMultimap {
      private static final long serialVersionUID = 0L;

      SynchronizedSortedSetMultimap(SortedSetMultimap delegate, @CheckForNull Object mutex) {
         super(delegate, mutex);
      }

      SortedSetMultimap delegate() {
         return (SortedSetMultimap)super.delegate();
      }

      public SortedSet get(Object key) {
         synchronized(this.mutex) {
            return Synchronized.sortedSet(this.delegate().get(key), this.mutex);
         }
      }

      public SortedSet removeAll(@CheckForNull Object key) {
         synchronized(this.mutex) {
            return this.delegate().removeAll(key);
         }
      }

      public SortedSet replaceValues(Object key, Iterable values) {
         synchronized(this.mutex) {
            return this.delegate().replaceValues(key, values);
         }
      }

      @CheckForNull
      public Comparator valueComparator() {
         synchronized(this.mutex) {
            return this.delegate().valueComparator();
         }
      }
   }

   static final class SynchronizedAsMapEntries extends SynchronizedSet {
      private static final long serialVersionUID = 0L;

      SynchronizedAsMapEntries(Set delegate, @CheckForNull Object mutex) {
         super(delegate, mutex);
      }

      public Iterator iterator() {
         return new TransformedIterator(super.iterator()) {
            Map.Entry transform(final Map.Entry entry) {
               return new ForwardingMapEntry() {
                  protected Map.Entry delegate() {
                     return entry;
                  }

                  public Collection getValue() {
                     return Synchronized.typePreservingCollection((Collection)entry.getValue(), SynchronizedAsMapEntries.this.mutex);
                  }
               };
            }
         };
      }

      public @Nullable Object[] toArray() {
         synchronized(this.mutex) {
            return ObjectArrays.toArrayImpl(this.delegate());
         }
      }

      public Object[] toArray(Object[] array) {
         synchronized(this.mutex) {
            return ObjectArrays.toArrayImpl(this.delegate(), array);
         }
      }

      public boolean contains(@CheckForNull Object o) {
         synchronized(this.mutex) {
            return Maps.containsEntryImpl(this.delegate(), o);
         }
      }

      public boolean containsAll(Collection c) {
         synchronized(this.mutex) {
            return Collections2.containsAllImpl(this.delegate(), c);
         }
      }

      public boolean equals(@CheckForNull Object o) {
         if (o == this) {
            return true;
         } else {
            synchronized(this.mutex) {
               return Sets.equalsImpl(this.delegate(), o);
            }
         }
      }

      public boolean remove(@CheckForNull Object o) {
         synchronized(this.mutex) {
            return Maps.removeEntryImpl(this.delegate(), o);
         }
      }

      public boolean removeAll(Collection c) {
         synchronized(this.mutex) {
            return Iterators.removeAll(this.delegate().iterator(), c);
         }
      }

      public boolean retainAll(Collection c) {
         synchronized(this.mutex) {
            return Iterators.retainAll(this.delegate().iterator(), c);
         }
      }
   }

   static class SynchronizedMap extends SynchronizedObject implements Map {
      @CheckForNull
      transient Set keySet;
      @CheckForNull
      transient Collection values;
      @CheckForNull
      transient Set entrySet;
      private static final long serialVersionUID = 0L;

      SynchronizedMap(Map delegate, @CheckForNull Object mutex) {
         super(delegate, mutex);
      }

      Map delegate() {
         return (Map)super.delegate();
      }

      public void clear() {
         synchronized(this.mutex) {
            this.delegate().clear();
         }
      }

      public boolean containsKey(@CheckForNull Object key) {
         synchronized(this.mutex) {
            return this.delegate().containsKey(key);
         }
      }

      public boolean containsValue(@CheckForNull Object value) {
         synchronized(this.mutex) {
            return this.delegate().containsValue(value);
         }
      }

      public Set entrySet() {
         synchronized(this.mutex) {
            if (this.entrySet == null) {
               this.entrySet = Synchronized.set(this.delegate().entrySet(), this.mutex);
            }

            return this.entrySet;
         }
      }

      public void forEach(BiConsumer action) {
         synchronized(this.mutex) {
            this.delegate().forEach(action);
         }
      }

      @CheckForNull
      public Object get(@CheckForNull Object key) {
         synchronized(this.mutex) {
            return this.delegate().get(key);
         }
      }

      @CheckForNull
      public Object getOrDefault(@CheckForNull Object key, @CheckForNull Object defaultValue) {
         synchronized(this.mutex) {
            return this.delegate().getOrDefault(key, defaultValue);
         }
      }

      public boolean isEmpty() {
         synchronized(this.mutex) {
            return this.delegate().isEmpty();
         }
      }

      public Set keySet() {
         synchronized(this.mutex) {
            if (this.keySet == null) {
               this.keySet = Synchronized.set(this.delegate().keySet(), this.mutex);
            }

            return this.keySet;
         }
      }

      @CheckForNull
      public Object put(Object key, Object value) {
         synchronized(this.mutex) {
            return this.delegate().put(key, value);
         }
      }

      @CheckForNull
      public Object putIfAbsent(Object key, Object value) {
         synchronized(this.mutex) {
            return this.delegate().putIfAbsent(key, value);
         }
      }

      public boolean replace(Object key, Object oldValue, Object newValue) {
         synchronized(this.mutex) {
            return this.delegate().replace(key, oldValue, newValue);
         }
      }

      @CheckForNull
      public Object replace(Object key, Object value) {
         synchronized(this.mutex) {
            return this.delegate().replace(key, value);
         }
      }

      public Object computeIfAbsent(Object key, Function mappingFunction) {
         synchronized(this.mutex) {
            return this.delegate().computeIfAbsent(key, mappingFunction);
         }
      }

      @CheckForNull
      public Object computeIfPresent(Object key, BiFunction remappingFunction) {
         synchronized(this.mutex) {
            return this.delegate().computeIfPresent(key, remappingFunction);
         }
      }

      @CheckForNull
      public Object compute(Object key, BiFunction remappingFunction) {
         synchronized(this.mutex) {
            return this.delegate().compute(key, remappingFunction);
         }
      }

      @CheckForNull
      public Object merge(Object key, @NonNull Object value, BiFunction remappingFunction) {
         synchronized(this.mutex) {
            return this.delegate().merge(key, value, remappingFunction);
         }
      }

      public void putAll(Map map) {
         synchronized(this.mutex) {
            this.delegate().putAll(map);
         }
      }

      public void replaceAll(BiFunction function) {
         synchronized(this.mutex) {
            this.delegate().replaceAll(function);
         }
      }

      @CheckForNull
      public Object remove(@CheckForNull Object key) {
         synchronized(this.mutex) {
            return this.delegate().remove(key);
         }
      }

      public boolean remove(@CheckForNull Object key, @CheckForNull Object value) {
         synchronized(this.mutex) {
            return this.delegate().remove(key, value);
         }
      }

      public int size() {
         synchronized(this.mutex) {
            return this.delegate().size();
         }
      }

      public Collection values() {
         synchronized(this.mutex) {
            if (this.values == null) {
               this.values = Synchronized.collection(this.delegate().values(), this.mutex);
            }

            return this.values;
         }
      }

      public boolean equals(@CheckForNull Object o) {
         if (o == this) {
            return true;
         } else {
            synchronized(this.mutex) {
               return this.delegate().equals(o);
            }
         }
      }

      public int hashCode() {
         synchronized(this.mutex) {
            return this.delegate().hashCode();
         }
      }
   }

   static class SynchronizedSortedMap extends SynchronizedMap implements SortedMap {
      private static final long serialVersionUID = 0L;

      SynchronizedSortedMap(SortedMap delegate, @CheckForNull Object mutex) {
         super(delegate, mutex);
      }

      SortedMap delegate() {
         return (SortedMap)super.delegate();
      }

      @CheckForNull
      public Comparator comparator() {
         synchronized(this.mutex) {
            return this.delegate().comparator();
         }
      }

      public Object firstKey() {
         synchronized(this.mutex) {
            return this.delegate().firstKey();
         }
      }

      public SortedMap headMap(Object toKey) {
         synchronized(this.mutex) {
            return Synchronized.sortedMap(this.delegate().headMap(toKey), this.mutex);
         }
      }

      public Object lastKey() {
         synchronized(this.mutex) {
            return this.delegate().lastKey();
         }
      }

      public SortedMap subMap(Object fromKey, Object toKey) {
         synchronized(this.mutex) {
            return Synchronized.sortedMap(this.delegate().subMap(fromKey, toKey), this.mutex);
         }
      }

      public SortedMap tailMap(Object fromKey) {
         synchronized(this.mutex) {
            return Synchronized.sortedMap(this.delegate().tailMap(fromKey), this.mutex);
         }
      }
   }

   static final class SynchronizedBiMap extends SynchronizedMap implements BiMap, Serializable {
      @CheckForNull
      private transient Set valueSet;
      @CheckForNull
      @RetainedWith
      private transient BiMap inverse;
      private static final long serialVersionUID = 0L;

      private SynchronizedBiMap(BiMap delegate, @CheckForNull Object mutex, @CheckForNull BiMap inverse) {
         super(delegate, mutex);
         this.inverse = inverse;
      }

      BiMap delegate() {
         return (BiMap)super.delegate();
      }

      public Set values() {
         synchronized(this.mutex) {
            if (this.valueSet == null) {
               this.valueSet = Synchronized.set(this.delegate().values(), this.mutex);
            }

            return this.valueSet;
         }
      }

      @CheckForNull
      public Object forcePut(@ParametricNullness Object key, @ParametricNullness Object value) {
         synchronized(this.mutex) {
            return this.delegate().forcePut(key, value);
         }
      }

      public BiMap inverse() {
         synchronized(this.mutex) {
            if (this.inverse == null) {
               this.inverse = new SynchronizedBiMap(this.delegate().inverse(), this.mutex, this);
            }

            return this.inverse;
         }
      }
   }

   static final class SynchronizedAsMap extends SynchronizedMap {
      @CheckForNull
      transient Set asMapEntrySet;
      @CheckForNull
      transient Collection asMapValues;
      private static final long serialVersionUID = 0L;

      SynchronizedAsMap(Map delegate, @CheckForNull Object mutex) {
         super(delegate, mutex);
      }

      @CheckForNull
      public Collection get(@CheckForNull Object key) {
         synchronized(this.mutex) {
            Collection<V> collection = (Collection)super.get(key);
            return collection == null ? null : Synchronized.typePreservingCollection(collection, this.mutex);
         }
      }

      public Set entrySet() {
         synchronized(this.mutex) {
            if (this.asMapEntrySet == null) {
               this.asMapEntrySet = new SynchronizedAsMapEntries(this.delegate().entrySet(), this.mutex);
            }

            return this.asMapEntrySet;
         }
      }

      public Collection values() {
         synchronized(this.mutex) {
            if (this.asMapValues == null) {
               this.asMapValues = new SynchronizedAsMapValues(this.delegate().values(), this.mutex);
            }

            return this.asMapValues;
         }
      }

      public boolean containsValue(@CheckForNull Object o) {
         return this.values().contains(o);
      }
   }

   static final class SynchronizedAsMapValues extends SynchronizedCollection {
      private static final long serialVersionUID = 0L;

      SynchronizedAsMapValues(Collection delegate, @CheckForNull Object mutex) {
         super(delegate, mutex, null);
      }

      public Iterator iterator() {
         return new TransformedIterator(super.iterator()) {
            Collection transform(Collection from) {
               return Synchronized.typePreservingCollection(from, SynchronizedAsMapValues.this.mutex);
            }
         };
      }
   }

   @GwtIncompatible
   @VisibleForTesting
   static final class SynchronizedNavigableSet extends SynchronizedSortedSet implements NavigableSet {
      @CheckForNull
      transient NavigableSet descendingSet;
      private static final long serialVersionUID = 0L;

      SynchronizedNavigableSet(NavigableSet delegate, @CheckForNull Object mutex) {
         super(delegate, mutex);
      }

      NavigableSet delegate() {
         return (NavigableSet)super.delegate();
      }

      @CheckForNull
      public Object ceiling(Object e) {
         synchronized(this.mutex) {
            return this.delegate().ceiling(e);
         }
      }

      public Iterator descendingIterator() {
         return this.delegate().descendingIterator();
      }

      public NavigableSet descendingSet() {
         synchronized(this.mutex) {
            if (this.descendingSet == null) {
               NavigableSet<E> dS = Synchronized.navigableSet(this.delegate().descendingSet(), this.mutex);
               this.descendingSet = dS;
               return dS;
            } else {
               return this.descendingSet;
            }
         }
      }

      @CheckForNull
      public Object floor(Object e) {
         synchronized(this.mutex) {
            return this.delegate().floor(e);
         }
      }

      public NavigableSet headSet(Object toElement, boolean inclusive) {
         synchronized(this.mutex) {
            return Synchronized.navigableSet(this.delegate().headSet(toElement, inclusive), this.mutex);
         }
      }

      public SortedSet headSet(Object toElement) {
         return this.headSet(toElement, false);
      }

      @CheckForNull
      public Object higher(Object e) {
         synchronized(this.mutex) {
            return this.delegate().higher(e);
         }
      }

      @CheckForNull
      public Object lower(Object e) {
         synchronized(this.mutex) {
            return this.delegate().lower(e);
         }
      }

      @CheckForNull
      public Object pollFirst() {
         synchronized(this.mutex) {
            return this.delegate().pollFirst();
         }
      }

      @CheckForNull
      public Object pollLast() {
         synchronized(this.mutex) {
            return this.delegate().pollLast();
         }
      }

      public NavigableSet subSet(Object fromElement, boolean fromInclusive, Object toElement, boolean toInclusive) {
         synchronized(this.mutex) {
            return Synchronized.navigableSet(this.delegate().subSet(fromElement, fromInclusive, toElement, toInclusive), this.mutex);
         }
      }

      public SortedSet subSet(Object fromElement, Object toElement) {
         return this.subSet(fromElement, true, toElement, false);
      }

      public NavigableSet tailSet(Object fromElement, boolean inclusive) {
         synchronized(this.mutex) {
            return Synchronized.navigableSet(this.delegate().tailSet(fromElement, inclusive), this.mutex);
         }
      }

      public SortedSet tailSet(Object fromElement) {
         return this.tailSet(fromElement, true);
      }
   }

   @GwtIncompatible
   @VisibleForTesting
   static final class SynchronizedNavigableMap extends SynchronizedSortedMap implements NavigableMap {
      @CheckForNull
      transient NavigableSet descendingKeySet;
      @CheckForNull
      transient NavigableMap descendingMap;
      @CheckForNull
      transient NavigableSet navigableKeySet;
      private static final long serialVersionUID = 0L;

      SynchronizedNavigableMap(NavigableMap delegate, @CheckForNull Object mutex) {
         super(delegate, mutex);
      }

      NavigableMap delegate() {
         return (NavigableMap)super.delegate();
      }

      @CheckForNull
      public Map.Entry ceilingEntry(Object key) {
         synchronized(this.mutex) {
            return Synchronized.nullableSynchronizedEntry(this.delegate().ceilingEntry(key), this.mutex);
         }
      }

      @CheckForNull
      public Object ceilingKey(Object key) {
         synchronized(this.mutex) {
            return this.delegate().ceilingKey(key);
         }
      }

      public NavigableSet descendingKeySet() {
         synchronized(this.mutex) {
            return this.descendingKeySet == null ? (this.descendingKeySet = Synchronized.navigableSet(this.delegate().descendingKeySet(), this.mutex)) : this.descendingKeySet;
         }
      }

      public NavigableMap descendingMap() {
         synchronized(this.mutex) {
            return this.descendingMap == null ? (this.descendingMap = Synchronized.navigableMap(this.delegate().descendingMap(), this.mutex)) : this.descendingMap;
         }
      }

      @CheckForNull
      public Map.Entry firstEntry() {
         synchronized(this.mutex) {
            return Synchronized.nullableSynchronizedEntry(this.delegate().firstEntry(), this.mutex);
         }
      }

      @CheckForNull
      public Map.Entry floorEntry(Object key) {
         synchronized(this.mutex) {
            return Synchronized.nullableSynchronizedEntry(this.delegate().floorEntry(key), this.mutex);
         }
      }

      @CheckForNull
      public Object floorKey(Object key) {
         synchronized(this.mutex) {
            return this.delegate().floorKey(key);
         }
      }

      public NavigableMap headMap(Object toKey, boolean inclusive) {
         synchronized(this.mutex) {
            return Synchronized.navigableMap(this.delegate().headMap(toKey, inclusive), this.mutex);
         }
      }

      public SortedMap headMap(Object toKey) {
         return this.headMap(toKey, false);
      }

      @CheckForNull
      public Map.Entry higherEntry(Object key) {
         synchronized(this.mutex) {
            return Synchronized.nullableSynchronizedEntry(this.delegate().higherEntry(key), this.mutex);
         }
      }

      @CheckForNull
      public Object higherKey(Object key) {
         synchronized(this.mutex) {
            return this.delegate().higherKey(key);
         }
      }

      @CheckForNull
      public Map.Entry lastEntry() {
         synchronized(this.mutex) {
            return Synchronized.nullableSynchronizedEntry(this.delegate().lastEntry(), this.mutex);
         }
      }

      @CheckForNull
      public Map.Entry lowerEntry(Object key) {
         synchronized(this.mutex) {
            return Synchronized.nullableSynchronizedEntry(this.delegate().lowerEntry(key), this.mutex);
         }
      }

      @CheckForNull
      public Object lowerKey(Object key) {
         synchronized(this.mutex) {
            return this.delegate().lowerKey(key);
         }
      }

      public Set keySet() {
         return this.navigableKeySet();
      }

      public NavigableSet navigableKeySet() {
         synchronized(this.mutex) {
            return this.navigableKeySet == null ? (this.navigableKeySet = Synchronized.navigableSet(this.delegate().navigableKeySet(), this.mutex)) : this.navigableKeySet;
         }
      }

      @CheckForNull
      public Map.Entry pollFirstEntry() {
         synchronized(this.mutex) {
            return Synchronized.nullableSynchronizedEntry(this.delegate().pollFirstEntry(), this.mutex);
         }
      }

      @CheckForNull
      public Map.Entry pollLastEntry() {
         synchronized(this.mutex) {
            return Synchronized.nullableSynchronizedEntry(this.delegate().pollLastEntry(), this.mutex);
         }
      }

      public NavigableMap subMap(Object fromKey, boolean fromInclusive, Object toKey, boolean toInclusive) {
         synchronized(this.mutex) {
            return Synchronized.navigableMap(this.delegate().subMap(fromKey, fromInclusive, toKey, toInclusive), this.mutex);
         }
      }

      public SortedMap subMap(Object fromKey, Object toKey) {
         return this.subMap(fromKey, true, toKey, false);
      }

      public NavigableMap tailMap(Object fromKey, boolean inclusive) {
         synchronized(this.mutex) {
            return Synchronized.navigableMap(this.delegate().tailMap(fromKey, inclusive), this.mutex);
         }
      }

      public SortedMap tailMap(Object fromKey) {
         return this.tailMap(fromKey, true);
      }
   }

   @GwtIncompatible
   static final class SynchronizedEntry extends SynchronizedObject implements Map.Entry {
      private static final long serialVersionUID = 0L;

      SynchronizedEntry(Map.Entry delegate, @CheckForNull Object mutex) {
         super(delegate, mutex);
      }

      Map.Entry delegate() {
         return (Map.Entry)super.delegate();
      }

      public boolean equals(@CheckForNull Object obj) {
         synchronized(this.mutex) {
            return this.delegate().equals(obj);
         }
      }

      public int hashCode() {
         synchronized(this.mutex) {
            return this.delegate().hashCode();
         }
      }

      public Object getKey() {
         synchronized(this.mutex) {
            return this.delegate().getKey();
         }
      }

      public Object getValue() {
         synchronized(this.mutex) {
            return this.delegate().getValue();
         }
      }

      public Object setValue(Object value) {
         synchronized(this.mutex) {
            return this.delegate().setValue(value);
         }
      }
   }

   static class SynchronizedQueue extends SynchronizedCollection implements Queue {
      private static final long serialVersionUID = 0L;

      SynchronizedQueue(Queue delegate, @CheckForNull Object mutex) {
         super(delegate, mutex, null);
      }

      Queue delegate() {
         return (Queue)super.delegate();
      }

      public Object element() {
         synchronized(this.mutex) {
            return this.delegate().element();
         }
      }

      public boolean offer(Object e) {
         synchronized(this.mutex) {
            return this.delegate().offer(e);
         }
      }

      @CheckForNull
      public Object peek() {
         synchronized(this.mutex) {
            return this.delegate().peek();
         }
      }

      @CheckForNull
      public Object poll() {
         synchronized(this.mutex) {
            return this.delegate().poll();
         }
      }

      public Object remove() {
         synchronized(this.mutex) {
            return this.delegate().remove();
         }
      }
   }

   static final class SynchronizedDeque extends SynchronizedQueue implements Deque {
      private static final long serialVersionUID = 0L;

      SynchronizedDeque(Deque delegate, @CheckForNull Object mutex) {
         super(delegate, mutex);
      }

      Deque delegate() {
         return (Deque)super.delegate();
      }

      public void addFirst(Object e) {
         synchronized(this.mutex) {
            this.delegate().addFirst(e);
         }
      }

      public void addLast(Object e) {
         synchronized(this.mutex) {
            this.delegate().addLast(e);
         }
      }

      public boolean offerFirst(Object e) {
         synchronized(this.mutex) {
            return this.delegate().offerFirst(e);
         }
      }

      public boolean offerLast(Object e) {
         synchronized(this.mutex) {
            return this.delegate().offerLast(e);
         }
      }

      public Object removeFirst() {
         synchronized(this.mutex) {
            return this.delegate().removeFirst();
         }
      }

      public Object removeLast() {
         synchronized(this.mutex) {
            return this.delegate().removeLast();
         }
      }

      @CheckForNull
      public Object pollFirst() {
         synchronized(this.mutex) {
            return this.delegate().pollFirst();
         }
      }

      @CheckForNull
      public Object pollLast() {
         synchronized(this.mutex) {
            return this.delegate().pollLast();
         }
      }

      public Object getFirst() {
         synchronized(this.mutex) {
            return this.delegate().getFirst();
         }
      }

      public Object getLast() {
         synchronized(this.mutex) {
            return this.delegate().getLast();
         }
      }

      @CheckForNull
      public Object peekFirst() {
         synchronized(this.mutex) {
            return this.delegate().peekFirst();
         }
      }

      @CheckForNull
      public Object peekLast() {
         synchronized(this.mutex) {
            return this.delegate().peekLast();
         }
      }

      public boolean removeFirstOccurrence(@CheckForNull Object o) {
         synchronized(this.mutex) {
            return this.delegate().removeFirstOccurrence(o);
         }
      }

      public boolean removeLastOccurrence(@CheckForNull Object o) {
         synchronized(this.mutex) {
            return this.delegate().removeLastOccurrence(o);
         }
      }

      public void push(Object e) {
         synchronized(this.mutex) {
            this.delegate().push(e);
         }
      }

      public Object pop() {
         synchronized(this.mutex) {
            return this.delegate().pop();
         }
      }

      public Iterator descendingIterator() {
         synchronized(this.mutex) {
            return this.delegate().descendingIterator();
         }
      }
   }

   static final class SynchronizedTable extends SynchronizedObject implements Table {
      SynchronizedTable(Table delegate, @CheckForNull Object mutex) {
         super(delegate, mutex);
      }

      Table delegate() {
         return (Table)super.delegate();
      }

      public boolean contains(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
         synchronized(this.mutex) {
            return this.delegate().contains(rowKey, columnKey);
         }
      }

      public boolean containsRow(@CheckForNull Object rowKey) {
         synchronized(this.mutex) {
            return this.delegate().containsRow(rowKey);
         }
      }

      public boolean containsColumn(@CheckForNull Object columnKey) {
         synchronized(this.mutex) {
            return this.delegate().containsColumn(columnKey);
         }
      }

      public boolean containsValue(@CheckForNull Object value) {
         synchronized(this.mutex) {
            return this.delegate().containsValue(value);
         }
      }

      @CheckForNull
      public Object get(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
         synchronized(this.mutex) {
            return this.delegate().get(rowKey, columnKey);
         }
      }

      public boolean isEmpty() {
         synchronized(this.mutex) {
            return this.delegate().isEmpty();
         }
      }

      public int size() {
         synchronized(this.mutex) {
            return this.delegate().size();
         }
      }

      public void clear() {
         synchronized(this.mutex) {
            this.delegate().clear();
         }
      }

      @CheckForNull
      public Object put(@ParametricNullness Object rowKey, @ParametricNullness Object columnKey, @ParametricNullness Object value) {
         synchronized(this.mutex) {
            return this.delegate().put(rowKey, columnKey, value);
         }
      }

      public void putAll(Table table) {
         synchronized(this.mutex) {
            this.delegate().putAll(table);
         }
      }

      @CheckForNull
      public Object remove(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
         synchronized(this.mutex) {
            return this.delegate().remove(rowKey, columnKey);
         }
      }

      public Map row(@ParametricNullness Object rowKey) {
         synchronized(this.mutex) {
            return Synchronized.map(this.delegate().row(rowKey), this.mutex);
         }
      }

      public Map column(@ParametricNullness Object columnKey) {
         synchronized(this.mutex) {
            return Synchronized.map(this.delegate().column(columnKey), this.mutex);
         }
      }

      public Set cellSet() {
         synchronized(this.mutex) {
            return Synchronized.set(this.delegate().cellSet(), this.mutex);
         }
      }

      public Set rowKeySet() {
         synchronized(this.mutex) {
            return Synchronized.set(this.delegate().rowKeySet(), this.mutex);
         }
      }

      public Set columnKeySet() {
         synchronized(this.mutex) {
            return Synchronized.set(this.delegate().columnKeySet(), this.mutex);
         }
      }

      public Collection values() {
         synchronized(this.mutex) {
            return Synchronized.collection(this.delegate().values(), this.mutex);
         }
      }

      public Map rowMap() {
         synchronized(this.mutex) {
            return Synchronized.map(Maps.transformValues(this.delegate().rowMap(), new org.sparkproject.guava.base.Function() {
               public Map apply(Map t) {
                  return Synchronized.map(t, SynchronizedTable.this.mutex);
               }
            }), this.mutex);
         }
      }

      public Map columnMap() {
         synchronized(this.mutex) {
            return Synchronized.map(Maps.transformValues(this.delegate().columnMap(), new org.sparkproject.guava.base.Function() {
               public Map apply(Map t) {
                  return Synchronized.map(t, SynchronizedTable.this.mutex);
               }
            }), this.mutex);
         }
      }

      public int hashCode() {
         synchronized(this.mutex) {
            return this.delegate().hashCode();
         }
      }

      public boolean equals(@CheckForNull Object obj) {
         if (this == obj) {
            return true;
         } else {
            synchronized(this.mutex) {
               return this.delegate().equals(obj);
            }
         }
      }
   }
}
