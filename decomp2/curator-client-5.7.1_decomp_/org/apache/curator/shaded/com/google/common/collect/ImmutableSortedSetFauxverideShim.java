package org.apache.curator.shaded.com.google.common.collect;

import java.util.stream.Collector;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotCall;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
abstract class ImmutableSortedSetFauxverideShim extends ImmutableSet.CachingAsList {
   /** @deprecated */
   @Deprecated
   @DoNotCall("Use toImmutableSortedSet")
   public static Collector toImmutableSet() {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Use naturalOrder")
   public static ImmutableSortedSet.Builder builder() {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Use naturalOrder (which does not accept an expected size)")
   public static ImmutableSortedSet.Builder builderWithExpectedSize(int expectedSize) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass a parameter of type Comparable")
   public static ImmutableSortedSet of(Object element) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass parameters of type Comparable")
   public static ImmutableSortedSet of(Object e1, Object e2) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass parameters of type Comparable")
   public static ImmutableSortedSet of(Object e1, Object e2, Object e3) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass parameters of type Comparable")
   public static ImmutableSortedSet of(Object e1, Object e2, Object e3, Object e4) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass parameters of type Comparable")
   public static ImmutableSortedSet of(Object e1, Object e2, Object e3, Object e4, Object e5) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass parameters of type Comparable")
   public static ImmutableSortedSet of(Object e1, Object e2, Object e3, Object e4, Object e5, Object e6, Object... remaining) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass parameters of type Comparable")
   public static ImmutableSortedSet copyOf(Object[] elements) {
      throw new UnsupportedOperationException();
   }
}
