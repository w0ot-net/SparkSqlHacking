package org.sparkproject.guava.util.concurrent;

import org.sparkproject.guava.annotations.GwtCompatible;

@FunctionalInterface
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface AsyncFunction {
   ListenableFuture apply(@ParametricNullness Object input) throws Exception;
}
