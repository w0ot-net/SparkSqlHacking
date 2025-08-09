package org.sparkproject.guava.collect;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
final class JdkBackedImmutableMap extends ImmutableMap {
   private final transient Map delegateMap;
   private final transient ImmutableList entries;

   static ImmutableMap create(int n, Map.Entry[] entryArray, boolean throwIfDuplicateKeys) {
      Map<K, V> delegateMap = Maps.newHashMapWithExpectedSize(n);
      Map<K, V> duplicates = null;
      int dupCount = 0;

      for(int i = 0; i < n; ++i) {
         entryArray[i] = RegularImmutableMap.makeImmutable((Map.Entry)Objects.requireNonNull(entryArray[i]));
         K key = (K)entryArray[i].getKey();
         V value = (V)entryArray[i].getValue();
         V oldValue = (V)delegateMap.put(key, value);
         if (oldValue != null) {
            if (throwIfDuplicateKeys) {
               throw conflictException("key", entryArray[i], entryArray[i].getKey() + "=" + oldValue);
            }

            if (duplicates == null) {
               duplicates = new HashMap();
            }

            duplicates.put(key, value);
            ++dupCount;
         }
      }

      if (duplicates != null) {
         Map.Entry<K, V>[] newEntryArray = new Map.Entry[n - dupCount];
         int inI = 0;

         for(int outI = 0; inI < n; ++inI) {
            Map.Entry<K, V> entry = (Map.Entry)Objects.requireNonNull(entryArray[inI]);
            K key = (K)entry.getKey();
            if (duplicates.containsKey(key)) {
               V value = (V)duplicates.get(key);
               if (value == null) {
                  continue;
               }

               entry = new ImmutableMapEntry(key, value);
               duplicates.put(key, (Object)null);
            }

            newEntryArray[outI++] = entry;
         }

         entryArray = newEntryArray;
      }

      return new JdkBackedImmutableMap(delegateMap, ImmutableList.asImmutableList(entryArray, n));
   }

   JdkBackedImmutableMap(Map delegateMap, ImmutableList entries) {
      this.delegateMap = delegateMap;
      this.entries = entries;
   }

   public int size() {
      return this.entries.size();
   }

   @CheckForNull
   public Object get(@CheckForNull Object key) {
      return this.delegateMap.get(key);
   }

   ImmutableSet createEntrySet() {
      return new ImmutableMapEntrySet.RegularEntrySet(this, this.entries);
   }

   public void forEach(BiConsumer action) {
      Preconditions.checkNotNull(action);
      this.entries.forEach((e) -> action.accept(e.getKey(), e.getValue()));
   }

   ImmutableSet createKeySet() {
      return new ImmutableMapKeySet(this);
   }

   ImmutableCollection createValues() {
      return new ImmutableMapValues(this);
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
