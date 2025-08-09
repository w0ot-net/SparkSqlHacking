package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
class FilteredEntryMultimap extends AbstractMultimap implements FilteredMultimap {
   final Multimap unfiltered;
   final Predicate predicate;

   FilteredEntryMultimap(Multimap unfiltered, Predicate predicate) {
      this.unfiltered = (Multimap)Preconditions.checkNotNull(unfiltered);
      this.predicate = (Predicate)Preconditions.checkNotNull(predicate);
   }

   public Multimap unfiltered() {
      return this.unfiltered;
   }

   public Predicate entryPredicate() {
      return this.predicate;
   }

   public int size() {
      return this.entries().size();
   }

   private boolean satisfies(@ParametricNullness Object key, @ParametricNullness Object value) {
      return this.predicate.apply(Maps.immutableEntry(key, value));
   }

   static Collection filterCollection(Collection collection, Predicate predicate) {
      return (Collection)(collection instanceof Set ? Sets.filter((Set)collection, predicate) : Collections2.filter(collection, predicate));
   }

   public boolean containsKey(@CheckForNull Object key) {
      return this.asMap().get(key) != null;
   }

   public Collection removeAll(@CheckForNull Object key) {
      return (Collection)MoreObjects.firstNonNull((Collection)this.asMap().remove(key), this.unmodifiableEmptyCollection());
   }

   Collection unmodifiableEmptyCollection() {
      return (Collection)(this.unfiltered instanceof SetMultimap ? Collections.emptySet() : Collections.emptyList());
   }

   public void clear() {
      this.entries().clear();
   }

   public Collection get(@ParametricNullness Object key) {
      return filterCollection(this.unfiltered.get(key), new ValuePredicate(key));
   }

   Collection createEntries() {
      return filterCollection(this.unfiltered.entries(), this.predicate);
   }

   Collection createValues() {
      return new FilteredMultimapValues(this);
   }

   Iterator entryIterator() {
      throw new AssertionError("should never be called");
   }

   Map createAsMap() {
      return new AsMap();
   }

   Set createKeySet() {
      return this.asMap().keySet();
   }

   boolean removeEntriesIf(Predicate predicate) {
      Iterator<Map.Entry<K, Collection<V>>> entryIterator = this.unfiltered.asMap().entrySet().iterator();
      boolean changed = false;

      while(entryIterator.hasNext()) {
         Map.Entry<K, Collection<V>> entry = (Map.Entry)entryIterator.next();
         K key = (K)entry.getKey();
         Collection<V> collection = filterCollection((Collection)entry.getValue(), new ValuePredicate(key));
         if (!collection.isEmpty() && predicate.apply(Maps.immutableEntry(key, collection))) {
            if (collection.size() == ((Collection)entry.getValue()).size()) {
               entryIterator.remove();
            } else {
               collection.clear();
            }

            changed = true;
         }
      }

      return changed;
   }

   Multiset createKeys() {
      return new Keys();
   }

   final class ValuePredicate implements Predicate {
      @ParametricNullness
      private final Object key;

      ValuePredicate(@ParametricNullness Object key) {
         this.key = key;
      }

      public boolean apply(@ParametricNullness Object value) {
         return FilteredEntryMultimap.this.satisfies(this.key, value);
      }
   }

   class AsMap extends Maps.ViewCachingAbstractMap {
      public boolean containsKey(@CheckForNull Object key) {
         return this.get(key) != null;
      }

      public void clear() {
         FilteredEntryMultimap.this.clear();
      }

      @CheckForNull
      public Collection get(@CheckForNull Object key) {
         Collection<V> result = (Collection)FilteredEntryMultimap.this.unfiltered.asMap().get(key);
         if (result == null) {
            return null;
         } else {
            result = FilteredEntryMultimap.filterCollection(result, FilteredEntryMultimap.this.new ValuePredicate(key));
            return result.isEmpty() ? null : result;
         }
      }

      @CheckForNull
      public Collection remove(@CheckForNull Object key) {
         Collection<V> collection = (Collection)FilteredEntryMultimap.this.unfiltered.asMap().get(key);
         if (collection == null) {
            return null;
         } else {
            K k = (K)key;
            List<V> result = Lists.newArrayList();
            Iterator<V> itr = collection.iterator();

            while(itr.hasNext()) {
               V v = (V)itr.next();
               if (FilteredEntryMultimap.this.satisfies(k, v)) {
                  itr.remove();
                  result.add(v);
               }
            }

            if (result.isEmpty()) {
               return null;
            } else if (FilteredEntryMultimap.this.unfiltered instanceof SetMultimap) {
               return Collections.unmodifiableSet(Sets.newLinkedHashSet(result));
            } else {
               return Collections.unmodifiableList(result);
            }
         }
      }

      Set createKeySet() {
         class KeySetImpl extends Maps.KeySet {
            KeySetImpl() {
               super(AsMap.this);
            }

            public boolean removeAll(Collection c) {
               return FilteredEntryMultimap.this.removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.in(c)));
            }

            public boolean retainAll(Collection c) {
               return FilteredEntryMultimap.this.removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(c))));
            }

            public boolean remove(@CheckForNull Object o) {
               return AsMap.this.remove(o) != null;
            }
         }

         return new KeySetImpl();
      }

      Set createEntrySet() {
         class EntrySetImpl extends Maps.EntrySet {
            Map map() {
               return AsMap.this;
            }

            public Iterator iterator() {
               return new AbstractIterator() {
                  final Iterator backingIterator;

                  {
                     this.backingIterator = FilteredEntryMultimap.this.unfiltered.asMap().entrySet().iterator();
                  }

                  @CheckForNull
                  protected Map.Entry computeNext() {
                     while(true) {
                        if (this.backingIterator.hasNext()) {
                           Map.Entry<K, Collection<V>> entry = (Map.Entry)this.backingIterator.next();
                           K key = (K)entry.getKey();
                           Collection<V> collection = FilteredEntryMultimap.filterCollection((Collection)entry.getValue(), FilteredEntryMultimap.this.new ValuePredicate(key));
                           if (collection.isEmpty()) {
                              continue;
                           }

                           return Maps.immutableEntry(key, collection);
                        }

                        return (Map.Entry)this.endOfData();
                     }
                  }
               };
            }

            public boolean removeAll(Collection c) {
               return FilteredEntryMultimap.this.removeEntriesIf(Predicates.in(c));
            }

            public boolean retainAll(Collection c) {
               return FilteredEntryMultimap.this.removeEntriesIf(Predicates.not(Predicates.in(c)));
            }

            public int size() {
               return Iterators.size(this.iterator());
            }
         }

         return new EntrySetImpl();
      }

      Collection createValues() {
         class ValuesImpl extends Maps.Values {
            ValuesImpl() {
               super(AsMap.this);
            }

            public boolean remove(@CheckForNull Object o) {
               if (o instanceof Collection) {
                  Collection<?> c = (Collection)o;
                  Iterator<Map.Entry<K, Collection<V>>> entryIterator = FilteredEntryMultimap.this.unfiltered.asMap().entrySet().iterator();

                  while(entryIterator.hasNext()) {
                     Map.Entry<K, Collection<V>> entry = (Map.Entry)entryIterator.next();
                     K key = (K)entry.getKey();
                     Collection<V> collection = FilteredEntryMultimap.filterCollection((Collection)entry.getValue(), FilteredEntryMultimap.this.new ValuePredicate(key));
                     if (!collection.isEmpty() && c.equals(collection)) {
                        if (collection.size() == ((Collection)entry.getValue()).size()) {
                           entryIterator.remove();
                        } else {
                           collection.clear();
                        }

                        return true;
                     }
                  }
               }

               return false;
            }

            public boolean removeAll(Collection c) {
               return FilteredEntryMultimap.this.removeEntriesIf(Maps.valuePredicateOnEntries(Predicates.in(c)));
            }

            public boolean retainAll(Collection c) {
               return FilteredEntryMultimap.this.removeEntriesIf(Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(c))));
            }
         }

         return new ValuesImpl();
      }
   }

   class Keys extends Multimaps.Keys {
      Keys() {
         super(FilteredEntryMultimap.this);
      }

      public int remove(@CheckForNull Object key, int occurrences) {
         CollectPreconditions.checkNonnegative(occurrences, "occurrences");
         if (occurrences == 0) {
            return this.count(key);
         } else {
            Collection<V> collection = (Collection)FilteredEntryMultimap.this.unfiltered.asMap().get(key);
            if (collection == null) {
               return 0;
            } else {
               K k = (K)key;
               int oldCount = 0;
               Iterator<V> itr = collection.iterator();

               while(itr.hasNext()) {
                  V v = (V)itr.next();
                  if (FilteredEntryMultimap.this.satisfies(k, v)) {
                     ++oldCount;
                     if (oldCount <= occurrences) {
                        itr.remove();
                     }
                  }
               }

               return oldCount;
            }
         }
      }

      public Set entrySet() {
         return new Multisets.EntrySet() {
            Multiset multiset() {
               return Keys.this;
            }

            public Iterator iterator() {
               return Keys.this.entryIterator();
            }

            public int size() {
               return FilteredEntryMultimap.this.keySet().size();
            }

            private boolean removeEntriesIf(Predicate predicate) {
               return FilteredEntryMultimap.this.removeEntriesIf((entry) -> predicate.apply(Multisets.immutableEntry(entry.getKey(), ((Collection)entry.getValue()).size())));
            }

            public boolean removeAll(Collection c) {
               return this.removeEntriesIf(Predicates.in(c));
            }

            public boolean retainAll(Collection c) {
               return this.removeEntriesIf(Predicates.not(Predicates.in(c)));
            }
         };
      }
   }
}
