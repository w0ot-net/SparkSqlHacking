package io.vertx.core.spi.cluster.impl.selector;

import java.util.Collections;

enum NullRoundRobinSelector implements RoundRobinSelector {
   INSTANCE;

   public String selectForSend() {
      return null;
   }

   public Iterable selectForPublish() {
      return Collections.emptyList();
   }
}
