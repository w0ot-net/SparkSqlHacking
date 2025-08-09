package org.sparkproject.guava.util.concurrent;

import org.sparkproject.guava.annotations.GwtCompatible;

@FunctionalInterface
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface AsyncCallable {
   ListenableFuture call() throws Exception;
}
