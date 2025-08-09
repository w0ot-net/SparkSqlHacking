package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.util.Collection;
import java.util.Set;
import shaded.parquet.it.unimi.dsi.fastutil.Size64;

public interface LongSet extends LongCollection, Set {
   LongIterator iterator();

   default LongSpliterator spliterator() {
      return LongSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Collection)this), 321);
   }

   boolean remove(long var1);

   /** @deprecated */
   @Deprecated
   default boolean remove(Object o) {
      return LongCollection.super.remove(o);
   }

   /** @deprecated */
   @Deprecated
   default boolean add(Long o) {
      return LongCollection.super.add(o);
   }

   /** @deprecated */
   @Deprecated
   default boolean contains(Object o) {
      return LongCollection.super.contains(o);
   }

   /** @deprecated */
   @Deprecated
   default boolean rem(long k) {
      return this.remove(k);
   }

   static LongSet of() {
      return LongSets.UNMODIFIABLE_EMPTY_SET;
   }

   static LongSet of(long e) {
      return LongSets.singleton(e);
   }

   static LongSet of(long e0, long e1) {
      LongArraySet innerSet = new LongArraySet(2);
      innerSet.add(e0);
      if (!innerSet.add(e1)) {
         throw new IllegalArgumentException("Duplicate element: " + e1);
      } else {
         return LongSets.unmodifiable(innerSet);
      }
   }

   static LongSet of(long e0, long e1, long e2) {
      LongArraySet innerSet = new LongArraySet(3);
      innerSet.add(e0);
      if (!innerSet.add(e1)) {
         throw new IllegalArgumentException("Duplicate element: " + e1);
      } else if (!innerSet.add(e2)) {
         throw new IllegalArgumentException("Duplicate element: " + e2);
      } else {
         return LongSets.unmodifiable(innerSet);
      }
   }

   static LongSet of(long... a) {
      switch (a.length) {
         case 0:
            return of();
         case 1:
            return of(a[0]);
         case 2:
            return of(a[0], a[1]);
         case 3:
            return of(a[0], a[1], a[2]);
         default:
            LongSet innerSet = (LongSet)(a.length <= 4 ? new LongArraySet(a.length) : new LongOpenHashSet(a.length));

            for(long element : a) {
               if (!innerSet.add(element)) {
                  throw new IllegalArgumentException("Duplicate element: " + element);
               }
            }

            return LongSets.unmodifiable(innerSet);
      }
   }
}
