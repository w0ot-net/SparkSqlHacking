package io.vertx.core.eventbus.impl;

import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;

public interface EventBusInternal extends EventBus {
   void start(Promise var1);

   void close(Promise var1);
}
