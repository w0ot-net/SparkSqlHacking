package io.vertx.core.impl;

import java.util.concurrent.Executor;

public interface EventExecutor extends Executor {
   boolean inThread();
}
