package org.sparkproject.guava.util.concurrent;

import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface FutureCallback {
   void onSuccess(@ParametricNullness Object result);

   void onFailure(Throwable t);
}
