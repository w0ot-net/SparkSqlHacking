package org.sparkproject.guava.util.concurrent;

import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class NullnessCasts {
   @ParametricNullness
   static Object uncheckedCastNullableTToT(@CheckForNull Object t) {
      return t;
   }

   @ParametricNullness
   static Object uncheckedNull() {
      return null;
   }

   private NullnessCasts() {
   }
}
