package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import javax.annotation.CheckForNull;

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
