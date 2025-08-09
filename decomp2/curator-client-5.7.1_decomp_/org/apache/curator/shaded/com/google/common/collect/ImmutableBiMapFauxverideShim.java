package org.apache.curator.shaded.com.google.common.collect;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotCall;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
abstract class ImmutableBiMapFauxverideShim extends ImmutableMap {
   /** @deprecated */
   @Deprecated
   @DoNotCall("Use toImmutableBiMap")
   public static Collector toImmutableMap(Function keyFunction, Function valueFunction) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Use toImmutableBiMap")
   public static Collector toImmutableMap(Function keyFunction, Function valueFunction, BinaryOperator mergeFunction) {
      throw new UnsupportedOperationException();
   }
}
