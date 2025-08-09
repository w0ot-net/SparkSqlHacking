package org.apache.curator.shaded.com.google.common.collect;

import java.util.Spliterator;
import java.util.function.Consumer;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
abstract class IndexedImmutableSet extends ImmutableSet.CachingAsList {
   abstract Object get(int index);

   public UnmodifiableIterator iterator() {
      return this.asList().iterator();
   }

   public Spliterator spliterator() {
      return CollectSpliterators.indexed(this.size(), 1297, this::get);
   }

   public void forEach(Consumer consumer) {
      Preconditions.checkNotNull(consumer);
      int n = this.size();

      for(int i = 0; i < n; ++i) {
         consumer.accept(this.get(i));
      }

   }

   @GwtIncompatible
   int copyIntoArray(@Nullable Object[] dst, int offset) {
      return this.asList().copyIntoArray(dst, offset);
   }

   ImmutableList createAsList() {
      return new ImmutableAsList() {
         public Object get(int index) {
            return IndexedImmutableSet.this.get(index);
         }

         boolean isPartialView() {
            return IndexedImmutableSet.this.isPartialView();
         }

         public int size() {
            return IndexedImmutableSet.this.size();
         }

         ImmutableCollection delegateCollection() {
            return IndexedImmutableSet.this;
         }
      };
   }
}
