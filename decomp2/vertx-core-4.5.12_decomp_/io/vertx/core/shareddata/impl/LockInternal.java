package io.vertx.core.shareddata.impl;

import io.vertx.core.shareddata.Lock;

public interface LockInternal extends Lock {
   int waiters();
}
