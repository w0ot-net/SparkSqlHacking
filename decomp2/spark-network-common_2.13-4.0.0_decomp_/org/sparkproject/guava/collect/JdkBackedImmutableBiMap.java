package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.util.Map;
import java.util.Objects;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
final class JdkBackedImmutableBiMap extends ImmutableBiMap {
   private final transient ImmutableList entries;
   private final Map forwardDelegate;
   private final Map backwardDelegate;
   @LazyInit
   @CheckForNull
   @RetainedWith
   private transient JdkBackedImmutableBiMap inverse;

   static ImmutableBiMap create(int n, @Nullable Map.Entry[] entryArray) {
      Map<K, V> forwardDelegate = Maps.newHashMapWithExpectedSize(n);
      Map<V, K> backwardDelegate = Maps.newHashMapWithExpectedSize(n);

      for(int i = 0; i < n; ++i) {
         Map.Entry<K, V> e = RegularImmutableMap.makeImmutable((Map.Entry)Objects.requireNonNull(entryArray[i]));
         entryArray[i] = e;
         V oldValue = (V)forwardDelegate.putIfAbsent(e.getKey(), e.getValue());
         if (oldValue != null) {
            throw conflictException("key", e.getKey() + "=" + oldValue, entryArray[i]);
         }

         K oldKey = (K)backwardDelegate.putIfAbsent(e.getValue(), e.getKey());
         if (oldKey != null) {
            throw conflictException("value", oldKey + "=" + e.getValue(), entryArray[i]);
         }
      }

      ImmutableList<Map.Entry<K, V>> entryList = ImmutableList.asImmutableList(entryArray, n);
      return new JdkBackedImmutableBiMap(entryList, forwardDelegate, backwardDelegate);
   }

   private JdkBackedImmutableBiMap(ImmutableList entries, Map forwardDelegate, Map backwardDelegate) {
      this.entries = entries;
      this.forwardDelegate = forwardDelegate;
      this.backwardDelegate = backwardDelegate;
   }

   public int size() {
      return this.entries.size();
   }

   public ImmutableBiMap inverse() {
      JdkBackedImmutableBiMap<V, K> result = this.inverse;
      if (result == null) {
         this.inverse = result = new JdkBackedImmutableBiMap(new InverseEntries(), this.backwardDelegate, this.forwardDelegate);
         result.inverse = this;
      }

      return result;
   }

   @CheckForNull
   public Object get(@CheckForNull Object key) {
      return this.forwardDelegate.get(key);
   }

   ImmutableSet createEntrySet() {
      return new ImmutableMapEntrySet.RegularEntrySet(this, this.entries);
   }

   ImmutableSet createKeySet() {
      return new ImmutableMapKeySet(this);
   }

   boolean isPartialView() {
      return false;
   }

   @J2ktIncompatible
   @GwtIncompatible
   Object writeReplace() {
      return super.writeReplace();
   }

   private final class InverseEntries extends ImmutableList {
      private InverseEntries() {
      }

      public Map.Entry get(int index) {
         Map.Entry<K, V> entry = (Map.Entry)JdkBackedImmutableBiMap.this.entries.get(index);
         return Maps.immutableEntry(entry.getValue(), entry.getKey());
      }

      boolean isPartialView() {
         return false;
      }

      public int size() {
         return JdkBackedImmutableBiMap.this.entries.size();
      }

      @J2ktIncompatible
      @GwtIncompatible
      Object writeReplace() {
         return super.writeReplace();
      }
   }
}
