package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.Spliterator;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
final class SingletonImmutableList extends ImmutableList {
   final transient Object element;

   SingletonImmutableList(Object element) {
      this.element = Preconditions.checkNotNull(element);
   }

   public Object get(int index) {
      Preconditions.checkElementIndex(index, 1);
      return this.element;
   }

   public UnmodifiableIterator iterator() {
      return Iterators.singletonIterator(this.element);
   }

   public Spliterator spliterator() {
      return Collections.singleton(this.element).spliterator();
   }

   public int size() {
      return 1;
   }

   public ImmutableList subList(int fromIndex, int toIndex) {
      Preconditions.checkPositionIndexes(fromIndex, toIndex, 1);
      return (ImmutableList)(fromIndex == toIndex ? ImmutableList.of() : this);
   }

   public String toString() {
      return '[' + this.element.toString() + ']';
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
