package org.apache.curator.shaded.com.google.common.collect;

import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotCall;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
abstract class ImmutableSortedMultisetFauxverideShim extends ImmutableMultiset {
   /** @deprecated */
   @Deprecated
   @DoNotCall("Use toImmutableSortedMultiset.")
   public static Collector toImmutableMultiset() {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Use toImmutableSortedMultiset.")
   public static Collector toImmutableMultiset(Function elementFunction, ToIntFunction countFunction) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Use naturalOrder.")
   public static ImmutableSortedMultiset.Builder builder() {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Elements must be Comparable. (Or, pass a Comparator to orderedBy or copyOf.)")
   public static ImmutableSortedMultiset of(Object element) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Elements must be Comparable. (Or, pass a Comparator to orderedBy or copyOf.)")
   public static ImmutableSortedMultiset of(Object e1, Object e2) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Elements must be Comparable. (Or, pass a Comparator to orderedBy or copyOf.)")
   public static ImmutableSortedMultiset of(Object e1, Object e2, Object e3) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Elements must be Comparable. (Or, pass a Comparator to orderedBy or copyOf.)")
   public static ImmutableSortedMultiset of(Object e1, Object e2, Object e3, Object e4) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Elements must be Comparable. (Or, pass a Comparator to orderedBy or copyOf.)")
   public static ImmutableSortedMultiset of(Object e1, Object e2, Object e3, Object e4, Object e5) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Elements must be Comparable. (Or, pass a Comparator to orderedBy or copyOf.)")
   public static ImmutableSortedMultiset of(Object e1, Object e2, Object e3, Object e4, Object e5, Object e6, Object... remaining) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Elements must be Comparable. (Or, pass a Comparator to orderedBy or copyOf.)")
   public static ImmutableSortedMultiset copyOf(Object[] elements) {
      throw new UnsupportedOperationException();
   }
}
