package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.Weak;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public final class Multimaps {
   private Multimaps() {
   }

   public static Collector toMultimap(Function keyFunction, Function valueFunction, Supplier multimapSupplier) {
      return CollectCollectors.toMultimap(keyFunction, valueFunction, multimapSupplier);
   }

   public static Collector flatteningToMultimap(Function keyFunction, Function valueFunction, Supplier multimapSupplier) {
      return CollectCollectors.flatteningToMultimap(keyFunction, valueFunction, multimapSupplier);
   }

   public static Multimap newMultimap(Map map, final com.google.common.base.Supplier factory) {
      return new CustomMultimap(map, factory);
   }

   public static ListMultimap newListMultimap(Map map, final com.google.common.base.Supplier factory) {
      return new CustomListMultimap(map, factory);
   }

   public static SetMultimap newSetMultimap(Map map, final com.google.common.base.Supplier factory) {
      return new CustomSetMultimap(map, factory);
   }

   public static SortedSetMultimap newSortedSetMultimap(Map map, final com.google.common.base.Supplier factory) {
      return new CustomSortedSetMultimap(map, factory);
   }

   @CanIgnoreReturnValue
   public static Multimap invertFrom(Multimap source, Multimap dest) {
      Preconditions.checkNotNull(dest);

      for(Map.Entry entry : source.entries()) {
         dest.put(entry.getValue(), entry.getKey());
      }

      return dest;
   }

   @J2ktIncompatible
   public static Multimap synchronizedMultimap(Multimap multimap) {
      return Synchronized.multimap(multimap, (Object)null);
   }

   public static Multimap unmodifiableMultimap(Multimap delegate) {
      return (Multimap)(!(delegate instanceof UnmodifiableMultimap) && !(delegate instanceof ImmutableMultimap) ? new UnmodifiableMultimap(delegate) : delegate);
   }

   /** @deprecated */
   @Deprecated
   public static Multimap unmodifiableMultimap(ImmutableMultimap delegate) {
      return (Multimap)Preconditions.checkNotNull(delegate);
   }

   @J2ktIncompatible
   public static SetMultimap synchronizedSetMultimap(SetMultimap multimap) {
      return Synchronized.setMultimap(multimap, (Object)null);
   }

   public static SetMultimap unmodifiableSetMultimap(SetMultimap delegate) {
      return (SetMultimap)(!(delegate instanceof UnmodifiableSetMultimap) && !(delegate instanceof ImmutableSetMultimap) ? new UnmodifiableSetMultimap(delegate) : delegate);
   }

   /** @deprecated */
   @Deprecated
   public static SetMultimap unmodifiableSetMultimap(ImmutableSetMultimap delegate) {
      return (SetMultimap)Preconditions.checkNotNull(delegate);
   }

   @J2ktIncompatible
   public static SortedSetMultimap synchronizedSortedSetMultimap(SortedSetMultimap multimap) {
      return Synchronized.sortedSetMultimap(multimap, (Object)null);
   }

   public static SortedSetMultimap unmodifiableSortedSetMultimap(SortedSetMultimap delegate) {
      return (SortedSetMultimap)(delegate instanceof UnmodifiableSortedSetMultimap ? delegate : new UnmodifiableSortedSetMultimap(delegate));
   }

   @J2ktIncompatible
   public static ListMultimap synchronizedListMultimap(ListMultimap multimap) {
      return Synchronized.listMultimap(multimap, (Object)null);
   }

   public static ListMultimap unmodifiableListMultimap(ListMultimap delegate) {
      return (ListMultimap)(!(delegate instanceof UnmodifiableListMultimap) && !(delegate instanceof ImmutableListMultimap) ? new UnmodifiableListMultimap(delegate) : delegate);
   }

   /** @deprecated */
   @Deprecated
   public static ListMultimap unmodifiableListMultimap(ImmutableListMultimap delegate) {
      return (ListMultimap)Preconditions.checkNotNull(delegate);
   }

   private static Collection unmodifiableValueCollection(Collection collection) {
      if (collection instanceof SortedSet) {
         return Collections.unmodifiableSortedSet((SortedSet)collection);
      } else if (collection instanceof Set) {
         return Collections.unmodifiableSet((Set)collection);
      } else {
         return (Collection)(collection instanceof List ? Collections.unmodifiableList((List)collection) : Collections.unmodifiableCollection(collection));
      }
   }

   private static Collection unmodifiableEntries(Collection entries) {
      return (Collection)(entries instanceof Set ? Maps.unmodifiableEntrySet((Set)entries) : new Maps.UnmodifiableEntries(Collections.unmodifiableCollection(entries)));
   }

   public static Map asMap(ListMultimap multimap) {
      return multimap.asMap();
   }

   public static Map asMap(SetMultimap multimap) {
      return multimap.asMap();
   }

   public static Map asMap(SortedSetMultimap multimap) {
      return multimap.asMap();
   }

   public static Map asMap(Multimap multimap) {
      return multimap.asMap();
   }

   public static SetMultimap forMap(Map map) {
      return new MapMultimap(map);
   }

   public static Multimap transformValues(Multimap fromMultimap, final com.google.common.base.Function function) {
      Preconditions.checkNotNull(function);
      Maps.EntryTransformer<K, V1, V2> transformer = Maps.asEntryTransformer(function);
      return transformEntries(fromMultimap, transformer);
   }

   public static ListMultimap transformValues(ListMultimap fromMultimap, final com.google.common.base.Function function) {
      Preconditions.checkNotNull(function);
      Maps.EntryTransformer<K, V1, V2> transformer = Maps.asEntryTransformer(function);
      return transformEntries(fromMultimap, transformer);
   }

   public static Multimap transformEntries(Multimap fromMap, Maps.EntryTransformer transformer) {
      return new TransformedEntriesMultimap(fromMap, transformer);
   }

   public static ListMultimap transformEntries(ListMultimap fromMap, Maps.EntryTransformer transformer) {
      return new TransformedEntriesListMultimap(fromMap, transformer);
   }

   public static ImmutableListMultimap index(Iterable values, com.google.common.base.Function keyFunction) {
      return index(values.iterator(), keyFunction);
   }

   public static ImmutableListMultimap index(Iterator values, com.google.common.base.Function keyFunction) {
      Preconditions.checkNotNull(keyFunction);
      ImmutableListMultimap.Builder<K, V> builder = ImmutableListMultimap.builder();

      while(values.hasNext()) {
         V value = (V)values.next();
         Preconditions.checkNotNull(value, values);
         builder.put(keyFunction.apply(value), value);
      }

      return builder.build();
   }

   public static Multimap filterKeys(Multimap unfiltered, final Predicate keyPredicate) {
      if (unfiltered instanceof SetMultimap) {
         return filterKeys((SetMultimap)unfiltered, keyPredicate);
      } else if (unfiltered instanceof ListMultimap) {
         return filterKeys((ListMultimap)unfiltered, keyPredicate);
      } else if (unfiltered instanceof FilteredKeyMultimap) {
         FilteredKeyMultimap<K, V> prev = (FilteredKeyMultimap)unfiltered;
         return new FilteredKeyMultimap(prev.unfiltered, Predicates.and(prev.keyPredicate, keyPredicate));
      } else if (unfiltered instanceof FilteredMultimap) {
         FilteredMultimap<K, V> prev = (FilteredMultimap)unfiltered;
         return filterFiltered(prev, Maps.keyPredicateOnEntries(keyPredicate));
      } else {
         return new FilteredKeyMultimap(unfiltered, keyPredicate);
      }
   }

   public static SetMultimap filterKeys(SetMultimap unfiltered, final Predicate keyPredicate) {
      if (unfiltered instanceof FilteredKeySetMultimap) {
         FilteredKeySetMultimap<K, V> prev = (FilteredKeySetMultimap)unfiltered;
         return new FilteredKeySetMultimap(prev.unfiltered(), Predicates.and(prev.keyPredicate, keyPredicate));
      } else if (unfiltered instanceof FilteredSetMultimap) {
         FilteredSetMultimap<K, V> prev = (FilteredSetMultimap)unfiltered;
         return filterFiltered(prev, Maps.keyPredicateOnEntries(keyPredicate));
      } else {
         return new FilteredKeySetMultimap(unfiltered, keyPredicate);
      }
   }

   public static ListMultimap filterKeys(ListMultimap unfiltered, final Predicate keyPredicate) {
      if (unfiltered instanceof FilteredKeyListMultimap) {
         FilteredKeyListMultimap<K, V> prev = (FilteredKeyListMultimap)unfiltered;
         return new FilteredKeyListMultimap(prev.unfiltered(), Predicates.and(prev.keyPredicate, keyPredicate));
      } else {
         return new FilteredKeyListMultimap(unfiltered, keyPredicate);
      }
   }

   public static Multimap filterValues(Multimap unfiltered, final Predicate valuePredicate) {
      return filterEntries(unfiltered, Maps.valuePredicateOnEntries(valuePredicate));
   }

   public static SetMultimap filterValues(SetMultimap unfiltered, final Predicate valuePredicate) {
      return filterEntries(unfiltered, Maps.valuePredicateOnEntries(valuePredicate));
   }

   public static Multimap filterEntries(Multimap unfiltered, Predicate entryPredicate) {
      Preconditions.checkNotNull(entryPredicate);
      if (unfiltered instanceof SetMultimap) {
         return filterEntries((SetMultimap)unfiltered, entryPredicate);
      } else {
         return (Multimap)(unfiltered instanceof FilteredMultimap ? filterFiltered((FilteredMultimap)unfiltered, entryPredicate) : new FilteredEntryMultimap((Multimap)Preconditions.checkNotNull(unfiltered), entryPredicate));
      }
   }

   public static SetMultimap filterEntries(SetMultimap unfiltered, Predicate entryPredicate) {
      Preconditions.checkNotNull(entryPredicate);
      return (SetMultimap)(unfiltered instanceof FilteredSetMultimap ? filterFiltered((FilteredSetMultimap)unfiltered, entryPredicate) : new FilteredEntrySetMultimap((SetMultimap)Preconditions.checkNotNull(unfiltered), entryPredicate));
   }

   private static Multimap filterFiltered(FilteredMultimap multimap, Predicate entryPredicate) {
      Predicate<Map.Entry<K, V>> predicate = Predicates.and(multimap.entryPredicate(), entryPredicate);
      return new FilteredEntryMultimap(multimap.unfiltered(), predicate);
   }

   private static SetMultimap filterFiltered(FilteredSetMultimap multimap, Predicate entryPredicate) {
      Predicate<Map.Entry<K, V>> predicate = Predicates.and(multimap.entryPredicate(), entryPredicate);
      return new FilteredEntrySetMultimap(multimap.unfiltered(), predicate);
   }

   static boolean equalsImpl(Multimap multimap, @CheckForNull Object object) {
      if (object == multimap) {
         return true;
      } else if (object instanceof Multimap) {
         Multimap<?, ?> that = (Multimap)object;
         return multimap.asMap().equals(that.asMap());
      } else {
         return false;
      }
   }

   private static class CustomMultimap extends AbstractMapBasedMultimap {
      transient com.google.common.base.Supplier factory;
      @GwtIncompatible
      @J2ktIncompatible
      private static final long serialVersionUID = 0L;

      CustomMultimap(Map map, com.google.common.base.Supplier factory) {
         super(map);
         this.factory = (com.google.common.base.Supplier)Preconditions.checkNotNull(factory);
      }

      Set createKeySet() {
         return this.createMaybeNavigableKeySet();
      }

      Map createAsMap() {
         return this.createMaybeNavigableAsMap();
      }

      protected Collection createCollection() {
         return (Collection)this.factory.get();
      }

      Collection unmodifiableCollectionSubclass(Collection collection) {
         if (collection instanceof NavigableSet) {
            return Sets.unmodifiableNavigableSet((NavigableSet)collection);
         } else if (collection instanceof SortedSet) {
            return Collections.unmodifiableSortedSet((SortedSet)collection);
         } else if (collection instanceof Set) {
            return Collections.unmodifiableSet((Set)collection);
         } else {
            return (Collection)(collection instanceof List ? Collections.unmodifiableList((List)collection) : Collections.unmodifiableCollection(collection));
         }
      }

      Collection wrapCollection(@ParametricNullness Object key, Collection collection) {
         if (collection instanceof List) {
            return this.wrapList(key, (List)collection, (AbstractMapBasedMultimap.WrappedCollection)null);
         } else if (collection instanceof NavigableSet) {
            return new AbstractMapBasedMultimap.WrappedNavigableSet(key, (NavigableSet)collection, (AbstractMapBasedMultimap.WrappedCollection)null);
         } else if (collection instanceof SortedSet) {
            return new AbstractMapBasedMultimap.WrappedSortedSet(key, (SortedSet)collection, (AbstractMapBasedMultimap.WrappedCollection)null);
         } else {
            return (Collection)(collection instanceof Set ? new AbstractMapBasedMultimap.WrappedSet(key, (Set)collection) : new AbstractMapBasedMultimap.WrappedCollection(key, collection, (AbstractMapBasedMultimap.WrappedCollection)null));
         }
      }

      @GwtIncompatible
      @J2ktIncompatible
      private void writeObject(ObjectOutputStream stream) throws IOException {
         stream.defaultWriteObject();
         stream.writeObject(this.factory);
         stream.writeObject(this.backingMap());
      }

      @GwtIncompatible
      @J2ktIncompatible
      private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
         stream.defaultReadObject();
         this.factory = (com.google.common.base.Supplier)Objects.requireNonNull(stream.readObject());
         Map<K, Collection<V>> map = (Map)Objects.requireNonNull(stream.readObject());
         this.setMap(map);
      }
   }

   private static class CustomListMultimap extends AbstractListMultimap {
      transient com.google.common.base.Supplier factory;
      @GwtIncompatible
      @J2ktIncompatible
      private static final long serialVersionUID = 0L;

      CustomListMultimap(Map map, com.google.common.base.Supplier factory) {
         super(map);
         this.factory = (com.google.common.base.Supplier)Preconditions.checkNotNull(factory);
      }

      Set createKeySet() {
         return this.createMaybeNavigableKeySet();
      }

      Map createAsMap() {
         return this.createMaybeNavigableAsMap();
      }

      protected List createCollection() {
         return (List)this.factory.get();
      }

      @GwtIncompatible
      @J2ktIncompatible
      private void writeObject(ObjectOutputStream stream) throws IOException {
         stream.defaultWriteObject();
         stream.writeObject(this.factory);
         stream.writeObject(this.backingMap());
      }

      @GwtIncompatible
      @J2ktIncompatible
      private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
         stream.defaultReadObject();
         this.factory = (com.google.common.base.Supplier)Objects.requireNonNull(stream.readObject());
         Map<K, Collection<V>> map = (Map)Objects.requireNonNull(stream.readObject());
         this.setMap(map);
      }
   }

   private static class CustomSetMultimap extends AbstractSetMultimap {
      transient com.google.common.base.Supplier factory;
      @GwtIncompatible
      @J2ktIncompatible
      private static final long serialVersionUID = 0L;

      CustomSetMultimap(Map map, com.google.common.base.Supplier factory) {
         super(map);
         this.factory = (com.google.common.base.Supplier)Preconditions.checkNotNull(factory);
      }

      Set createKeySet() {
         return this.createMaybeNavigableKeySet();
      }

      Map createAsMap() {
         return this.createMaybeNavigableAsMap();
      }

      protected Set createCollection() {
         return (Set)this.factory.get();
      }

      Collection unmodifiableCollectionSubclass(Collection collection) {
         if (collection instanceof NavigableSet) {
            return Sets.unmodifiableNavigableSet((NavigableSet)collection);
         } else {
            return (Collection)(collection instanceof SortedSet ? Collections.unmodifiableSortedSet((SortedSet)collection) : Collections.unmodifiableSet((Set)collection));
         }
      }

      Collection wrapCollection(@ParametricNullness Object key, Collection collection) {
         if (collection instanceof NavigableSet) {
            return new AbstractMapBasedMultimap.WrappedNavigableSet(key, (NavigableSet)collection, (AbstractMapBasedMultimap.WrappedCollection)null);
         } else {
            return (Collection)(collection instanceof SortedSet ? new AbstractMapBasedMultimap.WrappedSortedSet(key, (SortedSet)collection, (AbstractMapBasedMultimap.WrappedCollection)null) : new AbstractMapBasedMultimap.WrappedSet(key, (Set)collection));
         }
      }

      @GwtIncompatible
      @J2ktIncompatible
      private void writeObject(ObjectOutputStream stream) throws IOException {
         stream.defaultWriteObject();
         stream.writeObject(this.factory);
         stream.writeObject(this.backingMap());
      }

      @GwtIncompatible
      @J2ktIncompatible
      private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
         stream.defaultReadObject();
         this.factory = (com.google.common.base.Supplier)Objects.requireNonNull(stream.readObject());
         Map<K, Collection<V>> map = (Map)Objects.requireNonNull(stream.readObject());
         this.setMap(map);
      }
   }

   private static class CustomSortedSetMultimap extends AbstractSortedSetMultimap {
      transient com.google.common.base.Supplier factory;
      @CheckForNull
      transient Comparator valueComparator;
      @GwtIncompatible
      @J2ktIncompatible
      private static final long serialVersionUID = 0L;

      CustomSortedSetMultimap(Map map, com.google.common.base.Supplier factory) {
         super(map);
         this.factory = (com.google.common.base.Supplier)Preconditions.checkNotNull(factory);
         this.valueComparator = ((SortedSet)factory.get()).comparator();
      }

      Set createKeySet() {
         return this.createMaybeNavigableKeySet();
      }

      Map createAsMap() {
         return this.createMaybeNavigableAsMap();
      }

      protected SortedSet createCollection() {
         return (SortedSet)this.factory.get();
      }

      @CheckForNull
      public Comparator valueComparator() {
         return this.valueComparator;
      }

      @GwtIncompatible
      @J2ktIncompatible
      private void writeObject(ObjectOutputStream stream) throws IOException {
         stream.defaultWriteObject();
         stream.writeObject(this.factory);
         stream.writeObject(this.backingMap());
      }

      @GwtIncompatible
      @J2ktIncompatible
      private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
         stream.defaultReadObject();
         this.factory = (com.google.common.base.Supplier)Objects.requireNonNull(stream.readObject());
         this.valueComparator = ((SortedSet)this.factory.get()).comparator();
         Map<K, Collection<V>> map = (Map)Objects.requireNonNull(stream.readObject());
         this.setMap(map);
      }
   }

   private static class UnmodifiableMultimap extends ForwardingMultimap implements Serializable {
      final Multimap delegate;
      @LazyInit
      @CheckForNull
      transient Collection entries;
      @LazyInit
      @CheckForNull
      transient Multiset keys;
      @LazyInit
      @CheckForNull
      transient Set keySet;
      @LazyInit
      @CheckForNull
      transient Collection values;
      @LazyInit
      @CheckForNull
      transient Map map;
      private static final long serialVersionUID = 0L;

      UnmodifiableMultimap(final Multimap delegate) {
         this.delegate = (Multimap)Preconditions.checkNotNull(delegate);
      }

      protected Multimap delegate() {
         return this.delegate;
      }

      public void clear() {
         throw new UnsupportedOperationException();
      }

      public Map asMap() {
         Map<K, Collection<V>> result = this.map;
         if (result == null) {
            result = this.map = Collections.unmodifiableMap(Maps.transformValues((Map)this.delegate.asMap(), (collection) -> Multimaps.unmodifiableValueCollection(collection)));
         }

         return result;
      }

      public Collection entries() {
         Collection<Map.Entry<K, V>> result = this.entries;
         if (result == null) {
            this.entries = result = Multimaps.unmodifiableEntries(this.delegate.entries());
         }

         return result;
      }

      public void forEach(BiConsumer consumer) {
         this.delegate.forEach((BiConsumer)Preconditions.checkNotNull(consumer));
      }

      public Collection get(@ParametricNullness Object key) {
         return Multimaps.unmodifiableValueCollection(this.delegate.get(key));
      }

      public Multiset keys() {
         Multiset<K> result = this.keys;
         if (result == null) {
            this.keys = result = Multisets.unmodifiableMultiset(this.delegate.keys());
         }

         return result;
      }

      public Set keySet() {
         Set<K> result = this.keySet;
         if (result == null) {
            this.keySet = result = Collections.unmodifiableSet(this.delegate.keySet());
         }

         return result;
      }

      public boolean put(@ParametricNullness Object key, @ParametricNullness Object value) {
         throw new UnsupportedOperationException();
      }

      public boolean putAll(@ParametricNullness Object key, Iterable values) {
         throw new UnsupportedOperationException();
      }

      public boolean putAll(Multimap multimap) {
         throw new UnsupportedOperationException();
      }

      public boolean remove(@CheckForNull Object key, @CheckForNull Object value) {
         throw new UnsupportedOperationException();
      }

      public Collection removeAll(@CheckForNull Object key) {
         throw new UnsupportedOperationException();
      }

      public Collection replaceValues(@ParametricNullness Object key, Iterable values) {
         throw new UnsupportedOperationException();
      }

      public Collection values() {
         Collection<V> result = this.values;
         if (result == null) {
            this.values = result = Collections.unmodifiableCollection(this.delegate.values());
         }

         return result;
      }
   }

   private static class UnmodifiableListMultimap extends UnmodifiableMultimap implements ListMultimap {
      private static final long serialVersionUID = 0L;

      UnmodifiableListMultimap(ListMultimap delegate) {
         super(delegate);
      }

      public ListMultimap delegate() {
         return (ListMultimap)super.delegate();
      }

      public List get(@ParametricNullness Object key) {
         return Collections.unmodifiableList(this.delegate().get(key));
      }

      public List removeAll(@CheckForNull Object key) {
         throw new UnsupportedOperationException();
      }

      public List replaceValues(@ParametricNullness Object key, Iterable values) {
         throw new UnsupportedOperationException();
      }
   }

   private static class UnmodifiableSetMultimap extends UnmodifiableMultimap implements SetMultimap {
      private static final long serialVersionUID = 0L;

      UnmodifiableSetMultimap(SetMultimap delegate) {
         super(delegate);
      }

      public SetMultimap delegate() {
         return (SetMultimap)super.delegate();
      }

      public Set get(@ParametricNullness Object key) {
         return Collections.unmodifiableSet(this.delegate().get(key));
      }

      public Set entries() {
         return Maps.unmodifiableEntrySet(this.delegate().entries());
      }

      public Set removeAll(@CheckForNull Object key) {
         throw new UnsupportedOperationException();
      }

      public Set replaceValues(@ParametricNullness Object key, Iterable values) {
         throw new UnsupportedOperationException();
      }
   }

   private static class UnmodifiableSortedSetMultimap extends UnmodifiableSetMultimap implements SortedSetMultimap {
      private static final long serialVersionUID = 0L;

      UnmodifiableSortedSetMultimap(SortedSetMultimap delegate) {
         super(delegate);
      }

      public SortedSetMultimap delegate() {
         return (SortedSetMultimap)super.delegate();
      }

      public SortedSet get(@ParametricNullness Object key) {
         return Collections.unmodifiableSortedSet(this.delegate().get(key));
      }

      public SortedSet removeAll(@CheckForNull Object key) {
         throw new UnsupportedOperationException();
      }

      public SortedSet replaceValues(@ParametricNullness Object key, Iterable values) {
         throw new UnsupportedOperationException();
      }

      @CheckForNull
      public Comparator valueComparator() {
         return this.delegate().valueComparator();
      }
   }

   private static class MapMultimap extends AbstractMultimap implements SetMultimap, Serializable {
      final Map map;
      private static final long serialVersionUID = 7845222491160860175L;

      MapMultimap(Map map) {
         this.map = (Map)Preconditions.checkNotNull(map);
      }

      public int size() {
         return this.map.size();
      }

      public boolean containsKey(@CheckForNull Object key) {
         return this.map.containsKey(key);
      }

      public boolean containsValue(@CheckForNull Object value) {
         return this.map.containsValue(value);
      }

      public boolean containsEntry(@CheckForNull Object key, @CheckForNull Object value) {
         return this.map.entrySet().contains(Maps.immutableEntry(key, value));
      }

      public Set get(@ParametricNullness final Object key) {
         return new Sets.ImprovedAbstractSet() {
            public Iterator iterator() {
               return new Iterator() {
                  int i;

                  public boolean hasNext() {
                     return this.i == 0 && MapMultimap.this.map.containsKey(key);
                  }

                  @ParametricNullness
                  public Object next() {
                     if (!this.hasNext()) {
                        throw new NoSuchElementException();
                     } else {
                        ++this.i;
                        return NullnessCasts.uncheckedCastNullableTToT(MapMultimap.this.map.get(key));
                     }
                  }

                  public void remove() {
                     CollectPreconditions.checkRemove(this.i == 1);
                     this.i = -1;
                     MapMultimap.this.map.remove(key);
                  }
               };
            }

            public int size() {
               return MapMultimap.this.map.containsKey(key) ? 1 : 0;
            }
         };
      }

      public boolean put(@ParametricNullness Object key, @ParametricNullness Object value) {
         throw new UnsupportedOperationException();
      }

      public boolean putAll(@ParametricNullness Object key, Iterable values) {
         throw new UnsupportedOperationException();
      }

      public boolean putAll(Multimap multimap) {
         throw new UnsupportedOperationException();
      }

      public Set replaceValues(@ParametricNullness Object key, Iterable values) {
         throw new UnsupportedOperationException();
      }

      public boolean remove(@CheckForNull Object key, @CheckForNull Object value) {
         return this.map.entrySet().remove(Maps.immutableEntry(key, value));
      }

      public Set removeAll(@CheckForNull Object key) {
         Set<V> values = new HashSet(2);
         if (!this.map.containsKey(key)) {
            return values;
         } else {
            values.add(this.map.remove(key));
            return values;
         }
      }

      public void clear() {
         this.map.clear();
      }

      Set createKeySet() {
         return this.map.keySet();
      }

      Collection createValues() {
         return this.map.values();
      }

      public Set entries() {
         return this.map.entrySet();
      }

      Collection createEntries() {
         throw new AssertionError("unreachable");
      }

      Multiset createKeys() {
         return new Keys(this);
      }

      Iterator entryIterator() {
         return this.map.entrySet().iterator();
      }

      Map createAsMap() {
         return new AsMap(this);
      }

      public int hashCode() {
         return this.map.hashCode();
      }
   }

   private static class TransformedEntriesMultimap extends AbstractMultimap {
      final Multimap fromMultimap;
      final Maps.EntryTransformer transformer;

      TransformedEntriesMultimap(Multimap fromMultimap, final Maps.EntryTransformer transformer) {
         this.fromMultimap = (Multimap)Preconditions.checkNotNull(fromMultimap);
         this.transformer = (Maps.EntryTransformer)Preconditions.checkNotNull(transformer);
      }

      Collection transform(@ParametricNullness Object key, Collection values) {
         com.google.common.base.Function<? super V1, V2> function = Maps.asValueToValueFunction(this.transformer, key);
         return (Collection)(values instanceof List ? Lists.transform((List)values, function) : Collections2.transform(values, function));
      }

      Map createAsMap() {
         return Maps.transformEntries((Map)this.fromMultimap.asMap(), (key, value) -> this.transform(key, value));
      }

      public void clear() {
         this.fromMultimap.clear();
      }

      public boolean containsKey(@CheckForNull Object key) {
         return this.fromMultimap.containsKey(key);
      }

      Collection createEntries() {
         return new AbstractMultimap.Entries();
      }

      Iterator entryIterator() {
         return Iterators.transform(this.fromMultimap.entries().iterator(), Maps.asEntryToEntryFunction(this.transformer));
      }

      public Collection get(@ParametricNullness final Object key) {
         return this.transform(key, this.fromMultimap.get(key));
      }

      public boolean isEmpty() {
         return this.fromMultimap.isEmpty();
      }

      Set createKeySet() {
         return this.fromMultimap.keySet();
      }

      Multiset createKeys() {
         return this.fromMultimap.keys();
      }

      public boolean put(@ParametricNullness Object key, @ParametricNullness Object value) {
         throw new UnsupportedOperationException();
      }

      public boolean putAll(@ParametricNullness Object key, Iterable values) {
         throw new UnsupportedOperationException();
      }

      public boolean putAll(Multimap multimap) {
         throw new UnsupportedOperationException();
      }

      public boolean remove(@CheckForNull Object key, @CheckForNull Object value) {
         return this.get(key).remove(value);
      }

      public Collection removeAll(@CheckForNull Object key) {
         return this.transform(key, this.fromMultimap.removeAll(key));
      }

      public Collection replaceValues(@ParametricNullness Object key, Iterable values) {
         throw new UnsupportedOperationException();
      }

      public int size() {
         return this.fromMultimap.size();
      }

      Collection createValues() {
         return Collections2.transform(this.fromMultimap.entries(), Maps.asEntryToValueFunction(this.transformer));
      }
   }

   private static final class TransformedEntriesListMultimap extends TransformedEntriesMultimap implements ListMultimap {
      TransformedEntriesListMultimap(ListMultimap fromMultimap, Maps.EntryTransformer transformer) {
         super(fromMultimap, transformer);
      }

      List transform(@ParametricNullness Object key, Collection values) {
         return Lists.transform((List)values, Maps.asValueToValueFunction(this.transformer, key));
      }

      public List get(@ParametricNullness Object key) {
         return this.transform(key, this.fromMultimap.get(key));
      }

      public List removeAll(@CheckForNull Object key) {
         return this.transform(key, this.fromMultimap.removeAll(key));
      }

      public List replaceValues(@ParametricNullness Object key, Iterable values) {
         throw new UnsupportedOperationException();
      }
   }

   static class Keys extends AbstractMultiset {
      @Weak
      final Multimap multimap;

      Keys(Multimap multimap) {
         this.multimap = multimap;
      }

      Iterator entryIterator() {
         return new TransformedIterator(this.multimap.asMap().entrySet().iterator()) {
            Multiset.Entry transform(final Map.Entry backingEntry) {
               return new Multisets.AbstractEntry() {
                  @ParametricNullness
                  public Object getElement() {
                     return backingEntry.getKey();
                  }

                  public int getCount() {
                     return ((Collection)backingEntry.getValue()).size();
                  }
               };
            }
         };
      }

      public Spliterator spliterator() {
         return CollectSpliterators.map(this.multimap.entries().spliterator(), Map.Entry::getKey);
      }

      public void forEach(Consumer consumer) {
         Preconditions.checkNotNull(consumer);
         this.multimap.entries().forEach((entry) -> consumer.accept(entry.getKey()));
      }

      int distinctElements() {
         return this.multimap.asMap().size();
      }

      public int size() {
         return this.multimap.size();
      }

      public boolean contains(@CheckForNull Object element) {
         return this.multimap.containsKey(element);
      }

      public Iterator iterator() {
         return Maps.keyIterator(this.multimap.entries().iterator());
      }

      public int count(@CheckForNull Object element) {
         Collection<V> values = (Collection)Maps.safeGet(this.multimap.asMap(), element);
         return values == null ? 0 : values.size();
      }

      public int remove(@CheckForNull Object element, int occurrences) {
         CollectPreconditions.checkNonnegative(occurrences, "occurrences");
         if (occurrences == 0) {
            return this.count(element);
         } else {
            Collection<V> values = (Collection)Maps.safeGet(this.multimap.asMap(), element);
            if (values == null) {
               return 0;
            } else {
               int oldCount = values.size();
               if (occurrences >= oldCount) {
                  values.clear();
               } else {
                  Iterator<V> iterator = values.iterator();

                  for(int i = 0; i < occurrences; ++i) {
                     iterator.next();
                     iterator.remove();
                  }
               }

               return oldCount;
            }
         }
      }

      public void clear() {
         this.multimap.clear();
      }

      public Set elementSet() {
         return this.multimap.keySet();
      }

      Iterator elementIterator() {
         throw new AssertionError("should never be called");
      }
   }

   abstract static class Entries extends AbstractCollection {
      abstract Multimap multimap();

      public int size() {
         return this.multimap().size();
      }

      public boolean contains(@CheckForNull Object o) {
         if (o instanceof Map.Entry) {
            Map.Entry<?, ?> entry = (Map.Entry)o;
            return this.multimap().containsEntry(entry.getKey(), entry.getValue());
         } else {
            return false;
         }
      }

      public boolean remove(@CheckForNull Object o) {
         if (o instanceof Map.Entry) {
            Map.Entry<?, ?> entry = (Map.Entry)o;
            return this.multimap().remove(entry.getKey(), entry.getValue());
         } else {
            return false;
         }
      }

      public void clear() {
         this.multimap().clear();
      }
   }

   static final class AsMap extends Maps.ViewCachingAbstractMap {
      @Weak
      private final Multimap multimap;

      AsMap(Multimap multimap) {
         this.multimap = (Multimap)Preconditions.checkNotNull(multimap);
      }

      public int size() {
         return this.multimap.keySet().size();
      }

      protected Set createEntrySet() {
         return new EntrySet();
      }

      void removeValuesForKey(@CheckForNull Object key) {
         this.multimap.keySet().remove(key);
      }

      @CheckForNull
      public Collection get(@CheckForNull Object key) {
         return this.containsKey(key) ? this.multimap.get(key) : null;
      }

      @CheckForNull
      public Collection remove(@CheckForNull Object key) {
         return this.containsKey(key) ? this.multimap.removeAll(key) : null;
      }

      public Set keySet() {
         return this.multimap.keySet();
      }

      public boolean isEmpty() {
         return this.multimap.isEmpty();
      }

      public boolean containsKey(@CheckForNull Object key) {
         return this.multimap.containsKey(key);
      }

      public void clear() {
         this.multimap.clear();
      }

      class EntrySet extends Maps.EntrySet {
         Map map() {
            return AsMap.this;
         }

         public Iterator iterator() {
            return Maps.asMapEntryIterator(AsMap.this.multimap.keySet(), (key) -> AsMap.this.multimap.get(key));
         }

         public boolean remove(@CheckForNull Object o) {
            if (!this.contains(o)) {
               return false;
            } else {
               Map.Entry<?, ?> entry = (Map.Entry)Objects.requireNonNull((Map.Entry)o);
               AsMap.this.removeValuesForKey(entry.getKey());
               return true;
            }
         }
      }
   }
}
