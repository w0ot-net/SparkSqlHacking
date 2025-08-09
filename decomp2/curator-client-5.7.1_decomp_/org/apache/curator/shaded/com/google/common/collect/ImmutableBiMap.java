package org.apache.curator.shaded.com.google.common.collect;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collector;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotCall;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
public abstract class ImmutableBiMap extends ImmutableBiMapFauxverideShim implements BiMap {
   public static Collector toImmutableBiMap(Function keyFunction, Function valueFunction) {
      return CollectCollectors.toImmutableBiMap(keyFunction, valueFunction);
   }

   public static ImmutableBiMap of() {
      return RegularImmutableBiMap.EMPTY;
   }

   public static ImmutableBiMap of(Object k1, Object v1) {
      return new SingletonImmutableBiMap(k1, v1);
   }

   public static ImmutableBiMap of(Object k1, Object v1, Object k2, Object v2) {
      return RegularImmutableBiMap.fromEntries(entryOf(k1, v1), entryOf(k2, v2));
   }

   public static ImmutableBiMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3) {
      return RegularImmutableBiMap.fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3));
   }

   public static ImmutableBiMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4) {
      return RegularImmutableBiMap.fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4));
   }

   public static ImmutableBiMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5) {
      return RegularImmutableBiMap.fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5));
   }

   public static ImmutableBiMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6) {
      return RegularImmutableBiMap.fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5), entryOf(k6, v6));
   }

   public static ImmutableBiMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6, Object k7, Object v7) {
      return RegularImmutableBiMap.fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5), entryOf(k6, v6), entryOf(k7, v7));
   }

   public static ImmutableBiMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6, Object k7, Object v7, Object k8, Object v8) {
      return RegularImmutableBiMap.fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5), entryOf(k6, v6), entryOf(k7, v7), entryOf(k8, v8));
   }

   public static ImmutableBiMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6, Object k7, Object v7, Object k8, Object v8, Object k9, Object v9) {
      return RegularImmutableBiMap.fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5), entryOf(k6, v6), entryOf(k7, v7), entryOf(k8, v8), entryOf(k9, v9));
   }

   public static ImmutableBiMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6, Object k7, Object v7, Object k8, Object v8, Object k9, Object v9, Object k10, Object v10) {
      return RegularImmutableBiMap.fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5), entryOf(k6, v6), entryOf(k7, v7), entryOf(k8, v8), entryOf(k9, v9), entryOf(k10, v10));
   }

   @SafeVarargs
   public static ImmutableBiMap ofEntries(Map.Entry... entries) {
      return RegularImmutableBiMap.fromEntries(entries);
   }

   public static Builder builder() {
      return new Builder();
   }

   public static Builder builderWithExpectedSize(int expectedSize) {
      CollectPreconditions.checkNonnegative(expectedSize, "expectedSize");
      return new Builder(expectedSize);
   }

   public static ImmutableBiMap copyOf(Map map) {
      if (map instanceof ImmutableBiMap) {
         ImmutableBiMap<K, V> bimap = (ImmutableBiMap)map;
         if (!bimap.isPartialView()) {
            return bimap;
         }
      }

      return copyOf((Iterable)map.entrySet());
   }

   public static ImmutableBiMap copyOf(Iterable entries) {
      Map.Entry<K, V>[] entryArray = (Map.Entry[])Iterables.toArray(entries, (Object[])EMPTY_ENTRY_ARRAY);
      switch (entryArray.length) {
         case 0:
            return of();
         case 1:
            Map.Entry<K, V> entry = entryArray[0];
            return of(entry.getKey(), entry.getValue());
         default:
            return RegularImmutableBiMap.fromEntries(entryArray);
      }
   }

   ImmutableBiMap() {
   }

   public abstract ImmutableBiMap inverse();

   public ImmutableSet values() {
      return this.inverse().keySet();
   }

   final ImmutableSet createValues() {
      throw new AssertionError("should never be called");
   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final Object forcePut(Object key, Object value) {
      throw new UnsupportedOperationException();
   }

   @J2ktIncompatible
   Object writeReplace() {
      return new SerializedForm(this);
   }

   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Use SerializedForm");
   }

   public static final class Builder extends ImmutableMap.Builder {
      public Builder() {
      }

      Builder(int size) {
         super(size);
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
      public Builder putAll(Map map) {
         super.putAll(map);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder putAll(Iterable entries) {
         super.putAll(entries);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder orderEntriesByValue(Comparator valueComparator) {
         super.orderEntriesByValue(valueComparator);
         return this;
      }

      @CanIgnoreReturnValue
      Builder combine(ImmutableMap.Builder builder) {
         super.combine(builder);
         return this;
      }

      public ImmutableBiMap build() {
         return this.buildOrThrow();
      }

      public ImmutableBiMap buildOrThrow() {
         switch (this.size) {
            case 0:
               return ImmutableBiMap.of();
            case 1:
               Map.Entry<K, V> onlyEntry = (Map.Entry)Objects.requireNonNull(this.entries[0]);
               return ImmutableBiMap.of(onlyEntry.getKey(), onlyEntry.getValue());
            default:
               if (this.valueComparator != null) {
                  if (this.entriesUsed) {
                     this.entries = (Map.Entry[])Arrays.copyOf(this.entries, this.size);
                  }

                  Arrays.sort(this.entries, 0, this.size, Ordering.from(this.valueComparator).onResultOf(Maps.valueFunction()));
               }

               this.entriesUsed = true;
               return RegularImmutableBiMap.fromEntryArray(this.size, this.entries);
         }
      }

      /** @deprecated */
      @Deprecated
      @DoNotCall
      public ImmutableBiMap buildKeepingLast() {
         throw new UnsupportedOperationException("Not supported for bimaps");
      }

      @VisibleForTesting
      ImmutableBiMap buildJdkBacked() {
         Preconditions.checkState(this.valueComparator == null, "buildJdkBacked is for tests only, doesn't support orderEntriesByValue");
         switch (this.size) {
            case 0:
               return ImmutableBiMap.of();
            case 1:
               Map.Entry<K, V> onlyEntry = (Map.Entry)Objects.requireNonNull(this.entries[0]);
               return ImmutableBiMap.of(onlyEntry.getKey(), onlyEntry.getValue());
            default:
               this.entriesUsed = true;
               return RegularImmutableBiMap.fromEntryArray(this.size, this.entries);
         }
      }
   }

   @J2ktIncompatible
   private static class SerializedForm extends ImmutableMap.SerializedForm {
      private static final long serialVersionUID = 0L;

      SerializedForm(ImmutableBiMap bimap) {
         super(bimap);
      }

      Builder makeBuilder(int size) {
         return new Builder(size);
      }
   }
}
