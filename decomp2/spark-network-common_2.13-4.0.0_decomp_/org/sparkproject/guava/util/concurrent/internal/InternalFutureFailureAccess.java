package org.sparkproject.guava.util.concurrent.internal;

public abstract class InternalFutureFailureAccess {
   protected InternalFutureFailureAccess() {
   }

   protected abstract Throwable tryInternalFastPathGetFailure();
}
