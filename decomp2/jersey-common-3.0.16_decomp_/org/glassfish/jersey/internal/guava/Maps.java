package org.glassfish.jersey.internal.guava;

import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Maps {
   private static final Joiner.MapJoiner STANDARD_JOINER;

   private Maps() {
   }

   private static Function keyFunction() {
      return Maps.EntryFunction.KEY;
   }

   private static Function valueFunction() {
      return Maps.EntryFunction.VALUE;
   }

   private static Iterator keyIterator(Iterator entryIterator) {
      return Iterators.transform(entryIterator, keyFunction());
   }

   static Iterator valueIterator(Iterator entryIterator) {
      return Iterators.transform(entryIterator, valueFunction());
   }

   public static HashMap newHashMapWithExpectedSize(int expectedSize) {
      return new HashMap(capacity(expectedSize));
   }

   static int capacity(int expectedSize) {
      if (expectedSize < 3) {
         CollectPreconditions.checkNonnegative(expectedSize, "expectedSize");
         return expectedSize + 1;
      } else {
         return expectedSize < 1073741824 ? expectedSize + expectedSize / 3 : Integer.MAX_VALUE;
      }
   }

   static Iterator asMapEntryIterator(Set set, final Function function) {
      return new TransformedIterator(set.iterator()) {
         Map.Entry transform(Object key) {
            return Maps.immutableEntry(key, function.apply(key));
         }
      };
   }

   private static Set removeOnlySet(final Set set) {
      return new ForwardingSet() {
         protected Set delegate() {
            return set;
         }

         public boolean add(Object element) {
            throw new UnsupportedOperationException();
         }

         public boolean addAll(Collection es) {
            throw new UnsupportedOperationException();
         }
      };
   }

   private static SortedSet removeOnlySortedSet(final SortedSet set) {
      return new ForwardingSortedSet() {
         protected SortedSet delegate() {
            return set;
         }

         public boolean add(Object element) {
            throw new UnsupportedOperationException();
         }

         public boolean addAll(Collection es) {
            throw new UnsupportedOperationException();
         }

         public SortedSet headSet(Object toElement) {
            return Maps.removeOnlySortedSet(super.headSet(toElement));
         }

         public SortedSet subSet(Object fromElement, Object toElement) {
            return Maps.removeOnlySortedSet(super.subSet(fromElement, toElement));
         }

         public SortedSet tailSet(Object fromElement) {
            return Maps.removeOnlySortedSet(super.tailSet(fromElement));
         }
      };
   }

   public static Map.Entry immutableEntry(Object key, Object value) {
      return new ImmutableEntry(key, value);
   }

   static Predicate keyPredicateOnEntries(Predicate keyPredicate) {
      return Predicates.compose(keyPredicate, keyFunction());
   }

   static Predicate valuePredicateOnEntries(Predicate valuePredicate) {
      return Predicates.compose(valuePredicate, valueFunction());
   }

   static Object safeGet(Map map, Object key) {
      Preconditions.checkNotNull(map);

      try {
         return map.get(key);
      } catch (ClassCastException var3) {
         return null;
      } catch (NullPointerException var4) {
         return null;
      }
   }

   static boolean safeContainsKey(Map map, Object key) {
      Preconditions.checkNotNull(map);

      try {
         return map.containsKey(key);
      } catch (ClassCastException var3) {
         return false;
      } catch (NullPointerException var4) {
         return false;
      }
   }

   static Object safeRemove(Map map, Object key) {
      Preconditions.checkNotNull(map);

      try {
         return map.remove(key);
      } catch (ClassCastException var3) {
         return null;
      } catch (NullPointerException var4) {
         return null;
      }
   }

   static {
      STANDARD_JOINER = Collections2.STANDARD_JOINER.withKeyValueSeparator();
   }

   private static enum EntryFunction implements Function {
      KEY {
         public Object apply(Map.Entry entry) {
            return entry.getKey();
         }
      },
      VALUE {
         public Object apply(Map.Entry entry) {
            return entry.getValue();
         }
      };

      private EntryFunction() {
      }
   }

   private static class AsMapView extends ImprovedAbstractMap {
      final Function function;
      private final Set set;

      AsMapView(Set set, Function function) {
         this.set = (Set)Preconditions.checkNotNull(set);
         this.function = (Function)Preconditions.checkNotNull(function);
      }

      Set backingSet() {
         return this.set;
      }

      public Set createKeySet() {
         return Maps.removeOnlySet(this.backingSet());
      }

      Collection createValues() {
         return Collections2.transform(this.set, this.function);
      }

      public int size() {
         return this.backingSet().size();
      }

      public boolean containsKey(Object key) {
         return this.backingSet().contains(key);
      }

      public Object get(Object key) {
         return Collections2.safeContains(this.backingSet(), key) ? this.function.apply(key) : null;
      }

      public Object remove(Object key) {
         return this.backingSet().remove(key) ? this.function.apply(key) : null;
      }

      public void clear() {
         this.backingSet().clear();
      }

      protected Set createEntrySet() {
         return new EntrySet() {
            Map map() {
               return AsMapView.this;
            }

            public Iterator iterator() {
               return Maps.asMapEntryIterator(AsMapView.this.backingSet(), AsMapView.this.function);
            }
         };
      }
   }

   abstract static class ImprovedAbstractMap extends AbstractMap {
      private transient Set entrySet;
      private transient Set keySet;
      private transient Collection values;

      abstract Set createEntrySet();

      public Set entrySet() {
         Set<Map.Entry<K, V>> result = this.entrySet;
         return result == null ? (this.entrySet = this.createEntrySet()) : result;
      }

      public Set keySet() {
         Set<K> result = this.keySet;
         return result == null ? (this.keySet = this.createKeySet()) : result;
      }

      Set createKeySet() {
         return new KeySet(this);
      }

      public Collection values() {
         Collection<V> result = this.values;
         return result == null ? (this.values = this.createValues()) : result;
      }

      Collection createValues() {
         return new Values(this);
      }
   }

   static class KeySet extends Sets.ImprovedAbstractSet {
      final Map map;

      KeySet(Map map) {
         this.map = (Map)Preconditions.checkNotNull(map);
      }

      Map map() {
         return this.map;
      }

      public Iterator iterator() {
         return Maps.keyIterator(this.map().entrySet().iterator());
      }

      public int size() {
         return this.map().size();
      }

      public boolean isEmpty() {
         return this.map().isEmpty();
      }

      public boolean contains(Object o) {
         return this.map().containsKey(o);
      }

      public boolean remove(Object o) {
         if (this.contains(o)) {
            this.map().remove(o);
            return true;
         } else {
            return false;
         }
      }

      public void clear() {
         this.map().clear();
      }
   }

   static class Values extends AbstractCollection {
      final Map map;

      Values(Map map) {
         this.map = (Map)Preconditions.checkNotNull(map);
      }

      final Map map() {
         return this.map;
      }

      public Iterator iterator() {
         return Maps.valueIterator(this.map().entrySet().iterator());
      }

      public boolean remove(Object o) {
         try {
            return super.remove(o);
         } catch (UnsupportedOperationException var5) {
            for(Map.Entry entry : this.map().entrySet()) {
               if (Objects.equals(o, entry.getValue())) {
                  this.map().remove(entry.getKey());
                  return true;
               }
            }

            return false;
         }
      }

      public boolean removeAll(Collection c) {
         try {
            return super.removeAll((Collection)Preconditions.checkNotNull(c));
         } catch (UnsupportedOperationException var6) {
            Set<K> toRemove = Sets.newHashSet();

            for(Map.Entry entry : this.map().entrySet()) {
               if (c.contains(entry.getValue())) {
                  toRemove.add(entry.getKey());
               }
            }

            return this.map().keySet().removeAll(toRemove);
         }
      }

      public boolean retainAll(Collection c) {
         try {
            return super.retainAll((Collection)Preconditions.checkNotNull(c));
         } catch (UnsupportedOperationException var6) {
            Set<K> toRetain = Sets.newHashSet();

            for(Map.Entry entry : this.map().entrySet()) {
               if (c.contains(entry.getValue())) {
                  toRetain.add(entry.getKey());
               }
            }

            return this.map().keySet().retainAll(toRetain);
         }
      }

      public int size() {
         return this.map().size();
      }

      public boolean isEmpty() {
         return this.map().isEmpty();
      }

      public boolean contains(Object o) {
         return this.map().containsValue(o);
      }

      public void clear() {
         this.map().clear();
      }
   }

   abstract static class EntrySet extends Sets.ImprovedAbstractSet {
      abstract Map map();

      public int size() {
         return this.map().size();
      }

      public void clear() {
         this.map().clear();
      }

      public boolean contains(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry<?, ?> entry = (Map.Entry)o;
            Object key = entry.getKey();
            V value = (V)Maps.safeGet(this.map(), key);
            return Objects.equals(value, entry.getValue()) && (value != null || this.map().containsKey(key));
         }
      }

      public boolean isEmpty() {
         return this.map().isEmpty();
      }

      public boolean remove(Object o) {
         if (this.contains(o)) {
            Map.Entry<?, ?> entry = (Map.Entry)o;
            return this.map().keySet().remove(entry.getKey());
         } else {
            return false;
         }
      }

      public boolean removeAll(Collection c) {
         try {
            return super.removeAll((Collection)Preconditions.checkNotNull(c));
         } catch (UnsupportedOperationException var3) {
            return Sets.removeAllImpl(this, (Iterator)c.iterator());
         }
      }

      public boolean retainAll(Collection c) {
         try {
            return super.retainAll((Collection)Preconditions.checkNotNull(c));
         } catch (UnsupportedOperationException var7) {
            Set<Object> keys = Sets.newHashSetWithExpectedSize(c.size());

            for(Object o : c) {
               if (this.contains(o)) {
                  Map.Entry<?, ?> entry = (Map.Entry)o;
                  keys.add(entry.getKey());
               }
            }

            return this.map().keySet().retainAll(keys);
         }
      }
   }
}
