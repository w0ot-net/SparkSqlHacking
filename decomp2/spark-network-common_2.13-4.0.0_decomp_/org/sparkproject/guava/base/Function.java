package org.sparkproject.guava.base;

import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

@FunctionalInterface
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface Function extends java.util.function.Function {
   @ParametricNullness
   Object apply(@ParametricNullness Object input);

   boolean equals(@CheckForNull Object object);
}
