package shaded.parquet.it.unimi.dsi.fastutil.booleans;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;
import shaded.parquet.it.unimi.dsi.fastutil.Size64;

public interface BooleanCollection extends Collection, BooleanIterable {
   BooleanIterator iterator();

   default BooleanSpliterator spliterator() {
      return BooleanSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Collection)this), 320);
   }

   boolean add(boolean var1);

   boolean contains(boolean var1);

   boolean rem(boolean var1);

   /** @deprecated */
   @Deprecated
   default boolean add(Boolean key) {
      return this.add(key);
   }

   /** @deprecated */
   @Deprecated
   default boolean contains(Object key) {
      return key == null ? false : this.contains((Boolean)key);
   }

   /** @deprecated */
   @Deprecated
   default boolean remove(Object key) {
      return key == null ? false : this.rem((Boolean)key);
   }

   boolean[] toBooleanArray();

   /** @deprecated */
   @Deprecated
   default boolean[] toBooleanArray(boolean[] a) {
      return this.toArray(a);
   }

   boolean[] toArray(boolean[] var1);

   boolean addAll(BooleanCollection var1);

   boolean containsAll(BooleanCollection var1);

   boolean removeAll(BooleanCollection var1);

   /** @deprecated */
   @Deprecated
   default boolean removeIf(Predicate filter) {
      return this.removeIf(filter instanceof BooleanPredicate ? (BooleanPredicate)filter : (key) -> filter.test(key));
   }

   default boolean removeIf(BooleanPredicate filter) {
      Objects.requireNonNull(filter);
      boolean removed = false;
      BooleanIterator each = this.iterator();

      while(each.hasNext()) {
         if (filter.test(each.nextBoolean())) {
            each.remove();
            removed = true;
         }
      }

      return removed;
   }

   boolean retainAll(BooleanCollection var1);
}
