package org.apache.curator.shaded.com.google.common.base;

import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@FunctionalInterface
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface Predicate extends java.util.function.Predicate {
   boolean apply(@ParametricNullness Object input);

   boolean equals(@CheckForNull Object object);

   default boolean test(@ParametricNullness Object input) {
      return this.apply(input);
   }
}
