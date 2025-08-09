package org.apache.curator.shaded.com.google.common.collect;

import java.util.Spliterator;
import java.util.Spliterators;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
class RegularImmutableList extends ImmutableList {
   static final ImmutableList EMPTY = new RegularImmutableList(new Object[0]);
   @VisibleForTesting
   final transient Object[] array;

   RegularImmutableList(Object[] array) {
      this.array = array;
   }

   public int size() {
      return this.array.length;
   }

   boolean isPartialView() {
      return false;
   }

   Object[] internalArray() {
      return this.array;
   }

   int internalArrayStart() {
      return 0;
   }

   int internalArrayEnd() {
      return this.array.length;
   }

   int copyIntoArray(@Nullable Object[] dst, int dstOff) {
      System.arraycopy(this.array, 0, dst, dstOff, this.array.length);
      return dstOff + this.array.length;
   }

   public Object get(int index) {
      return this.array[index];
   }

   public UnmodifiableListIterator listIterator(int index) {
      return Iterators.forArray(this.array, 0, this.array.length, index);
   }

   public Spliterator spliterator() {
      return Spliterators.spliterator(this.array, 1296);
   }
}
