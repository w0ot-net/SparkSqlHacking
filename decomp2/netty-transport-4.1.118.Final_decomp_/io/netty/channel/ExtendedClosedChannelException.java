package io.netty.channel;

import java.nio.channels.ClosedChannelException;

final class ExtendedClosedChannelException extends ClosedChannelException {
   ExtendedClosedChannelException(Throwable cause) {
      if (cause != null) {
         this.initCause(cause);
      }

   }

   public Throwable fillInStackTrace() {
      return this;
   }
}
