package io.netty.channel;

import io.netty.util.concurrent.PromiseNotifier;

/** @deprecated */
@Deprecated
public final class ChannelPromiseNotifier extends PromiseNotifier implements ChannelFutureListener {
   public ChannelPromiseNotifier(ChannelPromise... promises) {
      super(promises);
   }

   public ChannelPromiseNotifier(boolean logNotifyFailure, ChannelPromise... promises) {
      super(logNotifyFailure, promises);
   }
}
