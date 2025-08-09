package org.apache.curator.shaded.com.google.common.util.concurrent;

import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@FunctionalInterface
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface AsyncFunction {
   ListenableFuture apply(@ParametricNullness Object input) throws Exception;
}
