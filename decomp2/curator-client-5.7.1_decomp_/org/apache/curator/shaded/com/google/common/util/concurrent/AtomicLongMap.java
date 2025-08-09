package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@GwtCompatible
@J2ktIncompatible
public final class AtomicLongMap implements Serializable {
   private final ConcurrentHashMap map;
   @CheckForNull
   private transient Map asMap;

   private AtomicLongMap(ConcurrentHashMap map) {
      this.map = (ConcurrentHashMap)Preconditions.checkNotNull(map);
   }

   public static AtomicLongMap create() {
      return new AtomicLongMap(new ConcurrentHashMap());
   }

   public static AtomicLongMap create(Map m) {
      AtomicLongMap<K> result = create();
      result.putAll(m);
      return result;
   }

   public long get(Object key) {
      return (Long)this.map.getOrDefault(key, 0L);
   }

   @CanIgnoreReturnValue
   public long incrementAndGet(Object key) {
      return this.addAndGet(key, 1L);
   }

   @CanIgnoreReturnValue
   public long decrementAndGet(Object key) {
      return this.addAndGet(key, -1L);
   }

   @CanIgnoreReturnValue
   public long addAndGet(Object key, long delta) {
      return this.accumulateAndGet(key, delta, Long::sum);
   }

   @CanIgnoreReturnValue
   public long getAndIncrement(Object key) {
      return this.getAndAdd(key, 1L);
   }

   @CanIgnoreReturnValue
   public long getAndDecrement(Object key) {
      return this.getAndAdd(key, -1L);
   }

   @CanIgnoreReturnValue
   public long getAndAdd(Object key, long delta) {
      return this.getAndAccumulate(key, delta, Long::sum);
   }

   @CanIgnoreReturnValue
   public long updateAndGet(Object key, LongUnaryOperator updaterFunction) {
      Preconditions.checkNotNull(updaterFunction);
      return (Long)this.map.compute(key, (k, value) -> updaterFunction.applyAsLong(value == null ? 0L : value));
   }

   @CanIgnoreReturnValue
   public long getAndUpdate(Object key, LongUnaryOperator updaterFunction) {
      Preconditions.checkNotNull(updaterFunction);
      AtomicLong holder = new AtomicLong();
      this.map.compute(key, (k, value) -> {
         long oldValue = value == null ? 0L : value;
         holder.set(oldValue);
         return updaterFunction.applyAsLong(oldValue);
      });
      return holder.get();
   }

   @CanIgnoreReturnValue
   public long accumulateAndGet(Object key, long x, LongBinaryOperator accumulatorFunction) {
      Preconditions.checkNotNull(accumulatorFunction);
      return this.updateAndGet(key, (oldValue) -> accumulatorFunction.applyAsLong(oldValue, x));
   }

   @CanIgnoreReturnValue
   public long getAndAccumulate(Object key, long x, LongBinaryOperator accumulatorFunction) {
      Preconditions.checkNotNull(accumulatorFunction);
      return this.getAndUpdate(key, (oldValue) -> accumulatorFunction.applyAsLong(oldValue, x));
   }

   @CanIgnoreReturnValue
   public long put(Object key, long newValue) {
      return this.getAndUpdate(key, (x) -> newValue);
   }

   public void putAll(Map m) {
      m.forEach(this::put);
   }

   @CanIgnoreReturnValue
   public long remove(Object key) {
      Long result = (Long)this.map.remove(key);
      return result == null ? 0L : result;
   }

   boolean remove(Object key, long value) {
      return this.map.remove(key, value);
   }

   @CanIgnoreReturnValue
   public boolean removeIfZero(Object key) {
      return this.remove(key, 0L);
   }

   public void removeAllZeros() {
      this.map.values().removeIf((x) -> x == 0L);
   }

   public long sum() {
      return this.map.values().stream().mapToLong(Long::longValue).sum();
   }

   public Map asMap() {
      Map<K, Long> result = this.asMap;
      return result == null ? (this.asMap = this.createAsMap()) : result;
   }

   private Map createAsMap() {
      return Collections.unmodifiableMap(this.map);
   }

   public boolean containsKey(Object key) {
      return this.map.containsKey(key);
   }

   public int size() {
      return this.map.size();
   }

   public boolean isEmpty() {
      return this.map.isEmpty();
   }

   public void clear() {
      this.map.clear();
   }

   public String toString() {
      return this.map.toString();
   }

   long putIfAbsent(Object key, long newValue) {
      AtomicBoolean noValue = new AtomicBoolean(false);
      Long result = (Long)this.map.compute(key, (k, oldValue) -> {
         if (oldValue != null && oldValue != 0L) {
            return oldValue;
         } else {
            noValue.set(true);
            return newValue;
         }
      });
      return noValue.get() ? 0L : result;
   }

   boolean replace(Object key, long expectedOldValue, long newValue) {
      if (expectedOldValue == 0L) {
         return this.putIfAbsent(key, newValue) == 0L;
      } else {
         return this.map.replace(key, expectedOldValue, newValue);
      }
   }
}
