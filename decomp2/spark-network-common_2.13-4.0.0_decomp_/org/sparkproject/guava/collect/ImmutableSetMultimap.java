package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import com.google.j2objc.annotations.Weak;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.MoreObjects;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
public class ImmutableSetMultimap extends ImmutableMultimap implements SetMultimap {
   private final transient ImmutableSet emptySet;
   @LazyInit
   @CheckForNull
   @RetainedWith
   private transient ImmutableSetMultimap inverse;
   @LazyInit
   @CheckForNull
   @RetainedWith
   private transient ImmutableSet entries;
   @GwtIncompatible
   @J2ktIncompatible
   private static final long serialVersionUID = 0L;

   public static Collector toImmutableSetMultimap(Function keyFunction, Function valueFunction) {
      return CollectCollectors.toImmutableSetMultimap(keyFunction, valueFunction);
   }

   public static Collector flatteningToImmutableSetMultimap(Function keyFunction, Function valuesFunction) {
      return CollectCollectors.flatteningToImmutableSetMultimap(keyFunction, valuesFunction);
   }

   public static ImmutableSetMultimap of() {
      return EmptyImmutableSetMultimap.INSTANCE;
   }

   public static ImmutableSetMultimap of(Object k1, Object v1) {
      Builder<K, V> builder = builder();
      builder.put(k1, v1);
      return builder.build();
   }

   public static ImmutableSetMultimap of(Object k1, Object v1, Object k2, Object v2) {
      Builder<K, V> builder = builder();
      builder.put(k1, v1);
      builder.put(k2, v2);
      return builder.build();
   }

   public static ImmutableSetMultimap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3) {
      Builder<K, V> builder = builder();
      builder.put(k1, v1);
      builder.put(k2, v2);
      builder.put(k3, v3);
      return builder.build();
   }

   public static ImmutableSetMultimap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4) {
      Builder<K, V> builder = builder();
      builder.put(k1, v1);
      builder.put(k2, v2);
      builder.put(k3, v3);
      builder.put(k4, v4);
      return builder.build();
   }

   public static ImmutableSetMultimap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5) {
      Builder<K, V> builder = builder();
      builder.put(k1, v1);
      builder.put(k2, v2);
      builder.put(k3, v3);
      builder.put(k4, v4);
      builder.put(k5, v5);
      return builder.build();
   }

   public static Builder builder() {
      return new Builder();
   }

   public static Builder builderWithExpectedKeys(int expectedKeys) {
      CollectPreconditions.checkNonnegative(expectedKeys, "expectedKeys");
      return new Builder(expectedKeys);
   }

   public static ImmutableSetMultimap copyOf(Multimap multimap) {
      return copyOf(multimap, (Comparator)null);
   }

   private static ImmutableSetMultimap copyOf(Multimap multimap, @CheckForNull Comparator valueComparator) {
      Preconditions.checkNotNull(multimap);
      if (multimap.isEmpty() && valueComparator == null) {
         return of();
      } else {
         if (multimap instanceof ImmutableSetMultimap) {
            ImmutableSetMultimap<K, V> kvMultimap = (ImmutableSetMultimap)multimap;
            if (!kvMultimap.isPartialView()) {
               return kvMultimap;
            }
         }

         return fromMapEntries(multimap.asMap().entrySet(), valueComparator);
      }
   }

   public static ImmutableSetMultimap copyOf(Iterable entries) {
      return (new Builder()).putAll(entries).build();
   }

   static ImmutableSetMultimap fromMapEntries(Collection mapEntries, @CheckForNull Comparator valueComparator) {
      if (mapEntries.isEmpty()) {
         return of();
      } else {
         ImmutableMap.Builder<K, ImmutableSet<V>> builder = new ImmutableMap.Builder(mapEntries.size());
         int size = 0;

         for(Map.Entry entry : mapEntries) {
            K key = (K)entry.getKey();
            Collection<? extends V> values = (Collection)entry.getValue();
            ImmutableSet<V> set = valueSet(valueComparator, values);
            if (!set.isEmpty()) {
               builder.put(key, set);
               size += set.size();
            }
         }

         return new ImmutableSetMultimap(builder.buildOrThrow(), size, valueComparator);
      }
   }

   static ImmutableSetMultimap fromMapBuilderEntries(Collection mapEntries, @CheckForNull Comparator valueComparator) {
      if (mapEntries.isEmpty()) {
         return of();
      } else {
         ImmutableMap.Builder<K, ImmutableSet<V>> builder = new ImmutableMap.Builder(mapEntries.size());
         int size = 0;

         for(Map.Entry entry : mapEntries) {
            K key = (K)entry.getKey();
            ImmutableSet.Builder<? extends V> values = (ImmutableSet.Builder)entry.getValue();
            ImmutableSet<V> set = valueSet(valueComparator, values.build());
            if (!set.isEmpty()) {
               builder.put(key, set);
               size += set.size();
            }
         }

         return new ImmutableSetMultimap(builder.buildOrThrow(), size, valueComparator);
      }
   }

   ImmutableSetMultimap(ImmutableMap map, int size, @CheckForNull Comparator valueComparator) {
      super(map, size);
      this.emptySet = emptySet(valueComparator);
   }

   public ImmutableSet get(Object key) {
      ImmutableSet<V> set = (ImmutableSet)this.map.get(key);
      return (ImmutableSet)MoreObjects.firstNonNull(set, this.emptySet);
   }

   public ImmutableSetMultimap inverse() {
      ImmutableSetMultimap<V, K> result = this.inverse;
      return result == null ? (this.inverse = this.invert()) : result;
   }

   private ImmutableSetMultimap invert() {
      Builder<V, K> builder = builder();

      for(Map.Entry entry : this.entries()) {
         builder.put(entry.getValue(), entry.getKey());
      }

      ImmutableSetMultimap<V, K> invertedMultimap = builder.build();
      invertedMultimap.inverse = this;
      return invertedMultimap;
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final ImmutableSet removeAll(@CheckForNull Object key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final ImmutableSet replaceValues(Object key, Iterable values) {
      throw new UnsupportedOperationException();
   }

   public ImmutableSet entries() {
      ImmutableSet<Map.Entry<K, V>> result = this.entries;
      return result == null ? (this.entries = new EntrySet(this)) : result;
   }

   private static ImmutableSet valueSet(@CheckForNull Comparator valueComparator, Collection values) {
      return (ImmutableSet)(valueComparator == null ? ImmutableSet.copyOf(values) : ImmutableSortedSet.copyOf(valueComparator, values));
   }

   private static ImmutableSet emptySet(@CheckForNull Comparator valueComparator) {
      return (ImmutableSet)(valueComparator == null ? ImmutableSet.of() : ImmutableSortedSet.emptySet(valueComparator));
   }

   private static ImmutableSet.Builder valuesBuilder(@CheckForNull Comparator valueComparator) {
      return (ImmutableSet.Builder)(valueComparator == null ? new ImmutableSet.Builder() : new ImmutableSortedSet.Builder(valueComparator));
   }

   @GwtIncompatible
   @J2ktIncompatible
   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      stream.writeObject(this.valueComparator());
      Serialization.writeMultimap(this, stream);
   }

   @CheckForNull
   Comparator valueComparator() {
      return this.emptySet instanceof ImmutableSortedSet ? ((ImmutableSortedSet)this.emptySet).comparator() : null;
   }

   @GwtIncompatible
   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      Comparator<Object> valueComparator = (Comparator)stream.readObject();
      int keyCount = stream.readInt();
      if (keyCount < 0) {
         throw new InvalidObjectException("Invalid key count " + keyCount);
      } else {
         ImmutableMap.Builder<Object, ImmutableSet<Object>> builder = ImmutableMap.builder();
         int tmpSize = 0;

         for(int i = 0; i < keyCount; ++i) {
            Object key = Objects.requireNonNull(stream.readObject());
            int valueCount = stream.readInt();
            if (valueCount <= 0) {
               throw new InvalidObjectException("Invalid value count " + valueCount);
            }

            ImmutableSet.Builder<Object> valuesBuilder = valuesBuilder(valueComparator);

            for(int j = 0; j < valueCount; ++j) {
               valuesBuilder.add(Objects.requireNonNull(stream.readObject()));
            }

            ImmutableSet<Object> valueSet = valuesBuilder.build();
            if (valueSet.size() != valueCount) {
               throw new InvalidObjectException("Duplicate key-value pairs exist for key " + key);
            }

            builder.put(key, valueSet);
            tmpSize += valueCount;
         }

         ImmutableMap<Object, ImmutableSet<Object>> tmpMap;
         try {
            tmpMap = builder.buildOrThrow();
         } catch (IllegalArgumentException e) {
            throw (InvalidObjectException)(new InvalidObjectException(e.getMessage())).initCause(e);
         }

         ImmutableMultimap.FieldSettersHolder.MAP_FIELD_SETTER.set(this, tmpMap);
         ImmutableMultimap.FieldSettersHolder.SIZE_FIELD_SETTER.set(this, tmpSize);
         ImmutableSetMultimap.SetFieldSettersHolder.EMPTY_SET_FIELD_SETTER.set(this, emptySet(valueComparator));
      }
   }

   public static final class Builder extends ImmutableMultimap.Builder {
      public Builder() {
      }

      Builder(int expectedKeys) {
         super(expectedKeys);
      }

      ImmutableCollection.Builder newValueCollectionBuilderWithExpectedSize(int expectedSize) {
         return (ImmutableCollection.Builder)(this.valueComparator == null ? ImmutableSet.builderWithExpectedSize(expectedSize) : new ImmutableSortedSet.Builder(this.valueComparator, expectedSize));
      }

      int expectedValueCollectionSize(int defaultExpectedValues, Iterable values) {
         if (values instanceof Set) {
            Set<?> collection = (Set)values;
            return Math.max(defaultExpectedValues, collection.size());
         } else {
            return defaultExpectedValues;
         }
      }

      @CanIgnoreReturnValue
      public Builder expectedValuesPerKey(int expectedValuesPerKey) {
         super.expectedValuesPerKey(expectedValuesPerKey);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder put(Object key, Object value) {
         super.put(key, value);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder put(Map.Entry entry) {
         super.put(entry);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder putAll(Iterable entries) {
         super.putAll(entries);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder putAll(Object key, Iterable values) {
         super.putAll(key, values);
         return this;
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
      Builder combine(ImmutableMultimap.Builder other) {
         super.combine(other);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder orderKeysBy(Comparator keyComparator) {
         super.orderKeysBy(keyComparator);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder orderValuesBy(Comparator valueComparator) {
         super.orderValuesBy(valueComparator);
         return this;
      }

      public ImmutableSetMultimap build() {
         if (this.builderMap == null) {
            return ImmutableSetMultimap.of();
         } else {
            Collection<Map.Entry<K, ImmutableCollection.Builder<V>>> mapEntries = this.builderMap.entrySet();
            if (this.keyComparator != null) {
               mapEntries = Ordering.from(this.keyComparator).onKeys().immutableSortedCopy(mapEntries);
            }

            return ImmutableSetMultimap.fromMapBuilderEntries(mapEntries, this.valueComparator);
         }
      }
   }

   private static final class EntrySet extends ImmutableSet {
      @Weak
      private final transient ImmutableSetMultimap multimap;

      EntrySet(ImmutableSetMultimap multimap) {
         this.multimap = multimap;
      }

      public boolean contains(@CheckForNull Object object) {
         if (object instanceof Map.Entry) {
            Map.Entry<?, ?> entry = (Map.Entry)object;
            return this.multimap.containsEntry(entry.getKey(), entry.getValue());
         } else {
            return false;
         }
      }

      public int size() {
         return this.multimap.size();
      }

      public UnmodifiableIterator iterator() {
         return this.multimap.entryIterator();
      }

      boolean isPartialView() {
         return false;
      }

      @J2ktIncompatible
      @GwtIncompatible
      Object writeReplace() {
         return super.writeReplace();
      }
   }

   @GwtIncompatible
   @J2ktIncompatible
   private static final class SetFieldSettersHolder {
      static final Serialization.FieldSetter EMPTY_SET_FIELD_SETTER = Serialization.getFieldSetter(ImmutableSetMultimap.class, "emptySet");
   }
}
