package io.vertx.core.impl.future;

import io.netty.util.concurrent.FutureListener;
import io.vertx.core.Promise;
import io.vertx.core.impl.ContextInternal;

public interface PromiseInternal extends Promise, FutureListener, FutureInternal {
   ContextInternal context();
}
