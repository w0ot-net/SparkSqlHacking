package io.netty.channel;

import io.netty.util.concurrent.PromiseAggregator;

/** @deprecated */
@Deprecated
public final class ChannelPromiseAggregator extends PromiseAggregator implements ChannelFutureListener {
   public ChannelPromiseAggregator(ChannelPromise aggregatePromise) {
      super(aggregatePromise);
   }
}
