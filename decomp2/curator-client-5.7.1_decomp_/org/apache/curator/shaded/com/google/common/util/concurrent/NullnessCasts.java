package org.apache.curator.shaded.com.google.common.util.concurrent;

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
   static Object uncheckedNull() {
      return null;
   }

   private NullnessCasts() {
   }
}
