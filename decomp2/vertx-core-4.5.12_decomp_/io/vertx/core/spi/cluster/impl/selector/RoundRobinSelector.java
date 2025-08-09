package io.vertx.core.spi.cluster.impl.selector;

public interface RoundRobinSelector {
   String selectForSend();

   Iterable selectForPublish();
}
