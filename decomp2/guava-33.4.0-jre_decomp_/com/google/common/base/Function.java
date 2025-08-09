package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import javax.annotation.CheckForNull;

@FunctionalInterface
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface Function extends java.util.function.Function {
   @ParametricNullness
   Object apply(@ParametricNullness Object input);

   boolean equals(@CheckForNull Object object);
}
