package io.vertx.core.impl;

import io.vertx.core.Closeable;
import io.vertx.core.Vertx;

public interface WorkerExecutorInternal extends io.vertx.core.WorkerExecutor, Closeable {
   Vertx vertx();

   WorkerPool getPool();
}
