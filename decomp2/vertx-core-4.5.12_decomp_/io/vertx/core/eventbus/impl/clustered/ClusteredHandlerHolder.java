package io.vertx.core.eventbus.impl.clustered;

import io.vertx.core.eventbus.impl.HandlerHolder;
import io.vertx.core.eventbus.impl.HandlerRegistration;
import io.vertx.core.impl.ContextInternal;

public class ClusteredHandlerHolder extends HandlerHolder {
   private final long seq;

   public ClusteredHandlerHolder(HandlerRegistration handler, boolean replyHandler, boolean localOnly, ContextInternal context, long seq) {
      super(handler, replyHandler, localOnly, context);
      this.seq = seq;
   }

   public long getSeq() {
      return this.seq;
   }
}
