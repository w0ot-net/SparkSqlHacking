package org.apache.curator.shaded.com.google.common.util.concurrent.internal;

public abstract class InternalFutureFailureAccess {
   protected InternalFutureFailureAccess() {
   }

   protected abstract Throwable tryInternalFastPathGetFailure();
}
