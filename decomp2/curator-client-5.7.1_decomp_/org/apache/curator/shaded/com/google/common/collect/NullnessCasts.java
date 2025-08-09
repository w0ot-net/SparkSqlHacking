package org.apache.curator.shaded.com.google.common.collect;

import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class NullnessCasts {
   @ParametricNullness
   static Object uncheckedCastNullableTToT(@CheckForNull Object t) {
      return t;
   }

   @ParametricNullness
   static Object unsafeNull() {
      return null;
   }

   private NullnessCasts() {
   }
}
