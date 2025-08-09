package io.vertx.core.spi.cluster.impl.selector;

import java.util.Collections;
import java.util.List;

class SimpleRoundRobinSelector implements RoundRobinSelector {
   private final List nodeIds;
   private final Index index;

   SimpleRoundRobinSelector(List nodeIds) {
      if (nodeIds.size() > 1) {
         this.nodeIds = Collections.unmodifiableList(nodeIds);
         this.index = new Index(nodeIds.size());
      } else {
         this.nodeIds = Collections.singletonList(nodeIds.get(0));
         this.index = null;
      }

   }

   public String selectForSend() {
      return this.index == null ? (String)this.nodeIds.get(0) : (String)this.nodeIds.get(this.index.nextVal());
   }

   public Iterable selectForPublish() {
      return this.nodeIds;
   }
}
