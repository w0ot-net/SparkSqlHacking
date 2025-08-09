package org.glassfish.jersey.internal.guava;

import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public interface LoadingCache extends Cache, Function {
   Object get(Object var1) throws ExecutionException;

   /** @deprecated */
   @Deprecated
   Object apply(Object var1);
}
