package org.apache.curator.shaded.com.google.common.base;

import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@FunctionalInterface
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface Function extends java.util.function.Function {
   @ParametricNullness
   Object apply(@ParametricNullness Object input);

   boolean equals(@CheckForNull Object object);
}
