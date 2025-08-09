package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class NullnessCasts {
   @ParametricNullness
   static Object uncheckedCastNullableTToT(@CheckForNull Object t) {
      return t;
   }

   private NullnessCasts() {
   }
}
