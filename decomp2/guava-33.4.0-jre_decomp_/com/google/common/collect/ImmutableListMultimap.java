package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
public class ImmutableListMultimap extends ImmutableMultimap implements ListMultimap {
   @LazyInit
   @CheckForNull
   @RetainedWith
   private transient ImmutableListMultimap inverse;
   @GwtIncompatible
   @J2ktIncompatible
   private static final long serialVersionUID = 0L;

   public static Collector toImmutableListMultimap(Function keyFunction, Function valueFunction) {
      return CollectCollectors.toImmutableListMultimap(keyFunction, valueFunction);
   }

   public static Collector flatteningToImmutableListMultimap(Function keyFunction, Function valuesFunction) {
      return CollectCollectors.flatteningToImmutableListMultimap(keyFunction, valuesFunction);
   }

   public static ImmutableListMultimap of() {
      return EmptyImmutableListMultimap.INSTANCE;
   }

   public static ImmutableListMultimap of(Object k1, Object v1) {
      Builder<K, V> builder = builder();
      builder.put(k1, v1);
      return builder.build();
   }

   public static ImmutableListMultimap of(Object k1, Object v1, Object k2, Object v2) {
      Builder<K, V> builder = builder();
      builder.put(k1, v1);
      builder.put(k2, v2);
      return builder.build();
   }

   public static ImmutableListMultimap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3) {
      Builder<K, V> builder = builder();
      builder.put(k1, v1);
      builder.put(k2, v2);
      builder.put(k3, v3);
      return builder.build();
   }

   public static ImmutableListMultimap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4) {
      Builder<K, V> builder = builder();
      builder.put(k1, v1);
      builder.put(k2, v2);
      builder.put(k3, v3);
      builder.put(k4, v4);
      return builder.build();
   }

   public static ImmutableListMultimap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5) {
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

   public static ImmutableListMultimap copyOf(Multimap multimap) {
      if (multimap.isEmpty()) {
         return of();
      } else {
         if (multimap instanceof ImmutableListMultimap) {
            ImmutableListMultimap<K, V> kvMultimap = (ImmutableListMultimap)multimap;
            if (!kvMultimap.isPartialView()) {
               return kvMultimap;
            }
         }

         return fromMapEntries(multimap.asMap().entrySet(), (Comparator)null);
      }
   }

   public static ImmutableListMultimap copyOf(Iterable entries) {
      return (new Builder()).putAll(entries).build();
   }

   static ImmutableListMultimap fromMapEntries(Collection mapEntries, @CheckForNull Comparator valueComparator) {
      if (mapEntries.isEmpty()) {
         return of();
      } else {
         ImmutableMap.Builder<K, ImmutableList<V>> builder = new ImmutableMap.Builder(mapEntries.size());
         int size = 0;

         for(Map.Entry entry : mapEntries) {
            K key = (K)entry.getKey();
            Collection<? extends V> values = (Collection)entry.getValue();
            ImmutableList<V> list = valueComparator == null ? ImmutableList.copyOf(values) : ImmutableList.sortedCopyOf(valueComparator, values);
            if (!list.isEmpty()) {
               builder.put(key, list);
               size += list.size();
            }
         }

         return new ImmutableListMultimap(builder.buildOrThrow(), size);
      }
   }

   static ImmutableListMultimap fromMapBuilderEntries(Collection mapEntries, @CheckForNull Comparator valueComparator) {
      if (mapEntries.isEmpty()) {
         return of();
      } else {
         ImmutableMap.Builder<K, ImmutableList<V>> builder = new ImmutableMap.Builder(mapEntries.size());
         int size = 0;

         for(Map.Entry entry : mapEntries) {
            K key = (K)entry.getKey();
            ImmutableList.Builder<V> values = (ImmutableList.Builder)entry.getValue();
            ImmutableList<V> list = valueComparator == null ? values.build() : values.buildSorted(valueComparator);
            builder.put(key, list);
            size += list.size();
         }

         return new ImmutableListMultimap(builder.buildOrThrow(), size);
      }
   }

   ImmutableListMultimap(ImmutableMap map, int size) {
      super(map, size);
   }

   public ImmutableList get(Object key) {
      ImmutableList<V> list = (ImmutableList)this.map.get(key);
      return list == null ? ImmutableList.of() : list;
   }

   public ImmutableListMultimap inverse() {
      ImmutableListMultimap<V, K> result = this.inverse;
      return result == null ? (this.inverse = this.invert()) : result;
   }

   private ImmutableListMultimap invert() {
      Builder<V, K> builder = builder();

      for(Map.Entry entry : this.entries()) {
         builder.put(entry.getValue(), entry.getKey());
      }

      ImmutableListMultimap<V, K> invertedMultimap = builder.build();
      invertedMultimap.inverse = this;
      return invertedMultimap;
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final ImmutableList removeAll(@CheckForNull Object key) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final ImmutableList replaceValues(Object key, Iterable values) {
      throw new UnsupportedOperationException();
   }

   @GwtIncompatible
   @J2ktIncompatible
   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      Serialization.writeMultimap(this, stream);
   }

   @GwtIncompatible
   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      int keyCount = stream.readInt();
      if (keyCount < 0) {
         throw new InvalidObjectException("Invalid key count " + keyCount);
      } else {
         ImmutableMap.Builder<Object, ImmutableList<Object>> builder = ImmutableMap.builder();
         int tmpSize = 0;

         for(int i = 0; i < keyCount; ++i) {
            Object key = Objects.requireNonNull(stream.readObject());
            int valueCount = stream.readInt();
            if (valueCount <= 0) {
               throw new InvalidObjectException("Invalid value count " + valueCount);
            }

            ImmutableList.Builder<Object> valuesBuilder = ImmutableList.builder();

            for(int j = 0; j < valueCount; ++j) {
               valuesBuilder.add(Objects.requireNonNull(stream.readObject()));
            }

            builder.put(key, valuesBuilder.build());
            tmpSize += valueCount;
         }

         ImmutableMap<Object, ImmutableList<Object>> tmpMap;
         try {
            tmpMap = builder.buildOrThrow();
         } catch (IllegalArgumentException e) {
            throw (InvalidObjectException)(new InvalidObjectException(e.getMessage())).initCause(e);
         }

         ImmutableMultimap.FieldSettersHolder.MAP_FIELD_SETTER.set(this, tmpMap);
         ImmutableMultimap.FieldSettersHolder.SIZE_FIELD_SETTER.set(this, tmpSize);
      }
   }

   public static final class Builder extends ImmutableMultimap.Builder {
      public Builder() {
      }

      Builder(int expectedKeys) {
         super(expectedKeys);
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
         super.putAll(key, values);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder putAll(Multimap multimap) {
         super.putAll(multimap);
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

      public ImmutableListMultimap build() {
         return (ImmutableListMultimap)super.build();
      }
   }
}
