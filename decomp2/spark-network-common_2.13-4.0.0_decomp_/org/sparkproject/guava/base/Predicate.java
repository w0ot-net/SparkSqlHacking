package org.sparkproject.guava.base;

import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

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
