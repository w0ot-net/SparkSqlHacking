package org.apache.curator.shaded.com.google.common.util.concurrent;

import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface FutureCallback {
   void onSuccess(@ParametricNullness Object result);

   void onFailure(Throwable t);
}
