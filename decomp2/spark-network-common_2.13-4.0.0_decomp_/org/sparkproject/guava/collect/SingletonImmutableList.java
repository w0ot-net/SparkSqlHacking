package org.sparkproject.guava.collect;

import java.util.Collections;
import java.util.Spliterator;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.Preconditions;

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
