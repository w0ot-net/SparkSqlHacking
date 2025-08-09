package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface FutureCallback {
   void onSuccess(@ParametricNullness Object result);

   void onFailure(Throwable t);
}
