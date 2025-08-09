package org.apache.curator.shaded.com.google.common.collect;

import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotCall;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
abstract class ImmutableSortedMapFauxverideShim extends ImmutableMap {
   /** @deprecated */
   @Deprecated
   @DoNotCall("Use toImmutableSortedMap")
   public static Collector toImmutableMap(Function keyFunction, Function valueFunction) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Use toImmutableSortedMap")
   public static Collector toImmutableMap(Function keyFunction, Function valueFunction, BinaryOperator mergeFunction) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Use naturalOrder")
   public static ImmutableSortedMap.Builder builder() {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Use naturalOrder (which does not accept an expected size)")
   public static ImmutableSortedMap.Builder builderWithExpectedSize(int expectedSize) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass a key of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass keys of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1, Object k2, Object v2) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass keys of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass keys of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass keys of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass keys of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass keys of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6, Object k7, Object v7) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass keys of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6, Object k7, Object v7, Object k8, Object v8) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass keys of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6, Object k7, Object v7, Object k8, Object v8, Object k9, Object v9) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass keys of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6, Object k7, Object v7, Object k8, Object v8, Object k9, Object v9, Object k10, Object v10) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("ImmutableSortedMap.ofEntries not currently available; use ImmutableSortedMap.copyOf")
   public static ImmutableSortedMap ofEntries(Map.Entry... entries) {
      throw new UnsupportedOperationException();
   }
}
