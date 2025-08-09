package org.apache.curator.shaded.com.google.common.collect;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotCall;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotMock;
import org.apache.curator.shaded.com.google.j2objc.annotations.Weak;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public abstract class ImmutableMultimap extends BaseImmutableMultimap implements Serializable {
   final transient ImmutableMap map;
   final transient int size;
   @J2ktIncompatible
   private static final long serialVersionUID = 0L;

   public static ImmutableMultimap of() {
      return ImmutableListMultimap.of();
   }

   public static ImmutableMultimap of(Object k1, Object v1) {
      return ImmutableListMultimap.of(k1, v1);
   }

   public static ImmutableMultimap of(Object k1, Object v1, Object k2, Object v2) {
      return ImmutableListMultimap.of(k1, v1, k2, v2);
   }

   public static ImmutableMultimap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3) {
      return ImmutableListMultimap.of(k1, v1, k2, v2, k3, v3);
   }

   public static ImmutableMultimap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4) {
      return ImmutableListMultimap.of(k1, v1, k2, v2, k3, v3, k4, v4);
   }

   public static ImmutableMultimap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5) {
      return ImmutableListMultimap.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5);
   }

   public static Builder builder() {
      return new Builder();
   }

   public static ImmutableMultimap copyOf(Multimap multimap) {
      if (multimap instanceof ImmutableMultimap) {
         ImmutableMultimap<K, V> kvMultimap = (ImmutableMultimap)multimap;
         if (!kvMultimap.isPartialView()) {
            return kvMultimap;
         }
      }

      return ImmutableListMultimap.copyOf(multimap);
   }

   public static ImmutableMultimap copyOf(Iterable entries) {
      return ImmutableListMultimap.copyOf(entries);
   }

   ImmutableMultimap(ImmutableMap map, int size) {
      this.map = map;
      this.size = size;
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public ImmutableCollection removeAll(@CheckForNull Object key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public ImmutableCollection replaceValues(Object key, Iterable values) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final void clear() {
      throw new UnsupportedOperationException();
   }

   public abstract ImmutableCollection get(Object key);

   public abstract ImmutableMultimap inverse();

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final boolean put(Object key, Object value) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final boolean putAll(Object key, Iterable values) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final boolean putAll(Multimap multimap) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final boolean remove(@CheckForNull Object key, @CheckForNull Object value) {
      throw new UnsupportedOperationException();
   }

   boolean isPartialView() {
      return this.map.isPartialView();
   }

   public boolean containsKey(@CheckForNull Object key) {
      return this.map.containsKey(key);
   }

   public boolean containsValue(@CheckForNull Object value) {
      return value != null && super.containsValue(value);
   }

   public int size() {
      return this.size;
   }

   public ImmutableSet keySet() {
      return this.map.keySet();
   }

   Set createKeySet() {
      throw new AssertionError("unreachable");
   }

   public ImmutableMap asMap() {
      return this.map;
   }

   Map createAsMap() {
      throw new AssertionError("should never be called");
   }

   public ImmutableCollection entries() {
      return (ImmutableCollection)super.entries();
   }

   ImmutableCollection createEntries() {
      return new EntryCollection(this);
   }

   UnmodifiableIterator entryIterator() {
      return new UnmodifiableIterator() {
         final Iterator asMapItr;
         @CheckForNull
         Object currentKey;
         Iterator valueItr;

         {
            this.asMapItr = ImmutableMultimap.this.map.entrySet().iterator();
            this.currentKey = null;
            this.valueItr = Iterators.emptyIterator();
         }

         public boolean hasNext() {
            return this.valueItr.hasNext() || this.asMapItr.hasNext();
         }

         public Map.Entry next() {
            if (!this.valueItr.hasNext()) {
               Map.Entry<K, ? extends ImmutableCollection<V>> entry = (Map.Entry)this.asMapItr.next();
               this.currentKey = entry.getKey();
               this.valueItr = ((ImmutableCollection)entry.getValue()).iterator();
            }

            return Maps.immutableEntry(Objects.requireNonNull(this.currentKey), this.valueItr.next());
         }
      };
   }

   Spliterator entrySpliterator() {
      return CollectSpliterators.flatMap(this.asMap().entrySet().spliterator(), (keyToValueCollectionEntry) -> {
         K key = (K)keyToValueCollectionEntry.getKey();
         Collection<V> valueCollection = (Collection)keyToValueCollectionEntry.getValue();
         return CollectSpliterators.map(valueCollection.spliterator(), (value) -> Maps.immutableEntry(key, value));
      }, 64 | (this instanceof SetMultimap ? 1 : 0), (long)this.size());
   }

   public void forEach(BiConsumer action) {
      Preconditions.checkNotNull(action);
      this.asMap().forEach((key, valueCollection) -> valueCollection.forEach((value) -> action.accept(key, value)));
   }

   public ImmutableMultiset keys() {
      return (ImmutableMultiset)super.keys();
   }

   ImmutableMultiset createKeys() {
      return new Keys();
   }

   public ImmutableCollection values() {
      return (ImmutableCollection)super.values();
   }

   ImmutableCollection createValues() {
      return new Values(this);
   }

   UnmodifiableIterator valueIterator() {
      return new UnmodifiableIterator() {
         Iterator valueCollectionItr;
         Iterator valueItr;

         {
            this.valueCollectionItr = ImmutableMultimap.this.map.values().iterator();
            this.valueItr = Iterators.emptyIterator();
         }

         public boolean hasNext() {
            return this.valueItr.hasNext() || this.valueCollectionItr.hasNext();
         }

         public Object next() {
            if (!this.valueItr.hasNext()) {
               this.valueItr = ((ImmutableCollection)this.valueCollectionItr.next()).iterator();
            }

            return this.valueItr.next();
         }
      };
   }

   @DoNotMock
   public static class Builder {
      final Map builderMap = Platform.preservesInsertionOrderOnPutsMap();
      @CheckForNull
      Comparator keyComparator;
      @CheckForNull
      Comparator valueComparator;

      Collection newMutableValueCollection() {
         return new ArrayList();
      }

      @CanIgnoreReturnValue
      public Builder put(Object key, Object value) {
         CollectPreconditions.checkEntryNotNull(key, value);
         Collection<V> valueCollection = (Collection)this.builderMap.get(key);
         if (valueCollection == null) {
            this.builderMap.put(key, valueCollection = this.newMutableValueCollection());
         }

         valueCollection.add(value);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder put(Map.Entry entry) {
         return this.put(entry.getKey(), entry.getValue());
      }

      @CanIgnoreReturnValue
      public Builder putAll(Iterable entries) {
         for(Map.Entry entry : entries) {
            this.put(entry);
         }

         return this;
      }

      @CanIgnoreReturnValue
      public Builder putAll(Object key, Iterable values) {
         if (key == null) {
            throw new NullPointerException("null key in entry: null=" + Iterables.toString(values));
         } else {
            Collection<V> valueCollection = (Collection)this.builderMap.get(key);
            if (valueCollection != null) {
               for(Object value : values) {
                  CollectPreconditions.checkEntryNotNull(key, value);
                  valueCollection.add(value);
               }

               return this;
            } else {
               Iterator<? extends V> valuesItr = values.iterator();
               if (!valuesItr.hasNext()) {
                  return this;
               } else {
                  valueCollection = this.newMutableValueCollection();

                  while(valuesItr.hasNext()) {
                     V value = (V)valuesItr.next();
                     CollectPreconditions.checkEntryNotNull(key, value);
                     valueCollection.add(value);
                  }

                  this.builderMap.put(key, valueCollection);
                  return this;
               }
            }
         }
      }

      @CanIgnoreReturnValue
      public Builder putAll(Object key, Object... values) {
         return this.putAll(key, (Iterable)Arrays.asList(values));
      }

      @CanIgnoreReturnValue
      public Builder putAll(Multimap multimap) {
         for(Map.Entry entry : multimap.asMap().entrySet()) {
            this.putAll(entry.getKey(), (Iterable)entry.getValue());
         }

         return this;
      }

      @CanIgnoreReturnValue
      public Builder orderKeysBy(Comparator keyComparator) {
         this.keyComparator = (Comparator)Preconditions.checkNotNull(keyComparator);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder orderValuesBy(Comparator valueComparator) {
         this.valueComparator = (Comparator)Preconditions.checkNotNull(valueComparator);
         return this;
      }

      @CanIgnoreReturnValue
      Builder combine(Builder other) {
         for(Map.Entry entry : other.builderMap.entrySet()) {
            this.putAll(entry.getKey(), (Iterable)entry.getValue());
         }

         return this;
      }

      public ImmutableMultimap build() {
         Collection<Map.Entry<K, Collection<V>>> mapEntries = this.builderMap.entrySet();
         if (this.keyComparator != null) {
            mapEntries = Ordering.from(this.keyComparator).onKeys().immutableSortedCopy(mapEntries);
         }

         return ImmutableListMultimap.fromMapEntries(mapEntries, this.valueComparator);
      }
   }

   @GwtIncompatible
   @J2ktIncompatible
   static class FieldSettersHolder {
      static final Serialization.FieldSetter MAP_FIELD_SETTER = Serialization.getFieldSetter(ImmutableMultimap.class, "map");
      static final Serialization.FieldSetter SIZE_FIELD_SETTER = Serialization.getFieldSetter(ImmutableMultimap.class, "size");
   }

   private static class EntryCollection extends ImmutableCollection {
      @Weak
      final ImmutableMultimap multimap;
      private static final long serialVersionUID = 0L;

      EntryCollection(ImmutableMultimap multimap) {
         this.multimap = multimap;
      }

      public UnmodifiableIterator iterator() {
         return this.multimap.entryIterator();
      }

      boolean isPartialView() {
         return this.multimap.isPartialView();
      }

      public int size() {
         return this.multimap.size();
      }

      public boolean contains(@CheckForNull Object object) {
         if (object instanceof Map.Entry) {
            Map.Entry<?, ?> entry = (Map.Entry)object;
            return this.multimap.containsEntry(entry.getKey(), entry.getValue());
         } else {
            return false;
         }
      }
   }

   class Keys extends ImmutableMultiset {
      public boolean contains(@CheckForNull Object object) {
         return ImmutableMultimap.this.containsKey(object);
      }

      public int count(@CheckForNull Object element) {
         Collection<V> values = (Collection)ImmutableMultimap.this.map.get(element);
         return values == null ? 0 : values.size();
      }

      public ImmutableSet elementSet() {
         return ImmutableMultimap.this.keySet();
      }

      public int size() {
         return ImmutableMultimap.this.size();
      }

      Multiset.Entry getEntry(int index) {
         Map.Entry<K, ? extends Collection<V>> entry = (Map.Entry)ImmutableMultimap.this.map.entrySet().asList().get(index);
         return Multisets.immutableEntry(entry.getKey(), ((Collection)entry.getValue()).size());
      }

      boolean isPartialView() {
         return true;
      }

      @GwtIncompatible
      @J2ktIncompatible
      Object writeReplace() {
         return new KeysSerializedForm(ImmutableMultimap.this);
      }

      @GwtIncompatible
      @J2ktIncompatible
      private void readObject(ObjectInputStream stream) throws InvalidObjectException {
         throw new InvalidObjectException("Use KeysSerializedForm");
      }
   }

   @GwtIncompatible
   @J2ktIncompatible
   private static final class KeysSerializedForm implements Serializable {
      final ImmutableMultimap multimap;

      KeysSerializedForm(ImmutableMultimap multimap) {
         this.multimap = multimap;
      }

      Object readResolve() {
         return this.multimap.keys();
      }
   }

   private static final class Values extends ImmutableCollection {
      @Weak
      private final transient ImmutableMultimap multimap;
      @J2ktIncompatible
      private static final long serialVersionUID = 0L;

      Values(ImmutableMultimap multimap) {
         this.multimap = multimap;
      }

      public boolean contains(@CheckForNull Object object) {
         return this.multimap.containsValue(object);
      }

      public UnmodifiableIterator iterator() {
         return this.multimap.valueIterator();
      }

      @GwtIncompatible
      int copyIntoArray(@Nullable Object[] dst, int offset) {
         for(ImmutableCollection valueCollection : this.multimap.map.values()) {
            offset = valueCollection.copyIntoArray(dst, offset);
         }

         return offset;
      }

      public int size() {
         return this.multimap.size();
      }

      boolean isPartialView() {
         return true;
      }
   }
}
