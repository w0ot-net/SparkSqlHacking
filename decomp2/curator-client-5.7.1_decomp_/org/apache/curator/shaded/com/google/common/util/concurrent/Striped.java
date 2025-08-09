package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.MoreObjects;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.base.Supplier;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.curator.shaded.com.google.common.collect.MapMaker;
import org.apache.curator.shaded.com.google.common.math.IntMath;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public abstract class Striped {
   private static final int LARGE_LAZY_CUTOFF = 1024;
   private static final int ALL_SET = -1;

   private Striped() {
   }

   public abstract Object get(Object key);

   public abstract Object getAt(int index);

   abstract int indexFor(Object key);

   public abstract int size();

   public Iterable bulkGet(Iterable keys) {
      List<Object> result = Lists.newArrayList(keys);
      if (result.isEmpty()) {
         return ImmutableList.of();
      } else {
         int[] stripes = new int[result.size()];

         for(int i = 0; i < result.size(); ++i) {
            stripes[i] = this.indexFor(result.get(i));
         }

         Arrays.sort(stripes);
         int previousStripe = stripes[0];
         result.set(0, this.getAt(previousStripe));

         for(int i = 1; i < result.size(); ++i) {
            int currentStripe = stripes[i];
            if (currentStripe == previousStripe) {
               result.set(i, result.get(i - 1));
            } else {
               result.set(i, this.getAt(currentStripe));
               previousStripe = currentStripe;
            }
         }

         return Collections.unmodifiableList(result);
      }
   }

   static Striped custom(int stripes, Supplier supplier) {
      return new CompactStriped(stripes, supplier);
   }

   public static Striped lock(int stripes) {
      return custom(stripes, PaddedLock::new);
   }

   public static Striped lazyWeakLock(int stripes) {
      return lazy(stripes, () -> new ReentrantLock(false));
   }

   private static Striped lazy(int stripes, Supplier supplier) {
      return (Striped)(stripes < 1024 ? new SmallLazyStriped(stripes, supplier) : new LargeLazyStriped(stripes, supplier));
   }

   public static Striped semaphore(int stripes, int permits) {
      return custom(stripes, () -> new PaddedSemaphore(permits));
   }

   public static Striped lazyWeakSemaphore(int stripes, int permits) {
      return lazy(stripes, () -> new Semaphore(permits, false));
   }

   public static Striped readWriteLock(int stripes) {
      return custom(stripes, ReentrantReadWriteLock::new);
   }

   public static Striped lazyWeakReadWriteLock(int stripes) {
      return lazy(stripes, WeakSafeReadWriteLock::new);
   }

   private static int ceilToPowerOfTwo(int x) {
      return 1 << IntMath.log2(x, RoundingMode.CEILING);
   }

   private static int smear(int hashCode) {
      hashCode ^= hashCode >>> 20 ^ hashCode >>> 12;
      return hashCode ^ hashCode >>> 7 ^ hashCode >>> 4;
   }

   private static final class WeakSafeReadWriteLock implements ReadWriteLock {
      private final ReadWriteLock delegate = new ReentrantReadWriteLock();

      WeakSafeReadWriteLock() {
      }

      public Lock readLock() {
         return new WeakSafeLock(this.delegate.readLock(), this);
      }

      public Lock writeLock() {
         return new WeakSafeLock(this.delegate.writeLock(), this);
      }
   }

   private static final class WeakSafeLock extends ForwardingLock {
      private final Lock delegate;
      private final WeakSafeReadWriteLock strongReference;

      WeakSafeLock(Lock delegate, WeakSafeReadWriteLock strongReference) {
         this.delegate = delegate;
         this.strongReference = strongReference;
      }

      Lock delegate() {
         return this.delegate;
      }

      public Condition newCondition() {
         return new WeakSafeCondition(this.delegate.newCondition(), this.strongReference);
      }
   }

   private static final class WeakSafeCondition extends ForwardingCondition {
      private final Condition delegate;
      private final WeakSafeReadWriteLock strongReference;

      WeakSafeCondition(Condition delegate, WeakSafeReadWriteLock strongReference) {
         this.delegate = delegate;
         this.strongReference = strongReference;
      }

      Condition delegate() {
         return this.delegate;
      }
   }

   private abstract static class PowerOfTwoStriped extends Striped {
      final int mask;

      PowerOfTwoStriped(int stripes) {
         Preconditions.checkArgument(stripes > 0, "Stripes must be positive");
         this.mask = stripes > 1073741824 ? -1 : Striped.ceilToPowerOfTwo(stripes) - 1;
      }

      final int indexFor(Object key) {
         int hash = Striped.smear(key.hashCode());
         return hash & this.mask;
      }

      public final Object get(Object key) {
         return this.getAt(this.indexFor(key));
      }
   }

   private static class CompactStriped extends PowerOfTwoStriped {
      private final Object[] array;

      private CompactStriped(int stripes, Supplier supplier) {
         super(stripes);
         Preconditions.checkArgument(stripes <= 1073741824, "Stripes must be <= 2^30)");
         this.array = new Object[this.mask + 1];

         for(int i = 0; i < this.array.length; ++i) {
            this.array[i] = supplier.get();
         }

      }

      public Object getAt(int index) {
         return this.array[index];
      }

      public int size() {
         return this.array.length;
      }
   }

   @VisibleForTesting
   static class SmallLazyStriped extends PowerOfTwoStriped {
      final AtomicReferenceArray locks;
      final Supplier supplier;
      final int size;
      final ReferenceQueue queue = new ReferenceQueue();

      SmallLazyStriped(int stripes, Supplier supplier) {
         super(stripes);
         this.size = this.mask == -1 ? Integer.MAX_VALUE : this.mask + 1;
         this.locks = new AtomicReferenceArray(this.size);
         this.supplier = supplier;
      }

      public Object getAt(int index) {
         if (this.size != Integer.MAX_VALUE) {
            Preconditions.checkElementIndex(index, this.size());
         }

         ArrayReference<? extends L> existingRef = (ArrayReference)this.locks.get(index);
         L existing = (L)(existingRef == null ? null : existingRef.get());
         if (existing != null) {
            return existing;
         } else {
            L created = (L)this.supplier.get();
            ArrayReference<L> newRef = new ArrayReference(created, index, this.queue);

            while(!this.locks.compareAndSet(index, existingRef, newRef)) {
               existingRef = (ArrayReference)this.locks.get(index);
               existing = (L)(existingRef == null ? null : existingRef.get());
               if (existing != null) {
                  return existing;
               }
            }

            this.drainQueue();
            return created;
         }
      }

      private void drainQueue() {
         Reference<? extends L> ref;
         while((ref = this.queue.poll()) != null) {
            ArrayReference<? extends L> arrayRef = (ArrayReference)ref;
            this.locks.compareAndSet(arrayRef.index, arrayRef, (Object)null);
         }

      }

      public int size() {
         return this.size;
      }

      private static final class ArrayReference extends WeakReference {
         final int index;

         ArrayReference(Object referent, int index, ReferenceQueue queue) {
            super(referent, queue);
            this.index = index;
         }
      }
   }

   @VisibleForTesting
   static class LargeLazyStriped extends PowerOfTwoStriped {
      final ConcurrentMap locks;
      final Supplier supplier;
      final int size;

      LargeLazyStriped(int stripes, Supplier supplier) {
         super(stripes);
         this.size = this.mask == -1 ? Integer.MAX_VALUE : this.mask + 1;
         this.supplier = supplier;
         this.locks = (new MapMaker()).weakValues().makeMap();
      }

      public Object getAt(int index) {
         if (this.size != Integer.MAX_VALUE) {
            Preconditions.checkElementIndex(index, this.size());
         }

         L existing = (L)this.locks.get(index);
         if (existing != null) {
            return existing;
         } else {
            L created = (L)this.supplier.get();
            existing = (L)this.locks.putIfAbsent(index, created);
            return MoreObjects.firstNonNull(existing, created);
         }
      }

      public int size() {
         return this.size;
      }
   }

   private static class PaddedLock extends ReentrantLock {
      long unused1;
      long unused2;
      long unused3;

      PaddedLock() {
         super(false);
      }
   }

   private static class PaddedSemaphore extends Semaphore {
      long unused1;
      long unused2;
      long unused3;

      PaddedSemaphore(int permits) {
         super(permits, false);
      }
   }
}
