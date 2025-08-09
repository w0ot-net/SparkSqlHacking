package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;

@FunctionalInterface
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface AsyncFunction {
   ListenableFuture apply(@ParametricNullness Object input) throws Exception;
}
