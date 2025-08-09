package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import javax.annotation.CheckForNull;

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
