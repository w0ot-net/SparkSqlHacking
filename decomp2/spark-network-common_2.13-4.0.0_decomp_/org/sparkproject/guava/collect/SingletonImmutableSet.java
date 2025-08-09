package org.sparkproject.guava.collect;

import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
final class SingletonImmutableSet extends ImmutableSet {
   final transient Object element;

   SingletonImmutableSet(Object element) {
      this.element = Preconditions.checkNotNull(element);
   }

   public int size() {
      return 1;
   }

   public boolean contains(@CheckForNull Object target) {
      return this.element.equals(target);
   }

   public UnmodifiableIterator iterator() {
      return Iterators.singletonIterator(this.element);
   }

   public ImmutableList asList() {
      return ImmutableList.of(this.element);
   }

   boolean isPartialView() {
      return false;
   }

   int copyIntoArray(@Nullable Object[] dst, int offset) {
      dst[offset] = this.element;
      return offset + 1;
   }

   public final int hashCode() {
      return this.element.hashCode();
   }

   public String toString() {
      return '[' + this.element.toString() + ']';
   }

   @J2ktIncompatible
   @GwtIncompatible
   Object writeReplace() {
      return super.writeReplace();
   }
}
