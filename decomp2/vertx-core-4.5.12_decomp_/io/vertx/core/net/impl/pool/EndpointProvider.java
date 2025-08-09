package io.vertx.core.net.impl.pool;

import io.vertx.core.impl.ContextInternal;

public interface EndpointProvider {
   Endpoint create(ContextInternal var1, Runnable var2);
}
